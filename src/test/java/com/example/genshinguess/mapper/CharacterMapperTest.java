package com.example.genshinguess.mapper;

import com.example.genshinguess.bean.Character;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CharacterMapperTest {

	@Autowired
	CharacterMapper characterMapper;

	@Test
	public void mapperTest() {
		Character count = characterMapper.selectById(5);
		System.out.println(count);
	}

}