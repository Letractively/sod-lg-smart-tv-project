
package com.sgsong.Game;

import com.sgsong.Struct.Def;

import com.sgsong.Draw.SG;

import com.sgsong.Element.Element;
import com.sgsong.Element.ElementList;

public class MessageBoxGoStop
{
	private GameMain	m_pMain = null;

	private Element		m_bg = new Element();
	private Element		m_go = new Element();
	private Element		m_stop = new Element();
	
	public MessageBoxGoStop()
	{		
	}	

	public void initMe( GameMain pMain, SG pSG )
	{
		m_pMain = pMain;

		m_bg.InitMe( m_pMain, pSG );
		m_bg.setID( Element.id_messege_box_gostop_bg );
		m_bg.setImageID( Def.message_box_gostop );	
		m_bg.setPos( 0, 0 );
		m_bg.setVisible( false );	
		m_bg.setAlpha( 0.7f );

		m_pMain.addElement( m_bg, ElementList.top );

		m_go.InitMe( m_pMain, pSG );
		m_go.setID( Element.id_messege_box_gostop_go );
		m_go.setImageID( Def.btn_go );	
		m_go.setTouch( true );
		m_go.setPos( 14, 96 );
		m_go.setVisible( false );	

		m_pMain.addElement( m_go, ElementList.top );

		m_stop.InitMe( m_pMain, pSG );
		m_stop.setID( Element.id_messege_box_gostop_stop );
		m_stop.setImageID( Def.btn_stop );
		m_stop.setTouch( true );
		m_stop.setPos( 113, 96 );
		m_stop.setVisible( false );	

		m_pMain.addElement( m_stop, ElementList.top );
	}
	
	public void setPos( int nX, int nY )
	{
		m_bg.setPos( nX, nY );
		m_go.setPos( nX + 14, nY + 96 );
		m_stop.setPos( nX +113, nY +96 );
	}

	public void show()
	{
		m_bg.setVisible( true );

		m_go.setVisible( true );
		m_stop.setVisible( true );
	}

	public void hide()
	{
		m_bg.setVisible( false );

		m_go.setVisible( false );
		m_stop.setVisible( false );
	}

	
}
