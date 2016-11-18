package common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MapDataFetcher {

	private static int ZOOM_LEVEL = 18;
	private static int PUSH_PIN_SHAPE = 6;

	public static BufferedImage getCurImage(Point cur, String carNum) {

		String strURL = String.format("%s%s/%s/?mapSize=1020,600&pushpin=%s;%s;%s&key=%s",
				Environment._BING_CUR_IMAGE_URL, cur.toString(), ZOOM_LEVEL, cur.toString(), PUSH_PIN_SHAPE, carNum, Environment._KEY);		

		return getImageFromURL(strURL);
	}
	
	public static BufferedImage getRouteCurImage(Point cur, Point dep, Point dst, String carNum) {

		String strURL = String.format("%s%s/%s/Routes?wp.0=%s&wp.1=%s&mapSize=1020,600&pushpin=%s;%s;%s&key=%s",
				Environment._BING_CUR_IMAGE_URL, cur.toString(), ZOOM_LEVEL, dep.toString(), dst.toString(), cur.toString(), PUSH_PIN_SHAPE, carNum, Environment._KEY);		

		return getImageFromURL(strURL);
	}
	
	public static BufferedImage getRouteImage(Point dep, Point dst, String carNum) {
		
		String strURL = String.format("%sRoutes?wp.0=%s&wp.1=%s&mapSize=1020,600&key=%s",
				Environment._BING_CUR_IMAGE_URL, dep.toString(), dst.toString(), Environment._KEY);

		return getImageFromURL(strURL);
	}

	public static ArrayList<Point> getGeocode(Point dep, Point dst) {

		String strURL = String.format("%s" + "output=xml&" + "wp.0=%s&" + "wp.1=%s&" + "key=%s", 
				Environment._BING_ROUTE_DATA_URL, dep.toString(), dst.toString(), Environment._KEY);

		HttpURLConnection urlConnect = null;
		int rspCode = -1;

		try {
			urlConnect = (HttpURLConnection) new URL(strURL).openConnection();
			rspCode = urlConnect.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rspCode == 200) {
			InputStream ist;
			InputSource is = null;

			try {
				ist = urlConnect.getInputStream();
				Reader reader = new InputStreamReader(ist, "UTF-8");
				is = new InputSource(reader);
				is.setEncoding("UTF-8");
				is.getCharacterStream().read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Document doc = parseXML(is);

			doc.getDocumentElement().normalize();

			NodeList RouteLeg = doc.getFirstChild().getLastChild().getFirstChild().getLastChild().getFirstChild().getLastChild().getChildNodes();


			ArrayList<Point> Route = new ArrayList<Point>();
			for (int i = 0; i < RouteLeg.getLength(); i++) {
				if (RouteLeg.item(i).getNodeName() == "ItineraryItem") {
					double la = Double.parseDouble(RouteLeg.item(i).getChildNodes().item(3).getFirstChild().getFirstChild().getNodeValue());
					double lg = Double.parseDouble(RouteLeg.item(i).getChildNodes().item(3).getFirstChild().getNextSibling().getFirstChild().getNodeValue());

					Route.add(new Point(la, lg));
				}
			}

			return Route;
		}

		return null;
	}
	
	private static BufferedImage getImageFromURL(String strURL) {
		HttpURLConnection urlConnect = null;
		int rspCode = -1;

		try {
			urlConnect = (HttpURLConnection) new URL(strURL).openConnection();
			rspCode = urlConnect.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (rspCode == 200) {
			try {
				BufferedImage img = ImageIO.read(urlConnect.getInputStream());
				return img;
				
			} catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	private static Document parseXML(InputSource stream) {

		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;

		try{
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

			doc = objDocumentBuilder.parse(stream);

		} catch(Exception ex){
			ex.printStackTrace();
		}       

		return doc;
	}

}
