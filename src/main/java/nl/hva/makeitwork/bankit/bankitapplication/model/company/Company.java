package nl.hva.makeitwork.bankit.bankitapplication.model.company;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    private int companyId; //dutch kvk number
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Industry industry;

    public Company() {
    }

    public Company(int companyId, String name, Industry industry) {
        this.companyId = companyId;
        this.name = name;
        this.industry = industry;
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

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry sector) {
        this.industry = sector;
    }
}
