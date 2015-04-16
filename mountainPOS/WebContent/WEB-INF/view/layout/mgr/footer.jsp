<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>


<div id="mposKeyPad" title="M-POS Key Pad" ng-controller="mposKeyPadCtrl">
	<div class="row">
		<span class="span1" ng-repeat="r in rowN track by $index">
			<button class="btn btn-info" ng-click="mposKeyPut(r)" style="width:40px;">{{r}}</button>&nbsp;
		</span>
	</div>
	<div class="row">
		&nbsp;
	</div>
	<div class="row">
		<span class="span1" ng-repeat="r in row1 track by $index">
			<button class="btn btn-info" ng-click="mposKeyPut(r)" style="width:40px;">{{r}}</button>&nbsp;
		</span>
	</div>
	<div class="row">
		&nbsp;
	</div>
	<div class="row">
		<span class="span1" ng-repeat="r in row2 track by $index">
			<button class="btn btn-info" ng-click="mposKeyPut(r)" style="width:40px;">{{r}}</button>&nbsp;
		</span>
	</div>
	<div class="row">
		&nbsp;
	</div>
	<div class="row">
		<span class="span1" ng-repeat="r in row3 track by $index">
			<button class="btn btn-info" ng-click="mposKeyPut(r)" style="width:40px;">{{r}}</button>&nbsp;
		</span>
		<button class="btn btn-info" ng-click="mposKeyPut('#shift#')" style="width:70px;">shift</button>&nbsp;
	</div>
</div>


<div class="col-lg-12 well text-center">
	<p>COPYRIGHT&copy; 2015 JYB COMMUNICATION</p>
	<div>
		<a href="javascript:showKeypad();">
			open
		</a>
	</div>
</div>

<script type="text/javascript">
var $input;
(function(){
	$( "#mposKeyPad" ).dialog({ autoOpen: false, width:600 });
	
	app.directive('mposKey',function(){
		return {
			restrict:'A',
			//priority:1,
			//require : 'ngModel',
			link : function(scope, element, attrs, ctrl){
				element.bind('focus',function(){
					$input ={s:scope,e:element,a:attrs};
				});
			}
		};
	});
	app.controller('mposKeyPadCtrl',['$scope',function($scope){
		$scope.rowN=["1","2","3","4","5","6","7","8","9","0"];
		$scope.row1a = ["Q","W","E","R","T","Y","U","I","O","P"];
		$scope.row2a = ["A","S","D","F","G","H","J","K","L",";"];
		$scope.row3a = ["Z","X","C","V","B","N","M",",",".","/"];
		$scope.row1 = ["q","w","e","r","t","y","u","i","o","p"];
		$scope.row2 = ["a","s","d","f","g","h","j","k","l",":"];
		$scope.row3 = ["z","x","c","v","b","n","m","<",">","?"];
		$scope.mposKeyPut = function (key){
			if(key=="#shift#"){
				var temp = $scope.row1;
				$scope.row1 = $scope.row1a;
				$scope.row1a = temp;
				temp = $scope.row2;
				$scope.row2 = $scope.row2a;
				$scope.row2a = temp;
				temp = $scope.row3;
				$scope.row3 = $scope.row3a;
				$scope.row3a = temp;
				return;
			}
			if($input && !$($input.e).hasClass('ng-hide')){
				eval("$input.s."+($input.a.ngModel)+"+='"+key+"'");
				$input.s.$digest();
			}
				
		};
	}]);
})();

function mposKeyPut(key){
	if($input && !$($input.e).hasClass('ng-hide')){
		eval("$input.s."+($input.a.ngModel)+"+='"+key+"'");
		$input.s.$digest();
	}
		
};
function showKeypad(){
	$( "#mposKeyPad" ).dialog( "open" );
}
</script>
