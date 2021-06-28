class ParticleEmitter {
    Vector2 p, v, a;
    double mm, r, life;

    ParticleEmitter(Vector2 p, Vector2 v, Vector2 a, double mm, double r, double life) {
        this.p = p;
        this.v = v;
        this.a = a;
        this.mm = mm;
        this.r = r;
        this.life = life;
    }

    ParticleEmitter(double px, double py) {
        this(new Vector2(px, py), new Vector2(0, 0), new Vector2(0, 0), 0.0, 1.0, 100.0);
    }

    void setpos(double x, double y) {
        p.x = x;
        p.y = y;
    }

    boolean isdead() {
        return life < 0.0;
    }

    Particle pgen(double pr, double t, double lifetime) {
        double[] mr = new double[]{-mm, mm};
        return new Particle(
                pr,
                1.0,
                lifetime,
                p.dup(),
                v.plus(R.vrndxy(mr, mr)),
                new Vector2(0.0, 0.0)
        );
    }

    void blip(double t) {
        p.add(v);
        v.add(a);
        life -= t;
    }
}