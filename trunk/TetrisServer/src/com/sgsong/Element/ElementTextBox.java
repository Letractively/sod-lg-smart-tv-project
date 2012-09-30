
package com.sgsong.Element;

import com.sgsong.Element.ElementText;
import com.sgsong.Game.GameMain;
import com.sgsong.Struct.Def;

public class ElementTextBox
{
	public static final int GAP_WIDTH = 12; 
	public static final int GAP_HEIGHT = 16;
	
	private GameMain	m_pMain;
	
	private ElementText[] m_txt;
	
	private int		m_nTxtCount;
	
	private int m_nPosX;
	private int m_nPosY;
	
	private int m_nCntWidth;
	private int m_nCntHeight;
	
	public ElementTextBox()
	{
		m_pMain = null;
		m_nTxtCount = 0;
	}
	
	public void setGameMain( GameMain pMain )
	{
		m_pMain = pMain;		
	}
	
	public void createMe( int nCount )
	{
		m_nTxtCount = nCount;
		
		m_txt = new ElementText[nCount];
		
		for( int i=0; i<m_nTxtCount; i++ )
		{
			m_txt[i] = new ElementText();
			m_txt[i].InitMe( m_pMain, m_pMain.getSG() );
		//	m_txt[i].setID(Element.id_text);
			m_txt[i].setImageID(Def.game_font);
			m_txt[i].setPartCount(16, 16);
			m_txt[i].setText( 'a' );
			m_txt[i].setPos( 0, 0 );
			m_txt[i].setVisible(false);
			
			m_pMain.addElement( m_txt[i] , ElementList.middle );
		}		
	}
	
	public void setPos( int nPosX, int nPosY )
	{
		m_nPosX = nPosX;
		m_nPosY = nPosY;
	}
	
	public void setSize( int nCntWidth, int nCntHeight )
	{
		m_nCntWidth = nCntWidth;
		m_nCntHeight = nCntHeight;
	}
	
	public void setText( String strMsg )
	{
		int nCurX = m_nPosX;
		int nCurY = m_nPosY;
		
		int nCountOfWidth = 0;
		int nCountOfHeight = 0;
		
		int nCount = 0;
		char[] chTmp = strMsg.toCharArray();
		
		for( int i=0; i<chTmp.length; i++ )
		{
			if( nCount == m_nTxtCount )
				break;
			
			if( chTmp[i] == ' ' )
			{
				nCountOfWidth++;
				
				if( nCountOfWidth == m_nCntWidth )
				{
					nCountOfWidth = 0;
					nCountOfHeight++;				
				}
				
				continue;
			}
			
			nCurX = m_nPosX + nCountOfWidth * GAP_WIDTH;
			nCurY = m_nPosY + nCountOfHeight * GAP_HEIGHT;
			
			m_txt[nCount].setText( chTmp[i] );
			m_txt[nCount].setPos( nCurX, nCurY );
			m_txt[nCount].setVisible( true );
			
			nCount++;
			nCountOfWidth++;
			
			if( nCountOfWidth == m_nCntWidth )
			{
				nCountOfWidth = 0;
				nCountOfHeight++;				
			}
		}
	}
}
