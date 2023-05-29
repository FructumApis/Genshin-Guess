package com.example.genshinguess.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.genshinguess.bean.Character;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author FructumApis
 */
@Mapper
public interface CharacterMapper extends BaseMapper<Character> {

}
