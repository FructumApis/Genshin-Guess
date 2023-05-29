package com.example.genshinguess.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author FructumApis
 */
@Data
@TableName("t_character")
@AllArgsConstructor
@NoArgsConstructor
public class Character {

	private int id;

	private String name;

	private int quality;

	private String weapon;

	private String vision;

	private String gender;

	private String region;

}
