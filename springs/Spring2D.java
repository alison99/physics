package springs;
import java.awt.Color;
import java.util.ArrayList;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.frames.DisplayFrame;
import projectile.Particle;
import projectile.ProjectileLauncher;

public class Spring2D extends AbstractSimulation {
	ArrayList <SpringParticle> plist = new ArrayList <SpringParticle> ();
	Circle bn = new Circle();
	DisplayFrame frame = new DisplayFrame("x", "y", "Projectile");
	double rest;
	double d;
	double t;

	protected void doStep() {

		for (int i = 0; i < control.getDouble("number of particles"); i++) {
			
			if(i==control.getDouble("number of particles")-1) d = 0;
			else {
			d = Math.sqrt((plist.get(i+1).x-plist.get(i).x)*(plist.get(i+1).x-plist.get(i).x) +
					(plist.get(i+1).y-plist.get(i).y)*(plist.get(i+1).y-plist.get(i).y));
			}
			
			if(d==0) {
			plist.get(i).y = Math.sin((2*Math.PI*t)/control.getDouble("period"));
			}
			
			if(d!=0) {
				plist.get(i).ay = (plist.get(i).k*(d-rest)*((plist.get(i+1).y-plist.get(i).y)/d))/plist.get(i).mass;
			}

			plist.get(i).step();
		}
		t++;
	}

	public void stop() {

	}

	public void reset() {
		frame.clearDrawables();
		control.setValue("number of particles", 10);
		control.setValue("x", 0.00);
		control.setValue("y", 0.00);
		control.setValue("total mass", 50);
		control.setValue("yTop", 5);
		control.setValue("delta t", 0.03);	
		control.setValue("k", 0.2);
		control.setValue("length", 20);
		control.setValue("period", 25);
	}

	public void initialize() {
		frame.clearDrawables();
		plist.clear();

		double x = control.getDouble("x");
		double mass = control.getDouble("total mass")/control.getInt("number of particles");
		rest = control.getDouble("length")/control.getDouble("number of particles");

		for(int i = 0; i < control.getDouble("number of particles"); i++) {
			plist.add(new SpringParticle(x + rest*i, control.getDouble("y"), mass,
					control.getDouble("k"), control.getDouble("delta t"), control.getDouble("length")));
			
			plist.get(i).setXY(x + rest*i, plist.get(i).y);

			frame.addDrawable(plist.get(i));
		
		}

		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
		frame.setPreferredMinMax(-5, 25, -10, 10);
	}
	public static void main(String[] args) {

		SimulationControl.createApp(new Spring2D());
	}

}

