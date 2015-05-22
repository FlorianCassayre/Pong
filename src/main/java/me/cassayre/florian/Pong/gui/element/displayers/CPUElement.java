package me.cassayre.florian.Pong.gui.element.displayers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.gui.element.Element;
import me.cassayre.florian.Pong.gui.message.BasicMessage;
import me.cassayre.florian.Pong.utils.GraphicsUtils;

public class CPUElement extends Element {

	public CPUElement(Pong pong, Point location, Dimension dimensions) {
		super(pong, location, dimensions);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(206, 129, 7));
		g.fillRoundRect(this.location.x, this.location.y, this.dimensions.width, this.dimensions.height, 20, 15);
		g.setColor(Color.WHITE);
		g.drawRoundRect(this.location.x, this.location.y, this.dimensions.width, this.dimensions.height, 20, 15);
		GraphicsUtils.drawCenteredString(g, "Ordinateur", new Font("arial", 0, 24), this.location.x + this.dimensions.width / 2, this.location.y + this.dimensions.height / 2);
	}

	@Override
	public boolean onClick() {
		pong.getWindow().setCurrentMessage(new BasicMessage(pong, "Ce mode de jeu n'est pas encore disponible !"));
		return false;
	}

}
