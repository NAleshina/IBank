package ru.netology;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Data
@RequiredArgsConstructor
public class PersonGenerator {
    private String login;
    private String password;
    private String status;

    public PersonGenerator(String locale, boolean passwordType, boolean statusType) {
        Faker faker = new Faker(new Locale(locale));
        login = faker.name().username();
        if (passwordType) {
            password = getValidPassword();
        } else {
            password = getInvalidPassword();
        }
        status = getStatus(statusType);
    }

    private String getValidPassword() {
        List<String> passwords = Arrays.asList("poM", "aRk", "Os", "js", "Mw", "oZ", "ra", "di");
        Random rndLetters = new Random();
        Random rndNumbers = new Random();
        return passwords.get(rndLetters.nextInt(passwords.size() - 1)) + rndNumbers.nextInt(1000000);
    }

    private String getInvalidPassword() {
        List<String> passwords = Arrays.asList("", " ");
        Random rndLetters = new Random();
        return passwords.get(rndLetters.nextInt(passwords.size() - 1));
    }

    private String getStatus(boolean statusType) {
        if (statusType) {
            return "active";
        } else {
            return "blocked";
        }
    }
}

