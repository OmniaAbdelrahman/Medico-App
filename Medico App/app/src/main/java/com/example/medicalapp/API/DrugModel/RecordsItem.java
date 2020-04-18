package com.example.medicalapp.API.DrugModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;
@Entity
public class RecordsItem{
	@PrimaryKey(autoGenerate = true)
	int iddrug;
	@ColumnInfo
	@SerializedName("immunotherapy")
	private boolean immunotherapy;
	@ColumnInfo
	@SerializedName("name")
	private String name;
	@ColumnInfo
	@SerializedName("anti_neoplastic")
	private boolean antiNeoplastic;
	@Ignore
	@SerializedName("alias")
	private List<String> alias;
	@ColumnInfo
	@SerializedName("chembl_id")
	private String chemblId;
	@ColumnInfo
	@SerializedName("fda_approved")
	private boolean fdaApproved;

	public void setImmunotherapy(boolean immunotherapy){
		this.immunotherapy = immunotherapy;
	}

	public boolean isImmunotherapy(){
		return immunotherapy;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAntiNeoplastic(boolean antiNeoplastic){
		this.antiNeoplastic = antiNeoplastic;
	}

	public boolean isAntiNeoplastic(){
		return antiNeoplastic;
	}

	public void setAlias(List<String> alias){
		this.alias = alias;
	}

	public List<String> getAlias(){
		return alias;
	}

	public void setChemblId(String chemblId){
		this.chemblId = chemblId;
	}

	public String getChemblId(){
		return chemblId;
	}

	public void setFdaApproved(boolean fdaApproved){
		this.fdaApproved = fdaApproved;
	}

	public boolean isFdaApproved(){
		return fdaApproved;
	}
	@Ignore
	@Override
	public String toString(){
		return
				"RecordsItem{" +
						"immunotherapy = '" + immunotherapy + '\'' +
						",name = '" + name + '\'' +
						",anti_neoplastic = '" + antiNeoplastic + '\'' +
						",alias = '" + alias + '\'' +
						",chembl_id = '" + chemblId + '\'' +
						",fda_approved = '" + fdaApproved + '\'' +
						"}";
	}

	public RecordsItem() {
	}
}