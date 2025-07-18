package service.custom.impl;

import model.dto.CartItemDTO;
import model.entity.CartItemEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperRepository;
import repository.custom.CartItemDAO;
import service.custom.CartItemService;
import util.RepositoryType;

import java.sql.SQLException;

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
}
