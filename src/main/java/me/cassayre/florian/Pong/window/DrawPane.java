package me.cassayre.florian.Pong.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import me.cassayre.florian.Pong.Pong;
import me.cassayre.florian.Pong.gui.Gui;
import me.cassayre.florian.Pong.gui.element.Element;
import me.cassayre.florian.Pong.gui.message.BasicMessage;
import me.cassayre.florian.Pong.utils.GraphicsUtils;

@SuppressWarnings("serial")
public class DrawPane extends JPanel {

private final Pong pong;
	
	public DrawPane(final Pong pong) {
		this.pong = pong;
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				BasicMessage message = pong.getWindow().getCurrentMessage();
				Gui gui = pong.getWindow().getCurrentGui();
				if(message != null) { // Message boxes have maximum priority
					if(e.getX() >= 200 && e.getY() >= 200 && e.getX() < pong.getWindow().getContentPane().getSize().width - 200 && e.getY() < pong.getWindow().getContentPane().getSize().height - 200) {
						pong.getWindow().setCurrentMessage(null);
					}
				} else if(gui != null) { // Gui have high priority
					for(Element el : gui.getElements()) {
						if(e.getX() >= el.getLocation().x && e.getY() >= el.getLocation().y && e.getX() < el.getDimensions().getWidth() + el.getLocation().x && e.getY() < el.getDimensions().getHeight() + el.getLocation().y) {
							if(el.onClick())
								pong.getWindow().setCurrentGui(null);
							return;
						}
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(pong.getGameLoop() == null || !pong.getGameLoop().isReady()) return;
		drawBackground(g);
		drawRackets(g);
		drawPoints(g);
		drawBall(g);
		drawCurrentGui(g);
		
		drawFPS(g);
		
		drawCurrentMessage(g);
	}
	
	private void drawBackground(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 600);
		
		g.setColor(Color.GREEN);
		g.fillRect(50, 50, this.getSize().width - 100, this.getSize().height - 100);
		
		g.setColor(Color.WHITE);
		g.drawLine(79, 0, 79, this.getSize().height);
		g.drawLine(this.getSize().width - 80, 0, this.getSize().width - 80, this.getSize().height);
		g.drawLine(this.getSize().width / 2, 0, this.getSize().width / 2, this.getSize().height);
		
		g.setColor(Color.BLACK);
		g.drawRect(50, 50, this.getSize().width - 100, this.getSize().height - 100);
	}
	
	private void drawFPS(Graphics g) {
		g.setColor(Color.GRAY);
		g.setFont(new Font("arial", 0, 24));
		g.drawString(pong.getWindow().getFps() + " FPS", 900, 20);
	}
	
	private void drawPoints(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("arial", 0, 24));
		GraphicsUtils.drawCenteredString(g, pong.getGameLoop().getFirstRacket().getPoints() + ":" + pong.getGameLoop().getSecondRacket().getPoints(), new Font("arial", 0, 32), this.getSize().width / 2, 20);
	}
	
	private void drawRackets(Graphics g) {
		pong.getGameLoop().getFirstRacket().paint(g);
		pong.getGameLoop().getSecondRacket().paint(g);
	}
	
	private void drawBall(Graphics g) {
		pong.getGameLoop().getBall().paint(g);
	}
	
	private void drawCurrentGui(Graphics g) {
		Gui gui = pong.getWindow().getCurrentGui();
		if(gui == null) return;
		for(Element element : gui.getElements()) {
			element.paint(g);
		}
	}
	
	private void drawCurrentMessage(Graphics g) {
		BasicMessage message = pong.getWindow().getCurrentMessage();
		if(message == null) return;
		message.paint(g);
	}
}
