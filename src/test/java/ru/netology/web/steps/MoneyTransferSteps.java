package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferSteps {
    private static DashboardPage dashboardPage;

    @Пусть("пользователь вошел на страницу {string} с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor());
    }

    @Когда("он переводит {string} с карты номер {string} на {string} карту на странице {string}")
    public void transfer(String amount, int fromCardNumber, int toCardNumber) {
        var transferPage = dashboardPage.selectCardToTransfer(Integer.parseInt(toCardNumber));
        dashboardPage = transferPage.Transfer(String.valueOf(amount), fromCardNumber);
    }

    @Тогда("баланс {string} карты на странице {string} должен стать {string}")
    public void verifyToCardBalance(String toCardNumber, String expectedToCardBalance) {
        var actualToCardBalance = dashboardPage.getCardBalance(Integer.parseInt(toCardNumber));
    }
}
