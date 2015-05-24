package me.cassayre.florian.Pong.window;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.gui.Gui;
import me.cassayre.florian.Pong.gui.message.BasicMessage;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private final Pong pong;
	private Gui currentGui = null;
	private BasicMessage currentMessage = null;
	private int fps = 0;
	private boolean isFirstUp = false;
	private boolean isFirstDown = false;
	private boolean isSecondUp = false;
	private boolean isSecondDown = false;
	
	public Window(Pong pong) {
		super("Pong");
		this.pong = pong;
		
		this.setContentPane(new DrawPane(pong));
		this.setSize(new Dimension(1000, 600));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addKeyListener(new KeyListener() {
			// TODO Check gamemode
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyChar() == 'z') {
					isFirstDown = true;
					
				} else if(key.getKeyChar() == 's') {
					isFirstUp = true;
				} else if(key.getKeyChar() == 'o') {
                    isSecondDown = true;
                } else if(key.getKeyChar() == 'l') {
                    isSecondUp = true;
                }
			}

			@Override
			public void keyReleased(KeyEvent key) {
				if(key.getKeyChar() == 'z') {
					isFirstDown = false;
				} else if(key.getKeyChar() == 's') {
					isFirstUp = false;
				} else if(key.getKeyChar() == 'o') {
                    isSecondDown = false;
                } else if(key.getKeyChar() == 'l') {
                    isSecondUp = false;
                }
			}

			@Override
			public void keyTyped(KeyEvent key) {
			}
			
		});
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
	
	public Gui getCurrentGui() {
		return this.currentGui;
	}
	
	public void setCurrentGui(Gui gui) {
		this.currentGui = gui;
	}

	public BasicMessage getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(BasicMessage currentMessage) {
		this.currentMessage = currentMessage;
	}

	public Pong getPong() {
		return pong;
	}

	public boolean isFirstUp() {
		return isFirstUp;
	}

	public boolean isFirstDown() {
		return isFirstDown;
	}

	public boolean isSecondUp() {
		return isSecondUp;
	}

	public boolean isSecondDown() {
		return isSecondDown;
	}

	public void setSecondUp(boolean isSecondUp) {
		this.isSecondUp = isSecondUp;
	}

	public void setSecondDown(boolean isSecondDown) {
		this.isSecondDown = isSecondDown;
	}

	public void setFirstUp(boolean isFirstUp) {
		this.isFirstUp = isFirstUp;
	}

	public void setFirstDown(boolean isFirstDown) {
		this.isFirstDown = isFirstDown;
	}
}
