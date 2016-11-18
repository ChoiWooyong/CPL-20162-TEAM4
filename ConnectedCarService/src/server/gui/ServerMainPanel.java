package server.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.MyCar;
import common.CarAttribute;
import common.Environment;
import common.MapDataFetcher;
import common.Point;
import common.gui.ImagePanel;

public class ServerMainPanel extends JPanel implements Runnable, ActionListener {
	
	private MyCar car; 
	
	private ImagePanel mapPanel;
	
	private JButton btnSetDest;
	private JButton btnFind;
	private JLabel lblConnectedCar;
	
	public ServerMainPanel(CarAttribute attr, boolean isDebug) throws IOException{
		
		setLayout(null);
		setSize(1020, 722);
		setBackground(Color.WHITE);
		
		btnSetDest = new JButton("");//set destination
		btnSetDest.setActionCommand("1");
		btnSetDest.setIcon(new ImageIcon(ServerMainPanel.class.getResource("/common/gui/SetDst.png")));
		btnSetDest.setBounds(83, 616, 240, 57);
		add(btnSetDest);
		
		btnFind = new JButton("");//find car
		btnFind.setActionCommand("2");
		btnFind.setIcon(new ImageIcon(ServerMainPanel.class.getResource("/common/gui/Find.png")));
		btnFind.setBounds(360, 616, 240, 57);
		add(btnFind);
		
		lblConnectedCar = new JLabel("Connected Car : None");
		lblConnectedCar.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblConnectedCar.setBounds(666, 630, 264, 24);
		add(lblConnectedCar);
		
		setVisible(true);
		
		car = new MyCar(attr);
		
		if (isDebug) {
			car.setCurPos(new Point(35.892441,128.609169));
			
		} else {
			new Thread(car).start();
		}
		
		mapPanel = new ImagePanel(MapDataFetcher.getCurImage(car.getCurPos(), car.getAttr().getNum()));
		mapPanel.setBounds(0, 0, 1020, 600);
		add(mapPanel);
		mapPanel.setVisible(true);
		
		//new Thread(this).run();
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		JButton btn = (JButton) arg0.getSource();
		char command = btn.getActionCommand().charAt(0);
		
		switch(command) {
		case 1:  // Set Destination
			
			break;
		case 2:  // Find Car
			new Thread(new CarManager()).start();	
			
			break;
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			while(true)
			{
				mapPanel.updateImage(MapDataFetcher.getCurImage(car.getCurPos(),car.getAttr().getNum()));
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
				car.startConnectedCar_Server();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
