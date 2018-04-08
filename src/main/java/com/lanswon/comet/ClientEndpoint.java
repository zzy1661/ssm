package com.lanswon.comet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.websocket.ClientEndpoint
public class ClientEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientEndpoint.class);

	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		LOGGER.info("Connected ... {} ", session.getId());
//		try {
			this.session = session;
//			session.getBasicRemote().sendText("start");
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			LOGGER.info("Received .... {}", message);
			String userInput = bufferRead.readLine();
			return userInput;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		LOGGER.info(String.format("Session %s close because of %s", session.getId(), closeReason));
	}

	public void send(String message) {
		this.session.getAsyncRemote().sendText(message);
	}

	public void close() throws IOException {
		if (this.session.isOpen()) {
			this.session.close();
		}
	}

	public static void main(String[] args) throws Exception {
		// Auto-generated method stub
		WebSocketContainer conmtainer = ContainerProvider.getWebSocketContainer();
		ClientEndpoint client = new ClientEndpoint();
		conmtainer.connectToServer(client, new URI("ws://localhost:8080/TejianSupervision/notify"));

		int turn = 0;
		while (turn++ < 10) {
			client.send("send text: " + turn);
			Thread.sleep(1000);
		}
		client.close();
	}
}
