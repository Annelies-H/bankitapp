package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

    public Optional<Employee> findEmployeeByUsername(String username);
    public Employee findEmployeeByUserId(int userId);
}
