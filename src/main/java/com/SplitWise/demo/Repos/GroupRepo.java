package com.SplitWise.demo.Repos;

import com.SplitWise.demo.Models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<Groups, Integer>{
    
}
