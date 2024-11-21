package com.example.artdefinder.dto.gemini;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Part {
    private String text;

    @Override
    public String toString() {
        return "Part{" +
                "text='" + text + '\'' +
                '}';
    }
}
