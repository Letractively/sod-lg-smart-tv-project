
package com.sgsong.Game;

import com.sgsong.Struct.*;

import com.sgsong.Draw.SG_PT;
import com.sgsong.Draw.SG;

import com.sgsong.Element.Element;
import com.sgsong.Element.ElementList;
import com.sgsong.Element.Effect;

import com.sgsong.Sound.*;

import com.sgsong.Animation.*;

import com.sgsong.Game.GameMain;

public class EffectMan
{
	public static final int max_go = 6;
	
	private Effect		m_effect_jjock = new Effect();
	private Effect		m_effect_bbuck = new Effect();
	private Effect		m_effect_godori = new Effect();
	private Effect		m_effect_hongdan = new Effect();
	private Effect		m_effect_chungdan = new Effect();
	private Effect		m_effect_chodan = new Effect();
	
	private Effect		m_effect_ssul = new Effect();
	private Effect		m_effect_stop = new Effect();
	private Effect		m_effect_ddadac = new Effect();
	
	private Effect[] 	m_effect_go = new Effect[max_go];
	
	private Effect		m_cardImpact = new Effect();
	private Effect		m_eatBbuck = new Effect();
	
	private GameMain	m_pMain = null;

	private SG_PT		m_pt_jjock = new SG_PT();
	private SG_PT		m_pt_bbuck = new SG_PT();
	private SG_PT		m_pt_godori = new SG_PT();
	private SG_PT		m_pt_hongdan = new SG_PT();
	private SG_PT		m_pt_chungdan = new SG_PT();
	private SG_PT		m_pt_chodan = new SG_PT();
	
	private SG_PT		m_pt_ddadac = new SG_PT();
	private SG_PT		m_pt_ssul = new SG_PT();
	private SG_PT		m_pt_stop = new SG_PT();

	private SG_PT[]		m_pt_go = new SG_PT[max_go];	
	
	public EffectMan()
	{		
	}
	
	public void initMe( GameMain pMain, SG pSG )
	{
		m_pMain = pMain;

		initPosition();
		
		m_cardImpact.InitMe( m_pMain, pSG );
		m_cardImpact.setID( Element.id_effect_card_impact );
		m_cardImpact.setImageID( Def.card_impact );
		m_cardImpact.setSprite( 4, 1 );
		m_cardImpact.setAniID( Animation.id_effect_card_impact );
		m_cardImpact.setEasingCurve( EasingCurve.Linear );
		m_cardImpact.setTime( 0.2f );	
		m_cardImpact.setPos( 0, 0 );
		m_cardImpact.setVisible( false );
		m_pMain.addElement( m_cardImpact, ElementList.top );

		m_eatBbuck.InitMe( m_pMain, pSG );
		m_eatBbuck.setID( Element.id_effect_eat_bbuck );
		m_eatBbuck.setImageID( Def.eat_bbuck );
		m_eatBbuck.setSprite( 5, 2 );
		m_eatBbuck.setAniID( Animation.id_effect_eat_bbuck );
		m_eatBbuck.setEasingCurve( EasingCurve.Linear );
		m_eatBbuck.setTime( 0.3f );	
		m_eatBbuck.setPos( 0, 0 );
		m_eatBbuck.setVisible( false );
		m_pMain.addElement( m_eatBbuck, ElementList.top );	


		// ÂÊ
		m_effect_jjock.InitMe( m_pMain, pSG );
		m_effect_jjock.setID( Element.id_effect_jjock );
		m_effect_jjock.setImageID( Def.jjock );
		m_effect_jjock.setSprite( 5, 2 );
		m_effect_jjock.setEasingCurve( EasingCurve.InCirc );
		m_effect_jjock.setTime( 1.5f );
		m_effect_jjock.setAniID( Animation.id_effect_jjock );
		m_effect_jjock.setPos( m_pt_jjock.nX, m_pt_jjock.nY );	
		m_effect_jjock.setVisible( false );

		m_pMain.addElement( m_effect_jjock, ElementList.top );

		// »¶
		m_effect_bbuck.InitMe( m_pMain, pSG );
		m_effect_bbuck.setID( Element.id_effect_bbuck );
		m_effect_bbuck.setImageID( Def.bbuck );
		m_effect_bbuck.setSprite( 7, 1 );
		m_effect_bbuck.setTime( 1.5f );
		m_effect_bbuck.setAniID( Animation.id_effect_bbuck );
		m_effect_bbuck.setPos( m_pt_bbuck.nX, m_pt_bbuck.nY );	
		m_effect_bbuck.setVisible( false );

		m_pMain.addElement( m_effect_bbuck, ElementList.top );

		// °íµµ¸®
		m_effect_godori.InitMe( m_pMain, pSG );
		m_effect_godori.setID( Element.id_effect_godori );
		m_effect_godori.setImageID( Def.godori );
		m_effect_godori.setSprite( 3, 1 );
		m_effect_godori.setTime( 1.0f );
		m_effect_godori.setAniID( Animation.id_effect_godori );
		m_effect_godori.setPos( m_pt_godori.nX, m_pt_godori.nY );	
		m_effect_godori.setVisible( false );

		m_pMain.addElement( m_effect_godori, ElementList.top );

		// È«´Ü
		m_effect_hongdan.InitMe( m_pMain, pSG );
		m_effect_hongdan.setID( Element.id_effect_hongdan );
		m_effect_hongdan.setImageID( Def.hongdan );
		m_effect_hongdan.setSprite( 4, 2 );
		m_effect_hongdan.setEasingCurve( EasingCurve.OutCirc );
		m_effect_hongdan.setTime( 1.0f );
		m_effect_hongdan.setAniID( Animation.id_effect_hongdan );
		m_effect_hongdan.setPos( m_pt_hongdan.nX, m_pt_hongdan.nY );	
		m_effect_hongdan.setVisible( false );

		m_pMain.addElement( m_effect_hongdan, ElementList.top );

		// Ã»´Ü
		m_effect_chungdan.InitMe( m_pMain, pSG );
		m_effect_chungdan.setID( Element.id_effect_chodan );
		m_effect_chungdan.setImageID( Def.chungdan );
		m_effect_chungdan.setSprite( 5, 2 );
		m_effect_chungdan.setEasingCurve( EasingCurve.OutCirc );
		m_effect_chungdan.setTime( 1.0f );
		m_effect_chungdan.setAniID( Animation.id_effect_chungdan );
		m_effect_chungdan.setPos( m_pt_chungdan.nX, m_pt_chungdan.nY );	
		m_effect_chungdan.setVisible( false );

		m_pMain.addElement( m_effect_chungdan, ElementList.top );

		// ÃÊ´Ü
		m_effect_chodan.InitMe( m_pMain, pSG );
		m_effect_chodan.setID( Element.id_effect_chodan );
		m_effect_chodan.setImageID( Def.chodan );
		m_effect_chodan.setSprite( 5, 2 );
		m_effect_chodan.setEasingCurve( EasingCurve.OutCirc );
		m_effect_chodan.setTime( 1.0f );
		m_effect_chodan.setAniID( Animation.id_effect_chodan );
		m_effect_chodan.setPos( m_pt_chodan.nX, m_pt_chodan.nY );	
		m_effect_chodan.setVisible( false );

		m_pMain.addElement( m_effect_chodan, ElementList.top );
		
		// µûµü
		m_effect_ddadac.InitMe( m_pMain, pSG );
		m_effect_ddadac.setID( Element.id_effect_ddadac );
		m_effect_ddadac.setImageID( Def.ddadack );
		m_effect_ddadac.setEasingCurve( EasingCurve.OutCirc );
		m_effect_ddadac.setTime( 1.0f );
		m_effect_ddadac.setSprite( 4, 1 );
		m_effect_ddadac.setAniID( Animation.id_effect_ddadac );
		m_effect_ddadac.setPos( m_pt_ddadac.nX, m_pt_ddadac.nY );	
		m_effect_ddadac.setVisible( false );

		m_pMain.addElement( m_effect_ddadac, ElementList.top );

		// ¾µ
		m_effect_ssul.InitMe( m_pMain, pSG );
		m_effect_ssul.setID( Element.id_effect_ssul );
		m_effect_ssul.setImageID( Def.ssul );
		m_effect_ssul.setEasingCurve( EasingCurve.OutCirc );
		m_effect_ssul.setTime( 1.3f );
		m_effect_ssul.setSprite( 5, 1 );
		m_effect_ssul.setAniID( Animation.id_effect_ssul );
		m_effect_ssul.setPos( m_pt_ssul.nX, m_pt_ssul.nY );	
		m_effect_ssul.setVisible( false );

		m_pMain.addElement( m_effect_ssul, ElementList.top );


		// ½ºÅé
		m_effect_stop.InitMe( m_pMain, pSG );
		m_effect_stop.setID( Element.id_effect_stop );
		m_effect_stop.setImageID( Def.stop );
		m_effect_stop.setEasingCurve( EasingCurve.OutCirc );
		m_effect_stop.setTime( 1.0f );
		m_effect_stop.setSprite( 6, 1 );
		m_effect_stop.setAniID( Animation.id_effect_stop );
		m_effect_stop.setPos( m_pt_stop.nX, m_pt_stop.nY );	
		m_effect_stop.setVisible( false );

		m_pMain.addElement( m_effect_stop, ElementList.top );

		// GO
		for( int i=0; i<max_go; i++ )
		{
			m_effect_go[i] = new Effect();
			
			m_effect_go[i].InitMe( m_pMain, pSG );
			m_effect_go[i].setID( Element.id_effect_1go + i );
			m_effect_go[i].setImageID( Def.go_one + i );
			
			m_effect_go[i].setPos( m_pt_go[i].nX, m_pt_go[i].nY );	
			m_effect_go[i].setVisible( false );

			m_pMain.addElement( m_effect_go[i], ElementList.top );
		}

		m_effect_go[0].setAniID( Animation.id_effect_1go );
		m_effect_go[0].setSprite( 3, 1 );
		m_effect_go[0].setTime( 1.0f );

		m_effect_go[1].setAniID( Animation.id_effect_2go );
		m_effect_go[1].setSprite( 3, 1 );
		m_effect_go[1].setTime( 1.0f );

		m_effect_go[2].setAniID( Animation.id_effect_3go );
		m_effect_go[2].setSprite( 3, 1 );
		m_effect_go[2].setTime( 1.0f );

		m_effect_go[3].setAniID( Animation.id_effect_4go );
		m_effect_go[3].setSprite( 3, 1 );
		m_effect_go[3].setTime( 1.0f );

		m_effect_go[4].setAniID( Animation.id_effect_5go );
		m_effect_go[4].setSprite( 5, 2 );
		m_effect_go[4].setTime( 1.7f );

		m_effect_go[5].setAniID( Animation.id_effect_6go );
		m_effect_go[5].setSprite( 5, 2 );
		m_effect_go[5].setTime( 1.7f );

	}

	public void initPosition()
	{
		m_pt_jjock.nX = 200;
		m_pt_jjock.nY = 100;

		m_pt_bbuck.nX = 200;
		m_pt_bbuck.nY = 100;

		m_pt_chodan.nX = 200;
		m_pt_chodan.nY = 100;

		m_pt_chungdan.nX = 200;
		m_pt_chungdan.nY = 100;

		m_pt_hongdan.nX = 200;
		m_pt_hongdan.nY = 100;

		m_pt_godori.nX = 200;
		m_pt_godori.nY = 100;

		m_pt_ssul.nX = 200;
		m_pt_ssul.nY = 100;

		m_pt_stop.nX = 200;
		m_pt_stop.nY = 100;

		m_pt_ddadac.nX = 200;
		m_pt_ddadac.nY = 100;

		for( int i=0; i<max_go; i++ )
		{
			m_pt_go[i] = new SG_PT();
			m_pt_go[i].nX = 200;
			m_pt_go[i].nY = 100;
		}
	}

	public void onEndAni( int nID )
	{
		if( nID == Animation.id_effect_jjock )
		{
			m_effect_jjock.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_bbuck )
		{
			m_effect_bbuck.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_chodan )
		{
			m_effect_chodan.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_chungdan )
		{
			m_effect_chungdan.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_hongdan )
		{
			m_effect_hongdan.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_godori )
		{
			m_effect_godori.setVisible( false );
			return;
		}
		
		if( nID == Animation.id_effect_stop )
		{
			m_effect_stop.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_ssul )
		{
			m_effect_ssul.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_ddadac )
		{
			m_effect_ddadac.setVisible( false );
			return;
		}
		

		if( nID == Animation.id_effect_card_impact )
		{
			m_cardImpact.setVisible( false );
			return;
		}

		if( nID == Animation.id_effect_eat_bbuck )
		{
			m_eatBbuck.setVisible( false );
			return;
		}


	}

	public void showChungDan()
	{
		m_effect_chungdan.act();
		m_pMain.playSound( GameSound.dan );
	}

	public void showHongDan()
	{
		m_effect_hongdan.act();
		m_pMain.playSound( GameSound.dan );
	}

	public void showChoDan()
	{
		m_effect_chodan.act();
		m_pMain.playSound( GameSound.dan );
	}

	public void showGodori()
	{
		m_effect_godori.act();
		m_pMain.playSound( GameSound.godori );
	}

	public void showJJock()
	{
		m_effect_jjock.act();
		m_pMain.playSound( GameSound.jjock );
	}

	public void showBBuck()
	{
		m_effect_bbuck.act();
		m_pMain.playSound( GameSound.bbuck );
	}

	public void showGo( int nCount )
	{
		if( nCount < 1 )
			nCount = 1;

		if( nCount > max_go )
		{
			nCount = max_go;
		}

		m_effect_go[nCount-1].act();

		m_pMain.playSound( GameSound.go_one + (nCount-1) );
	}

	public void hideGo()
	{
		for( int i=0; i<max_go; i++ )
		{
			m_effect_go[i].setVisible( false );
		}
	}	

	public void showStop()
	{
		m_effect_stop.act();
		m_pMain.playSound( GameSound.stop );
	}

	public void showDdadac()
	{
		m_effect_ddadac.act();
		m_pMain.playSound( GameSound.ddadac );
	}

	public void showSsul()
	{
		m_effect_ssul.act();
		m_pMain.playSound( GameSound.ssul );
	}
	

	public void showCardImpact( int nPosX, int nPosY )
	{
		m_cardImpact.setPos( nPosX-30, nPosY-20 );
		m_cardImpact.act();
	}

	public void showEatBBuckCard( int nPosX, int nPosY )
	{
		m_eatBbuck.setPos( nPosX-52, nPosY-60 );
		m_eatBbuck.act();	
	}


}