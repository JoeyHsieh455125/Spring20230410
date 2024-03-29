<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
	<meta charset="UTF-8">
	<title>Lotto</title>
</head>
<body style="padding: 15px">
	<!-- 電腦選號按鈕 -->
	<button type="button"
			onclick="window.location.href='${pageContext.request.contextPath}/mvc/lotto/add';"
			class="pure-button pure-button-primary">Lotto 539 電腦選號</button>
	<p>
	<!-- 電腦選號統計資料 -->
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>號碼</th>
				<c:forEach var="st" items="${ stat }">
					<th nowrap>${st.key }</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>次數</td>
				<c:forEach var="st" items="${ stat }">
					<td nowrap>${st.value }</td>
				</c:forEach>
			</tr>
			<tr>
				<td>%</td>
				<c:forEach var="st" items="${ stat }">
					<td nowrap>
						<fmt:formatNumber 
							type="number"
							value="${ (st.value / (fn:length(lottos)*5)) * 100 }"
							maxFractionDigits="2"/>
					</td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
	<p>
	<!-- 電腦選號歷史紀錄資料 -->
	歷史紀錄(${fn:length(lottos)}):
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>index</th>
				<th>號碼1</th>
				<th>號碼2</th>
				<th>號碼3</th>
				<th>號碼4</th>
				<th>號碼5</th>
				<th>更新</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="status" var="lotto" items="${ lottos }">
				<tr>
					<td>${status.index }</td>
					<!-- 分析 Set 集合 -->
					<c:forEach varStatus="num_status" var="num" items="${ lotto }">
						<td onclick="window.location.href='${pageContext.request.contextPath}/mvc/lotto/update/${status.index }/${num_status.index }';">
						${ num }</td>
					</c:forEach>
					<td>
						<button type="button"
								onclick="window.location.href='${pageContext.request.contextPath}/mvc/lotto/update/${status.index} ';"
								class="pure-button pure-button-primary">更新</button>
					</td>
					<td>
						<button type="button" 
								onclick="window.location.href='${pageContext.request.contextPath}/mvc/lotto/delete/${status.index}';"
								class="pure-button pure-button-primary">刪除</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>