package com.orion.orionapi.service;

import com.orion.orionapi.dto.ChatCompletionMessageDto;
import com.orion.orionapi.dto.ChatCompletionResponseDto;
import com.orion.orionapi.dto.MessageDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {

    private final String OPENAI_CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";
    private final String OPENAI_API_KEY = "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";
    private final String MODEL = "gpt-3.5-turbo";
    private final RestTemplate restTemplate;

    public ChatService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public MessageDto getCompletion(List<MessageDto> messages) {
        ResponseEntity<ChatCompletionResponseDto> response;
        response = this.restTemplate.postForEntity(
                OPENAI_CHAT_COMPLETION_URL,
                getRequestEntity(getRequestHeaders(), messages),
                ChatCompletionResponseDto.class
        );

        return MessageDto
                .builder()
                .isFromUser(false)
                .text(response.getBody().getChoices().get(0).getMessage().getContent())
                .build();
    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        return headers;
    }

    private HttpEntity<Map<String, Object>> getRequestEntity(HttpHeaders headers, List<MessageDto> messages) {
        Map<String, Object> map = new HashMap<>();
        map.put("model", MODEL);
        map.put("messages", getMessages(messages));
        map.put("max_tokens", 400);

        return new HttpEntity<>(map, headers);
    }

    private List<ChatCompletionMessageDto> getMessages(List<MessageDto> inputMessages) {
        List<ChatCompletionMessageDto> messages = new ArrayList<>();
        ChatCompletionMessageDto context = ChatCompletionMessageDto
                .builder()
                .role("system")
                .content("Eres un asistente útil llamado Orion. Eres un chatbot que ofrece servicios de inteligencia artificial para empresas pequeñas y medianas." +
                        " La empresa que representas ofrece dos servicios o productos. " +
                        "Un plan basico de 300 dolares al mes con mas de 1000 interacciones al mes" +
                        " y ofreces un plan premium de 1000 dolares con interacciones ilimitadas." +
                        "En caso de no saber la respuesta con la informacion que se te da contesta dicinedo 'No lo sé, lo lamento.'")
                .build();
        messages.add(context);

        messages.addAll(inputMessages.stream().map(m -> ChatCompletionMessageDto
                .builder()
                .role(m.isFromUser() ? "user" : "assistant")
                .content(m.getText())
                .build()).toList()
        );
        return messages;
    }
}
