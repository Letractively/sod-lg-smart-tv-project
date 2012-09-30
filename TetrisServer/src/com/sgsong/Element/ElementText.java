
package com.sgsong.Element;

import com.sgsong.Element.ElementPart;

public class ElementText extends ElementPart
{
	// 16 ~ 25 - 0 ~ 9
	// 33 ~ 58 - ´ë¹®ÀÚ A ~ Z
	// 65 ~			97 
	
	public ElementText()
	{		
	}
	
	public void setText( char ch )
	{
		int nPart = 0;		
		
		nPart = ch - 32;
		
	//	if( ch >= 'A' && ch <= 'Z' )
	//	{			
	//		nPart = ch - 32;
	//	}
	//	else if( ch >= 'a' && ch <= 'z' )
	//	{
	//		nPart = ch - 32;
	//	}
	//	else if( )
		
		
	//	String sgLog = String.format("[SGSG] ch: %c, ch: %d, nPart: %d", ch, (int)ch, nPart );		
		
		setPart( nPart );
		
	//	m_pMain.ShowLog(sgLog);
	}
}