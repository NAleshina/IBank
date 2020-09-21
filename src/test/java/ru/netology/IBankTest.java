package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IBankTest {

    @Test
    void shouldValidTest() {
        PersonGenerator person = DataGenerator.setUpAll("en", true, true);
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(person.getLogin());
        $("[data-test-id=password] .input__control").setValue(person.getPassword());
        $("[data-test-id=action-login]").click();
        $(".heading").shouldHave(Condition.exactText("Личный кабинет"));
    }

    @Test
    void shouldInvalidLoginTest() {
        PersonGenerator person = DataGenerator.setUpAll("ru", true, true);
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(person.getLogin());
        $("[data-test-id=password] .input__control").setValue(person.getPassword());
        $("[data-test-id=action-login]").click();
        $(".heading").shouldHave(Condition.exactText("Личный кабинет"));
    }

    @Test
    void shouldInvalidPasswordTest() {
        PersonGenerator person = DataGenerator.setUpAll("en", false, true);
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(person.getLogin());
        $("[data-test-id=password] .input__control").setValue(person.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBlockedStatusTest() {
        PersonGenerator person = DataGenerator.setUpAll("en", true, false);
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue(person.getLogin());
        $("[data-test-id=password] .input__control").setValue(person.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.text("заблокирован"));
    }

    @Test
    void shouldNotExistPersonTest() {
        open("http://localhost:9999");
        $("[data-test-id=login] .input__control").setValue("vasya");
        $("[data-test-id=password] .input__control").setValue("password");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content").shouldHave(Condition.text("Ошибка!"));
    }
}
