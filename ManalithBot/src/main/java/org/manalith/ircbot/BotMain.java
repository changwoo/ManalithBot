/*
 	org.manalith.ircbot/BotMain.java
 	ManalithBot - An open source IRC bot based on the PircBot Framework.
 	Copyright (C) 2005, 2011, 2012  Ki-Beom, Kim

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.manalith.ircbot;

import java.nio.charset.Charset;
import java.util.StringTokenizer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

//import org.hsqldb.Server;

public class BotMain {
	public static ManalithBot BOT; // FIXME 플러그인이 메시지 전송을 위해 봇 인스턴스를 호출하는 경로

	public static void main(String[] args) throws Exception {
		// 인코딩을 검사한다.
		if (!Charset.defaultCharset().toString().equals("UTF-8")) {
			System.out.println("-Dfile.encoding=UTF-8 옵션으로 실행시켜 주세요.");
			return;
		}

		// 옵션을 가져온다.
		Options options = new Options();
		options.addOption("c", true, "config file");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse(options, args);

		String configFile = cmd.getOptionValue("c");
		if (configFile != null) {
			ConfigurationManager.setConfigFile(configFile);
		}

		// 설정 초기화
		ConfigurationManager config = ConfigurationManager.getInstance();

		// DB 시작
		// Server hsqlServer = null;
		//
		// try {
		// hsqlServer = new Server();
		//
		// hsqlServer.setDatabaseName(0, "manalith_ircbot");
		// hsqlServer.setDatabasePath(0, "file:manalith_ircbot");
		//
		// hsqlServer.start();
		// } finally {
		// if (hsqlServer != null) {
		// hsqlServer.stop();
		// }
		// }

		// 봇 구동
		final ManalithBot bot = new ManalithBot(config.getBotName());
		BOT = bot;
		bot.setVerbose(config.getVerbose());
		bot.setEncoding(config.getServerEncoding());
		bot.connect(config.getServer(), config.getServerPort());

		// bot.addPlugin(new org.manalith.ircbot.plugin.fdbot.fdbotPlugin(bot));
		//bot.addPlugin(new org.manalith.ircbot.plugin.waitbdbot.WaitBDBotPlugin(bot));
		bot.addPlugin(new org.manalith.ircbot.plugin.twitreader.TwitReaderPlugin(bot));
		//bot.addPlugin(new org.manalith.ircbot.plugin.nvidiadrivernews.NvidiaDriverNewsPlugin(bot));
		//bot.addPlugin(new org.manalith.ircbot.plugin.missedmessage.MissedMessagePlugin(bot));
		bot.addPlugin(new org.manalith.ircbot.plugin.distropkgfinder.DistroPkgFinderPlugin(bot));
		// bot.addPlugin(new org.manalith.ircbot.plugin.Calc.CalcPlugin(bot));
		//bot.addPlugin(new org.manalith.ircbot.plugin.kvl.KVLPlugin(bot));
		//bot.addPlugin(new org.manalith.ircbot.plugin.cer2.CERPlugin(bot));
		// bot.addPlugin(new org.manalith.ircbot.plugin.weather.WeatherPlugin(bot));
		//bot.addPlugin(new org.manalith.ircbot.plugin.ircrelayupdater.IRCRelayUpdaterPlugin(bot));
		// bot.addPlugin(new org.manalith.ircbot.plugin.et.ETPlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.rss.SlashdotReaderPlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.javaapi.JavaApiPlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.google.GooglePlugin(bot));
		// bot.addPlugin(new org.manalith.ircbot.plugin.bsh.BshPlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.chat.TranslatePlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.dictionary.DictionaryPlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.relay.RelayPlugin());
		// bot.addPlugin(new org.manalith.ircbot.plugin.setzer.SetzerPlugin());

		final StringTokenizer st = new StringTokenizer(
				config.getDefaultChannels(), ",");
		while (st.hasMoreTokens())
			bot.joinChannel(st.nextToken());
	}
}
