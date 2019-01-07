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
public class Utility {

    public static String generateTitleWithSquarColor(String title, Color color) {

        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        String result = "<html>"
                + title + " "
                + "<span style=\"color:"
                + "rgb(" + r + ", " + g + ", " + b + ")"
                + "\">&#9632;</span></html>";

        return result;
    }

}
