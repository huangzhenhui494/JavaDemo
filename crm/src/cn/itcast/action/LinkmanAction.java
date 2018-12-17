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

import cn.itcast.domain.Customer;
import cn.itcast.domain.Linkman;
import cn.itcast.domain.PageBean;
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
	
	//	以下代码是客户功能实现代码
	List<Customer> customerList;
	//	联系人列表
	List<Linkman> linkmanList; 
	//	通过id查询联系人
	private Linkman linkmanById;
	//	分页数据
	private PageBean<Linkman> pb;
	public PageBean<Linkman> getPb() {
		return pb;
	}
	public Linkman getLinkmanById() {
		return linkmanById;
	}
	public List<Linkman> getLinkmanList() {
		return linkmanList;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	
	//	分页数据
	private Integer pageNumber;
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	private Integer pageSize = 3;
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
	
	/**
	 * 添加保存
	 * @return
	 */
	@Action(value="linkman_save",results={@Result(name="toAction",location="linkman_list",type="redirectAction")})
	public String add() {
		linkmanService.save(linkman);
		return "toAction";
	}
	
/*	*//**
	 * linkman列表和条件查询 ,不是分页
	 * @return
	 *//*
	@Action(value="linkman_list",results= {@Result(name="tolist",location="/jsp/linkman/list.jsp")})
	public String list() {
		
		 * 1 查询所有客户,将所属客户回显到条件查询窗口
		 * 2 查询所有联系人,将联系人列表显示在页面
		 
		customerList = customerService.findAll();
		//  针对条件要玩离线查询,默认全查linkmanList = linkmanService.findAll(dc);
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		//	如果是条件查询,添加条件
		//	通过名字查询
		if(linkman.getLkm_name() != null) {
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		//	通过所属客户查询
		//  注意,这边如果直接点击联系人列表,没有设置条件的话,会出现空指针异常Customer为空,不能get
		if(linkman.getCustomer() != null && linkman.getCustomer().getCust_id() != -1) {
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		//	若点击的是联系人,做默认的语法全查
		//	点击的是筛选做条件差
		linkmanList = linkmanService.findAll(dc);
		return "tolist";
	}*/
	
	/**
	 * 分页查询
	 * @return
	 */
	@Action(value="linkman_list",results= {@Result(name="tolist",location="/jsp/linkman/list.jsp")})
	public String list() {
		
		customerList = customerService.findAll();
		//  针对条件要玩离线查询,默认全查linkmanList = linkmanService.findAll(dc);
		DetachedCriteria dc = DetachedCriteria.forClass(Linkman.class);
		//	如果是条件查询,添加条件
		//	通过名字查询
		if(linkman.getLkm_name() != null) {
			dc.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		//	通过所属客户查询
		//  注意,这边如果直接点击联系人列表,没有设置条件的话,会出现空指针异常Customer为空,不能get
		if(linkman.getCustomer() != null && linkman.getCustomer().getCust_id() != -1) {
			dc.add(Restrictions.eq("customer.cust_id", linkman.getCustomer().getCust_id()));
		}
		
		//	若点击的是联系人,做默认的语法全查
		//	点击的是筛选做条件差
		//  添加,修改等页面没有pageNumber
		if(pageNumber == null) {
			pageNumber = 1;
		}
		
		pb = linkmanService.findAll(dc,pageNumber,pageSize);
		// dc:能拿到数据  pageNumber  pageSize
		return "tolist";
	}
	
	/**
	 * updateUI,获取数据到edit页面
	 * @return
	 */
	@Action(value="linkman_updateUI",results= {@Result(name="toedit",location="/jsp/linkman/edit.jsp")})
	public String updateUI() {
		/*
		 * 1 查customerList
		 * 2 根据页面传递的id 将点击的联系人数据给查出来,返回的对象
		 * 3 将对象数据放在值栈中  带到edit.jsp
		 * 
		 */
		customerList = customerService.findAll();
		linkmanById = linkmanService.findLinkmanById(linkman.getLkm_id());
		return "toedit";
	}
	
	/**
	 * 更新update
	 * @return
	 */
	@Action(value="linkman_update",results= {@Result(name="tolist",location="linkman_list",type="redirectAction")})
	public String update() {
		//获取编辑表单的数据到linkman,修改
		linkmanService.update(linkman);
		return "tolist";
	}
	
	/**
	 * 删除
	 * @return
	 */
	@Action(value="linkman_delete",results= {@Result(name="toAction",location="linkman_list",type="redirectAction")})
	public String delete() {
		linkmanService.delete(linkman);
		return "toAction";
	}

	
	// 封装模型驱动
	private Linkman linkman = new Linkman();
	public Linkman getLinkman() {
		return linkman;
	}
	@Override
	public Linkman getModel() {
		return linkman;
	}

	
}
