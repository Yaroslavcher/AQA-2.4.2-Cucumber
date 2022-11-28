package ru.netology.web.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class MoneyTransferSteps {
    private static DashboardPage dashboardPage;
    private TransferPage transferPage;


    @Пусть("пользователь залогинен с именем {} и паролем {}")
    public void loginWithNameAndPassword(String login, String password) {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor(DataHelper.getAuthInfo()));
    }

    @Когда("он переводит {} руб с карты номер {} на {} карту с главной страницы")
    public void validTransfer(String amount, String fromCardNumber, int toCardNumber) {
        var transferPage = dashboardPage.selectCardToTransfer(toCardNumber);
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), fromCardNumber);
    }


    @Тогда("баланс {} карты на главной странице должен стать {} руб")
    public void verifyToCardBalance(int toCardNumber, int expectedToCardBalance) {
        var actualToCardBalance = dashboardPage.getCardBalance(toCardNumber);
        assertEquals(expectedToCardBalance, actualToCardBalance);
    }

    @Когда("он вводит {} руб c карты номер {} на {} карту с главной страницы")
    public void transfer(String amount, String fromCardNumber, int toCardNumber) {
        var transferPage = dashboardPage.selectCardToTransfer(toCardNumber);
        transferPage.transfer(String.valueOf(amount), fromCardNumber);
    }
    @Тогда("появляется ошибка")
    public void errorMessage() {
        TransferPage transferPage = new TransferPage();
        transferPage.seeErrorMessage("Выполнена попытка перевода суммы, превышающей баланс");
    }
}
