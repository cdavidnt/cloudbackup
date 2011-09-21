package org.cloudbackup;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

public class BackupImage {

	public static final String TAG_BACKUP_ORDER = "backup_order";
	public static final String TAG_BACKUP_DELETABLE = "backup_deletable";
	public static final String TAG_BACKUP_TYPE = "backup_type";

	public static final String BACKUP_TYPE_AUTOMATIC = "automatic";

	private String id;
	private Date createdAt;
	private String type;
	private boolean deletable = true;
	private boolean delete;
	private String orderType;
	private Integer orderNumber;

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getOrderType() {
		return orderType;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete() {
		setDelete(true);
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isValidAutomatic() {
		return type != null && BACKUP_TYPE_AUTOMATIC.equalsIgnoreCase(type) && orderType != null && orderType.length() > 0 && orderNumber != null;
	}

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
		BackupImage me = new BackupImage();
		Map<String, String> tags = Maps.newHashMap();
		if (originalTags != null) {
			tags.putAll(originalTags);
		}
		trimAllValues(tags);
		me.id = id;
		me.createdAt = createdAt;
		me.type = tags.get(TAG_BACKUP_TYPE);
		me.deletable = ValueUtils.booleanFromString(tags.get(TAG_BACKUP_DELETABLE), me.deletable);
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

	public Map<String, String> toTags() {
		return toTags(this);
	}

	public static Map<String, String> toTags(BackupImage instance) {
		HashMap<String, String> tags = Maps.newHashMap();
		tags.put(TAG_BACKUP_TYPE, instance.type);
		tags.put(TAG_BACKUP_DELETABLE, ValueUtils.toString(instance.deletable));
		tags.put(TAG_BACKUP_ORDER, formatBackupOrder(instance));
		return tags;
	}

	private static void parseBackupOrder(BackupImage instance, String backupOrder) {
		if (backupOrder != null) {
			String[] orderSplit = backupOrder.split("_", 2);
			if (orderSplit.length == 2) {
				instance.orderType = orderSplit[0];
				instance.orderNumber = ValueUtils.integerFromString(orderSplit[1]);
			}
		}
	}

	private static String formatBackupOrder(BackupImage instance) {
		if (instance != null && instance.orderType != null && instance.orderNumber != null) {
			return instance.orderType + "_" + instance.orderNumber;
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BackupImage [");
		if (id != null) {
			builder.append("id=").append(id).append(", ");
		}
		if (createdAt != null) {
			builder.append("createdAt=").append(createdAt).append(", ");
		}
		if (type != null) {
			builder.append("type=").append(type).append(", ");
		}
		builder.append("deletable=").append(deletable).append(", delete=").append(delete).append(", ");
		if (orderType != null) {
			builder.append("orderType=").append(orderType).append(", ");
		}
		if (orderNumber != null) {
			builder.append("orderNumber=").append(orderNumber).append(", ");
		}
		builder.append("isValidAutomatic()=").append(isValidAutomatic()).append("]");
		return builder.toString();
	}

}
