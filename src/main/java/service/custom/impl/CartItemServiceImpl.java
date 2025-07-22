package service.custom.impl;

import model.dto.CartItemDTO;
import model.dto.EmployeeDTO;
import model.entity.CartItemEntity;
import model.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperRepository;
import repository.custom.CartItemDAO;
import service.custom.CartItemService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
