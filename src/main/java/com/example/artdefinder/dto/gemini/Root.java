package com.example.artdefinder.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Root {
    private ArrayList<Candidate> candidates;
    private UsageMetadata usageMetadata;

    @Override
    public String toString() {
        // Customize this to include relevant fields
        return "Root{" +
                "candidates='" + candidates + '\'' +
                ", usageMetadata='" + usageMetadata + '\'' +
                '}';
    }
}
