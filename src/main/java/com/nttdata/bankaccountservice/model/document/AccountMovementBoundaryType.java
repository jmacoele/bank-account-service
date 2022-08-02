package com.nttdata.bankaccountservice.model.document;

import com.nttdata.bankaccountservice.model.document.type.AccountMovementBoundaryFrequencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Account Movemente Boundary Type Class.
 *
 * @author jmacoele
 *
 */

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