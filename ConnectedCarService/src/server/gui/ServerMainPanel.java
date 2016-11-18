package server.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import server.MyCar;
import common.CarAttribute;
import common.Environment;
import common.MapDataFetcher;
import common.Point;
import common.gui.ImagePanel;

public class ServerMainPanel extends JPanel implements Runnable {

	private MyCar car; 

	private ImagePanel mapPanel;
	private int mapMode = 1;
	private Point destPoint;

	private JButton btnSetDest;
	private JButton btnFind;
	private JLabel lblConnectedCar;
	private JLabel speedText;

	public ServerMainPanel(CarAttribute attr, boolean isDebug) throws IOException {

		setLayout(null);
		setSize(800, 480);
		setBackground(Color.WHITE);

		btnSetDest = new JButton("");  // Set destination
		btnSetDest.setActionCommand("1");
		btnSetDest.setIcon(new ImageIcon(ServerMainPanel.class.getResource("/common/gui/SetDst.png")));
		btnSetDest.setBounds(20, 335, 240, 57);
		btnSetDest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String destMsg = JOptionPane.showInputDialog("Input your destination");

				System.out.println(destMsg);
				if(destMsg.equals("1")) // Kyungpook North gate
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

		btnFind = new JButton("");  // Find car
		btnFind.setActionCommand("2");
		btnFind.setIcon(new ImageIcon(ServerMainPanel.class.getResource("/common/gui/Find.png")));
		btnFind.setBounds(290, 335, 240, 57);
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new CarManager()).start();
			}
		});
		add(btnFind);

		lblConnectedCar = new JLabel("Connected Car : None");
		lblConnectedCar.setFont(new Font("����", Font.PLAIN, 20));
		lblConnectedCar.setBounds(560, 350, 264, 24);
		add(lblConnectedCar);

		setVisible(true);

		car = new MyCar(attr, isDebug);

		if (isDebug) {
			car.getAttr().setCurPos(new Point(35.892441,128.609169));

		} else {
			new Thread(car).start();
		}
		
		mapPanel = new ImagePanel(MapDataFetcher.getCurImage(car.getAttr().getCurPos(), car.getAttr().getNum()));
		mapPanel.setBounds(0, 0, 800, 320);
		add(mapPanel);
		
		speedText = new JLabel();
		speedText.setHorizontalAlignment(SwingConstants.RIGHT);
		speedText.setForeground(Color.DARK_GRAY);
		speedText.setFont(new Font("����", Font.BOLD, 30));
		speedText.setBounds(732, 10, 56, 54);
		mapPanel.add(speedText);
		mapPanel.setVisible(true);

		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedImage img = null;
			while(true)	{
				if (car.getSelectedIdx() != -1)
					lblConnectedCar.setText("Conntected Car : " + car.getCarInfo().get(car.getSelectedIdx()).getAttr().getNum());
				
				switch(mapMode) {
				case 1:
					img = MapDataFetcher.getCurImage(car.getAttr().getCurPos(), car.getAttr().getNum());
					break;
				case 2:
					img = MapDataFetcher.getRouteImage(car.getAttr().getCurPos(), destPoint, car.getAttr().getNum());
					mapPanel.updateImage(img);
					mapPanel.updateUI();
					Thread.sleep(Environment._IMAGE_UPDATE_TIME * 3);			
					mapMode = 3;
					break;
				case 3:
					img = MapDataFetcher.getRouteCurImage(car.getAttr().getCurPos(), car.getAttr().getCurPos(), destPoint, car.getAttr().getNum());
					break;
				}
				mapPanel.updateImage(img);
				mapPanel.updateUI();
				speedText.setText(Integer.toString(car.getCurSpeed()));
				Thread.sleep(Environment._IMAGE_UPDATE_TIME);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch b
			e.printStackTrace();
		}
	}

	private class CarManager implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				car.makeRoute(destPoint);
				car.startConnectedCar_Server();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
