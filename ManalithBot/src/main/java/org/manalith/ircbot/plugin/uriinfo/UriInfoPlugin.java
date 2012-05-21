/*
 	org.manalith.ircbot.plugin.uriinfo/UriInfoPlugin.java
 	ManalithBot - An open source IRC bot based on the PircBot Framework.
 	Copyright (C) 2012  Changwoo Ryu <cwryu@debian.org>
 	Copyright (C) 2012  Seong-ho Cho <darkcircle.0426@gmail.com>

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
package org.manalith.ircbot.plugin.uriinfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.manalith.ircbot.plugin.AbstractBotPlugin;
import org.manalith.ircbot.resources.MessageEvent;
import org.springframework.stereotype.Component;

@Component
public class UriInfoPlugin extends AbstractBotPlugin {
	private Logger logger = Logger.getLogger(getClass());

	public String getCommands() {
		return null;
	}

	public String getName() {
		return "URI 타이틀";
	}

	public String getHelp() {
		return "대화 중 등장하는 URI의 타이틀을 표시합니다";
	}

	private String findUri(String msg) {
		if (!msg.contains("http"))
			return null;

		String URI_REGEX = ".*(https?://\\S+).*";
		Pattern pattern = Pattern.compile(URI_REGEX);
		Matcher matcher = pattern.matcher(msg);

		if (!matcher.matches())
			return null;

		return matcher.group(1);
	}

	private String getInfo(String newUri) {
		
		String result;
		
		/*
		try {
			return Jsoup
					.connect(uri)
					.header("User-Agent",
							"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0")
					.get().title().replaceAll("\\n", "").replaceAll("\\r", "")
					.replaceAll("(\\s){2,}", " ");
		
		//*/
		
		try {
			String content_type = (new URL(newUri)).openConnection()
					.getContentType();
			
			// all possible failure case
			if (!content_type.contains("text/")
					|| !content_type.contains("application/")
					&& !content_type.contains("ml")) {
				result = "[Link Content-type] "
						+ (new URL(newUri)).openConnection().getContentType();
			} else {
				result = "[Link Title] "
						+ Jsoup.connect(newUri)
								.header("User-Agent",
										"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0")
								.get().title().replaceAll("\\n", "")
								.replaceAll("\\r", "")
								.replaceAll("(\\s){2,}", " ");
			}
		}
		catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return null;
		}
		
		return result;
	}

	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		String channel = event.getChannel().getName();

		String uri = findUri(message);
		if (uri == null)
			return;

		String info = getInfo(uri);
		if (info != null) {
			bot.sendLoggedMessage(channel, info);
		}

		// This plugin runs implicitly; it does NOT need to call
		// event.setExecuted(true)
	}
}
