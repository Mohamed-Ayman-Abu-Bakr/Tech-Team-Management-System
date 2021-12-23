package Classes;

import Exceptions.*;



public class Data_Validation {
    public static void checkIfNotEmpty(String s) throws EmptyInputException{
        if(s.isEmpty()){
            throw new EmptyInputException("Please fill all empty Fields");
        }

    }

    public static void checkEmail(String email) throws InvalidEmailException {
        String regex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if(email.isEmpty()){
            throw new InvalidEmailException("Email field cannot be empty");
        }
        if(!email.matches(regex)){
            throw new InvalidEmailException("Invalid Email Address");
        }
    }
    public static void checkDate(String date) throws InvalidDateException {
        if(date.isEmpty()){
            throw new InvalidDateException("Date Field cannot be empty");
        }
    }
    public static void checkTime(String time) throws InvalidTimeException {
        String regex = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$";
        if(time.isEmpty()){
            throw new InvalidTimeException("Time Field cannot be empty");
        }
        if(!time.matches(regex)){
            throw new InvalidTimeException("Invalid time format. Time should be in the form of HH:MM");
        }
    }
    public static void checkNum(String num) throws InvalidNumberException {
        String regex = "^01[0-2,5][0-9]{8}$";
        if(!num.matches(regex)){
            throw new InvalidNumberException("Invalid Number");
        }
    }

    public static void checkCost(String cost) throws EmptyInputException, InvalidCostException {
        if(cost.isEmpty()){
            throw new EmptyInputException("Cost field cannot be empty");
        }
        try {
            if (Double.parseDouble(cost) < 0) {
                throw new InvalidCostException("Cost must be positive");
            }
        }catch (NumberFormatException e){
            throw new InvalidCostException("Please enter a valid number");
        }
    }
    public static void checkUsername(String username) throws EmptyInputException, InvalidUsernameException {
        if(username.isEmpty()){
            throw new EmptyInputException("Username cannot be empty");
        }
        if(username.length()<5){
            throw new InvalidUsernameException("The username should have at least 5 characters");
        }
    }
    public static void checkPassword(String password) throws EmptyInputException, InvalidPasswordException {
        if(password.isEmpty()){
            throw new EmptyInputException("Password cannot be empty");
        }
        if(password.length()<5){
            throw new InvalidPasswordException("The password should have at least 5 characters");
        }
    }
}
