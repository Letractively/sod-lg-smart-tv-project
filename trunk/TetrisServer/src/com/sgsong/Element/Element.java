
package com.sgsong.Element;

import com.sgsong.Animation.Animation;
import com.sgsong.Draw.SG;
import com.sgsong.Draw.SG_COLOR;
import com.sgsong.Draw.SG_RECT;
import com.sgsong.Game.GameMain;
import com.sgsong.Struct.Def;

//import android.util.Log;

public class Element
{	
	public static final int id_loading_bg = 10;
	public static final int id_loading_bar = 11;

	public static final int id_bg = 100;

	public static final int id_dummy = 200;

	public static final int id_mini_card_0 = 300;
	public static final int id_mini_card_9 = 309;

	public static final int id_card_sol_kwang = 400;
	public static final int id_card_sol_hong = 401;
	public static final int id_card_sol_one_pi1 = 402;
	public static final int id_card_sol_one_pi2 = 403;

	public static final int id_card_2_sae = 404;
	public static final int id_card_2_hong = 405;
	public static final int id_card_2_one_pi1 = 406;
	public static final int id_card_2_one_pi2 = 407;		

	public static final int id_card_3_kwang = 408;
	public static final int id_card_3_hong = 409;
	public static final int id_card_3_one_pi1 = 410;
	public static final int id_card_3_one_pi2 = 411;

	public static final int id_card_black_sae = 412;
	public static final int id_card_black_ddi = 413;
	public static final int id_card_black_one_pi1 = 414;
	public static final int id_card_black_one_pi2 = 415;

	public static final int id_card_cho_mung = 416;
	public static final int id_card_cho_ddi = 417;
	public static final int id_card_cho_one_pi1 = 418;
	public static final int id_card_cho_one_pi2 = 419;

	public static final int id_card_moc_mung = 420;
	public static final int id_card_moc_chung = 421;
	public static final int id_card_moc_one_pi1 = 422;
	public static final int id_card_moc_one_pi2 = 423;		

	public static final int id_card_pig_mung = 424;
	public static final int id_card_pig_ddi = 425;
	public static final int id_card_pig_one_pi1 = 426;
	public static final int id_card_pig_one_pi2 = 427;		

	public static final int id_card_8_kwang = 428;
	public static final int id_card_8_sae = 429;
	public static final int id_card_8_one_pi1 = 430;
	public static final int id_card_8_one_pi2 = 431;		

	public static final int id_card_9_two_pi = 432;
	public static final int id_card_9_chung = 433;
	public static final int id_card_9_one_pi1 = 434;
	public static final int id_card_9_one_pi2 = 435;

	public static final int id_card_10_mung = 436;
	public static final int id_card_10_chung = 437;
	public static final int id_card_10_one_pi1 = 438;
	public static final int id_card_10_one_pi2 = 439;

	public static final int id_card_ddong_kwang = 440;
	public static final int id_card_ddong_two_pi = 441;
	public static final int id_card_ddong_one_pi1 = 442;
	public static final int id_card_ddong_one_pi2 = 443;		

	public static final int id_card_bi_kwang = 444;
	public static final int id_card_bi_ddi = 445;
	public static final int id_card_bi_mung = 446;
	public static final int id_card_bi_two_pi = 447;		

	public static final int id_card_bomb_0 = 600;
	public static final int id_card_bomb_11 = 611;

	public static final int id_btn_start = 700;
	public static final int id_btn_wait = 760;

	public static final int id_effect_jjock = 800;
	public static final int id_effect_bbuck = 810;
	public static final int id_effect_godori = 820;
	public static final int id_effect_hongdan = 830;
	public static final int id_effect_chungdan = 840;		

	public static final int id_effect_chodan = 850;
	public static final int id_effect_card_impact = 860;

	public static final int id_effect_eat_bbuck = 870;
	public static final int id_effect_ssul = 880;
	public static final int id_effect_ddadac = 890;

	public static final int id_effect_1go = 1000;
	public static final int id_effect_2go = 1001;
	public static final int id_effect_3go = 1002;
	public static final int id_effect_4go = 1003;
	public static final int id_effect_5go = 1004;
	public static final int id_effect_6go = 1005;

	public static final int id_effect_stop = 1100;

	public static final int id_select_bg = 1200;
	public static final int id_select_card1 = 1210;
	public static final int id_select_card2 = 1220;

	public static final int id_messege_box_gostop_bg = 1300;
	public static final int id_messege_box_gostop_go = 1310;
	public static final int id_messege_box_gostop_stop = 1320;

	public static final int id_messege_box = 1400;
	public static final int id_messege_box_ok = 1410;

	public static final int id_number = 1500;

	public static final int id_turn_mark = 1600;
	public static final int id_sun_mark = 1610;
	public static final int id_mark_win_lose = 1620;

	protected int		m_nOrWidth;
	protected int		m_nOrHeight;

	protected SG_COLOR	m_color = new SG_COLOR();
	protected SG_RECT	m_rect = new SG_RECT();

	protected float	m_rotate;
	protected float	m_scale;

	protected int		m_nID;

	protected boolean	m_bVisible;

	protected SG		m_pSG = null;

	protected GameMain	m_pMain = null;

	protected int		m_imgID;
	
	protected Animation	m_ani = null;

	protected boolean		m_bTouchable;

	protected boolean		m_bRealRect;
	protected SG_RECT		m_Realrect = new SG_RECT();

	protected int			m_nRealWidth;
	protected int			m_nRealHeight;
	
	protected boolean		m_bAnimation = false;
	
	public Element()
	{
		initVariable();
	}
	
	public void InitMe( GameMain pMain, SG pSG )
	{
		m_pMain = pMain;
		m_pSG = pSG;	
	}
	
	public void addOnAnimation()
	{
		m_ani = new Animation();
		
		m_bAnimation = true;		
		m_ani.Init( m_pMain );
	}
	
	public void initVariable()
	{
		m_bAnimation = false;
		
		m_nOrWidth = 0;
		m_nOrHeight = 0;	

		m_color.fRed = 1.0f;
		m_color.fGreen = 1.0f;
		m_color.fBlue = 1.0f;
		m_color.fAlpha = 1.0f;

		m_rotate = 0.0f;
		m_scale = 1.0f;

		m_nID = -1;

		m_bVisible = false;	
		m_bTouchable = false;
		m_bRealRect = false;

		m_nRealWidth = 0;
		m_nRealHeight = 0;

		m_imgID = 0;		

		m_rect.nLeft = 0;
		m_rect.nTop = 0;
		m_rect.nRight = 0;
		m_rect.nBottom = 0;	
	}
	
	public int getPosX()
	{
		return m_rect.nLeft;
	}

	public int getPosY()
	{
		return m_rect.nTop;
	}

//	public void setMoveAni( int nID, int nCurveType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec, boolean bRepeat )
	public void setMoveAni( int nID, int nCurveType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
	//	m_ani.setMove( nID, nCurveType, nStartX, nStartY, nEndX, nEndY, fSec, bRepeat );
		m_ani.setMove( nID, nCurveType, nStartX, nStartY, nEndX, nEndY, fSec, false );
	}

	public void setMoveAniStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearMove();
	}
	
//	public void setCenterAni( int nID, int nCurveType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec, boolean bRepeat )
	public void setCenterAni( int nID, int nCurveType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
	//	m_ani.setCenter( nID, nCurveType, nStartX, nStartY, nEndX, nEndY, fSec, bRepeat );
		m_ani.setCenter( nID, nCurveType, nStartX, nStartY, nEndX, nEndY, fSec, false );
	}

	public void setCenterAniStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearCenter();
	}

//	public void setPartAni( int nID, int nCurveType, int nStart, int nEnd, float fSec, boolean bRepeat, boolean bLoop )
	public void setPartAni( int nID, int nCurveType, int nStart, int nEnd, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
	//	m_ani.setPart( nID, nCurveType, nStart, nEnd, fSec, bRepeat, bLoop );
		m_ani.setPart( nID, nCurveType, nStart, nEnd, fSec, false, false );
	}

	public void setPartAniStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearPart();
	}

//	public void setChangeValueAni( int nID, int nCurveType, int nType, int nStart, int nEnd, float fSec, boolean bRepeat )
	public void setChangeValueAni( int nID, int nCurveType, int nType, int nStart, int nEnd, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
	//	m_ani.setChangeValue( nID, nCurveType, nType, nStart, nEnd, fSec, bRepeat );
		m_ani.setChangeValue( nID, nCurveType, nType, nStart, nEnd, fSec, false );
	}

	public void setChangeValueStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearChangeValue();
	}

//	public void setAlphaAni( int nID, int nCurveType, float fStart, float fEnd, float fSec, boolean bRepeat, boolean bLoop )
	public void setAlphaAni( int nID, int nCurveType, float fStart, float fEnd, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.setAlpha( nID, nCurveType, fStart, fEnd, fSec, false, false );
	}

	public void setAlphaStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearAlpha();
	}	
	
//	public void setScaleAni( int nID, int nCurveType, float fStart, float fEnd, float fSec, boolean bRepeat, boolean bLoop )
	public void setScaleAni( int nID, int nCurveType, float fStart, float fEnd, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.setScale( nID, nCurveType, fStart, fEnd, fSec, false, false );
	}

	public void setScaleStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearScale();
	}

//	public void setRotateAni( int nID, int nCurveType, float fStart, float fEnd, float fSec, boolean bRepeat )
	public void setRotateAni( int nID, int nCurveType, float fStart, float fEnd, float fSec )
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
	//	m_ani.setRotate( nID, nCurveType, fStart, fEnd, fSec, bRepeat );
		m_ani.setRotate( nID, nCurveType, fStart, fEnd, fSec, false );
	}

	public void setRotateStop()
	{
		if( false == m_bAnimation )
		{
			return;
		}
		
		m_ani.clearRotate();
	}	
	
	public void DrawMe( long curTime )
	{
		if( true == m_bAnimation )
		{
			// animation
			m_ani.setCurTime( curTime );
		}
		
		if( (m_pSG == null) || (m_imgID == -1) || (m_nID == -1) )
			return;

		if( m_bVisible == false )
			return;		
		
		if( false == m_bAnimation )
		{
			m_pSG.DrawFullImage( m_imgID, m_rect, m_scale, m_rotate, m_color );
			return;
		}		
		
		if( true == m_ani.isMove() )
		{
			setPos( m_ani.getX(), m_ani.getY() );		
		}
		
		if( true == m_ani.isCenter() )
		{
			setCenter( m_ani.getCenterX(), m_ani.getCenterY() );		
		}

		if( true == m_ani.isAlpha() )
		{
			setAlpha( m_ani.getAlpha() );
		}

		// rotate
		if( true == m_ani.isRotate() )
		{
			setRotate( m_ani.getRotate() );
		}

		// scale
		if( true == m_ani.isScale() )
		{
			setScale( m_ani.getScale() );
		}

		m_pSG.DrawFullImage( m_imgID, m_rect, m_scale, m_rotate, m_color );
	}

	public void setAlpha( float fAlpha )
	{
		m_color.fAlpha = fAlpha;
	}
	
	public void setColor( int nColor )
	{
		if( nColor == Def.sg_color_red )
		{
			m_color.fRed = 1.0f;
			m_color.fGreen = 0.0f;
			m_color.fBlue = 0.0f;
			return;
		}

		if( nColor == Def.sg_color_green )
		{
			m_color.fRed = 0.0f;
			m_color.fGreen = 1.0f;
			m_color.fBlue = 0.0f;
			return;
		}

		if( nColor == Def.sg_color_blue )
		{
			m_color.fRed = 0.0f;
			m_color.fGreen = 0.0f;
			m_color.fBlue = 1.0f;
			return;
		}

		if( nColor == Def.sg_color_black )
		{
			m_color.fRed = 0.0f;
			m_color.fGreen = 0.0f;
			m_color.fBlue = 0.0f;
			return;
		}
		
		if( nColor == Def.sg_color_yellow )
		{
			m_color.fRed = 1.0f;
			m_color.fGreen = 1.0f;
			m_color.fBlue = 0.0f;
			return;
		}
		
		if( nColor == Def.sg_color_normal )
		{
			m_color.fRed = 1.0f;
			m_color.fGreen = 1.0f;
			m_color.fBlue = 1.0f;
			return;
		}
	}

	public void setScale( float fScale )
	{
		m_scale = fScale;
	}

	public void setRotate( float fRotate )
	{
		m_rotate = fRotate;
	}

	public void setCenter( int nX, int nY )
	{
		m_rect.nCenterX = nX;
		m_rect.nCenterY = nY;

		int nWidth = m_rect.nRight - m_rect.nLeft;
		int nHeight = m_rect.nBottom - m_rect.nTop;	

		if( (nWidth % 2) == 1 )
		{		
			int nLeftWidth = (nWidth-1) / 2 + 1;
			m_rect.nLeft = m_rect.nCenterX - nLeftWidth;
		}
		else
		{		
			m_rect.nLeft = m_rect.nCenterX - (nWidth / 2);
		}

		if( (nHeight % 2) == 1 )
		{
			int nTopWidth = (nHeight-1) / 2 + 1;
			m_rect.nTop = m_rect.nCenterY - nTopWidth;
		}
		else
		{
			m_rect.nTop = m_rect.nCenterY - (nHeight / 2);
		}

		m_rect.nRight = m_rect.nLeft + nWidth;
		m_rect.nBottom = m_rect.nTop + nHeight;

		m_Realrect.nLeft = m_rect.nCenterX - m_nRealWidth / 2;
		m_Realrect.nTop = m_rect.nCenterY - m_nRealHeight / 2;
		m_Realrect.nRight = m_rect.nCenterX + m_nRealWidth / 2;
		m_Realrect.nBottom = m_rect.nCenterY + m_nRealHeight / 2;
	}

	public int getCenterX()
	{
		return m_rect.nCenterX;
	}

	public int getCenterY()
	{
		return m_rect.nCenterY;
	}

	public void setPos( int nX, int nY )
	{
		int nWidth = m_rect.nRight - m_rect.nLeft;
		int nHeight = m_rect.nBottom - m_rect.nTop;

		m_rect.nLeft = nX;
		m_rect.nTop = nY;
		m_rect.nRight = m_rect.nLeft + nWidth;
		m_rect.nBottom = m_rect.nTop + nHeight;

		if( (nWidth % 2) == 1 )
		{
			int nLeftWidth = (nWidth-1) / 2 + 1;
			m_rect.nCenterX = m_rect.nLeft + nLeftWidth;
		}
		else
		{
			m_rect.nCenterX = m_rect.nLeft + nWidth / 2;
		}

		if( (nHeight % 2) == 1 )
		{
			int nTopWidth = (nHeight-1) / 2 + 1;
			m_rect.nCenterY = m_rect.nTop + nTopWidth;		
		}
		else
		{
			m_rect.nCenterY = m_rect.nTop + nHeight / 2;
		}

		m_Realrect.nLeft = m_rect.nCenterX - m_nRealWidth / 2;
		m_Realrect.nTop = m_rect.nCenterY - m_nRealHeight / 2;
		m_Realrect.nRight = m_rect.nCenterX + m_nRealWidth / 2;
		m_Realrect.nBottom = m_rect.nCenterY + m_nRealHeight / 2;
	}

	public void setOrSize( int nWidth, int nHeight )
	{
		m_nOrWidth = nWidth;
		m_nOrHeight = nHeight;
	}	

	public void setSize( int nWidth, int nHeight )
	{	
		m_rect.nRight = m_rect.nLeft + nWidth;
		m_rect.nBottom = m_rect.nTop + nHeight;
	}

	public void setID( int nID )
	{	
		m_nID = nID;
	}

	public void setImageID( int nID )
	{
		m_imgID = nID;

		setOrSize( m_pSG.getImg(nID).nWidth, m_pSG.getImg(nID).nHeight );
		setSize( m_pSG.getImg(nID).nWidth, m_pSG.getImg(nID).nHeight );
	}

	public void setVisible( boolean bSet )
	{	
		m_bVisible = bSet;
	}

	public boolean isVisible()
	{
		return m_bVisible;
	}

	boolean isInRect( int nPosX, int nPosY )
	{
		if( m_bVisible == false )
			return false;

		if( m_bTouchable == false )
			return false;

		if( true == m_bRealRect )
		{
			if( (nPosX >= m_Realrect.nLeft) && (nPosX <= m_Realrect.nRight ) )
			{
				if( (nPosY >= m_Realrect.nTop) && (nPosY <= m_Realrect.nBottom ) )
				{
					return true;			
				}
			}

			return false;
		}
		else
		{			
			if( (nPosX > m_rect.nLeft) && (nPosX < m_rect.nRight) )
			{
				if( (nPosY > m_rect.nTop) && (nPosY < m_rect.nBottom) )
				{
					return true;			
				}
			}

			return false;
		}	
	}

	public void setTouch( boolean bSet )
	{
		m_bTouchable = bSet;
	}

	public void setRealRect( int nLeft, int nTop, int nRight, int nBottom )
	{
		m_bRealRect = true;

		m_Realrect.nLeft = nLeft;
		m_Realrect.nTop = nTop;
		m_Realrect.nRight = nRight;
		m_Realrect.nBottom = nBottom;
	}

	public void setRealRect( int nWidth, int nHeight )
	{
		m_nRealWidth = nWidth;
		m_nRealHeight = nHeight;

		m_bRealRect = true;

		setCenter( m_rect.nCenterX, m_rect.nCenterY );
	}

	public SG_RECT getRect()
	{
		if( true == m_bRealRect )
			return m_Realrect;

		return m_rect;
	}
	
	public int getID()
	{
		return m_nID;
	}
	
}



