package me.cassayre.florian.Pong.game;

import java.awt.Point;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.utils.Direction;

public class ComputerPlayer extends Thread {
	private final Pong pong;
	private final Racket racket;
	private Ball ball;

	private final int X_MIN;
	private final int Y_MIN;
	private final int X_MAX;
	private final int Y_MAX;

	public ComputerPlayer(Pong pong, Racket racket) {
		this.pong = pong;
		this.racket = racket;
		try {
			this.ball = (Ball) pong.getGameLoop().getBall().clone();
		} catch (CloneNotSupportedException e) {
			this.ball = null;
			e.printStackTrace();
		}

		this.X_MIN = 80;
		this.X_MAX = (int) (pong.getWindow().getContentPane().getSize().getWidth() - 80);
		this.Y_MIN = 50;
		this.Y_MAX = (int) (pong.getWindow().getContentPane().getSize().getHeight() - 50);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pong.getWindow().setSecondUp(false);
			pong.getWindow().setSecondDown(false);
			// The ball is going to him
			int placement = getPlacement();
			if(racket.getLocation().y > placement + 10) {
				pong.getWindow().setSecondDown(true);
			} else if(racket.getLocation().y < placement - 10) {
				pong.getWindow().setSecondUp(true);
			} else {
				// Well placed
			}
		}
	}

	private int getPlacement() {
		try {
			this.ball = (Ball) pong.getGameLoop().getBall().clone();
		} catch (CloneNotSupportedException e) {
			this.ball = null;
			e.printStackTrace();
		}
		for(int i = 0; i < 10000; i++) {
			Point nextLoc = ball.nextLocation();
			if (nextLoc.y - 5 < Y_MIN || nextLoc.y + 5 > Y_MAX) { // Up and down
				ball.move(Direction.VERTICAL, 0);
			} else if (nextLoc.x < X_MIN) { // Left racket
				ball.move(Direction.HORIZONTAL, 0);
			} else if (nextLoc.x + 5 > X_MAX) { // Right racket
				return ball.getLocation().y - 50;
			} else {
				ball.move(null, 0);
			}
		}
		return pong.getWindow().getContentPane().getSize().height / 2 - 50;
	}
}
