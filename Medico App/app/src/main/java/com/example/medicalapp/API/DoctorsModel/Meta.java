package com.example.medicalapp.API.DoctorsModel;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("total")
	private int total;

	@SerializedName("item_type")
	private String itemType;

	@SerializedName("data_type")
	private String dataType;

	@SerializedName("count")
	private int count;

	@SerializedName("limit")
	private int limit;

	@SerializedName("skip")
	private int skip;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setItemType(String itemType){
		this.itemType = itemType;
	}

	public String getItemType(){
		return itemType;
	}

	public void setDataType(String dataType){
		this.dataType = dataType;
	}

	public String getDataType(){
		return dataType;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setSkip(int skip){
		this.skip = skip;
	}

	public int getSkip(){
		return skip;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"total = '" + total + '\'' + 
			",item_type = '" + itemType + '\'' + 
			",data_type = '" + dataType + '\'' + 
			",count = '" + count + '\'' + 
			",limit = '" + limit + '\'' + 
			",skip = '" + skip + '\'' + 
			"}";
		}
}