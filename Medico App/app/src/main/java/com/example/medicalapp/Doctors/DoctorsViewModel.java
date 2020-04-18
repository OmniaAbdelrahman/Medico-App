package com.example.medicalapp.Doctors;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.medicalapp.API.APIManager;
import com.example.medicalapp.API.DiagnosesModel.DiagnosesResponse;
import com.example.medicalapp.API.DoctorsModel.DataItem;
import com.example.medicalapp.API.DoctorsModel.DoctorResponse;
import com.example.medicalapp.API.DoctorsModel.LanguagesItem;
import com.example.medicalapp.API.DoctorsModel.PhonesItem;
import com.example.medicalapp.API.DoctorsModel.PracticesItem;
import com.example.medicalapp.API.DoctorsModel.SpecialtiesItem;
import com.example.medicalapp.API.DoctorsModel.VisitAddress;
import com.example.medicalapp.Constants;
import com.example.medicalapp.database.AppDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsViewModel extends AndroidViewModel {
    public DoctorsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<DataItem>> doctors = new MutableLiveData<>();
    public static List<String> temp;
    public List<DataItem>datlist;
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<String> alertMessage = new MutableLiveData<>();


    public void loadDoctors(String name) {
        showLoading.setValue(true);

        APIManager.getDoctorApi().getDoctors(Constants.DOCTORAPI, name, "100").enqueue(new Callback<DoctorResponse>() {
            @Override
            public void onResponse(Call<DoctorResponse> call, Response<DoctorResponse> response) {
                if (!response.isSuccessful()) {

                    return;
                }
                showLoading.setValue(false);

                doctors.setValue(response.body().getData());
            }

            @Override
            public void onFailure(Call<DoctorResponse> call, Throwable t) {
                showLoading.setValue(false);
                doctors.setValue(new ArrayList<DataItem>());
            }
        });


    }

    static void loadTemp() {
        temp = new ArrayList<>();
        //cities 0
        temp.add("Seoul");
        temp.add("Tokyo");
        temp.add("Istanbul");
        temp.add("Singapore");
        temp.add("New York");
        temp.add("Dubai");
        temp.add("Paris");
        temp.add("London");
        temp.add("Bangkok");
        //countries 9
        temp.add("South Korea");
        temp.add("Japan");
        temp.add("Turkey");
        temp.add("Malaysia");
        temp.add("U.S.A.");
        temp.add("UAE");
        temp.add("France");
        temp.add("England");
        temp.add("Thailand");
        //phones 18
        temp.add("7146394018");
        temp.add("7556394113");
        temp.add("7146394329");
        temp.add("9816394075");
        temp.add("2146394002");
        //languages 23
        temp.add("English");
        temp.add("French");
        temp.add("Spanish");
        temp.add("Russian");
        temp.add("Arabic");
        //education 28
        temp.add("Harvard University");
        temp.add("University of Oxford");
        temp.add("University of Cambridge");
        temp.add("Stanford University");
        temp.add("University of California, Los Angeles (UCLA)");
        temp.add("UCL (University College London)");
        temp.add("Karolinska Institute");
        temp.add("Johns Hopkins University");
        temp.add("Massachusetts Institute of Technology (MIT)");
        //rating 37
        temp.add("118");
        temp.add("201");
        temp.add("80");
        temp.add("350");
        temp.add("461");
        temp.add("98");
        temp.add("854");
        temp.add("57");
        temp.add("2001");
        temp.add("3854");
        //lat 47
        temp.add("13.736717");
        temp.add("51.509865");
        temp.add("48.864716");
        temp.add("25.276987");
        temp.add("40.730610");

        temp.add("35.652832");
        temp.add("45.464211");
        temp.add("41.390205");
        temp.add("41.015137");
        temp.add("-8.409518");
        //long 47
        temp.add("100.523186");
        temp.add("-0.118092");
        temp.add("2.349014");
        temp.add("55.296249");
        temp.add("-73.935242");

        temp.add("139.839478");
        temp.add("9.191383");
        temp.add("2.154007");
        temp.add("28.979530");
        temp.add("115.188919");

    }

    public static void  check(List<DataItem> doctors ) {
        DataItem dataItem;
        loadTemp();
        for(int i=0;i<doctors.size();i++) {
            dataItem=doctors.get(i);
            int city = new Random().nextInt(8 - 0 + 1) + 0;
            int country = new Random().nextInt(17 - 9 + 1) + 9;
            int phone = new Random().nextInt(22 - 18 + 1) + 18;
            int language = new Random().nextInt(27 - 23 + 1) + 23;
            int education = new Random().nextInt(36 - 28 + 1) + 28;
            int rate = new Random().nextInt(46 - 37 + 1) + 37;
            int lat = new Random().nextInt(56 - 47 + 1) + 47;
            int lng = new Random().nextInt(66 - 57 + 1) + 57;

            //language
            List<LanguagesItem> languagesItem = new ArrayList<>();
            languagesItem.add(new LanguagesItem(temp.get(language)));

            List<PhonesItem> phonesItem = new ArrayList<>();
            phonesItem.add(new PhonesItem(temp.get(phone)));

            VisitAddress visitAddress = new VisitAddress(temp.get(country), temp.get(city));//,2.5,2.5);

            List<SpecialtiesItem> specialtiesItem = new ArrayList<>();
            specialtiesItem.add(new SpecialtiesItem("Family Practitioner", "Specializes in your and your family's total health.", "medical", "Family Medicine"));


            List<PracticesItem> practicesItem = new ArrayList<>();
            practicesItem.add(new PracticesItem(languagesItem, visitAddress, phonesItem, Double.valueOf(temp.get(lng)), Double.valueOf(temp.get(lat))));

            List<Object> educations = new ArrayList<>();
            List<Object> rates = new ArrayList<>();
            Object o;
            String s;
            s = temp.get(education);
            o = (Object) s;
            educations.add(o);
            s = temp.get(rate);
            o = (Object) s;
            rates.add(o);


            if (dataItem.getPractices().isEmpty()) {
                dataItem.setPractices(practicesItem);
            } else {
                if (dataItem.getPractices().get(0).getVisitAddress() == null)
                    dataItem.getPractices().get(0).setVisitAddress(visitAddress);
                if (dataItem.getPractices().get(0).getLanguages() == null)
                    dataItem.getPractices().get(0).setLanguages(languagesItem);
                if (dataItem.getPractices().get(0).getPhones() == null)
                    dataItem.getPractices().get(0).setPhones(phonesItem);
                if (dataItem.getPractices().get(0).getLat() == 0)
                    dataItem.getPractices().get(0).setLat(Double.valueOf(temp.get(lat)));
                if (dataItem.getPractices().get(0).getLon() == 0)
                    dataItem.getPractices().get(0).setLon(Double.valueOf(temp.get(lng)));

            }
            if (dataItem.getSpecialties().isEmpty()) {
                dataItem.setSpecialties(specialtiesItem);
            } else {
                if (dataItem.getSpecialties().get(0).getCategory() == null)
                    dataItem.getSpecialties().get(0).setCategory("medical");

                if (dataItem.getSpecialties().get(0).getActor() == null)
                    dataItem.getSpecialties().get(0).setActor("Doactor");
                if (dataItem.getSpecialties().get(0).getDescription() == null)
                    dataItem.getSpecialties().get(0).setDescription("very cliver");
                if (dataItem.getSpecialties().get(0).getName() == null)
                    dataItem.getSpecialties().get(0).setName("Family Medicine");
            }

            if (dataItem.getEducations().isEmpty())
                dataItem.setEducations(educations);
            //if (dataItem.getRatings().isEmpty())
            dataItem.setRatings(rates);
        }

    }


}