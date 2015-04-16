<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/res/se/js/HuskyEZCreator.js" charset="utf-8"></script>

<style>
<!--
.mgrMnuCtrlGrp{text-align: right; margin: 0 10px 20px 0;}
.mnuListTr>td{vertical-align: middle;!important}
-->
</style>
<div ng-controller="mgrMnuCtrlGrp" id="mgrMnuCtrlGrp">
	<div class="mgrMnuCtrlGrp row">
		<button class="btn btn-success" data-toggle="modal" data-target="#mgrMnuAddModal"><span class="glyphicon glyphicon-plus" area-hidden="true"></span>메뉴추가</button>
	</div>
	<div class="row">
		<table class="table table-hover">
			<colgroup>
				<col width="15%">
				<col width="40%">
				<col width="15%">
				<col width="10%">
				<col width="20%">
			</colgroup>
			<tr class="success">
				<th class="text-center">카테고리</th>
				<th class="text-center">메뉴명</th>
				<th class="text-center">가격</th>
				<th class="text-center">대표사진</th>
				<th class="text-center">수정</th>
			</tr>
			<tr class="mnuListTr" ng-repeat="data in list track by $index">
				<td>{{data.cate_nm}}</td>
				<td>{{data.menu_nm}}</td>
				<td>{{data.price | currency:"￦":0}}</td>
				<td>
				   	<img class="img-responsive img-circle" ng-src="{{data.img_src}}" alt="{{data.menu_nm}}" width="50"/>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	

	<div class="modal fade" id="mgrMnuAddModal" tabindex="-1" role="dialog" aria-labelledby="menuAddForm" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
				</div>
				<div class="modal-body">
					
						 <div class="form-group">
						    <label for="menuCate">카테고리</label>
							<select id="menuCate" class="form-control">
								<c:forEach items="${menuCateList}" var="cate">
									<option value="${cate.cate_no}"><c:forEach var="n" begin="1" end="${cate.level }">　<c:if test="${n eq cate.level }">└▶</c:if></c:forEach>  ${cate.cate_nm}</option>
								</c:forEach>
							</select>
						  </div>
						 <div class="form-group">
						    <div class="col-xs-8">
							    <label for="menuNm">메뉴명</label>
							    <input type="text" class="form-control" id="menuNm" placeholder="메뉴명을 입력해주세요." maxlength="30">
						    </div>
						    <div class="col-xs-4">
							    <label for="menuPrice">메뉴가격</label>
							    <input type="number" class="form-control" id="menuPrice" placeholder="가격" maxlength="10">
						    </div>
						  </div>
						 <div class="form-group">
						    <div class="col-xs-4">
							    <label for="menuImg">대표이미지</label>
							    <form id="menuImgForm" name="menuImgForm" method="post" action="./menuImgUpload.san" enctype="multipart/form-data">
							    	<input type="file" class="form-control" id="menuImg" name="menuImg" placeholder="사진첨부">
							    </form>
						    </div>
						    <div class="col-xs-8">
							  	<div id="mnuImgResult">
							  	</div>
						    </div>
						  </div>
						  <div class="form-group">
						  	<textarea id="description" name="description"></textarea>
						  </div>
<!-- 						메뉴명 -->
<!-- 						가격 -->
<!-- 						대표이미지 -->
<!-- 						설명 -->
						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" ng-click="submit()">Save changes</button>
				</div>
			</div>
		</div>
	</div>
	
	
</div>
<script type="text/javascript">
//Smart Editor
var oEditors = [];

// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "description",
	sSkinURI: "${ctx}/res/se/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});
</script>


<script type="text/javascript">
var dataGem = {menuCate:"",menuNm:"",menuPrice:"",menuImg:"",description:""};
(function(){
	$('#mgrMnuAddModal').modal({ show : true, keyboard:true});
	app.controller('mgrMnuCtrlGrp',['$scope','$http','msgService',function($scope, $http,msgService){
		
		$scope.list = eval('${menuList}');
		
		$scope.submit = function(){
			dataGem.menuCate = $('#menuCate').val();
			dataGem.menuNm = $('#menuNm').val();
			dataGem.menuPrice = $('#menuPrice').val();
			dataGem.description = oEditors.getById["description"].getIR();
			console.log(dataGem);
			
			$http.post('./insertMenu.san',dataGem).
			success(function(data, status, headers, config){
				if(data.resultCode=="200"){
					msgService.showMsg('A','알림','성공적으로 저장되었습니다.');
					$scope.list= eval(data.resultMsg);
				}else{
					msgService.showMsg('E','알림',data.resultMsg);
				}
			}).
			error(function(data, status, headers, config){
				console.log(data);
				msgService.showMsg('E','알림','일시적인 오류입니다. 잠시 후에 다시 시도해주세요.');
			});
			
		};
	}]);
	$('#menuImg').on('change',function(){
		var formData = new FormData();
		
		$.each($('#menuImg')[0].files, function(i, file) {          
			formData.append('file-' + i, file);
        });
		
		$.ajax({
            type:'POST',
            url: $('#menuImgForm').attr('action'),
            dataType:"text",
            data:formData,
            //cache:false,
            contentType: false,
            processData: false,
            success:function(data){
                console.log("success");
                console.log(data);
                //class="img-responsive img-circle"
                $('#mnuImgResult').html($('<img />').attr('src','${ctx}/upload/mnu/'+data)).addClass('img-responsive img-circle');
                dataGem.menuImg='${ctx}/upload/mnu/'+data;
            },
            error: function(data){
                console.log("error");
                console.log(data);
            }
        });
	});
})();
</script>
