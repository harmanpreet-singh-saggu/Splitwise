package com.SplitWise.demo.Repos;

import com.SplitWise.demo.Models.Balances;
import com.SplitWise.demo.Models.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepo extends JpaRepository<Balances, Integer>{

    public Balances findByFromUserAndToUser(Users debtor, Users paidBy);

    public List<Balances> findByFromUserOrToUser(Users user, Users user0);
    
}
