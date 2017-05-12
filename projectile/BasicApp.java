package projectile;
import org.opensourcephysics.controls.*;

/**
 * This class contains the basics of any simulation
 * without the specific graphics.
 */
public class BasicApp extends AbstractSimulation {  //be sure to extend AbstractSimulation

        int x;  //class variable.  All methods will want to access this, so it must be declared outside of all methods.
        

        /**
         *Required method.  You must implement the doStep() to run the app
         *Every 1/10 s, this method will print "x = " and the value of x to the message area of the control
         *panel.  It will then increment the variable x.
         */
        protected void doStep() 
        {
                control.println("x = "+x);
                x++;
        }
        
        /**
         * Sets default values of parameters and defines name-value pairs.
         * Also clears all outputs and sets value of parameters back to their defaults.
         * Specifically, this method creates the name-value pair:  (Value of X,50)
         * In the control panel window, the string "Value of X" will appear in the left column
         * and 50 will appear in the right.  The right column is editable by the user.
         */
        public void reset() {
                control.setValue("Value of X", 50);
        }
        
        /**
         * User defined initial values are captured when the Initialize button is pressed.
         * Specifically, this method will read in value that corresponds with the name "Value of X"
         * and set the class variable x (declared above) equal to it.  
         */
        public void initialize() {
                
                x = control.getInt("Value of X");
        }

        
        public static void main(String[] args) 
        {
                //This line creates an instance of your app and starts it running.
                SimulationControl.createApp(new BasicApp());
        }

}