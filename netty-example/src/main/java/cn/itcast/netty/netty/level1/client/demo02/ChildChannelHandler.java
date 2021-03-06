package cn.itcast.netty.netty.level1.client.demo02;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("报告");
		System.out.println("信息：客户端与服务端建立通道链接，下面开始加载管道信息处理");
		System.out.println("报告完毕");

		// 于行的半包粘包处理、String类型解码、String类型编码、消息处理
		ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
		ch.pipeline().addLast(new StringDecoder());
		ch.pipeline().addLast(new StringEncoder());
		ch.pipeline().addLast(new MyClientHanlder());
	}
}
