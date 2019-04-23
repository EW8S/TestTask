package ew8s.com.myapplication.adapters;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import ew8s.com.myapplication.R;
import ew8s.com.myapplication.models.ModelCar;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelCar> mModelCars = new LinkedList<>();
    private Context mContext;
    private MutableLiveData<String> mString = new MutableLiveData<>();

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    Observer<String> observer;

    public RecyclerAdapter(Context context, List<ModelCar> modelCars) {
        mModelCars = modelCars;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ((ViewHolder)viewHolder).mManufacturer.setText(mModelCars.get(i).getManufacturer());
        ((ViewHolder)viewHolder).mModel.setText("Модель - "+mModelCars.get(i).getModel());
        ((ViewHolder)viewHolder).mPrice.setText("Цена "+String.valueOf(mModelCars.get(i).getPrice())+" BYN");
        ((ViewHolder)viewHolder).mPower.setText("Мощность "+String.valueOf(mModelCars.get(i).getPower())+" Л.С.");
        ((ViewHolder)viewHolder).mFuel.setText("Топливо "+mModelCars.get(i).getFuel());
        ((ViewHolder)viewHolder).mTransmission.setText("Трансмиссия "+mModelCars.get(i).getTransmission());

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                observer.onChanged(String.valueOf(i));
                return false;
            }

    });

    }



    @Override
    public int getItemCount() {
        return mModelCars.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mManufacturer;
        private TextView mModel;
        private TextView mPrice;
        private TextView mPower;
        private TextView mFuel;
        private TextView mTransmission;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mManufacturer = itemView.findViewById(R.id.manufacturer);
            mModel = itemView.findViewById(R.id.model);
            mPrice = itemView.findViewById(R.id.price);
            mPower = itemView.findViewById(R.id.power);
            mFuel = itemView.findViewById(R.id.fuel);
            mTransmission = itemView.findViewById(R.id.transmission);

        }
    }
}
