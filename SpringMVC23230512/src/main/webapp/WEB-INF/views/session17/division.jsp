<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
	<meta charset="UTF-8">
	<title>整數除法計算</title>
</head>
<body style="padding: 15px">
	<spform:form class="pure-form" 
				 method="post" 
				 modelAttribute="division" 
				 action="${ pageContext.request.contextPath }/mvc/session17/division/">
		<fieldset>
			<legend>整數除法計算</legend>
			分子(X)：<spform:input path="x" /><p />
			分母(Y)：<spform:input path="y" /><p />
			<button type="submit" class="pure-button pure-button-primary">計算</button>
		</fieldset>
	</spform:form>
	結果：${ division.result }
	<p />
			${ globalData.copyright } ${ globalData.address }
</body>
</html>