package com.example.medicalapp.Home;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalapp.API.APIManager;
import com.example.medicalapp.API.NewsModel.ArticlesItem;
import com.example.medicalapp.API.NewsModel.NewsResponse;
import com.example.medicalapp.Constants;
import com.example.medicalapp.database.webdatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> showLoading=new MutableLiveData<>();
    public MutableLiveData<String> alertMessage=new MutableLiveData<>();
    public MutableLiveData<List<ArticlesItem>> articles=new MutableLiveData<>();
    List<ArticlesItem> item;
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadNews(){
        showLoading.setValue(true);
        final cheakThread thread = new cheakThread();
        thread.start();
        APIManager.getNewsApis().getNews("medical", Constants.NEWS_API)
                .enqueue(new Callback<NewsResponse>() {

                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if(!response.isSuccessful()){
                            return;
                        }

                       showLoading.setValue(false);

                        articles.setValue(response.body().getArticles());

                        if( item == null||item.isEmpty()){
                        SaveNewsThread threadadd = new SaveNewsThread(response.body().getArticles());
                        threadadd.start();
                       }
                        else {
                            Log.e("shit","back");
                            updateNewsThread threadupdate = new updateNewsThread(response.body().getArticles());
                            threadupdate.start();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        showLoading.setValue(false);
                      //  alertMessage.postValue(t.getMessage());
                        articles.postValue(thread.articlesItems);

                    }
                });

    }
    class SaveNewsThread extends Thread{
        List<ArticlesItem>articlesItems;

        public SaveNewsThread(List<ArticlesItem>items) {
            this.articlesItems = items;
        }

        @Override
        public void run() {
                webdatabase.get_instatnce(getApplication()).newsDao().addnews(articlesItems);

        }
    }

    class updateNewsThread extends Thread{
        List<ArticlesItem>articlesItems;
        public updateNewsThread(List<ArticlesItem>items) {
            this.articlesItems = items;
        }

        @Override
        public void run() {
            webdatabase.get_instatnce(getApplication()).newsDao().updatenews(articlesItems);

        }
    }
    class cheakThread extends Thread{

        List<ArticlesItem>articlesItems;

        @Override
        public void run() {

           item = webdatabase.get_instatnce(getApplication()).newsDao().ARTICLES_ITEMS();
           articlesItems = item;
        }
        public List<ArticlesItem>articlesItemList(){
            return  articlesItems ;
        }
    }

}