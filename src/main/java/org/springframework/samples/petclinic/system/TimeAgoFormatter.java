package org.springframework.samples.petclinic.system;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class TimeAgoFormatter {

	public static String format(LocalDateTime pastTime) {
		if (pastTime == null) {
			return "Never";
		}

		LocalDateTime now = LocalDateTime.now();
		long duration = ChronoUnit.SECONDS.between(pastTime, now);

		if (duration < 60) {
			return "Just now";
		}
		if (duration < 3600) {
			long minutes = TimeUnit.SECONDS.toMinutes(duration);
			return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
		}
		if (duration < 86400) {
			long hours = TimeUnit.SECONDS.toHours(duration);
			return hours + (hours == 1 ? " hour ago" : " hours ago");
		}
		if (duration < 604800) { // 7 days
			long days = TimeUnit.SECONDS.toDays(duration);
			return days + (days == 1 ? " day ago" : " days ago");
		}

		long weeks = ChronoUnit.WEEKS.between(pastTime, now);
		if (weeks < 4) {
			return weeks + (weeks == 1 ? " week ago" : " weeks ago");
		}

		long months = ChronoUnit.MONTHS.between(pastTime, now);
		if (months < 12) {
			return months + (months == 1 ? " month ago" : " months ago");
		}

		long years = ChronoUnit.YEARS.between(pastTime, now);
		return years + (years == 1 ? " year ago" : " years ago");
	}

}
