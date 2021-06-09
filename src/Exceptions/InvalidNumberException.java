package Exceptions;

import Classes.Popup_Window;


public class InvalidNumberException extends Exception{

    public InvalidNumberException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
