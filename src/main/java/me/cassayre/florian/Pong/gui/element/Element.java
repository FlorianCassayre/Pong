package me.cassayre.florian.Pong.gui.element;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.window.Paintable;

public abstract class Element implements Paintable {

	protected final Pong pong;
	protected boolean visible = true;
	protected final Dimension dimensions;
	protected final Point location;

	public Element(Pong pong, Point location, Dimension dimensions) {
		this.pong = pong;
		this.dimensions = dimensions;
		this.location = location;
	}

	public final void show() {
		this.visible = true;
	}

	public final void hide() {
		this.visible = false;
	}

	public final boolean isVisible() {
		return this.visible;
	}

	public final Dimension getDimensions() {
		return dimensions;
	}

	public final Point getLocation() {
		return location;
	}

	public abstract void paint(Graphics g);

	public abstract boolean onClick();
}
