package com.example.artdefinder.dto.request;

import com.example.artdefinder.constant.Language;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArtRequestDto {
//    private String image;
    private Language language;
    private String question;
    private String geminiResponse;
}
