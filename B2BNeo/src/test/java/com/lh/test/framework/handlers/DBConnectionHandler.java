package com.lh.test.framework.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lh.test.framework.configuration.Configuration;

import oracle.jdbc.pool.OracleDataSource;

public class DBConnectionHandler {
	public Logger Log = Logger.getLogger(DBConnectionHandler.class.getName());
	static int i=1;
	
	
	public Integer getDBRecordCount(String Query, Map<Integer, String> queryParameters)
			throws SQLException, ClassNotFoundException {
		Log.info("----------------Running DB Query---------");
		Connection con = getConnection();
		//
		java.sql.Statement stmt = con.createStatement();
		PreparedStatement query = con.prepareStatement(Query);
		Log.info("Query:" + Query);
		queryParameters.forEach((k, v) -> {
			try {
				Log.info("Parameter "+k.toString()+"Values: ["+v+"]");
				query.setString(k, v);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		ResultSet rs = query.executeQuery();
		rs.next();

		Log.info("DB Result:" + rs.getString(1));
		return Integer.parseInt(rs.getString(1));

	}
	
	public ResultSet executeDBQuery(String Query) throws SQLException {
		Log.info("----------------Running DB Query---------");
		Connection con = getConnection();
		//
		java.sql.Statement stmt = con.createStatement();
		PreparedStatement query = con.prepareStatement(Query);
		Log.info("Query:" + Query);
		ResultSet rs = query.executeQuery();
		return rs;
	}
	
	//TODO: Use the method getDBRecordCount
	public ResultSet getFramesFromDB(Map<String,String> queryParameters)
	{
		ResultSet rs = null;
		try
		{
		Log.info("----------------Running DB Query---------");
		Connection con = getConnection();
		String queryStr = "Select * from b2b_frame ";
		StringBuilder whereClauseSB = new StringBuilder();
		whereClauseSB.append("where ");
		queryParameters.forEach((k, v) -> {
			if(k.equalsIgnoreCase("origin_co") || k.equalsIgnoreCase("destination_co"))
				whereClauseSB.append(k+" in ? AND ");
			else
				whereClauseSB.append(k+"=? AND ");
		});
		
		String finalQuery = queryStr+ whereClauseSB.toString().substring(0,whereClauseSB.toString().length()-4);
		PreparedStatement prepStmt = con.prepareStatement(finalQuery);
		i=1;
		queryParameters.forEach((k, v) -> {
			try {
				prepStmt.setString(i, v);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				i++;
		});
		Log.info("Query:" + finalQuery);
		rs = prepStmt.executeQuery();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rs;
		
		
	}

	public Connection getConnection() throws SQLException {
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setServerName(Configuration.getDbUrl());
		dataSource.setDatabaseName(Configuration.getDbSid());
		dataSource.setPortNumber(Integer.parseInt(Configuration.getDbPort()));
		dataSource.setUser(Configuration.getDbUsername());
		dataSource.setPassword(Configuration.getDbPassword());
		dataSource.setDriverType("thin");
		return dataSource.getConnection();
	}
	
	//TODO: Why is this written?
	public static void main(String[] args) throws SQLException
	{
		DBConnectionHandler dbHandler = new DBConnectionHandler();
		Map<String,String> queryParameters = new HashMap<String,String>();
		queryParameters.put("origin_co", "BOM");
		queryParameters.put("destination_co", "SIN");
		queryParameters.put("filing_method", "CAT35 float");
		ResultSet rs = dbHandler.getFramesFromDB(queryParameters);
		
		while(rs.next())
		{
			System.out.println(rs.getString(1));
		}
	}

}
