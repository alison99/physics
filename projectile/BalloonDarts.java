package projectile;
import java.awt.Color;
import java.util.ArrayList;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;
/**
 * BalloonDarts
 * @author Alison Chen
 * 
 * I considered a carnival game where you throw darts to try to pop a balloon.
 * My question was: "What is the best angle to throw a dart so that it hits a balloon moving in a circle at a constant angular velocity,
 * at the bottom of its cycle, which is directly across from the height of your throw,
 * if you begin your throw at the same time that the balloon starts moving from the top of its cycle?"
 * My program shoots multiple darts at various angles within a range to test which one hits the balloon.
 * 
 * The BalloonDarts class extends AbstractSimulation, and contains all the inherited methods.
 * The doStep() method animates the moving balloon as well as the particles, which shrink in size to indicate that they are moving further away
 * The stop() method stops the program and prints the angle that hit the balloon.
 * initialize() and reset() initialize the user-input values and resets them to default values respectively
 * The program may print more than one angle due to the precision of the time step and rounding.
 * The animation is a two-dimensional demonstration where the frame of reference is that of a person looking directly at the balloon.
 * Positive is upwards from the starting point where the balloon begins its cycle and the balloon ends its cycle at (0,0).
 * The balloon and particles are centered along the x-axis because the animation focuses on a particle moving directly forward and
 * thus the x position is always the same, while the y- position changes.
 * The particles all have the same initial velocity, y- position, and acceleration to ensure that the only variable is the angle.
 * The user can input any of these values, including the range and the number of darts.
 * The effects of air resistance are included by using the drag coefficient in both the x and y directions.
 * 
 * stepY() is used to animate the motion of the particles on the screen, while stepX() calculates where the dart hit the wall as in ProjectileLauncher
 * If the dart hits the wall before the balloon arrives, the particle stops moving and changes color.
 * 
 * Since I could not find the coefficient for a balloon dart, I made the assumption that it was the same as the drag on a baseball.
 * Thus the air resistance is probably too much because darts have less mass and are a different shape, and thus move through the air differently.
 * The color of the dart changes to indicate whether it hit the balloon or if hit the wall before the balloon arrived.
 * The program ends when either the balloon has reached the bottom of its cycle or all the darts have reached the wall
 * By running this program along with the ProjectileLauncher with the same user inputted values,
 * one can model the trajectory of a three dimensional dart thrown at a wall containing a moving balloon from two different perspective: 
 * directly facing the balloon and seeing it from the side, which gives a complete picture through two-dimensional representation.
 * 
 * The most difficult part of the project and the area where I learned the most was trying to animate an object with uniform circular motion
 * I needed to understand how to determine the x and y position of an object at any given point based on its angular velocity, radius, and center of the circle.
 * I found x = a + r * cos(theta) and y = b + r * sin(theta) where x and y are the x and y positions respectively, (a,b) is the center, and r is the radius
 * Theta is the angle in radians that expresses the position of the balloon along the circle,
 * which is determined by the angular velocity (given in radians per second) multipled by the time step (given by the user input)
 * The position of the darts is determined by the y- position of a projectile moving as in the ProjectileLauncher
 * Since it is a two-dimensional representation, the x-position on the animation remains constant so the darts move along the y-axis.
 * If seen from the side however, the x-position of the particles change as given in ProjectileLauncher, which determines whether the darts hit the wall.
 * 
 */

public class BalloonDarts extends AbstractSimulation{
	DisplayFrame bframe = new DisplayFrame("x", "y", "Balloon Darts");
	ArrayList <Particle> plist = new ArrayList <Particle> ();
	Balloon b = new Balloon();
	Trail btrail = new Trail();
	double precision;
	double time;
	//this refers to the number of time steps that it takes for the balloon to reach the bottom of its cycle
	double steps;
	double hitwall;

	//move the balloon and particles on frame
	protected void doStep() {
		//this loop stops the motion of the objects when the balloon reaches 0 and if all darts have hit the wall before the ballooon
		if(steps > 0 && hitwall < control.getDouble("number of darts")) {
			//move the balloon
			b.step();
			btrail.addPoint(b.getX(),b.getY());
			//move the darts
			for(int i = 0; i < control.getDouble("number of darts"); i++) {
				//indicate if particle hit the wall before balloon
				if(plist.get(i).getX()>= control.getDouble("distance")) {
					if(plist.get(i).hit == 0) {
					plist.get(i).hit = 1; 
					hitwall+=plist.get(i).hit;}
					plist.get(i).color = Color.cyan;
				}
				//move the particle and calculate the corresponding x-position if seen from the side = as in ProjectileLauncher
				else {
					plist.get(i).stepX();
					plist.get(i).stepY();
				}			}
		}
		//indicate if successfully hit the balloon
		for (int i = 0; i < control.getDouble("number of darts"); i++) {
			double x = 0.0;
			double bx = Math.round(b.getX()*1000)/1000;
			double y = Math.round(plist.get(i).getY() * 1000)/1000;
			double by = Math.round(b.getY()*1000)/1000;
			if(y==by && x==bx) {
				plist.get(i).color = Color.yellow; }
		}
		steps --;
	}

	//stops the program and prints whether hit the balloon
	public void stop() {
		for (int i = 0; i < control.getDouble("number of darts"); i++) {
			//rounding means that the program may print multiple values
			//rounding is necessary because precision of time step means that numbers are not exact
			double x = 0.0;
			double bx = Math.round(b.getX()*1000)/1000;
			double y = Math.round(plist.get(i).getY() * 1000)/1000;
			double by = Math.round(b.getY()*1000)/1000;
			//if position of dart is the same as position of the balloon, it hit
			if(y==by && x==bx) {
				System.out.println("Yay! You popped the balloon!");
				System.out.println("Your angle was " + plist.get(i).getAngle());
				System.out.println();
			}
		}
	}

	//resets the initial values
	//initial and final angle are in degrees but angular velocity must be given in radians by definition
	//distance refers to the distance to the wall from the starting position
	//there is no initial x position because it all occurs along the x-axis = this is a two-dimensional view where the dart goes straight ahead
	public void reset() {
		bframe.clearDrawables();
		control.setValue("number of darts", 30);
		control.setValue("y0", 0.00);
		control.setValue("v0", 21.25);
		control.setValue("initial angle", 20);
		control.setValue("final angle", 80);
		control.setValue("ay", -9.807);
		control.setValue("delta t", 0.03);		
		control.setValue("angular velocity", 1.7);
		control.setValue("radius", 10);
		control.setValue("distance", 35);
	}

	//initializes user input values
	public void initialize() {
		hitwall = 0;
		bframe.clearDrawables();
		btrail.clear();
		
		//determine number of time steps by calculating the time that it takes for the balloon to go half of its cycle
		//this is possible because the balloon moves at uniform velocity (or it would be an unfair carnival game)
		time = (Math.PI)/(control.getDouble("angular velocity"));
		steps = time/(control.getDouble("delta t"));
		
		//set values for balloon
		b.setT(0);
		b.setR(control.getDouble("radius"));
		b.setA(0.0);
		b.setB(control.getDouble("y0") + control.getDouble("radius"));
		b.setX(0.0);
		b.setY(control.getDouble("y0") + 2* control.getDouble("radius"));
		b.setAngularv(control.getDouble("angular velocity"));
		b.setDelta(control.getDouble("delta t"));
		b.setXY(b.getX(), b.getY());
		bframe.addDrawable(b);
		b.pixRadius = 12;
		
		//set values for and create darts according to the number that is desired by the user
		plist.clear();
		precision = (control.getDouble("final angle") - control.getDouble("initial angle"))/(control.getDouble("number of darts"));
		for(int i = 0; i < control.getDouble("number of darts"); i++) {
			plist.add(new Particle(0.0, control.getDouble("y0"),
					control.getDouble("v0"), (control.getDouble("initial angle") + (i)*precision),
					0.0, control.getDouble("ay"), control.getDouble("delta t")));
			plist.get(i).setXY(0.0, control.getDouble("y0"));
			plist.get(i).setHit(0);
			plist.get(i).pixRadius = 9;
			plist.get(i).color = Color.blue;
			bframe.addDrawable(plist.get(i));
		}
		btrail.color = Color.pink;
		bframe.addDrawable(btrail);
		bframe.setPreferredMinMax(-50, 50, -20, 70);
		bframe.setVisible(true);
		bframe.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {

		SimulationControl.createApp(new BalloonDarts());
	}

}
