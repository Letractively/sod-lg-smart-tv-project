
package com.sgsong.Element;

import java.util.Vector;
import com.sgsong.Game.GameMain;
import com.sgsong.Draw.SG_PT;
import com.sgsong.Draw.SG;

public class VNumber
{	
	private Vector<NumberInfo> m_list = new Vector<NumberInfo>();
	
	private GameMain	m_pMain;
	
	private int m_gap;

	private SG_PT	m_pos = new SG_PT();
	private boolean	m_bShow;
	
	public VNumber()
	{
		m_pMain = null;
		m_list.clear();
		m_bShow = false;
		m_gap = 22;
		m_pos.nX = 0;
		m_pos.nY = 0;
	}	

	public void initMe( GameMain pMain, SG pSG, int nSize )
	{
		m_pMain = pMain;

		int nDiv = 1;
		for( int i=0; i<nSize; i++ )
		{
			ElementPart tmpPart = new ElementPart();
			tmpPart.InitMe( m_pMain, pSG );
			tmpPart.setID( Element.id_number );		
			tmpPart.setPartCount( 10, 1 );
			tmpPart.setPart( 0 );		
			tmpPart.setVisible( false );

			m_pMain.addElement( tmpPart, ElementList.top );	

			NumberInfo tmp = new NumberInfo();
			tmp.vNum = tmpPart;
			tmp.divideNum = nDiv;
			tmp.mNum = 0;		
			tmp.bShow = false;

			m_list.add( tmp );		

			nDiv = nDiv * 10;
		}	
	}

	public void setImage( int imgName )
	{
		for( int i=0; i<m_list.size(); i++ )
		{
			m_list.get(i).vNum.setImageID( imgName );
		}
	}

	public void setGap( int gap )
	{
		m_gap = gap;
	}

	public void show()
	{
		m_bShow = true;

		for( int i=0; i<(int)m_list.size(); i++ )
		{
			if( m_list.get(i).bShow == true )
				m_list.get(i).vNum.setVisible( m_bShow );
			else
				m_list.get(i).vNum.setVisible( false );
		}
	}

	public void hide()
	{
		m_bShow = false;

		for( int i=0; i<(int)m_list.size(); i++ )
		{
			m_list.get(i).vNum.setVisible( m_bShow );
		}
	}

	public boolean isVisible()
	{
		return m_bShow;
	}

	public void setPos( int nX, int nY )
	{
		m_pos.nX = nX;
		m_pos.nY = nY;

		for( int i=0; i<m_list.size(); i++ )
		{
			m_list.get(i).vNum.setPos( m_pos.nX - i * m_gap, m_pos.nY );
		}
	}

	public void setAlpha( float fAlpha )
	{
		for( int i=0; i<m_list.size(); i++ )
		{
			m_list.get(i).vNum.setAlpha( fAlpha );
		}
	}

	public void setAmount( int amount )
	{
		for( int i=0; i<m_list.size(); i++ )
		{
			m_list.get(i).bShow = false;
		}
		
		if( amount < 0 )
			amount = 0;

		int nSize = m_list.size();
		if( nSize == 0 )
			return;
		
		if( amount >= m_list.get(nSize-1).divideNum * 10 )
		{
			amount = m_list.get(nSize-1).divideNum * 10 - 1;
		}

		boolean bExist = false;

		for( int i=(nSize-1); i>=0; i-- )
		{
			int n = amount / m_list.get(i).divideNum;
			if( n > 0 )
			{
				bExist = true;
			}

			m_list.get(i).mNum = n;
			m_list.get(i).vNum.setPart( m_list.get(i).mNum );
			if( bExist == true )
			{				
				m_list.get(i).bShow = true;
			}
			else
			{
				m_list.get(i).bShow = false;
			}

			amount = amount - n * m_list.get(i).divideNum;
		}

		if( amount == 0 )
		{			
			m_list.get(0).bShow = true;
		}

		if( true == m_bShow )
			show();
	}	
}