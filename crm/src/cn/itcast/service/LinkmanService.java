package cn.itcast.service;

import java.util.List;

import cn.itcast.domain.Linkman;

public interface LinkmanService {

	void save(Linkman linkman);

	List<Linkman> findAll();

}
