
package com.sgsong.Game;

import com.sgsong.Element.ElementPart;

public class Card extends ElementPart
{
	public static final int MAX_CARD = 48;
	public static final int MAX_BOMB_CARD = 12;

	public static final int MINI_GAP_W = 17;
	public static final int MINI_GAP_H = 23;

	public static final int GAP_W = 41;
	public static final int GAP_H = 60;

	public static final int back_index = 3;

	public static final int type_one_pi = 1000;
	public static final int type_two_pi = 1010;
	public static final int type_three_pi = 1020;

	public static final int type_ddi_bi = 1030;
	public static final int type_ddi_chung = 1040;
	public static final int type_ddi_hong = 1050;
	public static final int type_ddi_cho = 1060;

	public static final int type_mung_normal = 1070;
	public static final int type_mung_sae = 1080;
	public static final int type_mung_pig = 1090;

	public static final int type_kwnag = 1100;
	public static final int type_kwnag_bi = 1110;

	public static final int type_pimung = 1120;

	public static final int num_1 = 1;
	public static final int num_2 = 2;
	public static final int num_3 = 3;
	public static final int num_4 = 4;
	public static final int num_5 = 5;

	public static final int num_6 = 6;
	public static final int num_7 = 7;
	public static final int num_8 = 8;
	public static final int num_9 = 9;
	public static final int num_10 = 10;

	public static final int num_11 = 11;
	public static final int num_12 = 12;	
			
	private		int m_nType;
	private		int m_nNumber;

	private		int m_nBack;
	private		int m_nFront;

	private		boolean	m_bFront;	
	
	public Card()
	{		
		super();

		m_nType = type_one_pi;
		m_nNumber = num_1;

		m_bFront = false;
	}

	public void setType( int nType )
	{
		m_nType = nType;
	}

	public void setNumber( int nNum )
	{
		m_nNumber = nNum;
	}

	public void setBackCard( int nPart )
	{
		m_nBack = nPart;
	}

	public void setFrontCard( int nPart )
	{
		m_nFront = nPart;
	}

	public void setBack()
	{
		m_bFront = false;

		setPart( m_nBack );
	}

	public void setFront()
	{
		m_bFront = true;

		setPart( m_nFront );
	}
	
	public int getNumber()
	{
		return m_nNumber;
	}

	public boolean isKwang()
	{
		if( (m_nType >= type_kwnag) && (m_nType <= type_kwnag_bi) )
			return true;
		
		return false;
	}

	public boolean isDDi()
	{
		if( (m_nType >= type_ddi_bi) && (m_nType <= type_ddi_cho) )
			return true;

		return false;
	}

	public boolean isPi()
	{
		if( (m_nType >= type_one_pi) && (m_nType <= type_three_pi) )
			return true;

		return false;
	}

	public int getType()
	{
		return m_nType;
	}

	public boolean isMung()
	{
		if( (m_nType >= type_mung_normal) && (m_nType <= type_mung_pig) )
			return true;

		return false;
	}
}