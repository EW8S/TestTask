package ew8s.com.myapplication.models;

/**
 * Created by Aliaksandr Kisel on 02.02.2019.
 */

public class ModelCar {
    private String manufacturer;
    private String model;
    private int price;
    private int power;
    private String fuel;
    private String transmission;

    public ModelCar(String manufacturer, String model, int price, int power, String fuel, String transmission) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.power = power;
        this.fuel = fuel;
        this.transmission = transmission;
    }

    public ModelCar(){};

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

}
