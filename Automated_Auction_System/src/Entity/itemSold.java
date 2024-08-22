package Entity;

public class itemSold {
	private int soldItemId;
    private int itemId;
    private int buyerId;
    private double soldPrice;
	public itemSold(int soldItemId, int itemId, int buyerId, double soldPrice) {
		super();
		this.soldItemId = soldItemId;
		this.itemId = itemId;
		this.buyerId = buyerId;
		this.soldPrice = soldPrice;
	}
	public int getSoldItemId() {
		return soldItemId;
	}
	public void setSoldItemId(int soldItemId) {
		this.soldItemId = soldItemId;
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
	public double getSoldPrice() {
		return soldPrice;
	}
	public void setSoldPrice(double soldPrice) {
		this.soldPrice = soldPrice;
	}
	@Override
	public String toString() {
		return "itemSold [soldItemId=" + soldItemId + ", itemId=" + itemId + ", buyerId=" + buyerId + ", soldPrice="
				+ soldPrice + "]";
	}
    
    
}
