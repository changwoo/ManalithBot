package org.manalith.ircbot.plugin.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.manalith.ircbot.common.stereotype.BotCommand;
import org.manalith.ircbot.plugin.AbstractBotPlugin;
import org.manalith.ircbot.resources.MessageEvent;
import org.springframework.stereotype.Component;

@Component
public class TimestampPlugin extends AbstractBotPlugin {
	@Override
	public String getName() {
		return "유닉스 타임스탬프 변환 플러그인";
	}

	@Override
	public String getCommands() {
		return "!timestamp [timestamp]";
	}

	@BotCommand(value = { "!timestamp" }, minimumArguments = 1)
	public String convertTimeStamp(MessageEvent event, String... args) {
		return convertTimeStamp(Long.parseLong(args[0]));
	}

	public String convertTimeStamp(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss (z Z)");
		return format.format(new Date(timestamp * 1000));
	}

}