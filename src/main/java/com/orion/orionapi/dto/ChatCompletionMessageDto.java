package com.orion.orionapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatCompletionMessageDto {
    private String role;
    private String content;
}
