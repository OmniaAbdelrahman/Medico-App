package com.example.medicalapp.API.DrugModel;


import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("links")
	private Links links;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("current_page")
	private int currentPage;

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"per_page = '" + perPage + '\'' + 
			",total_count = '" + totalCount + '\'' + 
			",links = '" + links + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",current_page = '" + currentPage + '\'' + 
			"}";
		}
}