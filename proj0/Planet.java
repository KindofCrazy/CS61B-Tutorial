public class Planet {
    private static final double G = 6.67e-11; // Gravitational constant

    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the body (for
                               // example, jupiter.gif)

    public Planet(double xP, double yP, double xV,
            double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet b) {
        double r = this.calcDistance(b);
        return G * this.mass * b.mass / (r * r);
    }

    public double calcForceExertedByX(Planet b) {
        double dx = b.xxPos - this.xxPos;
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        return f * dx / r;
    }

    public double calcForceExertedByY(Planet b) {
        double dy = b.yyPos - this.yyPos;
        double r = this.calcDistance(b);
        double f = this.calcForceExertedBy(b);
        return f * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netX = 0;
        for (Planet b : allPlanets) {
            if (this.equals(b))
                continue;
            netX += this.calcForceExertedByX(b);
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netY = 0;
        for (Planet b : allPlanets) {
            if (this.equals(b))
                continue;
            netY += this.calcForceExertedByY(b);
        }
        return netY;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
