public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double GRAVITATIONAL_CONSTANT = 6.67e-11;

	public Planet(double xP, double yP, double xV,
		double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + 
			(p.yyPos - yyPos) * (p.yyPos - yyPos));
	}

	public double calcForceExertedBy(Planet p) {
		double distance = calcDistance(p);
		return GRAVITATIONAL_CONSTANT * mass * p.mass / (distance * distance);
	}

	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double sum = 0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				sum += calcForceExertedByX(p);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		int sum = 0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				sum += calcForceExertedByY(p);
			}
		}
		return sum;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel + aX * dt;
		yyVel = yyVel + aY * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}

	public void draw() {
		String path = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, path);
	}
}