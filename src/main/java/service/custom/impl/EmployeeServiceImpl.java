package service.custom.impl;

import db.DBConnection;
import model.dto.EmployeeDTO;
import model.entity.EmployeeEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDAO;
import service.custom.EmployeeService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.EMPLOYEE);

    @Override
    public EmployeeDTO loginUser(String id, String password) throws SQLException {

        EmployeeEntity employeeEntity = employeeDAO.searchByUserName(id);
        if (employeeEntity == null){
            return null;
        }
        if (employeeEntity.getPassword().equals(password)){
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
    public EmployeeEntity searchByUserName(String username) throws SQLException {
        return employeeDAO.searchByUserName(username);
    }

    @Override
    public Boolean creatAdmin() {
        EmployeeEntity employeeEntity = new EmployeeEntity(1, "admin", "admin@gmail.com", "a", "super-admin");
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
}
