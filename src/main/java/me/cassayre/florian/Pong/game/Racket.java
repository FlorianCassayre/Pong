package me.cassayre.florian.Pong.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.utils.Direction;
import me.cassayre.florian.Pong.window.Paintable;

public class Racket implements Paintable {
	
	private final Pong pong;
	private final Point location;
	private final Dimension dimensions;
	private final Direction direction;
	private final int MIN;
	private final int MAX;
	private int points = 0;

	public Racket(Pong pong, Point location, Dimension dimensions, Direction direction, int min, int max) {
		this.pong = pong;
		this.location = location;
		this.dimensions = dimensions;
		this.direction = direction;
		this.MIN = min;
		this.MAX = max;
	}
	
	public void tryMoveUp() {
		if(direction == Direction.HORIZONTAL) {
			if(this.location.x + dimensions.width < MAX) this.location.x++;
		} else {
			if(this.location.y + dimensions.height < MAX) this.location.y++;
		}
	}
	
	public void tryMoveDown() {
		if(direction == Direction.HORIZONTAL) {
			if(this.location.x > MIN) this.location.x--;
		} else {
			if(this.location.y > MIN) this.location.y--;
		}
	}

	public Point getLocation() {
		return location;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(location.x, location.y, dimensions.width, dimensions.height);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
}
