<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>
<style>
<!--
.menuCate{margin-top: 5px;}
.mnuCtrlGrp{ text-align: right; margin: 0px 10px 10px 0px;}
-->
</style>
<div ng-controller="mgrMnuCateCtrl" id="mgrMnuCateCtrl">
	<div class="mnuCtrlGrp">
		<button class="btn btn-success" ng-click="result()">저장</button>
		<button class="btn btn-info" ng-click="createCate()">카테고리만들기</button>
	</div>
	
	<div class="menuCate" ng-repeat="data in list track by $index">
		<span ng-repeat="d in getDepth(data.level) track by $index"> 
			<span>　　</span><span ng-if="$last"><i class="glyphicon glyphicon-chevron-right"></i></span>
		</span>
		<button class="btn btn-default" ng-class="(isNew(data)) ? 'btn-info' : 'btn-default'" ng-click="flag=!flag">
			{{data.cateNm}}
		</button>
		&nbsp;&nbsp;
		<input type="text" ng-model="data.cateNm" size="15" ng-show="flag&&modi_flag" mpos-key/>
		<div class="btn-group" ng-show="flag">
			<button class="btn btn-primary" ng-click="cateAdd(data);flag=!flag"><i class="glyphicon glyphicon-plus"></i></button>
			<button class="btn btn-danger" ng-click="cateRemove(data);flag=!flag"><i class="glyphicon glyphicon-remove"></i></button>
			<button class="btn btn-warning" ng-click="modi_flag=!modi_flag"><i class="glyphicon glyphicon-pencil"></i></button>
		</div>
	</div>
</div>

<script type="text/javascript">
(function(){
	var tmplData = {cateNo:'X0000000000',prevCateNo:'0',cateNm:'메뉴카테고리',level:0};
	//var cateList = [data];
	app.controller('mgrMnuCateCtrl',['$scope','$http','msgService',function($scope, $http,msgService){
		$scope.list = eval('${mnuCateGem}');
		$scope.result = function(){
			var reqData = JSON.stringify($scope.list);
			
			console.log(reqData);
			
			$http.post('./insertMnuCate.san',reqData).
			success(function(data, status, headers, config){
				if(data.resultCode=="200"){
					msgService.showMsg('A','알림','성공적으로 저장되었습니다.');
				}else{
					msgService.showMsg('E','알림',data.resultMsg);
				}
			}).
			error(function(data, status, headers, config){
				console.log(data);
				msgService.showMsg('E','알림','일시적인 오류입니다. 잠시 후에 다시 시도해주세요.');
			});
			
		};
		$scope.cateRemove = function(data){
			if(!confirm("하위 카테고리는 삭제됩니다."))return false;
			var arr=[];
			for(var i = 0 ; i < $scope.list.length ; i++){
				if($scope.list[i] !== data){
					arr.push($scope.list[i]);
				}
			}
			arr = prevCateRemove(arr,data.cateNo);
			$scope.list=arr;
		};
		$scope.cateAdd = function(data){
			var newData = JSON.parse(JSON.stringify(tmplData));
			var arr=[];
			for(var i = 0 ; i < $scope.list.length ; i++){
				arr.push($scope.list[i]);
				if($scope.list[i] === data){
					newData.cateNo = getCateSeq();
					newData.level = data.level+1;
					newData.prevCateNo = data.cateNo;
					arr.push(newData);
				}
			}
			$scope.list=arr;
		};
		$scope.getDepth = function(i){
			return new Array(i);
		};
		$scope.isNew = function(data){
			return data.cateNo.charAt(0)=='X';
		};
		$scope.createCate = function(){
			var newData = JSON.parse(JSON.stringify(tmplData));
			newData.cateNo = getCateSeq();
			newData.level = 0;
			newData.prevCateNo = '0';
			$scope.list.push(newData);
		};
	}]);
})();
	var seq =1;
	function prevCateRemove(cateList,prevCateNo){
		var arr=[];
		console.log(cateList);
		for(var i = 0 ; i < cateList.length ; i++){
			if(cateList[i].prevCateNo != prevCateNo){
				//지우지 않을 때
				arr.push(cateList[i]);
			}else{
				//지울때
				cateList = prevCateRemove(cateList, cateList[i].cateNo);
			}
		}
		return arr;
	};
	function getCateSeq(){
		var str = "" + (seq++);
		var pad = "X000000000";
		return pad.substring(0, pad.length - str.length) + str;
	}
</script>