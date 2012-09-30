
package com.sgsong.Animation;

import com.sgsong.Game.GameMain;
import com.sgsong.Element.Element;

import java.util.Vector;

//Priority = 0 이 기본, 첫번째 놈과 priority 가 같은 놈들은 모두 애니메이션
public class AnimationGroupSeq
{
	private Vector<AniInfo> m_list = new Vector<AniInfo>();	

	private GameMain	m_pMain;
	
	public AnimationGroupSeq()
	{
		m_pMain = null;
		m_list.clear();
	}
	
	public void init( GameMain pMain )
	{
		m_pMain = pMain;
	}

	public void addMove( Element pTarget, int nID, int nEasingType, int nStartX, int nStartY, int nEndX, int nEndY, float fSec, boolean bNext )
	{
		AniInfo tmp = new AniInfo();
		tmp.clear();

		tmp.target = pTarget;

		tmp.nAniID = nID;
		tmp.nType = Animation.type_move;
		tmp.nEasingType = nEasingType;

		tmp.nStartX = nStartX;
		tmp.nStartY = nStartY;

		tmp.nEndX = nEndX;
		tmp.nEndY = nEndY;	

		tmp.fSec = fSec;

		int nSize = (int) m_list.size();
		if( nSize > 0 )
		{
			tmp.nPreAniID = m_list.get(nSize-1).nAniID;

			if( true == bNext )
			{
				tmp.nPriority = m_list.get(nSize-1).nPriority + 1;
			}
			else
			{
				tmp.nPriority = m_list.get(nSize-1).nPriority;
			}
		}	

		m_list.add( tmp );
	}

	public void add( int nType, Element pTarget, int nID, int nEasingType, float fStartValue, float fEndValue, float fSec, boolean bNext )
	{
		AniInfo tmp = new AniInfo();
		tmp.clear();		

		tmp.target = pTarget;

		tmp.nAniID = nID;
		tmp.nType = nType;
		tmp.nEasingType = nEasingType;

		tmp.fStartValue = fStartValue;
		tmp.fEndValue = fEndValue;

		tmp.fSec = fSec;

		int nSize = (int) m_list.size();
		if( nSize > 0 )
		{
			tmp.nPreAniID = m_list.get(nSize-1).nAniID;

			if( true == bNext )
			{
				tmp.nPriority = m_list.get(nSize-1).nPriority + 1;
			}
			else
			{
				tmp.nPriority = m_list.get(nSize-1).nPriority;
			}
		}	

//		if( (true == bNext) && (nSize > 0) )
//		{
//			tmp.nPriority = m_list[nSize-1].nPriority + 1;
//		}	

		m_list.add( tmp );
	}

	public void addSound( int nID )
	{
		int nSize = (int) m_list.size();
		if( nSize == 0 )
			return;

		m_list.get(nSize-1).nSoundID = nID;
	}

	public boolean isNextAni( int nID )
	{
		int nSize = (int) m_list.size();
		
		if( nSize == 0 )
			return false;

		if( nID == m_list.get(0).nPreAniID )
		{
		//	m_list.erase( m_list.begin() + 0 );
			return true;
		}

		return false;
	}

	public void clear()
	{
		m_list.clear();
	}

	public void start()
	{
		int nSize = (int) m_list.size();
		if( nSize == 0 )
			return;

		// 첫번째 것을 꺼내서 go
		int nPlayPriority = m_list.get(0).nPriority;

		// 그거와 같은 놈들 내보내고 제거 혹시나 해서 100번 이하로 돈다
		for( int k=0; k<100; k++ )
		{
			int nListSize = (int) m_list.size();
			if( nListSize == 0 )
			{
				break;
			}

			boolean bFind = false;

			for( int i=0; i<nListSize; i++ )
			{
				if( nPlayPriority == m_list.get(i).nPriority )
				{
					AniInfo info = m_list.get(i);
					Element pEle = info.target;

					switch( info.nType )
					{
					case Animation.type_move:
						pEle.setMoveAni( info.nAniID, info.nEasingType, info.nStartX, info.nStartY, info.nEndX, info.nEndY, info.fSec );
						break;

					case Animation.type_scale:
						pEle.setScaleAni( info.nAniID, info.nEasingType, info.fStartValue, info.fEndValue, info.fSec );
						break;

					case Animation.type_alpha:
						pEle.setAlphaAni( info.nAniID, info.nEasingType, info.fStartValue, info.fEndValue, info.fSec );
						break;

					case Animation.type_rotate:
						pEle.setRotateAni( info.nAniID, info.nEasingType, info.fStartValue, info.fEndValue, info.fSec );
						break;
					}

					// sound
					if( info.nSoundID > -1 )
					{
						m_pMain.playSound( info.nSoundID );
					}

					m_list.remove( i );

					bFind = true;
					break;
				}
			}

			// 찾았으면 다음것도 찾아본다
			if( true == bFind )
			{
				continue;
			}
			// 없으니깐 나간다.
			else
			{
				break;
			}
		}
	}

	


}