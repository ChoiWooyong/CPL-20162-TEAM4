package client.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import client.OtherCar;
import common.CarAttribute;
import common.Environment;
import common.MapDataFetcher;
import common.Point;
import common.gui.ImagePanel;

import javax.swing.ImageIcon;

public class ClientMainPanel extends JPanel implements Runnable, ActionListener {

	private boolean isDebug = false;
	
	private String serv_ip;
	private OtherCar car;

	private ImagePanel mapPanel;

	private JButton btnSetDest;
	private JToggleButton tglbtnONOFF;
	private JLabel lblConnectedCar;

	public ClientMainPanel(CarAttribute attr, String serv_ip, boolean isDebug) throws IOException {

		this.isDebug = isDebug;
		
		setLayout(null);
		setSize(1008, 638);

		btnSetDest = new JButton("Set Destination");
		btnSetDest.setActionCommand("1");
		btnSetDest.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnSetDest.setBounds(81, 564, 220, 57);
		add(btnSetDest);

		tglbtnONOFF = new JToggleButton("");
		tglbtnONOFF.setActionCommand("2");
		tglbtnONOFF.setSelectedIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/ON.png")));
		tglbtnONOFF.setIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/OFF.png")));
		tglbtnONOFF.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		tglbtnONOFF.setBounds(358, 564, 240, 57);
		add(tglbtnONOFF);

		lblConnectedCar = new JLabel("Connected Car : None");
		lblConnectedCar.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblConnectedCar.setBounds(664, 578, 264, 24);
		add(lblConnectedCar);
		
		// If GPS isn't connected well, It will wait
		car = new OtherCar(attr);
		
		if (isDebug) {
			car.setCurPos(new Point(35.892441, 128.609169));
			
		} else {
			new Thread(car).run();
		}
		
		mapPanel = new ImagePanel(MapDataFetcher.getCurImage(car.getCurPos(), car.getAttr().getNum()));
		mapPanel.setBounds(0, 0, 1008, 550);
		
		add(mapPanel);
		
		// Thread for Update Map Image
		new Thread(this).run();

		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) arg0.getSource();
		char command = btn.getActionCommand().charAt(0);
		
		switch(command) {
		case 1:  // Set Destination
			
			break;
		case 2:  // Connected Car ON/OFF
			if (btn.isSelected()) {  // ON
				new Thread(new CarManager()).run();
				
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
			// Update Map Image
			while (true) {
				mapPanel.updateImage(MapDataFetcher.getCurImage(car.getCurPos(), car.getAttr().getNum()));
				Thread.sleep(Environment._IMAGE_UPDATE_TIME);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private class CarManager implements Runnable {
		
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
}
