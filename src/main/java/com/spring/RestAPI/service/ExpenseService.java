package com.spring.RestAPI.service;

import com.spring.RestAPI.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);
    Expense getExpenseById(Long id);
    void deleteExpenseById(Long id);
    Expense saveExpenseDetails(Expense expense);
    Expense updateExpenseDetails(Long id, Expense expense);
    List<Expense> readByCategory (String category , Pageable page);
    List<Expense> readByName(String keyword, Pageable page);
    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);


}
