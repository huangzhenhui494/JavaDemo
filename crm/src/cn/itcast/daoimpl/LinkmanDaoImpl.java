package cn.itcast.daoimpl;

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

}
