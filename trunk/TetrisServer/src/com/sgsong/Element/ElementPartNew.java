
package com.sgsong.Element;

public class ElementPartNew extends Element
{
	public static final int MAX_SIZE = 30;
	
	protected int		m_nMaxPart;
	protected int		m_nPartIndex;
	protected int		m_partImg[] = new int[MAX_SIZE];	
	
	public ElementPartNew()
	{
		super();

		m_nMaxPart = 0;
		m_nPartIndex = 0;

		for( int i=0; i<MAX_SIZE; i++ )
		{
			m_partImg[i] = 0;
		}
	}

	public void setPartCount( int nCount )
	{
		m_nMaxPart = nCount;
	}
	
	public void setPart( int nIndex )
	{
		m_nPartIndex = nIndex;

		setImageID( m_partImg[m_nPartIndex] );	
	}
	
	public void registImg( int nIndex, int nImgID )
	{
		m_partImg[nIndex] = nImgID;
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
			m_pSG.DrawFullImage( m_partImg[m_nPartIndex], m_rect, m_scale, m_rotate, m_color );
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
		
		m_pSG.DrawFullImage( m_partImg[m_nPartIndex], m_rect, m_scale, m_rotate, m_color );
	}
}
