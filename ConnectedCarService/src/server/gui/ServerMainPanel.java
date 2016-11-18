package server.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.MyCar;

import common.CarAttribute;
import common.MapDataFetcher;
import common.Point;
import common.gui.ImagePanel;

public class ServerMainPanel extends JPanel implements Runnable {
	
	private MyCar car; 
	private ImagePanel mapPanel;
	private JButton btnSetDest;
	private JButton btnFind;
	private JLabel lblConnectedCar;
	
	public ServerMainPanel(CarAttribute attr, boolean isDebug) throws IOException{
		
		setLayout(null);
		
		setSize(1020, 722);
		
		btnSetDest = new JButton("Set Destination");
		btnSetDest.setActionCommand("1");
		btnSetDest.setBounds(81, 564, 220, 57);
		add(btnSetDest);
		
		btnFind = new JButton("");
		btnFind.setActionCommand("2");
		btnFind.setBounds(358, 564, 240, 57);
		add(btnFind);
		
		lblConnectedCar = new JLabel("Connected Car : None");
		lblConnectedCar.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblConnectedCar.setBounds(664, 578, 264, 24);
		add(lblConnectedCar);
		
		setVisible(true);
		
		car = new MyCar(attr);
		if (isDebug) {
			car.setCurPos(new Point(35.892441,128.609169));
			
		} else {
			new Thread(car).start();
		}
		
		mapPanel = new ImagePanel(MapDataFetcher.getCurImage(car.getCurPos(), car.getAttr().getNum()));
		mapPanel.setBounds(0, 0, 1008, 550);
		
		add(mapPanel);
		
		//new Thread(this).run();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) arg0.getSource();
		char command = btn.getActionCommand().charAt(0);
		
		switch(command) {
		case 1:  // Set Destination
			
			break;
		case 2:  // Find Car
			if (btn.isSelected()) {  // ON
				
			} else {  // OFF
				// Close safely
			}
			break;
		}
		
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
