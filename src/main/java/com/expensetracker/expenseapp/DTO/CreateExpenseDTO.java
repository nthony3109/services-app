package com.expensetracker.expenseapp.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateExpenseDTO {
       private String expenseName;
       private String expenseDescr;
       private double amount;
       private String category;

       public String getExpenseName() {
              return expenseName;
       }

       public void setExpenseName(String expenseName) {
              this.expenseName = expenseName;
       }

       public String getCategory() {
              return category;
       }

       public void setCategory(String category) {
              this.category = category;
       }

       public double getAmount() {
              return amount;
       }

       public void setAmount(double amount) {
              this.amount = amount;
       }

       public String getExpenseDescr() {
              return expenseDescr;
       }

       public void setExpenseDescr(String expenseDescr) {
              this.expenseDescr = expenseDescr;
       }
}
