package org.apache.rave.portal.service.impl;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.rave.portal.service.CryptoService;
import org.springframework.stereotype.Service;

@Service(value = "cryptoService")
public class DefaultCryptoService implements CryptoService {

	private String iv = "fedcba9876543210";
	private String key = "";
	private Cipher cipher = null;
	private SecretKeySpec keySpec = null;
	private IvParameterSpec ivSpec = null;

	/**
	 * Constructor
	 *
	 * @throws Exception
	 */
	public DefaultCryptoService() throws Exception {
		this.key = "D4:6E:AC:3F:F0:BE";

		// Make sure the key length should be 16
		int len = this.key.length();
		if (len < 16) {
			int addSpaces = 16 - len;
			for (int i = 0; i < addSpaces; i++) {
				this.key = this.key + " ";
			}
		} else {
			this.key = this.key.substring(0, 16);
		}
		this.keySpec = new SecretKeySpec(this.key.getBytes(), "AES");
		this.ivSpec = new IvParameterSpec(iv.getBytes());
		this.cipher = Cipher.getInstance("AES/CBC/NoPadding");
	}

	/**
	 * Bytes to Hexa conversion
	 *
	 * @param data
	 * @return
	 */
	public String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0"
							+ java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str;
		}
	}

	/**
	 * Encrpt the goven string
	 *
	 * @param plainData
	 * @throws Exception
	 */
	public String encrypt(String plainData) throws Exception {

		// Make sure the plainData length should be multiple with 16
		int len = plainData.length();
		int q = len / 16;
		int addSpaces = ((q + 1) * 16) - len;
		for (int i = 0; i < addSpaces; i++) {
			plainData = plainData + " ";
		}

		this.cipher.init(Cipher.ENCRYPT_MODE, this.keySpec, this.ivSpec);
		byte[] encrypted = cipher.doFinal(plainData.getBytes());

		return bytesToHex(encrypted);
	}

	public byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(
						str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}
	}

	/**
	 * Decrypt the given excrypted string
	 *
	 * @param encrStr
	 * @throws Exception
	 */
	public String decrypt(String encrData) throws Exception {
		this.cipher.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);
		byte[] outText = this.cipher.doFinal(hexToBytes(encrData));

		String decrData = new String(outText).trim();
		return decrData;
	}

	public static void main(String[] args) throws Exception {
		DefaultCryptoService c = new DefaultCryptoService();
		String encrStr = c.encrypt("ankita");
		// String encrStr = c.decrypt("b84b1abdf26b293873225ed7d626015a");
		System.out.println("Encrypted Str :" + encrStr);
		// 45c95f1bb638d469bcc88b39fbb0f616
		 // bc5b1eba42c8076424e851daa477646c
		// 793e83e47f4bda549ef370f4fa058f51

	}
}