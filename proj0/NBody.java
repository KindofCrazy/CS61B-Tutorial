public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);

        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble();

        Planet[] p = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            p[i] = new Planet(xP, yP, xV, yV, m, img);
        }

        return p;
    }

    public static void main(String[] args) {
        // Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planet = readPlanets(filename);

        // Drawing the Background
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // Drawing One Planet

        // Drawing All of the Planets
        for (Planet p : planet) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();

        double t = 0;
        double[] xForces = new double[planet.length];
        double[] yForces = new double[planet.length];
        while (t < T) {
            for (int i = 0; i < planet.length; i++) {
                xForces[i] = planet[i].calcNetForceExertedByX(planet);
                yForces[i] = planet[i].calcNetForceExertedByY(planet);
            }
            for (int i = 0; i < planet.length; i++) {
                planet[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planet) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        // Printing the Universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
