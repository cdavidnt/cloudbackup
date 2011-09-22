package org.cloudbackup;

public class BackupSamples {

	public static BackupSampleBuilder monthlyNotDeletable() {
		return monthly().notDeletable();
	}

	public static BackupSampleBuilder monthly() {
		return BackupSampleBuilder.create().monthly().automatic();
	}

	public static BackupSampleBuilder yearlyNotDeletable() {
		return yearly().notDeletable();
	}

	public static BackupSampleBuilder yearly() {
		return BackupSampleBuilder.create().yearly().automatic();
	}

	public static BackupSampleBuilder dailyNotDeletable() {
		return daily().notDeletable();
	}

	public static BackupSampleBuilder daily() {
		return BackupSampleBuilder.create().daily().automatic();
	}

	public static BackupSampleBuilder monthlyNotAutomaticNotDeletable() {
		return monthlyNotAutomatic().notDeletable();
	}

	public static BackupSampleBuilder monthlyNotAutomatic() {
		return BackupSampleBuilder.create().monthly();
	}

	public static BackupSampleBuilder yearlyNotAutomaticNotDeletable() {
		return yearlyNotAutomatic().notDeletable();
	}

	public static BackupSampleBuilder yearlyNotAutomatic() {
		return BackupSampleBuilder.create().yearly();
	}

	public static BackupSampleBuilder dailyNotAutomaticNotDeletable() {
		return dailyNotAutomatic().notDeletable();
	}

	public static BackupSampleBuilder dailyNotAutomatic() {
		return BackupSampleBuilder.create().daily();
	}

}
