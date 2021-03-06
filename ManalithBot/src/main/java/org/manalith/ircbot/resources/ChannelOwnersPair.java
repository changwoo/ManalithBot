/*
 *  Created on 2005. 8. 11
 	org.manalith.ircbot.resources/ChannelOwnersPair.java
 	ManalithBot - An open source IRC bot based on the PircBot Framework.
 	Copyright (C) 2005  Ki-Beom, Kim

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
package org.manalith.ircbot.resources;

import java.util.List;


public class ChannelOwnersPair {
	private String channel;
	private List<String> owners;
	
	public ChannelOwnersPair(String channel, List<String> owners){
		this.channel = channel;
		this.owners = owners;
	}

	
	/**
	 * @return Returns the channel.
	 */
	public String getChannel() {
		return channel;
	}

	
	/**
	 * @param channel The channel to set.
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	
	/**
	 * @return Returns the owners.
	 */
	public List<String> getOwners() {
		return owners;
	}

	
	/**
	 * @param owners The owners to set.
	 */
	public void setOwners(List<String> owners) {
		this.owners = owners;
	}
	
	
}
