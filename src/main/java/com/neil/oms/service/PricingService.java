package com.neil.oms.service;


import com.neil.oms.model.Stock;

/**
 * Created by neilmendum on 16/12/2017.
 */
public interface PricingService {

    Stock updatePrice(Stock stock);

    Stock getStock(String isin);

}
