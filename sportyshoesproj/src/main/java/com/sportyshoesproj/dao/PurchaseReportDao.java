package com.sportyshoesproj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sportyshoesproj.pojo.PurchaseReport;

@Repository
public class PurchaseReportDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<PurchaseReport> getAllPurchaseReport(){
		return jdbcTemplate.query("select * from purchasereport", new RowMapper<PurchaseReport>() {

			@Override
			public PurchaseReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				PurchaseReport purchaseReport = new PurchaseReport();
				purchaseReport.setPurchaseId(rs.getInt(1));
				purchaseReport.setPurchaseUserId(rs.getInt(2));
				purchaseReport.setPurchaseProductId(rs.getInt(3));
				purchaseReport.setDate(rs.getString(4));
				return purchaseReport;
			}
			
		});
	}
	
	public List<PurchaseReport> getAllPurchaseReportByDate(String date){
		return jdbcTemplate.query("select * from purchasereport where date = ?", new RowMapper<PurchaseReport>() {

			@Override
			public PurchaseReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				PurchaseReport purchaseReport = new PurchaseReport();
				purchaseReport.setPurchaseId(rs.getInt(1));
				purchaseReport.setPurchaseUserId(rs.getInt(2));
				purchaseReport.setPurchaseProductId(rs.getInt(3));
				purchaseReport.setDate(rs.getString(4));
				return purchaseReport;
			}
			
		}, date);
	}
	
	
	
}
