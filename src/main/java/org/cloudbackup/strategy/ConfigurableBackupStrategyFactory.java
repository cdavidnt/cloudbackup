package org.cloudbackup.strategy;

import java.util.Date;

import org.cloudbackup.Dates;
import org.cloudbackup.filters.SpecificDateOrRangeDateImage;

public class ConfigurableBackupStrategyFactory {

	public ConfigurableBackupStrategy dailyStrategy(int maxBackups) {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(maxBackups);
		backupStrategy.setOrderType("D");
		return backupStrategy;
	}

	public ConfigurableBackupStrategy monthlyStrategy(int maxBackups, Date referenceDate, int marginDays) {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(maxBackups);
		backupStrategy.setOrderType("M");
		Date firstDayOfMonth = Dates.at(referenceDate).firstDayOfMonth().date();
		backupStrategy.setAlternativeElegibleFilter(new SpecificDateOrRangeDateImage(firstDayOfMonth, marginDays));
		return backupStrategy;
	}

	public ConfigurableBackupStrategy yearlyStrategy(int maxBackups, Date referenceDate, int marginDays) {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(maxBackups);
		backupStrategy.setOrderType("A");
		Date firstDayOfYear = Dates.at(referenceDate).firstDayOfYear().date();
		backupStrategy.setAlternativeElegibleFilter(new SpecificDateOrRangeDateImage(firstDayOfYear, marginDays));
		return backupStrategy;
	}

}
