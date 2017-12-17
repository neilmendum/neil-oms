package com.neil.oms.controller;

import com.neil.oms.model.Stock;
import com.neil.oms.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/home")
    public String inventoryPage(Model model) {
        List<Stock> allStocks = inventoryService.getInventory();
        model.addAttribute("stocks", allStocks);

        return "home";
    }
}
