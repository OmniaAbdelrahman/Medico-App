package com.example.medicalapp.API.DoctorsModel;


import com.google.gson.annotations.SerializedName;


public class InsurancesItem{

	@SerializedName("insurance_plan")
	private InsurancePlan insurancePlan;

	@SerializedName("insurance_provider")
	private InsuranceProvider insuranceProvider;

	public void setInsurancePlan(InsurancePlan insurancePlan){
		this.insurancePlan = insurancePlan;
	}

	public InsurancePlan getInsurancePlan(){
		return insurancePlan;
	}

	public void setInsuranceProvider(InsuranceProvider insuranceProvider){
		this.insuranceProvider = insuranceProvider;
	}

	public InsuranceProvider getInsuranceProvider(){
		return insuranceProvider;
	}

	@Override
 	public String toString(){
		return 
			"InsurancesItem{" + 
			"insurance_plan = '" + insurancePlan + '\'' + 
			",insurance_provider = '" + insuranceProvider + '\'' + 
			"}";
		}
}