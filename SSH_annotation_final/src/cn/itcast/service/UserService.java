package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.User;

public interface UserService {

	void save(User user);

	List<User> findAll();

	User findById(int i);

}
