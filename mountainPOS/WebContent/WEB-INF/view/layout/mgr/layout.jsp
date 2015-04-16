<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="IE=Edge" />
	<meta name="description" content="Web POS System for SAN restaurant">
	<meta name="author" content="JeonYB">
	<title><tiles:insertAttribute name="title" /></title>
	<link href="${ctx}/res/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/res/css/jquery-ui.min.css" rel="stylesheet">
	<link href="${ctx}/res/css/jyb.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx }/res/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx }/res/js/angular.min.js"></script>
	<script type="text/javascript" src="${ctx }/res/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/res/js/jquery-ui.min.js"></script>
<script type="text/javascript">
	var app = angular.module('mposApp',[]);
</script>



<!-- 
   *@@                                  .@@*   @@@  *@@###@@@
   .@@    .@@@*.    .@@@@*    **..@@@.    @@* @@@   *@@   *@@
   .@@   @@   *@* .@@*  .@@*  @@@. .@@*    @@@@*    *@@@@@@*
   .@@  @@@@@@@@@ @@@    .@@  @@.   @@*     @@*     *@@   .@@.
.  @@@  .@@       .@@    @@*  @@*   @@*     @@*     *@@   .@@.
@@@@.    .*@@@@@    *@@@@*.   @@.   @@*     @@.     *@@@@@@*
                                                2015 . 04 . 17 
 -->

</head>
<body ng-app="mposApp">
	<nav class="navbar navber-default">
		<div class="container-fluid">
			<tiles:insertAttribute name="header" />
		</div>
	</nav>
	<div class="continer-fluid">
		<div class="row">
			<div class="col-lg-3 well">
				<tiles:insertAttribute name="left" />
			</div>
			<div id="body" class="col-lg-8 col-lg-offset-1 well">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<footer>
		<div class="continer-fluid">
			<div  id="footer" class="row">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</footer>
</body>
</html>
