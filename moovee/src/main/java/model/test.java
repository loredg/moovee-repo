package model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class test {
	
	public static void main(String args[]) throws SQLException {
	
	String pw = "adminmoovee";
	System.out.println(PasswordHashing.toHash(pw));
	
	
	}
}
