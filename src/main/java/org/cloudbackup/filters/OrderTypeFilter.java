package org.cloudbackup.filters;

import org.cloudbackup.BackupImage;

import com.google.common.base.Predicate;

public class OrderTypeFilter implements Predicate<BackupImage> {

	private String orderType;

	public OrderTypeFilter(String orderType) {
		this.orderType = orderType;
	}

	public boolean apply(BackupImage input) {
		return orderType.equalsIgnoreCase(input.getOrderType());
	}

}
