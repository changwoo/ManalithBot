//
// CommandTokenAnalyzer.java
// darkcircle dot 0426 at gmail dot com
//
// This source can be distributed under the terms of GNU General Public License version 3
// which is derived from the license of Manalith bot.

package org.manalith.ircbot.plugin.cer2;

import org.manalith.ircbot.plugin.cer2.exceptions.EmptyTokenStreamException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CommandTokenAnalyzer extends TokenAnalyzer {

	public CommandTokenAnalyzer() {
		super();
	}

	public CommandTokenAnalyzer(String newTokenString) {
		this.setTokenStringData(newTokenString);
	}

	@Override
	public TokenType getTokenType(String TokenString) {
		TokenType result = TokenType.Unknown;

		Pattern cmd_pattern = Pattern.compile("\\-\\-[a-z]+");
		Pattern currency_pattern = Pattern.compile("[A-Z]{3}");
		Pattern fieldabbr_pattern = Pattern.compile("[a-z]{2,3}");
		Pattern amount_pattern = Pattern
				.compile("[0-9]{1,3}((\\,)?[0-9]{3})*(.[0-9]{1,2})?");

		Matcher cmd_match = cmd_pattern.matcher(TokenString);
		Matcher currency_match = currency_pattern.matcher(TokenString);
		Matcher fieldabbr_match = fieldabbr_pattern.matcher(TokenString);
		Matcher amount_match = amount_pattern.matcher(TokenString);

		if (cmd_match.matches()) {
			result = TokenType.Command;
		} else if (currency_match.matches()) {
			result = TokenType.CurrencyUnit;
		} else if (fieldabbr_match.matches()) {
			result = TokenType.FieldAbbr;
		} else if (amount_match.matches()) {
			result = TokenType.Amount;
		}

		return result;
	}

	@Override
	public TokenSubtype getTokenSubtype(String TokenString,
			TokenType CurrentType) {
		TokenSubtype result = TokenSubtype.Unknown;

		if (CurrentType == TokenType.Command) {
			String cmd = TokenString.substring(2, TokenString.length());
			if (cmd.equals("help")) {
				result = TokenSubtype.CommandHelp;
			} else if (cmd.equals("show")) {
				result = TokenSubtype.CommandShow;
			} else if (cmd.equals("lastround")) {
				result = TokenSubtype.CommandLastRound;
			} else if (cmd.equals("convert")) {
				result = TokenSubtype.CommandConvert;
			} else if (cmd.equals("buycash")) {
				result = TokenSubtype.CommandBuyCash;
			} else if (cmd.equals("cellcash")) {
				result = TokenSubtype.CommandCellCash;
			} else if (cmd.equals("sendremit")) {
				result = TokenSubtype.CommandSendRemit;
			} else if (cmd.equals("recvremit")) {
				result = TokenSubtype.CommandRecvRemit;
			} else {
				result = TokenSubtype.Unknown;
			}
		} else if (CurrentType == TokenType.CurrencyUnit) {
			try {
				result = TokenSubtype.valueOf("Currency" + TokenString);
			} catch (IllegalArgumentException e) {
				result = TokenSubtype.Unknown;
			}
		} else if (CurrentType == TokenType.FieldAbbr) {
			if (TokenString.equals("all")) {
				result = TokenSubtype.FAAll;
			} else if (TokenString.equals("cr")) {
				result = TokenSubtype.FACentralRate;
			} else if (TokenString.equals("cb")) {
				result = TokenSubtype.FABuyCash;
			} else if (TokenString.equals("cc")) {
				result = TokenSubtype.FACellCash;
			} else if (TokenString.equals("rs")) {
				result = TokenSubtype.FASendRemit;
			} else if (TokenString.equals("rr")) {
				result = TokenSubtype.FARecvRemit;
			} else if (TokenString.equals("de")) {
				result = TokenSubtype.FADollarExcRate;
			} else {
				result = TokenSubtype.Unknown;
			}
		} else if (CurrentType == TokenType.Amount) {
			Pattern natural_pattern = Pattern
					.compile("[0-9]{1,3}((\\,)?[0-9]{3})*");
			Pattern fp_pattern = Pattern
					.compile("[0-9]{1,3}((\\,)?[0-9]{3})*\\.[0-9]{1,2}");

			Matcher natural_match = natural_pattern.matcher(TokenString);
			Matcher fp_match = fp_pattern.matcher(TokenString);

			if (natural_match.matches())
				result = TokenSubtype.AmountNatural;
			else if (fp_match.matches())
				result = TokenSubtype.AmountFp;
		}

		return result;
	}

	public TokenArray analysisTokenStream() throws EmptyTokenStreamException {
		TokenArray result = new TokenArray();

		String charstream = this.getTokenStringData();
		String[] temp = charstream.split("\\s");
		int len = temp.length;
		if (len == 0)
			throw new EmptyTokenStreamException();
		else {
			if (len == 1 && temp[0].length() == 0)
				throw new EmptyTokenStreamException();
			for (int i = 0; i < len; i++) {
				TokenType type = this.getTokenType(temp[i]);
				TokenSubtype subtype = this.getTokenSubtype(temp[i], type);
				TokenUnit newTokenUnit = new TokenUnit(temp[i], type, subtype);
				result.addElement(newTokenUnit);
			}
		}

		return result;
	}

}