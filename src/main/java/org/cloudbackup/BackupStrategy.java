package org.cloudbackup;

import java.util.List;

public interface BackupStrategy {
	public void execute(List<BackupImage> images);
}
