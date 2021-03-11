package com.cg.em.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.em.exception.EventManagementException;
import com.cg.em.model.Event;

public interface EventDAO {
	String add(Event event) throws EventManagementException;

	boolean delete(String id) throws EventManagementException;

	Event get(String id) throws EventManagementException;

	List<Event> getAllEventsInLocation(String location) throws EventManagementException;

	List<Event> getAllEventsOnMentionedDate(LocalDate dateMentioned) throws EventManagementException;

	List<Event> getAll() throws EventManagementException;;

	boolean update(Event event) throws EventManagementException;

	void persist() throws EventManagementException;
}