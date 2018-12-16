package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.User;

public interface UserDao {

	void save(User user);

	List<User> findAll();

	User findById(int i);

}
