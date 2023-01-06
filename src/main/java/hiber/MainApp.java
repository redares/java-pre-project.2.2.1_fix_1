package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      //userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("aaa", 111)));
      //userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("bbb", 222)));
      //userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("ccc", 333)));
      //userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("ddd", 444)));

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());

         if (user.getCar() != null) {
            System.out.println("Car = "+ user.getCar().getModel());
         }

         System.out.println();
      }

      System.out.println("User by CAR:");
      User user = userService.getUserByCar(new Car("aaa", 111));
      System.out.println("Id = "+user.getId());
      System.out.println("First Name = "+user.getFirstName());
      System.out.println("Last Name = "+user.getLastName());
      System.out.println("Email = "+user.getEmail());

      context.close();
   }
}
