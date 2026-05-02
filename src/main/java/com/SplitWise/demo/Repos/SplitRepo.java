package com.SplitWise.demo.Repos;

import com.SplitWise.demo.Models.Split;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SplitRepo extends JpaRepository<Split, Integer>{

    public List<Split> findAllByExpense_Id(int id);
    
}
