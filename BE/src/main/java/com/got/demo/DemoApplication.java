package com.got.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.got.demo.domain.CharacterEntity;
import com.got.demo.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CharacterService characterService){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<CharacterEntity>> typeReference = new TypeReference<List<CharacterEntity>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/characters.json");
			try {
				List<CharacterEntity> characterEntities = mapper.readValue(inputStream,typeReference);

//				for (int i = 0; i < characterEntities.size(); i++) {
//					CharacterEntity entity = characterEntities.get(i);
//					System.out.println(entity.printALl());
//				}

//				characterService.dropTable();
				characterService.deleteAllCharacters();
				characterService.saveAllCharacters(characterEntities);
//				characterService.parentTableAttributeGeneration();
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}

}
