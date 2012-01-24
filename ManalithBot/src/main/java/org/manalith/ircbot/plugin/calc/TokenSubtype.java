/*
 	org.manalith.ircbot.plugin.calc/TokenSubtype.java
 	ManalithBot - An open source IRC bot based on the PircBot Framework.
 	Copyright (C) 2011  Seong-ho, Cho <darkcircle.0426@gmail.com>

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
package org.manalith.ircbot.plugin.calc;

public enum TokenSubtype {
	Unknown, Binary, Octal, Decimal, Hexadec, SpFltPoint, DpFltPoint, ExpFltPoint, Plus, Minus, Times, Divide, Modulus, Power, Factorial, Left_Parenthesis, Righ_Parenthesis, Sine, Cosine, Tangent, ArcSine, ArcCosine, ArcTangent, ToBin, ToOct, ToDec, ToHex, Sqrt
}
