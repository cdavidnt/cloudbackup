package org.cloudbackup.filters;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.cloudbackup.BackupImage;
import org.cloudbackup.DateRange;
import org.cloudbackup.Dates;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class RangeDateImage implements Function<Collection<BackupImage>, Collection<BackupImage>>, Comparator<BackupImage> {
	private DateRange dateRange;

	public RangeDateImage(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public RangeDateImage(Date referenceDate, int daysBefore, int daysAfter) {
		//@formatter:off
		this(new DateRange(	Dates.at(referenceDate).addDays(-daysBefore).midday().date(), 
										Dates.at(referenceDate).addDays(daysAfter).midday().date()));
		//@formatter:on
	}

	public Collection<BackupImage> apply(Collection<BackupImage> input) {
		List<BackupImage> backups = Lists.newArrayList();
		for (BackupImage backupImage : input) {
			Date backupDate = Dates.at(backupImage.getCreatedAt()).midday().date();
			if (dateRange.isInRange(backupDate)) {
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
