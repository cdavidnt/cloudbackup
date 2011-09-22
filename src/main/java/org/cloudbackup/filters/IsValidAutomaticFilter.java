package org.cloudbackup.filters;

import org.cloudbackup.BackupImage;
import org.cloudbackup.BackupImageConverter;

import com.google.common.base.Predicate;

public class IsValidAutomaticFilter implements Predicate<BackupImage> {

	public boolean apply(BackupImage input) {
		return input.getType() != null && BackupImageConverter.BACKUP_TYPE_AUTOMATIC.equalsIgnoreCase(input.getType()) && input.getOrderType() != null && input.getOrderType().length() > 0 && input.getOrderNumber() != null;
	}

}
