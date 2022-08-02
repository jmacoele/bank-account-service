package com.nttdata.bankaccountservice.model.document;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bankaccountservice.model.document.type.AccountMovementBoundaryFrequencyEnum;

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
@Document(value = "accountmovementlimit")
public class AccountMovementBoundaryType {

  private AccountMovementBoundaryFrequencyEnum accountMovementBoundaryFrequency;

  private Long movementAmountBoundary;

  private int dayOfMonth;

}