package cn.itcast.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public void save(Customer customer) {
		hibernateTemplate.save(customer);
	}


	@Override
	public List<BaseDict> findLevel(String code) {
		//hql语法 from 类名 where 字段名
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code=?", code);
	}


	@Override
	public List<BaseDict> findSource(String code) {
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code=?", code);
	}


	@Override
	public List<BaseDict> findIndustry(String code) {
		return (List<BaseDict>) hibernateTemplate.find("from BaseDict where dict_type_code=?", code);
	}


	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find("from Customer");
	}


	@Override
	public List<Customer> conditionFind(DetachedCriteria dc) {
		return (List<Customer>) hibernateTemplate.findByCriteria(dc);
	}

}
