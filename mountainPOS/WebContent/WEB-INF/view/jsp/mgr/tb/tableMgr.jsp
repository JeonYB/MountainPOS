<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>
<style>
<!--
#tableGrid tr td{height:100px;}
.unuseTable{width: 100%; height: 100%; display: block; background-color: #dddddd; padding: 2px 2px 2px 2px;}
.useTable{
	width: 100%; height: 100%; display: block; padding: 2px 2px 2px 2px; background-color: #ffffff; border: solid 2px #cccccc;
	text-align : right;
}
.useTable p{ text-align: center;}
.tableCtrlGrp{ text-align: right; margin: 0px 10px 10px 0px;}
-->
</style>
<div ng-controller="tbMgrCtrl" >
	<div class="tableCtrlGrp">
		GRID-X : 
		&nbsp;<a href="javascript:void(0)"><i class="glyphicon glyphicon-plus" ng-click="gridX = gridX+1" style="color:red;"></i></a>&nbsp;
		<b style="font-size:120%;">{{gridX}}</b>
		&nbsp;<a href="javascript:void(0)"><i class="glyphicon glyphicon-minus" ng-click="gridX = gridX-1" style="color:blue;"></i></a>&nbsp;
		&nbsp;&nbsp;
		GRID-Y : 
		&nbsp;<a href="javascript:void(0)"><i class="glyphicon glyphicon-plus" ng-click="gridY = gridY+1" style="color:red;"></i></a>&nbsp;
		<b style="font-size:120%;">{{gridY}}</b>
		&nbsp;<a href="javascript:void(0)"><i class="glyphicon glyphicon-minus" ng-click="gridY = gridY-1" style="color:blue;"></i></a>&nbsp;
		&nbsp;&nbsp;
		<button class="btn btn-warning" ng-click="init()">테이블 초기화</button>
		&nbsp;
		<button class="btn btn-success" ng-click="result()">저장</button>
	</div>
	<table class="table" id="tableGrid" width="100%" >
		<tr ng-repeat="y in tablesGem track by $index">
			<td id="" ng-repeat="x in y.cols track by $index">
				<a id="tableId_{{x.tableId}}" ng-class="(x.useFlag) ? 'useTable' : 'unuseTable'" href="javascript:void(0)" ng-click="!x.useFlag && selTable(x.tableId,x)" >
					<div ng-show="x.useFlag">
						<i class="glyphicon glyphicon-pencil" ng-click="isModiNm=!isModiNm"></i>
						<i class="glyphicon glyphicon-plus" ng-click="x.maxSeat=x.maxSeat+1"></i>
						<i class="glyphicon glyphicon-minus" ng-click="x.maxSeat=x.maxSeat-1"></i>
						<span class="badge" ng-bind="x.maxSeat"></span>
						<p ng-bind="x.tableNm" ng-hide="isModiNm"></p>
						<p><input type="text" ng-model="x.tableNm" ng-show="isModiNm" size="8" mpos-key/></p>
						<i class="glyphicon glyphicon-trash" ng-click="removeTable(x)" style="color:red;"></i>
					</div>
				</a>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
	var test;
(function(){
	var dataGem = {
            "row": 0,
            "col": 0,
            "tableId": 0,
            "maxSeat" : 4,
            "useFlag" : false,
            "tableNm": ""
        };
	var rowGem = {cols:[]};
	app.controller('tbMgrCtrl',['$scope','$http','$controller','msgService',function($scope, $http, $controller,msgService){
		$scope.tablesGem = eval('${tablesGem}');
		$scope.gridX = eval('${gridX}');
		$scope.gridY = eval('${gridY}');
		$scope.init = function(){
			if(!confirm("저장된 테이블이 초기화됩니다."))return false;
			var tableId=0;
			$scope.tablesGem = [];
			for(var i = 0 ; i < $scope.gridY ; i++){
				var row = JSON.parse(JSON.stringify(rowGem));
				for(var y = 0 ; y < $scope.gridX ; y++){
					var data = JSON.parse(JSON.stringify(dataGem));
					data.row = i;
					data.col = y;
					data.tableId = tableId++;
					row.cols.push(data);
				}
				$scope.tablesGem.push(row);
			}
		};
		$scope.selTable = function(tabId,data){
			if("##remove##" == data.tableNm){
				data.tableNm = " ";
				return false;
			}
			data.tableNm = $scope.getTableNm();
			data.useFlag=true;
		};
		$scope.result = function(){
			var reqData = JSON.stringify($scope.tablesGem);
			$http.post('./insertTable.san',reqData).
				success(function(data, status, headers, config){
					if(data.resultCode=="200")
						msgService.showMsg('A','알림','테이블 저장이 성공적으로 이루어졌습니다.');
					else
						msgService.showMsg('E','알림',data.resultMsg);
				}).
				error(function(data, status, headers, config){
					console.log(data);
					msgService.showMsg('E','알림','일시적인 오류입니다. 잠시 후에 다시 시도해주세요.');
				});
		};
		$scope.getTableNm = function(){
			return '테이블 '+($('.useTable').size()+1);
		};
		$scope.removeTable = function(data){
			if(!confirm(data.tableNm+'을 삭제합니다.'))return false;
			data.useFlag = false;
			data.maxSeat = 4;
			data.tableNm ="##remove##";
		};
	}]);
})();	
</script>