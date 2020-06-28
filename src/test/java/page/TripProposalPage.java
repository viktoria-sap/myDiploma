package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TripProposalPage {
    private SelenideElement buttonBuyByDebit = $(byText("Купить"));
    private SelenideElement buttonBuyCredit = $(byText("Купить в кредит"));

    public FillingInCardData selectBuyByDebitCard() {
        buttonBuyByDebit.click();
        return new FillingInCardData();
    }

    public FillingInCardData selectBuyByCreditCard() {
        buttonBuyCredit.click();
        return new FillingInCardData();
    }
}
