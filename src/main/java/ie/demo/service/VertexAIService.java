package ie.demo.service;

import com.google.cloud.vertexai.Transport;
import com.google.cloud.vertexai.VertexAI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VertexAIService {

    @Value("${ENV_REGION:region}")
    private String region;
    @Value("${ENV_PROJECT_ID:project-id}")
    private String project;
    @Value("${ENV_MODEL:model}")
    private String model;

    private ChatModel geminiChatModel;

    @PostConstruct
    void initModel() {
        geminiChatModel = new VertexAiGeminiChatModel(
                new VertexAI.Builder()
                    .setLocation(region)
                    .setProjectId(project)
                    .setTransport(Transport.REST)
                .build(),
                VertexAiGeminiChatOptions.builder()
                    .model(model)
                    .temperature(0.2)
                    .topK(5)
                    .topP(0.95)
                .build());
    }

    public List<String> synonyms(String word) {
        String prompt = "Provide only a simple comma separated list of up to 5 synonyms for the word: " + word;
        log.info(prompt);
        ChatResponse response = geminiChatModel.call(new Prompt(prompt));
        String content = response.getResult().getOutput().getContent();
        log.info("VertexAI response: {}", content);
        return Arrays.stream(content.split(",")).collect(Collectors.toList());
    }
}
