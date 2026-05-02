package com.SplitWise.demo.Services;

import com.SplitWise.demo.DTOs.ExpenseDTO;
import com.SplitWise.demo.Models.Balances;
import com.SplitWise.demo.Models.Expense;
import com.SplitWise.demo.Models.Groups;
import com.SplitWise.demo.Models.Split;
import com.SplitWise.demo.Models.Users;
import com.SplitWise.demo.Repos.BalanceRepo;
import com.SplitWise.demo.Repos.ExpenseRepo;
import com.SplitWise.demo.Repos.GroupRepo;
import com.SplitWise.demo.Repos.SplitRepo;
import com.SplitWise.demo.Repos.UserRepo;
import com.SplitWise.demo.Strategy.EqualSplit;
import com.SplitWise.demo.Strategy.ExactSplit;
import com.SplitWise.demo.Strategy.PercentageSplit;
import com.SplitWise.demo.Strategy.SplitStrategy;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private GroupRepo groupRepo;
    
    @Autowired
    private ExpenseRepo expenseRepo;
    
    @Autowired
    private SplitRepo splitRepo;
    
    @Autowired
    private BalanceRepo balanceRepo;
    
    @Autowired
    private EqualSplit equal;
    
    @Autowired
    private ExactSplit exact;
    
    @Autowired
    private PercentageSplit percentage;
    
    public ResponseEntity<Object> AddExpense(ExpenseDTO expense)
    {
        try
        {  
            System.out.println(expense);
            Users paidBy = userRepo.findById(expense.getPaidBy()).orElse(null);
            if(paidBy == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"+paidBy);
            }
        
            Groups group = groupRepo.findById(expense.getGroupId()).orElse(null);
            if(group == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User group not found");
            }
        
            List<Users> users = userRepo.findAllById(expense.getUserIds());
        
            //Save Expense
            Expense exp = new Expense();
            exp.setAmount(expense.getAmount());
            exp.setUserId(paidBy);
            exp.setGroupId(group);
            expenseRepo.save(exp);
        
            // Add Splits for all users
            SplitStrategy strategy = getStrategy(expense.getSplitType());
            List<Split> splits = strategy.split(expense.getAmount(), users, expense.getValues());
        
            for(Split s:splits )
            {
                s.setExpenseId( exp);
            }
            splitRepo.saveAll(splits);
        
            //Update Balances
            updateBalances(paidBy,splits);   
            return ResponseEntity.status(HttpStatus.CREATED).body("Expense added successfully");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    public ResponseEntity<Object> UpdateExpense(ExpenseDTO expense, int id)
    {
        try
        {  
            Optional<Expense> exp = expenseRepo.findById(id);
            if(!exp.isPresent())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found with expense id: "+id);
            }          
            
            //Update Expense and save
            Expense uptexp = exp.get();
            uptexp.setAmount(expense.getAmount());
            expenseRepo.save(uptexp);
               
            // Update Splits for all users
            SplitStrategy strategy = getStrategy(expense.getSplitType());
            List<Split> listSplits = splitRepo.findAllByExpense_Id(id);
            
            List<Split> splits = strategy.Updatesplit(expense.getAmount(), listSplits, expense.getValues());
            splitRepo.saveAll(splits);
        
            //Update Balances with new amount 
            updateBalances(exp.get().getUserId(),splits);   
            return ResponseEntity.status(HttpStatus.CREATED).body("Expense Updated successfully");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    private SplitStrategy getStrategy(String type) {
        return switch (type) {
            case "EXACT" -> exact;
            case "PERCENT" -> percentage;
            default -> equal;
        };
    }
    
    private void updateBalances(Users paidBy, List<Split> splits)
    {
        for(Split s: splits)
        {
            if(s.getUserId().getId() == paidBy.getId())
            {
                continue;
            }
            
            Users debtor = s.getUserId();
            double amount = s.getAmount();
            
            Balances balance = balanceRepo.findByFromUserAndToUser(debtor,paidBy);
            if(balance == null)
            {
                balance = new Balances();
                balance.setFromUser(debtor);
                balance.setToUser(paidBy);
                balance.setAmount(amount);
            }
            else
            {
                balance.setAmount(balance.getAmount()+amount);
            }
            balanceRepo.save(balance);
        }
    }
    
    public ResponseEntity<Object> getUserBalances(int userId)
    {
        try 
        {
            Users user = userRepo.findById(userId).orElse(null);
            if(user == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        
            List<Balances> bl = balanceRepo.findByFromUserOrToUser(user, user);
           
            
            if(bl == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No balance available for given "+user);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.OK).body(bl);
            }
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } 
    }
}
