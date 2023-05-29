package com.example.genshinguess.service;

import com.example.genshinguess.bean.Character;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CharacterServiceTest {

	@Autowired
	CharacterService characterService;

	@Test
	public void randomCharacterTest() {
		for (int i = 0; i < 5; i++) {
			Character character = characterService.randomCharacter();
			System.out.println(character);
		}
	}

	@Test
	public void getByNameTest() {
		Character name = characterService.getByName("行秋");
		System.out.println(name);
	}

}