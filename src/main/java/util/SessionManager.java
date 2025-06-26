package util;

import model.dto.EmployeeDTO;

public class SessionManager {

    private static SessionManager instance;
    private static EmployeeDTO currentUser;
    private SessionManager(){

    }

    public static SessionManager getInstance(){
        if (instance!=null){
            return instance;
        }
        return instance = new SessionManager();
    }

    public void setUser(EmployeeDTO currentUser){
        this.currentUser = currentUser;
    }
    public EmployeeDTO getUser(){
        return currentUser;
    }
    public void logout(){
        currentUser=null;
    }
}
