package nl.hva.makeitwork.bankit.bankitapplication.model.dao;

import nl.hva.makeitwork.bankit.bankitapplication.model.user.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Integer> {
}
