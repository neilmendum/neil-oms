package com.neil.oms.controller;

import com.neil.oms.NeilOmsApp;
import com.neil.oms.model.OrderInput;
import com.neil.oms.model.OrderStatus;
import com.neil.oms.model.Portfolio;
import com.neil.oms.model.Side;
import com.neil.oms.model.dto.OrderFormDto;
import com.neil.oms.model.dto.adapter.OrderFormAdapter;
import com.neil.oms.service.OrderService;
import com.neil.oms.service.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by neilmendum on 16/12/2017.
 */
@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private OrderFormAdapter orderFormAdapter;

    @RequestMapping("/order/{side}/{isin}")
    public String orderPage(@PathVariable Side side,
                            @PathVariable String isin,
                            Model model) {
        Portfolio portfolio = portfolioService.getPortfolio(1L);

        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setIsin(isin);
        orderFormDto.setSide(side.name());
        orderFormDto.setPortfolioId(portfolio.getId());

        model.addAttribute("orderFormDto", orderFormDto);
        return "order";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String orderResultsPage(@ModelAttribute OrderFormDto orderFormDto,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.warn("Errors in the form submitted: {}", bindingResult);
        }

        OrderInput input = orderFormAdapter.apply(orderFormDto);
        OrderStatus orderStatus = orderService.placeOrder(input);
        model.addAttribute("orderStatus", orderStatus);

        return "orderResults";
    }
}
