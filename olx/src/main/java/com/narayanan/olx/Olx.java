package com.narayanan.olx;

import com.narayanan.olx.setup.SetUpView;

public class Olx {
    public static final String APP_NAME = "OLX";
    public static final String APP_VERSION = "0.0.1";

    public static void main(String[] args) {
        new Olx().init();
    }

    private void init() {
        new SetUpView().init();
    }
}
