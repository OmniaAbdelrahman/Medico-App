package com.example.medicalapp.Drugs;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.medicalapp.API.APIManager;
import com.example.medicalapp.API.DrugModel.DrugInfoModel.DrugInfoResponse;
import com.example.medicalapp.API.DrugModel.DrugResponse;
import com.example.medicalapp.API.DrugModel.RecordsItem;
import com.example.medicalapp.database.webdatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrugsViewModel  extends AndroidViewModel {
    public DrugsViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<DrugResponse> Drug = new MutableLiveData<>();
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<String> alertMessage = new MutableLiveData<>();
    public MutableLiveData<DrugInfoResponse>drugInfo=new MutableLiveData<>();
    List<RecordsItem>item;
    public void loadDrugs(String page) {
        showLoading.setValue(true);
        final cheakThread  thread = new cheakThread();
        thread.start();
        APIManager.getDrugApi().getDrugs(page,"30").enqueue(new Callback<DrugResponse>() {
            @Override
            public void onResponse(Call<DrugResponse> call, Response<DrugResponse> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                showLoading.setValue(false);
                Drug.setValue(response.body());
                if(item==null||item.isEmpty()){
                    SavedrugThread savedrugThread = new SavedrugThread(response.body().getRecords());
                    savedrugThread.start();
                }else
                {
                    updatedrugsThread updatedrugsThread =  new updatedrugsThread(response.body().getRecords());
                    updatedrugsThread.start();
                }
            }

            @Override
            public void onFailure(Call<DrugResponse> call, Throwable t) {
                showLoading.setValue(false);
                DrugResponse drugResponse = new DrugResponse();
                drugResponse.setMeta(null);
                drugResponse.setRecords(thread.ItemList());
                Drug.setValue(drugResponse);

            }
        });


    }

    public void loadDrugInfo(String drugId){
        APIManager.getDrugApi().getDrugInfo(drugId).enqueue(new Callback<DrugInfoResponse>() {
            @Override
            public void onResponse(Call<DrugInfoResponse> call, Response<DrugInfoResponse> response) {
                if(!response.isSuccessful()){

                    return;
                }
                showLoading.setValue(false);
                System.out.println("yaaaaaaaaaaaaaaa"+response.body().getAttributes().get(1).getName());

            }

            @Override
            public void onFailure(Call<DrugInfoResponse> call, Throwable t) {
                showLoading.setValue(false);

                System.out.println("yallll"+t.getLocalizedMessage());

            }
        });
    }
    class SavedrugThread extends Thread{
        List<RecordsItem> Items;

        public SavedrugThread(List<RecordsItem> Items) {
            this.Items = Items;
        }

        @Override
        public void run() {
            webdatabase.get_instatnce(getApplication()).drugDAo().add_drug(Items);

        }
    }

    class updatedrugsThread extends Thread{
        List<RecordsItem> Items;

        public updatedrugsThread(List<RecordsItem> items) {
            Items = items;
        }

        @Override
        public void run() {
            webdatabase.get_instatnce(getApplication()).drugDAo().update_drug(Items);

        }
    }
    class cheakThread extends Thread{

        List<RecordsItem>Items;

        @Override
        public void run() {

            item = webdatabase.get_instatnce(getApplication()).drugDAo().RECORDS_ITEMS();
            this.Items = item;
        }
        public List<RecordsItem>ItemList(){
            return  Items ;
        }
    }
}