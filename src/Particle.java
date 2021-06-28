import java.awt.*;
import java.awt.geom.Ellipse2D;

class Particle {
    double r, m, life;
    Vector2 p, v, a;

    Particle(double r, double m, double life, Vector2 p, Vector2 v, Vector2 a) {
        this.r = r;
        this.m = m;
        this.life = life;
        this.p = p;
        this.v = v;
        this.a = a;
    }

    void applyforce(Vector2 f) {
        a.add(f);
    }

    boolean isdead() {
        return life < 0.0;
    }

    void blip(double t, double w, double h) {
        double rndcent = R.rnd(0.0, 1000.0);
        if (rndcent > 111.0 && rndcent < 111.1) {
            p.rndxy(new double[]{0.0, w}, new double[]{0.0, h});
        } else {
            p.add(v);
            v.add(a);
        }
        life -= t;
    }

    void render(Graphics2D g2) {
        g2.setPaint(new Color(24, 202, 230, 128));
        Ellipse2D ce = new Ellipse2D.Double(p.x - r / 2, p.y - r / 2, r, r);
        g2.fill(ce);
    }
}