package Exceptions;

import Classes.Popup_Window;


public class InvalidCostException extends Exception{

    public InvalidCostException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
