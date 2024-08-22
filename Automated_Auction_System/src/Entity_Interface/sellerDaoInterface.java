package Entity_Interface;

import java.sql.SQLException;

import Entity.seller;

public interface sellerDaoInterface extends soldItemDaoInteface, itemDaoInterface{
	void addSeller(seller seller) throws SQLException;
}
