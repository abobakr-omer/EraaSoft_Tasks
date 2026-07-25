package com.item.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import com.item.service.ItemService;
import com.item.service.impl.ItemDetailsServiceImpl;
import com.item.service.impl.ItemServiceImpl;

@WebServlet("/ItemController")
public class ItemController extends HttpServlet {

	@Resource(name="jdbc/demo")
	private DataSource dataSource;

	private ItemService itemService;
	private ItemDetailsService itemDetailsService;

	// Creates item services when the servlet starts.
	@Override
	public void init() throws ServletException {
		itemService=new ItemServiceImpl(dataSource);
		itemDetailsService=new ItemDetailsServiceImpl(dataSource);
	}

	// Validates the extra details form before add or update.
	private String validateItemDetailsInput(HttpServletRequest request) {

		String itemId=request.getParameter("itemId");
		String description=request.getParameter("description");
		String category=request.getParameter("category");
		String manufacturer=request.getParameter("manufacturer");
		String warrantyMonths=request.getParameter("warrantyMonths");

		if(itemId==null || itemId.trim().isEmpty()) {
			return "Item ID is required.";
		}

		try {
			Long parsedItemId=Long.parseLong(itemId);

			if(parsedItemId<=0) {
				return "Invalid item ID.";
			}

		} catch(NumberFormatException e) {
			return "Invalid item ID.";
		}

		if(description!=null && description.trim().length()>500) {
			return "Description must not exceed 500 characters.";
		}

		if(category!=null && category.trim().length()>100) {
			return "Category must not exceed 100 characters.";
		}

		if(manufacturer!=null && manufacturer.trim().length()>100) {
			return "Manufacturer must not exceed 100 characters.";
		}

		if(warrantyMonths==null || warrantyMonths.trim().isEmpty()) {
			return "Warranty months is required.";
		}

		try {
			int parsedWarrantyMonths=Integer.parseInt(warrantyMonths);

			if(parsedWarrantyMonths<0) {
				return "Warranty months must be greater than or equal to 0.";
			}

		} catch(NumberFormatException e) {
			return "Warranty months must be a valid whole number.";
		}

		return null;
	}

	// Validates item form data before creating or updating an item.
	private String validateItemInput(HttpServletRequest request,boolean validateId) {

		String itemName=request.getParameter("itemName");
		String itemPrice=request.getParameter("itemPrice");
		String itemTotalNumber=request.getParameter("itemTotalNumber");

		if(validateId) {
			String itemId=request.getParameter("itemId");

			if(itemId==null || itemId.trim().isEmpty()) {
				return "Item ID is required.";
			}

			try {
				long id=Long.parseLong(itemId);

				if(id<=0) {
					return "Invalid item ID.";
				}

			} catch(NumberFormatException e) {
				return "Invalid item ID.";
			}
		}

		if(itemName==null || itemName.trim().isEmpty()) {
			return "Item name is required.";
		}

		itemName=itemName.trim();

		if(itemName.length()<2 || itemName.length()>100) {
			return "Item name must be between 2 and 100 characters.";
		}

		if(itemPrice==null || itemPrice.trim().isEmpty()) {
			return "Item price is required.";
		}

		try {
			double price=Double.parseDouble(itemPrice);

			if(!Double.isFinite(price) || price<=0) {
				return "Item price must be greater than 0.";
			}

		} catch(NumberFormatException e) {
			return "Item price must be a valid number.";
		}

		if(itemTotalNumber==null || itemTotalNumber.trim().isEmpty()) {
			return "Total number is required.";
		}

		try {
			int totalNumber=Integer.parseInt(itemTotalNumber);

			if(totalNumber<0) {
				return "Total number must be greater than or equal to 0.";
			}

		} catch(NumberFormatException e) {
			return "Total number must be a valid whole number.";
		}

		return null;
	}

	// Sends the user to the generic error page with a clear message.
	private void redirectToErrorPage(HttpServletRequest request,HttpServletResponse response,String errorMessage) throws IOException {
		request.getSession().setAttribute("errorMessage",errorMessage);
		response.sendRedirect(request.getContextPath()+"/error.jsp");
	}

	// Removes null values and extra spaces from text input.
	private String cleanText(String value) {

		if(value==null) {
			return "";
		}

		return value.trim();
	}

	// Builds temporary item details safely after validation fails.
	private ItemDetails buildItemDetailsFromRequestSafely(HttpServletRequest request) {

		Long itemId=getLongParameterSafely(request,"itemId");
		String description=cleanText(request.getParameter("description"));
		String category=cleanText(request.getParameter("category"));
		String manufacturer=cleanText(request.getParameter("manufacturer"));
		int warrantyMonths=getIntParameterSafely(request,"warrantyMonths");

		return new ItemDetails(itemId,description,category,manufacturer,warrantyMonths);
	}

	// Reads a long parameter safely and returns 0 if invalid.
	private Long getLongParameterSafely(HttpServletRequest request,String parameterName) {

		try {
			return Long.parseLong(request.getParameter(parameterName));
		} catch(Exception e) {
			return 0L;
		}
	}

	// Reads an integer parameter safely and returns 0 if invalid.
	private int getIntParameterSafely(HttpServletRequest request,String parameterName) {

		try {
			return Integer.parseInt(request.getParameter(parameterName));
		} catch(Exception e) {
			return 0;
		}
	}

	// Builds item details after validation passes.
	private ItemDetails buildItemDetailsFromRequest(HttpServletRequest request) {

		Long itemId=Long.parseLong(request.getParameter("itemId"));
		String description=cleanText(request.getParameter("description"));
		String category=cleanText(request.getParameter("category"));
		String manufacturer=cleanText(request.getParameter("manufacturer"));
		int warrantyMonths=Integer.parseInt(request.getParameter("warrantyMonths"));

		return new ItemDetails(itemId,description,category,manufacturer,warrantyMonths);
	}

	// Handles all item actions based on the action request parameter.
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		try {

			String action=request.getParameter("action");

			if(action==null) {
				action="showItems";
			}

			switch(action) {

			case "showItems":
				showItems(request,response);
				break;

			case "showItem":
				showItem(request,response);
				break;

			case "addItem":
				addItem(request,response);
				break;

			case "updateItem":
				updateItem(request,response);
				break;

			case "deleteItem":
				deleteItem(request,response);
				break;

			case "showItemDetails":
				showItemDetails(request,response);
				break;

			case "addItemDetails":
				addItemDetails(request,response);
				break;

			case "updateItemDetails":
				updateItemDetails(request,response);
				break;

			case "deleteItemDetails":
				deleteItemDetails(request,response);
				break;

			default:
				showItems(request,response);
				break;
			}

		} catch(Exception e) {
			e.printStackTrace();
			redirectToErrorPage(request,response,"Unable to complete the item operation. Please try again.");
		}
	}

	// Handles form submissions by using the same action logic as doGet.
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	// Deletes existing item details and returns the user to the items list.
	private void deleteItemDetails(HttpServletRequest request,HttpServletResponse response) throws IOException {

		Long itemId=getLongParameterSafely(request,"itemId");

		if(itemId<=0) {
			redirectToErrorPage(request,response,"Invalid item ID.");
			return;
		}

		Item item=itemService.getItemById(itemId);

		if(item==null) {
			redirectToErrorPage(request,response,"The selected item was not found.");
			return;
		}

		ItemDetails itemDetails=itemDetailsService.getItemDetailsByItemId(itemId);

		if(itemDetails==null) {
			redirectToErrorPage(request,response,"No details found for this item to delete.");
			return;
		}

		boolean isDeleted=itemDetailsService.deleteItemDetailsByItemId(itemId);

		if(isDeleted) {
			response.sendRedirect(request.getContextPath()+"/ItemController?action=showItems");
			return;
		}

		redirectToErrorPage(request,response,"Item details could not be deleted. Please try again.");
	}

	// Updates item details after checking that the item and details already exist.
	private void updateItemDetails(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String validationError=validateItemDetailsInput(request);
		Long itemId=getLongParameterSafely(request,"itemId");

		Item item=itemService.getItemById(itemId);

		if(item==null) {
			redirectToErrorPage(request,response,"The selected item was not found.");
			return;
		}

		ItemDetails existingItemDetails=itemDetailsService.getItemDetailsByItemId(itemId);

		if(existingItemDetails==null) {
			redirectToErrorPage(request,response,"No details found for this item to update.");
			return;
		}

		if(validationError!=null) {
			ItemDetails itemDetails=buildItemDetailsFromRequestSafely(request);
			itemDetails.setId(existingItemDetails.getId());
			request.setAttribute("validationError",validationError);
			request.setAttribute("item",item);
			request.setAttribute("itemDetails",itemDetails);
			request.getRequestDispatcher("/itemDetails.jsp").forward(request,response);
			return;
		}

		ItemDetails itemDetails=buildItemDetailsFromRequest(request);
		boolean isUpdated=itemDetailsService.updateItemDetails(itemDetails);

		if(isUpdated) {
			response.sendRedirect(request.getContextPath()+"/ItemController?action=showItems");
			return;
		}

		redirectToErrorPage(request,response,"Item details could not be updated. Please try again.");
	}

	// Adds item details only when the item does not already have details.
	private void addItemDetails(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String validationError=validateItemDetailsInput(request);
		Long itemId=getLongParameterSafely(request,"itemId");

		if(validationError!=null) {
			Item item=itemService.getItemById(itemId);
			ItemDetails itemDetails=buildItemDetailsFromRequestSafely(request);
			request.setAttribute("validationError",validationError);
			request.setAttribute("item",item);
			request.setAttribute("itemDetails",itemDetails);
			request.getRequestDispatcher("/itemDetails.jsp").forward(request,response);
			return;
		}

		Item item=itemService.getItemById(itemId);

		if(item==null) {
			redirectToErrorPage(request,response,"The selected item was not found.");
			return;
		}

		ItemDetails existingItemDetails=itemDetailsService.getItemDetailsByItemId(itemId);

		if(existingItemDetails!=null) {
			redirectToErrorPage(request,response,"This item already has details.");
			return;
		}

		ItemDetails itemDetails=buildItemDetailsFromRequest(request);
		boolean isAdded=itemDetailsService.addItemDetails(itemDetails);

		if(isAdded) {
			response.sendRedirect(request.getContextPath()+"/ItemController?action=showItemDetails&ID="+itemId);
			return;
		}

		redirectToErrorPage(request,response,"Item details could not be added. Please try again.");
	}

	// Opens the update item page with the selected item data.
	private void showItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

		long id=Long.parseLong(request.getParameter("ID"));
		Item item=itemService.getItemById(id);

		if(item!=null) {
			request.setAttribute("item",item);
			request.getRequestDispatcher("/updateItem.jsp").forward(request,response);
			return;
		}

		redirectToErrorPage(request,response,"The selected item was not found.");
	}

	// Opens the item details page with main item data and extra details if they exist.
	private void showItemDetails(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

		Long itemId=Long.parseLong(request.getParameter("ID"));
		Item item=itemService.getItemById(itemId);

		if(item==null) {
			redirectToErrorPage(request,response,"The selected item was not found.");
			return;
		}

		ItemDetails itemDetails=itemDetailsService.getItemDetailsByItemId(itemId);
		request.setAttribute("item",item);
		request.setAttribute("itemDetails",itemDetails);
		request.getRequestDispatcher("/itemDetails.jsp").forward(request,response);
	}

	// Redirects to the items list after successful POST operations.
	private void redirectToItems(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath()+"/ItemController?action=showItems");
	}

	// Builds an item object from a valid add item request.
	private Item buildItemFromRequest(HttpServletRequest request) {

		String name=request.getParameter("itemName").trim();
		double price=Double.parseDouble(request.getParameter("itemPrice"));
		int totalNumber=Integer.parseInt(request.getParameter("itemTotalNumber"));

		return new Item(name,price,totalNumber);
	}

	// Builds an item object from a valid update item request.
	private Item buildItemFromRequestWithID(HttpServletRequest request) {

		Long id=Long.parseLong(request.getParameter("itemId"));
		String name=request.getParameter("itemName").trim();
		double price=Double.parseDouble(request.getParameter("itemPrice"));
		int totalNumber=Integer.parseInt(request.getParameter("itemTotalNumber"));

		return new Item(id,name,price,totalNumber);
	}

	// Loads all items and forwards them to showItems.jsp.
	private void showItems(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		List<Item> items=itemService.getItems();
		request.setAttribute("itemsData",items);
		request.getRequestDispatcher("/showItems.jsp").forward(request,response);
	}

	// Builds a temporary item safely after validation fails.
	private Item buildItemFromRequestWithSafeValues(HttpServletRequest request) {

		long id=0;
		double price=0;
		int totalNumber=0;

		String itemId=request.getParameter("itemId");
		String name=request.getParameter("itemName");
		String itemPrice=request.getParameter("itemPrice");
		String itemTotalNumber=request.getParameter("itemTotalNumber");

		try {
			id=Long.parseLong(itemId);
		} catch(Exception e) {
			id=0;
		}

		try {
			price=Double.parseDouble(itemPrice);
		} catch(Exception e) {
			price=0;
		}

		try {
			totalNumber=Integer.parseInt(itemTotalNumber);
		} catch(Exception e) {
			totalNumber=0;
		}

		return new Item(id,name,price,totalNumber);
	}

	// Deletes item details first, then deletes the main item.
	private void deleteItem(HttpServletRequest request,HttpServletResponse response) throws IOException {

		Long id=Long.parseLong(request.getParameter("ID"));
		ItemDetails itemDetails=itemDetailsService.getItemDetailsByItemId(id);

		if(itemDetails!=null) {
			boolean isItemDetailsDeleted=itemDetailsService.deleteItemDetailsByItemId(id);

			if(!isItemDetailsDeleted) {
				redirectToErrorPage(request,response,"Item details could not be deleted. Please try again.");
				return;
			}
		}

		boolean isItemDeleted=itemService.removeItemById(id);

		if(isItemDeleted) {
			response.sendRedirect(request.getContextPath()+"/ItemController?action=showItems");
			return;
		}

		redirectToErrorPage(request,response,"Item could not be deleted. Please try again.");
	}

	// Updates an item after backend validation passes.
	private void updateItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

		String validationError=validateItemInput(request,true);

		if(validationError!=null) {
			Item item=buildItemFromRequestWithSafeValues(request);
			request.setAttribute("validationError",validationError);
			request.setAttribute("item",item);
			request.getRequestDispatcher("/updateItem.jsp").forward(request,response);
			return;
		}

		Item item=buildItemFromRequestWithID(request);
		boolean isRowUpdated=itemService.updateItem(item);

		if(isRowUpdated) {
			redirectToItems(request,response);
			return;
		}

		redirectToErrorPage(request,response,"Item could not be updated. Please try again.");
	}

	// Adds a new item after backend validation passes.
	private void addItem(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {

		String validationError=validateItemInput(request,false);

		if(validationError!=null) {
			request.setAttribute("validationError",validationError);
			request.getRequestDispatcher("/add-item.jsp").forward(request,response);
			return;
		}

		Item item=buildItemFromRequest(request);
		boolean isRowInserted=itemService.addItem(item);

		if(isRowInserted) {
			redirectToItems(request,response);
			return;
		}

		redirectToErrorPage(request,response,"Item could not be added. Please try again.");
	}
}
