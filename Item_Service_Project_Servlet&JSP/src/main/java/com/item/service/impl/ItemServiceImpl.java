package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.ItemService;

public class ItemServiceImpl implements ItemService {

	private DataSource dataSource;

	// Receives the database connection pool from the controller.
	public ItemServiceImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	// Inserts a new item into the ITEM table.
	@Override
	public boolean addItem(Item item) {

		Connection connection=null;
		PreparedStatement statement=null;
		boolean isInserted=false;

		String query="INSERT INTO item(NAME, PRICE, TOTAL_NUMBER) VALUES (?,?,?)";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,item.getName());
			statement.setDouble(2,item.getPrice());
			statement.setInt(3,item.getTotalNumber());

			int rowsInserted=statement.executeUpdate();
			isInserted=rowsInserted>0;

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

		return isInserted;
	}

	// Updates an existing item by ID.
	@Override
	public boolean updateItem(Item item) {

		Connection connection=null;
		PreparedStatement statement=null;
		boolean isRowUpdated=false;

		String query="UPDATE item SET NAME=?, PRICE=?, TOTAL_NUMBER=? WHERE ID=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,item.getName());
			statement.setDouble(2,item.getPrice());
			statement.setInt(3,item.getTotalNumber());
			statement.setLong(4,item.getId());

			int rowUpdated=statement.executeUpdate();
			isRowUpdated=rowUpdated>0;

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

		return isRowUpdated;
	}

	// Finds one item by its ID.
	@Override
	public Item getItemById(Long id) {

		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		Item item=null;

		String query="SELECT * FROM item WHERE ID=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setLong(1,id);

			resultSet=statement.executeQuery();

			if(resultSet.next()) {
				long itemId=resultSet.getLong("ID");
				String name=resultSet.getString("NAME");
				double price=resultSet.getDouble("PRICE");
				int totalNumber=resultSet.getInt("TOTAL_NUMBER");

				item=new Item(itemId,name,price,totalNumber);
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

		return item;
	}

	// Deletes an item by ID.
	@Override
	public boolean removeItemById(Long id) {

		Connection connection=null;
		PreparedStatement statement=null;
		boolean isDeleted=false;

		String query="DELETE FROM item WHERE ID=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setLong(1,id);

			int rowsDeleted=statement.executeUpdate();
			isDeleted=rowsDeleted>0;

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

		return isDeleted;
	}

	// Loads all items ordered by ID for the dashboard table.
	@Override
	public List<Item> getItems() {

		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		List<Item> items=new ArrayList<>();

		String query="SELECT * FROM item ORDER BY ID ASC";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);
			resultSet=statement.executeQuery();

			while(resultSet.next()) {
				Item item=new Item(
						resultSet.getLong("ID"),
						resultSet.getString("NAME"),
						resultSet.getDouble("PRICE"),
						resultSet.getInt("TOTAL_NUMBER")
				);

				items.add(item);
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

		return items;
	}
}
