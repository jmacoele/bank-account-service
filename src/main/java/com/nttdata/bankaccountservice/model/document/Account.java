package com.nttdata.bankaccountservice.model.document;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bankaccountservice.model.document.type.AccountClientAssociationTypeEnum;
import com.nttdata.bankaccountservice.model.document.type.AccountTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "accounts")
public class Account {
	
	@Id
	private String id;
	
	private String number;

	private AccountTypeEnum accountType;
	
	private AccountClientAssociationTypeEnum clientAssociationType;
	
	@NotEmpty
	private List<String> clientIds;
	
	private List<String> authorizedSignerIds;
	
	private Double maintenanceFee;
	
	private AccountMovementBoundaryType accountMovementBoundaryType;
	
	private Double countableBalance;
	
	private List<AccountTransaction> transactions;
	
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
	
}
