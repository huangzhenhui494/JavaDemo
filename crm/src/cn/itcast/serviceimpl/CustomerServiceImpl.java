package cn.itcast.serviceimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	public List<BaseDict> findLevel(String code) {
		return customerDao.findLevel(code);
	}

	@Override
	public List<BaseDict> findSource(String code) {
		return customerDao.findSource(code);
	}

	@Override
	public List<BaseDict> findIndustry(String code) {
		return customerDao.findIndustry(code);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public List<Customer> conditionFind(DetachedCriteria dc) {
		return customerDao.conditionFind(dc);
	}

	@Override
	public Customer findById(Integer cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}


}
