package Entity_interface_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import Connection.singleton;
import Entity.item;
import Entity.itemSold;
import Entity.seller;
import Entity_Interface.sellerDaoInterface;


public class sellerDAOImpl implements sellerDaoInterface{

	
	Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
	@Override
	public void addSeller(seller seller) throws SQLException {
		 try {
	            conn =singleton.getsingleton().getConnection();
	            String sql = "INSERT INTO buyer (b_id, b_name, b_email, b_phone, b_address) VALUES (?, ?, ?, ?, ?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, seller.getSellerId());
	            stmt.setString(2, seller.getName());
	            stmt.setString(3, seller.getEmail());
	            stmt.setString(4, seller.getPhone());
	            stmt.setString(5, seller.getAddress());
	            stmt.executeUpdate();
	        } finally {
	            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
	            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }	
	        }
	}
	@Override
	public void addItem(item item) throws SQLException {
		// TODO Auto-generated method stub
		 try {
	            conn =singleton.getsingleton().getConnection();
	            String sql = "INSERT INTO item (item_id, seller_id, item_name, category, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, item.getItemId());
	            stmt.setInt(2, item.getSellerId());
	            stmt.setString(3, item.getItemName());
	            stmt.setString(4, item.getCategory());
	            stmt.setDouble(5, item.getPrice());
	            stmt.setInt(6, item.getQuantity());
	            stmt.executeUpdate();
	            
	            
	        } finally {
	            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
	            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	}
	@Override
	public void updateItem(item item) throws SQLException {
		// TODO Auto-generated method stub
		 try {
		        conn = singleton.getsingleton().getConnection();
		        
		        String sql = "UPDATE items SET seller_id = ?, item_name = ?, category = ?, price = ?, quantity = ? WHERE item_id = ?";
		        stmt = conn.prepareStatement(sql);
		        
		        stmt.setInt(1, item.getSellerId());
		        stmt.setString(2, item.getItemName());
		        stmt.setString(3, item.getCategory());
		        stmt.setDouble(4, item.getPrice());
		        stmt.setInt(5, item.getQuantity());
		        stmt.setInt(6, item.getItemId());
		        
		        int rowsAffected = stmt.executeUpdate();
		        
		        if (rowsAffected == 0) {
		            System.out.println("No item found with ID: " + item.getItemId());
		        }
		        else {
		        	System.out.println("Item with "+item.getItemId()+" got successfully updated");
		        }
		        
		    } 
		 finally {
		        if (stmt != null) {
		             stmt.close(); 
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    }
	}
	@Override
	public void deleteItem(int itemId) throws SQLException {
		// TODO Auto-generated method stub
		try {
			 conn = singleton.getsingleton().getConnection();
		        
		        String sql = "DELETE from items  WHERE item_id = ?";
		        stmt.setInt(1, itemId);
		        stmt = conn.prepareStatement(sql);
		        int rowsAffected = stmt.executeUpdate();
		        if (rowsAffected == 0) {
		            System.out.println("No item found with ID: " + itemId);
		        }
		        else {
		        	System.out.println("Item with "+itemId+" got deleted successfully");
		        }
		}
		finally {
			if (stmt != null) {
	             stmt.close(); 
	        }
	        if (conn != null) {
	            conn.close();
	        }
		}
	}
	@Override
	public void addSoldItem(itemSold soldItem) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public itemSold getSoldItemById(int soldItemId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<itemSold> getSoldItemsBySellerId(int sellerId) throws SQLException {
		// TODO Auto-generated method stub
		 List<itemSold> soldItems = new ArrayList<>();
		 try {
			 conn = singleton.getsingleton().getConnection();
		        
			 String sql = "SELECT i.item_id, i.item_name, i.category, i.price, i.quantity, s.sold_i_id, s.buyer_id, s.sold_price " +
                     "FROM items i " +
                     "JOIN sold_items s ON i.item_id = s.item_id " +
                     "WHERE i.seller_id = ?";
		        
		        stmt = conn.prepareStatement(sql);
		        stmt.setInt(1, sellerId);

		        rs = stmt.executeQuery();
		        
		        while(rs.next()) {
		        	itemSold soldItem = new itemSold(
		        			rs.getInt("soldItemId"),
		                    rs.getInt("itemId"),
		                    rs.getInt("buyerId"),
		                    rs.getDouble("soldPrice"));
		        			soldItems.add(soldItem);
		        }
		        
		 }
		 finally {
			 if(rs!= null) {
		        	rs.close();
		        }
			 if (stmt != null) {
	             stmt.close(); 
	        }
	        if (conn != null) {
	            conn.close();
	        }
		 }
		return soldItems;
	}
	@Override
	public List<itemSold> getAllSoldItems() throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public item getItemById(int itemId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
