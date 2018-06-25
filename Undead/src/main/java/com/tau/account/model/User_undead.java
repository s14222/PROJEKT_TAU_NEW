package com.tau.account.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_undead")
public class User_undead {

    private Long undeadId;
    private Long userId;

    @Id
    public Long getUndeadId() {
        return undeadId;
    }

    public void setUndeadId(Long undeadId) {
        this.undeadId = undeadId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
