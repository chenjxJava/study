package com.chenjx.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
	/**
	 * 生成密码和加密盐
	 * @param password
	 * @return
	 */
	public Map<String,Object> generatePassword(String password){
		String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(
				algorithmName,
				password,
				ByteSource.Util.bytes(salt),
				hashIterations).toHex();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("salt", salt);
		map.put("password", newPassword);
		return map;
	}
	/**
	 * 比对原始密码是否正确
	 * @param password
	 * @param encryptionpassword
	 * @param salt
	 * @return
	 */
	public boolean checkPassword(String password,String encryptionpassword ,String salt){
		String newPassword = new SimpleHash(
				algorithmName,
				password,
				ByteSource.Util.bytes(salt),
				hashIterations).toHex();
		if(encryptionpassword.equals(newPassword)){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		//Map<String, Object> admin = new PasswordHelper().generatePassword("admin");//{password=9f8472a3ee04d0395ab4016aa4cbdb43, salt=77b5a93df42ce5e41c7d043f28dc9f6d}
		//System.out.println(admin);

		boolean admin = new PasswordHelper().checkPassword("admin",
			"9f8472a3ee04d0395ab4016aa4cbdb43", "77b5a93df42ce5e41c7d043f28dc9f6d");
		System.out.println(admin);
	}
}
