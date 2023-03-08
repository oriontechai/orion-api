package com.orion.orionapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatCompletionUsageDto {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
