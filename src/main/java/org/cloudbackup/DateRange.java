package org.cloudbackup;

import java.util.Date;

public class DateRange {

	private Date min;
	private Date max;

	public DateRange(Date min, Date max) {
		this.min = min;
		this.max = max;
	}

	public Date getMax() {
		return max;
	}

	public Date getMin() {
		return min;
	}

	public boolean isInRange(Date date) {
		return min.before(date) && max.after(date) || min.equals(date) || max.equals(date);
	}

}
