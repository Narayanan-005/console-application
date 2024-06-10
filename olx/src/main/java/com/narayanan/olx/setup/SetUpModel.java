package com.narayanan.olx.setup;

import com.narayanan.olx.datalayer.DataBase;
import com.narayanan.olx.features.FeaturesView;
import com.narayanan.olx.model.User;

class SetUpModel {
    private SetUpView setUpView;

    SetUpModel(SetUpView setUpView) {
        this.setUpView = setUpView;
    }

    public void validate(String name, String email, String phone, String aboutMe, String password) {
        if (DataBase.getInstance().userExist(email)) {
            setUpView.callBackSignUp("Email already Exist");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            setUpView.callBackSignUp("Invalid Phone Number");
            return;
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobileNo(phone);
        user.setAboutMe(aboutMe);
        DataBase.getInstance().addUser(user, password);
        setUpView.showMsg("User Created Successfully");
    }

    public void validateUser(String email, String password) {
        if (DataBase.getInstance().userExist(email)) {
            if (DataBase.getInstance().validateUser(email, password)) {
                new FeaturesView().init();
            } else {
                System.out.println("Invalid Password");
            }
        } else {
            setUpView.callBackSignIn("Email Not Found");
        }
    }
}
