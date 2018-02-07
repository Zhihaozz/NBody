public class NBody {

	public static double readRadius(String path) {
		In in = new In(path);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int planetsNumber = in.readInt();
		Planet[] planets = new Planet[planetsNumber];
		in.readDouble();
		for (int i = 0; i < planetsNumber; i++) {
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
				in.readDouble(), in.readString());
		}
		return planets;
	 }

	 public static void main(String[] args) {
	 	double T = Double.parseDouble(args[0]);
	 	double dt = Double.parseDouble(args[1]);
	 	String filename = args[2];
	 	double radius = readRadius(filename);
	 	Planet[] planets = readPlanets(filename);

	 	StdDraw.setScale(-radius, radius);
	 	StdDraw.picture(0, 0, "images/starfield.jpg");

	 	for (Planet p : planets) {
	 		p.draw();
	 	}

	 	StdDraw.enableDoubleBuffering();
	 	double time = 0;

	 	StdAudio.play("audio/2001.mid");

	 	while (time != T) {
	 		double[] xForces = new double[planets.length];
	 		double[] yForces = new double[planets.length];
	 		for (int i = 0; i < planets.length; i++) {
	 			xForces[i] = planets[i].calcNetForceExertedByX(planets);
	 			yForces[i] = planets[i].calcNetForceExertedByY(planets);
	 		}
	 		for (int i = 0; i < planets.length; i++) {
	 			planets[i].update(dt, xForces[i], yForces[i]);
	 		}

	 		StdDraw.clear();

	 		StdDraw.setScale(-radius, radius);
	 		StdDraw.picture(0, 0, "images/starfield.jpg");

	 		for (Planet p : planets) {
	 			p.draw();
	 		}

	 		StdDraw.show();
	 		StdDraw.pause(10);

	 		time += dt;
	 	}

	 	StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

	 }
}