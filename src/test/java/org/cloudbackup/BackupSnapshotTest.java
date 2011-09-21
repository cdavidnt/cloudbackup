package org.cloudbackup;

import junit.framework.Assert;

import org.junit.Test;

public class BackupSnapshotTest {

	@Test
	public void testarCreateFrom_SucessoElegivel() {
		BackupImage snap1 = BackupSamples.daily().order(0).build();
		System.out.println(snap1);
		System.out.println(snap1.toTags());
		Assert.assertNotNull(snap1);
		Assert.assertEquals("D", snap1.getOrderType());
		Assert.assertEquals((Integer) 0, snap1.getOrderNumber());
		Assert.assertEquals(true, snap1.isValidAutomatic());
	}

	@Test
	public void testarCreateFrom_SucessoNaoElegivel() {
		BackupImage snap1 = BackupSamples.dailyNotAutomatic().order(1).build();
		System.out.println(snap1);
		System.out.println(snap1.toTags());
		Assert.assertNotNull(snap1);
		Assert.assertEquals("D", snap1.getOrderType());
		Assert.assertEquals((Integer) 1, snap1.getOrderNumber());
		Assert.assertEquals(false, snap1.isValidAutomatic());
	}

}
