package org.manalith.ircbot.plugin.DistroPkgFinder;

import org.manalith.ircbot.BotMain;
import org.manalith.ircbot.ManalithBot;
import org.manalith.ircbot.plugin.AbstractBotPlugin;
import org.manalith.ircbot.resources.MessageEvent;

public class DistroPkgFinderPlugin extends AbstractBotPlugin {

	public DistroPkgFinderPlugin(ManalithBot bot) {
		super(bot);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "뒷북 디스트로 패키지 검색";
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getHelp () 
	{
		return "!deb (pkg_name) | !ubu (pkg_name) | !fed (pkg_name)";
	}
	
	public void onMessage(MessageEvent event) {
		String message = event.getMessage();
		String channel = event.getChannel();
		String [] command = message.split("\\s");
		
		if ( (command[0].equals("!deb") || command[0].equals("!ubu") || command[0].equals("!fed")) && command.length > 2 )
		{
			BotMain.BOT.sendLoggedMessage(channel, "검색 단어는 하나면 충분합니다.");
		}
		else if ( command[0].equals("!deb") )
		{
			DebianPkgFinderRunner runner = new DebianPkgFinderRunner ( command[1] );
			BotMain.BOT.sendLoggedMessage(channel, runner.run());
		}
		else if ( command[0].equals("!ubu") )
		{
			UbuntuPkgFinderRunner runner = new UbuntuPkgFinderRunner ( command[1] );
			BotMain.BOT.sendLoggedMessage(channel, runner.run());
		}
		else if ( command[0].equals("!fed") )
		{
			FedoraPkgFinderRunner runner = new FedoraPkgFinderRunner ( command[1] );
			BotMain.BOT.sendLoggedMessage(channel, runner.run());
		}

	}
}
