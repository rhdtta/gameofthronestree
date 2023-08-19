package com.got.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterEntityDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("characterName")
    private String characterName;

    @JsonProperty("houseName")
    private String houseName;

    @JsonProperty("imageThumb")
    private String characterImageThumb;

    @JsonProperty("imageFull")
    private String characterImageFull;

    @JsonProperty("children")
    private List<CharacterEntityDTO> children;
    @JsonProperty("siblings")
    private List<String> siblings;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("marriedEngaged")
    private List<String> marriedEngaged;

    public CharacterEntityDTO(CharacterEntity E) {
        this.characterImageFull = E.getCharacterImageFull();
        this.characterName = E.getCharacterName();
        this.id = E.getId();
        this.houseName = E.getHouseName();
        this.marriedEngaged = E.getMarriedEngaged();
        this.siblings = E.getSiblings();
        this.nickname = E.getNickname();
        this.characterImageThumb = E.getCharacterImageThumb();
    }

    public void setChildren(List<CharacterEntityDTO> children) {
        this.children = children;
    }
}
