package axoloti.piccolo.components.displays;

import axoloti.preferences.Theme;
import axoloti.abstractui.IAxoObjectInstanceView;
import java.awt.Dimension;
import java.awt.Graphics2D;
import org.piccolo2d.util.PPaintContext;

public class PVBarComponent extends PDispComponentAbstract {

    private double value;
    private double max;
    private double min;

    int height = 128;
    int width = 8;

    public PVBarComponent(double value, double min, double max, IAxoObjectInstanceView axoObjectInstanceView) {
        super(axoObjectInstanceView);
        this.max = max;
        this.min = min;
        this.value = value;
        Dimension d = new Dimension(width, height);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
    }
    int px;

    final int margin = 2;

    int ValToPos(double v) {
        return (int) (margin + ((max - v) * (height - 2 * margin)) / (max - min));
    }

    @Override
    protected void paint(PPaintContext paintContext) {
        Graphics2D g2 = paintContext.getGraphics();
        g2.setPaint(Theme.getCurrentTheme().Component_Secondary);
        g2.fillRect(0, 0, (int) getBoundsReference().width, height);
        g2.setPaint(Theme.getCurrentTheme().Component_Primary);
        g2.drawRect(0, 0, (int) getBoundsReference().width, height);
        int p = ValToPos(value);
        int p1 = ValToPos(0);
        g2.setPaint(Theme.getCurrentTheme().Component_Mid);
        g2.fillRect(margin, p, width - margin * 2, p1 - p);
    }

    @Override
    public void setValue(double value) {
        if (value > max) {
            value = max;
        }
        if (value < min) {
            value = min;
        }
        if (this.value != value) {
            this.value = value;
            repaint();
        }
    }

    public void setMinimum(double min) {
        this.min = min;
    }

    public double getMinimum() {
        return min;
    }

    public void setMaximum(double max) {
        this.max = max;
    }

    public double getMaximum() {
        return max;
    }
}