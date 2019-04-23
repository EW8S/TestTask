package ew8s.com.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ew8s.com.myapplication.models.ModelCar;
import ew8s.com.myapplication.models.ToInfoCar;
import ew8s.com.myapplication.viewmodels.InfoCarActivityViewModel;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class InfoCar extends AppCompatActivity {

    private Button mButton;
    private TextView mCaption;

    private EditText mManufacturer;
    private EditText mModel;
    private EditText mPrice;
    private EditText mPower;
    private EditText mFuel;
    private EditText mTransmission;

    InfoCarActivityViewModel mInfoCarActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_car);

        Intent intent = getIntent();
        String s = intent.getStringExtra("s");

        mCaption = findViewById(R.id.tvCaption);
        mManufacturer = findViewById(R.id.edManufacturer);
        mModel = findViewById(R.id.edModel);
        mPrice = findViewById(R.id.edPrice);
        mPower = findViewById(R.id.edPower);
        mFuel = findViewById(R.id.edFuel);
        mTransmission = findViewById(R.id.edTransmission);

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelCar modelCar = new ModelCar(
                        mManufacturer.getText().toString(),
                        mModel.getText().toString(),
                        Integer.valueOf(mPrice.getText().toString()),
                        Integer.valueOf(mPower.getText().toString()),
                        mFuel.getText().toString(),
                        mTransmission.getText().toString());
                mInfoCarActivityViewModel.doWork(modelCar);
            }
        });

        mInfoCarActivityViewModel = ViewModelProviders.of(this).get(InfoCarActivityViewModel.class);
        mInfoCarActivityViewModel.init(this.getApplicationContext());
        mInfoCarActivityViewModel.setNomber(s);

        mInfoCarActivityViewModel.getClose().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    mInfoCarActivityViewModel = null;
                    finish();
                }
            }
        });

        mInfoCarActivityViewModel.getToModelCar().observe(this, new Observer<ToInfoCar>() {
            @Override
            public void onChanged(@Nullable ToInfoCar toInfoCar) {

                String text = getApplicationContext().getString(R.string.tvCaptinNew);
                if(toInfoCar.getCaption().equals(text)){
                    mCaption.setText(toInfoCar.getCaption());
                    mManufacturer.setText("");
                    mModel.setText("");
                    mPrice.setText("");
                    mPower.setText("");
                    mFuel.setText("");
                    mTransmission.setText("");
                    mButton.setText(getResources().getString(R.string.buttonNew));
                }else {
                    mCaption.setText(toInfoCar.getCaption());
                    mManufacturer.setText(toInfoCar.getModelCar().getManufacturer());
                    mModel.setText(toInfoCar.getModelCar().getModel());
                    mPrice.setText(String.valueOf(toInfoCar.getModelCar().getPrice()));
                    mPower.setText(String.valueOf(toInfoCar.getModelCar().getPower()));
                    mFuel.setText(toInfoCar.getModelCar().getFuel());
                    mTransmission.setText(toInfoCar.getModelCar().getManufacturer());
                    mButton.setText(getResources().getString(R.string.buttonEdit));
                }
            }
        });
    }
}
