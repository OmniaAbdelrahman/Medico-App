package com.example.medicalapp.API.DiagnosesModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Issue{
	@PrimaryKey(autoGenerate = true)
	int issue_id;
	@ColumnInfo
	@SerializedName("Accuracy")
	private double accuracy;
	@ColumnInfo
	@SerializedName("Ranking")
	private int ranking;
	@ColumnInfo
	@SerializedName("ProfName")
	private String profName;
	@ColumnInfo
	@SerializedName("IcdName")
	private String icdName;
	@ColumnInfo
	@SerializedName("ID")
	private int iD;
	@ColumnInfo
	@SerializedName("Icd")
	private String icd;
	@ColumnInfo
	@SerializedName("Name")
	private String name;

	public Issue() {
	}

	public void setAccuracy(double accuracy){
		this.accuracy = accuracy;
	}

	public double getAccuracy(){
		return accuracy;
	}

	public void setRanking(int ranking){
		this.ranking = ranking;
	}

	public int getRanking(){
		return ranking;
	}

	public void setProfName(String profName){
		this.profName = profName;
	}

	public String getProfName(){
		return profName;
	}

	public void setIcdName(String icdName){
		this.icdName = icdName;
	}

	public String getIcdName(){
		return icdName;
	}

	public void setID(int iD){
		this.iD = iD;
	}

	public int getID(){
		return iD;
	}

	public void setIcd(String icd){
		this.icd = icd;
	}

	public String getIcd(){
		return icd;
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
				"Issue{" +
						"accuracy = '" + accuracy + '\'' +
						",ranking = '" + ranking + '\'' +
						",profName = '" + profName + '\'' +
						",icdName = '" + icdName + '\'' +
						",iD = '" + iD + '\'' +
						",icd = '" + icd + '\'' +
						",name = '" + name + '\'' +
						"}";
	}
}