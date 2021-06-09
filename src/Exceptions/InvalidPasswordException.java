package Exceptions;

import Classes.Popup_Window;


public class InvalidPasswordException extends Exception{

    public InvalidPasswordException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
