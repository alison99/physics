package orbital;
import java.awt.Color;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;

public class Planet extends Circle {
	Trail t = new Trail();
	
	double x;
	double y;
	double mass1;
	double mass2;
	double vX;
	double vY;
	double ax1;
	double a1;
	double ay1;
	double ax2;
	double a2;
	double ay2;
	double distance1;
	double distance2;
	double delta;

	//particle takes in values for variables
	Planet(double x, double y, double v0x, double v0y, double mass1, double mass2, double delta) {
		this.x = x;
		this.y = y;
		this.mass1 = mass1;
		this.mass2 = mass2;
		this.delta = delta;
		this.vX = v0x;
		this.vY = v0y;
		setXY(x, y);
	}
	
	//create getters and setters for all characteristics
	
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
	
	public double getAx1() {
		return ax1;
	}

	public void setAx1(double ax1) {
		this.ax1 = ax1;
	}

	public double getA1() {
		return a1;
	}

	public void setA1(double a1) {
		this.a1 = a1;
	}

	public double getAy1() {
		return ay1;
	}

	public void setAy1(double ay1) {
		this.ay1 = ay1;
	}

	public double getAx2() {
		return ax2;
	}

	public void setAx2(double ax2) {
		this.ax2 = ax2;
	}

	public double getA2() {
		return a2;
	}

	public void setA2(double a2) {
		this.a2 = a2;
	}

	public double getAy2() {
		return ay2;
	}

	public void setAy2(double ay2) {
		this.ay2 = ay2;
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
	
	public double getMass1() {
		return mass1;
	}

	public void setMass1(double mass1) {
		this.mass1 = mass1;
	}

	public double getMass2() {
		return mass2;
	}

	public void setMass2(double mass2) {
		this.mass2 = mass2;
	}

	public double getDistance1() {
		return distance1;
	}

	public void setDistance1(double distance1) {
		this.distance1 = distance1;
	}

	public double getDistance2() {
		return distance2;
	}

	public void setDistance2(double distance2) {
		this.distance2 = distance2;
	}

	//moves x and y
	public void step() {
		//find acceleration
		a1 = ((mass1)*6.67408E-11)/(distance1*distance1);
		a2 = ((mass2)*6.67408E-11)/(distance2*distance2);
		
		//calculates the velocity in the x direction
		vX = vX + (ax1+ax2)*delta;
		
		//calculates the x position
		x = x + vX*delta;
		
		//calculates the velocity in the y direction
		vY = vY + (ay1+ay2)* delta;

		//calculates the y position
		y = y + vY *delta;
		
		//sets the animation of the particle
		setXY(x, y);
		t.addPoint(x,y);
	
	}
}
