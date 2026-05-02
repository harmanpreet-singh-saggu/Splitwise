package com.SplitWise.demo.Strategy;

import com.SplitWise.demo.Models.Split;
import com.SplitWise.demo.Models.Users;
import java.util.List;

public interface SplitStrategy {
    List<Split> split(double amount, List<Users> users, List<Double> values);
    
    List<Split> Updatesplit(double amount, List<Split> listSplits, List<Double> values);
}
