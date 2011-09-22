package org.cloudbackup;

import junit.framework.Assert;

import org.junit.Test;

public class BackupSnapshotTest {

	@Test
	public void testarCreateFrom_SucessoElegivel() {
		BackupImage snap1 = BackupSamples.daily().order(0).build();
		System.out.println(snap1);
		System.out.println(BackupImageConverter.toTags(snap1));
		Assert.assertNotNull(snap1);
		Assert.assertEquals("D", snap1.getOrderType());
		Assert.assertEquals((Integer) 0, snap1.getOrderNumber());
		Assert.assertEquals(true, BackupImageConverter.isValidAutomatic(snap1));
	}

	@Test
	public void testarCreateFrom_SucessoNaoElegivel() {
		BackupImage snap1 = BackupSamples.dailyNotAutomatic().order(1).build();
		System.out.println(snap1);
		System.out.println(BackupImageConverter.toTags(snap1));
		Assert.assertNotNull(snap1);
		Assert.assertEquals("D", snap1.getOrderType());
		Assert.assertEquals((Integer) 1, snap1.getOrderNumber());
		Assert.assertEquals(false, BackupImageConverter.isValidAutomatic(snap1));
	}

}
