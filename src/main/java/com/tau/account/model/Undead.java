package com.tau.account.model;

import javax.persistence.*;

@Entity
@Table(name = "undead")
public class Undead {
    private Long id;
    private String name;
    private String ability;
    private Integer strength;
    private Integer health;
    private String userName;
    private Boolean male;
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Undead(Long id, String name, String ability, Integer strength, Integer health, String userName, Boolean male){

        this.id = id;
        this.name = name;
        this.ability = ability;
        this.strength = strength;
        this.health = health;
        this.male = male;
    }

    public Undead(){

    }
}
