package com.expensetracker.expenseapp.Controllers;

import com.expensetracker.expenseapp.DTO.CreateExpenseDTO;
import com.expensetracker.expenseapp.DTO.GetExpenseDTO;
import com.expensetracker.expenseapp.Models.Expense;
import com.expensetracker.expenseapp.Services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    private ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service= service;
    }

    //to create  expenses
    @PostMapping("/create/expense")
    public ResponseEntity<?> CreateExpense(
            @RequestBody CreateExpenseDTO expenseDTO) {
          service.createExpense(expenseDTO);
         // System.out.println(expenseDTO);
          return  ResponseEntity.status(HttpStatus.CREATED)
                  .body(expenseDTO);
    }
        // to get all expenses
    @GetMapping("/get/expenses")
    public ResponseEntity<List<GetExpenseDTO>> getExpenses(){
        List<GetExpenseDTO> allExpenses = service.getExpenses();
        //return new ResponseEntity<>(allExpenses,HttpStatus.FOUND);
        System.out.println("this is from controller class" + allExpenses);
        return  ResponseEntity.ok(allExpenses);
    }

        //update expense
    @PutMapping("/update/expense/{id}")
    public  ResponseEntity<?>
    updateExpense(
            @PathVariable long id,
            @RequestBody GetExpenseDTO dto)
    {
        service.updateExpense(dto,id );
        System.out.println(dto);
        System.out.println(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
  @DeleteMapping("/delete/expense/{id}")
    public  ResponseEntity<?> deleteExpense(@PathVariable long id) {
         boolean isDeleted = service.deleteExpense(id);
         if ( (isDeleted)) {
             return ResponseEntity.ok("expense deleted");
         }
         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                 .body("expense not deleted ");
  }
  @GetMapping("/expenses/search")
    public ResponseEntity<List<Expense>> searchExpense(@RequestParam("keyword") String keyword)
  {
      List<Expense> searchResults = service.searchExpenses(keyword);
      return ResponseEntity.ok(searchResults);
  }
}
