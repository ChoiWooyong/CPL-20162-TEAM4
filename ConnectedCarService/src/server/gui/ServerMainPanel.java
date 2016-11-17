package server.gui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import common.CarAttribute;
import common.gui.MyPanel;
import server.MyCar;

public class ServerMainPanel extends JPanel implements Runnable {

	private MyCar car; 
	
	public ServerMainPanel(CarAttribute attr){
		
		setLayout(null);
		
		setSize(1008, 638);
		
		JButton btnNewButton_1 = new JButton("SERVER");
		btnNewButton_1.setBounds(706, 389, 139, 23);
		add(btnNewButton_1);
		
		setVisible(true);

		//car = new MyCar(attr);
		
		//new Thread(this).run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			car.startConnectedCar_Server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
