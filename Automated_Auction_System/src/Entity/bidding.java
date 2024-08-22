package Entity;

import java.sql.Timestamp;

public class bidding {
	private int bidId;
    private int itemId;
    private int buyerId;
    private double bidAmount;
    private Timestamp bidTime;
    
    
    
	public bidding(int bidId, int itemId, int buyerId, double bidAmount, Timestamp bidTime) {
		super();
		this.bidId = bidId;
		this.itemId = itemId;
		this.buyerId = buyerId;
		this.bidAmount = bidAmount;
		this.bidTime = bidTime;
	}
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
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
	public double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public Timestamp getBidTime() {
		return bidTime;
	}
	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}
	@Override
	public String toString() {
		return "bidding [bidId=" + bidId + ", itemId=" + itemId + ", buyerId=" + buyerId + ", bidAmount=" + bidAmount
				+ ", bidTime=" + bidTime + "]";
	}
    
}
