package Entity_Interface;

import Entity.itemSold;
import java.sql.SQLException;
import java.util.List;

public interface soldItemDaoInteface {
	  void addSoldItem(itemSold soldItem) throws SQLException;
	  itemSold getSoldItemById(int soldItemId) throws SQLException;
	    List<itemSold> getSoldItemsBySellerId(int sellerId) throws SQLException;
	    List<itemSold> getAllSoldItems() throws SQLException;
	    
}

