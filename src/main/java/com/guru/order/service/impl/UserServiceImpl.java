/**
 * 
 */
package com.guru.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guru.order.data.UserDao;
import com.guru.order.dto.UserDTO;
import com.guru.order.service.UserService;

/**
 * @author RPILLARISETT
 * 
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<UserDTO> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public void addUser(UserDTO userDTO) {
		try {
			userDao.addUser(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		try {
			userDao.updateUser(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(UserDTO userDTO) {
		try {
			userDao.deleteUser(userDTO.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
