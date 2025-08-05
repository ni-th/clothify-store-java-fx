package service.custom.impl;

import db.DBConnection;
import model.dto.EmployeeDTO;
import model.dto.ProductDTO;
import model.entity.EmployeeEntity;
import model.entity.ProductEntity;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDAO;
import service.ServiceFactory;
import service.custom.ProductService;
import util.RepositoryType;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDAO productDAO = DaoFactory.getInstance().getRepositoryType(RepositoryType.PRODUCT);

    @Override
    public Boolean add(ProductDTO productDTO) {
        return productDAO.add(new ModelMapper().map(productDTO, ProductEntity.class));
    }

    @Override
    public ProductDTO searchById(Integer id) throws SQLException {
        if (productDAO.searchById(id) == null){
            return null;
        }
        return new ModelMapper().map(productDAO.searchById(id), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> all = productDAO.getAll();
        List<ProductDTO> list = new ArrayList<>();
        all.forEach(productEntity -> {
            list.add(new ModelMapper().map(productEntity, ProductDTO.class));
        });
        return  list;
    }

    @Override
    public Boolean updateQty(Integer id, Integer qty) throws SQLException {
        ProductEntity productEntity = productDAO.searchById(id);
        Integer qtyInDB = productEntity.getQty();
        return productDAO.updateQty(id,qtyInDB-qty);
    }

    @Override
    public void generateReport() {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/product-report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            JasperExportManager.exportReportToPdfFile(jasperPrint ,"reports/product_report - "+date+".pdf");
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
