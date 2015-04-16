package com.san.mpos.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MposBaseDao {
	protected Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@Autowired
	protected SqlSession sqlSession;

	@Autowired
	protected SqlSession sqlSessionBatch;

}
