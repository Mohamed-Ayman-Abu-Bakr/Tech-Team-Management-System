package Exceptions;

import Classes.Popup_Window;


public class InvalidNameException extends Exception{
    public InvalidNameException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
