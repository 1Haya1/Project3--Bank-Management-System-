package org.example.p3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.p3.Api.ApiResponse;
import org.example.p3.Model.Account;
import org.example.p3.Model.User;
import org.example.p3.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;




    @PostMapping("/account")
    public ResponseEntity newAccount(@AuthenticationPrincipal User user , @RequestBody @Valid Account account) {
        accountService.CreateAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created"));
    }



    @GetMapping("/details")
    public ResponseEntity accountDetails(@AuthenticationPrincipal User user , Integer accountId) {
        return ResponseEntity.status(200).body(accountService.accountDetail(user.getId(), accountId));
    }


    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user , @PathVariable Integer accountId , @PathVariable Integer amount) {
        accountService.deposit(user.getId(),accountId , amount);

        return ResponseEntity.status(200).body(new ApiResponse("Deposit successfully"));
    }


    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user ,@PathVariable Integer accountId ,  @PathVariable Integer amount){
        accountService.withdraw(user.getId(),accountId , amount);

        return ResponseEntity.status(200).body(new ApiResponse("Withdraw successfully"));
    }




    @PutMapping("/transfer/{userAccountId}/{anotherUserId}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user , @PathVariable Integer userAccountId , @PathVariable Integer anotherUserId , @PathVariable Integer amount){

        accountService.transferFunds(userAccountId, anotherUserId, amount);

        return ResponseEntity.status(200).body(new ApiResponse("Transfer successfully"));
    }

}
