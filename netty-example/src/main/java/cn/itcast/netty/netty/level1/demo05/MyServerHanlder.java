package cn.itcast.netty.netty.level1.demo05;

import java.util.Date;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MyServerHanlder extends ChannelHandlerAdapter{

	/*
	 * channelAction 
	 * 
	 * channel 通道
	 * action  活跃的
	 * 
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 * 
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().localAddress().toString()+" channelActive");
		// 通知您已经链接上客户端
		String str = "您已经开启与服务端链接"+" "+new Date()+" "+ctx.channel().localAddress() + "\r\n";
		ctx.writeAndFlush(str);
	}
	
	/*
	 * channelInactive
	 * 
	 * channel 	通道
	 * Inactive 不活跃的
	 * 
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 * 
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().localAddress().toString()+" channelInactive");
	}
	
	/*
	 * channelRead
	 * 
	 * channel 通道
	 * Read    读
	 * 
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
	 * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
	 * 
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//注意此处已经不需要手工解码了
		System.out.println(new Date()+" "+msg);
		//通知您已经链接上客户端
		// 这里msg会被客户端与服务端连续拼接从而会引起长度越界异常
		String str = "服务端收到："+new Date()+" "+ msg + "\r\n";
		ctx.writeAndFlush(str);

	}
	
	/*
	 * channelReadComplete
	 * 
	 * channel  通道
	 * Read     读取
	 * Complete 完成
	 * 
	 * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
	 * ctx.flush()
	 * 
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush() ;
	}
	
	/*
	 * exceptionCaught
	 * 
	 * exception	异常
	 * Caught		抓住
	 * 
	 * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 * 
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n"+cause.getMessage());
	}
}
