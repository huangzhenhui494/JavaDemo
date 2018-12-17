package cn.itcast.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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

	@Override
	public int findCount(DetachedCriteria dc) {
		//	设置dc的语法   要改成语法select count(*)
		dc.setProjection(Projections.rowCount());
		//	查count返回的是Long
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dc);//默认语法:select * from 表  / select * from 表 where
		return list.get(0).intValue();
	}

	@Override
	public List<Linkman> findOnePage(DetachedCriteria dc, int pageIndex, Integer pageSize) {
		//  当前dc的语法:select count(*)  上面那个方法
		dc.setProjection(null);//	回归默认语法 select * from / select * from 表 where ... 
		return (List<Linkman>) hibernateTemplate.findByCriteria(dc, pageIndex, pageSize);
	}

}
