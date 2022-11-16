package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement transferHeader = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id='error-message']");

    public TransferPage() {
        transferHeader.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountTransfer, DataHelper.CardInfo cardInfo) {
        transfer(amountTransfer, String.valueOf(cardInfo));
        return new DashboardPage();
    }

    public DashboardPage transfer(String amountTransfer, String fromCardNumber) {
        amountInput.setValue(amountTransfer);
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return null;
    }

    public void seeErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
