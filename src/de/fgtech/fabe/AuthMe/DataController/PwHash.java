package de.fgtech.fabe.AuthMe.DataController;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PwHash {

	public static String encrypt(String string) {
		try {
			final MessageDigest m = MessageDigest.getInstance("MD5");
			final byte[] bytes = string!=null ? string.getBytes() : new byte[0];
			m.update(bytes, 0, bytes.length);
			final BigInteger i = new BigInteger(1, m.digest());

			return String.format("%1$032X", i).toLowerCase();
		} catch (final Exception e) {
			return "";
		}
	}

}
