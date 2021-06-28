class R {
    static double rnd(double a, double b) {
        return a + Math.random() * (b - a);
    }

    static Vector2 vrndxy(double[] xb, double[] yb) {
        return new Vector2(
                R.rnd(xb[0], xb[1]),
                R.rnd(yb[0], yb[1])
        );
    }
}
