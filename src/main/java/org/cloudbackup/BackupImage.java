package org.cloudbackup;

import java.util.Date;

public interface BackupImage {

	public String getId();

	public String getType();

	public String getOrderType();

	public Integer getOrderNumber();

	public boolean isDeletable();

	public boolean isDelete();

	public void setDelete();

	public void setDelete(boolean delete);

	public void setOrderNumber(Integer orderNumber);

	public void setOrderType(String orderType);

	public Date getCreatedAt();

}