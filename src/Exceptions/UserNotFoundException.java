package Exceptions;

import Classes.Popup_Window;


public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
