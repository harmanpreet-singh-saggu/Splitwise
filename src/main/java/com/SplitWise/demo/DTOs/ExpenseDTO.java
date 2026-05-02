package com.SplitWise.demo.DTOs;

import java.util.List;

public class ExpenseDTO {
    
    public double amount;
    public int paidBy;
    public int groupId;
    public List<Integer> userIds;
    public String splitType;  // EXACT OR EQUAL OR PERCENTAGE
    public List<Double> values;

    public ExpenseDTO(double amount, int paidBy, int groupId, List<Integer> userIds, String splitType, List<Double> values) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.groupId = groupId;
        this.userIds = userIds;
        this.splitType = splitType;
        this.values = values;
    }

    public ExpenseDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(int paidBy) {
        this.paidBy = paidBy;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
    
    
}
