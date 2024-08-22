package Entity_Interface;

import java.sql.SQLException;
import java.util.List;

import Entity.dispute;

public interface disputeDaoInterface {
	 void createdispute(dispute dispute) throws SQLException;
	    dispute getDisputeById(int disputeId) throws SQLException;
	    List<dispute> getAllDisputes() throws SQLException;
	    void updateDispute(dispute dispute) throws SQLException;
	    void deleteDispute(int disputeId) throws SQLException;
}
