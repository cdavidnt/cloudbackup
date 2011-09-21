package org.cloudbackup;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.cloudbackup.filters.IsValidAutomaticFilter;
import org.cloudbackup.filters.OrderTypeFilter;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

public class ConfigurableBackupStrategy implements BackupStrategy {

	private int maxBackups;
	private String orderType;
	private Function<Collection<BackupImage>, Collection<BackupImage>> alternativeElegibleFilter;

	public void execute(List<BackupImage> images) {
		Collection<BackupImage> elegible = Collections2.filter(images, getElegibleFilter());
		if (alternativeElegibleFilter != null && elegible.isEmpty()) {
			Collection<BackupImage> tempImages = Collections2.filter(images, new IsValidAutomaticFilter());
			elegible = alternativeElegibleFilter.apply(tempImages);
		}
		reorderImages(elegible);
		deleteImages(elegible);
	}

	protected void reorderImages(Collection<BackupImage> elegible) {
		int index = 0;
		for (Iterator<BackupImage> iterator = elegible.iterator(); iterator.hasNext();) {
			BackupImage backupImage = iterator.next();
			backupImage.setOrderType(orderType);
			backupImage.setOrderNumber(index++);
		}
	}

	protected void deleteImages(Collection<BackupImage> elegible) {
		Iterable<BackupImage> deletable = Iterables.skip(elegible, maxBackups);
		for (BackupImage deletableImage : deletable) {
			deletableImage.setDelete();
		}
	}

	protected Predicate<BackupImage> getElegibleFilter() {
		return Predicates.and(new IsValidAutomaticFilter(), new OrderTypeFilter(orderType));
	}

	public int getMaxBackups() {
		return maxBackups;
	}

	public void setMaxBackups(int maxBackups) {
		this.maxBackups = maxBackups;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Function<Collection<BackupImage>, Collection<BackupImage>> getAlternativeElegibleFilter() {
		return alternativeElegibleFilter;
	}

	public void setAlternativeElegibleFilter(Function<Collection<BackupImage>, Collection<BackupImage>> alternativeFilter) {
		this.alternativeElegibleFilter = alternativeFilter;
	}

}
