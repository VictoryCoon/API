package com.alive.networks.worker.network;

import java.util.concurrent.ThreadFactory;

public class HttpThreadFactory implements ThreadFactory {
	private int idx = 0;
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, "Receiver-" + idx++);
	}
}