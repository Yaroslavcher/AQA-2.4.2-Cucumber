package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class MoneyTransferSteps {
    private static DashboardPage dashboardPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor(DataHelper.getAuthInfo()));
    }

    @Когда("он переводит {string} руб с карты номер {string} на {string} карту с главной страницы")
    public void transfer(String amount, String fromCardNumber, String toCardNumber) {
        var transferPage = dashboardPage.selectCardToTransfer(toCardNumber);
        dashboardPage = transferPage.transfer(String.valueOf(amount), fromCardNumber);
    }

    @Тогда("баланс {string} карты на главной странице должен стать {int} руб")
    public void verifyToCardBalance(String toCardNumber, int expectedToCardBalance) {
        var actualToCardBalance = dashboardPage.getCardBalance(toCardNumber);
        assertEquals(expectedToCardBalance, actualToCardBalance);
    }
}
