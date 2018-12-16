package cn.itcast.web;

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

import cn.itcast.domain.User;
import cn.itcast.service.UserService;

@Controller("userAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace(value="/")
public class UserAction extends ActionSupport implements ModelDriven<User>{

	//封装页面的数据
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	@Autowired
	private UserService userService;
	
	/**
	 * 调用save方法
	 * @return
	 */
	@Action(value="user_save",results= {@Result(name="success",location="/jsp/success.jsp")})
	public String save() {
		// 调用service
		userService.save(user);
		return SUCCESS;
	}
	
	/**
	 * 查找所有
	 */
	@Action(value="user_findAll",results= {@Result(name="success",location="/jsp/success.jsp")})
	public String findAll() {
		List<User> list = userService.findAll();
		for (User user : list) {
			System.out.println(user);
		}
		return SUCCESS;
	}
	
	/**
	 * 查找单个
	 */
	@Action(value="user_findById",results= {@Result(name="success",location="/jsp/success.jsp")})
	public String findById() {
		User user = userService.findById(1);
		System.out.println(user);
		return SUCCESS;
	}
	
	
}
