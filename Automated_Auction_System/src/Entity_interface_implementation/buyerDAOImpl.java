package Entity_interface_implementation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Connection.singleton;
import Entity.buyer;
import Entity.item;
import Entity_Interface.buyerDao;


public class buyerDAOImpl implements buyerDao {
	
	 Connection conn = null;
     PreparedStatement stmt = null;
     ResultSet rs = null;
	@Override
    public void createBuyer(buyer buyer) throws SQLException {

        try {
            conn =singleton.getsingleton().getConnection();
            String sql = "INSERT INTO buyer (b_id, b_name, b_email, b_phone, b_address) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, buyer.getBuyerId());
            stmt.setString(2, buyer.getName());
            stmt.setString(3, buyer.getEmail());
            stmt.setString(4, buyer.getPhone());
            stmt.setString(5, buyer.getAddress());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public buyer getBuyerById(int buyerId) throws SQLException {
       

        try {
            conn = singleton.getsingleton().getConnection();
            String sql = "SELECT * FROM buyer WHERE b_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, buyerId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new buyer(
                    rs.getInt("b_id"),
                    rs.getString("b_name"),
                    rs.getString("b_email"),
                    rs.getString("b_phone"),
                    rs.getString("b_address")
                );
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }

    @Override
    public List<buyer> getAllBuyers() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<buyer> buyers = new ArrayList<>();

        try {
            conn = singleton.getsingleton().getConnection();
            String sql = "SELECT * FROM buyer";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                buyers.add(new buyer(
                    rs.getInt("b_id"),
                    rs.getString("b_name"),
                    rs.getString("b_email"),
                    rs.getString("b_phone"),
                    rs.getString("b_address")
                ));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return buyers;
	}

    public List<item> searchItemsByCategory(String category) throws SQLException{
        List<item> items = new ArrayList<>();
        try {
            conn = singleton.getsingleton().getConnection(); 
            String sql = "SELECT * FROM items WHERE category = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(new item(
                    rs.getInt("item_id"),
                    rs.getInt("seller_id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                ));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return items;
    }
    public List<item> selectItemsToBuy(List<Integer> itemIds) throws SQLException{
		return null;
    	
    }

}
