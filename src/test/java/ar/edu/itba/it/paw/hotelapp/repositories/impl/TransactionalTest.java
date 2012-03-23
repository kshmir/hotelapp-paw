package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import ar.edu.itba.it.paw.db.ConnectionDispatcher;

public class TransactionalTest {

	private ConnectionDispatcher dispatcher;

	public TransactionalTest() {
		this.dispatcher = ConnectionDispatcher.getTestDispatcher();
	}

	public ConnectionDispatcher getDispatcher() {
		return this.dispatcher;
	}

}
