package org.example.account;



import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class AccountMangerTest {
    private AccountManagerImpl manager=new AccountManagerImpl();

    @Test
    void testCustomerWithSufficientBalance()
    {
        Customer customer=new Customer(1000,true,true);
        String result=manager.withdraw(customer,700);
        assertThat(result).isEqualTo("success");

       // assertEquals("success",result);
    }
    @Test
    void testCustomerWithCreditAllowed()
    {
        Customer customerWithCreditAllowed=new Customer(500,false,true);
        String result=manager.withdraw(customerWithCreditAllowed,700);
        assertThat(result).isEqualTo("insufficient account balance");

        //assertEquals("insufficient account balance",result);
    }
    @Test
    void testCustomerWithExpectedBalanceMoreTHanCreditAndNotVip(){
        Customer customerWithExpectedBalanceMoreThanCreditAndNotVip = new Customer(100,true,false);
        String result = manager.withdraw(customerWithExpectedBalanceMoreThanCreditAndNotVip,1900);
        assertThat(result).isEqualTo("maximum credit exceeded");

    }
}
