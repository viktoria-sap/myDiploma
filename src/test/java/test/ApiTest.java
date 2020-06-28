package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restApiHelper.RestApiHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080"); }

    @Test
    public void shouldCheckStatusViaAPIByDebitCardWithValidData() {
        val validCardInformation = DataHelper.getValidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByDebitCard(validCardInformation);
        assertTrue(response.contains("APPROVED"), "Is true when status is approved");
    }

    @Test
    public void shouldCheckStatusViaAPIByDebitCardWithInvalidData() {
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByDebitCard(invalidCardInformation);
        assertTrue(response.contains("DECLINED"),"Is true when status is declined" );
    }

    @Test
    public void shouldCheckStatusViaAPIByCreditCardWithValidData() {
        val validCardInformation = DataHelper.getValidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByCreditCard(validCardInformation);
        assertTrue(response.contains("APPROVED"), "Is true when status is approved");
    }

    @Test
    public void shouldCheckStatusViaAPIByCreditCardWithInvalidData() {
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        final String response = RestApiHelper.fillPaymentFormByCreditCard(invalidCardInformation);
        assertTrue(response.contains("DECLINED"), "Is true when status is declined");
    }
}
