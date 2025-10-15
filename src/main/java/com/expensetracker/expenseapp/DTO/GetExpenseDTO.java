package com.expensetracker.expenseapp.DTO;

import com.expensetracker.expenseapp.Models.Expense;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetExpenseDTO {
    private Long id;
    private String expenseName;
    private String expenseDecr;
    private double amount;
    private String category;
    private LocalDateTime dateTime;


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseDecr() {
        return expenseDecr;
    }

    public void setExpenseDecr(String expenseDecr) {
        this.expenseDecr = expenseDecr;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
