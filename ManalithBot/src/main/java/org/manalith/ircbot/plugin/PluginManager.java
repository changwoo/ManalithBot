package org.manalith.ircbot.plugin;

import java.util.ArrayList;
import java.util.List;

import org.manalith.ircbot.resources.MessageEvent;

public class PluginManager{
	private List<IBotPlugin> list = new ArrayList<IBotPlugin>();
	
	public void add(IBotPlugin plugin){
		list.add(plugin);
	}
	
	public void remove(IBotPlugin plugin){
		list.remove(plugin);
	}
	
	public void onJoin(String channel,
			String sender,
			String login,
			String hostName){
		for(IBotPlugin plugin : list)
			plugin.onJoin(channel, sender, login, hostName);
	}
	
	public void onMessage(String channel,
			String sender,
			String login,
			String hostName,
			String message){
		MessageEvent msg = new MessageEvent(channel, sender, login, hostName, message);
		
		for(IBotPlugin plugin : list) {
			plugin.onMessage(msg);
			if(msg.isExecuted()) break;
		}
	}
	
	public List<IBotPlugin> getList(){
		return list;
	}
	
	public String getPluginInfo(){
		StringBuilder sb = new StringBuilder();
		
		for(IBotPlugin p : list){
			sb.append(p.getName() + "(" + p.getNamespace() + ")");
		}
		
		return sb.toString();
	}
}