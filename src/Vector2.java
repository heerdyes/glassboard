class Vector2 {
    double x, y;

    Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double mag() {
        return Math.sqrt(x * x + y * y);
    }

    Vector2 normalized() {
        double vm = mag();
        return new Vector2(x / vm, y / vm);
    }

    Vector2 minus(Vector2 v2) {
        return new Vector2(x - v2.x, y - v2.y);
    }

    Vector2 plus(Vector2 v2) {
        return new Vector2(x + v2.x, y + v2.y);
    }

    void nrm() {
        double vm = mag();
        if (vm > 0.0) {
            x /= vm;
            y /= vm;
        }
    }

    void mul(double f) {
        this.x *= f;
        this.y *= f;
    }

    void add(Vector2 s) {
        this.x += s.x;
        this.y += s.y;
    }

    void rndxy(double[] xb, double[] yb) {
        x = R.rnd(xb[0], xb[1]);
        y = R.rnd(yb[0], yb[1]);
    }

    Vector2 dup() {
        return new Vector2(x, y);
    }
}