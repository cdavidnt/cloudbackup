package org.cloudbackup.filters;
import org.cloudbackup.BackupImage;

import com.google.common.base.Predicate;

public class MustDeleteFilter implements Predicate<BackupImage> {

	public boolean apply(BackupImage input) {
		return input.isDeletable() && input.isDelete();
	}

}
