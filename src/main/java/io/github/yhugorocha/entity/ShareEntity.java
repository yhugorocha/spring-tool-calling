package io.github.yhugorocha.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "share")
@Entity
public class ShareEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "company")
    private String company;
    @Column(name = "quantity")
    private int quantity;

    public ShareEntity() {
    }

    public ShareEntity(Long id, String company, int quantity) {
        this.id = id;
        this.company = company;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShareEntity that = (ShareEntity) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, quantity);
    }
}
