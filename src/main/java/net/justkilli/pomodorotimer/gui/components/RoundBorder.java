package net.justkilli.pomodorotimer.gui.components;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

/**
 * The RoundBorder class is a custom border implementation that creates a rounded
 * border with a specified color, thickness, and corner radii.
 *
 * Example usage:
 * RoundBorder border = new RoundBorder(Color.RED, 2, 10);
 * component.setBorder(border);
 */
public class RoundBorder extends AbstractBorder {
    private final Color borderColor;
    private final int thickness;
    private final int cornerRadius;
    private final Insets insets;
    private final BasicStroke stroke;
    private final int strokePadding;
    private final RenderingHints renderingHints;

    /**
     * Creates a round border with the specified properties.
     *
     * @param borderColor the color of the border
     * @param thickness the thickness of the border
     * @param cornerRadius the radius of the rounded corners
     */
    public RoundBorder(Color borderColor, int thickness, int cornerRadius) {
        this.thickness = thickness;
        this.cornerRadius = cornerRadius;
        this.borderColor = borderColor;
        this.stroke = new BasicStroke(thickness);
        this.strokePadding = thickness / 2;
        this.renderingHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int padding = cornerRadius + strokePadding;
        this.insets = new Insets(padding, padding, padding, padding);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics2D = (Graphics2D) g;
        Area area = createRoundedRectangleArea(graphics2D, width, height);
        setParentBackgroundColor(c, graphics2D, area, width, height);
        graphics2D.setColor(borderColor);
        graphics2D.setStroke(stroke);
        graphics2D.draw(area);
    }

    /**
     * Creates a rounded rectangle area based on the specified parameters.
     *
     * @param graphics2D the Graphics2D object to use for drawing
     * @param width      the width of the rounded rectangle area
     * @param height     the height of the rounded rectangle area
     * @return the created rounded rectangle area as an instance of Area
     */
    private Area createRoundedRectangleArea(Graphics2D graphics2D, int width, int height) {
        int bottomLineY = height - thickness;
        RoundRectangle2D.Double roundRectangle = new RoundRectangle2D.Double(
                strokePadding,
                strokePadding,
                width - thickness,
                bottomLineY,
                cornerRadius,
                cornerRadius);

        Area area = new Area(roundRectangle);

        graphics2D.setRenderingHints(renderingHints);

        return area;
    }

    /**
     * Sets the background color of the parent component to the specified Graphics2D object.
     *
     * @param component  the component to set the parent background color for
     * @param graphics2D the Graphics2D object to use for drawing
     * @param area       the area of the component to set the parent background color for
     * @param width      the width of the component
     * @param height     the height of the component
     */
    private void setParentBackgroundColor(Component component, Graphics2D graphics2D, Area area, int width, int height) {
        Component parent = component.getParent();
        if (parent != null) {
            Color parentBackground = parent.getBackground();
            Rectangle rect = new Rectangle(0, 0, width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            graphics2D.setClip(borderRegion);
            graphics2D.setColor(parentBackground);
            graphics2D.fillRect(0, 0, width, height);
            graphics2D.setClip(null);
        }
    }
}
