package com.item.service;

import com.item.model.ItemDetails;

public interface ItemDetailsService {

	// Gets the details connected to one item.
	ItemDetails getItemDetailsByItemId(long itemId);

	// Adds details for one item.
	boolean addItemDetails(ItemDetails itemDetails);

	// Updates details for one item.
	boolean updateItemDetails(ItemDetails itemDetails);

	// Deletes details for one item.
	boolean deleteItemDetailsByItemId(long itemId);
}
