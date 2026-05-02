package com.SplitWise.demo.Models;

import com.SplitWise.demo.Models.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class UserGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups groupId;

    public UserGroup(int id, Users userId, Groups groupId) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
    }

    public UserGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Groups getGroupId() {
        return groupId;
    }

    public void setGroupId(Groups groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "UserGroup{" + "id=" + id + ", userId=" + userId + ", groupId=" + groupId + '}';
    }
    
    
}
