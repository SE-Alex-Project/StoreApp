package store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import store.models.Employee;
import store.models.Role;
import store.models.Store;
import store.models.User;
import store.repos.EmployeeRepo;
import store.repos.StoreRepo;
import store.repos.UserRepo;

@SpringBootTest
public class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeerepo;
    @Autowired
    private StoreRepo storeRepo;

    @Test
    void saveEmployee() {
//        User user = User.builder()
//                .email("yousef@gmail.com")
//                .fName("yousef")
//                .lName("hassan")
//                .password("12345")
//                .confirmPassword("12345")
//                .role(Role.EMPLOYEE)
//                .build();
//
//        Store store =Store.builder()
//                .location("alex")
//                .name("store_1")
//                .build();
//        storeRepo.save(store);
//
//        Employee employee = Employee.builder()
////                .eRole("EMPLOYEE")
//                .salary(2500)
//                .store(store)
//                .user(user)
//                .build();
//        employeerepo.save(employee);
//
//        //////////////////////////////////////////////////
//        User user2 = User.builder()
//                .email("ahmed@gmail.com")
//                .fName("ahmed")
//                .lName("abdallah")
//                .password("12345")
//                .confirmPassword("12345")
//                .role(Role.EMPLOYEE)
//                .build();
//
//        Employee employee2 = Employee.builder()
////                .eRole("EMPLOYEE")
//                .salary(5000)
//                .store(store)
//                .user(user2)
//                .build();
//
//        employeerepo.save(employee2);
    }
    @Test
    void getStore() {

    }

}
