package ar.edu.itba.it.paw.hotelapp.web.util;

public class PathResolver {
	public static int getResourceIdFromPath(final String path) throws Exception {
		return Integer.valueOf(path.substring(1));
	}
}
