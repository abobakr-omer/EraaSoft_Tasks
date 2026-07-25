package com.item.model;

public class ItemDetails {
	
	private long id;
    private long itemId;
    private String description;
    private String category;
    private String manufacturer;
    private int warrantyMonths;
    

    public ItemDetails() {
    }

    public ItemDetails(long id, long itemId, String description, String category, String manufacturer, int warrantyMonths) {
        this.id = id;
        this.itemId = itemId;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.warrantyMonths = warrantyMonths;
    }
    
    public ItemDetails( long itemId, String description, String category, String manufacturer, int warrantyMonths) {
        this.itemId = itemId;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.warrantyMonths = warrantyMonths;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", warrantyMonths=" + warrantyMonths +
                '}';
    }
	 
	 
	

}
