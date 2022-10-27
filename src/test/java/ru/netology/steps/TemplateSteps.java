package ru.netology.steps;

import io.cucumber.java.ru.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPageV1;
import ru.netology.pages.TransferPage;
import ru.netology.pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static junit.framework.TestCase.assertEquals;

public class TemplateSteps {
    private static DashboardPage dashboardPage;

    @Дано("пусть пользователь залогинен с именем {string} и паролем {string}")
    public void пустьПользовательЗалогиненСИменемИПаролем(String login, String password) {
        var loginPage = open("http://localhost:9999/", LoginPageV1.class);
        var authInfo = new DataHelper.AuthInfo(login, password);
        var verificationPage = loginPage.validLogin(authInfo);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
    }

    @Когда("когда пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void когдаПользовательПереводитРублейСКартыСНомеромНаСвоюКартуСГлавнойСтраницы(int amount, String cardFromNumber, int cardForTransfer) {
        var transferPage = dashboardPage.selectCardToTransfer(DataHelper.getCardInfoByNumberOnPage(cardForTransfer));
        transferPage.makeValidTransfer(String.valueOf(amount), DataHelper.getCardInfoByNumber(cardFromNumber));
    }

    @Тогда("тогда баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void тогдаБалансЕгоКартыИзСпискаНаГлавнойСтраницеДолженСтатьРублей(int cardForTransfer, int expectedBalance) {
        var actualBalance = dashboardPage.getCardBalance(DataHelper.getCardInfoByNumberOnPage(cardForTransfer));
        assertEquals(expectedBalance, actualBalance);
    }
}
