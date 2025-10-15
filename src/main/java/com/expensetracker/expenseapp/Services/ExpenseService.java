package com.expensetracker.expenseapp.Services;

import com.expensetracker.expenseapp.DTO.CreateExpenseDTO;
import com.expensetracker.expenseapp.DTO.GetExpenseDTO;
import com.expensetracker.expenseapp.Models.Expense;
import com.expensetracker.expenseapp.Repo.ExpenseRepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private ExpenseRepo expenseRepo;
    public  ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo =  expenseRepo;
    }

    public void createExpense(CreateExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setExpenseName(expenseDTO.getExpenseName());
        expense.setExpenseDecr(expenseDTO.getExpenseDescr());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        System.out.println("this is in the service layer" + expense);
        expenseRepo.save(expense);
    }
    public List<GetExpenseDTO> getExpenses() {
        List<Expense> allExpenses = expenseRepo.findAll();
//        List<GetExpenseDTO> dtoList = new ArrayList<>();
//        for (Expense expense : allExpenses) {
//            dtoList.add(new GetExpenseDTO(
//                    expense.getExpenseName(),
//                    expense.getExpenseDecr(),
//                    expense.getAmount(),
//                    expense.getCategory(),
//                    expense.getDateTime()
//            ));
//        }
//        return dtoList;

  return allExpenses.stream()
          .map(expense -> new GetExpenseDTO(
                  expense.getId(),
                   expense.getExpenseName(),
                   expense.getExpenseDecr(),
                  expense.getAmount(),
                   expense.getCategory(),
                   expense.getDateTime()
           )).collect(Collectors.toList());

    }


    public void updateExpense(GetExpenseDTO dto, long id) {
        expenseRepo.findById(id)
                .map( expense ->{
                    expense.setExpenseName(dto.getExpenseName());
                    expense.setExpenseDecr(dto.getExpenseDecr());
                    expense.setAmount(dto.getAmount());
                    expense.setCategory(dto.getCategory());
                return  expenseRepo.save(expense);
                } );
    }

    public boolean deleteExpense(long id) {
        boolean exists = expenseRepo.existsById(id);
        if(!exists) {
                        return false;
        }
        expenseRepo.deleteById(id);
        boolean deleted = !expenseRepo.existsById(id);
               return deleted;
    }
    public List<Expense> searchExpenses (String keyword) {
        if ( keyword == null  || keyword.trim().isEmpty()) {
            return List.of();
        }
        return expenseRepo.searchExpenses(keyword.trim());
    }
}
