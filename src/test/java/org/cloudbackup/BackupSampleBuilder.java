package org.cloudbackup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.google.common.collect.Maps;

public class BackupSampleBuilder {

	public static int nextId = 0;
	public static int nextOrder = 0;
	public static Date nextDay = new Date();

	private String id;
	private Date createdAt;
	private Map<String, String> tags = Maps.newHashMap();
	private boolean automatic;
	private Boolean deletable;
	private String orderType;
	private Integer orderNumber;

	public static BackupSampleBuilder create() {
		return new BackupSampleBuilder();
	}

	public BackupSampleBuilder date(int day) {
		this.createdAt = SampleDateUtils.date(day);
		return this;
	}

	public BackupSampleBuilder date(Date date) {
		this.createdAt = SampleDateUtils.date(date);
		return this;
	}

	public BackupSampleBuilder date(int day, int month) {
		this.createdAt = SampleDateUtils.date(day, month);
		return this;
	}

	public BackupSampleBuilder date(int day, int month, int year) {
		this.createdAt = SampleDateUtils.date(day, month, year);
		return this;
	}

	public BackupSampleBuilder firstDayOfYear() {
		return this.date(1, 1);
	}

	public BackupSampleBuilder firstDayOfMonth() {
		return this.date(1);
	}
	
	public BackupSampleBuilder firstDayOfMonth(int month) {
		return this.date(1, month - 1);
	}

	public BackupSampleBuilder now() {
		this.createdAt = SampleDateUtils.now();
		return this;
	}

	public BackupSampleBuilder id(String id) {
		this.id = id;
		return this;
	}

	public BackupSampleBuilder tag(String name, String value) {
		tags.put(name, value);
		return null;
	}

	public BackupSampleBuilder tags(String... tags) {
		Map<String, String> map = Maps.newHashMap();
		String tagName = null;
		for (int i = 0; i < tags.length; i++) {
			if (i % 2 == 0) {
				tagName = tags[i];
			} else {
				map.put(tagName, tags[i]);
			}
		}
		this.tags.putAll(map);
		return this;
	}

	public BackupSampleBuilder automatic() {
		this.automatic = true;
		return this;
	}

	public BackupSampleBuilder automatic(boolean enable) {
		this.automatic = enable;
		return this;
	}

	public BackupSampleBuilder notDeletable() {
		this.deletable = false;
		return this;
	}

	public BackupSampleBuilder deletable(boolean enable) {
		this.deletable = enable;
		return this;
	}

	public BackupSampleBuilder daily() {
		this.orderType = "D";
		return this;
	}

	public BackupSampleBuilder monthly() {
		this.orderType = "M";
		return this;
	}

	public BackupSampleBuilder yearly() {
		this.orderType = "Y";
		return this;
	}

	public BackupSampleBuilder order(int number) {
		this.orderNumber = number;
		return this;
	}

	public BackupSampleBuilder nextDay() {
		this.createdAt = nextDay;

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(nextDay);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		nextDay = calendar.getTime();
		return this;
	}

	public BackupSampleBuilder nextOrder() {
		this.orderNumber = nextOrder++;
		return this;
	}

	public BackupImage build() {
		if (id == null) {
			id = "snap-" + nextId++;
		}
		if (automatic) {
			tags.put(BackupImage.TAG_BACKUP_TYPE, BackupImage.BACKUP_TYPE_AUTOMATIC);
		}
		if (deletable != null) {
			tags.put(BackupImage.TAG_BACKUP_DELETABLE, this.deletable ? "yes" : "no");
		}
		if (orderType != null) {
			if (orderNumber == null) {
				nextOrder();
			}
			tags.put(BackupImage.TAG_BACKUP_ORDER, this.orderType + "_" + this.orderNumber);
		}
		if (createdAt == null) {
			now();
		}
		return BackupImage.createFrom(id, createdAt, tags);
	}

}