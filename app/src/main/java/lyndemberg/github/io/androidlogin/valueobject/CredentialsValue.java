package lyndemberg.github.io.androidlogin.valueobject;

import java.io.Serializable;

public class CredentialsValue implements Serializable {
    private String email;
    private String password;

    public CredentialsValue(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CredentialsValue() {
    }

    @Override
    public String toString() {
        return "CredentialsValue{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
