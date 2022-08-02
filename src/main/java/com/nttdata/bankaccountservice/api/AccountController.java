package com.nttdata.bankaccountservice.api;

import com.nttdata.bankaccountservice.model.document.Account;
import com.nttdata.bankaccountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Account Controller.
 *
 * @author jmacoele
 *
 */

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  @Operation(summary = "Get list of Accounts")
  public Flux<Account> getAll() {
    log.info("getAll" + "OK");
    return accountService.findAll().log();
  }

  @GetMapping("{id}")
  @Operation(summary = "Get Account by Id")
  public Mono<Account> getById(@PathVariable("id") final String id) {
    log.info("getById: " + id);
    return accountService.findById(id).log();
  }

  @PutMapping("{id}")
  @Operation(summary = "Update Account by Id")
  public Mono<Account> updateById(@PathVariable("id") final String id,
      @RequestBody final Account account) throws Exception {
    log.info("update: " + id);
    return accountService.save(id, account).log();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create Account")
  public Mono<Account> create(@RequestBody final Account account)
      throws Exception {
    log.info("create: " + account.getNumber());
    return accountService.save(account).log();
  }

  @DeleteMapping("{id}")
  @Operation(summary = "Delete Account")
  public Mono<Account> delete(@PathVariable final String id) {
    log.info("delete: " + id);
    return accountService.delete(id).log();
  }

  @GetMapping("/exists/{id}")
  @Operation(summary = "verify existence of Account")
  public Mono<Boolean> existsById(
      @PathVariable("id") final String id) {
    log.info("exists by: " + id);
    return accountService.existsById(id).log();
  }

}
