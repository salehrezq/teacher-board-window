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

    public static String[] extractClientAddressSegments(String fullAddress) {

        String[] address_segments = new String[2];
        int i;
        String str = fullAddress;
        int strLength = str.length();
        for (i = 0; i < strLength; i++) {
            if (str.charAt(i) == '/') {
                break;
            }
        }

        String name = str.substring(0, i);
        String IP = str.substring(i + 1);

        address_segments[0] = name;
        address_segments[1] = IP;

        return address_segments;
    }

}
