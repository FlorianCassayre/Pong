package me.cassayre.florian.Pong.gui.displayers;

import java.awt.Dimension;
import java.awt.Point;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.gui.Gui;
import me.cassayre.florian.Pong.gui.element.displayers.BasicGameElement;
import me.cassayre.florian.Pong.gui.element.displayers.CPUElement;

public class MainMenu extends Gui {

	public MainMenu(Pong pong) {
		super(pong);
		
		Dimension size = pong.getWindow().getContentPane().getSize();
		elements.add(new BasicGameElement(pong, new Point(size.width / 2 - 100, size.height / 2 - 125), new Dimension(200, 150)));
		elements.add(new CPUElement(pong, new Point(size.width / 2 - 100, size.height / 2 + 75), new Dimension(200, 100)));
	}

}
