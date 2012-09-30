
package com.sgsong.Element;

public class ElementPart extends Element
{
	private int		m_nCntWidth;
	private int		m_nCntHeight;

	private int		m_nPartIndex;
	
	public ElementPart()
	{
		super();

		m_nCntWidth = 1;
		m_nCntHeight = 1;

		m_nPartIndex = 0;
	}

	public void setPartCount( int nCntWidth, int nCntHeight )
	{
		m_nCntWidth = nCntWidth;
		m_nCntHeight = nCntHeight;

		setSize( m_nOrWidth/nCntWidth, m_nOrHeight/m_nCntHeight );
	}
	
	public void setImageID( int nID )
	{
		m_imgID = nID;

		setOrSize( m_pSG.getImg(nID).nWidth, m_pSG.getImg(nID).nHeight );
		setSize( m_pSG.getImg(nID).nWidth/m_nCntWidth, m_pSG.getImg(nID).nHeight/m_nCntHeight );
	}

	public void setPart( int nIndex )
	{
		m_nPartIndex = nIndex;
	}
	
	public int getPart()
	{
		return m_nPartIndex;
	}

	// override
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
			m_pSG.DrawPartImage( m_imgID, m_nCntWidth, m_nCntHeight, m_nPartIndex,
					m_rect, m_scale, m_rotate, m_color );
			return;
		}		

		// move
		if( true == m_ani.isMove() )
		{
			setPos( m_ani.getX(), m_ani.getY() );		
		}

		// part
		if( true == m_ani.isPart() )
		{
			setPart( m_ani.getPart() );
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
		
		m_pSG.DrawPartImage( m_imgID, m_nCntWidth, m_nCntHeight, m_nPartIndex,
				m_rect, m_scale, m_rotate, m_color );
	}
}
