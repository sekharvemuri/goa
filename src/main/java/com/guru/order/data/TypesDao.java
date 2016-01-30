package com.guru.order.data;

import java.util.List;

import com.guru.order.data.vo.TypeVO;

public interface TypesDao {

	List<TypeVO> getTypes();

	void saveType(TypeVO typeVO) throws Exception;

}
