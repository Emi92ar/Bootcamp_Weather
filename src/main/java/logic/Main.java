package logic;
/**
 * Main class
 *@author Emiliano Bentivegna
 */
public class Main 
{
    public static void main( String[] args )
    {
        Actualday.Location location = new Actualday.Location("Santiago");
        Manager manager = new Manager(location);
        manager.PrintInformation();
    }
}

