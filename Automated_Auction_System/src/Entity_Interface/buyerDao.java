package Entity_Interface;

import java.sql.SQLException;
import java.util.List;

import Entity.item;
import Entity.buyer;

public interface buyerDao {
	void createBuyer(buyer buyer) throws SQLException;
    buyer getBuyerById(int buyerId) throws SQLException;
    List<buyer> getAllBuyers() throws SQLException;
    List<item> searchItemsByCategory(String category) throws SQLException;
    List<item> selectItemsToBuy(List<Integer> itemIds) throws SQLException;
    
}
