package common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GeocodeFetcher {
	// http://dev.virtualearth.net/REST/v1/Routes?wp.0=DEPATURE&wp.1=DESTINATION&key=BINGMAPSKEY
	
	private Point departure;
	private Point destination;
	
	public GeocodeFetcher(Point dep, Point dst) {
		departure = dep;
		destination = dst;
	}
	
	public URL genURL() throws MalformedURLException {
		String strURL = String.format("%soutput=xml&wp.0=%s&" + "wp.1=%s&" + "key=%s", 
				Environment.BING_ROUTE_URL, departure.toString(), destination.toString(), Environment.KEY);
		return new URL(strURL);
	}
	
	public ArrayList<Point> getGeocode() {
		HttpURLConnection urlConnect = null;
		int rspCode = -1;
		
		try {
			urlConnect = (HttpURLConnection) genURL().openConnection();
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

        	// Tree 구성
            doc.getDocumentElement().normalize();

            // 잡다한거 많이 -> Route -> RouteLeg -> ItineraryItem
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
	
	/**
	 * XML string -> Document object
	 * @param stream
	 * @return
	 * @throws Exception
	 */
	private Document parseXML(InputSource stream) {

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
