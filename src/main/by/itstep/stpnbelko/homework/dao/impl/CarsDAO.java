package by.itstep.stpnbelko.homework.dao.impl;

import by.itstep.stpnbelko.homework.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarsDAO {

    private static CarsDAO instance = null;
    private static List<Car> cars = new ArrayList<>();

    static {
        cars.add(new Car(1, "BWM", 51000));
        cars.add(new Car(3, "AUDI", 52000));
        cars.add(new Car(2, "OPEL", 52000));
    }

    private CarsDAO() {

    }

    public static CarsDAO getInstance() {
        if (instance == null) {
            instance = new CarsDAO();
        }
        return instance;
    }

    public static List<Car> all() {
        return cars;
    }

    public int add(Car car) {
        int newId = cars.size() + 1;
        car.setId(newId);
        cars.add(car);

        return newId;
    }

    public Car getById(int id) {
        Car car = new Car(id);
        int pos = cars.indexOf(car);
        if (pos >= 0) {
            return cars.get(id);
        } else {
            return null;
        }
    }

    public boolean update(Car car) {
        int pos = cars.indexOf(car);
        if (pos >= 0) {
            cars.set(pos, car);
            return true;
        }
        return false;

    }

    public boolean delete(int id) {
        Car car = new Car(id);
        int pos = cars.indexOf(car);

        if (pos >= 0) {
            return cars.remove(car);
        } else {
            return false;
        }

    }
}
