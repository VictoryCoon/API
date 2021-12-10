package com.alive.networks.worker.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.BindException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alive.networks.config.ProcessConfig;

public class HttpServer implements Runnable{

	private Logger logger = LoggerFactory.getLogger(HttpServer.class);
	private int port;
	private int threadCnt;

	public HttpServer(int port, int threadCnt) {
		this.port = port;
		this.threadCnt = threadCnt;
	}

	@Override
	public void run() {
		logger.info("START THREAD " + Thread.currentThread().getName());

		ServerBootstrap sb = null;

		while(Thread.currentThread().isInterrupted() == false){
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			EventLoopGroup workerGroup = new NioEventLoopGroup(this.threadCnt,  new HttpThreadFactory());

			try{
				sb = new ServerBootstrap();
				sb.group(bossGroup,workerGroup)
				.channel(NioServerSocketChannel.class)
				.childOption(ChannelOption.SO_LINGER, 0)
				.childHandler(new HttpServerInitializer(ProcessConfig.SSL_USE_FLAG));

				Channel ch = sb.bind(this.port).sync().channel();
				logger.info("Http Listen Port : " + this.port);
				ch.closeFuture().sync();
			} catch(InterruptedException e){
				break;
			} catch(Exception e){
				if(e instanceof BindException){
					logger.error("Cant not Load Listen Port[" + this.port + "] is Already Used");
				}
				else{
					logger.error("", e);
				}
			}finally{
				if(bossGroup !=null){
					bossGroup.shutdownGracefully();
				}
				if(workerGroup != null){
					workerGroup.shutdownGracefully();
				}
				sb = null;
			}
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				break;
			}
		} // end of while

		logger.info("END THREAD " + Thread.currentThread().getName());
    }
}

