import javax.swing.*;
import java.awt.*;

class Glassframe extends JWindow implements SignalListener {
    int sw, sh;
    int minw, minh, maxw, maxh, dw, dh;
    char szstate;
    int xgap, ygap;

    void initvalues() {
        sw = 1920;
        sh = 1080;
        minw = 100;
        minh = 50;
        maxw = 1918;
        maxh = 980;
        dw = -1;
        dh = 22;
        xgap = 5;
        ygap = 63;
        szstate = 'f';
    }

    public Glassframe() {
        initvalues();
        setLocation((sw / 2 - maxw / 2) + dw, (sh / 2 - maxh / 2) + dh);
        setSize(maxw, maxh);
        setBackground(new Color(0, 0, 0, 0));
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 1));

        Sketchpad sp = new Sketchpad();
        sp.addSignalListener(this);
        addMouseMotionListener(sp);
        addMouseListener(sp);
        add(sp);
    }

    public void signalReceived(Signal x) {
        if ("togglesize".equals(x.name) && szstate == 'f') {
            setSize(minw, minh);
            setLocation(sw - minw - xgap, ygap);
            szstate = 's';
        } else if ("togglesize".equals(x.name) && szstate == 's') {
            setSize(maxw, maxh);
            setLocation((sw / 2 - maxw / 2) + dw, (sh / 2 - maxh / 2) + dh);
            szstate = 'f';
        } else if ("shutdown".equals(x.name)) {
            L.d("shutdown signal received. bye!");
            System.exit(0);
        }
    }
}
