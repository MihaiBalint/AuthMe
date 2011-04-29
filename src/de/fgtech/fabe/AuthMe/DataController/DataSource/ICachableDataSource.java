package de.fgtech.fabe.AuthMe.DataController.DataSource;

import de.fgtech.fabe.AuthMe.DataController.RegistrationCache.RegistrationCache;

public interface ICachableDataSource extends IDataSource {
	public RegistrationCache loadAllAuths(); 
}
