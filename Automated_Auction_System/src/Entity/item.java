package Entity;

public class item {
	private int itemId;
    private int sellerId;
    private String itemName;
    private String category;
    private double price;
    private int quantity;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public item(int itemId, int sellerId, String itemName, String category, double price, int quantity) {
		super();
		this.itemId = itemId;
		this.sellerId = sellerId;
		this.itemName = itemName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "item [itemId=" + itemId + ", sellerId=" + sellerId + ", itemName=" + itemName + ", category=" + category
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}
    
    
}
