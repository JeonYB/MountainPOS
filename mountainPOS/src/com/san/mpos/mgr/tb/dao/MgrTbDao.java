package com.san.mpos.mgr.tb.dao;

import java.util.List;
import java.util.Map;

import com.san.mpos.mgr.tb.dto.MgrTbDto;

public interface MgrTbDao {
	public List<MgrTbDto> getTable(Map paramMap);
	public void delTable(Map paramMap);
	public void insertTableList(List<MgrTbDto> list);
	public void insertTable(MgrTbDto dto);
}
