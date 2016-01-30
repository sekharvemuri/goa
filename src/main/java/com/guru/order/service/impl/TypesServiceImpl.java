package com.guru.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.data.TypesDao;
import com.guru.order.data.vo.TypeVO;
import com.guru.order.service.TypesService;

@Component
public class TypesServiceImpl implements TypesService {
	
	@Autowired
	private TypesDao typesDao;

	@Override
	public List<TypeVO> getTypes() {
		return this.typesDao.getTypes();
	}

	@Override
	public void saveType(TypeVO typeVO) throws Exception {
		this.typesDao.saveType(typeVO);
	}

}
