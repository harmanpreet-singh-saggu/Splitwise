
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
public class Split {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users userId;
    private double amount;

    public Split(int id, Expense expenseId, Users userId, double amount) {
        this.id = id;
        this.expense = expenseId;
        this.userId = userId;
        this.amount = amount;
    }

    public Split() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Expense getExpenseId() {
        return expense;
    }

    public void setExpenseId(Expense expenseId) {
        this.expense = expenseId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Split{" + "id=" + id + ", expenseId=" + expense + ", userId=" + userId + ", amount=" + amount + '}';
    }
   
    
}
