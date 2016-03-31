/**
 * 
 */
package com.guru.order.data.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.guru.order.data.UserDao;
import com.guru.order.dto.UserDTO;

/**
 * @author RPILLARISETT
 * 
 */
@Component
public class UserDaoImpl extends BaseDao implements UserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.guru.order.data.UserDao#getUsers()
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UserDTO> getUsers() {
		String query = "select id, first_name, email, mobile from candidates";
		try {
			return (List<UserDTO>) getJdbcTemplate().query(query,
				new BeanPropertyRowMapper(UserDTO.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void addUser(UserDTO userDTO) throws Exception {
		String query = "insert into candidates (id, first_name, email, mobile) values (?,?,?,?)";
		getJdbcTemplate().update(
				query,
				new Object[] { userDTO.getId(), userDTO.getName(),
						userDTO.getEmail(), userDTO.getMobileNumber() });
	}

	@Override
	public void updateUser(UserDTO userDTO) throws Exception {
		String query = "update candidates set first_name=?, email=?, mobile=? where id=?";
		getJdbcTemplate().update(
				query,
				new Object[] { userDTO.getName(), userDTO.getEmail(),
						userDTO.getMobileNumber(), userDTO.getId() });
	}

	@Override
	public void deleteUser(int id) throws Exception {
		String query = "delete from candidates where id=?";
		getJdbcTemplate().update(query, new Object[] { id });
	}
}
