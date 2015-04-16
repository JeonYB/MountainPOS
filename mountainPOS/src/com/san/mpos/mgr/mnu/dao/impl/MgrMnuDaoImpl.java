package com.san.mpos.mgr.mnu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.san.mpos.common.dao.MposBaseDao;
import com.san.mpos.mgr.mnu.dao.MgrMnuDao;
import com.san.mpos.mgr.mnu.dto.MgrMnuCateDto;
import com.san.mpos.mgr.mnu.dto.MgrMnuDto;
import com.san.mpos.mgr.tb.dao.MgrTbDao;
import com.san.mpos.mgr.tb.dto.MgrTbDto;

@Service
@Transactional
public class MgrMnuDaoImpl extends MposBaseDao implements MgrMnuDao {

	@Override
	@Transactional(readOnly = true)
	public List<MgrMnuCateDto> getMenuCateList(Map paramMap) {
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		return mgrMnuDao.getMenuCateList(paramMap); 
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delMnuCate(Map paramMap) {
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		mgrMnuDao.delMnuCate(paramMap);
	}
	
	@Override
	@Transactional(readOnly = false)
	public String getMnuSeq(){
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		return mgrMnuDao.getMnuSeq(); 
	}
	@Override
	@Transactional(readOnly = false)
	public void insertMnuCateList(List<MgrMnuCateDto> list) {
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		for(int i = 0 ; i < list.size() ; i++){
			mgrMnuDao.insertMnuCate(list.get(i));
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public void insertMnuCate(MgrMnuCateDto dto) {
//		MgrTbDao mgrTbDao = sqlSession.getMapper(MgrTbDao.class);
//		mgrTbDao.insertTable(dto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HashMap<String,Object>> getMenuList(Map paramMap){
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		return mgrMnuDao.getMenuList(paramMap); 
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<HashMap<String,Object>> getMnuCateList(Map paramMap){
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		return mgrMnuDao.getMnuCateList(paramMap); 
	}

	@Override
	@Transactional(readOnly = false)
	public void insertMnu(Map paramMap){
		MgrMnuDao mgrMnuDao = sqlSession.getMapper(MgrMnuDao.class);
		mgrMnuDao.insertMnu(paramMap); 
	}
}
