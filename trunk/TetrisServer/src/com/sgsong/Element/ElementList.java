
package com.sgsong.Element;

import java.util.Vector;

public class ElementList
{	
	public static final int MAX_LIST = 3;
	
	public static final int bottom = 0;
	public static final int middle = 1;
	public static final int top = 2;
	
	private Vector<Element> m_list_bottom = new Vector<Element>();
	private Vector<Element> m_list_middle = new Vector<Element>();
	private Vector<Element> m_list_top = new Vector<Element>();
	
	public ElementList()
	{			
	}	

	public void Draw( long curTime )
	{
		// bottom
		for( int i=0; i<m_list_bottom.size(); i++ )
		{
			if( m_list_bottom.get(i) != null )
				m_list_bottom.get(i).DrawMe( curTime );
		}
		
		// middle
		for( int i=0; i<m_list_middle.size(); i++ )
		{
			if( m_list_middle.get(i) != null )
				m_list_middle.get(i).DrawMe( curTime );
		}		
		
		// top
		for( int i=0; i<m_list_top.size(); i++ )
		{
			if( m_list_top.get(i) != null )
				m_list_top.get(i).DrawMe( curTime );
		}		
	}

	public void setTopOrder( int nID )
	{
		boolean bFind = false;		
		int nIndex = 0;	

		// find bottom
		for( int i=0; i<(int)m_list_bottom.size(); i++ )
		{
			if( m_list_bottom.get(i).getID() == nID )
			{
				nIndex = i;
				bFind = true;
				break;
			}		
		}
		
		if( true == bFind )
		{
			Element tmp = m_list_bottom.get(nIndex);
			m_list_bottom.remove( nIndex );
			m_list_bottom.add( tmp );
			return;
		}
		
		// find middle
		for( int i=0; i<(int)m_list_middle.size(); i++ )
		{
			if( m_list_middle.get(i).getID() == nID )
			{
				nIndex = i;
				bFind = true;
				break;
			}		
		}
		
		if( true == bFind )
		{
			Element tmp = m_list_middle.get(nIndex);
			m_list_middle.remove( nIndex );
			m_list_middle.add( tmp );
			return;
		}
		
		// find top
		for( int i=0; i<(int)m_list_top.size(); i++ )
		{
			if( m_list_top.get(i).getID() == nID )
			{
				nIndex = i;
				bFind = true;
				break;
			}		
		}
		
		if( true == bFind )
		{
			Element tmp = m_list_top.get(nIndex);
			m_list_top.remove( nIndex );
			m_list_top.add( tmp );
			return;
		}
		
		// not find...
	}

	public void addElement( Element pEle, int nOrderType )
	{
		if( bottom == nOrderType )
		{
			m_list_bottom.add( pEle );
			return;
		}
		
		if( middle == nOrderType )
		{
			m_list_middle.add( pEle );
			return;
		}
		
		if( top == nOrderType )
		{
			m_list_top.add( pEle );
			return;
		}		
	}

	public int getTouchedElementID( int nX, int nY )
	{
		int nID = -1;		
		
		// find top
		int nSize = m_list_top.size();
		if( nSize > 0 )
		{
			nSize--;
			
			for( int i=nSize; i>=0; i-- )
			{
				if( m_list_top.get(i) == null )
					continue;

				if( true == m_list_top.get(i).isInRect( nX, nY ) )
				{
					nID = m_list_top.get(i).getID();
					return nID;				
				}
			}			
		}
		
		// find middle
		nSize = m_list_middle.size();
		if( nSize > 0 )
		{
			nSize--;
			
			for( int i=nSize; i>=0; i-- )
			{
				if( m_list_middle.get(i) == null )
					continue;

				if( true == m_list_middle.get(i).isInRect( nX, nY ) )
				{
					nID = m_list_middle.get(i).getID();
					return nID;				
				}
			}			
		}
		
		// find bottom
		nSize = m_list_bottom.size();
		if( nSize > 0 )
		{
			nSize--;
			
			for( int i=nSize; i>=0; i-- )
			{
				if( m_list_bottom.get(i) == null )
					continue;

				if( true == m_list_bottom.get(i).isInRect( nX, nY ) )
				{
					nID = m_list_bottom.get(i).getID();
					return nID;				
				}
			}			
		}		
		if(480 < nX && 820 > nX && 340 < nY && 470 > nY)
		{
			return 700;
		}
		return nID;		
	}
}