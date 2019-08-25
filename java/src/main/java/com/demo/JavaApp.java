package com.demo;

public class JavaApp {
    public static void main(String[] args) {
        JavaApp app = new JavaApp();
        System.out.println(app.getGreeting());
    }

    public String getGreeting() {
        return "Hello from Java!";
    }
}