package service.custom.impl;

import db.DBConnection;
import model.dto.CartItemDTO;
import model.dto.EmployeeDTO;
import model.entity.CartItemEntity;
import model.entity.EmployeeEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperRepository;
import repository.custom.CartItemDAO;
import service.custom.CartItemService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    @Override
    public Boolean add(CartItemDTO cartItemDTO) {
        CartItemDAO cartItemDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.CARTITEM);
        return cartItemDAO.add(new ModelMapper().map(cartItemDTO , CartItemEntity.class));
    }

    @Override
    public CartItemDTO searchById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Integer getLastID() {
        CartItemDAO cartItemDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.CARTITEM);
        return cartItemDAO.getLastID();
    }

    @Override
    public List<CartItemDTO> getAll() {
        CartItemDAO cartItemDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.CARTITEM);
        List<CartItemEntity> all = cartItemDAO.getAll();
        List<CartItemDTO> list = new ArrayList<>();
        all.forEach(cartItemEntity -> {
            list.add(new ModelMapper().map(cartItemEntity, CartItemDTO.class));
        });
        return  list;
    }



    @Override
    public void generateReport(Integer id) {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/bill-report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            HashMap<String, Object> param = new HashMap<>();
            param.put("OrderID",id);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint ,"bills/bill_report - "+id+".pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
