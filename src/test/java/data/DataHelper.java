package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static CardInformation getValidCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", faker.name().fullName(), "343");
    }

    public static CardInformation getInvalidCardInformation() {
        return new CardInformation("4444 4444 4444 4442", "22", "11", faker.name().fullName(), "343");
    }

    public static CardInformation getCardInformationwWithWrongNumber() {
        return new CardInformation("4444 4444", "22", "11", faker.name().fullName(), "343");
    }

    public static CardInformation getCardInformationwWithWrongMonth() {
        return new CardInformation("4444 4444 4444 4441", "3", "4", faker.name().fullName(), "343");
    }

    public static CardInformation getCardInformationwWithWrongCvc() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", faker.name().fullName(), "34");
    }

    public static CardInformation getCardInformationwWithWrongName() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", "45453", "34");
    }

    public static CardInformation getCardInformationwWithoutName() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", " ", "34");
    }

    @Value
    public static class CardInformation {
        private String number, year, month, holder, cvc;
    }
}
