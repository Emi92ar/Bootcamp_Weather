package logic;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static Manager man; 
	
    public static void main( String[] args )
    {	
//    	ApplicationContext context = new ClassPathXmlApplicationContext("resources.xml");
//        Manager a = (Manager) context.getBean("manager");
    	
    	
    	System.out.println ("Starting");
        System.out.println ("Insert the city's name to know the weather condition");
        String keyboardInput = "";
        Scanner scannerInput = new Scanner (System.in); 
        keyboardInput = scannerInput.nextLine();
        System.out.println ("You ask for: " + keyboardInput +".");
        Actualday.Location location = new Actualday.Location(keyboardInput);
        
//        Manager man = new Manager(location);
        man.PrintInformation();
    }
}

