﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#level option[value='<s:property value="customerById.cust_level.dict_id"/>']").prop("selected",true);
		$("#source option[value='<s:property value="customerById.cust_source.dict_id"/>']").prop("selected",true);
		$("#industry option[value='<s:property value="customerById.cust_industry.dict_id"/>']").prop("selected",true);
	})
</script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<s:debug></s:debug>
<FORM id=form1 name=form1
		action="${pageContext.request.contextPath}/customer_update.action"
		
		method=post>
		<input type="hidden" name="cust_id" value="<s:property value="customerById.cust_id"/>">
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT value="<s:property value="customerById.cust_name"/>" class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_name">
								</td>
								<td>客户级别 ：</td>
								<td>
									<select id="level" name="cust_level.dict_id" style="WIDTH: 180px">
										<s:iterator value="levelList" var="baseDict"> 
											<!-- 每次的iterator都会把baseDict放在context中,所以要# -->
											<option value=<s:property value="#baseDict.dict_id"/>>
												<s:property value="#baseDict.dict_item_name"/>
											</option>
										</s:iterator>
									</select>
								</td>
							</TR>
							
							<TR>
								
								<td>信息来源 ：</td>
								<td>
									<select id="source" name="cust_source.dict_id" style="WIDTH: 180px">
										<s:iterator value="sourceList" var="baseDict"> 
											<option value=<s:property value="#baseDict.dict_id"/>>
												<s:property value="#baseDict.dict_item_name"/>
											</option>
										</s:iterator>
									</select>
								</td>
								<td>所属行业 ：</td>
								<td>
									<select id="industry" name="cust_industry.dict_id" style="WIDTH: 180px">
										<s:iterator value="industryList" var="baseDict"> 
											<option value=<s:property value="#baseDict.dict_id"/>>
												<s:property value="#baseDict.dict_item_name"/>
											</option>
										</s:iterator>
									</select>
								</td>
							</TR>
							
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
								<INPUT value="<s:property value="customerById.cust_phone"/>" class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone">
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT value="<s:property value="customerById.cust_mobile"/>" class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile">
								</td>
							</TR>
							
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
