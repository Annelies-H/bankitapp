package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.PrivateAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    @Autowired
    private BusinessAccountDAO bdao;
    @Autowired
    private PrivateAccountDAO pdao;

    
}
