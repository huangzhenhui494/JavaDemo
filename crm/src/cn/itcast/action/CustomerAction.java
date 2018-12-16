package cn.itcast.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.BaseDict;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;

@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	@Autowired
	private CustomerService customerService;
	
	// 封装页面数据
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	
	//	以下代码是客户功能实现代码
	//查询所有的级别数据
	private List<BaseDict> levelList;
	//查询的所有的来源数据
	private List<BaseDict> sourceList;
	//查询的所有的行业数据
	private List<BaseDict> industryList;
	//get方法,方便它直接拿着我们的数据放在值栈里
	public List<BaseDict> getLevelList() {
		return levelList;
	}
	public List<BaseDict> getSourceList() {
		return sourceList;
	}
	public List<BaseDict> getIndustryList() {
		return industryList;
	}
	
	//查询所有的客户信息
	private List<Customer> customerList;
	public List<Customer> getCustomerList() {
		return customerList;
	}
	/**
	 * 新增客户页面功能
	 * @return
	 */
	@Action(value="customer_addUI",results= {@Result(name="addUI",location="/jsp/customer/add.jsp")})
	public String addUI() {
		/*步骤分析:
		 * 	1 查询所有客户级别数据 006 返回list
		 * 	2 查询所有信息来源数据 002 返回list
		 *  3 查询所有所属行业数据 001 返回list
		 *  4 把查询的三个list放在值栈中 到add.jsp页面显示到下拉框中
		 */
		//(或者查询所有,然后到add.jsp中进行判断,option中的value尤其要注意加<s:property value=""/>)
		//	1 查询所有客户级别数据 006 返回list
		levelList = customerService.findLevel("006");
		//	2 查询所有信息来源数据 002 返回list
		sourceList = customerService.findSource("002");
		//	3 查询所有所属行业数据 001 返回list
		industryList = customerService.findIndustry("001");
		//	4 把查询的三个list放在值栈中 1成员属性(重点)  2 ValueStack的API(push)
		return "addUI";
	}
	
	/**
	 * 保存客户
	 * @return
	 */
	@Action(value="customer_save",results= {@Result(name="toAction",location="customer_list",type="redirectAction")})
	public String save() {
		/*
		 * 步骤:
		 *  1 将页面的数据封装给modelDriver 
		 *  2 将封装好的对象进行传递保存
		 *  3 保存完毕,需要执行查询操作 将最新数据查询出来到页面展示,重定向
		 * */
		customerService.save(customer);
		return "toAction";
	}
	
	/**
	 * 查询全部和筛选查询栏
	 * @return
	 */
	@Action(value="customer_list",results= {@Result(name="tolist",location="/jsp/customer/list.jsp")})
	public String list() {
		/*步骤:
		 * 上集:
		 *  1 将条件中的客户级别数据查询出来 
		 *  2 将条件中的客户来源数据查询出来
		 *  3 将条件中的客户行业数据查询出来
		 *  4 把查询的数据放在值栈中,带到list.jsp页面显示数据在条件里的下拉框中
		 * 
		 * 下集:
		 *   1 将客户数据从数据库中全部查询出来  list
		 *   2 将查询出来的list数据放在值栈中,带到list.jsp页面显示所有客户的数据
		 * */
		
		//1 查询所有客户级别数据 006  返回的是list
		levelList=customerService.findLevel("006");
		//2 查询所有信息来源数据 002  返回的是list
		sourceList=customerService.findSource("002");
		//3 查询所有所属行业数据 001 返回的是list
		industryList=customerService.findIndustry("001");
		
		// 客户表的全查
		customerList = customerService.findAll();
		return "tolist";
	}
	
	/**
	 * 条件查询
	 * @return
	 */
	@Action(value="customer_conditionFind",results={@Result(name="tolist",location="/jsp/customer/list.jsp")})
	public String conditionFind() {
		/*
		 * 步骤:
		 * 	1 将页面的数据封装给modeDriver的customer对象中
		 *  2 离线条件查询(抛弃了传统的方式 使用hibernate提供的离线对象来完成条件查询)
		 */
		
		//	获取离线对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class); // 默认语法,相当于from Customer
		//	做条件 where cust_name like?
		dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		//此时Dao中的语句也会变化,就是where. . .
		//	当没有请选择的时候,才添加条件
		if(customer.getCust_level().getDict_id() != -1) {
			dc.add(Restrictions.eq("cust_level.dict_id", customer.getCust_level().getDict_id()));
		}
		//	当没有请选择的时候,才添加条件
		if(customer.getCust_source().getDict_id() != -1) {
			dc.add(Restrictions.eq("cust_source.dict_id", customer.getCust_source().getDict_id()));
		}
		//	当没有请选择的时候,才添加条件
		if(customer.getCust_industry().getDict_id() != -1) {
			dc.add(Restrictions.eq("cust_industry.dict_id", customer.getCust_industry().getDict_id()));
		}
		
		//1 查询所有客户级别数据 006  返回的是list
		levelList=customerService.findLevel("006");
		//2 查询所有信息来源数据 002  返回的是list
		sourceList=customerService.findSource("002");
		//3 查询所有所属行业数据 001 返回的是list
		industryList=customerService.findIndustry("001");
		
		//	传递dc对象
		customerList = customerService.conditionFind(dc);
		return "tolist"; //	此时的list页面没有下拉框的内容,返回增加信息
	}
	

	
}
