package projectile;
import java.util.Scanner;

import org.opensourcephysics.controls.*;  //needed for the simulation
import org.opensourcephysics.display.*;   //needed to use Circles
import org.opensourcephysics.frames.*;//needed to use a DisplayFrame

/**
 * This class contains the basics of any simulation with some simple graphics.
 */
public class MovingBallApp extends AbstractSimulation {

        Circle c = new Circle();
        DisplayFrame frame = new DisplayFrame("x", "y", "Frame");  //two object which are created outside of any method so all methods can access them
        
        /**
         *In this class, the doStep() increments the x and y coordinate of the circle by 0.1
         *The effect is that ball moves up and to the right across the screen
         */
        protected void doStep() {

               c.setXY(c.getX() + 0.1, c.getY() + 0.1);

        }

        /**
         * Set the default values of "x" and "y" which will later be read as the initial x and y coordinate of the Circle
         */
        public void reset() {
     
        		control.setValue("Vo", 0);
                control.setValue("VoX", 0);
                control.setValue("VoY", 0);
        }

        /**
         * Reads in the "x" and "y" value from the Control Panel and sets them as the x and y coordinates of the Circle
         * Makes sure the frame is visible, and adds the circle to the DisplayFrame 
         */
        public void initialize() {

                c.setXY(control.getDouble("x"), control.getDouble("y"));
                frame.setVisible(true);
                frame.addDrawable(c);

        }

        public static void main(String[] args) {

                SimulationControl.createApp(new MovingBallApp());
        }

}
