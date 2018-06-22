package com.tau.account.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private List<Undead> undeadList;
    private String pesel;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {

        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {

        this.passwordConfirm = passwordConfirm;
    }

    @OneToMany
    //(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    public List<Undead> getUndeadList() {
        return undeadList;
    }

    public void setUndeadList(List<Undead> undeadList) {
        this.undeadList = undeadList;
    }

    @Column(unique = true, nullable = false)
    public String getPesel() {
        return pesel;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
