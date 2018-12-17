package cn.itcast.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.domain.Linkman;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkmanService;

@Controller("linkmanAction")
@ParentPackage("struts-default")
@Namespace("/")
@Scope("prototype")
public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LinkmanService linkmanService;

	//以下代码是客户功能实现代码
	List<Customer> customerList;
	public List<Customer> getCustomerList() {
		return customerList;
	}
	
	/**
	 * 获取保存页面的所属客户列表,跳转到add.jsp
	 * @return
	 */
	@Action(value="linkman_addUI",results= {@Result(name="addUI",location="/jsp/linkman/add.jsp")})
	public String addUI() {
		/*
		 * 步骤:
		 *  1 获取客户名称
		 *  2 将客户名称集合放入值栈中
		 *  3 客户名称显示到添加联系人的客户列表中(add.jsp)
		 */
		customerList = customerService.findAll();
		return "addUI";
	}
	
	@Action(value="linkman_save")
	public String add() {
		linkmanService.save(linkman);
		return null;
	}
	
	
	
	
	
	// 封装模型驱动
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		return linkman;
	}

	
}