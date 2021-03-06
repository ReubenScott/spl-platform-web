package com.kindustry.cashier.service;

import java.util.List;

import com.kindustry.cashier.entity.CommodityEntity;
import com.kindustry.framework.service.IBaseService;

public interface ICashierService extends IBaseService {

  // 設備註冊
  public boolean regester();

  // 通过条形码查询商品
  public CommodityEntity findGoodsByBarcode(String barcode);

  // 预支付接口 客户端提供参数： 商品明细： 会员号， 商品条码 , 数量 ，金额
  public void prePayment(List<CommodityEntity> goods);

}
