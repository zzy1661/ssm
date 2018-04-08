package com.lanswon.util;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import com.lanswon.comet.ClientEndpoint;

public class WebSocketUtil {
	public static void sendMsg(String url, String channel, String userID, String content) throws Exception {
		System.out.println("websocket url  start : " + url);
		
		WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
		ClientEndpoint client = new ClientEndpoint();
		if (!url.startsWith("ws://")) {
			url = "ws://" + url;
		}
		conmtainer.connectToServer(client, new URI(url + "/" + channel + "/" + userID));
		client.send(content);
		System.out.println("websocket url end : " + new URI(url + "/" + channel + "/" + userID).toString());
		client.close();
	}
}
