package br.com.programadorbr.controller;

import br.com.programadorbr.service.ChatService;
import br.com.programadorbr.service.ImageService;
import br.com.programadorbr.service.StatementsService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAIController {

    private final ChatService chatService;
    private final StatementsService statementsService;
    private final ImageService imageService;

    public GenerativeAIController(ChatService chatService,
                                  StatementsService statementsService,
                                  ImageService imageService) {
        this.chatService = chatService;
        this.statementsService = statementsService;
        this.imageService = imageService;
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

    @GetMapping("generate-image")
    public List<String> generateImages(@RequestParam String prompt,
                                 @RequestParam(defaultValue = "hd") String quality,
                                 @RequestParam(defaultValue = "1") Integer quantity,
                                 @RequestParam(defaultValue = "1024") Integer height,
                                 @RequestParam(defaultValue = "1024") Integer width) {
        ImageResponse response = imageService.generateImage(prompt, quality, quantity, height, width);
        return response.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
    }
}
