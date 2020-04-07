package nl.hva.makeitwork.bankit.bankitapplication.service;

import nl.hva.makeitwork.bankit.bankitapplication.model.account.AddAccountholderRequest;
import nl.hva.makeitwork.bankit.bankitapplication.model.repository.AddAccountHolderRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddAccountHolderService {
    @Autowired
    AddAccountHolderRequestDAO dao;

    /**
     * Slaat een nieuwe request op. Als er al een bestaand verzoek is voor deze specifieke rekening en klant
     * dan wordt het oude request geupdte met de nieuwe beveiligingscode.
     * @param request
     */
    public void saveRequest(AddAccountholderRequest request) {
        int id = getRequestID(request.getIban(), request.getBsn());
        request.setId(id);
        dao.save(request);
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




}
