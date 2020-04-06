package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.AddAccountholderRequest;
import org.springframework.data.repository.CrudRepository;

public interface AddAccountHolderRequestDAO  extends CrudRepository<AddAccountholderRequest, Integer> {

}
