package cn.itcast.serviceimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkmanDao;
import cn.itcast.domain.Linkman;
import cn.itcast.domain.PageBean;
import cn.itcast.service.LinkmanService;

@Service("linkmanService")
@Transactional
public class LinkmanServiceImpl implements LinkmanService {

	@Autowired
	private LinkmanDao linkmanDao;

	@Override
	public void save(Linkman linkman) {
		linkmanDao.save(linkman);
	}

	@Override
	public List<Linkman> findAll(DetachedCriteria dc) {
		return linkmanDao.findAll(dc);
	}

	@Override
	public Linkman findLinkmanById(Long lkm_id) {
		return linkmanDao.findLinkmanById(lkm_id);
	}

	@Override
	public void update(Linkman linkman) {
		linkmanDao.update(linkman);
	}

	@Override
	public void delete(Linkman linkman) {
		linkmanDao.delete(linkman);
	}

	@Override
	public PageBean<Linkman> findAll(DetachedCriteria dc, Integer pageNumber, Integer pageSize) {
		
		//	总条数	// dc:可能是全查,也可能带条件的
		int dataCount = linkmanDao.findCount(dc);
		// 	创建PageBean();
		PageBean<Linkman> pb = new PageBean<Linkman>();
		// set pageNumber和pageSize,获取pageIndex
		pb.setPageNumber(pageNumber);
		pb.setPageSize(pageSize);
		//	显示的数据
		List<Linkman> linkmanList = linkmanDao.findOnePage(dc,pb.getPageIndex(),pageSize);
		//	PageBean封装
		//  封装总条数和list集合
		pb.setDataCount(dataCount);
		pb.setLinkmanList(linkmanList);
		return pb;
	}

	
	
}
