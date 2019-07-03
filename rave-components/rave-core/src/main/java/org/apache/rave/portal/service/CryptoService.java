package org.apache.rave.portal.service;


public interface CryptoService {

	 String encrypt(String plainData) throws Exception;

	 String decrypt(String encrData) throws Exception;

}
