package com.example.artdefinder.dto.response;

import com.example.artdefinder.constant.Language;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArtResponseDto {
    private Long id;
    private Language language;
    private String question;
    private String geminiResponse;
}
