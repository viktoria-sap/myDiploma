package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.TripProposalPage;
import sqlUtils.SqlUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    String wayOfPaymentPay = "pay";
    String wayOfPaymentByCredit = "credit";

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    public void shouldCheckIfSuccessWithValidCardInformationPaymentByDebitCard() throws SQLException {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByDebitCard();
        val validCardInformation = DataHelper.getValidCardInformation();
//        fillingInCardData.checkPaymentMethodIsCorrect(wayOfPaymentPay);
        fillingInCardData.fillCardInformationForSelectedWay(validCardInformation,  wayOfPaymentPay);
        fillingInCardData.checkIfPaymentSuccessful();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByDebitCard = SqlUtils.getStatusForPaymentByDebitCard(paymentId);
        val paymentAmount = SqlUtils.getPaymentAmount(paymentId);
        assertEquals("APPROVED", statusForPaymentByDebitCard);
        assertEquals("4500000", paymentAmount);

    }

    @Test
    public void shouldCheckIfSuccessWithValidCardInformationPaymentByCreditCard() throws SQLException {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        fillingInCardData.fillCardInformationForSelectedWay(validCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfPaymentSuccessful();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByCreditCard = SqlUtils.getStatusForPaymentByCreditCard(paymentId);
        assertEquals("APPROVED", statusForPaymentByCreditCard);
    }

    @Test
    public void shouldCheckIfNotSuccessWithInvalidCardInformationPaymentByDebitCard() throws SQLException {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData.checkIfPaymentNotSuccessful();  //баг, показывает в веб-приложении успех
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByDebitCard = SqlUtils.getStatusForPaymentByDebitCard(paymentId);
        assertThat(statusForPaymentByDebitCard, equalTo("DECLINED"));
    }

    @Test
    public void shouldCheckIfNotSuccessWithInvalidCardInformationPaymentByCreditCard() throws SQLException {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val validCardInformation = DataHelper.getInvalidCardInformation();
        fillingInCardData.fillCardInformationForSelectedWay(validCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfPaymentNotSuccessful(); // баг, показывает в вебе успех
        val paymentId = SqlUtils.getPaymentId();
        val statusForPaymentByCreditCard = SqlUtils.getStatusForPaymentByCreditCard(paymentId);
        assertThat(statusForPaymentByCreditCard, equalTo("DECLINED"));
    }

    @Test
    public void shouldCheckIfNotSuccessWithWrongCardNumber() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongNumber();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckIfNotSuccessWithWrongMonth() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongMonth();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckIfNotSuccessWithWrongYearMoreThan30() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongMonth();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckIfNotSuccessWithWrongYearFromOneNumber() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongMonth();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckIfNotSuccessWithWrongCVC() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongCvc();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckIfNotSuccessWithWrongName() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithWrongName();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }

    @Test
    public void shouldCheckIfNotSuccessWithoutName() {
        val tripProposalPage = new TripProposalPage();
        val fillingInCardData = tripProposalPage.selectBuyByCreditCard();
        val invalidCardInformation = DataHelper.getCardInformationwWithoutName();
        fillingInCardData.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentByCredit);
        fillingInCardData.checkIfWrongFormatOfField();
        val fillingInCardData2 = tripProposalPage.selectBuyByDebitCard();
        fillingInCardData2.fillCardInformationForSelectedWay(invalidCardInformation, wayOfPaymentPay);
        fillingInCardData2.checkIfWrongFormatOfField();
    }
}
