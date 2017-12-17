package com.neil.oms.service;

import com.neil.oms.model.*;
import com.neil.oms.service.impl.PortfolioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.neil.oms.TestArtifacts.*;
import static org.junit.Assert.*;

/**
 * Created by neilmendum on 16/12/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PortfolioServiceTest {

    private PortfolioService portfolioService;

    @Mock
    private HardcodedValuesService hardcodedValuesService;

    @Before
    public void setup() {
        portfolioService = new PortfolioServiceImpl(hardcodedValuesService);
    }

    @Test
    public void testUpdateForBuyIfNoPreviousBuys() {
        // Given
        int amountToBuy = 10;
        Portfolio portfolio = getPortfolio();
        OrderInput orderInput = getOrderInput(portfolio, amountToBuy, Side.BUY);

        // When
        portfolioService.updatePortfolioForBuyOrder(orderInput);

        // Then
        assertEquals(Integer.valueOf(amountToBuy), portfolio.getInventory().get(getVodaIsin()));
        assertEquals(BigDecimal.ZERO, portfolio.getBalance());
    }

    @Test
    public void testUpdateForBuyIfHasPreviousBuys() {
        // Given
        int amountToBuy = 10;
        Portfolio portfolio = getPortfolioWithVodaStockAlready();
        OrderInput orderInput = getOrderInput(portfolio, amountToBuy, Side.BUY);

        // When
        portfolioService.updatePortfolioForBuyOrder(orderInput);

        // Then
        assertEquals(Integer.valueOf(20), portfolio.getInventory().get(getVodaStock().getIsin())); // 10 + 10
        assertEquals(BigDecimal.ZERO, portfolio.getBalance());
    }

    @Test
    public void testUpdateForSellAll() {
        // Given
        int amountToSell = 10;
        Portfolio portfolio = getPortfolioWithVodaStockAlready();
        OrderInput orderInput = getOrderInput(portfolio, amountToSell, Side.SELL);

        // When
        portfolioService.updatePortfolioForSellOrder(orderInput);

        // Then
        assertNull(portfolio.getInventory().get(getVodaStock().getIsin()));
        assertEquals(BigDecimal.valueOf(200), portfolio.getBalance()); // 100 + 100
    }

    @Test
    public void testUpdateForSellHalf() {
        // Given
        int amountToSell = 5;
        Portfolio portfolio = getPortfolioWithVodaStockAlready();
        OrderInput orderInput = getOrderInput(portfolio, amountToSell, Side.SELL);

        // When
        portfolioService.updatePortfolioForSellOrder(orderInput);

        // Then
        assertEquals(Integer.valueOf(amountToSell), portfolio.getInventory().get(getVodaStock().getIsin()));
        assertEquals(BigDecimal.valueOf(150), portfolio.getBalance()); // 100 + 50
    }
}
