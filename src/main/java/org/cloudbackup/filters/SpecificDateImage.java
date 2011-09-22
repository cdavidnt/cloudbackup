package org.cloudbackup.filters;

import java.util.Collection;
import java.util.Date;

import org.cloudbackup.BackupImage;
import org.cloudbackup.Dates;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class SpecificDateImage implements Function<Collection<BackupImage>, Collection<BackupImage>> {

	private Date specificDate;

	public SpecificDateImage(Date specificDate) {
		this.specificDate = specificDate;
	}

	public Collection<BackupImage> apply(Collection<BackupImage> input) {
		for (BackupImage backupImage : input) {
			if (midday(backupImage.getCreatedAt()).equals(midday(specificDate))) {
				return Lists.newArrayList(input);
			}
		}
		return Lists.newArrayList();
	}

	protected Date midday(Date date) {
		return Dates.at(date).midday().date();
	}
}
