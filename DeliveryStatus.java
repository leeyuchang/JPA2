package com.jpa1.jpa1.domain;

public enum DeliveryStatus {
  READY {
    @Override
    public boolean isCancelable() {
      return true;
    }
  }, // 배송준비（配送準備）
  COMP {
    @Override
    public boolean isCancelable() {
      return false;
    }
  }; // 배송완료（配送完了）

  public abstract boolean isCancelable();

}
