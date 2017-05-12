package projectile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;
/**
 * ProjectileLauncher
 * @author Alison Chen
 * This class extends AbstractSimulation and creates an animation with a corresponding control panel, using the inherited method doStep().
 * doStep() loops every tenth of a second to move the object on the screen
 * stop() and reset() methods stop the program and reset the initial values respectively, while initialize() initializes the user-inputted values
 * 
 * The goal of this assignment was to create a program to model projectile motion on a graph of x and y position.
 * We sought to answer the question: "What is the best angle to hit a home run so that it goes directly over the wall?"
 * 
 * My program shoots multiple balls at varying angles within a range to test which angle just makes it over the wall.
 * My frame of reference consists of gravity downwards as negative, and wall a positive displacement to the right of the starting point.
 * The particles all have the same initial velocity, x- position, y- position, and acceleration to ensure that the only variable is the angle.
 * The user can input any of these values, including the range and the number of balls.
 * The effects of air resistance are included by using the drag coefficient of a baseball in both the x and y directions.
 * The particles will change color to indicate whether they fall short, hit the wall, or go over the wall.
 * The stop() method not only stops the program but also prints out the best angle for a home run
 * 
 * The most interesting part of this was seeing the difference that the effect of air resistance had on the best angle at a given velocity.
 * At 35º, without air resistance, the best angle was 54.88º, but with resistance it became 56.233º
 * 
 * BalloonDarts Project:
 * For my personal project, I explored an extension of this question, by considering a carnival game where you throw darts to try to pop a balloon.
 * The question was: "What is the best angle to throw a dart so that it hits a balloon moving in a circle at a constant angular velocity,
 * at the bottom of its cycle, which is directly across from the height of your throw,
 * if you begin your throw at the same time that the balloon starts moving from the top of its cycle?"
 *
 * My program shoots multiple darts at various angles within a range to test which one hits the balloon.
 * The animation is a two-dimensional demonstration where the frame of reference is that of a person looking directly at the balloon.
 * Positive is upwards from the starting point where the balloon begins its cycle and the balloon ends its cycle at (0,0).
 * The balloon and particles are centered along the x-axis because the animation focuses on a particle moving directly forward and
 * thus the x position is always the same, while the y- position changes.
 * The particles all have the same initial velocity, y- position, and acceleration to ensure that the only variable is the angle.
 * The user can input any of these values, including the range and the number of darts.
 * The effects of air resistance are included by using the drag coefficient in both the x and y directions.
 * The BalloonDarts class extends AbstractSimulation, and contains all the inherited methods.
 * The doStep() method animates the moving balloon as well as the particles, which shrink in size to indicate that they are moving further away
 * The stop() method stops the program and prints the angle that hit the balloon.
 * stepY() is used to animate the motion of the particles on the screen, while stepX() calculates where the dart hit the wall as in ProjectileLauncher
 * If the dart hits the wall before the balloon arrives, the particle stops moving and changes color.
 * The program may print more than one angle due to the precision of the time step and rounding.
 * 
 * Since I could not find the coefficient for a balloon dart, I made the assumption that it was the same as the drag on a baseball.
 * Thus the air resistance is probably too much because darts have less mass and are a different shape, and thus move through the air differently.
 * The color of the dart changes to indicate whether it hit the balloon or if hit the wall before the balloon arrived.
 * The program ends when either the balloon has reached the bottom of its cycle or all the darts have reached the wall
 * By running this program along with the ProjectileLauncher with the same user inputted values,
 * one can model the trajectory of a three dimensional dart thrown at a wall containing a moving balloon from two different perspective: 
 * directly facing the balloon and seeing it from the side, which gives a complete picture through two-dimensional representation.
 * 
 * The most difficult part of the project for me and the area where I learned the most was trying to animate an object with uniform circular motion
 * I needed to understand how to determine the x and y position of an object at any given point based on its angular velocity, radius, and center of the circle.
 * I found x = a + r * cos(theta) and y = b + r * sin(theta) where x and y are the x and y positions respectively, (a,b) is the center, and r is the radius
 * Theta is the angle in radians that expresses the position of the balloon along the circle,
 * which is determined by the angular velocity (given in radians per second) multipled by the time step (given by the user input)
 * The step() method uses this formula to calculate the position of the balloon recursively every given time step
 * The position of the darts is determined by the y- position of a projectile moving as in the ProjectileLauncher
 * Since it is a two-dimensional representation, the x-position on the animation remains constant so the darts move along the y-axis.
 * If seen from the side however, the x-position of the particles change as given in ProjectileLauncher, which determines whether the darts hit the wall.
 */

public class ProjectileLauncher extends AbstractSimulation {
	Scanner scan = new Scanner(System.in);
	ArrayList <Particle> plist = new ArrayList <Particle> ();
	Circle bn = new Circle();
	DisplayFrame frame = new DisplayFrame("x", "y", "Projectile");
	double precision;

	//moves the particle
	protected void doStep() {
		//front end of wall
		double x1 = control.getDouble("distance");
		//back end of wall
		double x2 = control.getDouble("distance") + control.getDouble("width");
		double height = control.getDouble("height");
		
		//launch particle
		for(int i = 0; i < control.getDouble("number of balls"); i++) {
			//stop particle if hits wall
			if(plist.get(i).getX() >= x1 && plist.get(i).getX() < x2 && plist.get(i).getY() <=height) {
				plist.get(i).stop();
			}
			//stop particle when y is 0 so that it does not go through the ground
			else{
				if(plist.get(i).getY() >= 0) {
					plist.get(i).step();
				}
			}
		}
			//color particle depending on where it lands
			//1. x<120 -> falls short
			//2. x>=120 && x<122 && y<=12 -> hits wall
			//3. x>122 -> over the wall
		
			for (int j = 0; j < control.getDouble("number of balls"); j++) {
				//hit wall
				if(plist.get(j).getX() >= x1 && plist.get(j).getX() < x2 && plist.get(j).getY()<=height) {
						plist.get(j).color = Color.magenta;
						plist.get(j).getT().color = Color.yellow;
				}
				//over the wall
				else if(plist.get(j).getX() >= x2) {
					plist.get(j).color = Color.cyan;
					plist.get(j).getT().color = Color.orange;
				}
			}	
		}

	//stops program and prints best angle
	public void stop() {
		int p = 0;
		double x2 = control.getDouble("distance")+control.getDouble("width");
		//this loops through all the balls that went over the wall to find the one with shortest distance from the wall
		//the angle that went over but had the shortest distance from the wall is the best angle
		//if distance from wall is less than 100, set new distance to "distance", then loop
		double distance = 100;
		for(int i = 0; i < control.getDouble("number of balls"); i++) {
			if(plist.get(i).getX()>= x2) {
				if((plist.get(i).getX()-x2) < distance) {
					distance = plist.get(i).getX() - x2;
					p = i;
					}
			}
		}
		//print best angle
		System.out.println("What is the best angle to hit a homerun?");
		System.out.println("The best angle is " + plist.get(p).getAngle());
		
	}

	//reset initial values
	//(x,y) of ball starts at (0,0) with no added acceleration in the x direction
	//distance from the starting position is 120 to model a home run
	public void reset() {
		frame.clearDrawables();
		control.setValue("number of balls", 500);
		control.setValue("x0", 0.00);
		control.setValue("y0", 0.00);
		control.setValue("v0", 38);
		control.setValue("initial angle", 30);
		control.setValue("final angle", 60);
		control.setValue("ax", 0);
		control.setValue("ay", -9.807);
		control.setValue("delta t", 0.03);		
		control.setValue("distance", 120);
		control.setValue("width", 2);
		control.setValue("height", 12);
	}

	//initialize user input values and create balls
	public void initialize() {
		frame.clearDrawables();
		plist.clear();
		//evenly space out the angles within a range by calculating the average between the final and initial angles
		precision = (control.getDouble("final angle") - control.getDouble("initial angle"))/(control.getDouble("number of balls"));
		//create particles according to number given in user input
		for(int i = 0; i < control.getDouble("number of balls"); i++) {
			plist.add(new Particle(control.getDouble("x0"), control.getDouble("y0"),
					control.getDouble("v0"), (control.getDouble("initial angle") + (i)*precision),
					control.getDouble("ax"), control.getDouble("ay"), control.getDouble("delta t")));
			plist.get(i).setXY(control.getDouble("x0"), control.getDouble("y0"));
			plist.get(i).getT().clear();
			plist.get(i).getT().color = Color.blue;
			frame.addDrawable(plist.get(i));
			frame.addDrawable(plist.get(i).getT());
		}

		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
		frame.setPreferredMinMax(-5, 50, -5, 50);

		//create wall
		double xwall= control.getDouble("distance") + (control.getDouble("width")/2);
		double ywall = (control.getDouble("height"))/2;
		
		DrawableShape wall = DrawableShape.createRectangle(xwall, ywall, control.getDouble("width"), control.getDouble("height"));
		frame.addDrawable(wall);
	}

	public static void main(String[] args) {

		SimulationControl.createApp(new ProjectileLauncher());
	}
}
