package org.example.dto.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class DayOfWeekService {

	public LocalDate getLastWeekdayDate(LocalDate date) {
		Objects.requireNonNull(date);

		if (isWeekday(date.getDayOfWeek())) {
			return date;
		} else {
			return switch(date.getDayOfWeek()) {
				case SATURDAY -> date.minusDays(1);
				case SUNDAY -> date.minusDays(2);
				default -> throw new IllegalStateException();
			};
		}
	}

	public boolean isWeekday(DayOfWeek dayOfWeek){
		return switch (dayOfWeek) {
			case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> true;
			case SATURDAY, SUNDAY -> false;
		};
	}
}
