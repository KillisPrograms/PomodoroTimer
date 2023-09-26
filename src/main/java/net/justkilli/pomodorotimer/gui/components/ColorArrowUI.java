package net.justkilli.pomodorotimer.gui.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

/**
 * The ColorArrowUI class is a custom UI class that extends the BasicComboBoxUI class.
 * It provides a customized arrow button for a combo box with specified colors.
 */
public class ColorArrowUI extends BasicComboBoxUI {

    private final Color background;
    private final Color shadow;
    private final Color darkShadow;
    private final Color highlight;

    /**
     * Creates a new ColorArrowUI object with the specified colors.
     *
     * @param background  the background color of the arrow
     * @param shadow      the shadow color of the arrow
     * @param darkShadow  the dark shadow color of the arrow
     * @param highlight   the highlight color of the arrow
     */
    public ColorArrowUI(Color background, Color shadow, Color darkShadow, Color highlight) {
        this.background = background;
        this.shadow = shadow;
        this.darkShadow = darkShadow;
        this.highlight = highlight;
    }
    @Override
    protected JButton createArrowButton() {
        return new BasicArrowButton(
            BasicArrowButton.SOUTH,
            background, shadow,
            darkShadow, highlight);
    }
}
