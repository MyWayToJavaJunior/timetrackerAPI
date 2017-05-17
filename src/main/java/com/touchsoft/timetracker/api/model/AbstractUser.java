package com.touchsoft.timetracker.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "u_id")
    private Long id;
    @Column(name = "u_name")
    private String name;
    @JsonIgnore
    @Column(name = "u_active")
    private Boolean active;
    @JsonIgnore
    @Column(name = "u_company_id")
    private Long companyId;
    @Column(name = "u_manager_id")
    private Long manager;
    @Column(name = "u_comanager")
    private Boolean coManager;


    public AbstractUser() {

    }

    public AbstractUser(long userId, String name) {
        this.id = userId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long userID) {
        this.id = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
    }

    public Boolean getCoManager() {
        return coManager;
    }

    public void setCoManager(Boolean coManager) {
        this.coManager = coManager;
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", companyId=" + companyId +
                ", manager=" + manager +
                ", coManager=" + coManager +
                '}';
    }
}
