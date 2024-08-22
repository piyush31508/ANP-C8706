package Entity_Interface;

import java.sql.SQLException;
import java.util.List;

import Entity.item;

public interface itemDaoInterface {
	void addItem(item item) throws SQLException;
    void updateItem(item item) throws SQLException;
    void deleteItem(int itemId) throws SQLException;
    item getItemById(int itemId) throws SQLException;
//    List<SoldItem> getSoldItems() throws SQLException;
}
