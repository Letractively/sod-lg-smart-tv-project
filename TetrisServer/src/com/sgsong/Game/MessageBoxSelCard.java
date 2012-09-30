
package com.sgsong.Game;

import com.sgsong.Struct.Def;

import com.sgsong.Draw.SG;

import com.sgsong.Element.Element;
import com.sgsong.Element.ElementList;

//import com.sgsong.Animation.EasingCurve;

public class MessageBoxSelCard
{
	private GameMain	m_pMain = null;

	private int			m_nSelFirstID;
	private int			m_nSelSecondID;

	private Card		m_select_card_bg = new Card();
	private Card		m_select_card1 = new Card();
	private Card		m_select_card2 = new Card();
	
	public MessageBoxSelCard()
	{		
	}
	
	public void initMe( GameMain pMain, SG pSG )
	{
		m_pMain = pMain;

		m_nSelFirstID = -1;
		m_nSelSecondID = -1;

		m_select_card_bg.InitMe( m_pMain, pSG );
		m_select_card_bg.setID( Element.id_select_bg );
		m_select_card_bg.setImageID( Def.message_box_select_card );	
		m_select_card_bg.setPos( 0, 0 );
		m_select_card_bg.setVisible( false );	
		m_select_card_bg.setAlpha( 0.7f );


		m_pMain.addElement( m_select_card_bg, ElementList.top );

		m_select_card1.InitMe( m_pMain, pSG );
		m_select_card1.setID( Element.id_select_card1 );
		m_select_card1.setImageID( Def.gostop_card );	
		m_select_card1.setPartCount( 4, 13 );
		m_select_card1.setFront();
		m_select_card1.setVisible( false );
		m_select_card1.setPos( 43, 59 );

		m_pMain.addElement( m_select_card1, ElementList.top );

		m_select_card2.InitMe( m_pMain, pSG );
		m_select_card2.setID( Element.id_select_card2 );
		m_select_card2.setImageID( Def.gostop_card );	
		m_select_card2.setPartCount( 4, 13 );
		m_select_card2.setFront();
		m_select_card2.setVisible( false );
		m_select_card2.setPos( 115, 59 );

		m_pMain.addElement( m_select_card2, ElementList.top );
	}
	
	public void setPos( int nX, int nY )
	{
		m_select_card_bg.setPos( nX, nY );
		m_select_card1.setPos( nX + 43, nY + 59 );
		m_select_card2.setPos( nX + 115, nY + 59 );
	}

	public void setFirstCard( Card pCard )
	{
		m_nSelFirstID = pCard.getID();
		m_select_card1.setPart( pCard.getPart() );
	}

	public void setSecondCard( Card pCard )
	{
		m_nSelSecondID = pCard.getID();
		m_select_card2.setPart( pCard.getPart() );
	}

	public void show()
	{
		m_select_card_bg.setVisible( true );	

		m_select_card1.setVisible( true );
		m_select_card1.setTouch( true );

		m_select_card2.setVisible( true );
		m_select_card2.setTouch( true );

	//	m_select_card_bg.setAlphaAni( 0, EasingCurve.InCubic, 0.1f, 1.0f, 0.3f );
	//	m_select_card_bg.setScaleAni( 0, EasingCurve.InCubic, 0.1f, 1.0f, 0.3f );

	//	m_select_card1.setAlphaAni( 0, EasingCurve.InCubic, 0.1f, 1.0f, 0.3f );
	//	m_select_card1.setScaleAni( 0, EasingCurve.InCubic, 0.1f, 1.0f, 0.3f );

	//	m_select_card2.setAlphaAni( 0, EasingCurve.InCubic, 0.1f, 1.0f, 0.3f );
	//	m_select_card2.setScaleAni( 0, EasingCurve.InCubic, 0.1f, 1.0f, 0.3f );
	}

	public void hide()
	{
		m_select_card_bg.setVisible( false );	

		m_select_card1.setVisible( false );
		m_select_card1.setTouch( false );

		m_select_card2.setVisible( false );
		m_select_card2.setTouch( false );
	}
}