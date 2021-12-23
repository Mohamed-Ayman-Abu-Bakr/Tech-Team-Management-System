package Exceptions;

import Classes.Popup_Window;


public class UserNotFoundException extends Exception{

    public UserNotFoundException() {
        super("User not found \nInvalid email or password");
        Popup_Window.error("User not found \nInvalid email or password");
    }
}
