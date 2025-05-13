package br.com.programadorbr.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatementsService {

    private final ChatModel chatModel;

    public StatementsService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getStatements(String names) {

        var template = """
                Crie um JSON contendo informações sobre as seguintes pessoas/personagens: {names}.
                Para cada nome, gere exatamente três sentenças curtas sobre essa pessoa/personagem.
                As sentenças devem ser curtas e devem estar na primeira pessoa do singular.
                Uma das sentenças deve ser falsa (mentira).
                O formato de saída deve ser apenas um JSON válido e conter um array de objetos chamado "characters".
                Cada objeto deve ter os seguintes campos:
                * "name: o nome da pessoa ou personagem",
                * "statements: um array com três sentenças curtas (strings)"
                * "lie_index: o índice (0, 1 ou 2) da sentença que é falsa"
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "names", names
        );
        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }

}
