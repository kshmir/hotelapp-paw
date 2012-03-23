package ar.edu.itba.it.paw.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

	Connection getConnection() throws SQLException;
}
