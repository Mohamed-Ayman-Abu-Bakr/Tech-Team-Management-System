package Exceptions;

import Classes.Popup_Window;


public class InvalidTimeException extends Exception{

    public InvalidTimeException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
