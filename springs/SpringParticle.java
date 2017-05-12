package springs;

import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Trail;

public class SpringParticle extends Circle{
//		Trail t = new Trail();
		
		double x;
		double y;
		double v0;
		double k;
		double vX;
		double vY;
		double ax;
		double ay;
		double mass;
		double delta;
		double distTot;

		//particle takes in values for variables
		SpringParticle(double x, double y, double mass, double k, double delta, double distTot)
		{
			this.x = x;
			this.y = y;
//			this.v0 = v0;
//			this.angle = angle;
			this.mass = mass;
			this.k = k;
			this.delta = delta;
//			this.vX = v0 * Math.cos(Math.toRadians(angle));
//			this.vY = v0 * Math.sin(Math.toRadians(angle));
			this.vY = v0;
			this.distTot = distTot;
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
//		//angle launched at
//		public double getAngle() {
//			return angle;
//		}
//
//		public void setAngle(double angle) {
//			this.angle = angle;
//		}
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

		public void step() {
			//F=ma; a=F/m
//			
//			vX = vX + ax * delta;
//			x = x + vX * delta;

			vY = ay * delta;
			y = y + vY * delta;			
			
			//sets the animation of the particle
			setXY(x, y);
			pixRadius = 5;
		}
		
		public void stop() {
		//stops the program
		}

	}
