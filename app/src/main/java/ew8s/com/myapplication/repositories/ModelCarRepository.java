package ew8s.com.myapplication.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import ew8s.com.myapplication.models.ModelCar;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class ModelCarRepository {

    private Observer<List<ModelCar>> mObserver;
    private static ModelCarRepository mInstance;
    private ArrayList<ModelCar> mDataSet = new ArrayList<>();

    // Singleton pattern
    public static ModelCarRepository getInstance(){
        if(mInstance == null){
            mInstance = new ModelCarRepository();
        }
        return mInstance;
    }

    public void setObserver(Observer<List<ModelCar>> observer) {
        this.mObserver = observer;
    }

    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<ModelCar>> getModelCars(){
        setModelCars();
        MutableLiveData<List<ModelCar>> data = new MutableLiveData<>();
        data.setValue(mDataSet);
        return data;
    }

    public MutableLiveData<List<ModelCar>> getModelCarsNo(){
        MutableLiveData<List<ModelCar>> data = new MutableLiveData<>();
        data.setValue(mDataSet);
        return data;
    }

    public void addToRepository(ModelCar modelCar){
        mDataSet.add(modelCar);
        mObserver.onChanged(mDataSet);
    }

    public void editToRepository(int i, ModelCar modelCar){
        mDataSet.set(i,modelCar);
        mObserver.onChanged(mDataSet);
    }

    private void setModelCars(){
        mDataSet.add(
                new ModelCar("LADA", "Granta", 18000, 87,
                        "Бензин", "Механическая")
        );
        mDataSet.add(
                new ModelCar("LADA", "Vesta SW Cross", 30340, 122,
                        "Бензин", "Автоматическая")
        );
        mDataSet.add(
                new ModelCar("LADA", "Largus Cross", 25570, 106,
                        "Бензин", "Механическая")
        );
        mDataSet.add(
                new ModelCar("Renault", "Duster", 35200, 109,
                        "Дизель", "Механическая")
        );
        mDataSet.add(
                new ModelCar("Renault", "SANDERO", 21500, 82,
                        "Бензин", "Механическая")
        );
        mDataSet.add(
                new ModelCar("Renault", "KAPTUR", 39000, 143,
                        "Бензин", "Автоматическая")
        );
        mDataSet.add(
                new ModelCar("KIA", "RIO", 28500, 123,
                        "Бензин", "Автоматическая")
        );
        mDataSet.add(
                new ModelCar("KIA", "RIO+", 29500, 153,
                        "Бензин", "Автоматическая")
        );
    }
}











