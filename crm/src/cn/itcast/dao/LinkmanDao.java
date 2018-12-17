package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Linkman;

public interface LinkmanDao {

	void save(Linkman linkman);

	List<Linkman> findAll();

}
