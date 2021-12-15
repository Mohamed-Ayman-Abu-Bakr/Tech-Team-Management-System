package Exceptions;

import Classes.Popup_Window;

public class TaskAlreadyVerifiedException extends Exception{

    public TaskAlreadyVerifiedException(String message) {
        super(message);
        Popup_Window.error(message);
    }
}
