package client.gui;

import javax.swing.JButton;

import client.OtherCar;
import common.CarAttribute;
import common.gui.MyPanel;

public class ClientMainPanel extends MyPanel {

	private OtherCar car; 
	
	public ClientMainPanel(CarAttribute attr, String serv_ip) throws Exception {
		setSize(1008, 638);

		JButton btnFinded = new JButton("Allow to be finded");
		btnFinded.setBounds(706, 177, 139, 23);
		add(btnFinded);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(706, 389, 139, 23);
		add(btnNewButton_1);
		
		car = new OtherCar(attr);
		car.startConnectedCar_Client(serv_ip);
	}
}
