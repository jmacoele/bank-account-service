package com.nttdata.bankaccountservice.model.document.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account Movement Boundary Frequency Enum
 * 
 * @author jmacoele
 *
 */

public enum AccountMovementBoundaryFrequencyEnum {
  MOVEMENTBOUNDARY("M"), DATEBOUNDARY("D");

  private String value;

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static AccountMovementBoundaryFrequencyEnum of(
      String value) {
    if (null == value) {
      return null;
    }

    for (AccountMovementBoundaryFrequencyEnum item : AccountMovementBoundaryFrequencyEnum
        .values()) {
      if (value.equals(item.getValue())) {
        return item;
      }
    }

    throw new RuntimeException(
        "AccountMovementBoundaryFrequencyEnum: unknown value: "
            + value);
  }

  AccountMovementBoundaryFrequencyEnum(String value) {
    this.value = value;
  }

}