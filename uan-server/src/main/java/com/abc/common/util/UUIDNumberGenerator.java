package com.abc.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * @author liubo UUID工具类,生成尽可能唯一的UUID
 */
@Component
public class UUIDNumberGenerator {
	/**
	 * 自动建立的字段: CONSTANT_256
	 */
	private static final int CONSTANT_256 = 256;

	/**
	 * 自动建立的字段: CONSTANT_16
	 */
	private static final int CONSTANT_16 = 16;

	/**
	 * 自动建立的字段: CONSTANT_32
	 */
	private static final int CONSTANT_32 = 32;

	/**
	 * 自动建立的字段: CONSTANT_48
	 */
	private static final int CONSTANT_48 = 48;

	/**
	 * 自动建立的字段: CONSTANT_12
	 */
	private static final int CONSTANT_12 = 12;

	/**
	 * 自动建立的字段: CONSTANT_36
	 */
	private static final int CONSTANT_36 = 36;
	private static final int UUID_NUMBER_LENGTH = 24;

	private static UUIDNumberGenerator instance = new UUIDNumberGenerator();

	private InetAddress id = null;// 缓存IP地址

	public UUIDNumberGenerator() {
	}

	/**
	 * 生成UUID，IP+随机数+系统时间，尽可能保证不重复
	 * 
	 * @return UUID
	 */
	public synchronized UUID generateUUID() {
		long hi = 0;
		try {
			if (id == null) {
				id = InetAddress.getLocalHost();
			}
			byte[] address = id.getAddress();
			for (int i = 3; i >= 0; i--) {
				long adr = address[i];
				if (adr < 0) {
					adr = CONSTANT_256 + adr;
				}
				hi = hi | adr << ((i + 4) * 8);
			}
			hi += new SecureRandom().nextInt();
			LogWriter.trace(UUIDNumberGenerator.class, String.valueOf(hi));
		} catch (UnknownHostException e) {
			LogWriter.trace(UUIDNumberGenerator.class, "unsupported InetAddress");
			hi = new SecureRandom().nextLong();
		}
		return new UUID(hi, System.nanoTime());
	}

	/**
	 * 生成UUID的String，IP+随机数+系统时间，尽可能保证不重复
	 * 
	 * @return
	 */
	public String generate() {
		return toShortString(generateUUID());
	}

	/**
	 * UUID转换为24位长的字符串
	 * 
	 * @param u
	 *            UUID
	 * @return 24位长的字符串
	 */
	private String toShortString(UUID u) {
		long mostSigBits = u.getMostSignificantBits();
		long leastSigBits = u.getLeastSignificantBits();
		return (digits(mostSigBits >> CONSTANT_32, 8) + digits(mostSigBits >> CONSTANT_16, 4) + digits(mostSigBits, 4)
				+ digits(leastSigBits >> CONSTANT_48, 4) + digits(leastSigBits, CONSTANT_12));
	}

	/**
	 * 数值转换为字符串
	 * 
	 * @param val
	 *            数值
	 * @param digits
	 *            数位数
	 * @return 字符串
	 */
	private String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toString(hi | (val & (hi - 1)), CONSTANT_36).substring(1);
	}

	/**
	 * @return
	 * 
	 */
	public int getLength() {
		return UUID_NUMBER_LENGTH;
	}

}
