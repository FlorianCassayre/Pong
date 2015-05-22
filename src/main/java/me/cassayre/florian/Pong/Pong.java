package me.cassayre.florian.Pong;

import me.cassayre.florian.Pong.game.GameLoop;
import me.cassayre.florian.Pong.gui.displayers.MainMenu;
import me.cassayre.florian.Pong.gui.displayers.ScoreGui;
import me.cassayre.florian.Pong.window.DrawerLoop;
import me.cassayre.florian.Pong.window.FPSManager;
import me.cassayre.florian.Pong.window.Window;

public class Pong {
	
	private final Window window = new Window(this);
	private final GameLoop gameLoop = new GameLoop(this);
	private final DrawerLoop drawerLoop = new DrawerLoop(this);
	private final FPSManager fpsManager = new FPSManager(this);
	private boolean isPlaying = true;
	
	public Pong() {
		drawerLoop.start();
		fpsManager.start();
		
		window.setCurrentGui(new MainMenu(this));
	}
	
	public Window getWindow() {
		return window;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public DrawerLoop getDrawerLoop() {
		return drawerLoop;
	}

	public FPSManager getFpsManager() {
		return fpsManager;
	}

	public void startGame() {
		gameLoop.start();
	}
	
	public void resetGame() {
		gameLoop.interrupt();
		window.setCurrentGui(new ScoreGui(this));
	}
	
	public static void main(String[] args) {
		new Pong();
	}

}
