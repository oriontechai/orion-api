package com.orion.orionapi.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatCompletionResponseDto {
    private String id;
    private String object;
    private long created;
    private List<ChoiceDto> choices;
    private ChatCompletionUsageDto usage;
}
