package com.neil.oms.service;

import com.neil.oms.model.OrderInput;
import com.neil.oms.model.OrderStatus;
import com.neil.oms.model.OrderStatusMessage;
import com.neil.oms.model.Side;
import com.neil.oms.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.neil.oms.TestArtifacts.*;
import static org.junit.Assert.*;

/**
 * Created by neilmendum on 16/12/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private PortfolioService portfolioService;

    @Mock
    private HardcodedValuesService hardcodedValuesService;

    @Before
    public void setup() {
        orderService = new OrderServiceImpl(portfolioService, hardcodedValuesService);
    }

    @Test
    public void testBuyOrderWasAccepted() {
        // Given
        int amountToBuy = 10;
        OrderInput orderInput = getOrderInput(getPortfolio(), amountToBuy, Side.BUY);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertTrue(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.SUCCESS, o.getOrderStatusMessage());
    }

    @Test
    public void testBuyOrderWasRejectedDueToInsufficientBalance() {
        // Given
        int amountToBuy = 100;
        OrderInput orderInput = getOrderInput(getPortfolio(), amountToBuy, Side.BUY);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertFalse(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.INVALID_BUY, o.getOrderStatusMessage());
    }

    @Test
    public void testBuyOrderWasRejectedDueToNegativeAmount() {
        // Given
        int amountToBuy = -1;
        OrderInput orderInput = getOrderInput(getPortfolio(), amountToBuy, Side.BUY);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertFalse(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.INVALID_NEGATIVE, o.getOrderStatusMessage());
    }

    @Test
    public void testSellOrderWasRejectedDueToPortfolioNotOwningThatStock() {
        // Given
        int amountToSell = 10;
        OrderInput orderInput = getOrderInput(getPortfolio(), amountToSell, Side.SELL);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertFalse(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.INVALID_SELL, o.getOrderStatusMessage());
    }

    @Test
    public void testSellOrderWasRejectedDueToPortfolioNotOwningEnoughOfThatStock() {
        // Given
        int amountToSell = 15;
        OrderInput orderInput = getOrderInput(getPortfolioWithVodaStockAlready(), amountToSell, Side.SELL);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertFalse(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.INVALID_SELL_NEGATIVE, o.getOrderStatusMessage());
    }

    @Test
    public void testSellOrderWasAccepted() {
        // Given
        int amountToSell = 10;
        OrderInput orderInput = getOrderInput(getPortfolioWithVodaStockAlready(), amountToSell, Side.SELL);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertTrue(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.SUCCESS, o.getOrderStatusMessage());
    }

    @Test
    public void testSellOrderWasRejectedDueToNegativeAmount() {
        // Given
        int amountToSell = -1;
        OrderInput orderInput = getOrderInput(getPortfolioWithVodaStockAlready(), amountToSell, Side.SELL);

        // When
        OrderStatus o = orderService.placeOrder(orderInput);

        // Then
        assertFalse(o.getOrderStatusMessage().isValid());
        assertEquals(OrderStatusMessage.INVALID_NEGATIVE, o.getOrderStatusMessage());
    }
}
