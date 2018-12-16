package cn.itcast.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(User user) {
		hibernateTemplate.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>)hibernateTemplate.find("from User");
	}

	@Override
	public User findById(int i) {
		User user = hibernateTemplate.load(User.class, i);
		return user;
	}
	
	
	// 方式三  继承hibernateDaoSupport
	
	/*@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
		
	}
*/
	
	// 方式二
	/*private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void save(User user) {
		hibernateTemplate.save(user);
	}*/
	
	
	
	// 方案一
	/*private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void save(User user)
	{
		// 加载配置文件
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
		
		
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		//session.close();
	}
*/

}
