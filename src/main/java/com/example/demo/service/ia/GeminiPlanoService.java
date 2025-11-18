package com.example.demo.service.ia;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiPlanoService {

    private final WebClient webClient;

    // O Spring injeta o WebClient que configuramos no AppConfig
    public GeminiPlanoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getSustainabilityPlan(String context, String goal) {
        // Estrutura para os dados que sua API Python espera
        class PlanRequest {
            public String userContext;
            public String sustainabilityGoal;
            public PlanRequest(String uc, String sg) {
                this.userContext = uc;
                this.sustainabilityGoal = sg;
            }
        }

        // Estrutura para a resposta JSON que sua API Python retorna
        class PlanResponse {
            // Deve ter o mesmo nome do campo JSON: "plan"
            public String plan;
            public String getPlan() { return plan; }
            // Adicione um construtor vazio para desserialização
            public PlanResponse() {}
        }

        try {
            return webClient.post()
                    .uri("/api/generate-plan")
                    .bodyValue(new PlanRequest(context, goal))
                    .retrieve()
                    .bodyToMono(PlanResponse.class) // Mapeia o JSON para o objeto Java
                    .map(PlanResponse::getPlan)     // Extrai apenas o valor do campo "plan"
                    .block();                       // Espera o resultado da API Python

        } catch (Exception e) {
            System.err.println("Erro ao chamar a API Flask: " + e.getMessage());
            return "Falha na comunicação com o serviço de IA.";
        }
    }
}