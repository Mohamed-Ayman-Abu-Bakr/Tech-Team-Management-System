package Exceptions;

import Classes.Popup_Window;


public class InvalidAddressException extends Exception{

    public InvalidAddressException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
