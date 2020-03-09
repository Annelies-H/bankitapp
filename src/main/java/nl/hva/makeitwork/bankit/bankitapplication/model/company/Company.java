package nl.hva.makeitwork.bankit.bankitapplication.model.company;

import nl.hva.makeitwork.bankit.bankitapplication.model.ContactDetails;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String sector;

    public Company() {
    }

    public Company(int companyId, String name, String sector) {
        this.companyId = companyId;
        this.name = name;
        this.sector = sector;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
