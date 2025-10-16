package com.expensetracker.expenseapp.Repo;

import com.expensetracker.expenseapp.Models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository <Expense, Long>{

    @Query(value = """
        SELECT * FROM expense e
        WHERE LOWER(e.expense_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(e.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR LOWER(e.expense_decr) LIKE LOWER(CONCAT('%', :keyword, '%'))
           OR DATE_FORMAT(e.date_time, '%d-%m-%Y') LIKE CONCAT('%', :keyword, '%')
        """, nativeQuery = true)
    Page<Expense> searchExpenses(@Param("keyword") String keyword, Pageable pageable);
}
