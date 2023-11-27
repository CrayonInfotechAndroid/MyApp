package com.example.myapp.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDAO {
    @Query("select * from expense")
    List<Expense> getAllExpense();

    @Insert
    void addTransaction(Expense expense);

    @Update
    void updateTransaction(Expense expense);

    @Delete
    void deleteTransaction(Expense expense);
}
