package com.orion.orionapi.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChoiceDto {
    private int index;
    private ChatCompletionMessageDto message;
    private String finishReason;
}
