package de.fgtech.fabe.AuthMe.DataController.DataSource;

import java.util.Map;

public interface IDataSource {
	
	public boolean saveAuth(String playername, String plainTextPass, Map<String, String> customInformation);
	public boolean updateAuth(String playername, String newPlainTextPass);
	public boolean removeAuth(String playername);
	
	public boolean isPlayerRegistered(String playername);
	public int getRegisteredPlayerAmount();
	
	public boolean checkPass(String playername, String playerGivenPlainTextPass);
}
