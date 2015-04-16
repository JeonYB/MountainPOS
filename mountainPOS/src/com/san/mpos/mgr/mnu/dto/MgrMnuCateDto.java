package com.san.mpos.mgr.mnu.dto;

import java.util.Map;

import com.san.mpos.common.util.MposStringUtil;


public class MgrMnuCateDto {
	private String posId;
	private String cateNo;
	private String prevCateNo;
	private String cateNm;
	private int level;
	
	public MgrMnuCateDto() {}
	public MgrMnuCateDto(Map<String,Object> cate) {
		setCateNo(MposStringUtil.getString(cate, "cateNo"));
		setCateNm(MposStringUtil.getString(cate, "cateNm"));
		setPrevCateNo(MposStringUtil.getString(cate, "prevCateNo"));
		setLevel(MposStringUtil.getInt(cate, "level"));
	}
	public String posId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getCateNo() {
		return cateNo;
	}
	public void setCateNo(String cateNo) {
		this.cateNo = cateNo;
	}
	public String getPrevCateNo() {
		return prevCateNo;
	}
	public void setPrevCateNo(String prevCateNo) {
		this.prevCateNo = prevCateNo;
	}
	public String getCateNm() {
		return cateNm;
	}
	public void setCateNm(String cateNm) {
		this.cateNm = cateNm;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "MgrMnuDto [posId=" + posId + ", cateNo=" + cateNo
				+ ", prevCateNo=" + prevCateNo + ", cateNm=" + cateNm
				+ ", level=" + level + "]";
	}
	
}
