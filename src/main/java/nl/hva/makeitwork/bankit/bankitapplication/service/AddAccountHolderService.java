package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.AddAccountholderRequest;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.Bankaccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.BusinessAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.account.PrivateAccount;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.*;
import nl.hva.makeitwork.bankit.bankitapplication.model.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddAccountHolderService {
    @Autowired
    AddAccountHolderRequestDAO dao;
    @Autowired
    CustomerDAO cdao;
    @Autowired
    BusinessAccountDAO bdao;
    @Autowired
    PrivateAccountDAO pdao;

    public boolean checkSecretCode(AddAccountholderRequest request) {
        String providedCode = request.getSecretCode();
        String actualCode = dao.findById(request.getId()).get().getSecretCode();
        return providedCode.equals(actualCode);
    }

    public void deleteRequest(AddAccountholderRequest request) {
        dao.deleteAddAccountholderRequestById(request.getId());
    }

    public void acceptRequest(AddAccountholderRequest rq, Customer customer) {
        int id = rq.getId();
        AddAccountholderRequest request = dao.findById(id).get();
        updatePrivateAccount(request.getIban(), customer);
        updateBusinessAccount(request.getIban(), customer);
        dao.deleteAddAccountholderRequestById(id);
    }

    private void updatePrivateAccount(String iban, Customer customer) {
        Optional<PrivateAccount> optional = pdao.findPrivateAccountByIban(iban);
        if (optional.isEmpty()) {
            return;
        }
        PrivateAccount account = optional.get();
        account.addAccountHolder(customer);
        pdao.save(account);
        customer.addAccount(account);
        cdao.save(customer);
    }

    private void updateBusinessAccount(String iban, Customer customer) {
        Optional<BusinessAccount> optional = bdao.findBusinessAccountByIban(iban);
        if (optional.isEmpty()) {
            return;
        }
        BusinessAccount account = optional.get();
        account.addAccountHolder(customer);
        bdao.save(account);
        customer.addAccount(account);
        cdao.save(customer);
    }

    public List<AddAccountholderRequest> getReceivedRequests(int bsn) {
        List<AddAccountholderRequest> result = dao.findAllByBsn(bsn);
        return result;
    }

    public AddAccountholderRequest getRequestById(int id) {
        Optional<AddAccountholderRequest> optRequest = dao.findById(id);
        if (optRequest.isEmpty()) {
            return new AddAccountholderRequest();
        }
        return optRequest.get();
    }

    /**
     * Slaat een nieuwe request op indien de klant niet al rekeninghouder is.
     * Als er al een bestaand verzoek is voor deze specifieke rekening en klant
     * dan wordt het oude request geupdte met de nieuwe beveiligingscode.
     * @param request
     * @resturn of het request is aangemaakt in de db
     */
    public boolean saveRequest(AddAccountholderRequest request) {
        boolean shouldCreateRequest = !isAccountHolder(request.getIban(), request.getBsn());
        if (shouldCreateRequest) {
            int id = getRequestID(request.getIban(), request.getBsn());
            request.setId(id);
            dao.save(request);
        }
        return shouldCreateRequest;
    }

    /**
     *
     * @param iban
     * @param bsn
     * @return AddAccountHolderRequest id
     */
    private int getRequestID(String iban, int bsn) {
        Optional<AddAccountholderRequest> dbresult = dao.findByIbanAndAndBsn(iban, bsn);
        if (dbresult.isEmpty()) {
            return 0;
        }
        return dbresult.get().getId();
    }

    /**
     *
     * @param iban
     * @param bsn
     * @return whether the customer with bsn is already accountholder of account with iban
     */
    public boolean isAccountHolder(String iban, int bsn) {
        Customer c = cdao.findBySocialSecurityNumber(bsn).get();
        boolean result = false;
        for (PrivateAccount account : c.getPrivateAccounts()) {
            if (iban.equals(account.getIban())) {
                result = true;
            }
        }
        for (BusinessAccount account : c.getBusinessAccounts()) {
            if (iban.equals(account.getIban())) {
                result = true;
            }
        }
        return result;
    }





}
