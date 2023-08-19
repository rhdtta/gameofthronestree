package com.got.demo.service;

import com.got.demo.domain.CharacterEntity;
import com.got.demo.domain.CharacterEntityDTO;
import com.got.demo.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void saveAllCharacters(List<CharacterEntity> characterEntities) {
        characterRepository.saveAll(characterEntities);
    }

    public List<CharacterEntityDTO> getAllCharactersByHouse(String houseName) {
        List<CharacterEntity> allCharacters = characterRepository.findAll();
        List<CharacterEntity> selectedHouseName = new ArrayList<>();
        for(CharacterEntity character: allCharacters) {
            if(character.getHouseName()!=null && character.getHouseName().equals(houseName)) {
                selectedHouseName.add(character);
            }
        }

        Map<Long, List<Long>> parentChildrenMap = new HashMap<>();
        parentChildrenMap = parentMapping(selectedHouseName);

        List<CharacterEntity> nullParents = getNullParents(selectedHouseName);
        List<CharacterEntityDTO> tree = getTreeStructure(nullParents, parentChildrenMap);
        return tree;
    }

    private List<CharacterEntity> getNullParents(List<CharacterEntity> selectedHouseName) {
        List<CharacterEntity> nullParents = new ArrayList<>();
        for(CharacterEntity SHN: selectedHouseName) {
            if(SHN.getParents().isEmpty()) {
                nullParents.add(SHN);
            }
        }

        return nullParents;
    }

    private List<CharacterEntityDTO> getTreeStructure(List<CharacterEntity> nullParents, Map<Long, List<Long>> childrenMap) {
        List<CharacterEntityDTO> tree = new ArrayList<>();
        for(CharacterEntity NP: nullParents) {
            CharacterEntityDTO entityTree = constructTree(NP, childrenMap);
            tree.add((entityTree));
        }
        return tree;
    }

    private CharacterEntityDTO constructTree(CharacterEntity np, Map<Long, List<Long>> childrenMap) {
        CharacterEntityDTO newEntity = new CharacterEntityDTO(np);
        List<CharacterEntityDTO> childList = new ArrayList<>();
        if(childrenMap.get(np.getId()) != null) {
            for(Long id: childrenMap.get(np.getId())) {
                CharacterEntity child = getCharacterEntityByid(id);
                CharacterEntityDTO childDTO = constructTree(child, childrenMap);
                childList.add(childDTO);
            }
        }

        newEntity.setChildren(childList);
        return newEntity;
    }

    private CharacterEntity getCharacterEntityByid(Long id) {
        List<CharacterEntity> temp = characterRepository.findAll();
        CharacterEntity ans = new CharacterEntity();
        for(CharacterEntity CE: temp) {
            if(CE.getId() == id) ans = CE;
        }

        return ans;
    }

    private Map<Long, List<Long>> parentMapping(List<CharacterEntity> selectedHouseName) {
        Map<Long, List<Long>> parentChildrenMap = new HashMap<>();

        for(CharacterEntity CE: selectedHouseName) {
            List<String> parents = CE.getParents();
            for(String parent: parents) {
                for(CharacterEntity ce: selectedHouseName) {
                    if(ce.getCharacterName().equals(parent)) {
                        if(parentChildrenMap.containsKey(ce.getId())) {
                            parentChildrenMap.get(ce.getId()).add(CE.getId());
                        } else {
                            List<Long> temp= new ArrayList<>();
                            temp.add(CE.getId());
                            parentChildrenMap.put(ce.getId(), temp);
                        }
                    }
                }
            }
        }

        return parentChildrenMap;
    }

    public void deleteAllCharacters() {
        characterRepository.deleteAll();
    }


    public List<String> getHouseNames() {
        List<String> houses = new ArrayList<>();
        List<CharacterEntity> entities = characterRepository.findAll();
        for(CharacterEntity entity: entities) {
            if(entity.getHouseName()!=null && !houses.contains(entity.getHouseName())) {
                houses.add(entity.getHouseName());
            }
        }
        return houses;

    }
}
