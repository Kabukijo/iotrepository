package application.iot.repository;

import org.springframework.stereotype.Component;

import application.iot.entity.JanelaStatus;
import application.iot.entity.JanelaStatus.Janela;

@Component
public class GasRepo {

	private JanelaStatus janelaStatus;

	public Janela verificarJanela() {
		try {
			if (janelaStatus == null) {
				janelaStatus = new JanelaStatus();
			}
			return janelaStatus.verificarJanela();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}