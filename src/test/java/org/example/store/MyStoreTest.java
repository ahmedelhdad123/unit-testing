package org.example.store;

import org.example.account.AccountManagerImpl;
import org.example.account.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class MyStoreTest {
    AccountManagerImpl mockAccountManager= Mockito.mock(AccountManagerImpl.class);
    MyStore myStore=new MyStore(mockAccountManager);

    @Test
    public void testSuccessful()
    {
        Product product=new Product(20,8);
        Customer customer=new Customer(1000,true,true);
        when(mockAccountManager.withdraw(customer,product.getPrice())).thenReturn("success");
        myStore.buy(product,customer);
        assertThat(product.getQuantity()).isEqualTo(7);
    }
    @Test
    public void testOutOfStockPurchase() {
        Product product = new Product(15,0);
        Customer customer = new Customer(1000,true,true);
        assertThatThrownBy(() -> myStore.buy(product, customer)).isInstanceOf(RuntimeException.class)
                .hasMessage("Product out of stock");
        assertThat(customer.getBalance()).isEqualTo(1000);
    }

    @Test
    public void testFailedPayment()
    {
        Product product = new Product(15,10);
        Customer customer = new Customer(10,false,false);
        when(mockAccountManager.withdraw(customer,product.getPrice())).thenReturn("insufficient balance");
        assertThatThrownBy(() -> myStore.buy(product, customer)).isInstanceOf(RuntimeException.class)
                .hasMessage("Payment failure: insufficient balance");
        assertThat(customer.getBalance()).isEqualTo(10);
    }
}
