package model;
//import com.mysql.jdbc.Statement;

import persistence.DataBaseDAO;
import persistence.DataBaseMySQL;

import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
@Component
public class ForecastDAOImp implements ForecastDAO{
	@Autowired
	private DataBaseDAO dat;
	@Autowired
	private Connection con;
	
//	public ForecastDAOImp(){
//		//Call to DataBase MySQL, create if not exists and create the connection
//		dat = new DataBaseMySQL();
//	}
	
	public void Insert(Forecast forecast){
		try{
			con = dat.getCon();
			Statement st = (Statement) con.createStatement();
			String DbName = dat.getDbName();
			st.executeUpdate("use "+DbName+";");
			Day day;
			for(int i=0; i < forecast.AmountDay(); i++){
				day = forecast.getDay(i);
				st.executeUpdate("INSERT INTO days(`day_name`, `forecast_id`, `tempmax`, `tempmin`) "
								+ "SELECT '"+day.getDay()+"', MAX(id_actual),'"+ day.getTempMax()+"','"+day.getTempMin()+"' "
								+ "from actualday;");
			}
			System.out.println("SetInfoDay Satisfactoriamente");
			st.close();
		}
		catch (Exception e){
			System.out.println("***********Fallo Info Day******************");
		}
	}
}
