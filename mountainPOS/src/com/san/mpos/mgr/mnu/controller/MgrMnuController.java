package com.san.mpos.mgr.mnu.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.san.mpos.common.config.MposConfigUtils;
import com.san.mpos.common.util.CommonUtil;
import com.san.mpos.common.util.MposJsonFactory;
import com.san.mpos.common.util.MposStringUtil;
import com.san.mpos.mgr.mnu.dao.MgrMnuDao;
import com.san.mpos.mgr.mnu.dto.MgrMnuCateDto;


@Controller
@RequestMapping(value = "/mgr/mnu/")
public class MgrMnuController {
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(MgrMnuController.class);
	
	@Autowired
	MgrMnuDao mgrMnuDao;
	
	public static String TEMP_POS_ID = "0000000001";
	
	
	@RequestMapping("menuCateMgr")
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView("/mgr/mnu/menuCateMgr");
		
		/**- TEST posId **/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("posId", TEMP_POS_ID);
		
		List<MgrMnuCateDto> result = mgrMnuDao.getMenuCateList(paramMap);
		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.info(mapper.writeValueAsString(result));
			mav.addObject("mnuCateGem",mapper.writeValueAsString(result));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping("insertMnuCate")
	public String insertMnuCate(HttpServletRequest request, HttpServletResponse res, @RequestBody String rBody){
		logger.info(rBody);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,Object>> jsTabList = new ArrayList<HashMap<String,Object>>(); 

		ArrayList<MgrMnuCateDto> insertList = new ArrayList<MgrMnuCateDto>();

		/**- TEST posId **/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("posId", TEMP_POS_ID);
		
		HashMap<String,String> prevCateMap = new HashMap<String, String>();
		
		try {
			jsTabList = mapper.readValue(rBody, new TypeReference<List<HashMap<String,Object>>>(){});
			
			//일단 막아놓고~
			mgrMnuDao.delMnuCate(paramMap);
			
			for(int i = 0 ; i < jsTabList.size() ; i++){
				MgrMnuCateDto dto = new MgrMnuCateDto(jsTabList.get(i));
				dto.setPosId(MposStringUtil.getString(paramMap, "posId"));
				if(dto.getCateNo().startsWith("X")){
					if(prevCateMap.get(dto.getPrevCateNo()) != null){
						dto.setPrevCateNo(prevCateMap.get(dto.getPrevCateNo()));
					}
					String newSeq = mgrMnuDao.getMnuSeq();
					prevCateMap.put(dto.getCateNo(), newSeq);
					dto.setCateNo(newSeq);
				}
				insertList.add(dto);
			}
			System.out.println(insertList);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(insertList.size() < 1){
			return MposJsonFactory.getInstance().getNG("저장될 데이터가 없습니다.").toJSONString();
		}else{
			
			mgrMnuDao.insertMnuCateList(insertList);
			
		}
		return MposJsonFactory.getInstance().getOK().toJSONString();
	}
	
	
	@RequestMapping("menuMgr")
	public ModelAndView menuMgr(){
		ModelAndView mav = new ModelAndView("/mgr/mnu/menuMgr");
		
		/**- TEST posId **/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("posId", TEMP_POS_ID);
		
		List<HashMap<String,Object>> result = mgrMnuDao.getMenuList(paramMap);
		ObjectMapper mapper = new ObjectMapper();
		try {
			logger.info(mapper.writeValueAsString(result));
			mav.addObject("menuList",mapper.writeValueAsString(result));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		mav.addObject("menuCateList",mgrMnuDao.getMnuCateList(paramMap));
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("insertMenu")
	public String insertMenu(@RequestBody String rBody){
		
		/**- TEST posId **/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
		String resultList="[]";
		try {
			paramMap = mapper.readValue(rBody, new TypeReference<HashMap<String,Object>>(){});
			paramMap.put("posId", TEMP_POS_ID);
			System.out.println(paramMap);
			mgrMnuDao.insertMnu(paramMap);
			System.out.println(paramMap.get("posId"));
			List<HashMap<String,Object>> result = mgrMnuDao.getMenuList(paramMap);
			resultList = mapper.writeValueAsString(result);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return MposJsonFactory.getInstance().getNG().toJSONString();
		}
		
		return MposJsonFactory.getInstance().getOK(resultList).toJSONString();
	}

	
	@RequestMapping("menuImgUpload")
	@ResponseBody
	public String menuImgUpload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
		ModelAndView mav = new ModelAndView("/mgr/mnu/menuMgr");
		String filePath=MposConfigUtils.getString("upload.main.path");
		logger.debug("### CpnUploadController - insert()");
		String resultMessage = "";

		response.setContentType("text/plain");
		if (!(request instanceof MultipartHttpServletRequest)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Expected multipart request");
			return null;
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		MultipartFile imgFile = multipartRequest.getFile("file-0"); 
		String fileName = imgFile.getOriginalFilename().trim();
		final String imgFileName = TEMP_POS_ID+ CommonUtil.getTime("yyyyMMddHHmmss")+ fileName.substring(fileName.lastIndexOf("."),fileName.length());

		// 용량 체크
		long fileSize = imgFile.getSize();
		if (fileSize > 20480000 || fileSize <= 0) {
			resultMessage = "20MB 이상의 파일은 업로드 할 수 없습니다.";
		}

		// 확장자 체크
		int pathPoint = imgFileName.lastIndexOf(".");
		String filePoint = imgFileName.substring(pathPoint + 1,
				imgFileName.length());
		String fileType = filePoint.toLowerCase();

		if (!fileType.equals("jpg") && !fileType.equals("bmp")
				&& !fileType.equals("gif")) {
			resultMessage = "이미지 파일만 업로드 가능합니다.";
		}

		// 파일을 지정한 위치에 upload
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs(); // 디렉토리 생성
		}

		String finalFnm = filePath + imgFileName;
		logger.debug("finalFnm = " + finalFnm);
		imgFile.transferTo(new File(finalFnm));
		resultMessage = "정상적으로 업로드 하였습니다.";
		resultMessage += "\n저장된 파일 => " + finalFnm;

		logger.debug("resultMessage = " + resultMessage);
		mav.addObject("resultMessage", resultMessage);

		return imgFileName;
	}
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("test")
	public ModelAndView test(){
		ModelAndView mav = new ModelAndView("/mgr/mnu/test");
		System.out.println(MposConfigUtils.getString("upload.main.path"));
		return mav;
	}
	
}

