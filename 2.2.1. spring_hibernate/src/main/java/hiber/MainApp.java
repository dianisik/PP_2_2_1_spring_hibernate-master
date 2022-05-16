package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      //userService.add(user1);
      carService.add(new Car("Audi", 123, user1));
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      //userService.add(user2);
      carService.add(new Car("BVW", 5, user2));
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      carService.add(new Car("Mercedes", 7, user3));
      //userService.add(user3 );
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      //userService.add(user4);
      carService.add(new Car("Ford", 3, user4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      List<Car> cars= carService.listCars();
      System.out.println("_____ users with cars _____");
      for (Car car: cars) {
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println("User id = " + car.getUser().getId());
      }
      List<Object[]> userWithCar = carService.getUserWithCar("Audi", 123);
      for (Object[] array : userWithCar) {
         User user = (User) array[0];
         Car car = (Car) array[1];
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Id = " + car.getId());
         System.out.println("Model = " + car.getModel());
         System.out.println("Series = " + car.getSeries());
         System.out.println("User id = " + car.getUser().getId());
         System.out.println("_____________________________________");
      }

      context.close();
   }
}
