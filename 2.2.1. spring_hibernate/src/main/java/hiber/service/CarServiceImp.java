package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static hiber.util.HibernateUtil.getSessionFactory;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImp implements CarService {

    @Transactional
    @Override
    public void add(Car car) {
        try (Session session = getSessionFactory().openSession()){
            session.save(car);
        }
    }
    @Transactional
    @Override
    public List<Car> listCars() {
        try (Session session = getSessionFactory().openSession()){
            return session.createQuery("FROM Car", Car.class).getResultList();
        }
    }
    @Transactional
    @Override
    public List<Object[]> getUserWithCar(String model, int series){
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery("FROM User u INNER JOIN Car c ON u.id = c.user WHERE c.model = :model AND c.series = :series");
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.list();
        }
    }
}