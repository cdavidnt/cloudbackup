package org.cloudbackup;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.cloudbackup.filters.MustDeleteFilter;
import org.cloudbackup.filters.OrderTypeFilter;
import org.cloudbackup.filters.SpecificDateOrRangeDateImage;
import org.cloudbackup.strategy.ConfigurableBackupStrategy;
import org.cloudbackup.strategy.ConfigurableBackupStrategyFactory;
import org.junit.After;
import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import static org.cloudbackup.BackupSamples.*;

public class ConfigurableBackupStrategyTest {

	@Test
	public void cenarioSucessoDiario() {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(1);
		backupStrategy.setOrderType("D");

		//@formatter:off
		List<BackupImage> images = Lists.newArrayList(
			daily().build(), 
			dailyNotDeletable().build(), 
			daily().build());
		//@formatter:off
		
		System.out.println("BEFORE: " + images);
		backupStrategy.execute(images);
		System.out.println("AFTER: " + images);
		
		Collection<BackupImage> myorder = Collections2.filter(images, new OrderTypeFilter(backupStrategy.getOrderType()));
		System.out.println("MY ORDER BACKUPS: " + myorder);
		
		Collection<BackupImage> deletable = Collections2.filter(images, new MustDeleteFilter());
		System.out.println("MUST DELETE: " + deletable);
		Assert.assertEquals(1, deletable.size());
	}
	
	@Test
	public void cenarioSucessoMensal() {
		ConfigurableBackupStrategyFactory factory = new ConfigurableBackupStrategyFactory();
		ConfigurableBackupStrategy backupStrategy = factory.monthlyStrategy(1, new Date(), 10);
		
		Date mayOneOneYearAgo = Dates.now().date(1, 5).previousYear().date();
		Date maySecond = Dates.now().date(2, 5).date();

		//@formatter:off
		List<BackupImage> images = Lists.newArrayList(
			daily().build(), 
			dailyNotDeletable().build(),
			daily().date(maySecond).build(),
			daily().date(mayOneOneYearAgo).build(),
			daily().build());
		//@formatter:off
		
		System.out.println("BEFORE: " + images);
		backupStrategy.execute(images);
		System.out.println("AFTER: " + images);
		
		Collection<BackupImage> myorder = Collections2.filter(images, new OrderTypeFilter(backupStrategy.getOrderType()));
		System.out.println("MY ORDER BACKUPS: " + myorder);
		
		Collection<BackupImage> deletable = Collections2.filter(images, new MustDeleteFilter());
		System.out.println("MUST DELETE: " + deletable);
		Assert.assertEquals(0, deletable.size());
	}
	
	
	@Test
	public void cenarioSucessoMensalComDelecao() {
		ConfigurableBackupStrategy backupStrategy = new ConfigurableBackupStrategy();
		backupStrategy.setMaxBackups(1);
		backupStrategy.setOrderType("M");
		Date firstDayOfMonth = Dates.now().firstDayOfMonth().date();
		backupStrategy.setAlternativeElegibleFilter(new SpecificDateOrRangeDateImage(firstDayOfMonth, 10));

		Date mayOneOneYearAgo = Dates.now().date(1, 5).previousYear().date();
		Date maySecond = Dates.now().date(2, 5).date();
		//@formatter:off
		List<BackupImage> images = Lists.newArrayList(
			daily().build(), 
			dailyNotDeletable().build(),
			daily().date(maySecond).build(),
			daily().date(mayOneOneYearAgo).build(),
			monthly().build(),
			monthly().build(),
			daily().build());
		//@formatter:off
		
		System.out.println("BEFORE: " + images);
		backupStrategy.execute(images);
		System.out.println("AFTER: " + images);

		Collection<BackupImage> myorder = Collections2.filter(images, new OrderTypeFilter(backupStrategy.getOrderType()));
		System.out.println("MY ORDER BACKUPS: " + myorder);
		
		Collection<BackupImage> deletable = Collections2.filter(images, new MustDeleteFilter());
		System.out.println("MUST DELETE: " + deletable);
		Assert.assertEquals(1, deletable.size());
	}
	
	@After
	public void after() {
		System.out.println("");
	}
}
