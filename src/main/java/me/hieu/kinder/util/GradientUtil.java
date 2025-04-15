package me.hieu.kinder.util;

import java.awt.*;

/**
 * Author: Le Thanh Hieu
 * Date: 15/04/2025
 */

public class GradientUtil {

    public static String gradient(String text, Color start, Color end) {
        StringBuilder builder = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (length - 1);
            int red = (int) (start.getRed() + ratio * (end.getRed() - start.getRed()));
            int green = (int) (start.getGreen() + ratio * (end.getGreen() - start.getGreen()));
            int blue = (int) (start.getBlue() + ratio * (end.getBlue() - start.getBlue()));
            builder.append(toHexColor(new Color(red, green, blue)))
                    .append("§l")
                    .append(text.charAt(i));
        }
        return builder.toString();
    }

    private static String toHexColor(Color color) {
        return String.format("§x§%x§%x§%x§%x§%x§%x",
                (color.getRed() >> 4) & 0xF,
                color.getRed() & 0xF,
                (color.getGreen() >> 4) & 0xF,
                color.getGreen() & 0xF,
                (color.getBlue() >> 4) & 0xF,
                color.getBlue() & 0xF
        );
    }

}
