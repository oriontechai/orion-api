package com.orion.orionapi.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private String text;
    private boolean isFromUser;
}
