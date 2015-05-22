package me.cassayre.florian.Pong.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.utils.Direction;
import me.cassayre.florian.Pong.window.Paintable;

public class Ball implements Paintable {
	
	private final Pong pong;
	private final Point spawnAt;
	private double x;
	private double y;
	private double direction;
	private double speed = 1;
	private Random random = new Random();
	private ClassLoader classLoader = getClass().getClassLoader();
	private File basicSound = new File(classLoader.getResource("tick.wav").getFile());
	private Clip clip;
	private InputStream in;
	private AudioInputStream inputStream;
	private boolean stop = false;
	
	public Ball(Pong pong, Point location) {
		this.pong = pong;
		this.spawnAt = location;
		this.x = location.x;
		this.y = location.y;
		this.direction = random.nextInt(2) * Math.PI;
	}
	
	public Ball(Pong pong, Point location, double direction) {
		this.pong = pong;
		this.spawnAt = location;
		this.x = location.x;
		this.y = location.y;
		this.direction = direction;
	}
	
	public Point getLocation() {
		return new Point((int) Math.round(x), (int) Math.round(y));
	}
	
	public void move(Direction hit, double deviation) {
		if(this.stop) return; // Prevents the ball from moving
		if(hit == null) { // Nothing special
			x += speed * Math.cos(direction);
			y += speed * Math.sin(direction);
		} else { // Hit something
			try { // TODO
				clip = AudioSystem.getClip();
				in = new FileInputStream(basicSound);
				inputStream = AudioSystem.getAudioInputStream(basicSound);
				clip.open(inputStream);
				clip.start();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			direction = normalizeAngle(direction + deviation);
			if(hit == Direction.HORIZONTAL) { // Wall
				direction = Math.PI - direction;
			} else { // Platform
				speed += 0.1; // Increasing the speed
				direction = - direction;
			}
			x += speed * Math.cos(direction);
			y += speed * Math.sin(direction);
		}
	}
	
	private double normalizeAngle(double angle) {
	    double newAngle = angle;
	    while (newAngle <= - Math.PI) newAngle += 2 * Math.PI;
	    while (newAngle > Math.PI) newAngle -= 2 * Math.PI;
	    return newAngle;
	}
	
	public Point nextLocation() {
		return new Point((int) (x + speed * Math.cos(direction)), (int) (y + speed * Math.sin(direction)));
	}
	
	public void paint(Graphics g) {
		Point loc = this.getLocation();
		g.setColor(Color.BLACK);
		g.fillOval(loc.x - 5, loc.y - 5, 10, 10);
	}
	
	public void respawn() {
		this.x = spawnAt.x;
		this.y = spawnAt.y;
		this.speed = 1;
		this.direction = random.nextInt(2) * Math.PI - 1 + random.nextDouble() * 2;
		this.stop = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stop = false;
			}
		}).start();
	}
	
	public boolean isStopped() {
		return stop;
	}
	
	public void setStopped(boolean stop) {
		this.stop = stop;
	}
}
