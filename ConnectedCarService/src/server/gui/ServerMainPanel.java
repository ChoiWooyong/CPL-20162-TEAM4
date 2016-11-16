package server.gui;

import javax.swing.JButton;

import common.CarAttribute;
import common.gui.MyPanel;
import server.MyCar;

public class ServerMainPanel extends MyPanel {

	private MyCar car; 
	
	public ServerMainPanel(CarAttribute attr, String serv_ip) throws Exception {
		setSize(1008, 638);
		
		JButton btnNewButton_1 = new JButton("SERVER");
		btnNewButton_1.setBounds(706, 389, 139, 23);
		add(btnNewButton_1);
		
		car = new MyCar(attr);
		car.startConnectedCar_Client(serv_ip);
	}
}
