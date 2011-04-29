package de.fgtech.fabe.AuthMe.DataController;

import java.util.Map;

import de.fgtech.fabe.AuthMe.DataController.DataSource.ICachableDataSource;
import de.fgtech.fabe.AuthMe.DataController.DataSource.IDataSource;
import de.fgtech.fabe.AuthMe.DataController.RegistrationCache.RegistrationCache;
import de.fgtech.fabe.AuthMe.DataController.RegistrationCache.RegistrationData;

import static de.fgtech.fabe.AuthMe.DataController.PwHash.encrypt;

public class CachingDataSource implements IDataSource {
	private ICachableDataSource datas;
	private RegistrationCache regcache;

	public CachingDataSource(ICachableDataSource dataf) {
		this.datas = dataf;
		reloadCache();
	}
	
	public void reloadCache() {
		this.regcache = datas.loadAllAuths();
	}

	public boolean saveAuth(String playername, String password, Map<String, String> customInformation) {
		// the underlying datasource will take care of pass hashing
		boolean executed = datas.saveAuth(playername.toLowerCase(), password, customInformation);
		if (!executed) return false;
		
		String hash = encrypt(password); // hash the pass in the cache
		regcache.insert(new RegistrationData(playername.toLowerCase(), hash));
		
		return true;
	}

	public boolean removeAuth(String playername) {
		boolean executed = datas.removeAuth(playername.toLowerCase());
		if (!executed) return false;

		regcache.remove(playername.toLowerCase());
		
		return true;
	}

	public boolean updateAuth(String playername, String password) {
		// the underlying datasource will take care of pass hashing
		boolean executed = datas.updateAuth(playername.toLowerCase(), password);
		if (!executed) return false;

		String hash = encrypt(password); // hash the pass in the cache
		regcache.getPlayerData(playername.toLowerCase()).setHash(hash);
		
		return true;
	}

	public boolean checkPass(String playername, String playerGivenPass) {
		String playerGivenHash = encrypt(playerGivenPass);
		String realHash = regcache.getHash(playername.toLowerCase());
		
		return realHash.equals(playerGivenHash);
	}

	public boolean isPlayerRegistered(String playername) {
		return regcache.isPlayerRegistered(playername.toLowerCase());
	}

	public int getRegisteredPlayerAmount() {
		return regcache.getRegisteredPlayerAmount();
	}

}