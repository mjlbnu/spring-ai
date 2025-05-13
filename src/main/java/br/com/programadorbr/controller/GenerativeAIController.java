package br.com.programadorbr.controller;

import br.com.programadorbr.service.ChatService;
import br.com.programadorbr.service.StatementsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerativeAIController {

    private final ChatService chatService;
    private final StatementsService statementsService;

    public GenerativeAIController(ChatService chatService, StatementsService statementsService) {
        this.chatService = chatService;
        this.statementsService = statementsService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-with-options")
    public String getResponseWithOptions(@RequestParam String prompt) {
        return chatService.getResponseWithOptions(prompt);
    }

    @GetMapping("statements-creator")
    public String getStatements(@RequestParam String names) {
        return statementsService.getStatements(names);
    }
}
