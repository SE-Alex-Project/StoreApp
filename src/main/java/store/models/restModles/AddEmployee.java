package store.models.restModles;

import lombok.Data;
import store.models.User;

import javax.validation.constraints.NotBlank;

@Data
public class AddEmployee{
    private User user;

    @NotBlank(message = "Employee Store is required")
    private Integer store;

    @NotBlank(message = "Employee Salary is required")
    private Double Salary;

}