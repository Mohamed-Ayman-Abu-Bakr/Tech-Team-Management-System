package Exceptions;

import Classes.Popup_Window;


public class EmptyInputException extends Exception{

    public EmptyInputException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
