<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- 모바일시 확대 기능 제거 검토 필요
<c:if test="${loginSession.mode eq 'mobile'}">
	<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
</c:if>	
<c:if test="${loginSession.mode ne 'mobile'}">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</c:if>	
 --%>
<title><tiles:insertAttribute name="title" /></title>
<meta name="description" content="">
<meta name="author" content="">
<!-- 
<link href="${ctx }/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${ctx }/css/carousel.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctx }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx }/dwr/util.js"></script>
 -->
</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="container">
		<div id="body">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>