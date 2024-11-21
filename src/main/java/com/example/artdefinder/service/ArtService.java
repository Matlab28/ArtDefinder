package com.example.artdefinder.service;

import com.example.artdefinder.client.GeminiApiClient;
import com.example.artdefinder.dto.gemini.Candidate;
import com.example.artdefinder.dto.gemini.Root;
import com.example.artdefinder.dto.request.ArtRequestDto;
import com.example.artdefinder.entity.ArtEntity;
import com.example.artdefinder.repository.ArtRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtService implements GeminiService {
    private final ModelMapper modelMapper;
    private final ArtRepository repository;
    private final GeminiApiClient client;

    @Value("${gemini.api.key}")
    private String key;
    private Root latestUpdate;


    @Override
    public Root processArtRequest(ArtRequestDto dto) {
        ArtEntity entity = modelMapper.map(dto, ArtEntity.class);

        String instruction = constructInstruction(dto);
        Root updates = getUpdates(instruction);
        String extractedText = extractedTextFromGeminiResponse(updates);
        dto.setGeminiResponse(extractedText);

        repository.save(entity);
        log.info("Art info has got");
        latestUpdate = updates;
        return latestUpdate;
    }

    @Override
    public Root getLatestResponse() {
        return latestUpdate;
    }

    private String constructInstruction(ArtRequestDto dto) {
        StringBuilder instruction = new StringBuilder();
        instruction.append("Response Language: ").append(dto.getLanguage()).append("\n");
        instruction.append("Question: ").append(dto.getQuestion()).append("\n");
        instruction.append("You will get image from camera as an input, and it will be art image. It might be" +
                " painting, monument or more. Based on image, provide user with detailed information about this" +
                " art. And additionally, user can ask a question (\"" + dto.getQuestion() + "\"). If user asks" +
                " a question, please take into account user's question while responding.");
        return instruction.toString();
    }

    private Root getUpdates(String instruction) {
        try {
            JsonObject json = new JsonObject();
            JsonArray partsArray = new JsonArray();
            JsonObject partsObject = new JsonObject();
            JsonArray contentsArray = new JsonArray();
            JsonObject contentsObject = new JsonObject();

            partsObject.add("text", new JsonPrimitive(instruction));
            partsArray.add(partsObject);
            contentsObject.add("parts", partsArray);
            contentsArray.add(contentsObject);
            json.add("contents", contentsArray);

            String content = json.toString();
            return client.getData(key, content);
        } catch (Exception e) {
            log.error("Error while getting response from Gemini AI: ", e);
            throw e;
        }
    }

    private String extractedTextFromGeminiResponse(Root updates) {
        StringBuilder textBuilder = new StringBuilder();

        if (updates.getCandidates() != null) {
            for (Candidate candidate : updates.getCandidates()) {
                String text = candidate.getContent().getParts().get(0).getText();
                text = text.replace("*", "");
                textBuilder.append(text).append("\n\n");
            }
        }

        String response = textBuilder.toString().trim();

        return response
                .replaceAll("(?i)\\bResponse Language:\\b", "\nResponse Language:\n")
                .replaceAll("(?i)\\bQuestion:\\b", "\nQuestion:\n");
    }
}
