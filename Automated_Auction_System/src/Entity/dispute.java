package Entity;

public class dispute {
	 private int disputeId;
	 private int itemId; 
	 private int buyerId;
	public int getDisputeId() {
		return disputeId;
	}
	public void setDisputeId(int disputeId) {
		this.disputeId = disputeId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public dispute(int disputeId, int itemId, int buyerId) {
		super();
		this.disputeId = disputeId;
		this.itemId = itemId;
		this.buyerId = buyerId;
	}
	@Override
	public String toString() {
		return "dispute [disputeId=" + disputeId + ", itemId=" + itemId + ", buyerId=" + buyerId + "]";
	}
	 
	 
}
