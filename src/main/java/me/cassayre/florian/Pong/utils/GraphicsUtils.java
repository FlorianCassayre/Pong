package me.cassayre.florian.Pong.utils;

import java.awt.Font;
import java.awt.Graphics;

public final class GraphicsUtils {
	private GraphicsUtils() {} // No instantiation

	public static void drawCenteredString(Graphics g, String s, Font font, int XPos, int YPos) {
		g.setFont(font);
		int width = -(int) (g.getFontMetrics().getStringBounds(s, g).getWidth() / 2);
		int height = (int) (g.getFontMetrics().getStringBounds(s, g).getHeight() / 2);
		g.drawString(s, width + XPos, height + YPos);
	}
}
