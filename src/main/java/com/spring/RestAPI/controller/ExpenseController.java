package com.spring.RestAPI.controller;

import com.spring.RestAPI.entity.Expense;
import com.spring.RestAPI.service.ExpenseService;
import com.spring.RestAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;


    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page){
   //     return expenseService.getAllExpenses(page);
        return expenseService.getAllExpenses(page).toList();
    }

/*   pass parameter by path variable
    @GetMapping("/expense/{id}")
    public String getAllExpenseById(@PathVariable("id") Long id){
        return "The expense id is "+id;
    }*/

    @GetMapping("/expense/{id}")
    public Expense getAllExpenseById(@PathVariable("id") Long id){
        return expenseService.getExpenseById(id);
    }




  /*  @DeleteMapping("/expense")
    public String deleteExpenseById(@RequestParam Long id)
    {
        return "using query string to pass id = "+id;
    }*/
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expense")
    public void deleteExpenseById(@RequestParam Long id)
    {
         expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
        expense.setUser(userService.getLoggedInUser());
         return expenseService.saveExpenseDetails(expense);

    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseService.readByCategory(category, page);
    }


    @GetMapping("/expenses/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page) {
        return expenseService.readByName(keyword, page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpensesByDates(@RequestParam(required = false) Date startDate,
                                            @RequestParam(required = false) Date endDate,
                                            Pageable page) {
        return expenseService.readByDate(startDate, endDate, page);
    }



}
