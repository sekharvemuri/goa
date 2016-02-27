package com.guru.order.data;

import java.util.List;

import com.guru.order.dto.UserDTO;

public interface UserDao {

	List<UserDTO> getUsers();

	void addUser(UserDTO userDTO) throws Exception;
	
	void updateUser(UserDTO userDTO) throws Exception;
	
	void deleteUser(int id) throws Exception;
}
