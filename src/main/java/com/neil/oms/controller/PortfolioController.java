package com.neil.oms.controller;

import com.neil.oms.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Controller
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @RequestMapping("/portfolio")
    public String portfolioPage(Model model) {
        model.addAttribute("portfolio", portfolioService.getPortfolio(1L));
        return "portfolio"; //portfolio.html
    }
}
