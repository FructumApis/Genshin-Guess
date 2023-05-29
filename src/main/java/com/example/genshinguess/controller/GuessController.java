package com.example.genshinguess.controller;

import com.example.genshinguess.bean.Character;
import com.example.genshinguess.service.CharacterService;
import com.mysql.cj.xdevapi.JsonArray;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FructumApis
 */
@Controller
public class GuessController {

	@Autowired
	CharacterService characterService;

	/**
	 * 生成一个随机对象存入session并跳转至主页
	 * @param session
	 * @return
	 */
	@GetMapping("/")
	public String index(HttpSession session) {
		session.setAttribute("character", characterService.randomCharacter());
		return "index";
	}

	/**
	 * 将猜测的对象与答案进行比对并返回结果集与对象
	 * @param name 猜测对象名
	 * @param session session对象
	 * @return 结果集与猜测对象
	 */
	@ResponseBody
	@PostMapping("/character/guess")
	public Map<String, Object> guessCharacter(@RequestParam("name") String name, HttpSession session) {
		Character guessCharacter = characterService.getByName(name);
		HashMap<String, Object> map = new HashMap<>();
		if (guessCharacter != null) {
			Character character = (Character) session.getAttribute("character");
			List<Boolean> result = characterService.compareCharacter(character, guessCharacter);
			map.put("result", result);
			map.put("guessCharacter", guessCharacter);
			map.put("flag", "true");
			for (Boolean r : result) {
				if (!r) {
                    map.put("flag", "false");
                    break;
                }
			}
		} else {
			map.put("flag", "error");
			map.put("message", "未找到该角色");
		}
		return map;
	}

	/**
	 * 根据角色名返回角色信息
	 * @param name 角色名
	 * @return 角色对象
	 */
	@PostMapping("/character/getCharacter")
	@ResponseBody
	public Character getCharacter(@RequestParam("name") String name) {
		return characterService.getByName(name);
	}

	@PostMapping("/character/getAll")
	@ResponseBody
	public List<Character> getAll() {
		return characterService.getAll();
	}

	@PostMapping(value = "/character/test")
	@ResponseBody
	public String test(JsonArray array) {
		System.out.println(array);
		return "success";
	}
}
