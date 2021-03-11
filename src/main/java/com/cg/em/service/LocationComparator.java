package com.cg.em.service;

import java.util.Comparator;
import java.time.LocalDate;

import com.cg.em.exception.EventManagementException;
import com.cg.em.model.Event;

public class LocationComparator implements Comparator<Event> {

	@Override
	public int compare(Event o1, Event o2) {
		String firstLocation = o1.getLocation();
		String otherLocation = o2.getLocation();

		return firstLocation.compareToIgnoreCase(otherLocation);
	}

}
