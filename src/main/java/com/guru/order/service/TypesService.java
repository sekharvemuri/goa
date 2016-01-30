package com.guru.order.service;

import java.util.List;

import com.guru.order.data.vo.TypeVO;

public interface TypesService {
	
	List<TypeVO> getTypes();
	
	void saveType(TypeVO typeVO) throws Exception;

}
