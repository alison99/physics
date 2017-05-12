package orbital;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.BoundedShape;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.InteractiveShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.DisplayFrame;
import projectile.Particle;
/**
 * 
 * @author student
 * print out ellipse based on foci and center
 * print out area all the time - show not changing
 * print t^2 and a^3
 * 
 * 
 * check binary star system
 * add more planets
 * bonus? 
 * use alatonce - 6
 */

public class ControlPanel extends AbstractSimulation {
	Scanner scan = new Scanner(System.in);
	Planet p1;
	Planet p2;
	Planet moon;
	Circle bn = new Circle();
	DisplayFrame frame = new DisplayFrame("x", "y", "Projectile");
	double distance1;
	double a;
	double afinal;
	double b;
	double c;
	double time = 0;
	int period = 0;
	double t;

	//moves the particle
	protected void doStep() {
		double xmin = control.getDouble("x02");
		double ymax = control.getDouble("y02");
		double xbefore = p2.x;
		double ybefore = p2.y;
			
		p1.step();
		p2.step();
		moon.step();
		
		//sun to the earth, earth to sun, moon to earth
		p1.setDistance1(Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y)));
		p2.setDistance1(Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y)));
		moon.setDistance1(Math.sqrt((moon.x-p2.x)*(moon.x-p2.x)+(moon.y-p2.y)*(moon.y-p2.y)));
		
		//sun to moon, earth to moon, moon to sun
		p1.setDistance2(Math.sqrt((moon.x-p1.x)*(moon.x-p1.x)+(moon.y-p1.y)*(moon.y-p1.y)));
		p2.setDistance2(Math.sqrt((moon.x-p2.x)*(moon.x-p2.x)+(moon.y-p2.y)*(moon.y-p2.y)));
		moon.setDistance2(Math.sqrt((moon.x-p1.x)*(moon.x-p1.x)+(moon.y-p1.y)*(moon.y-p1.y)));
		
		//earth-sun, moon-earth, sun-earth
		p2.setAx1(-p2.a1 * (p2.x-p1.x)/p2.distance1);
		p2.setAy1(-p2.a1 * (p2.y-p1.y)/p2.distance1);
		moon.setAx1(-moon.a1 * (moon.x-p2.x)/moon.distance1);
		moon.setAy1(-moon.a1 * (moon.y-p2.y)/moon.distance1);
		p1.setAx1(-p1.a1 * (p1.x-p2.x)/p1.distance1);
		p1.setAy1(-p1.a1 * (p1.y-p2.y)/p1.distance1);
		
		//earth-moon, sun-moon, moon-sun
		p2.setAx2(-p2.a2 * (p2.x-moon.x)/p2.distance2);
		p2.setAy2(-p2.a2 * (p2.y-moon.y)/p2.distance2);
		moon.setAx2(-moon.a2 * (moon.x-p1.x)/moon.distance2);
		moon.setAy2(-moon.a2 * (moon.y-p1.y)/moon.distance2);
		p1.setAx2(-p1.a2 * (p1.x-moon.x)/p1.distance2);
		p1.setAy2(-p1.a2 * (p1.y-moon.y)/p1.distance2);
			
		time = time + control.getDouble("delta t");
		
		//draw ellipse
		if(p2.x <= xbefore && p2.y >= ybefore) {
			xmin = p2.x; ymax = p2.y;
		
		a = (control.getDouble("x02")-xmin);
		b = ymax;
		c = a-p1.distance1;
		}
		if(p2.x>xbefore) {
			afinal = a;
	//	frame.addDrawable(InteractiveShape.createEllipse(control.getDouble("x02")-a, control.getDouble("y02"), 2*a, 2*b));
		
		}
		
		//find areas:
		double area = Math.abs(control.getDouble("x01")*(ybefore-p2.y)+xbefore*(p2.y-control.getDouble("y01"))+p2.x*(control.getDouble("y01")-ybefore))/2;
		System.out.println(area);
		
		//period
		if(p2.x>0&&xbefore<0) {
			period++;
			t=time/period;
		}
	}

	public void stop() { 
		System.out.println("a^3 = " + afinal*afinal*afinal + " and T^2= " + t*t);
		System.out.println((t*t)/(afinal*afinal*afinal));
	}

	public void reset() {
		frame.clearDrawables();
		control.setValue("x01", 0.00);
		control.setValue("y01", 0.00);
		control.setValue("v01", 0);
		control.setValue("x02", 1.49598E15); //normal: 1.49598E11
		control.setValue("y02", 0.00);
		control.setValue("moonX", 1.4959804E15); //normal: 1.4998E11
		control.setValue("moonY", 0.00);
		control.setValue("v01x", 0);
		control.setValue("v01y", 0);
		control.setValue("v02x", 0);
		control.setValue("v02y", 300); //normal: 30000
		control.setValue("moonVx", 0);
		control.setValue("moonVy", 1022); 
		control.setValue("mass1", 1.989E30);
		control.setValue("mass2", 5.972E24);
		control.setValue("moonMass", 7.348E22); 
		control.setValue("delta t", 100000);	
	}

	public void initialize() {
		frame.clearDrawables();
		
		double moonVx = control.getDouble("v02x") + control.getDouble("moonVx");
		double moonVy = control.getDouble("v02y") + control.getDouble("moonVy");
		
		p1 = new Planet(control.getDouble("x01"), control.getDouble("y01"), control.getDouble("v01x"), control.getDouble("v01y"),
				control.getDouble("mass2"), control.getDouble("moonMass"), control.getDouble("delta t"));
		p2 = new Planet(control.getDouble("x02"), control.getDouble("y02"), control.getDouble("v02x"), control.getDouble("v02y"),
				control.getDouble("mass1"), control.getDouble("moonMass"), control.getDouble("delta t"));
		moon = new Planet(control.getDouble("moonX"), control.getDouble("moonY"), moonVx, moonVy, control.getDouble("mass2"), control.getDouble("mass1"),
				control.getDouble("delta t"));
		
		p1.distance1 = Math.abs(control.getDouble("x01") - control.getDouble("x02"));
		p2.distance1 = Math.abs(control.getDouble("x02") - control.getDouble("x01"));
		moon.distance1 = Math.abs(control.getDouble("moonX") - control.getDouble("x02"));
		
		p1.distance2 = Math.abs(control.getDouble("x01") - control.getDouble("moonX"));
		p2.distance2 = Math.abs(control.getDouble("x02") - control.getDouble("moonX"));
		moon.distance2 = Math.abs(control.getDouble("moonX") - control.getDouble("x01"));
		
		
		p1.color = Color.orange;
		p1.pixRadius = 10;
		p2.color = Color.green;
		p2.pixRadius = 5;
		moon.color = Color.gray;
		moon.pixRadius = 3;
		p1.getT().color= Color.magenta;
		p2.getT().color = Color.blue;
		moon.getT().color = Color.darkGray;
		p1.getT().clear();
		p2.getT().clear();
		moon.getT().clear();
		frame.clearDrawables();
		frame.addDrawable(p1);
		frame.addDrawable(p1.getT());
		frame.addDrawable(p2);
		frame.addDrawable(p2.getT());	
		frame.addDrawable(moon);
		frame.addDrawable(moon.getT());	
		frame.setPreferredMinMax(-2E11, 2E11, -0.6E11, 1E11);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}

	public static void main(String[] args) {

		SimulationControl.createApp(new ControlPanel());
	}
}