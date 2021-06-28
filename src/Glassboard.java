import javax.swing.*;
import java.awt.*;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT;

public class Glassboard {
    static GraphicsConfiguration getGC() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT)) {
            System.err.println("per pixel translucency is not supported!");
            System.exit(0);
        }
        return gd.getDefaultConfiguration();
    }

    public static void main(String[] args) {
        GraphicsConfiguration gc = getGC();
        Runnable gui = new Runnable() {
            @Override
            public void run() {
                Glassframe gf = new Glassframe();
                gf.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(gui);
    }
}