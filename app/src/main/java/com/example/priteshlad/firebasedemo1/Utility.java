package com.example.priteshlad.firebasedemo1;

public class Utility {
    static boolean checkEmail(String email) {
        String msg;

        String emailReg = "^([a-z\\d.-_]+)@([a-z\\d-]+)\\.([a-z]{2,8})(\\.[a-z]{2,8})?$";

        if (email.matches(emailReg)) {
            msg = "Email Ok";
            System.out.println(msg);
            return true;
        } else {
            msg = "Please enter valid email";
            System.out.println(msg);
            return false;
        }
    }

    static boolean checkPassword(String password) {
        String msg;

        String passReg = "^([a-zA-Z0-9]+)$";

        if (!(password.length() < 8 || password.length() > 15)) {
            if (password.matches(passReg)) {
                msg = "Password Ok";
                System.out.println(msg);
                return true;
            } else {
                msg = "Password format error";
                System.out.println(msg);
                return false;
            }
        } else {
            msg = "Password should be 8-15 long";
            System.out.println(msg);
            return false;
        }
    }
}
