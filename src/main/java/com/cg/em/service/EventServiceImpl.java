package com.cg.em.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Comparator;
import com.cg.em.service.DateScheduledComparator;
import com.cg.em.service.LocationComparator;

import com.cg.em.dao.EventDAOJDBCImpl;
import com.cg.em.dao.EventDAO;
import com.cg.em.exception.EventManagementException;
import com.cg.em.model.Event;

public class EventServiceImpl implements IEventService {
	private EventDAO eventDao;

	public EventDAO getDAO() {
		return eventDao;
	}

	public EventServiceImpl() throws EventManagementException {
		eventDao = new EventDAOJDBCImpl();

	}

	private Map<String, Event> events;

	public boolean isValidId(String id) {
		Pattern idPattern = Pattern.compile("[A-Z]\\d{3}");
		Matcher idMatcher = idPattern.matcher(id);

		return idMatcher.matches();
	}

	public boolean isValidTitle(String title) {
		Pattern titlePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher titleMatcher = titlePattern.matcher(title);

		return titleMatcher.matches();
	}

	public boolean isValidDateScheduled(LocalDate DateScheduled) {
		LocalDate today = LocalDate.now();

		return today.isBefore(DateScheduled) || today.equals(DateScheduled);
	}

	public boolean isValidEvent(Event event) throws EventManagementException {
		boolean isValid = false;

		List<String> errMsgs = new ArrayList<>();

		if (!isValidId(event.getId()))
			errMsgs.add("id should start with a capital alphabet followed by 3 digits");

		if (!isValidTitle(event.getTitle()))
			errMsgs.add("Title must start with capital and must be in between 4 to 20 chars in length");

		if (!isValidDateScheduled(event.getDateScheduled()))
			errMsgs.add(" DateScheduled should not be a past date");

		if (errMsgs.isEmpty())
			isValid = true;
		else
			throw new EventManagementException(errMsgs.toString());

		return isValid;
	}

	@Override
	public String add(Event event) throws EventManagementException {
		String id = null;
		if (event != null && isValidEvent(event)) {
			id = eventDao.add(event);
		}
		return id;
	}

	@Override
	public boolean delete(String id) throws EventManagementException {
		boolean isDeleted = false;
		if (id != null && isValidId(id)) {
			eventDao.delete(id);
			isDeleted = true;
		} else {
			throw new EventManagementException("id should start with a capital alphabet followed by 3 digits ");
		}
		return isDeleted;
	}

	@Override
	public Event get(String id) throws EventManagementException {

		return eventDao.get(id);
	}

	@Override
	public List<Event> getAll() throws EventManagementException {

		return eventDao.getAll();
	}

	@Override
	public boolean update(Event event) throws EventManagementException {
		boolean isUpdated = false;
		if (event != null && isValidEvent(event)) {
			isUpdated = eventDao.update(event);
		}
		return isUpdated;
	}

	@Override
	public List<Event> listAllEvents(String location) throws EventManagementException {

		return new ArrayList<Event>(events.values());
	}

	@Override
	public List<Event> listAllEvents(LocalDate dateScheduled) throws EventManagementException {

		return new ArrayList<Event>(events.values());
	}

	@Override
	public void persist() throws EventManagementException {
		eventDao.persist();

	}

}
