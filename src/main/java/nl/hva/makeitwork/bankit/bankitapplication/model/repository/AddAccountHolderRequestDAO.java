package nl.hva.makeitwork.bankit.bankitapplication.model.repository;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.AddAccountholderRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddAccountHolderRequestDAO  extends CrudRepository<AddAccountholderRequest, Integer> {

    public Optional<AddAccountholderRequest> findByIbanAndAndBsn(String iban, int bsn);

}
