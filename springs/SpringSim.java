package springs;
import java.awt.Color;
import java.util.ArrayList;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.DisplayFrame;
import projectile.Particle;
import projectile.ProjectileLauncher;

public class SpringSim extends AbstractSimulation {
	ArrayList <SpringParticle> plist = new ArrayList <SpringParticle> ();
	Circle bn = new Circle();
	DisplayFrame frame = new DisplayFrame("x", "y", "Projectile");
	double gravity;
	double down;
	double up;
	double k = control.getDouble("k");

	protected void doStep() {
		//assume one particle is 0.01m
		for (int i = 0; i < control.getDouble("number of particles"); i++) {
			up = control.getDouble("number of particles") - (i+1);
			if(i==1) down = (control.getDouble("mass")*plist.get(i).ay);
			else down = control.getDouble("k")*(control.getDouble("number of particles")-i);
			plist.get(i).step();

		}
	}
	
	public void stop() {

	}

	public void reset() {
		frame.clearDrawables();
		control.setValue("number of particles", 5);
		control.setValue("x", 0.00);
		control.setValue("y", 0.00);
		control.setValue("v0", 0.00);
		//		control.setValue("ax", 0);
		control.setValue("ay", -9.807);
		control.setValue("delta t", 0.03);	
		control.setValue("mass", 0.5);
		control.setValue("k", 0.2);
	}

	public void initialize() {
		frame.clearDrawables();
		plist.clear();
		
		double x = control.getDouble("x");
		double y = control.getDouble("y");
		
		for (int i = 0; i < control.getDouble("number of particles"); i++) {
			if(i<(control.getDouble("number of particles")/2)) {
			plist.get(i).x = x + (control.getDouble("length")/control.getDouble("number of particles"))*i; //fix this
			}
			else {
				//negative version
			}
		}
		
		for(int i = 0; i < control.getDouble("number of particles"); i++) {
			//plist.add(new SpringParticle(x, y, control.getDouble("v0"), 0, 0,
		//			control.getDouble("ay"), control.getDouble("delta t")));
			plist.get(i).setXY(x, y);
			frame.addDrawable(plist.get(i));
		}

		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
		frame.setPreferredMinMax(-10, 10, -100, 10);
	}
	public static void main(String[] args) {

		SimulationControl.createApp(new SpringSim());
	}

}
