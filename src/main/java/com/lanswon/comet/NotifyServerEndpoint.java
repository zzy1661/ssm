package com.lanswon.comet;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发布订阅
 * @author czzhu
 * @date 2017-09-14
 */
@ServerEndpoint("/notify/{channel}/{userid}")
public class NotifyServerEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotifyServerEndpoint.class);
	
	//静态变量,记录当前在线连接数
	private static int onlineCount = 0;
	//oncurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<NotifyServerEndpoint> notifyServerEndpointSet = new CopyOnWriteArraySet<>();
	
	private Session session;
	
	@OnOpen
	public void onOpen(Session _session,@PathParam("channel") String channel,@PathParam("userid") String userid){
		LOGGER.info("Connected ... [sessionid : {},channel : {},userid : {}] " ,_session.getId(),channel,userid);
		this.session = _session;
		notifyServerEndpointSet.add(this);
		addOnlineCount();
		LOGGER.info("有新连接加入！当前在线人数为：{}",getOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String message,Session _session,@PathParam("channel") String channel,@PathParam("userid") String userid){
		String currentSessionId = _session.getId();
		LOGGER.info("receive message from [sessionid : {} , channel : {},userid : {}]",message,currentSessionId,channel,userid);
		
		//群发消息
		for(NotifyServerEndpoint item : notifyServerEndpointSet){
			try {
				if(!item.session.getId().equals(currentSessionId)){
					item.sendMessage(message);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				continue;
			}
		}
	}
	
	@OnError
	public void onError(Throwable t){
		LOGGER.error("error : {}" , t.getMessage());
	
	}
	@OnClose
	public void onClose(Session session, CloseReason reason,@PathParam("channel") String channel) {
		LOGGER.info(String.format("channel:{} Session %s closed because of %s",channel,session.getId(), reason));
		notifyServerEndpointSet.remove(this);
		subOnlineCount();
		LOGGER.info("有一连接关闭！当前在线人数为:{}",getOnlineCount());
	} 
	
	/**
	 * 发送消息
	 * @param message
	 */
	public void sendMessage(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
	}
	
	public static synchronized int getOnlineCount() {
	    return onlineCount;
	}
	
	public static synchronized void addOnlineCount(){
		NotifyServerEndpoint.onlineCount++;
	}
	public static synchronized void subOnlineCount(){
		NotifyServerEndpoint.onlineCount--;
	}
}
