package org.cloudbackup.filters;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.cloudbackup.BackupImage;
import org.cloudbackup.DateUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class RangeDateImage implements Function<Collection<BackupImage>, Collection<BackupImage>>, Comparator<BackupImage> {
	private Date minDate;
	private Date maxDate;

	public RangeDateImage(Date minDate, Date maxDate) {
		this.minDate = DateUtils.midday(minDate);
		this.maxDate = DateUtils.midday(maxDate);
	}

	public RangeDateImage(Date referenceDate, int daysBefore, int daysAfter) {
		this.minDate = DateUtils.midday(DateUtils.addDays(referenceDate, -daysBefore));
		this.maxDate = DateUtils.midday(DateUtils.addDays(referenceDate, daysAfter));
	}

	public Collection<BackupImage> apply(Collection<BackupImage> input) {
		List<BackupImage> backups = Lists.newArrayList();
		for (BackupImage backupImage : input) {
			Date backupDate = DateUtils.midday(backupImage.getCreatedAt());
			if (minDate.before(backupDate) && maxDate.after(backupDate) || minDate.equals(backupDate) || maxDate.equals(backupDate)) {
				backups.add(backupImage);
			}
		}

		Collections.sort(backups, this);

		if (backups.isEmpty()) {
			return Lists.newArrayList();
		} else {
			return Lists.newArrayList(backups.get(0));
		}
	}

	public int compare(BackupImage o1, BackupImage o2) {
		return o1.getCreatedAt().compareTo(o2.getCreatedAt());
	}

}
