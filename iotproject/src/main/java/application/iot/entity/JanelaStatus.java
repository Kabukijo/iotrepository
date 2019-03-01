package application.iot.entity;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class JanelaStatus {

	private IMqttClient client;

	private static final String TOPIC = "janela05";

	public Janela janela;

	public JanelaStatus() {

		janela = new Janela();
		try {
			if (client == null || !client.isConnected()) {

				String subscriberId = UUID.randomUUID().toString();
				client = new MqttClient("tcp://iot.eclipse.org:1883", subscriberId);
				MqttConnectOptions options = new MqttConnectOptions();

				options.setAutomaticReconnect(true);
				options.setCleanSession(true);
				options.setConnectionTimeout(10);
				client.connect(options);

				CountDownLatch receivedSignal = new CountDownLatch(10);
				client.subscribe(TOPIC, (topic, msg) -> {
					byte[] payload = msg.getPayload();
					setStatus(payload);
					System.out.println(payload);
					receivedSignal.countDown();
				});
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Janela verificarJanela() throws Exception {
		return janela;
	}

	private void setStatus(byte[] payload) {

		Integer status = Integer.valueOf(new String(payload));
		if (status == 1) {
			janela.setStatus("fechada");
		} else {
			janela.setStatus("aberta");
		}
	}

	public class Janela {

		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
}
