package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Linkman;

public interface LinkmanService {

	void save(Linkman linkman);

	List<Linkman> findAll(DetachedCriteria dc);

	Linkman findLinkmanById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

}
