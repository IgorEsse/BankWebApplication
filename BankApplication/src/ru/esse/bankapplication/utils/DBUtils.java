package ru.esse.bankapplication.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

//import org.eclipse.persistence.sdo.*;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;
 
import ru.esse.bankapplication.beans.Client;
import ru.esse.bankapplication.beans.Account; 
//import ru.esse.bankapplication.beans.Audit;
 
public class DBUtils {
	
	private static final String CH_NAMESPACE = "http://www.example.com/CH";
	//private static final String CH_MODEL = "C:\\Test\\xml\\cl.xsd";
	private static final String CH_MODEL = "C:\\Test\\xml\\PO.xsd";
	private static final String CH_XML = "C:\\Test\\xml\\clll.xml";
 
	private static void definePOTypes() throws IOException {
	     FileInputStream fis = new FileInputStream(CH_MODEL);
	     XSDHelper.INSTANCE.define(fis, null);
	     fis.close();
	}
	
    public static Client findUser(Connection conn, //
            String username, String password) throws SQLException {
 
        String sql = "SELECT a.id, "
        				  + "a.username, "
        				  + "a.password, "
        				  + "a.name, "
        				  + "a.surname, "
        				  + "a.birth_date "
        		   + "FROM Client a " //
                   + "WHERE a.username = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	String id = rs.getString("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String birth_date = rs.getString("birth_date");
            Client user = new Client();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setBirth_date(birth_date);
            return user;
        }
        return null;
    }
 
    public static Client findUser(Connection conn, String username) throws SQLException {
 
        String sql = "Select a.id, a.username, a.password, a.name, a.surname, a.birth_date from Client a "//
                + " where a.username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	String id = rs.getString("id");
            String password = rs.getString("password");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String birth_date = rs.getString("birth_date");
            Client user = new Client();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setBirth_date(birth_date);
            return user;
        }
        return null;
    }
    
    public static Client findUserId(Connection conn, String id) throws SQLException {
        String sql = "Select a.id, a.username, a.password, a.name, a.surname, a.birth_date from Client a where a.id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	id = rs.getString("id");
        	String username = rs.getString("username");
            String password = rs.getString("password");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String birth_date = rs.getString("birth_date");
            
            Client client = new Client(username,password,birth_date,name,surname);
            return client;
        }
        return null;
    }
    
    public static void insertUser(Connection conn, Client client) throws SQLException {
        String sql = "Insert into Client(username, password, birth_date, name, surname) values (?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, client.getUsername());
        pstm.setString(2, client.getPassword());
        pstm.setString(3, client.getBirth_date());
        pstm.setString(4, client.getName());
        pstm.setString(5, client.getSurname());
 
        pstm.executeUpdate();
    }
 
    public static void updateUser(Connection conn, Client client) throws SQLException {
        String sql = "Update Client set username =?, password=?, birth_date=?, name=?, surname=? where id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, client.getUsername());
        pstm.setString(2, client.getPassword());
        pstm.setString(3, client.getBirth_date());
        pstm.setString(4, client.getName());
        pstm.setString(5, client.getSurname());
        pstm.setString(6, client.getId());
        
        pstm.executeUpdate();
    }
    
    public static void deleteUser(Connection conn, String id) throws SQLException {
        String sql = "Delete From Client where id= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
    
    public static List<Account> queryAccount(Connection conn, String user_id) throws SQLException {
        String sql = "SELECT a.id, "
        				  + "a.balance, "
        				  + "a.open_date, "
        				  + "a.close_date, "
        				  + "a.status, "
        				  + "a.client_id "
        		   + "FROM Account a "
        		   +"WHERE a.client_id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user_id);
 
        ResultSet rs = pstm.executeQuery();
        List<Account> list = new ArrayList<Account>();
        while (rs.next()) {
        	String id = rs.getString("id");
            String balance = rs.getString("balance");
            String open_date = rs.getString("open_date");
            String close_date = rs.getString("close_date");
            String status = rs.getString("status");
            String client_id = rs.getString("client_id");
            Account account = new Account();
            account.setId(id);
            account.setBalance(balance);
            account.setOpen_date(open_date);
            account .setClose_date(close_date);
            account.setStatus(status);
            account.setClient_id(client_id);
            list.add(account);
        }
        return list;
    }
    
    public static Account findAccount(Connection conn, String id) throws SQLException {
        String sql = "Select a.id, a.balance, a.open_date, a.close_date, a.status, a.client_id from Account a where a.id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	id = rs.getString("id");
        	String balance = rs.getString("balance");
            String open_date = rs.getString("open_date");
            String close_date = rs.getString("close_date");
            String status = rs.getString("status");
            String client_id = rs.getString("client_id");
            
            Account account = new Account(id,balance,open_date,close_date,status,client_id);
            return account;
        }
        return null;
    }
 
    public static void updateAccount(Connection conn, Account account) throws SQLException, IOException {
        String sql = "Update Account set balance =?, open_date=?, close_date=?, status=?, client_id=? where id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, account.getBalance());
        pstm.setString(2, account.getOpen_date());
        pstm.setString(3, account.getClose_date());
        pstm.setString(4, account.getStatus());
        pstm.setString(5, account.getClient_id());
        pstm.setString(6, account.getId());
        
        //Сохранение истории в XML
        try {
			definePOTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
        DataObject acc = DataFactory.INSTANCE.create(CH_NAMESPACE,"Account");
        DataObject accountSDO = acc.createDataObject("Account");
        accountSDO.set("balance",account.getBalance());
        accountSDO.set("open_date",account.getOpen_date());
        accountSDO.set("close_date",account.getClose_date());
        accountSDO.set("status",account.getStatus());
        accountSDO.set("client_id",account.getClient_id());
        accountSDO.set("id",account.getId());
        OutputStream stream = new FileOutputStream(CH_XML);
        XMLHelper.INSTANCE.save(acc, CH_NAMESPACE, "Account", stream);
        
        pstm.executeUpdate();
    }
 
    public static void insertAccount(Connection conn, Account account) throws SQLException {
        String sql = "Insert into Account(balance, open_date, close_date, status, client_id) values (?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, account.getBalance());
        pstm.setString(2, account.getOpen_date());
        pstm.setString(3, account.getClose_date());
        pstm.setString(4, account.getStatus());
        pstm.setString(5, account.getClient_id());
 
        pstm.executeUpdate();
    }
 
    public static void deleteAccount(Connection conn, String id) throws SQLException {
        String sql = "Delete From Account where id= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, id);
 
        pstm.executeUpdate();
    }
 
}
