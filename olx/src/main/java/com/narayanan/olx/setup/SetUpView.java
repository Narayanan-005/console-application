package com.narayanan.olx.setup;

import com.narayanan.olx.Olx;
import com.narayanan.olx.model.User;

import java.util.Scanner;

public class SetUpView {
    private SetUpModel setUpModel;
    private Scanner scanner;

    public SetUpView() {
        this.setUpModel = new SetUpModel(this);
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        System.out.println("Welcome to " + Olx.APP_NAME + " Application \nVersion --" + Olx.APP_VERSION);
        setUp();
    }

    private void setUp() {
        System.out.println("\n1.Sign UP\n2.Sign In");
        int choice = scanner.nextInt();
        if (choice == 1) {
            signUp();
        } else if (choice == 2) {
            signIn();
        } else {
            System.out.println("Wrong Input");
            setUp();
        }
    }

    private void signUp() {
        System.out.print("Enter Name :");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Enter Email :");
        String email = scanner.next();
        System.out.print("Enter PhoneNumber :");
        String phone = scanner.next();
        System.out.print("Update About me :");
        String aboutMe = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Create Password :");
        String password = scanner.next();
        setUpModel.validate(name, email, phone, aboutMe, password);
    }

    public void callBackSignUp(String msg) {
        System.out.println(msg);
        System.out.println("Do you want to try again Type yes or no");
        if (scanner.next().equals("yes"))
            signUp();
        else
            setUp();
    }

    public void showMsg(String msg) {
        System.out.println("\n" + msg);
        setUp();
    }

    private void signIn() {
        System.out.print("Enter EmailId :");
        String email = scanner.next();
        System.out.print("Enter Password :");
        String password = scanner.next();
        setUpModel.validateUser(email, password);
    }

    public void callBackSignIn(String msg) {
        System.out.println(msg);
        System.out.println("Do you want to try again Type yes or no");
        if (scanner.next().equals("yes"))
            signIn();
        else
            setUp();
    }
}
