package com.guru.order.data.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.guru.order.data.TypesDao;
import com.guru.order.data.vo.TypeVO;

@Component
public class TypesDaoImpl extends BaseDao implements TypesDao {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TypeVO getTypeByName(String name) {
		String query = "select id, name, description from types where name=?";
		return ((TypeVO) getJdbcTemplate().query(query, new Object[] {name}, new BeanPropertyRowMapper(TypeVO.class)));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TypeVO> getTypes() {
		String query = "select id, name, description from types";
		return ((List<TypeVO>) getJdbcTemplate().query(query, new BeanPropertyRowMapper(TypeVO.class)));
	}

	@Override
	public void saveType(TypeVO typeVO) throws Exception {
		TypeVO existingType = getTypeByName(typeVO.getName());
		if (existingType == null) {
			addType(typeVO);
		}
		else {
			//TODO: update
		}
	}
	
	public void addType(TypeVO typeVO) {
		String query = "insert into types (name, description) values (?, ?);";
		getJdbcTemplate().update(query, new Object[] {typeVO.getName(), typeVO.getDescription()});
	}

}
