Extract zip file

Needed Apps: Java JDK 16, Intellij IDE

After extracting the zip file then open Intellij and then select open project and then select the folder you extracted

You won't need to include any libraries

The Main class is located in the Classes Package

Before running the main class add the following command to the VM Options: 
--module-path "$PROJECT_DIR$\lib\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml

You can add the command to the VM Options by opening the run menu in intellij title bar --> select main class from the menu on the left --> click on Modify Options located
at the top right corner --> select add VM Options --> enter the command given above in the Text Box

Here are some users for login:

Employee user:
username: employee
password: employee

Manager user:
username: manager
password: manager
