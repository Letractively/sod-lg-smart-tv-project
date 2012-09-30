
package com.sgsong.Element;

import com.sgsong.Animation.Animation;
import com.sgsong.Draw.SG_RECT;

public class ElementBound extends Element
{
	protected SG_RECT m_pRect;
	
	public ElementBound()
	{
		super();
	}
	
	public void setBound( SG_RECT pRect )
	{
		m_pRect = pRect;
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
			m_pSG.DrawBoundImage( m_imgID, m_pRect, m_rect.nLeft, m_rect.nTop, m_scale, m_rotate, m_color );
			return;
		}		

		// move
		if( true == m_ani.isMove() )
		{
			setPos( m_ani.getX(), m_ani.getY() );		
		}	

		// bound
		if( true == m_ani.isChangeValue() )
		{
			if( Animation.value_type_posY == m_ani.getChangeValueType() )
			{
				int nHeight = m_pRect.nBottom - m_pRect.nTop;

				m_pRect.nTop = m_ani.getChangeValue();
				m_pRect.nBottom = m_pRect.nTop + nHeight;
			}		
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

	//	m_pSG.setColor( m_color );	
		
		m_pSG.DrawBoundImage( m_imgID, m_pRect, m_rect.nLeft, m_rect.nTop, m_scale, m_rotate, m_color );
	}
}