package ru.netology.data;

import lombok.Value;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static CardInfo getCardInfoByNumberOnPage(int numberOnPage) {
        CardInfo cardInfo = null;
        if (numberOnPage == 1) {
            cardInfo = getFirstCardInfo();
        }
        if (numberOnPage == 2) {
            cardInfo = getSecondCardInfo();
        }
        return cardInfo;
    }

    public static CardInfo getCardInfoByNumber(String cardFromNumber) {
        CardInfo cardInfo = null;
        if (cardFromNumber.equals("5559 0000 0000 0001")) {
            cardInfo = getFirstCardInfo();
        }
        if (cardFromNumber.equals("5559 0000 0000 0002")) {
            cardInfo = getSecondCardInfo();
        }
        return cardInfo;
    }

    public static int generateValidAmount(int balance) {
        return new Random().nextInt(balance) + 1;
    }

    public static int generateInvalidAmount(int balance) {
        return Math.abs(balance) + new Random().nextInt(balance * 2);
    }
}
