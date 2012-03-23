package ar.edu.itba.it.paw.db.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

import ar.edu.itba.it.paw.db.ConnectionManager;

public class InMemorySQLiteConnectionManager implements ConnectionManager {

	private Connection conn = null;

	private static ConnectionManager instance = null;

	public static class AutoRollbackConnection implements Connection {
		Connection conn;

		public AutoRollbackConnection(final Connection conn)
				throws SQLException {
			this.conn = conn;
			this.conn.setAutoCommit(false);
		}

		public boolean isWrapperFor(final Class<?> iface) throws SQLException {
			return this.conn.isWrapperFor(iface);
		}

		public <T> T unwrap(final Class<T> iface) throws SQLException {
			return this.conn.unwrap(iface);
		}

		public void clearWarnings() throws SQLException {
			this.conn.clearWarnings();
		}

		public void close() throws SQLException {
			this.conn.rollback();
			this.conn.close();
		}

		public void commit() throws SQLException {
			this.conn.commit();
		}

		public Array createArrayOf(final String typeName,
				final Object[] elements) throws SQLException {
			return this.conn.createArrayOf(typeName, elements);
		}

		public Blob createBlob() throws SQLException {
			return this.conn.createBlob();
		}

		public Clob createClob() throws SQLException {
			return this.conn.createClob();
		}

		public NClob createNClob() throws SQLException {
			return this.conn.createNClob();
		}

		public SQLXML createSQLXML() throws SQLException {
			return this.conn.createSQLXML();
		}

		public Statement createStatement() throws SQLException {
			return this.conn.createStatement();
		}

		public Statement createStatement(final int resultSetType,
				final int resultSetConcurrency) throws SQLException {
			return this.conn.createStatement(resultSetType,
					resultSetConcurrency);
		}

		public Statement createStatement(final int resultSetType,
				final int resultSetConcurrency, final int resultSetHoldability)
				throws SQLException {
			return this.conn.createStatement(resultSetType,
					resultSetConcurrency, resultSetHoldability);
		}

		public Struct createStruct(final String typeName,
				final Object[] attributes) throws SQLException {
			return this.conn.createStruct(typeName, attributes);
		}

		public boolean getAutoCommit() throws SQLException {
			return this.conn.getAutoCommit();
		}

		public String getCatalog() throws SQLException {
			return this.conn.getCatalog();
		}

		public Properties getClientInfo() throws SQLException {
			return this.conn.getClientInfo();
		}

		public String getClientInfo(final String name) throws SQLException {
			return this.conn.getClientInfo(name);
		}

		public int getHoldability() throws SQLException {
			return this.conn.getHoldability();
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			return this.conn.getMetaData();
		}

		public int getTransactionIsolation() throws SQLException {
			return this.conn.getTransactionIsolation();
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return this.conn.getTypeMap();
		}

		public SQLWarning getWarnings() throws SQLException {
			return this.conn.getWarnings();
		}

		public boolean isClosed() throws SQLException {
			return this.conn.isClosed();
		}

		public boolean isReadOnly() throws SQLException {
			return this.conn.isReadOnly();
		}

		public boolean isValid(final int timeout) throws SQLException {
			return this.conn.isValid(timeout);
		}

		public String nativeSQL(final String sql) throws SQLException {
			return this.conn.nativeSQL(sql);
		}

		public CallableStatement prepareCall(final String sql)
				throws SQLException {
			return this.conn.prepareCall(sql);
		}

		public CallableStatement prepareCall(final String sql,
				final int resultSetType, final int resultSetConcurrency)
				throws SQLException {
			return this.conn.prepareCall(sql, resultSetType,
					resultSetConcurrency);
		}

		public CallableStatement prepareCall(final String sql,
				final int resultSetType, final int resultSetConcurrency,
				final int resultSetHoldability) throws SQLException {
			return this.conn.prepareCall(sql, resultSetType,
					resultSetConcurrency, resultSetHoldability);
		}

		public PreparedStatement prepareStatement(final String sql)
				throws SQLException {
			return this.conn.prepareStatement(sql);
		}

		public PreparedStatement prepareStatement(final String sql,
				final int autoGeneratedKeys) throws SQLException {
			return this.conn.prepareStatement(sql, autoGeneratedKeys);
		}

		public PreparedStatement prepareStatement(final String sql,
				final int[] columnIndexes) throws SQLException {
			return this.conn.prepareStatement(sql, columnIndexes);
		}

		public PreparedStatement prepareStatement(final String sql,
				final String[] columnNames) throws SQLException {
			return this.conn.prepareStatement(sql, columnNames);
		}

		public PreparedStatement prepareStatement(final String sql,
				final int resultSetType, final int resultSetConcurrency)
				throws SQLException {
			return this.conn.prepareStatement(sql, resultSetType,
					resultSetConcurrency);
		}

		public PreparedStatement prepareStatement(final String sql,
				final int resultSetType, final int resultSetConcurrency,
				final int resultSetHoldability) throws SQLException {
			return this.conn.prepareStatement(sql, resultSetType,
					resultSetConcurrency, resultSetHoldability);
		}

		public void releaseSavepoint(final Savepoint savepoint)
				throws SQLException {
			this.conn.releaseSavepoint(savepoint);
		}

		public void rollback() throws SQLException {
			this.conn.rollback();
		}

		public void rollback(final Savepoint savepoint) throws SQLException {
			this.conn.rollback(savepoint);
		}

		public void setAutoCommit(final boolean autoCommit) throws SQLException {
			this.conn.setAutoCommit(autoCommit);
		}

		public void setCatalog(final String catalog) throws SQLException {
			this.conn.setCatalog(catalog);
		}

		public void setClientInfo(final Properties properties)
				throws SQLClientInfoException {
			this.conn.setClientInfo(properties);
		}

		public void setClientInfo(final String name, final String value)
				throws SQLClientInfoException {
			this.conn.setClientInfo(name, value);
		}

		public void setHoldability(final int holdability) throws SQLException {
			this.conn.setHoldability(holdability);
		}

		public void setReadOnly(final boolean readOnly) throws SQLException {
			this.conn.setReadOnly(readOnly);
		}

		public Savepoint setSavepoint() throws SQLException {
			return this.conn.setSavepoint();
		}

		public Savepoint setSavepoint(final String name) throws SQLException {
			return this.conn.setSavepoint(name);
		}

		public void setTransactionIsolation(final int level)
				throws SQLException {
			this.conn.setTransactionIsolation(level);
		}

		public void setTypeMap(final Map<String, Class<?>> map)
				throws SQLException {
			this.conn.setTypeMap(map);
		}

	}

	public static ConnectionManager getConnectionManager() {
		if (instance == null) {
			instance = new InMemorySQLiteConnectionManager();
		}
		return instance;
	}

	private InMemorySQLiteConnectionManager() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (final ClassNotFoundException e) {

		}
	}

	public Connection getConnection() throws SQLException {
		if (this.conn == null) {
			this.conn = new AutoRollbackConnection(
					DriverManager.getConnection("jdbc:sqlite::memory:"));

			try {
				final File f = new File("src/test/resources/0.tables.sql");
				final BufferedReader reader = new BufferedReader(
						new FileReader(f));
				StringBuilder command = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					command.append(line).append("\n");
					if (line.contains(";")) {
						this.conn.createStatement().execute(command.toString());
						command = new StringBuilder();
					}
				}
				if (command.toString().endsWith(";")) {
					this.conn.createStatement().execute(command.toString());
				}

			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			} catch (final IOException e) {
				e.printStackTrace();
			} catch (final SQLException e) {

				e.printStackTrace();
			}

		}

		return this.conn;
	}
}
