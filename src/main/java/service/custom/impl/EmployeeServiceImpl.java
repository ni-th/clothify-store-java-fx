package service.custom.impl;

import db.DBConnection;
import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.validator.routines.EmailValidator;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import service.custom.EmployeeService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);
    BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

    @Override
    public EmployeeDTO loginUser(String id, String password) throws SQLException {

        EmployeeEntity employeeEntity = employeeDAO.searchByUserName(id);
        if (employeeEntity == null){
            return null;
        }
        if (Boolean.TRUE.equals(checkPassword(password, employeeEntity.getPassword()))){
            return new ModelMapper().map(employeeEntity, EmployeeDTO.class);
        }
        return null;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<EmployeeEntity> all = employeeDAO.getAll();
        List<EmployeeDTO> list = new ArrayList<>();
        all.forEach(employeeEntity -> {
            list.add(new ModelMapper().map(employeeEntity, EmployeeDTO.class));
        });
        return  list;
    }

    @Override
    public Boolean add(EmployeeDTO employee) {
        EmployeeEntity mapped = new ModelMapper().map(employee, EmployeeEntity.class);
        return employeeDAO.add(mapped);
    }

    @Override
    public Boolean update(EmployeeDTO employeeDTO) {
        EmployeeEntity mapped = new ModelMapper().map(employeeDTO, EmployeeEntity.class);
        return employeeDAO.update(mapped);
    }

    @Override
    public EmployeeDTO searchByUserName(String username) throws SQLException {
        return new ModelMapper().map(employeeDAO.searchByUserName(username), EmployeeDTO.class);
    }

    @Override
    public Boolean creatAdmin() {
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "admin", "admin@gmail.com", passwordEncrypter("Admin@123"), "super-admin");
        try {
            return employeeDAO.creatAdmin(employeeEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateReport() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/employee-report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            JasperExportManager.exportReportToPdfFile(jasperPrint ,"reports/employee_report - "+date+".pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer passwordValidator(String password) {
        if (password.length() < 8) return 1;//if length < 8
        if (!password.matches(".*[A-Z].*")) return 2;// if not contains A-Z
        if (!password.matches(".*[a-z].*")) return 3;// if not contains a-z
        if (!password.matches(".*\\d.*")) return 4;// if not contains number
        if (!password.matches(".*[@#$%^&+=!*/?()_\\-].*")) return 5;// if not contains symbol
        return 0;// if password is ok

    }

    @Override
    public String passwordEncrypter(String password) {
        return encryptor.encryptPassword(password);
    }

    @Override
    public Boolean checkPassword(String password,String hash) {
        return encryptor.checkPassword(password, hash);
    }

    @Override
    public Boolean emailValidator(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    @Override
    public Boolean deleteByID(Integer id) {
        try {
            return employeeDAO.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
