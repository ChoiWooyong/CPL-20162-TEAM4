package client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import client.OtherCar;
import common.CarAttribute;
import common.Environment;
import common.MapImageFetcher;
import common.gui.ImagePanel;
import javax.swing.ImageIcon;

public class ClientMainPanel extends JPanel implements Runnable {

	private String serv_ip;
	private OtherCar car; 
	
	private ImagePanel mapPanel;
	private MapImageFetcher fetcher;
	
	private JButton btnSetDest;
	private JToggleButton tglbtnONOFF;
	private JLabel lblConnectedCar;
	
	public ClientMainPanel(CarAttribute attr, String serv_ip) {

		setLayout(null);
		setSize(1008, 638);

		//mapPanel = new ImagePanel();
		//mapPanel.setBounds(0, 0, 1008, 550);
		//add(mapPanel);
		
		btnSetDest = new JButton("Set Destination");
		btnSetDest.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnSetDest.setBounds(81, 564, 217, 57);
		add(btnSetDest);
		
		tglbtnONOFF = new JToggleButton("");
		tglbtnONOFF.setSelectedIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/ON.png")));
		tglbtnONOFF.setIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/OFF.png")));
		tglbtnONOFF.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		tglbtnONOFF.setBounds(358, 564, 240, 57);
		add(tglbtnONOFF);
		
		lblConnectedCar = new JLabel("Connected Car : None");
		lblConnectedCar.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblConnectedCar.setBounds(664, 578, 264, 24);
		add(lblConnectedCar);
		
		car = new OtherCar(attr);

		fetcher = new MapImageFetcher(car);
		
		new Thread(fetcher).run();
		
		setVisible(true);
		
		new Thread(this).run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			mapPanel = new ImagePanel(fetcher.getImage());
			Thread.sleep(Environment._IMAGE_UPDATE_TIME / 3);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
