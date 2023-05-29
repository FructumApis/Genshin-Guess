package com.example.genshinguess.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.genshinguess.bean.Character;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author FructumApis
 */
public interface CharacterService extends IService<Character> {

	/**
	 * 抽取随机角色
	 * @return 角色对象
	 */
	public Character randomCharacter();

	/**
	 * 根据角色名查找
	 * @param name 角色名
	 * @return 角色对象
	 */
	public Character getByName(String name);

	/**
	 * 将两个角色对比并返回结果
	 * @param c1 角色一
	 * @param c2 角色二
	 * @return boolean链表
	 */
	public List<Boolean> compareCharacter(Character c1, Character c2);

	/**
	 * 得到所有角色
	 * @return 所有角色的数组
	 */
	List<Character> getAll();
}
