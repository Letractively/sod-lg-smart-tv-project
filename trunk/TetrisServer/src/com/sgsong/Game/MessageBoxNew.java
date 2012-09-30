
package com.sgsong.Game;

import com.sgsong.Struct.Def;

import com.sgsong.Draw.SG;

import com.sgsong.Element.Element;
import com.sgsong.Element.ElementPart;
import com.sgsong.Element.ElementList;

public class MessageBoxNew
{
	private GameMain	m_pMain = null;

	private Element		m_bg = new Element();
	private ElementPart	m_resultMark = new ElementPart();
	private ElementPart		m_button = new ElementPart();
	
	public MessageBoxNew()
	{		
	}	

	public void initMe( GameMain pMain, SG pSG )
	{
		m_pMain = pMain;

		m_bg.InitMe( m_pMain, pSG );
		m_bg.setID( Element.id_messege_box );	
		m_bg.setPos( 0, 0 );
		m_bg.setVisible( false );	
		m_bg.setAlpha( 0.7f );


		m_pMain.addElement( m_bg, ElementList.top );	

		m_resultMark.InitMe( m_pMain, pSG );
		m_resultMark.setID( Element.id_mark_win_lose );
		m_resultMark.setImageID( Def.mark_win_lose );
		m_resultMark.setPartCount( 2, 1 );
		m_resultMark.setPart( 0 );	
		m_resultMark.setPos( 0, 0 );
		m_resultMark.setVisible( false );	

		m_pMain.addElement( m_resultMark, ElementList.top );	

		m_button.InitMe( m_pMain, pSG );
		m_button.setID( Element.id_messege_box_ok );
		m_button.setImageID( Def.message_box_buttons );
		m_button.setPartCount( 1, 12 );
		m_button.setPart( 0 );
		m_button.setTouch( true );
		m_button.setPos( 0, 0 );
		m_button.setVisible( false );	

		m_pMain.addElement( m_button, ElementList.top );
	}
	
	public void setWin()
	{
		m_resultMark.setPart( 1 );
	}

	public void setLose()
	{
		m_resultMark.setPart( 0 );
	}

	public void setBG( int name )
	{
		m_bg.setImageID( name );
	}

	public void setPos( int nX, int nY )
	{
		m_bg.setPos( nX, nY );
		m_button.setPos( nX + 106, nY + 247 );
		m_resultMark.setPos( nX + 104 , nY + 144 );
	}

	public void show()
	{
		m_bg.setVisible( true );

		m_resultMark.setVisible( true );
		m_button.setVisible( true );	
	}

	public void hide()
	{
		m_bg.setVisible( false );

		m_resultMark.setVisible( false );
		m_button.setVisible( false );	
	}	
}
