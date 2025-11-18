package com.example.demo.controller.ai;

import com.example.demo.service.ia.GeminiPlanoService;
import com.example.demo.service.ia.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controlador que retorna dados diretamente (API)
@RequestMapping("/api/v1/plano-missao")
public class PlanController {

    @Autowired
    GeminiPlanoService planoService;

    @Autowired
    public PlanController(GeminiPlanoService geminiPlanService) {
        this.planoService = geminiPlanService;
    }

    @GetMapping("/gerar")
    // O usuário acessará via: /api/v1/plano-missao/gerar?context=...&goal=...
    public String gerarPlanoMissao(
            @RequestParam("context") String userContext,
            @RequestParam("goal") String sustainabilityGoal)
    {
        // 1. Log de entrada (Boa Prática)
        System.out.println("Recebida requisição para: " + userContext + " - " + sustainabilityGoal);

        // 2. Chama o serviço que integra com a API Python
        String planoGerado = planoService.getSustainabilityPlan(userContext, sustainabilityGoal);

        // 3. Retorna o resultado
        return planoGerado;
    }
}
