package org.cloudbackup;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.cloudbackup.filters.IsValidAutomaticFilter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

//TODO REFACTOR AND RENAME
public class BackupImageConverter {

	public static final String TAG_BACKUP_ORDER = "backup_order";
	public static final String TAG_BACKUP_DELETABLE = "backup_deletable";
	public static final String TAG_BACKUP_TYPE = "backup_type";

	public static final String BACKUP_TYPE_AUTOMATIC = "automatic";

	private static final IsValidAutomaticFilter IS_VALID_AUTOMATIC_FILTER = new IsValidAutomaticFilter();

	public static BackupImage createAutomaticFrom(String id, Date createdAt, Map<String, String> originalTags) {
		if (originalTags == null) {
			originalTags = Maps.newHashMap();
		}
		originalTags.put(TAG_BACKUP_TYPE, BACKUP_TYPE_AUTOMATIC);
		return createFrom(id, createdAt, originalTags);
	}

	public static BackupImage createFrom(String id, Date createdAt, Map<String, String> originalTags) {
		Preconditions.checkNotNull(id, "O id do snapshot não pode ser nulo");
		Preconditions.checkNotNull(createdAt, "A data de criação do snapshot não pode ser nula");
		BackupImageImpl me = new BackupImageImpl();
		Map<String, String> tags = Maps.newHashMap();
		if (originalTags != null) {
			tags.putAll(originalTags);
		}
		trimAllValues(tags);
		me.setId(id);
		me.setCreatedAt(createdAt);
		me.setType(tags.get(TAG_BACKUP_TYPE));
		me.setDeletable(ValueUtils.booleanFromString(tags.get(TAG_BACKUP_DELETABLE), me.isDeletable()));
		parseBackupOrder(me, tags.get(TAG_BACKUP_ORDER));
		return me;
	}

	private static void trimAllValues(Map<String, String> tags) {
		for (Entry<String, String> entry : tags.entrySet()) {
			if (entry.getValue() != null) {
				entry.setValue(entry.getValue().trim());
			}
		}

	}

	public static Map<String, String> toTags(BackupImage instance) {
		HashMap<String, String> tags = Maps.newHashMap();
		tags.put(TAG_BACKUP_TYPE, instance.getType());
		tags.put(TAG_BACKUP_DELETABLE, ValueUtils.toString(instance.isDeletable()));
		tags.put(TAG_BACKUP_ORDER, formatBackupOrder(instance));
		return tags;
	}

	private static void parseBackupOrder(BackupImage instance, String backupOrder) {
		if (backupOrder != null) {
			String[] orderSplit = backupOrder.split("_", 2);
			if (orderSplit.length == 2) {
				instance.setOrderType(orderSplit[0]);
				instance.setOrderNumber(ValueUtils.integerFromString(orderSplit[1]));
			}
		}
	}

	private static String formatBackupOrder(BackupImage instance) {
		if (instance != null && instance.getOrderType() != null && instance.getOrderNumber() != null) {
			return instance.getOrderType() + "_" + instance.getOrderNumber();
		}
		return null;
	}

	public static boolean isValidAutomatic(BackupImage image) {
		return IS_VALID_AUTOMATIC_FILTER.apply(image);
	}

}
