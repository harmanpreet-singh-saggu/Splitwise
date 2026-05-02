package com.SplitWise.demo.Repos;

import com.SplitWise.demo.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer>{
    
}
