package me.cassayre.florian.Pong.gui;

import java.util.ArrayList;
import java.util.List;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.gui.element.Element;

public abstract class Gui {

	protected final Pong pong;
	protected final List<Element> elements = new ArrayList<Element>();
	
	public Gui(Pong pong) {
		this.pong = pong;
	}

	public final List<Element> getElements() {
		return elements;
	}
}
