package org.cloudbackup;

import java.util.Date;

import org.cloudbackup.filters.SpecificDateOrRangeDateImage;

public class ConfigurableBackupStrategyFactory {

	public static ConfigurableBackupStrategy dailyStrategy(int maxBackups) {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(maxBackups);
		backupStrategy.setOrderType("D");
		return backupStrategy;
	}

	public static ConfigurableBackupStrategy monthlyStrategy(int maxBackups, Date referenceDate) {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(maxBackups);
		backupStrategy.setOrderType("M");

		Date firstDayOfMonth = BackupSamples.daily().firstDayOfMonth().build().getCreatedAt();
		backupStrategy.setAlternativeElegibleFilter(new SpecificDateOrRangeDateImage(firstDayOfMonth, 10));
		return backupStrategy;
	}

	public static ConfigurableBackupStrategy yearlyStrategy(int maxBackups, Date referenceDate) {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(maxBackups);
		backupStrategy.setOrderType("A");

		Date firstDayOfYear = BackupSamples.daily().firstDayOfYear().build().getCreatedAt();
		backupStrategy.setAlternativeElegibleFilter(new SpecificDateOrRangeDateImage(firstDayOfYear, 30));
		return backupStrategy;
	}

}
