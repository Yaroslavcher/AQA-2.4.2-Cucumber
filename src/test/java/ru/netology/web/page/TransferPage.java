package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

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

    public DashboardPage transfer(String amountTransfer, String fromCardNumber) {
        amountInput.setValue(amountTransfer);
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return new DashboardPage();
    }
}
