package com.san.mpos.mgr.tb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.san.mpos.common.dao.MposBaseDao;
import com.san.mpos.mgr.tb.dao.MgrTbDao;
import com.san.mpos.mgr.tb.dto.MgrTbDto;

@Service
@Transactional
public class MgrTbDaoImpl extends MposBaseDao implements MgrTbDao {

	@Override
	@Transactional(readOnly = true)
	public List<MgrTbDto> getTable(Map paramMap) {
		MgrTbDao mgrTbDao = sqlSession.getMapper(MgrTbDao.class);
		return mgrTbDao.getTable(paramMap); 
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delTable(Map paramMap){	
		MgrTbDao mgrTbDao = sqlSession.getMapper(MgrTbDao.class);
		mgrTbDao.delTable(paramMap); 
	}
	
	@Override
	@Transactional(readOnly = false)
	public void insertTableList(List<MgrTbDto> list) {
		MgrTbDao mgrTbDao = sqlSession.getMapper(MgrTbDao.class);
		for(int i = 0 ; i < list.size() ; i++){
			mgrTbDao.insertTable(list.get(i));
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public void insertTable(MgrTbDto dto) {
//		MgrTbDao mgrTbDao = sqlSession.getMapper(MgrTbDao.class);
//		mgrTbDao.insertTable(dto);
	}
	
	
}
