package org.cloudbackup.filters;

import java.util.Date;
import java.util.List;

import org.cloudbackup.BackupImage;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class OldestImage implements Function<List<BackupImage>, List<BackupImage>> {

	private Date oldestPossibleDate;

	public OldestImage() {
	}

	public OldestImage(Date oldestPossibleDate) {
		this.oldestPossibleDate = oldestPossibleDate;
	}

	public List<BackupImage> apply(List<BackupImage> input) {
		BackupImage oldestImage = null;
		for (BackupImage backupImage : input) {
			if (oldestPossibleDate == null || backupImage.getCreatedAt().after(oldestPossibleDate)) {
				if (oldestImage == null || backupImage.getCreatedAt().before(oldestImage.getCreatedAt())) {
					oldestImage = backupImage;
				}
			}
		}
		if (oldestImage != null) {
			return Lists.newArrayList(oldestImage);
		}
		return Lists.newArrayList();
	}

}
