package net.justkilli.pomodorotimer.gui.components;

import javax.swing.*;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class ColorArrowUI extends BasicComboBoxUI {

    private final Color background;
    private final Color shadow;
    private final Color darkShadow;
    private final Color highlight;

    public ColorArrowUI(Color background, Color shadow, Color darkShadow, Color highlight) {
        this.background = background;
        this.shadow = shadow;
        this.darkShadow = darkShadow;
        this.highlight = highlight;
    }
    @Override protected JButton createArrowButton() {
        return new BasicArrowButton(
            BasicArrowButton.SOUTH,
            background, shadow,
            darkShadow, highlight);
    }

}
