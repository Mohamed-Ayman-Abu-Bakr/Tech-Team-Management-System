package Exceptions;

import Classes.Popup_Window;


public class InvalidDateException extends Exception{

    public InvalidDateException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
