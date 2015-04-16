<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.oreilly.servlet.MultipartRequest, com.oreilly.servlet.multipart.DefaultFileRenamePolicy, com.san.mpos.common.config.MposConfigUtils, java.util.*, java.io.*"%>
<%@ include file="/WEB-INF/view/jsp/common/taglib.jsp"%>
<%
String getPathWebInf = MposConfigUtils.getString("upload.se2.path");
String webFolder="WebContent";String uploadPath = getPathWebInf.substring(getPathWebInf.indexOf(webFolder)+webFolder.length(),getPathWebInf.length());;
StringBuffer buffer = new StringBuffer();
String filename = "";

String objKey = "";
objKey = request.getParameter("objKey");

if("".equals(objKey)){
	objKey="CONTENTS";
}
if(request.getContentLength() > 10*1024*1024 ){
%>
	<script>alert("업로드 용량(총 10Mytes)을 초과하였습니다.");history.back();</script>
<%
	return;
} else {
	MultipartRequest multi=new MultipartRequest(request, getPathWebInf, 10*1024*1024, "UTF-8", new DefaultFileRenamePolicy());

	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("yyyy_MM_dd_HHmmss", java.util.Locale.KOREA);
	int cnt = 1;
	String upfile = multi.getFilesystemName("Filedata");
	if (!upfile.equals("")) {
		String dateString = formatter.format(new java.util.Date());
		String moveFileName = dateString + upfile.substring(upfile.lastIndexOf(".") );
		String fileExt = upfile.substring(upfile.lastIndexOf(".") + 1);
		File sourceFile = new File(getPathWebInf + File.separator + upfile);
		File targetFile = new File(getPathWebInf + File.separator + moveFileName);
		sourceFile.renameTo(targetFile);
		filename = moveFileName;
		%>
		<form id="fileform" name="fileform" method="post">
			<input type="hidden" name="filename" value="<%=filename%>">
			<input type="hidden" name="uploadPath" value="${ctx}<%=uploadPath%>">
		</form>
		<%
	}
}
%>

<script type="text/javascript">
	
	var objKey = "<%=objKey%>";
	
	function fileAttach(){ 
		f = document.fileform;
		fpath = f.uploadPath.value;
	    fname = f.filename.value; 
	    fcode = fpath + fname;
	    var sHTML = "<img src='"+ fpath+fname +"'/>";
	    try{	    	opener.parent.oEditors.getById["description"].exec("PASTE_HTML", [sHTML]);

	    }catch(e){ 
            alert(e); 
	    } 
	} 
	fileAttach();
	this.window.close();
</script>
