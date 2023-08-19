package com.got.demo.controller;

import com.got.demo.domain.CharacterEntity;
import com.got.demo.domain.CharacterEntityDTO;
import com.got.demo.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

//    Returns the family tree
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{houseName}")
    public List<CharacterEntityDTO> getFamilyTreeByHouse(@PathVariable("houseName") String houseName) {
        return characterService.getAllCharactersByHouse(houseName);
    }

//    Returns the list of house names
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public List<String> getHouseNames() {
        return characterService.getHouseNames();
    }
}