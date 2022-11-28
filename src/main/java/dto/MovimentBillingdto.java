package dto;

import lombok.Data;

@Data
public class MovimentBillingdto {

  private String date;
  private String name;
  private String account;
  private String type_moviment;
  private String type_account;
  private String balance_initial;
  private String state;
  private String valor_movimiento;
  private String saldo_disponible;

}
