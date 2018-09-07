package com.thy.dao;

import java.util.List;

import com.thy.pojo.BaseDict;

public interface DictMapper {
	public List<BaseDict> findDictByCode(String code);
}
