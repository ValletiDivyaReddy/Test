package com.cg.em.service;

import java.util.Comparator;
import java.time.LocalDate;

import com.cg.em.exception.EventManagementException;
import com.cg.em.model.Event;

public class DateScheduledComparator implements Comparator<Event> {

	@Override
	public int compare(Event o1, Event o2) {
		LocalDate firstDate = o1.getDateScheduled();
		LocalDate otherDate = o2.getDateScheduled();
		if (firstDate.isAfter(otherDate))
			return -1;
		else if (firstDate.isBefore(otherDate))
			return 1;
		else
			return 0;

	}

}
