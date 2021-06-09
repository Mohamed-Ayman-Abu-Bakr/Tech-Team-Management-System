package Exceptions;

import Classes.Popup_Window;


public class InvalidEmailException extends Exception{

    public InvalidEmailException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
