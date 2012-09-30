
package com.sgsong.Element;


public class Button extends ElementPart
{
	public static final int MAX_STATE = 3;
	
	public static final int state_disable = 0;
	public static final int state_normal = 1;	
	public static final int state_down = 2;	
	
	private int[] m_nStateIndex = new int[MAX_STATE];
	
	public Button()
	{
		for( int i=0; i<MAX_STATE; i++ )
		{
			m_nStateIndex[i] = 0;
		}
		
		setStyle_1Button();
	}
	
	public void setStyle_1Button()
	{
		m_nStateIndex[state_disable] = 0;
		m_nStateIndex[state_normal] = 0;
		m_nStateIndex[state_down] = 0;	
	}
	
	public void setStyle_2Button()
	{
		m_nStateIndex[state_disable] = 0;
		m_nStateIndex[state_normal] = 0;
		m_nStateIndex[state_down] = 1;	
	}
	
	public void setStyle_3Button()
	{
		m_nStateIndex[state_disable] = 0;
		m_nStateIndex[state_normal] = 1;
		m_nStateIndex[state_down] = 2;	
	}	

	public void setEnable( boolean bSet )
	{
		setTouch( bSet );

		if( true == bSet )
		{
			setPart( m_nStateIndex[state_normal] );
		}
		else
		{
			setPart( m_nStateIndex[state_disable] );
		}
	}

	public void setNormal()
	{
		setPart( m_nStateIndex[state_normal] );
	}

	public void setDown()
	{
		setPart( m_nStateIndex[state_down] );
	}	
}
