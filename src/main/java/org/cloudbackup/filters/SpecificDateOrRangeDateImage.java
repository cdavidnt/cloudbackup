package org.cloudbackup.filters;

import java.util.Collection;
import java.util.Date;

import org.cloudbackup.BackupImage;

import com.google.common.base.Function;

public class SpecificDateOrRangeDateImage implements Function<Collection<BackupImage>, Collection<BackupImage>> {

	private SpecificDateImage specificDateImage;
	private RangeDateImage rangeDateImage;

	public SpecificDateOrRangeDateImage(Date specificDate, int days) {
		this.specificDateImage = new SpecificDateImage(specificDate);
		this.rangeDateImage = new RangeDateImage(specificDate, days, days);
	}

	public Collection<BackupImage> apply(Collection<BackupImage> input) {
		Collection<BackupImage> result = specificDateImage.apply(input);
		if (result.isEmpty()) {
			return rangeDateImage.apply(input);
		}
		return result;
	}

}
