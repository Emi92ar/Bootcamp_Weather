package logic;
import java.util.Scanner;

import model.Actualday;
/**
 * Main class
 *@author Emiliano Bentivegna
 */
public class Main 
{
    public static void main( String[] args )
    {	
    	System.out.println ("Starting");
        System.out.println ("Insert the city's name to know the weather condition");
        String keyboardInput = "";
        Scanner scannerInput = new Scanner (System.in); 
        keyboardInput = scannerInput.nextLine();
        System.out.println ("You ask for: " + keyboardInput +".");
        Actualday.Location location = new Actualday.Location(keyboardInput);
        Manager manager = new Manager(location);
//        manager.PrintInformation();
    }
}

