package util;

import model.entity.EmployeeEntity;

public class SessionManager {

    private static SessionManager instance;
    private EmployeeEntity currentUser;
    private SessionManager(){}
}
