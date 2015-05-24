package me.cassayre.florian.Pong.game;

import java.awt.Dimension;
import java.awt.Point;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.utils.Direction;

public class GameLoop extends Thread {
	
	private final Pong pong;
	private final Racket racket1;
	private final Racket racket2;
	private final Ball ball;
	
	private final int X_MIN;
	private final int Y_MIN;
	private final int X_MAX;
	private final int Y_MAX;
	
	private boolean ready = false;
	
	public GameLoop(Pong pong, GameType game) {
		this.pong = pong;
		
		this.X_MIN = 80;
		this.X_MAX = (int) (pong.getWindow().getContentPane().getSize().getWidth() - 80);
		this.Y_MIN = 50;
		this.Y_MAX = (int) (pong.getWindow().getContentPane().getSize().getHeight() - 50);
		
		this.racket1 = new Racket(pong, new Point(60, (int) (pong.getWindow().getContentPane().getSize().getHeight() / 2 - 50)), new Dimension(20, 100), Direction.VERTICAL, 50, (int) (pong.getWindow().getContentPane().getSize().getHeight() - 50));
		this.racket2 = new Racket(pong, new Point((int) (pong.getWindow().getContentPane().getSize().getWidth() - 80), (int) (pong.getWindow().getContentPane().getSize().getHeight() / 2 - 50)), new Dimension(20, 100), Direction.VERTICAL, 50, (int) (pong.getWindow().getContentPane().getSize().getHeight() - 50));
		
		if(game == GameType.COMPUTER) {
			new ComputerPlayer(pong, racket2).start();
		}
		
		this.ball = new Ball(pong, new Point((int) (pong.getWindow().getContentPane().getSize().getWidth() / 2), (int) (pong.getWindow().getContentPane().getSize().getHeight() / 2)), 1);
	
		this.ready = true;
	}

	@Override
	public void run() {
		while(pong.isPlaying()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(pong.getWindow().isFirstUp()) {
				racket1.tryMoveUp();
			}
			if(pong.getWindow().isFirstDown()) {
				racket1.tryMoveDown();
			}
			
			if(pong.getWindow().isSecondUp()) {
				racket2.tryMoveUp();
			}
			if(pong.getWindow().isSecondDown()) {
				racket2.tryMoveDown();
			}
			
			Point nextLoc = ball.nextLocation();
			if(nextLoc.y - 5 < Y_MIN || nextLoc.y + 5 > Y_MAX) { // Up and down
				ball.move(Direction.VERTICAL, 0);
			} else if(nextLoc.x < X_MIN) { // Left racket
				double deviation = (double) (ball.getLocation().y - 50 - racket1.getLocation().y) * 0.01;
				if(ball.getLocation().y + 5 >= racket1.getLocation().y && ball.getLocation().y + 5 <= racket1.getLocation().y + 100) {
					ball.move(Direction.HORIZONTAL, - deviation);
				} else {
					ball.respawn();
					racket2.addPoints(1);
				}
			} else if(nextLoc.x + 5 > X_MAX) { // Right racket
				double deviation = (double) (ball.getLocation().y - 50 - racket2.getLocation().y) * 0.01;
				if(ball.getLocation().y + 5 >= racket2.getLocation().y && ball.getLocation().y + 5 <= racket2.getLocation().y + 100) {
					ball.move(Direction.HORIZONTAL, deviation);
				} else {
					ball.respawn();
					racket1.addPoints(1);
				}
			} else {
				ball.move(null, 0);
			}
		}
	}
	
	public Racket getFirstRacket() {
		return racket1;
	}
	
	public Racket getSecondRacket() {
		return racket2;
	}
	
	public Ball getBall() {
		return ball;
	}

	public boolean isReady() {
		return ready;
	}
}
