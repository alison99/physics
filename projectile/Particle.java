package projectile;
import java.awt.Color;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;

/**
 * Particle object for Projectile Motion 
 * @author Alison Chen
 * This class extends Circle and establishes the specific characteristics inherent in a projectile as well as getters and setters for each.
 * These include position, initial velocity, angle, and acceleration (including that of air resistance).
 * The step methods move the projectile in the animation, using recursion to determine the position of the particle.
 * Using the user-inputted time step, the velocity of the particle and position is calculated at every given time step
 * This allows for precise determinations of the position and velocity, as well as a changing acceleration,
 * whereas using formulas would limit the program to only objects moving with a constant acceleration
 * step() moves both the x and y positions of a particle, and is used in the ProjectileLauncer to move the particle
 * stepX() and stepY() move the x and y positions respectively and are used in the BalloonDarts animation.
 * In BalloonDarts, stepY() is used to move the particle in the animation while stepX() is used to calculate whether the dart hit the wall before the balloon
 * In that animation, only the y position is demonstrated on the screen, because the frame of reference is that of a projectile moving directly away
 * The stop() method stops the motion of the projectile in the animation.
 */

public class Particle extends Circle {
	Trail t = new Trail();
	
	double x;
	double y;
	double v0;
	double angle;
	double vX;
	double vY;
	double ax;
	double ay;
	double delta;
	double hit;

	//particle takes in values for variables
	Particle(double x, double y, double v0, double angle, double ax, double ay, double delta)
	{
		this.x = x;
		this.y = y;
		this.v0 = v0;
		this.angle = angle;
		this.ax = ax;
		this.ay = ay;
		this.delta = delta;
		this.vX = v0 * Math.cos(Math.toRadians(angle));
		this.vY = v0 * Math.sin(Math.toRadians(angle));
		setXY(x, y);
	}
	
	//create getters and setters for all characteristics
	
	//initial velocity	
	public double getV0() {
		return v0;
	}

	public void setV0(double v0) {
		this.v0 = v0;
	}
	//angle launched at
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	//velocity in x direction
	public double getvX() {
		return vX;
	}

	public void setvX(double vX) {
		this.vX = vX;
	}
	//velocity in y direction
	public double getvY() {
		return vY;
	}

	public void setvY(double vY) {
		this.vY = vY;
	}
	//acceleration in x
	public double getAx() {
		return ax;
	}

	public void setAx(double ax) {
		this.ax = ax;
	}
	//acceleration in y
	public double getAy() {
		return ay;
	}
	
	public void setAy(double ay) {
		this.ay = ay;
	}
	//time step
	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}
	//x position
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	//y position
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	//trail for particle
	public Trail getT() {
		return t;
	}

	public void setT(Trail t) {
		this.t = t;
	}
	//determine whether hits the wall
	public double getHit() {
		return hit;
	}

	public void setHit(double hit) {
		this.hit = hit;
	}

//	All step functions below use a recursive method to determine the position of the particle.
//	Using the user-inputted time step, the velocity of the particle and position is calculated every time step
//	This allows for very precise determinations of the position and velocity, as well as a changing acceleration,
//	whereas using formulas would limit the program to only objects at a constant acceleration
	
	//moves x position - for balloon darts
	//-0.01 is the drag coefficient for a baseball to model air resistance
	public void stepX() {
		//calculates velocity in the x direction
		vX = vX + (-0.01)*vX * delta;
		//calculates the x position
		x = x + vX * delta;
		//the x position is only used for calculating whether the particle hit the wall and is not part of the animation
	}
	//moves y position - for balloon darts
	//-0.01 is the drag coefficient for a baseball to model air resistance
	public void stepY() {
		//calculates the velocity in the y direction
		vY = vY + (ay+ ((-0.01)*vY)) * delta;
		//calculates the y position
		y = y + vY * delta;
		//sets the animation of the particle
		setXY(0.0, y);
		t.addPoint(x, y);
		
		//this shrinks the size the particle to show that it is moving directly away from you
		//it begins to shrink as it starts to fall downwards when the velocity becomes negative due to the effects of gravity
		if(vY < 0 && pixRadius >=5) {
			pixRadius -=.00000000000001;
		}
	}
	//moves both x and y - for projectile launcher
	public void step() {
		//calculates the velocity in the x direction
		vX = vX + (-0.01)*vX * delta;
		//calculates the x position
		x = x + vX * delta;
		//calculates the velocity in the y direction
//		aY= g-alpha*vY
		vY = vY + (ay+ ((-0.01)*vY)) * delta;
		//calculates the y position
		y = y + vY * delta;
		
		//sets the animation of the particle
		setXY(x, y);
		t.addPoint(x,y);
		pixRadius = 5;
	}
	//stops the projectile
	public void stop() {
	//stops the program
	}

}
