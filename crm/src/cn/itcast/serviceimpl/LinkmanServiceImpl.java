package cn.itcast.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.LinkmanDao;
import cn.itcast.domain.Linkman;
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
	public List<Linkman> findAll() {
		return linkmanDao.findAll();
	}

	
	
}
