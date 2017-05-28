package daos;


import java.sql.Connection;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import persistence.DataBaseMySQL;
@Component
public abstract class BASEWeatherDAOImp {
	
	@Autowired
	@Qualifier ("DataBaseMySQL")
	protected DataBaseDAO dat;
	
	protected Connection con;
	
	public BASEWeatherDAOImp(){
//		dat = new DataBaseMySQL();
	}
	
	public void InsertGeneric(String insertImpl){
		try{
			con = dat.getCon();
			Statement st = (Statement) con.createStatement();
			String DbName = dat.getDbName();
			st.executeUpdate("use "+DbName+";");
			//The next Line execute the insert 
			st.executeUpdate(insertImpl);
			System.out.println("SetInfo Correctly");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Fallo Info Day******************");
		} 
	}
}
