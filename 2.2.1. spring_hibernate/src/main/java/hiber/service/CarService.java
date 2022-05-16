package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;
import java.util.Map;

public interface CarService {
    void add(Car car);
    List<Car> listCars();

    List<Object[]> getUserWithCar(String model, int series);

}


