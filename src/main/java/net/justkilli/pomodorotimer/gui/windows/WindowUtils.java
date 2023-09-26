package net.justkilli.pomodorotimer.gui.windows;

import net.justkilli.pomodorotimer.gui.components.ColorArrowUI;
import net.justkilli.pomodorotimer.gui.design.BorderDesign;
import net.justkilli.pomodorotimer.gui.design.ColorDesign;
import net.justkilli.pomodorotimer.gui.design.FontDesign;

import javax.swing.*;

/**
 * WindowUtils class provides utility methods for designing GUI components.
 */
public class WindowUtils {

    /**
     * Applies a color design to a given component. Sets the background color and text color of the component based on the provided color design.
     *
     * @param component    the component to apply the color design to
     * @param colorDesign  the color design to apply
     */
    private static void applyColorDesign(JComponent component, ColorDesign colorDesign) {
        component.setBackground(colorDesign.compBackground());
        component.setForeground(colorDesign.text());
    }

    /**
     * Applies the specified font design to the provided component.
     *
     * @param component   the component to apply the font design to
     * @param fontDesign  the font design to apply
     */
    private static void applyFontDesign(JComponent component, FontDesign fontDesign) {
        component.setFont(fontDesign.text());
    }

    /**
     * Applies the specified border design to the given component.
     *
     * @param component     the component to apply the border design to
     * @param borderDesign  the border design to apply
     */
    private static void applyBorderDesign(JComponent component, BorderDesign borderDesign) {
        component.setBorder(borderDesign.components());
    }

    /**
     * Design a button with the specified color, font, and border design.
     *
     * @param button        the button to be designed
     * @param colorDesign   the color design to be applied to the button
     * @param fontDesign    the font design to be applied to the button
     * @param borderDesign  the border design to be applied to the button
     */
    public static void designButton(JButton button, ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        applyColorDesign(button, colorDesign);
        button.setForeground(colorDesign.buttonText());
        applyFontDesign(button, fontDesign);
        button.setFont(fontDesign.buttons());
        applyBorderDesign(button, borderDesign);
        button.setBorder(borderDesign.buttons());
    }

    /**
     * Sets the color, font, and border design for the provided JComboBox. It also applies a custom UI for the combo box arrow.
     *
     * @param comboBox     the JComboBox to be designed
     * @param colorDesign  the ColorDesign instance containing color configurations
     * @param fontDesign   the FontDesign instance containing font configurations
     * @param borderDesign the BorderDesign instance containing border configurations
     */
    public static void designComboBox(JComboBox<?> comboBox, ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        applyColorDesign(comboBox, colorDesign);
        applyFontDesign(comboBox, fontDesign);
        applyBorderDesign(comboBox, borderDesign);

        comboBox.setUI(new ColorArrowUI(colorDesign.compBackground(), colorDesign.compBackground(), colorDesign.text(), colorDesign.compBackground()));
    }

    /**
     * This method applies various design elements to a JLabel component including color, font, and border design.
     *
     * @param label The JLabel component to be designed.
     * @param colorDesign The ColorDesign object that contains color information.
     * @param fontDesign The FontDesign object that contains font information.
     * @param borderDesign The BorderDesign object that contains border information.
     */
    public static void designLabel(JLabel label, ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        applyColorDesign(label, colorDesign);
        label.setFont(fontDesign.timer());
        applyBorderDesign(label, borderDesign);
    }

    /**
     * Designs the base panel with the given color, font, and border designs.
     *
     * @param panel         the JPanel to be designed
     * @param colorDesign   the color design to be applied
     * @param fontDesign    the font design to be applied
     * @param borderDesign  the border design to be applied
     */
    public static void designBasePanel(JPanel panel, ColorDesign colorDesign, FontDesign fontDesign, BorderDesign borderDesign) {
        panel.setBackground(colorDesign.background());
    }
}
