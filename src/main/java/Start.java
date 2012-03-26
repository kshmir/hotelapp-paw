

import java.io.File;

import org.apache.catalina.startup.Tomcat;

public class Start {
	public static void main(final String[] args) throws Exception {

		final String webappDirLocation = "src/main/webapp/";
		final Tomcat tomcat = new Tomcat();

		// The port that we should run on can be set into an environment
		// variable
		// Look for that variable and default to 8080 if it isn't there.
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		tomcat.setPort(Integer.valueOf(webPort));

		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();

		// final Server server = new Server();
		// final SocketConnector connector = new SocketConnector();
		// connector.setMaxIdleTime(1000 * 60 * 60);
		// connector.setSoLingerTime(-1);
		// Integer port;
		// try {
		// port = Integer.valueOf(System.getenv("PORT"));
		// } catch (final Exception e) {
		// port = 8080;
		// }
		// connector.setPort(port);
		// server.setConnectors(new Connector[] { connector });
		// final WebAppContext bb = new WebAppContext();
		// bb.setServer(server);
		// bb.setContextPath("/");
		// bb.setWar("src/main/webapp");
		// server.addHandler(bb);
		// try {
		// System.out.println(">>> STARTING EMBEDDED JETTY SERVER, "
		// + "PRESS ANY KEY TO STOP");
		// server.start();
		// while (System.in.available() == 0) {
		// Thread.sleep(5000);
		// }
		// server.stop();
		// server.join();
		// } catch (final Exception e) {
		// e.printStackTrace();
		// System.exit(100);
		// }
	}
}