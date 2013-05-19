package org.manalith.ircbot.plugin.encode;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.manalith.ircbot.common.stereotype.BotCommand;
import org.manalith.ircbot.plugin.AbstractBotPlugin;
import org.springframework.stereotype.Component;

@Component
public class CryptoPlugin extends AbstractBotPlugin {
	@Override
	public String getName() {
		return "인코더";
	}

	@Override
	public String getCommands() {
		return "!md5|!sha1";
	}

	@BotCommand(value = { "!sha1" }, minimumArguments = 1)
	public String sha1(String... inputs) throws NoSuchAlgorithmException {
		String input = StringUtils.join(inputs, ' ');

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		return Hex.encodeHexString(messageDigest.digest(input.getBytes(Charset
				.forName("UTF-8"))));
	}

	@BotCommand(value = { "!md5" }, minimumArguments = 1)
	public String md5(String... inputs) throws NoSuchAlgorithmException {
		String input = StringUtils.join(inputs, ' ');

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		return Hex.encodeHexString(messageDigest.digest(input.getBytes(Charset
				.forName("UTF-8"))));
	}
}
