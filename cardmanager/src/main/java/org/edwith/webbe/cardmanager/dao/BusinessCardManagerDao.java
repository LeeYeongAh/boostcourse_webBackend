package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;
import org.edwith.webbe.cardmanager.ui.CardManagerUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/businesscarddb?serverTimezone=UTC";
	private static String dbUser = "businessuser";
	private static String dbpasswd = "0000";
	private List<BusinessCard> list = new ArrayList<>();
	
	
    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
    	CardManagerUI cardManagerUI = CardManagerUI.getInstance();
    	List<BusinessCard> cardList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT name, number, company FROM card where name like CONCAT( '%',?,'%')";
		try(Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)){
				ps.setString(1, keyword);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String name = rs.getString(1);
					String number = rs.getString(2);
					String company = rs.getString(3);
					
					BusinessCard card = new BusinessCard(name, number, company);
					cardList.add(card);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cardList;
    	
    	
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// 구현하세요.
    	
    	Connection conn = null;
		PreparedStatement ps = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
		String sql = "INSERT INTO card (name, number, company) VALUES (?, ?, ?)";
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, businessCard.getName());
		ps.setString(2, businessCard.getPhone());
		ps.setString(3, businessCard.getCompanyName());

		ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(Exception ex) {
					
				}
			}
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception ex) {
					
				}
			}
		}
		
		list.add(businessCard);
		return businessCard;
		
	}
    
}
