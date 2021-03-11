package com.cg.em.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cg.em.exception.EventManagementException;
import com.cg.em.model.Event;

public class EventDAOJDBCImpl implements EventDAO {
	public static final String DATA_STORE_FILE_NAME = "eventmanagement.dat";

	private Map<String, Event> events;

	public EventDAOJDBCImpl() throws EventManagementException {
		File file = new File(DATA_STORE_FILE_NAME);

		if (!file.exists()) {
			events = new TreeMap<>();
		} else {
			try (ObjectInputStream fin = new ObjectInputStream(new FileInputStream(DATA_STORE_FILE_NAME))) {

				Object obj = fin.readObject();

				if (obj instanceof Map) {
					events = (Map<String, Event>) obj;
				} else {
					throw new EventManagementException("Not a valid DataStore");
				}
			} catch (IOException | ClassNotFoundException exp) {
				throw new EventManagementException("Loading Data Failed");
			}
		}
	}

	@Override
	public String add(Event event) throws EventManagementException {
		String id = null;
		if (event != null) {
			id = event.getId();
			events.put(id, event);
		}
		return id;
	}

	@Override
	public boolean delete(String id) throws EventManagementException {
		boolean isDeleted = false;
		if (id != null) {
			events.remove(id);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public Event get(String id) throws EventManagementException {

		return events.get(id);
	}

	@Override
	public List<Event> getAll() throws EventManagementException {
		return new ArrayList<Event>(events.values());
	}

	@Override
	public boolean update(Event event) throws EventManagementException {
		boolean isUpdated = false;
		if (event != null) {
			String id = event.getId();
			events.replace(id, event);
		}
		return isUpdated;
	}

	@Override
	public void persist() throws EventManagementException {
		try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(events);
		} catch (IOException exp) {
			throw new EventManagementException("Saving data failed" + exp.getMessage());
		}

	}

	@Override
	public List<Event> listAllEvents(String location) throws EventManagementException {

		return new ArrayList<Event>(events.values());
	}

	@Override
	public List<Event> listAllEvents(LocalDate dateScheduled) throws EventManagementException {

		return new ArrayList<Event>(events.values());
	}

}
