package com.san.mpos.mgr.mnu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.san.mpos.mgr.mnu.dto.MgrMnuCateDto;

public interface MgrMnuDao {
	public List<MgrMnuCateDto> getMenuCateList(Map paramMap);
	public void delMnuCate(Map paramMap);
	public String getMnuSeq();
	public void insertMnuCateList(List<MgrMnuCateDto> list);
	public void insertMnuCate(MgrMnuCateDto dto);
	public List<HashMap<String,Object>> getMenuList(Map paramMap);
	public List<HashMap<String,Object>> getMnuCateList(Map paramMap);
	public void insertMnu(Map paramMap);
}
