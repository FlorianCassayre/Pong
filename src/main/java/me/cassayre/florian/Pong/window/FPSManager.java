package me.cassayre.florian.Pong.window;

import me.cassayre.florian.Pong.Pong;

public class FPSManager extends Thread {
	
	private final Pong pong;
	
	public FPSManager(Pong pong) {
		this.pong = pong;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pong.getWindow().setFps(pong.getDrawerLoop().getIterations());
		}
	}
}
