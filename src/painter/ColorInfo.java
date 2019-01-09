/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.awt.Color;

/**
 *
 * @author Saleh
 */
public class ColorInfo {

    private String colorName;
    private Color color;

    public ColorInfo(String colorName, Color color) {
        this.colorName = colorName;
        this.color = color;
    }

    public String getColorName() {
        return this.colorName;
    }

    public Color getColor() {
        return this.color;
    }

}
