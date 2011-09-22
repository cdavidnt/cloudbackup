package org.cloudbackup.strategy;

import java.util.List;

import org.cloudbackup.BackupImage;

public interface BackupStrategy {
	public void execute(List<BackupImage> images);
}
