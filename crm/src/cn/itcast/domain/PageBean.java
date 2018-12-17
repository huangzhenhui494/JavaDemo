package cn.itcast.domain;

import java.util.List;

public class PageBean<T> {

	// 	当前页:pageNumber
	private Integer pageNumber;
	//	每页显示条数:pageSize
	private Integer pageSize;
	//	总页数:pageTotal
	private Integer pageTotal;
	//	总条数:DataCount
	private Integer dataCount;
	//	每页显示的数据:linkmanList
	private List<T> linkmanList;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getLinkmanList() {
		return linkmanList;
	}
	public void setLinkmanList(List<T> linkmanList) {
		this.linkmanList = linkmanList;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		dataCount = dataCount;
	}
	
	
	//	索引
	public int getPageIndex() {
		return (pageNumber-1)*pageSize;
	}
	
	//	总页数
	public int getPageTotal() {
		return (int)Math.ceil(dataCount/pageSize*1.0);
	}

	
	
}
