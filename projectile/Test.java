package projectile;
import java.awt.Color;
//import java.util.ArrayList;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;


public class Test extends AbstractSimulation
{
	DisplayFrame frame = new DisplayFrame("x", "y", "Projectile");
/*	Particle p1 = new Particle(control.getDouble("x0"), control.getDouble("y0"), control.getDouble("v0"),
				control.getDouble("angle"), control.getDouble("ax"), control.getDouble("ay"), control.getDouble("delta"), a);
		Particle p2 = new Particle(control.getDouble("x0"), control.getDouble("y0"), control.getDouble("v0"),
				control.getDouble("angle"), control.getDouble("ax"), control.getDouble("ay"), control.getDouble("delta"), b);
		System.out.println(p1);
		*/
	
	Particle p1;
	Particle p2;

	public void initialize() {

		p1 = new Particle(control.getDouble("x0"), control.getDouble("y0"), control.getDouble("v0"),
				control.getDouble("angle1"), control.getDouble("ax"), control.getDouble("ay"), control.getDouble("delta"));
		p2 = new Particle(control.getDouble("x0"), control.getDouble("y0"), control.getDouble("v0"),
				control.getDouble("angle2"), control.getDouble("ax"), control.getDouble("ay"), control.getDouble("delta"));
		p1.getT().color= Color.green;
		p2.getT().color = Color.magenta;
		p1.getT().clear();
		p2.getT().clear();
		frame.clearDrawables();
		frame.addDrawable(p1);
		frame.addDrawable(p1.getT());
		frame.addDrawable(p2);
		frame.addDrawable(p2.getT());	
		frame.setPreferredMinMax(-5, 75, -5, 50);
	}

	protected void doStep() {
		if(p1.getY()>= 0) {
			p1.step();
		}
		if(p2.getY() >= 0) {
			p2.step();
		}
	}
	public void reset() {
		control.setValue("x0", 0);
		control.setValue("y0", 0);
		control.setValue("v0", 30);
		control.setValue("angle1", 60);
		control.setValue("angle2", 30);
		control.setValue("ax", 0);
		control.setValue("ay", -10);
		control.setValue("delta", .1);	
	}
	
	//			ArrayList<Particle> list = new ArrayList<Particle>();
	//			
	//			list.add(new Particle(3.14, 5.69));
	//			
	//			list.get(0).set


	//dostep
	//make this recursive
	//			if(p.getY() >= 0) {
	//				p.stepX();
	//				p.stepY();
	//				p.setXY(p.getX(), p.getY());
	//				t.addPoint(p.getX(), p.getY()); 
	//			}

	//initialize
	//		p.setV0(control.getDouble("v0"));
	//		p.setAngle(control.getDouble("angle"));
	//		p.setvX(control.getDouble("v0") * Math.cos(Math.toRadians(control.getDouble("angle"))));
	//		p.setvY(control.getDouble("v0") * Math.sin(Math.toRadians(control.getDouble("angle"))));
	//		p.setX(control.getDouble("x0"));
	//		p.setY(control.getDouble("y0"));
	//		p.setAx(control.getDouble("ax"));
	//		p.setAy(control.getDouble("ay"));
	//		p.setDelta(control.getDouble("delta t"));
	//		plist.get(i).setX(control.getDouble("x0"));
	//		plist.get(i).setY(control.getDouble("y0"));
	//		plist.get(i).setV0(control.getDouble("v0"));
	//		plist.get(i).setAngle(control.getDouble("angle") + i);
	//		plist.get(i).setvX(control.getDouble("v0") * Math.cos(Math.toRadians(control.getDouble("angle")+i)));
	//		plist.get(i).setvY(control.getDouble("v0") * Math.sin(Math.toRadians(control.getDouble("angle")+i)));
	//		plist.get(i).setAx(control.getDouble("ax"));
	//		plist.get(i).setAy(control.getDouble("ay"));
	//		plist.get(i).setDelta(control.getDouble("delta t"));
	//			
	//		plist.get(i).setXY(plist.get(i).getX(), plist.get(i).getY());
	//		p.setXY(p.getX(), p.getY());
	//		p.pixRadius=5;


	public static void main(String[] args) {

		SimulationControl.createApp(new Test());
	}






}
