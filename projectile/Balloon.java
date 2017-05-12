package projectile;
import java.awt.Color;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.display.Circle;
/**
 * Balloon object for BalloonDarts
 * @author Alison Chen
 * This class extends Circle and sets the properties of a balloon moving at uniform angular velocity in a circle
 * these include the center at (a,b), r as the radius, angular velocity, initial angle, and the time step
 * I found x = a + r * cos(theta) and y = b + r * sin(theta) where x and y are the x and y positions respectively, (a,b) is the center, and r is the radius
 * Theta is the angle in radians that expresses the position of the balloon along the circle,
 * which is determined by the angular velocity (given in radians per second) multipled by the time step (given by the user input)
 * The step() method uses this formula to calculate the position of the balloon recursively every given time step
 *
 * The position of the darts on the screen is determined by the y- position of a projectile moving as in the ProjectileLauncher
 * Since it is a two-dimensional representation, the x-position on the animation remains constant so the darts move along the y-axis.
 * If seen from the side however, the x-position of the particles change as given in ProjectileLauncher, which determines whether the darts hit the wall.
 */
public class Balloon extends Circle {
	double a;
	double b;
	double r;
	double x;
	double y;
	double angularv;
	double t;
	double delta;
	double angle;
	
	//create getters and setters
	//radius
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	//x-position (when seen from the side as in ProjectileLauncher)
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	//y-position
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	//trail of balloon
	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}
	//time step
	public double getDelta() {
		return delta;
	}
		
	public void setDelta(double delta) {
		this.delta = delta;
	}
	//angular velocity in radians/second
	public double getAngularv() {
		return angularv;
	}
	public void setAngularv(double angularv) {
		this.angularv = angularv;
	}
	//angle shot at
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	//center at (a,b)
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
//	The step function uses a recursive method to determine the position of the balloon every time step
	// angle = angular velocity * time passed
	public void step() {
		//determines x and y positions respectively
		x = a + r * Math.cos(angularv * t + Math.PI/2);
		y = b + r * Math.sin(angularv * t + Math.PI/2);
		setXY(x, y);
		//time = number of time steps
		t += delta;
	}
}
