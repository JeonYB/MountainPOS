package com.san.mpos.mgr.tb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.san.mpos.common.util.MposJsonFactory;
import com.san.mpos.mgr.tb.dao.MgrTbDao;
import com.san.mpos.mgr.tb.dto.MgrTbDto;


@Controller
@RequestMapping(value = "/mgr/tb/")
public class MgrTbController {
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(MgrTbController.class);
	
	@Autowired
	MgrTbDao mgrTbDao;
	
	public static String TEMP_POS_ID = "0000000001";
	
	@RequestMapping("tableMgr")
	public ModelAndView page(){
		ModelAndView mav = new ModelAndView("/mgr/tb/tableMgr");
		
		/**- TEST posId **/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("posId", TEMP_POS_ID);
		
		List<MgrTbDto> list = mgrTbDao.getTable(paramMap);
		mav.addObject("gridX",4);
		mav.addObject("gridY",6);
		mav.addObject("tablesGem","[]");
		
		if(list.size() > 0){
			mav.addObject("gridX",list.get(0).getMaxCol());
			mav.addObject("gridY",list.get(0).getMaxRow());

			JSONArray root = new JSONArray();
			JSONObject row = new JSONObject();
			JSONArray cols = new JSONArray();
			int currRow = 0;
			for(int i = 0 ; i < list.size() ; i++){
				if(list.get(i).getGridRow() == currRow){
					JSONObject data = list.get(i).toJSON();
					cols.add(data);
				}else{
					row.put("cols", cols);
					root.add(row);
					row = new JSONObject();
					cols = new JSONArray();
					currRow++;
					JSONObject data = list.get(i).toJSON();
					cols.add(data);
				}
				if(i == list.size()-1){
					row.put("cols", cols);
					root.add(row);
				}
					
			}
			
			mav.addObject("tablesGem",root.toJSONString());
		}
		
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("insertTable")
	public String insertTable(HttpServletRequest request, HttpServletResponse res, @RequestBody String rBody){
		logger.info(rBody);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,Object>> jsTabList = new ArrayList<HashMap<String,Object>>(); 

		ArrayList<MgrTbDto> insertList = new ArrayList<MgrTbDto>();

		/**- TEST posId **/
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("posId", TEMP_POS_ID);

		try {
			jsTabList = mapper.readValue(rBody, new TypeReference<List<HashMap<String,Object>>>(){});
			
			mgrTbDao.delTable(paramMap);
			for(int i = 0 ; i < jsTabList.size() ; i++){
				HashMap<String,Object> rows = jsTabList.get(i);
				List<HashMap<String,Object>> cols = (List<HashMap<String, Object>>) rows.get("cols");
				for(int y = 0 ; y < cols.size() ; y++){
					MgrTbDto dto = new MgrTbDto(cols.get(y));
					dto.setPosId(TEMP_POS_ID);
					insertList.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(insertList.size() < 1){
			return MposJsonFactory.getInstance().getNG("저장될 데이터가 없습니다.").toJSONString();
		}else{
			mgrTbDao.insertTableList(insertList);
		}
		return MposJsonFactory.getInstance().getOK().toJSONString();
	}
	
}
