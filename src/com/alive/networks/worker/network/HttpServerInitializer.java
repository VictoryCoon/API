package com.alive.networks.worker.network;import io.netty.channel.ChannelInitializer;import io.netty.channel.ChannelPipeline;import io.netty.channel.socket.SocketChannel;import io.netty.handler.codec.http.HttpContentCompressor;import io.netty.handler.codec.http.HttpRequestDecoder;import io.netty.handler.codec.http.HttpResponseEncoder;import io.netty.handler.ssl.SslHandler;import io.netty.handler.timeout.ReadTimeoutHandler;import javax.net.ssl.SSLEngine;import com.alive.networks.config.ProcessConfig;public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {	private boolean isSecure = false;	public HttpServerInitializer(boolean isSecure) {		this.isSecure = isSecure;	}	@Override	public void initChannel(SocketChannel ch) throws Exception {		ChannelPipeline p = ch.pipeline();		// Uncomment the following line if you want HTTPS		if (isSecure) {			SSLEngine engine = HttpServerSslContextFactory.getServerContext().createSSLEngine();			engine.setUseClientMode(false);			engine.setNeedClientAuth(false);			engine.setWantClientAuth(false);			p.addLast("ssl", new SslHandler(engine));		}        p.addLast("readTimeoutHandler", new ReadTimeoutHandler(ProcessConfig.RECEIVER_READ_TIMEOUT_SEC));		p.addLast("decoder", new HttpRequestDecoder());		p.addLast("encoder", new HttpResponseEncoder());		p.addLast("deflater", new HttpContentCompressor());		p.addLast("handler", new HttpServerHandler());	}}