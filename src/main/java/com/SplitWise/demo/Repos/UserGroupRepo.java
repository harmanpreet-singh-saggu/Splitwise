package com.SplitWise.demo.Repos;

import com.SplitWise.demo.Models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserGroupRepo extends JpaRepository<UserGroup, Integer>
{
    
}
