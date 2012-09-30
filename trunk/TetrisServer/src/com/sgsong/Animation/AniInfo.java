
package com.sgsong.Animation;

import com.sgsong.Element.Element;

public class AniInfo
{
	public Element	target;

	public int	nAniID;
	public int	nType;

	public int nEasingType;

	public int nStartX;
	public int nStartY;
	public int nEndX;
	public int nEndY;
  
	public float fStartValue;
	public float fEndValue;

	public float fSec;

	public int	  nPriority;
	public int   nPreAniID;

	public int	  nSoundID;
	
	public AniInfo()
	{		
	}
	
	public void clear()
	{
		target = null;

		nAniID = -1;
		nType = -1;

		nEasingType = EasingCurve.Linear;

		nStartX = 0;
		nStartY = 0;

		nEndX = 0;
		nEndY = 0;

		fStartValue = 0.0f;
		fEndValue = 0.0f;

		fSec = 0.0f;	
		nPriority = 0;

		nPreAniID = -1;

		nSoundID = -1;
	}
}