package com.example.medicalapp.API.NewsModel;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class ArticlesItem{
	@PrimaryKey(autoGenerate = true)
	int id_db;
	@ColumnInfo
	@SerializedName("publishedAt")
	private String publishedAt;
	@ColumnInfo
	@SerializedName("author")
	private String author;

	@ColumnInfo
	@SerializedName("urlToImage")
	private String urlToImage;
	@ColumnInfo
	@SerializedName("description")
	private String description;
	@Ignore
	@SerializedName("source")
	private Source source;
	@ColumnInfo
	@SerializedName("title")
	private String title;
	@ColumnInfo
	@SerializedName("url")
	private String url;
	@ColumnInfo
	@SerializedName("content")
	private String content;

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSource(Source source){
		this.source = source;
	}

	public Source getSource(){
		return source;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}
	@Ignore
	@Override
	public String toString(){
		return
				"ArticlesItem{" +
						"publishedAt = '" + publishedAt + '\'' +
						",author = '" + author + '\'' +
						",urlToImage = '" + urlToImage + '\'' +
						",description = '" + description + '\'' +
						",source = '" + source + '\'' +
						",title = '" + title + '\'' +
						",url = '" + url + '\'' +
						",content = '" + content + '\'' +
						"}";
	}

	public ArticlesItem() {
	}
}