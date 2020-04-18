package com.example.medicalapp.API.DrugModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DrugResponse{

	@SerializedName("records")
	private List<RecordsItem> records;

	@SerializedName("_meta")
	private Meta meta;

	public void setRecords(List<RecordsItem> records){
		this.records = records;
	}

	public List<RecordsItem> getRecords(){
		return records;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	@Override
 	public String toString(){
		return 
			"DrugResponse{" + 
			"records = '" + records + '\'' + 
			",_meta = '" + meta + '\'' + 
			"}";
		}
}