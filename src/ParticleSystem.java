import java.awt.*;
import java.util.ArrayList;

class ParticleSystem {
    double w, h, f;
    int n, dn;
    ArrayList<Particle> swarm;

    ParticleSystem(double w, double h, int n, int dn, double f) {
        this.w = w;
        this.h = h;
        this.n = n;
        this.dn = dn;
        this.f = f;
        swarm = new ArrayList<Particle>();
    }

    ParticleSystem(double w, double h, int n) {
        this(w, h, n, 0, 0);
    }

    long nlim(double t) {
        return Math.round(n + dn * Math.sin(f * t));
    }

    void add(Particle p, double t) {
        if (swarm.size() > nlim(t)) {
            swarm.remove(0);
        }
        swarm.add(p);
    }

    void render(double t, Graphics2D g) {
        for (int i = 0; i < swarm.size(); i++) {
            Particle p = swarm.get(i);
            if (p.isdead()) {
                swarm.remove(i);
                i -= 1;
                continue;
            }
            p.render(g);
            p.blip(t, w, h);
        }
    }
}