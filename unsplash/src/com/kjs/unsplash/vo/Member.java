package com.kjs.unsplash.vo;

public class Member {
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private String password;
    private String regDate;

    public Member(String firstName, String lastName, String nickName, String email, String password, String regDate) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
    }

    public Member(String firstName, String lastName, String nickName, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final String getNickName() {
        return nickName;
    }

    public final void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final String getRegDate() {
        return regDate;
    }

    public final void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Member [firstName=" + firstName + ", lastName=" + lastName + ", nickName=" + nickName + ", email="
                + email + ", password=" + password + ", regDate=" + regDate + "]";
    }

}
