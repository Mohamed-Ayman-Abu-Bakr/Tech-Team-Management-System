package Exceptions;

import Classes.Popup_Window;


public class InvalidUsernameException extends Exception{

    public InvalidUsernameException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
