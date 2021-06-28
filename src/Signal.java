public class Signal {
    String name;
    String[] data;

    Signal(String nm, String[] dt) {
        name = nm;
        data = dt;
    }

    Signal(String nm) {
        this(nm, null);
    }
}