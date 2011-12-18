//
// DateTimeRound.java
// darkcircle dot 0426 at gmail dot com
//
// This source can be distributed under the terms of GNU General Public License version 3
// which is derived from the license of Manalith bot.

package org.manalith.ircbot.plugin.CER;

import java.util.GregorianCalendar;

public class DateTimeRound {
	private GregorianCalendar calendar;
	private int round;
	
	public DateTimeRound ( )
	{
		calendar = null;
		round = 0;
	}
	public DateTimeRound ( GregorianCalendar newCalendar , int newRoundVal )
	{
		this.setCalendar ( newCalendar );
		this.setRoundVal ( newRoundVal );
	}
	
	public void setCalendar ( GregorianCalendar newCalendar )
	{
		this.calendar = newCalendar;
	}
	public GregorianCalendar getCalendar ( ) 
	{
		return this.calendar;
	}
	
	public void setRoundVal ( int newRoundVal )
	{
		this.round = newRoundVal;
	}
	public int getRoundVal ( )
	{
		return this.round;
	}
	
	public int compareTo ( DateTimeRound o )
	{
		int result = this.getCalendar().compareTo(o.getCalendar());
				
		if ( result == 0 )
			result = this.getRoundVal() - o.getRoundVal();
		
		return result;
	}
}