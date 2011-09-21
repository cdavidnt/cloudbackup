package org.cloudbackup.filters;

import java.util.Collection;
import java.util.Date;

import org.cloudbackup.BackupImage;
import org.cloudbackup.DateUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class SpecificDateImage implements Function<Collection<BackupImage>, Collection<BackupImage>> {

	private Date specificDate;

	public SpecificDateImage(Date specificDate) {
		this.specificDate = specificDate;
	}

	public Collection<BackupImage> apply(Collection<BackupImage> input) {
		for (BackupImage backupImage : input) {
			if (DateUtils.midday(backupImage.getCreatedAt()).equals(DateUtils.midday(specificDate))) {
				return Lists.newArrayList(input);
			}
		}
		return Lists.newArrayList();
	}
}
