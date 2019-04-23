package ew8s.com.myapplication.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import ew8s.com.myapplication.R;
import ew8s.com.myapplication.models.ModelCar;
import ew8s.com.myapplication.models.ToInfoCar;
import ew8s.com.myapplication.repositories.ModelCarRepository;


public class InfoCarActivityViewModel extends ViewModel {

    private MutableLiveData<List<ModelCar>> mModelCars;
    private MutableLiveData<ToInfoCar> mToInfoCar = new MutableLiveData<>();
    private ModelCarRepository mRepo;
    private MutableLiveData<Boolean> mClose = new MutableLiveData<>();
    private Context mContext;
    private ToInfoCar mInfoCar;
    private String mAct="";

    public void init(Context context){
        if(mModelCars != null){
            return;
        }
        mRepo = ModelCarRepository.getInstance();
        mModelCars = mRepo.getModelCarsNo();
        this.mContext = context;
    }

    public ToInfoCar setNomber(String s){
        mAct=s;
        String caption;
        if(s.equals("")){
            caption = mContext.getResources().getString(R.string.tvCaptinNew);
            mInfoCar = new ToInfoCar(caption, new ModelCar());
        } else{
            int nomber = Integer.valueOf(s);
            caption = mContext.getResources().getString(R.string.tvCaptinEdit);
            List<ModelCar> currentCars = mModelCars.getValue();
            mInfoCar = new ToInfoCar(caption, currentCars.get(nomber));
        }
        mToInfoCar.postValue(mInfoCar);
        return mInfoCar;

    }

    public void doWork(ModelCar modelCar){
        if(mAct.equals("")) {
            mRepo.addToRepository(modelCar);
            mClose.postValue(true);
        }else{
            int nomber = Integer.valueOf(mAct);
            mRepo.editToRepository(nomber, modelCar);
            mClose.postValue(true);
        }
    }

    public LiveData<Boolean> getClose(){
        return mClose;
    }

    public LiveData<ToInfoCar> getToModelCar(){
        return mToInfoCar;
    }

}
