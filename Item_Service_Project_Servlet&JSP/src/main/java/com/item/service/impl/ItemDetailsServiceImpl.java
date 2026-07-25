package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;

public class ItemDetailsServiceImpl implements ItemDetailsService {

	private DataSource dataSource;

	// Receives the database connection pool from the controller.
	public ItemDetailsServiceImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	// Finds the details row connected to one item.
	@Override
	public ItemDetails getItemDetailsByItemId(long itemId) {

		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;

		String query="SELECT ID, ITEM_ID, DESCRIPTION, CATEGORY, MANUFACTURER, WARRANTY_MONTHS FROM ITEM_DETAILS WHERE ITEM_ID=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setLong(1,itemId);

			resultSet=statement.executeQuery();

			if(resultSet.next()) {
				Long id=resultSet.getLong("ID");
				Long itemIdFromDB=resultSet.getLong("ITEM_ID");
				String description=resultSet.getString("DESCRIPTION");
				String category=resultSet.getString("CATEGORY");
				String manufacturer=resultSet.getString("MANUFACTURER");
				int warrantyMonths=resultSet.getInt("WARRANTY_MONTHS");

				return new ItemDetails(id,itemIdFromDB,description,category,manufacturer,warrantyMonths);
			}

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(resultSet!=null) {
					resultSet.close();
				}

				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return null;
	}

	// Inserts a new details row for an item.
	@Override
	public boolean addItemDetails(ItemDetails itemDetails) {

		Connection connection=null;
		PreparedStatement statement=null;

		String query="INSERT INTO ITEM_DETAILS(ITEM_ID, DESCRIPTION, CATEGORY, MANUFACTURER, WARRANTY_MONTHS) VALUES(?,?,?,?,?)";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setLong(1,itemDetails.getItemId());
			statement.setString(2,itemDetails.getDescription());
			statement.setString(3,itemDetails.getCategory());
			statement.setString(4,itemDetails.getManufacturer());
			statement.setInt(5,itemDetails.getWarrantyMonths());

			int rowsAffected=statement.executeUpdate();
			return rowsAffected>0;

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return false;
	}

	// Updates the details row connected to an item.
	@Override
	public boolean updateItemDetails(ItemDetails itemDetails) {

		Connection connection=null;
		PreparedStatement statement=null;

		String query="UPDATE ITEM_DETAILS SET DESCRIPTION=?, CATEGORY=?, MANUFACTURER=?, WARRANTY_MONTHS=? WHERE ITEM_ID=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,itemDetails.getDescription());
			statement.setString(2,itemDetails.getCategory());
			statement.setString(3,itemDetails.getManufacturer());
			statement.setInt(4,itemDetails.getWarrantyMonths());
			statement.setLong(5,itemDetails.getItemId());

			int rowsAffected=statement.executeUpdate();
			return rowsAffected>0;

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return false;
	}

	// Deletes the details row connected to an item.
	@Override
	public boolean deleteItemDetailsByItemId(long itemId) {

		Connection connection=null;
		PreparedStatement statement=null;

		String query="DELETE FROM ITEM_DETAILS WHERE ITEM_ID=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setLong(1,itemId);

			int rowsAffected=statement.executeUpdate();
			return rowsAffected>0;

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return false;
	}
}
