package com.example.artdefinder.entity;

import com.example.artdefinder.constant.Language;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "art")
public class ArtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Language language;

    @Column(length = 500)
    private String question;
}
