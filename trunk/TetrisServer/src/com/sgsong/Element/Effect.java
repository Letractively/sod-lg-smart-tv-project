
package com.sgsong.Element;

import com.sgsong.Animation.EasingCurve;

public class Effect extends ElementPart
{
	private int	m_nCurveType;
	private float m_fSec;
	private int m_nCount;

	private int m_nAniID;	
	
	public Effect()
	{
		super();

		m_nCurveType = EasingCurve.Linear;
		m_fSec = 0.5f;
		m_nCount = 0;
		m_nAniID = 0;		
	}

	public void setSprite( int nCntWidth, int nCntHeight )
	{
		super.setPartCount( nCntWidth, nCntHeight );

		m_nCount = nCntWidth * nCntHeight - 1;	
	}

	public void setAniID( int nID )
	{
		m_nAniID = nID;
		addOnAnimation();
	}

	public void setEasingCurve( int nType )
	{
		m_nCurveType = nType;
	}

	public void setTime( float fSec )
	{
		m_fSec = fSec;
	}	

	public void act()
	{	
		setPartAni( m_nAniID, m_nCurveType, 0, m_nCount, m_fSec );
		setVisible( true );
	}
}