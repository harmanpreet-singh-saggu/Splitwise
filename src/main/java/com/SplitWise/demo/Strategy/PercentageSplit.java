package com.SplitWise.demo.Strategy;

import com.SplitWise.demo.Models.Split;
import com.SplitWise.demo.Models.Users;
import com.SplitWise.demo.Repos.SplitRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PercentageSplit implements SplitStrategy{
        
    @Override
    public List<Split> split(double amount, List<Users> users, List<Double> values) {
        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            Split s = new Split();
            s.setUserId(users.get(i));
            s.setAmount(amount * values.get(i)/100);
            splits.add(s);
        }
        return splits;
    }

    @Override
    public List<Split> Updatesplit(double amount, List<Split> listSplits, List<Double> values) {
        List<Split> splits = new ArrayList<>();
        
        for (int i = 0; i < listSplits.size(); i++) {
            Split s = listSplits.get(i);
            s.setAmount(amount * values.get(i)/100);
            splits.add(s);
        }
        return splits;
    }
    
}
