package com.SplitWise.demo.Controllers;

import com.SplitWise.demo.DTOs.ExpenseDTO;
import com.SplitWise.demo.Services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    @PostMapping("/add")
    public ResponseEntity<Object> addExp(@RequestBody ExpenseDTO req)
    {
        return expenseService.AddExpense(req);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updtExp(@RequestBody ExpenseDTO req, @PathVariable int id)
    {
        return expenseService.UpdateExpense(req, id);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getBal(@PathVariable int id)
    {
        return expenseService.getUserBalances(id);
    }
}
