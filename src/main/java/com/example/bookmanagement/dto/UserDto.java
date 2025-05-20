package com.example.bookmanagement.dto;

public class UserDto {
    private String username;
    private String password;

    // Constructor không tham số
    public UserDto() {
    }

    // Constructor có tất cả tham số
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter và Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() nếu cần
    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
