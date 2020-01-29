package com.system.utility;

import java.util.ResourceBundle;

public class Bundle {
    private static ResourceBundle resource = ResourceBundle.getBundle("view_en");

    public static ResourceBundle getResource() {
        return resource;
    }

    public static void switchLanguage(String language) {
        switch (language) {
            case "ru":
                resource = ResourceBundle.getBundle("view_ru", new ControlUTF8());
                break;
            case "en":
                resource = ResourceBundle.getBundle("view_en");
                break;
        }

    }
}
