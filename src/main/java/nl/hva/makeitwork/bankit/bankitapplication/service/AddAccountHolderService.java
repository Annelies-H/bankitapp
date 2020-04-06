package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.AddAccountholderRequest;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.AddAccountHolderRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAccountHolderService {
    @Autowired
    AddAccountHolderRequestDAO dao;

    public void saveRequest(AddAccountholderRequest request) {
        dao.save(request);
    }


}
