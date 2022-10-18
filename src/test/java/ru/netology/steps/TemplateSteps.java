package ru.netology.steps;

import io.cucumber.java.ru.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPageV1;
import ru.netology.pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TemplateSteps {
    private static LoginPageV1 loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;

    @Дано("пользователь залогинен с именем {string} и паролем {string}")
    public void userLogged(String login, String password) {
        loginPage = open("http://localhost:9999/", LoginPageV1.class);
        verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify("12345");
        dashboardPage.dashBoardVisible();
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы с data-test-id= {string}")
    public void transferFromCard2ToCard1(int transferSum, String arg1, int arg2, String arg3) {
        dashboardPage.transferFromCard2ToCard1((transferSum));
    }

    @Тогда("баланс его {int} карты из списка на главной страницы с data-test-id= {string} должен стать {int}")
    public void checkNewBalance(int arg0, String idCard, int newBalance) {
        assert dashboardPage.getCardBalance(idCard) == newBalance;
    }
}
