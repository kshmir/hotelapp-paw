package ar.edu.itba.it.paw.hotelapp.web;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class Start {
	public static void main(final String[] args) throws Exception {
		final Server server = new Server();
		final SocketConnector connector = new SocketConnector();
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });
		final WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/HotelApp");
		bb.setWar("src/main/webapp");
		server.addHandler(bb);
		try {
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, "
					+ "PRESS ANY KEY TO STOP");
			server.start();
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}