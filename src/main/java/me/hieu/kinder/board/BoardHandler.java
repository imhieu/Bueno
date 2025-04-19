package me.hieu.kinder.board;

import lombok.Getter;
import lombok.Setter;
import me.hieu.kinder.util.CC;
import me.hieu.kinder.util.GradientUtil;
import me.hieu.kinder.util.TasksUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardHandler {

    private String currentTitle, currentFooter;
    private List<String> titles, footers;

    public BoardHandler(){
        setupTitles();
        setupFooters();
        animateTitle();
        animateFooter();
    }

    private void setupTitles() {
        titles = new ArrayList<>();
        Color[] fireColors = {
                new Color(255, 255, 0),    // Yellow
                new Color(255, 165, 0),    // Orange
                new Color(255, 69, 0)      // Red
        };
        titles.add(createFireEffect("SMP", fireColors));
        titles.add(createFireEffect("S  ", fireColors));
        titles.add(createFireEffect("SM ", fireColors));
        titles.add(createFireEffect("SMP", fireColors));
        titles.add(createFireEffect("•‿•", fireColors));
        titles.add(createFireEffect("•‿•", fireColors));
        titles.add(createFireEffect("•‿•", fireColors));
        titles.add(createFireEffect("•‿•", fireColors));
        titles.add(createFireEffect("SMP", fireColors));
        titles.add(createFireEffect("  P", fireColors));
        titles.add(createFireEffect(" MP", fireColors));
        titles.add(createFireEffect("SMP", fireColors));
        titles.add(createFireEffect("(-■_■)", fireColors));
        titles.add(createFireEffect("(-■_■)", fireColors));
        titles.add(createFireEffect("(-■_■)", fireColors));
        titles.add(createFireEffect("(-■_■)", fireColors));
        titles.add(createFireEffect("2025", fireColors));
        titles.add(createFireEffect("2   ", fireColors));
        titles.add(createFireEffect("20  ", fireColors));
        titles.add(createFireEffect("202 ", fireColors));
        titles.add(createFireEffect("2025", fireColors));
        titles.add(createFireEffect("(╯°□°)╯︵ ┻━┻", fireColors));
        titles.add(createFireEffect("(╯°□°)╯︵ ┻━┻", fireColors));
        titles.add(createFireEffect("(╯°□°)╯︵ ┻━┻", fireColors));
        titles.add(createFireEffect("(╯°□°)╯︵ ┻━┻", fireColors));
        titles.add(createFireEffect("2025", fireColors));
        titles.add(createFireEffect("   5", fireColors));
        titles.add(createFireEffect("  25", fireColors));
        titles.add(createFireEffect(" 025", fireColors));
        titles.add(createFireEffect("2025", fireColors));
        Color yearStart = new Color(50, 200, 255);
        Color yearEnd = new Color(0, 255, 100);
        titles.add(GradientUtil.gradient("2025", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2   ", yearStart, yearEnd));
        titles.add(GradientUtil.gradient(" 0  ", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("  2 ", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("   5", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2  5", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("20 5", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2025", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("   5", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("  2 ", yearStart, yearEnd));
        titles.add(GradientUtil.gradient(" 0  ", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2   ", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2  5", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2 25", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("2025", yearStart, yearEnd));
        titles.add(GradientUtil.gradient("SMP SMP SMP", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP        ", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("    SMP    ", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("        SMP", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP        ", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP SMP    ", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP SMP SMP", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP SMP SMP", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP SMP SMP", new Color(0, 200, 255), new Color(200, 0, 255)));
        titles.add(GradientUtil.gradient("SMP SMP SMP", new Color(0, 200, 255), new Color(200, 0, 255)));
        currentTitle = titles.get(0);
    }

    private void setupFooters(){
        footers = new ArrayList<>();
        footers.add(CC.translate("&7/location"));
        footers.add(CC.translate("&7/track"));
        footers.add(CC.translate("&7/message"));
        footers.add(CC.translate("&7/reply"));
        footers.add(CC.translate("&7/warps"));
        footers.add(CC.translate("&7/warp"));
        footers.add(CC.translate("&7/statistics"));
        //this.footers.add(CC.translate("&7/shop"));
        footers.add(CC.translate("&7/sell"));
        //this.footers.add(CC.translate("&7/buy"));
        footers.add(CC.translate("&7/balance"));
        footers.add(CC.translate("&7/pay"));
        footers.add(CC.translate("&7/backpack"));
        footers.add(CC.translate("&7/vote"));
        //footers.add(CC.translate("&7/mobcatch"));
        footers.add(CC.translate("&7/currency"));
        currentFooter = this.footers.get(0);
    }

    private String createFireEffect(String text, Color[] fireColors) {
        StringBuilder builder = new StringBuilder();
        int length = text.length();
        int midPoint = length / 2;
        for (int i = 0; i < length; i++) {
            int distanceFromCenter = Math.abs(midPoint - i);
            Color color = interpolateFireColor(fireColors, distanceFromCenter, length);
            builder.append(toHexColor(color)).append("§l").append(text.charAt(i));
        }
        return builder.toString();
    }

    private Color interpolateFireColor(Color[] fireColors, int distance, int totalLength) {
        float ratio = (float) distance / (totalLength / 2);
        int colorIndex = Math.min(fireColors.length - 1, Math.round(ratio * (fireColors.length - 1)));
        return fireColors[colorIndex];
    }

    private String toHexColor(Color color) {
        return String.format("§x§%x§%x§%x§%x§%x§%x",
                (color.getRed() >> 4) & 0xF,
                color.getRed() & 0xF,
                (color.getGreen() >> 4) & 0xF,
                color.getGreen() & 0xF,
                (color.getBlue() >> 4) & 0xF,
                color.getBlue() & 0xF
        );
    }

    int i = 0;

    private void animateTitle(){
        TasksUtil.runTaskTimer(() -> {
            i++;
            if (i == this.titles.size()){
                i=0;
            }
            this.currentTitle = this.titles.get(i);
        }, 10L, 4L);
    }

    int j = 0;

    private void animateFooter(){
        TasksUtil.runTaskTimer(() -> {
            j++;
            if (j == this.footers.size()){
                j=0;
            }
            this.currentFooter = this.footers.get(j);
        }, 40L, 40L);
    }

}
