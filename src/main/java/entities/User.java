package entities;

import lombok.*;
import utilis.*;

public class User {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Account account;

    public User() {
        setFirstName();
        setLastName();
        setAccount();
    }

    private void setFirstName() {
        this.firstName = UserDataHelper.getName(5);
    }

    private void setLastName() {
        this.lastName = UserDataHelper.getName(7);
    }

    private void setAccount() {
        this.account = new Account();
    }
}