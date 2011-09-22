package org.cloudbackup;

import java.util.Date;

public class BackupImageImpl implements BackupImage {

	private String id;
	private Date createdAt;
	private String type;
	private boolean deletable = true;
	private boolean delete;
	private String orderType;
	private Integer orderNumber;

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#getOrderType()
	 */
	@Override
	public String getOrderType() {
		return orderType;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#getOrderNumber()
	 */
	@Override
	public Integer getOrderNumber() {
		return orderNumber;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#isDeletable()
	 */
	@Override
	public boolean isDeletable() {
		return deletable;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#isDelete()
	 */
	@Override
	public boolean isDelete() {
		return delete;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#setDelete()
	 */
	@Override
	public void setDelete() {
		setDelete(true);
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#setDelete(boolean)
	 */
	@Override
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#setOrderNumber(java.lang.Integer)
	 */
	@Override
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#setOrderType(java.lang.String)
	 */
	@Override
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/* (non-Javadoc)
	 * @see org.cloudbackup.IBackupImage#getCreatedAt()
	 */
	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
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
		builder.append("]");
		return builder.toString();
	}

}
