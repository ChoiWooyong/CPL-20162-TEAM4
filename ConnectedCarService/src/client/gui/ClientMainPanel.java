package client.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.OtherCar;
import common.CarAttribute;
import common.gui.MyPanel;

public class ClientMainPanel extends JPanel implements Runnable {

	private String serv_ip;
	private OtherCar car; 
	
	public ClientMainPanel(CarAttribute attr, String serv_ip) {
		
		setLayout(null);
		
		this.serv_ip = serv_ip;

		JButton btnFinded = new JButton("Allow to be finded");
		btnFinded.setBounds(706, 177, 139, 23);
		add(btnFinded);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(706, 389, 139, 23);
		add(btnNewButton_1);
		
		setVisible(true);
		
		//car = new OtherCar(attr);
		
		//new Thread(this).run();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			car.startConnectedCar_Client(serv_ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
