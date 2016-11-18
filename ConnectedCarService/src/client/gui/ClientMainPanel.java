package client.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import client.OtherCar;
import common.CarAttribute;
import common.Environment;
import common.MapDataFetcher;
import common.Point;
import common.gui.ImagePanel;

import javax.swing.ImageIcon;

public class ClientMainPanel extends JPanel implements Runnable {

	private boolean isDebug = false;

	private String serv_ip;
	private OtherCar car;

	private ImagePanel mapPanel;
	private int mapMode = 1;  // 1 : Cur Position, 2 : Whole Route, 3 : Zoom Route 
	private Point destPoint;

	private JButton btnSetDest;
	private JToggleButton tglbtnONOFF;
	private JLabel lblConnectedCar;

	public ClientMainPanel(CarAttribute attr, String serv_ip, boolean isDebug) throws IOException {

		this.isDebug = isDebug;

		setLayout(null);
		setSize(1020, 722);
		setBackground(Color.WHITE);

		btnSetDest = new JButton("");
		btnSetDest.setIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/SetDst.png")));
		btnSetDest.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnSetDest.setBounds(83, 616, 240, 57);
		btnSetDest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String destMsg = JOptionPane.showInputDialog("Input your destination");

				System.out.println(destMsg);
				if(destMsg.equals("1")) //Kyungpook North gate
					destPoint = new Point(35.892461, 128.609228);
				else if(destMsg.equals("2")) // Kyungpook Main gate
					destPoint = new Point(35.885136, 128.614203);
				else if(destMsg.equals("3")) // Daegu city hall
					destPoint = new Point(35.871379, 128.601800);
				else if(destMsg.equals("4")) // Daegu airport
					destPoint = new Point(35.899019, 128.639006);
				else if(destMsg.equals("5")) // Exercise park
					destPoint = new Point(35.864444, 128.631835);
				mapMode = 2;
			}
		});
		add(btnSetDest);

		tglbtnONOFF = new JToggleButton("");
		tglbtnONOFF.setSelectedIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/ON.png")));
		tglbtnONOFF.setIcon(new ImageIcon(ClientMainPanel.class.getResource("/common/gui/OFF.png")));
		tglbtnONOFF.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		tglbtnONOFF.setBounds(360, 616, 240, 57);
		tglbtnONOFF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tglbtnONOFF.isSelected()) {
					new Thread(new CarManager()).start();
				}
			}
		});
		add(tglbtnONOFF);

		lblConnectedCar = new JLabel("Connected Car : None");
		lblConnectedCar.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		lblConnectedCar.setBounds(666, 630, 264, 24);
		add(lblConnectedCar);

		car = new OtherCar(attr);

		if (isDebug) {
			car.setCurPos(new Point(35.892441, 128.609169));

		} else {
			// Update current position
			new Thread(car).start();
		}

		mapPanel = new ImagePanel(MapDataFetcher.getCurImage(car.getCurPos(), car.getAttr().getNum()));
		mapPanel.setBounds(0, 0, 1020, 600);
		add(mapPanel);
		mapPanel.setVisible(true);

		// Thread for Update Map Image
		new Thread(this).start();
		
		setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedImage img = null;
			// Update Map Image
			while (true) {
				switch(mapMode) {
				case 1:
					img = MapDataFetcher.getCurImage(car.getCurPos(), car.getAttr().getNum());
					break;
				case 2:
					img = MapDataFetcher.getRouteImage(car.getCurPos(), destPoint, car.getAttr().getNum());
					Thread.sleep(3000);
					mapMode = 3;
					break;
				case 3:
					img = MapDataFetcher.getRouteCurImage(car.getCurPos(), car.getCurPos(), destPoint, car.getAttr().getNum());
					break;
				}
				mapPanel.updateImage(img);
				mapPanel.updateUI();
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
