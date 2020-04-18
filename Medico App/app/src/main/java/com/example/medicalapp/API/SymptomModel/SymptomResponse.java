package com.example.medicalapp.API.SymptomModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class SymptomResponse{
	@PrimaryKey(autoGenerate = true)
	int symp_id;
	@ColumnInfo
	@SerializedName("ID")
	private int iD;

	public SymptomResponse() {
	}

	@ColumnInfo
	@SerializedName("Name")
	private String name;

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	@Ignore
	@Override
	public String toString(){
		return
				"SymptomResponse{" +
						"iD = '" + iD + '\'' +
						",name = '" + name + '\'' +
						"}";
	}
}