package ew8s.com.myapplication.models;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class ToInfoCar {
    String caption;
    ModelCar modelCar;

    public ToInfoCar(String caption, ModelCar modelCar) {
        this.caption = caption;
        this.modelCar = modelCar;
    }

    public ToInfoCar(){};

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public ModelCar getModelCar() {
        return modelCar;
    }

    public void setModelCar(ModelCar modelCar) {
        this.modelCar = modelCar;
    }
}
