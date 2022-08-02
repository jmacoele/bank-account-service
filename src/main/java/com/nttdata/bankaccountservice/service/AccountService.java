package com.nttdata.bankaccountservice.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bankaccountservice.model.document.Account;
import com.nttdata.bankaccountservice.model.document.type.AccountMovementBoundaryFrequencyEnum;
import com.nttdata.bankaccountservice.model.document.type.AccountTypeEnum;
import com.nttdata.bankaccountservice.model.repository.IAccountRepository;
import com.nttdata.bankaccountservice.model.service.IAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService implements IAccountService  {

	@Autowired
	private IAccountRepository accountRepository;
	
	@Override
	public Mono<Account> findById(String id) {
		return this.accountRepository.findById(id).switchIfEmpty(Mono.empty());
	}

	@Override
	public Flux<Account> findAll() {
		return this.accountRepository.findAll().switchIfEmpty(Flux.empty());
	}

	private void validateAccount(Account account) throws Exception {
		
		/***
		 * Reglas para Cuenta de ahorros: 
		 */
		if (account.getAccountType().equals(AccountTypeEnum.SAVINGS)) {
			if (account.getMaintenanceFee() > 0.0) {
				throw new Exception("Cuenta de ahorro debe ser libre de comisión por mantenimiento.");
			}
			
			if (account.getAccountMovementBoundaryType() == null ||
				(account.getAccountMovementBoundaryType() != null && 
				(!account.getAccountMovementBoundaryType().getAccountMovementBoundaryFrequency().equals(AccountMovementBoundaryFrequencyEnum.MOVEMENTBOUNDARY) ||
				account.getAccountMovementBoundaryType().getMovementAmountBoundary() <= 0))) {
				throw new Exception("Cuenta de ahorro debe tener limite de movimientos mensuales.");
			}
		}
		
		/***
		 * Reglas para Cuenta corriente: 
		 */
		if (account.getAccountType().equals(AccountTypeEnum.CHECKINGACCOUNT)) {
			if (account.getMaintenanceFee() <= 0.0) {
				throw new Exception("Cuenta corriente debe tener comisión por mantenimiento.");
			}
			
			if (account.getAccountMovementBoundaryType() != null && 
				!account.getAccountMovementBoundaryType().getAccountMovementBoundaryFrequency().equals(AccountMovementBoundaryFrequencyEnum.MOVEMENTBOUNDARY) &&
				account.getAccountMovementBoundaryType().getMovementAmountBoundary() > 0) {
				throw new Exception("Cuenta corriente no debe tener limite de movimientos mensuales.");
			}			
				
		}
		
		/***
		 * Reglas para Cuenta plazo fijo: 
		 */
		if (account.getAccountType().equals(AccountTypeEnum.TIMEDEPOSIT)) {
			if (account.getMaintenanceFee() >= 0.0) {
				throw new Exception("Cuenta a plazo fijo debe ser libre de comisión por mantenimiento.");
			}
			
			if (account.getAccountMovementBoundaryType() == null ||
				(account.getAccountMovementBoundaryType() != null && 
				!account.getAccountMovementBoundaryType().getAccountMovementBoundaryFrequency().equals(AccountMovementBoundaryFrequencyEnum.MOVEMENTBOUNDARY) &&
				account.getAccountMovementBoundaryType().getMovementAmountBoundary() != 1)) {
				throw new Exception("Cuenta a plazo fijo solo permite un movimiento de retiro o depósito en un día específico del mes.");
			}
				
		}
		
		if (account.getClientIds().isEmpty()) {
			throw new Exception("La cuenta debe estar asociada a un cliente al menos");
		}
		
		/***
		 * Reglas para Clientes Personales
		 */
/*
		account.getClientIds()
			.forEach(x -> {

					accountRepository
						.findAll()
						.filter(a -> a.getClientIds().contains(x) &&
								     a.getAccountType().equals(account.getAccountType()) &&
									!a.getId().equals(account.getId()))
						.flatMap(exists -> {
							log.info("Excepcion jmacoe " + exists.getId());
							return Flux.error( 
								new Exception("Un cliente personal solo puede tener un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo.")); 
						})
						.then();
						

			});
			*/

	}
	
	@Override
	public Mono<Account> save(Account account) throws Exception {
		account.setId(UUID.randomUUID().toString());
		validateAccount(account);
		return this.accountRepository.save(account);
	}
	
	@Override
	public Mono<Account> save(String id, Account account) throws Exception {
		return this.accountRepository.save(account);
	}
	
	@Override
	public Mono<Account> delete(final String id) {
		  final Mono<Account> account = findById(id);
		  if (Objects.isNull(account)) {
		   return Mono.empty();
		  }
		  return findById(id)
				  .switchIfEmpty(Mono.empty())
				  .filter(Objects::nonNull)
				  .flatMap(accountToBeDeleted -> this.accountRepository
						  							.delete(accountToBeDeleted)
						  							.then(Mono.just(accountToBeDeleted)));
	}
	
	@Override
	public Mono<Boolean> existsById(String id) {
		return this.accountRepository.existsById(id);
	}
}
