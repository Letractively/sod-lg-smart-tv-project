
package com.sgsong.Animation;

import com.sgsong.Game.GameMain;

public class Animation
{
	// const start
	public static final int value_type_posX = 0;
	public static final int value_type_posY = 1;

	public static final int type_move = 0;
	public static final int type_scale = 1;
	public static final int type_alpha = 2;
	public static final int type_rotate = 3;

	public static final int id_send_first_card_to_com = 100;
	public static final int id_send_second_card_to_com = 200;

	public static final int id_send_first_card_to_me = 300;
	public static final int id_send_second_card_to_me = 400;
 
	public static final int id_send_first_card_to_board = 500;
	public static final int id_send_second_card_to_board = 600;

	public static final int id_send_mycard_to_board = 700;
	public static final int id_backcard_to_board = 800;
	public static final int id_send_comcard_to_board = 900;

	public static final int id_dummy_end_turn = 1000;
	public static final int id_dummy_calc_card = 1010;
	public static final int id_dummy_new_turn = 1020;
	public static final int id_dummy_com_turn = 1030;

	public static final int id_eat_card_kwang = 1100;
	public static final int id_eat_card_mung = 1110;
	public static final int id_eat_card_ddi = 1120;
	public static final int id_eat_card_pi = 1130;		

	public static final int id_effect_jjock = 1200;
	public static final int id_effect_bbuck = 1210;
	public static final int id_effect_godori = 1220;
	public static final int id_effect_hongdan = 1230;	

	public static final int id_effect_chungdan = 1240;	
	public static final int id_effect_chodan = 1250;
	public static final int id_effect_ssul = 1260;
	public static final int id_effect_card_impact = 1270;

	public static final int id_effect_eat_bbuck = 1280;	
	public static final int id_effect_ddadac = 1290;

	public static final int id_effect_1go = 1300;
	public static final int id_effect_2go = 1301;
	public static final int id_effect_3go = 1302;
	public static final int id_effect_4go = 1303;
	public static final int id_effect_5go = 1304;
	public static final int id_effect_6go = 1305;	

	public static final int id_effect_stop = 1400;
	// const end	
	
	private GameMain m_pMain;	

	private long m_curTime;
	
	// move
	private long m_MoveStartTime;
	private long m_MoveEndTime;	

	private int m_nMoveStartX;
	private int m_nMoveStartY;
	private int m_nMoveCurX;
	private int m_nMoveCurY;
	private int m_nMoveEndX;
	private int m_nMoveEndY;

	private boolean	m_bMoveSet;
	private boolean	m_bMoveRepeat;

	private int	m_nMoveID;
	
	private int	m_nMoveCurveType;
	
	// center	
	private long m_CenterStartTime;
	private long m_CenterEndTime;	

	private int m_nCenterStartX;
	private int m_nCenterStartY;
	private int m_nCenterCurX;
	private int m_nCenterCurY;
	private int m_nCenterEndX;
	private int m_nCenterEndY;

	private boolean	m_bCenterSet;
	private boolean	m_bCenterRepeat;

	private int	m_nCenterID;
	
	private int	m_nCenterCurveType;
	
	// part
	private long m_PartStartTime;
	private long m_PartEndTime;	

	private int m_nPartStart;	
	private int m_nPartCur;	
	private int m_nPartEnd;	

	private boolean	m_bPartSet;
	private boolean	m_bPartRepeat;
	private boolean	m_bPartLoop;	

	private int	m_nPartID;
	
	private int	m_nPartCurveType;
	
	// change value
	private long m_ChangeValueStartTime;
	private long m_ChangeValueEndTime;	
	
	private int m_nChangeValueStart;	
	private int m_nChangeValueCur;	
	private int m_nChangeValueEnd;	

	private int	m_nChangeValueID;
	private int	m_nChangeValueType;
	
	private boolean	m_bChangeValueSet;
	private boolean	m_bChangeValueRepeat;
	
	private int	m_nChangeValueCurveType;
	
	// alpha
	private long m_AlphaStartTime;
	private long m_AlphaEndTime;	

	private float m_fAlphaCur;
	private float m_fAlphaStart;	
	private float m_fAlphaEnd;

	private boolean	m_bAlphaSet;
	private boolean	m_bAlphaRepeat;
	private boolean m_bAlphaLoop;

	private int	m_nAlphaID;
	
	private int	m_nAlphaCurveType;
	
	// scale
	private long m_ScaleStartTime;
	private long m_ScaleEndTime;	

	private float m_fScaleCur;
	private float m_fScaleStart;	
	private float m_fScaleEnd;

	private boolean	m_bScaleSet;
	private boolean	m_bScaleRepeat;
	private boolean	m_bScaleLoop;

	private int	m_nScaleID;
	
	private int	m_nScaleCurveType;
	
	// rotate
	private long m_RotateStartTime;
	private long m_RotateEndTime;	

	private float m_fRotateCur;
	private float m_fRotateStart;	
	private float m_fRotateEnd;

	private boolean	m_bRotateSet;
	private boolean	m_bRotateRepeat;

	private int	m_nRotateID;
	
	private int	m_nRotateCurveType;
	
	public Animation()
	{		
		m_pMain = null;

		clearMove();
		clearCenter();
		clearPart();	
		clearAlpha();
		clearRotate();
		clearScale();
		clearChangeValue();
	}	

//	public void Init( Element pEle, GameMain pMain )
	public void Init( GameMain pMain )
	{		
		m_pMain = pMain;

		m_pMain.addAnimation( this );
	}

	public void setCurTime( long curTime )
	{
		m_curTime = curTime;
	}

	public int getX()
	{
		return m_nMoveCurX;
	}

	public int getY()
	{
		return m_nMoveCurY;
	}

	public void setMove( int nID, int nCurveType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec, boolean bRepeat )
	{
		m_nMoveID = nID;

		m_MoveStartTime = m_curTime;
		m_MoveEndTime = m_MoveStartTime + (long)(fSec * 1000.0f);

		m_nMoveStartX = nStartX;
		m_nMoveStartY = nStartY;

		m_nMoveEndX = nEndX;
		m_nMoveEndY = nEndY;

		m_bMoveSet = true;
		m_bMoveRepeat = bRepeat;
		
		m_nMoveCurveType = nCurveType;
	}

	public boolean isMove()
	{
		if( false == m_bMoveSet )
			return false;

		if( m_curTime < m_MoveStartTime )
			return false;

		if( m_curTime >= m_MoveEndTime )
		{
			m_nMoveCurX = m_nMoveEndX;
			m_nMoveCurY = m_nMoveEndY;

			if( true == m_bMoveRepeat )
			{
				long d = m_MoveEndTime - m_MoveStartTime;

				m_MoveStartTime = m_curTime;
				m_MoveEndTime = m_MoveStartTime + d;
			}

			return true;
		}

		// calc move
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_MoveStartTime;
		fPercent = (float)duration / (float)(m_MoveEndTime - m_MoveStartTime);	
		
		float newPercent = m_pMain.getCurveValue( m_nMoveCurveType, fPercent );
		
		m_nMoveCurX = m_nMoveStartX + (int)((m_nMoveEndX-m_nMoveStartX) * newPercent);
		m_nMoveCurY = m_nMoveStartY + (int)((m_nMoveEndY-m_nMoveStartY) * newPercent);

		return true;
	}

	public boolean isMoveEnd()
	{	
		if( true == m_bMoveSet )
		{
			if( m_curTime >= m_MoveEndTime )
			{			
				return true;
			}
		}

		return false;
	}

	public int getMoveID()
	{
		return m_nMoveID;
	}

	public void clearMove()
	{
		m_nMoveID = -1;

		m_MoveStartTime = 0;
		m_MoveEndTime = 0;

		m_nMoveStartX = 0;
		m_nMoveStartY = 0;

		m_nMoveEndX = 0;
		m_nMoveEndY = 0;

		m_nMoveCurX = 0;
		m_nMoveCurY = 0;

		m_bMoveSet = false;
		m_bMoveRepeat = false;
		
		m_nMoveCurveType = EasingCurve.Linear;
	}
	
	// center
	public int	getCenterX()
	{
		return m_nCenterCurX;
	}

	public int getCenterY()
	{
		return m_nCenterCurY;
	}

	public void setCenter( int nID, int nCurveType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec, boolean bRepeat )
	{
		m_nCenterID = nID;

		m_CenterStartTime = m_curTime;
		m_CenterEndTime = m_CenterStartTime + (long)(fSec * 1000.0f);

		m_nCenterStartX = nStartX;
		m_nCenterStartY = nStartY;

		m_nCenterEndX = nEndX;
		m_nCenterEndY = nEndY;

		m_bCenterSet = true;
		m_bCenterRepeat = bRepeat;
		
		m_nCenterCurveType = nCurveType;
	}

	public boolean isCenter()
	{
		if( false == m_bCenterSet )
			return false;

		if( m_curTime < m_CenterStartTime )
			return false;	

		if( m_curTime >= m_CenterEndTime )
		{
			m_nCenterCurX = m_nCenterEndX;
			m_nCenterCurY = m_nCenterEndY;

			if( true == m_bCenterRepeat )
			{
				long d = m_CenterEndTime - m_CenterStartTime;

				m_CenterStartTime = m_curTime;
				m_CenterEndTime = m_CenterStartTime + d;
			}			
			
			return true;
		}

		// calc move
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_CenterStartTime;		
		fPercent = (float)duration / (float)(m_CenterEndTime - m_CenterStartTime);
		
		float newPercent = m_pMain.getCurveValue( m_nCenterCurveType, fPercent );	
		
		m_nCenterCurX = m_nCenterStartX + (int)((m_nCenterEndX-m_nCenterStartX) * newPercent);
		m_nCenterCurY = m_nCenterStartY + (int)((m_nCenterEndY-m_nCenterStartY) * newPercent);

		return true;
	}

	public boolean isCenterEnd()
	{	
		if( true == m_bCenterSet )
		{
			if( m_curTime >= m_CenterEndTime )
			{			
				return true;
			}
		}

		return false;
	}

	public int getCenterID()
	{
		return m_nCenterID;
	}

	public void clearCenter()
	{
		m_nCenterID = -1;

		m_CenterStartTime = 0;
		m_CenterEndTime = 0;

		m_nCenterStartX = 0;
		m_nCenterStartY = 0;

		m_nCenterEndX = 0;
		m_nCenterEndY = 0;

		m_nCenterCurX = 0;
		m_nCenterCurY = 0;

		m_bCenterSet = false;
		m_bCenterRepeat = false;
		
		m_nCenterCurveType = EasingCurve.Linear;
	}

	// part
	public int getPart()
	{
		return m_nPartCur;
	}

	public void setPart( int nID, int nCurveType, int nStart, int nEnd, float fSec, boolean bRepeat, boolean bLoop )
	{
		m_nPartID = nID;

		m_PartStartTime = m_curTime;
		m_PartEndTime = m_PartStartTime + (long)(fSec * 1000.0f);	

		m_nPartStart = nStart;
		m_nPartEnd = nEnd;	

		m_bPartSet = true;
		m_bPartRepeat = bRepeat;
		m_bPartLoop = bLoop;
		
		m_nPartCurveType = nCurveType;
	}

	public boolean isPart()
	{
		if( false == m_bPartSet )
			return false;

		if( m_curTime < m_PartStartTime )
			return false;

		if( m_curTime >= m_PartEndTime )
		{
			m_nPartCur = m_nPartEnd;

			if( true == m_bPartRepeat )
			{
				long d = m_PartEndTime - m_PartStartTime;

				m_PartStartTime = m_curTime;
				m_PartEndTime = m_PartStartTime + d;
			}
			
			if( true == m_bPartLoop )
			{
				long d = m_PartEndTime - m_PartStartTime;

				m_PartStartTime = m_curTime;
				m_PartEndTime = m_PartStartTime + d;

				int nTmp = m_nPartEnd;
				m_nPartEnd = m_nPartStart;
				m_nPartStart = nTmp;

				m_bPartLoop = m_bPartRepeat;

			}

			return true;
		}

		// calc part
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_PartStartTime;
		fPercent = (float)duration / (float)(m_PartEndTime - m_PartStartTime);
		
		float newPercent = m_pMain.getCurveValue( m_nPartCurveType, fPercent );
		
		m_nPartCur = m_nPartStart + (int)((m_nPartEnd-m_nPartStart) * newPercent);

		return true;
	}

	public boolean isPartEnd()
	{	
		if( true == m_bPartSet )
		{
			if( m_curTime >= m_PartEndTime )
			{			
				return true;
			}
		}

		return false;
	}

	public int getPartID()
	{	
		return m_nPartID;
	}

	public void clearPart()
	{
		m_PartStartTime = 0;
		m_PartEndTime = 0;

		m_nPartStart = 0;
		m_nPartCur = 0;
		m_nPartEnd = 0;	

		m_nPartID = -1;

		m_bPartSet = false;
		m_bPartRepeat = false;
		
		m_nPartCurveType = EasingCurve.Linear;
	}

	public int getChangeValue()
	{
		return m_nChangeValueCur;
	}

	public void setChangeValue( int nID, int nCurveType, int nType, int nStart, int nEnd, float fSec, boolean bRepeat )
	{
		m_nChangeValueID = nID;
		m_nChangeValueType = nType;

		m_ChangeValueStartTime = m_curTime;		
		m_ChangeValueEndTime = m_ChangeValueStartTime + (long)(fSec * 1000.0f);
		
		m_nChangeValueStart = nStart;	
		m_nChangeValueEnd = nEnd;

		m_bChangeValueRepeat = bRepeat;

		m_bChangeValueSet = true;
		
		m_nChangeValueCurveType = nCurveType;
	}

	public boolean isChangeValue()
	{
		if( false == m_bChangeValueSet )
			return false;

		if( m_curTime < m_ChangeValueStartTime )
			return false;

		if( m_curTime >= m_ChangeValueEndTime )
		{
			m_nChangeValueCur = m_nChangeValueEnd;

			if( true == m_bChangeValueRepeat )
			{
				long d = m_ChangeValueEndTime - m_ChangeValueStartTime;				
				
				m_ChangeValueStartTime = m_curTime;				
				m_ChangeValueEndTime = m_ChangeValueStartTime + d;
			}

			return true;
		}

		// calc reel move
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_ChangeValueStartTime;			
		fPercent = (float)duration / (float)(m_ChangeValueEndTime - m_ChangeValueStartTime);
		
		float newPercent = m_pMain.getCurveValue( m_nChangeValueCurveType, fPercent );
		
		m_nChangeValueCur = m_nChangeValueStart + (int)((m_nChangeValueEnd-m_nChangeValueStart) * newPercent);

		return true;
	}

	public boolean isChangeValueEnd()
	{
		if( true == m_bChangeValueSet )
		{
			if( m_curTime >= m_ChangeValueEndTime )
			{			
				return true;
			}
		}

		return false;
	}

	public int getChangeValueID()
	{
		return m_nChangeValueID;
	}

	public int getChangeValueType()
	{
		return m_nChangeValueType;
	}

	public void clearChangeValue()
	{
		m_nChangeValueType = -1;
		m_nChangeValueID = -1;

		m_ChangeValueStartTime = 0;
		m_ChangeValueEndTime = 0;
		
		m_nChangeValueCur = 0;
		m_nChangeValueStart = 0;	
		m_nChangeValueEnd = 0;	

		m_bChangeValueSet = false;		

		m_bChangeValueRepeat = false;
		
		m_nChangeValueCurveType = EasingCurve.Linear;
	}

	// alpha
	public float getAlpha()
	{
		return m_fAlphaCur;
	}

	public void setAlpha( int nID, int nCurveType, float fStart, float fEnd, float fSec, boolean bRepeat, boolean bLoop )
	{
		m_nAlphaID = nID;

		m_AlphaStartTime = m_curTime;
		m_AlphaEndTime = m_AlphaStartTime + (long)(fSec * 1000.0f);

		m_fAlphaStart = fStart;
		m_fAlphaEnd = fEnd;	

		m_bAlphaSet = true;
		m_bAlphaRepeat = bRepeat;
		m_bAlphaLoop = bLoop;
		
		m_nAlphaCurveType = nCurveType;
	}

	public boolean isAlpha()
	{
		if( false == m_bAlphaSet )
			return false;

		if( m_curTime < m_AlphaStartTime )
			return false;

		if( m_curTime >= m_AlphaEndTime )
		{
			m_fAlphaCur = m_fAlphaEnd;

			if( true == m_bAlphaRepeat )
			{
				long d = m_AlphaEndTime - m_AlphaStartTime;

				m_AlphaStartTime = m_curTime;
				m_AlphaEndTime = m_AlphaStartTime + d;
			}
			
			if( true == m_bAlphaLoop )
			{
				long d = m_AlphaEndTime - m_AlphaStartTime;

				m_AlphaStartTime = m_curTime;
				m_AlphaEndTime = m_AlphaStartTime + d;

				float fTmp = m_fAlphaEnd;
				m_fAlphaEnd = m_fAlphaStart;
				m_fAlphaStart = fTmp;

				m_bAlphaLoop = m_bAlphaRepeat;
			}			

			return true;
		}

		// calc alpha
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_AlphaStartTime;
		fPercent = (float)duration / (float)(m_AlphaEndTime - m_AlphaStartTime);
		
		float newPercent = m_pMain.getCurveValue( m_nAlphaCurveType, fPercent );
		
		m_fAlphaCur = m_fAlphaStart + (m_fAlphaEnd-m_fAlphaStart) * newPercent;

		return true;
	}

	public boolean isAlphaEnd()
	{
		// alpha
		if( true == m_bAlphaSet )
		{
			if( m_curTime >= m_AlphaEndTime )
			{			
				return true;
			}
		}

		return false;
	}

	public int getAlphaID()
	{	
		return m_nAlphaID;
	}

	public void clearAlpha()
	{
		m_AlphaStartTime = 0;
		m_AlphaEndTime = 0;

		m_fAlphaStart = 0.0f;
		m_fAlphaCur = 0.0f;
		m_fAlphaEnd = 0.0f;	

		m_nAlphaID = -1;

		m_bAlphaSet = false;
		m_bAlphaRepeat = false;
		m_bAlphaLoop = false;
		
		m_nAlphaCurveType = EasingCurve.Linear;
	}

	// scale
	public float getScale()
	{
		return m_fScaleCur;
	}

	public void setScale( int nID, int nCurveType, float fStart, float fEnd, float fSec, boolean bRepeat, boolean bLoop )
	{
		m_nScaleID = nID;

		m_ScaleStartTime = m_curTime;
		m_ScaleEndTime = m_ScaleStartTime + (long)(fSec * 1000.0f);

		m_fScaleStart = fStart;
		m_fScaleEnd = fEnd;	

		m_bScaleSet = true;
		m_bScaleRepeat = bRepeat;
		m_bScaleLoop = bLoop;
		
		m_nScaleCurveType = nCurveType;
	}	

	public boolean isScale()
	{
		if( false == m_bScaleSet )
			return false;

		if( m_curTime < m_ScaleStartTime )
			return false;

		if( m_curTime >= m_ScaleEndTime )
		{
			m_fScaleCur = m_fScaleEnd;

			if( true == m_bScaleRepeat )
			{
				long d = m_ScaleEndTime - m_ScaleStartTime;

				m_ScaleStartTime = m_curTime;
				m_ScaleEndTime = m_ScaleStartTime + d;
			}
			
			if( true == m_bScaleLoop )
			{
				long d = m_ScaleEndTime - m_ScaleStartTime;

				m_ScaleStartTime = m_curTime;
				m_ScaleEndTime = m_ScaleStartTime + d;

				float fTmp = m_fScaleEnd;
				m_fScaleEnd = m_fScaleStart;
				m_fScaleStart = fTmp;

				m_bScaleLoop = m_bScaleRepeat;
			}			

			return true;
		}

		// calc scale
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_ScaleStartTime;
		fPercent = (float)duration / (float)(m_ScaleEndTime - m_ScaleStartTime);
		
		float newPercent = m_pMain.getCurveValue( m_nScaleCurveType, fPercent );
		
		m_fScaleCur = m_fScaleStart + (m_fScaleEnd-m_fScaleStart) * newPercent;

		return true;
	}

	public boolean isScaleEnd()
	{
		// scale
		if( true == m_bScaleSet )
		{
			if( m_curTime >= m_ScaleEndTime )
			{			
				return true;
			}
		}

		return false;

	}

	public int getScaleID()
	{	
		return m_nScaleID;
	}

	public void clearScale()
	{
		m_ScaleStartTime = 0;
		m_ScaleEndTime = 0;

		m_fScaleStart = 0.0f;
		m_fScaleCur = 0.0f;
		m_fScaleEnd = 0.0f;	

		m_nScaleID = -1;

		m_bScaleSet = false;
		m_bScaleRepeat = false;
		
		m_nScaleCurveType = EasingCurve.Linear;
	}

	// rotate
	public float getRotate()
	{
		return m_fRotateCur;
	}

	public void setRotate( int nID, int nCurveType, float fStart, float fEnd, float fSec, boolean bRepeat )
	{
		m_nRotateID = nID;

		m_RotateStartTime = m_curTime;
		m_RotateEndTime = m_RotateStartTime + (long)(fSec *1000.0f);	

		m_fRotateStart = fStart;
		m_fRotateEnd = fEnd;	

		m_bRotateSet = true;
		m_bRotateRepeat = bRepeat;
		
		m_nRotateCurveType = nCurveType;
	}

	public boolean isRotate()
	{
		if( false == m_bRotateSet )
			return false;

		if( m_curTime < m_RotateStartTime )
			return false;

		if( m_curTime >= m_RotateEndTime )
		{
			m_fRotateCur = m_fRotateEnd;

			if( true == m_bRotateRepeat )
			{
				long d = m_RotateEndTime - m_RotateStartTime;

				m_RotateStartTime = m_curTime;
				m_RotateEndTime = m_RotateStartTime + d;
			}

			return true;
		}

		// calc scale
		long duration = 0;
		double fPercent = 0.0f;

		duration = m_curTime - m_RotateStartTime;
		fPercent = (float)duration / (float)(m_RotateEndTime - m_RotateStartTime);
		
		float newPercent = m_pMain.getCurveValue( m_nRotateCurveType, fPercent );
		
		m_fRotateCur = m_fRotateStart + (m_fRotateEnd-m_fRotateStart) * newPercent;

		return true;
	}

	public boolean isRotateEnd()
	{
		// rotate
		if( true == m_bRotateSet )
		{
			if( m_curTime >= m_RotateEndTime )
			{			
				return true;
			}
		}

		return false;
	}

	public int getRotateID()
	{	
		return m_nRotateID;
	}

	public void clearRotate()
	{
		m_RotateStartTime = 0;
		m_RotateEndTime = 0;

		m_fRotateStart = 0.0f;
		m_fRotateCur = 0.0f;
		m_fRotateEnd = 0.0f;	

		m_nRotateID = -1;

		m_bRotateSet = false;
		m_bRotateRepeat = false;
		
		m_nRotateCurveType = EasingCurve.Linear;
	}
}