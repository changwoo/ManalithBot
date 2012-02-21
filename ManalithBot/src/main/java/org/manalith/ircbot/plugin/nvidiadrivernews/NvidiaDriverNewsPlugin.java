/*
 	org.manalith.ircbot.plugin.nvidiadrivernews/NvidiaDriverNewsPlugin.java
 	ManalithBot - An open source IRC bot based on the PircBot Framework.
 	Copyright (C) 2011, 2012  Seong-ho, Cho <darkcircle.0426@gmail.com>

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
package org.manalith.ircbot.plugin.nvidiadrivernews;

import org.manalith.ircbot.plugin.AbstractBotPlugin;
import org.manalith.ircbot.resources.MessageEvent;
import org.springframework.stereotype.Component;

@Component
public class NvidiaDriverNewsPlugin extends AbstractBotPlugin {

	public String getName() {
		return "Nvidia최신버전";
	}

	public String getCommands() {
		return "nvidia";
	}

	public String getHelp() {
		return "!nvidia";
	}

	public void onMessage(MessageEvent event) {
		String channel = event.getChannel();
		String msg = event.getMessage();

		if (msg.equals("!nvidia")) {
			NvidiaDriverNewsRunner runner = new NvidiaDriverNewsRunner();
			bot.sendLoggedMessage(channel, runner.run());
			event.setExecuted(true);
		}
	}
}
