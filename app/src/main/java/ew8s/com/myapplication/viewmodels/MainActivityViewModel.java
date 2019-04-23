package ew8s.com.myapplication.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ew8s.com.myapplication.InfoCar;
import ew8s.com.myapplication.models.ModelCar;
import ew8s.com.myapplication.repositories.ModelCarRepository;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class MainActivityViewModel extends ViewModel implements Observer<List<ModelCar>>{

    private MutableLiveData<List<ModelCar>> mModelCars;
    private MutableLiveData<Boolean> mAdd = new MutableLiveData<>();
    private ModelCarRepository mRepo;

    public void init(){
        if(mModelCars != null){
            return;
        }
        mRepo = ModelCarRepository.getInstance();
        mModelCars = mRepo.getModelCars();
        mRepo.setObserver(this);
    }

    public void addNewValue(final Context context, final String s){
        Intent intent = new Intent(context, InfoCar.class);
        intent.putExtra("s", s);
        context.startActivity(intent);

    }

    public void remove(int position) {
        List<ModelCar> currentCars = mModelCars.getValue();
        if (position < 0 || position >= currentCars.size()) {
            return;
        }
        currentCars.remove(position);
        mModelCars.postValue(currentCars);
    }

    public void sort(final int w){
        List<ModelCar> currentCars = mModelCars.getValue();
        Collections.sort(currentCars, new Comparator<ModelCar>() {
            @Override
            public int compare(ModelCar o1, ModelCar o2) {
                // TODO Auto-generated method stub
                //
                if(w == 0) {
                    return o1.getPrice() - o2.getPrice();
                } else{
                    return o1.getManufacturer().compareTo(o2.getManufacturer());
                }
            }
        });
        mModelCars.postValue(currentCars);
    }

    public LiveData<List<ModelCar>> getModelCar(){
        return mModelCars;
    }
    public LiveData<Boolean> getUpdate(){
        return mAdd;
    }

    @Override
    public void onChanged(@Nullable List<ModelCar> modelCars) {
        List<ModelCar> currentCars = mModelCars.getValue();
        mModelCars.postValue(currentCars);
    }
}
