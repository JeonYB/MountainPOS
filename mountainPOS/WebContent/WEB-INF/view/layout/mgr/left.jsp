<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<div ng-controller="msgCtrl" >
	
	
	<!-- 테스트 삭제 -->
	<button class="btn btn-warning" ng-click="test2('A','sdf','asf')">test!</button>
	
	
	<div ng-repeat="msg in msgList track by $index">
		<div class="alert" ng-class="(msg.msgClass=='A') ? 'alert-info' : 'alert-danger' " role="alert" style="margin-left: 5px;">
			<div>
				<a href="javascript:void(0)"><i class="glyphicon glyphicon-trash pull-right" ng-click="removeMsg($index)" style="color:red;"></i></a>
				<span class="pull-right"><small>({{msg.time | date : "HH:mm:ss"}})</small>&nbsp;</span>
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				<span><b>{{msg.msgHead}}</b></span>
				<p>{{msg.msgBody}}</p>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
(function(){
	app.service('msgService',function(){
		this.msgList = [];
		this.showMsg = function(cls ,head, body){
			var data = {msgClass : cls, msgHead : head , msgBody : body , time: new Date()};
			this.msgList.push(data);
		};
		this.getList = function(){return this.msgList;};
	});
	
	app.controller('msgCtrl',['$scope','msgService',function($scope,msgService){
		$scope.msgList = msgService.getList();
		$scope.removeMsg = function(i){
			$scope.msgList.splice(i,1);
		};
		
		
		
		
		$scope.test2 = function(){
			msgService.showMsg('A','TEST','OK!!');
		};
		
		
		
	}]);
})();
</script>
