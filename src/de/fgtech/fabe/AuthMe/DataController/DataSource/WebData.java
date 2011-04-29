package de.fgtech.fabe.AuthMe.DataController.DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;

import de.fgtech.fabe.AuthMe.DataController.PwHash;
import de.fgtech.fabe.AuthMe.MessageHandler.MessageHandler;

public class WebData implements IDataSource {
	private String sharedSecret = "GMCporpIStheBEST";
		
	private SecureRandom rand = new SecureRandom();
	private String playerNamePar = "pn";
	private String passwordPar = "sh";
	private String saltIndexPar = "si";
	private String regCountUrl = "http://gmcporp.com/forum/countreg.php";
	private String isPlayerRegUrl = "http://gmcporp.com/forum/isreg.php";
	private String checkPassUrl = "http://gmcporp.com/forum/check.php";
	
	public WebData(String sharedSecret, String regCountUrl, String isPlayerRegUrl, String checkPassUrl) {
		this.sharedSecret = sharedSecret;
		this.regCountUrl = regCountUrl;
		this.isPlayerRegUrl = isPlayerRegUrl;
		this.checkPassUrl = checkPassUrl;
	}

	@Override
	public boolean saveAuth(String playername, String hash, Map<String, String> customInformation) {
		MessageHandler.showError("[WebDS] New auth creation is not supported!");
		return false;
	}
	
	@Override
	public boolean updateAuth(String playername, String hash) {
		MessageHandler.showError("[WebDS] Auth update is not supported!");
		return false;
	}
	
	@Override
	public boolean removeAuth(String playername) {
		MessageHandler.showError("[WebDS] Auth removal is not supported!");
		return false;
	}

	
	@Override
	public boolean isPlayerRegistered(String playername) {
		try {
			InputStream in = withParam(isPlayerRegUrl,playerNamePar,playername).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String result = br.readLine();
			safeClose(br);
			return "yes".equalsIgnoreCase(result);
		} catch(IOException e) {
			MessageHandler.showError("[WebDS] Unable to check an authentication!");
			return false;
		}
	}

	@Override
	public int getRegisteredPlayerAmount() {
		try {
			InputStream in = new URL(regCountUrl).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String result = br.readLine();
			safeClose(br);
			return Integer.parseInt(result); 
		} catch(IOException e) {
			MessageHandler.showError("[WebDS] Unable to count authentications!");
			return 0;
		}
	}

	public boolean checkPass(String playername, String playerGivenPass) {
		InputStream in;
		BufferedReader br;
		try {
			String salt = rand.nextLong()+"1"+rand.nextLong()+"2"+rand.nextLong();
			
			String saltedPass = PwHash.encrypt(PwHash.encrypt(playerGivenPass)+sharedSecret+salt);
			in = withParams(checkPassUrl, 
					playerNamePar, playername, 
					passwordPar, saltedPass,
					saltIndexPar, salt
				).openStream();
			br = new BufferedReader(new InputStreamReader(in));
			String result = br.readLine();
			safeClose(br);
			return "yes".equalsIgnoreCase(result);
		} catch(IOException e) {
			MessageHandler.showError("[WebDS] Unable to check authentication!");
			return false;
		}
	}
	
	private static void safeClose(Reader rd) {
		try {
			rd.close();
		} catch (IOException e) { 
			// readers are always safe to close;
		}
	}
	
	private static URL withParam(String urlBase, String parName, String parValue) throws MalformedURLException {
		return new URL(urlBase +"?"+ parName +"="+parValue);
	}

	private static URL withParams(String urlBase, String... pars) throws MalformedURLException {
		StringBuilder b = new StringBuilder(urlBase);
		b.append("?");
		boolean name = true;
		for(String p : pars) {
			b.append(p);
			b.append( name ? "=" : "&");
			name = !name;
		}
		b.deleteCharAt(b.length()-1);
		
		return new URL(b.toString());
	}
}
