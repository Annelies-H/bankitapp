package nl.hva.makeitwork.bankit.bankitapplication.model.company;

import nl.hva.makeitwork.bankit.bankitapplication.model.repository.BusinessAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Company implements Comparable<Company>{
    @Id
    private Integer companyId; //dutch kvk number
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Industry industry;
    @Transient
    private double totalBalance;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return companyId.equals(company.companyId) &&
                name.equals(company.name) &&
                industry == company.industry;
    }

    @Override
    public int compareTo(Company o) {
        if (this.getTotalBalance() > o.getTotalBalance() ) {
            return -1;
        } else if (this.getTotalBalance() < o.getTotalBalance() ) {
            return 1;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name, industry);
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

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }
}
