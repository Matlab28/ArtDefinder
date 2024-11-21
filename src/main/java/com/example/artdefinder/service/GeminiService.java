package com.example.artdefinder.service;

import com.example.artdefinder.dto.gemini.Root;
import com.example.artdefinder.dto.request.ArtRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface GeminiService {
    Root processArtRequest(ArtRequestDto dto);

    Root getLatestResponse();
}
