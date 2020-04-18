package com.example.medicalapp.API.DrugModel.DrugInfoModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DrugInfoResponse{

	@SerializedName("pmids")
	private List<Object> pmids;

	@SerializedName("immunotherapy")
	private boolean immunotherapy;

	@SerializedName("name")
	private String name;

	@SerializedName("anti_neoplastic")
	private boolean antiNeoplastic;

	@SerializedName("alias")
	private List<String> alias;

	@SerializedName("attributes")
	private List<AttributesItem> attributes;

	@SerializedName("chembl_id")
	private String chemblId;

	@SerializedName("fda_approved")
	private boolean fdaApproved;

	@SerializedName("drug_claims")
	private List<DrugClaimsItem> drugClaims;

	public void setPmids(List<Object> pmids){
		this.pmids = pmids;
	}

	public List<Object> getPmids(){
		return pmids;
	}

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

	public void setAttributes(List<AttributesItem> attributes){
		this.attributes = attributes;
	}

	public List<AttributesItem> getAttributes(){
		return attributes;
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

	public void setDrugClaims(List<DrugClaimsItem> drugClaims){
		this.drugClaims = drugClaims;
	}

	public List<DrugClaimsItem> getDrugClaims(){
		return drugClaims;
	}

	@Override
 	public String toString(){
		return 
			"DrugInfoResponse{" + 
			"pmids = '" + pmids + '\'' + 
			",immunotherapy = '" + immunotherapy + '\'' + 
			",name = '" + name + '\'' + 
			",anti_neoplastic = '" + antiNeoplastic + '\'' + 
			",alias = '" + alias + '\'' + 
			",attributes = '" + attributes + '\'' + 
			",chembl_id = '" + chemblId + '\'' + 
			",fda_approved = '" + fdaApproved + '\'' + 
			",drug_claims = '" + drugClaims + '\'' + 
			"}";
		}
}