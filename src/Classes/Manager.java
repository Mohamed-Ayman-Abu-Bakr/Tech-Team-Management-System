package Classes;

public class Manager extends Employee{

    public Manager(int id, String name, String username, String password, String position,
                   String email, String birthdate, String phone, int completed_tasks) {
        super(id, name, username, password, position, email, birthdate, phone, completed_tasks);
    }


    public void invokeAddTask(){

    }
    public void invokeAddProject(Project p){
        p.AddProject();

    }

}
