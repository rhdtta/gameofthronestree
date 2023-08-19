package com.got.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Characters")
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "characterName")
    private String characterName;

    @Column(name = "houseName")
    private String houseName;

    @Column(name = "characterImageThumb")
    private String characterImageThumb;

    @Column(name = "characterImageFull")
    private String characterImageFull;

    @Column(name = "parents")
    @ElementCollection
    @CollectionTable(name = "parents", joinColumns = @JoinColumn(name = "character_id"))
    private List<String> parents;

    @Column(name = "siblings")
    @ElementCollection
    @CollectionTable(name = "siblings", joinColumns = @JoinColumn(name = "character_id"))
    private List<String> siblings;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "marriedEngaged")
    @ElementCollection
    @CollectionTable(name = "marriedEngaged", joinColumns = @JoinColumn(name = "character_id"))
    private List<String> marriedEngaged;

    public CharacterEntity() {
    }
}
