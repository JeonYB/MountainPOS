package com.san.mpos.mgr.tb.dto;

import java.util.Map;

import org.json.simple.JSONObject;

import com.san.mpos.common.util.MposStringUtil;

public class MgrTbDto {
	private int maxRow;
	private int maxCol;
	private String posId;
	private int gridRow;
	private int gridCol;
	private int tableId;
	private boolean useFlag;
	private String tableNm;
	private int maxSeat;
	
	
	
	public MgrTbDto() {}
	public MgrTbDto(Map<String,Object> cols) {
		setGridRow( MposStringUtil.getInt(cols,"row") );
		setGridCol( MposStringUtil.getInt(cols,"col") );
		setTableId( MposStringUtil.getInt(cols,"tableId") );
		setMaxSeat( MposStringUtil.getInt(cols,"maxSeat") );
		setUseFlag( MposStringUtil.getBoolean(cols,"useFlag") );
		setTableNm( MposStringUtil.getString(cols,"tableNm") );
	}
	
	
	public int getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public int getMaxCol() {
		return maxCol;
	}
	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public int getGridRow() {
		return gridRow;
	}
	public void setGridRow(int gridRow) {
		this.gridRow = gridRow;
	}
	public int getGridCol() {
		return gridCol;
	}
	public void setGridCol(int gridCol) {
		this.gridCol = gridCol;
	}
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public boolean isUseFlag() {
		return useFlag;
	}
	public void setUseFlag(boolean useFlag) {
		this.useFlag = useFlag;
	}
	public String getTableNm() {
		return tableNm;
	}
	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}
	public int getMaxSeat() {
		return maxSeat;
	}
	public void setMaxSeat(int maxSeat) {
		this.maxSeat = maxSeat;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON(){
		JSONObject jo = new JSONObject();
		jo.put("row", gridRow);
		jo.put("col", gridCol);
		jo.put("tableId", tableId);
		jo.put("maxSeat", maxSeat);
		jo.put("useFlag", useFlag);
		jo.put("tableNm", tableNm);
		return jo;
	}
	
	@Override
	public String toString() {
		return "MgrTbDto [maxRow=" + maxRow + ", maxCol=" + maxCol + ", posId="
				+ posId + ", gridRow=" + gridRow + ", gridCol=" + gridCol
				+ ", tableId=" + tableId + ", useFlag=" + useFlag
				+ ", tableNm=" + tableNm + ", maxSeat=" + maxSeat + "]";
	}
}
