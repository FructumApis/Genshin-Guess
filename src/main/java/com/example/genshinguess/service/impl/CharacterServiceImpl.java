package com.example.genshinguess.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.genshinguess.bean.Character;
import com.example.genshinguess.mapper.CharacterMapper;
import com.example.genshinguess.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author FructumApis
 */
@Service
public class CharacterServiceImpl extends ServiceImpl<CharacterMapper, Character> implements CharacterService {

	@Autowired
	CharacterMapper characterMapper;

	public Character randomCharacter() {
		Long count = characterMapper.selectCount(null);
		Random random = new Random();
		int r = random.nextInt(Math.toIntExact(count)) + 1;
		return characterMapper.selectById(r);
	}

	public Character getByName(String name) {
		QueryWrapper<Character> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name", name);
		return characterMapper.selectOne(queryWrapper);
	}

	@Override
	public List<Boolean> compareCharacter(Character c1, Character c2) {
		List<Boolean> result = new ArrayList<>();
		result.add(c1.getQuality() == c2.getQuality());
		result.add(Objects.equals(c1.getWeapon(), c2.getWeapon()));
		result.add(Objects.equals(c1.getVision(), c2.getVision()));
		result.add(Objects.equals(c1.getGender(), c2.getGender()));
		result.add(Objects.equals(c1.getRegion(), c2.getRegion()));
		return result;
	}

	@Override
	public List<Character> getAll() {
		return characterMapper.selectList(null);
	}

}
