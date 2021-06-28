import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class Sketchpad extends JComponent implements MouseMotionListener, MouseListener {
    Point2D pmouse, q;
    double dx, dy;
    Paint bg, fg;
    double time, dt;
    ArrayList<SignalListener> siglist;
    int gstate = 0;
    Stroke ps;
    Rectangle2D xbox;
    // TODO: use particle systems

    Sketchpad() {
        super();
        q = null;
        pmouse = null;
        dx = 0.0;
        dy = 0.0;
        bg = new Color(0, 0, 0, 0);
        fg = new Color(64, 224, 208);
        time = 0.0;
        dt = 0.01;
        siglist = new ArrayList<SignalListener>();
        ps = new BasicStroke(1.5f);
        xbox = null;
    }

    void addSignalListener(SignalListener sl) {
        siglist.add(sl);
    }

    void broadcastSignal(Signal x) {
        for (SignalListener sl : siglist) {
            sl.signalReceived(x);
        }
    }

    void renderclosebox(Graphics2D g2, double x, double y, double w, double h) {
        g2.setPaint(new Color(255, 0, 0));
        Rectangle2D xrect = new Rectangle2D.Double(x, y, w, h);
        g2.draw(xrect);
        g2.setStroke(new BasicStroke(2f));
        double d = 4.0;
        Line2D ob = new Line2D.Double(x + d, y + d, x + w - d, y + h - d);
        Line2D fw = new Line2D.Double(x + d, y + h - d, x + w - d, y + d);
        g2.draw(ob);
        g2.draw(fw);
        xbox = xrect;
    }

    void renderborders(Graphics2D g2, int cw, int ch) {
        g2.setPaint(new Color(255, 128, 0));
        g2.draw(new Rectangle2D.Double(1, 1, cw - 2, ch - 2));
    }

    void filltransparently(Graphics2D g2, int cw, int ch) {
        g2.setPaint(bg);
        g2.fillRect(0, 0, cw, ch);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int cw = getWidth();
        int ch = getHeight();
        int xrw = 20, xrh = 20;
        filltransparently(g2, cw, ch);
        renderborders(g2, cw, ch);
        renderclosebox(g2, cw - xrw - 2, 2, xrw, xrh);
        g2.setPaint(fg);
        if (pmouse != null && q != null) {
            g2.setStroke(ps);
            g2.draw(new Line2D.Double(pmouse, q));
        } else if (pmouse == null && q != null) {
            g2.draw(new Ellipse2D.Double(q.getX() - 2, q.getY() - 2, 4, 4));
        }
        time += dt;
    }

    // mouse sensing
    public void mouseDragged(MouseEvent me) {
        gstate = 0;
        pmouse = q;
        q = new Point2D.Double(me.getX() + dx, me.getY() + dy);
        repaint();
    }

    public void mouseMoved(MouseEvent me) {
    }

    public void mouseClicked(MouseEvent me) {
        if (xbox != null) {
            if (xbox.contains(me.getX(), me.getY())) {
                broadcastSignal(new Signal("shutdown"));
            }
        }
        pmouse = null;
        q = new Point2D.Double(me.getX() + dx, me.getY() + dy);
        repaint();
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
        if (gstate == 0) {
            gstate = 1;
        } else if (gstate == 2) {
            gstate = 3;
        }
        q = new Point2D.Double(me.getX() + dx, me.getY() + dy);
    }

    public void mouseReleased(MouseEvent me) {
        if (gstate == 1) {
            gstate = 2;
        } else if (gstate == 3) {
            pmouse = null;
            q = null;
            String[] sigdat = new String[]{"" + me.getX(), "" + me.getY()};
            broadcastSignal(new Signal("togglesize", sigdat));
            gstate = 0;
        }
    }
}