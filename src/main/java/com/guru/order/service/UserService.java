package com.guru.order.service;

import java.util.List;

import com.guru.order.dto.UserDTO;

public interface UserService {

	List<UserDTO> getUsers();

	void addUser(UserDTO userDTO);

	void updateUser(UserDTO userDTO);

	void deleteUser(UserDTO userDTO);
}
