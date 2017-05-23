package logic;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.Actualday;
/**
 * Main class
 *@author Emiliano Bentivegna
 */

@Component
public class Main 
{
	@Autowired
	@Qualifier("man")
	private Manager man;
	
	
    public static void main( String[] args )
    {	
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Main main =  context.getBean(Main.class);
    	main.startApp(args);    	
    }
	
	public void startApp(String[] args){
		System.out.println ("Starting");
        System.out.println ("Insert the city's name to know the weather condition");
        String keyboardInput = "";
        Scanner scannerInput = new Scanner (System.in); 
        keyboardInput = scannerInput.nextLine();
        System.out.println ("You ask for: " + keyboardInput +".");
        
        //Create location
        Actualday.Location location = new Actualday.Location(keyboardInput);
        
//        Manager man = new Manager(location);
        man.setLocation(location);
        man.AskForYahooForecast("yahoo");
		man.ParserInfo();
		man.CreateNodesWithInfo();
		man.PrintInformation();
	}
}

