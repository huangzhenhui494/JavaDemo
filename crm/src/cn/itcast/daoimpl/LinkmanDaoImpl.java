package cn.itcast.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.LinkmanDao;
import cn.itcast.domain.Linkman;

@Repository("linkmanDao")
public class LinkmanDaoImpl implements LinkmanDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(Linkman linkman) {
		hibernateTemplate.save(linkman);
	}

	@Override
	public List<Linkman> findAll(DetachedCriteria dc) {
		return (List<Linkman>) hibernateTemplate.findByCriteria(dc);
	}

	@Override
	public Linkman findLinkmanById(Long lkm_id) {
		return hibernateTemplate.get(Linkman.class, lkm_id);
	}

	@Override
	public void update(Linkman linkman) {
		hibernateTemplate.update(linkman);
	}

	@Override
	public void delete(Linkman linkman) {
		hibernateTemplate.delete(linkman);
	}

}
