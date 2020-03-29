package nl.hva.makeitwork.bankit.bankitapplication.model.company;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    private Integer companyId; //dutch kvk number
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

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", industry=" + industry +
                '}';
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
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
