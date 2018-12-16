<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
	<LINK href="${pageContext.request.contextPath}/css/Style.css" type="text/css" rel="stylesheet">
	<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
	<!-- 日期插件，使用jquery -->
	<script type="text/javascript" src="jquery/jquery-1.4.2.js"></script>
	<link rel="stylesheet" href="jquery/jquery.datepick.css" type="text/css">
	<script type="text/javascript" src="jquery/jquery.datepick.js"></script>
	<script type="text/javascript" src="jquery/jquery.datepick-zh-CN.js"></script>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
			$('#birthday').datepick({dateFormat: 'yy-mm-dd'}); 
		});
</script>
<body>
	<input type="text" id="birthday"/>
</body>
</html>