
package com.sgsong.Sound;


public class GameSound
{
	public static final int MAX_LIST = 10;	
	
	public static final int MAX_SIZE = 16;
	
	public static final int card_send = 0;
	public static final int card_hit = 1;	
	public static final int bomb = 2;
	
	public static final int bbuck = 3;
	
	public static final int dan = 4;
	public static final int ddadac = 5;
	public static final int godori = 6;	
	public static final int jjock = 7;
	public static final int ssul = 8;
	
	public static final int go_one = 9;	
	public static final int go_two = 10;
	public static final int go_three = 11;
	public static final int go_four = 12;	
	public static final int go_five = 13;
	public static final int go_six = 14;
	
	public static final int stop = 15;
	
	private SND_INFO[] m_sound = new SND_INFO[MAX_LIST];

	private long m_curTime;
	
	public GameSound()
	{
		m_curTime = 0;

		for( int i=0; i<MAX_LIST; i++ )
		{
			m_sound[i] = new SND_INFO();
			m_sound[i].bSet = false;
			m_sound[i].nID = -1;
			m_sound[i].startTime = 0;
		}
	}	

	public void setCurTime( long curTime )
	{
		m_curTime = curTime;
	}

	public int getID()
	{
		int nID = m_sound[0].nID;

		for( int i=0; i<MAX_LIST-1; i++ )
		{
			m_sound[i].bSet = m_sound[i+1].bSet;
			m_sound[i].nID = m_sound[i+1].nID;
			m_sound[i].startTime = m_sound[i+1].startTime;
		}

		m_sound[MAX_LIST-1].bSet = false;
		m_sound[MAX_LIST-1].nID = -1;
		m_sound[MAX_LIST-1].startTime = 0;

		return nID;
	}

	public boolean isRegistSound()
	{
		if( (true == m_sound[0].bSet) && (m_sound[0].startTime <= m_curTime) )
			return true;

		return false;
	}

	public void playSoundAfter( int nID, float fSec )
	{
		int nIndex = MAX_LIST - 1;
		for( int i=0; i<MAX_LIST; i++ )
		{
			if( false == m_sound[i].bSet )
			{
				nIndex = i;
				break;
			}
		}
		
		m_sound[nIndex].bSet = true;
		m_sound[nIndex].startTime = m_curTime + (int)(fSec * 1000);
		m_sound[nIndex].nID = nID;
	}

	public void playSound( int nID )
	{		
		int nIndex = MAX_LIST - 1;
		for( int i=0; i<MAX_LIST; i++ )
		{
			if( false == m_sound[i].bSet )
			{
				nIndex = i;
				break;
			}
		}

		m_sound[nIndex].bSet = true;
		m_sound[nIndex].startTime = m_curTime;
		m_sound[nIndex].nID = nID;
	}
}