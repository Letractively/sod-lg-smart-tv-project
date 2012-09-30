
package com.sgsong.Game;

import java.util.Vector;
import com.sgsong.Draw.SG_PT;
import com.sgsong.Animation.*;
import com.sgsong.Element.VNumber;
import com.sgsong.Struct.*;

public class GamePlayer
{
	public static final int GAP = 10;
	public static final int MAX_PLAY_CARD = 10;
	
	public static final int min_point = 7;
	public static final int min_pi = 10;	
	
	private Vector<Card> m_kwang = new Vector<Card>();
	private Vector<Card> m_mung = new Vector<Card>();
	private Vector<Card> m_ddi = new Vector<Card>();
	private Vector<Card> m_pi = new Vector<Card>();
	
	private int	m_nPoint_kwang;	
	private int	m_nPoint_pi;
	private int	m_nPoint_ddi;
	private int	m_nPoint_mung;

	private int m_nPoint_total;
	private int m_nCnt_pi;	
	
	private SG_PT	m_ptKwang = new SG_PT();
	private SG_PT	m_ptPi = new SG_PT();
	private SG_PT	m_ptDdi = new SG_PT();
	private SG_PT	m_ptMung = new SG_PT();

	private boolean m_bGodori;
	private boolean m_bChoDan;
	private boolean m_bHongDan;
	private boolean m_bChungDan;
	private boolean m_bFiveKwang;

	private boolean m_bCheckGodori;
	private boolean m_bCheckChoDan;
	private boolean m_bCheckChungDan;
	private boolean m_bCheckHongDan;
	private boolean m_bCheckFiveKwang;
	
	private int	m_nGo;
	private int m_nPreGoPoint;
	
	private int[]	m_playCard = new int[MAX_PLAY_CARD];
	
	private VNumber		m_totalPoint = new VNumber();
	private VNumber		m_GwangPoint = new VNumber();
	private VNumber		m_MungPoint = new VNumber();
	private VNumber		m_DDiPoint = new VNumber();
	private VNumber		m_PiPoint = new VNumber();
	private VNumber		m_PiCount = new VNumber();
	
	private int			m_nShakeCount;

	
	private GameMain m_pMain = null;
	
	public GamePlayer()
	{			
	}

	public void initMe( GameMain pMain )
	{
		m_pMain = pMain;

		initVariable();
		initPostion();
		registScore();

	}
	
	public void registScore()
	{
		m_totalPoint.initMe( m_pMain, m_pMain.getSG(), 4 );
		m_totalPoint.setImage( Def.number_big );
		m_totalPoint.setGap( 27 );	
		m_totalPoint.setAmount( 0 );
		m_totalPoint.hide();

		m_GwangPoint.initMe( m_pMain, m_pMain.getSG(), 2 );
		m_GwangPoint.setImage( Def.number_yellow );
		m_GwangPoint.setGap( 20 );	
		m_GwangPoint.setAmount( 0 );
		m_GwangPoint.setAlpha( 0.7f );
		m_GwangPoint.hide();

		m_MungPoint.initMe( m_pMain, m_pMain.getSG(), 2 );
		m_MungPoint.setImage( Def.number_yellow );
		m_MungPoint.setGap( 20 );	
		m_MungPoint.setAmount( 0 );
		m_MungPoint.setAlpha( 0.7f );
		m_MungPoint.hide();

		m_DDiPoint.initMe( m_pMain, m_pMain.getSG(), 2 );
		m_DDiPoint.setImage( Def.number_yellow );
		m_DDiPoint.setGap( 20 );	
		m_DDiPoint.setAmount( 0 );
		m_DDiPoint.setAlpha( 0.7f );
		m_DDiPoint.hide();

		m_PiPoint.initMe( m_pMain, m_pMain.getSG(), 2 );
		m_PiPoint.setImage( Def.number_yellow );
		m_PiPoint.setGap( 20 );	
		m_PiPoint.setAmount( 0 );
		m_PiPoint.setAlpha( 0.7f );
		m_PiPoint.hide();	

		m_PiCount.initMe( m_pMain, m_pMain.getSG(), 2 );
		m_PiCount.setImage( Def.number_red );
		m_PiCount.setGap( 20 );	
		m_PiCount.setAmount( 0 );
		m_PiCount.setAlpha( 0.7f );
		m_PiCount.hide();
	}


	public void initVariable()
	{
		m_kwang.clear();
		m_pi.clear();
		m_ddi.clear();
		m_mung.clear();

		m_nPoint_kwang = 0;	
		m_nPoint_pi = 0;	
		m_nPoint_ddi = 0;	
		m_nPoint_mung = 0;	

		m_nPoint_total = 0;	
		m_nCnt_pi = 0;	

		m_bGodori = false;
		m_bChoDan = false;
		m_bHongDan = false;
		m_bChungDan = false;
		m_bFiveKwang = false;
		
		m_nGo = 0;
		m_nPreGoPoint = 0;
		m_nShakeCount = 0;


		clearPlayCard();
		
		m_totalPoint.setAmount( 0 );
		m_totalPoint.show();
		
		m_GwangPoint.setAmount( 0 );	
		m_GwangPoint.hide();
		
		m_MungPoint.setAmount( 0 );	
		m_MungPoint.hide();
		
		m_DDiPoint.setAmount( 0 );	
		m_DDiPoint.hide();
		
		m_PiPoint.setAmount( 0 );	
		m_PiPoint.hide();	
		
		m_PiCount.setAmount( 0 );	
		m_PiCount.hide();

	}	

	public void initPostion()
	{
		m_ptKwang.nX = 0;
		m_ptKwang.nY = 0;

		m_ptPi.nX = 0;
		m_ptPi.nY = 0;

		m_ptDdi.nX = 0;
		m_ptDdi.nY = 0;

		m_ptMung.nX = 0;
		m_ptMung.nY = 0;	
	}
	
	public void setPosTotalPoint( int nX, int nY )
	{
		m_totalPoint.setPos( nX, nY );
	}


	public void setPosKwang( int nX, int nY )
	{
		m_ptKwang.nX = nX;
		m_ptKwang.nY = nY;
		
		m_GwangPoint.setPos( nX, nY );

	}

	public void setPosMung( int nX, int nY )
	{
		m_ptMung.nX = nX;
		m_ptMung.nY = nY;
		
		m_MungPoint.setPos( nX, nY );

	}

	public void setPosDDi( int nX, int nY )
	{
		m_ptDdi.nX = nX;
		m_ptDdi.nY = nY;
		
		m_DDiPoint.setPos( nX, nY );

	}

	public void setPosPi( int nX, int nY )
	{
		m_ptPi.nX = nX;
		m_ptPi.nY = nY;
		
		m_PiCount.setPos( nX, nY );
		m_PiPoint.setPos( nX + 50, nY );
	}	

	public int getGo()
	{
		return m_nGo;
	}

	public void addCard( Card pCard )
	{
		if( pCard.isKwang() )
		{
			addKwang( pCard );
			return;
		}

		if( pCard.isMung() )
		{
			addMung( pCard );
			return;
		}

		if( pCard.isPi() )
		{
			addPi( pCard );
			return;
		}

		if( pCard.isDDi() )
		{
			addDDi( pCard );
			return;
		}
	}

	public boolean canGo()
	{
		if( m_nGo == 0 )
		{
			if( m_nPoint_total >= min_point )
				return true;
			else
				return false;
		}

		// 이전에 고를 했을 경우
		if( m_nPoint_total > m_nPreGoPoint )
		{
			return true;
		}

		return false;

	}
	
	public void updateGo()
	{
		m_nGo++;

		m_nPreGoPoint = m_nPoint_total;
	}


	public void addKwang( Card pCard )
	{
		m_bCheckFiveKwang = m_bFiveKwang;

		m_kwang.add( pCard );		

		pCard.setMoveAni( Animation.id_eat_card_kwang, EasingCurve.Linear, 
			pCard.getPosX(), pCard.getPosY(), m_ptKwang.nX, m_ptKwang.nY, 0.2f );
	}

	public void addMung( Card pCard )
	{
		m_bCheckGodori = m_bGodori;

		m_mung.add( pCard );

		pCard.setMoveAni( Animation.id_eat_card_mung, EasingCurve.Linear, 
			pCard.getPosX(), pCard.getPosY(), m_ptMung.nX, m_ptMung.nY, 0.2f );
	}

	public void addPi( Card pCard )
	{
		m_pi.add( pCard );

		pCard.setMoveAni( Animation.id_eat_card_pi, EasingCurve.Linear, 
			pCard.getPosX(), pCard.getPosY(), m_ptPi.nX, m_ptPi.nY, 0.2f );
	}

	public void addDDi( Card pCard )
	{
		m_bCheckChoDan = m_bChoDan;
		m_bCheckChungDan = m_bChungDan;
		m_bCheckHongDan = m_bHongDan;

		m_ddi.add( pCard );

		pCard.setMoveAni( Animation.id_eat_card_ddi, EasingCurve.Linear, 
			pCard.getPosX(), pCard.getPosY(), m_ptDdi.nX, m_ptDdi.nY, 0.2f );
	}

	public boolean havePi()
	{
		int nSize = (int) m_pi.size();
		if( nSize == 0 )
			return false;
		else
			return true;
	}

	public Card getPi()
	{
		int nLast = (int) m_pi.size() - 1;
		
		return m_pi.get(nLast);
	}

	public void deletePi()
	{
		int nLast = (int) m_pi.size() - 1;
		
		m_pi.remove( nLast );

		sortPi();
		updatePointPi();

	}

	public void sortAll()
	{
		sortPi();
		sortMung();
		sortKwang();
		sortDDi();
	}

	public void sortKwang()
	{
		int nSize = (int) m_kwang.size();
		if( nSize == 0 )
			return;

		for( int i=0; i<nSize; i++ )
		{
			m_kwang.get(i).setPos( m_ptKwang.nX + i * GAP, m_ptKwang.nY );
			m_pMain.setTopOrder( m_kwang.get(i).getID() );
		}
	}

	public void sortMung()
	{
		int nSize = (int) m_mung.size();
		if( nSize == 0 )
			return;

		for( int i=0; i<nSize; i++ )
		{
			m_mung.get(i).setPos( m_ptMung.nX + i * GAP, m_ptMung.nY );
			m_pMain.setTopOrder( m_mung.get(i).getID() );
		}
	}

	public void sortDDi()
	{
		int nSize = (int) m_ddi.size();
		if( nSize == 0 )
			return;

		for( int i=0; i<nSize; i++ )
		{
			m_ddi.get(i).setPos( m_ptDdi.nX + i * GAP, m_ptDdi.nY );
			m_pMain.setTopOrder( m_ddi.get(i).getID() );
		}
	}

	public void sortPi()
	{
		int nSize = (int) m_pi.size();
		if( nSize == 0 )
			return;

		for( int i=0; i<nSize; i++ )
		{
			m_pi.get(i).setPos( m_ptPi.nX + i * GAP, m_ptPi.nY );
			m_pMain.setTopOrder( m_pi.get(i).getID() );
		}
	}

	public void updatePointKwang()
	{
		int nSize = (int)m_kwang.size();

		if( nSize <= 2 )
		{
			m_nPoint_kwang = 0;			
		}
		else if( nSize == 3 )
		{
			m_nPoint_kwang = nSize;

			for( int i=0; i<nSize; i++ )
			{
				if( m_kwang.get(i).getType() == Card.type_kwnag_bi )
				{
					m_nPoint_kwang--;
					break;
				}
			}
		}
		else if( nSize == 4 )
		{
			m_nPoint_kwang = 4;
		}
		else if( nSize == 5 )
		{
			m_bFiveKwang = true;
			m_nPoint_kwang = 15;
		}	


		m_nPoint_total = m_nPoint_kwang + m_nPoint_mung + m_nPoint_ddi + m_nPoint_pi;
	}

	public void updatePointMung()
	{
		m_nPoint_mung = 0;

		int nSize = (int) m_mung.size();
		if( nSize <= 2 )
		{		
			return;
		}

		// 고도리 체크
		if( m_bGodori == false )
		{
			int nCountSae = 0;
			for( int i=0; i<nSize; i++ )
			{
				if( m_mung.get(i).getType() == Card.type_mung_sae )
					nCountSae++;
			}

			if( nCountSae == 3 )
			{
				m_bGodori = true;			
			}
		}

		// 일반 점수
		if( nSize >= 5 )
		{
			m_nPoint_mung = nSize - 4;		
		}

		if( m_bGodori == true )
		{
			m_nPoint_mung += 5;
		}

		m_nPoint_total = m_nPoint_kwang + m_nPoint_mung + m_nPoint_ddi + m_nPoint_pi;
	}

	public void updatePointDDi()
	{
		m_nPoint_ddi = 0;

		int nSize = (int) m_ddi.size();
		if( nSize <= 2 )
		{		
			return;
		}

		// 초단 체크
		if( m_bChoDan == false )
		{
			int nCount = 0;
			for( int i=0; i<nSize; i++ )
			{
				if( m_ddi.get(i).getType() == Card.type_ddi_cho )
					nCount++;
			}

			if( nCount == 3 )
			{
				m_bChoDan = true;
			}
		}

		// 청단 체크
		if( m_bChungDan == false )
		{
			int nCount = 0;
			for( int i=0; i<nSize; i++ )
			{
				if( m_ddi.get(i).getType() == Card.type_ddi_chung )
					nCount++;
			}

			if( nCount == 3 )
			{
				m_bChungDan = true;
			}
		}

		// 홍단 체크
		if( m_bHongDan == false )
		{
			int nCount = 0;
			for( int i=0; i<nSize; i++ )
			{
				if( m_ddi.get(i).getType() == Card.type_ddi_hong )
					nCount++;
			}

			if( nCount == 3 )
			{
				m_bHongDan = true;
			}
		}

		// 일반 점수
		if( nSize >= 5 )
		{
			m_nPoint_ddi = nSize - 4;		
		}

		if( m_bChoDan == true )
		{
			m_nPoint_ddi += 3;
		}

		if( m_bChungDan == true )
		{
			m_nPoint_ddi += 3;
		}

		if( m_bHongDan == true )
		{
			m_nPoint_ddi += 3;
		}

		m_nPoint_total = m_nPoint_kwang + m_nPoint_mung + m_nPoint_ddi + m_nPoint_pi;
	}

	public void updatePointPi()
	{
		m_nPoint_pi = 0;
		m_nCnt_pi = 0;

		int nSize = (int) m_pi.size();
	//	if( nSize == 0 )
		//	return;

		for( int i=0; i<nSize; i++ )
		{
			if( m_pi.get(i).getType() == Card.type_one_pi )
			{
				m_nCnt_pi++;
			}
			else if( (m_pi.get(i).getType() == Card.type_two_pi) || (m_pi.get(i).getType() == Card.type_mung_pig) )		
			{
				m_nCnt_pi += 2;
			}
			else if( m_pi.get(i).getType() == Card.type_three_pi )
			{
				m_nCnt_pi += 3;
			}
		}

		m_nPoint_pi = m_nCnt_pi - 9;
		if( m_nPoint_pi < 0 )
			m_nPoint_pi = 0;

		m_nPoint_total = m_nPoint_kwang + m_nPoint_mung + m_nPoint_ddi + m_nPoint_pi;
		

		m_PiPoint.setAmount( m_nPoint_pi );
		if( (int)m_pi.size() > 0 )
			m_PiPoint.show();

		if( m_nCnt_pi >= min_pi )
			m_PiCount.setImage( Def.number_blue );

		m_PiCount.setAmount( m_nCnt_pi );
		m_PiCount.show();

		m_totalPoint.setAmount( m_nPoint_total );

	}

	public void onEndAi( int nID )
	{
		if( nID == Animation.id_eat_card_kwang )
		{
			onAniKwang();
			return;
		}

		if( nID == Animation.id_eat_card_mung )
		{
			onAniMung();
			return;
		}

		if( nID == Animation.id_eat_card_ddi )
		{
			onAniDDi();
			return;
		}

		if( nID == Animation.id_eat_card_pi )
		{
			onAniPi();
			return;
		}
	}

	public void onAniKwang()
	{
		sortKwang();
		updatePointKwang();
		
		if( m_bCheckFiveKwang == false && m_bFiveKwang == true )
		{
			m_pMain.showFiveKwang();
		}
		
		m_GwangPoint.setAmount( m_nPoint_kwang );
		if( (int)m_kwang.size() > 0 )
			m_GwangPoint.show();

		m_totalPoint.setAmount( m_nPoint_total );

	}

	public void onAniMung()
	{
		sortMung();
		updatePointMung();

		// 고도리가 되었다.
		if( m_bCheckGodori == false && m_bGodori == true )
		{
			m_pMain.showGodori();
		}
		
		m_MungPoint.setAmount( m_nPoint_mung );
		if( (int)m_mung.size() > 0 )
			m_MungPoint.show();

		m_totalPoint.setAmount( m_nPoint_total );

	}

	public void onAniDDi()
	{
		sortDDi();
		updatePointDDi();

		// 초단이 되었다.
		if( m_bCheckChoDan == false && m_bChoDan == true )
		{
			m_pMain.showChoDan();
		}

		// 청단이 되었다.
		if( m_bCheckChungDan == false && m_bChungDan == true )
		{
			m_pMain.showChungDan();
		}

		// 홍단이 되었다.
		if( m_bCheckHongDan == false && m_bHongDan == true )
		{
			m_pMain.showHongDan();
		}
		
		m_DDiPoint.setAmount( m_nPoint_ddi );
		if( (int)m_ddi.size() > 0 )
			m_DDiPoint.show();

		m_totalPoint.setAmount( m_nPoint_total );

	}

	public void onAniPi()
	{
		sortPi();
		updatePointPi();
		

		/*m_PiPoint.setAmount( m_nPoint_pi );
		if( (int)m_pi.size() > 0 )
			m_PiPoint.show();

		if( m_nCnt_pi >= min_pi )
			m_PiCount.setImage( Def::number_blue );

		m_PiCount.setAmount( m_nCnt_pi );
		m_PiCount.show();

		m_totalPoint.setAmount( m_nPoint_total );*/

	}
	
	public boolean remainPlayCard()
	{
		for( int i=0; i<MAX_PLAY_CARD; i++ )
		{
			if( m_playCard[i] > -1 )
				return true;
		}

		return false;
	}

	public boolean existPlayCard( int i )
	{
		if( m_playCard[i] == -1 )
			return false;

		return true;
	}

	public void setPlayCard( int i, int nID )
	{
		m_playCard[i] = nID;
	}

	public int getPlayCard( int i )
	{
		return m_playCard[i];
	}

	public void clearPlayCard()
	{
		for( int i=0; i<MAX_PLAY_CARD; i++ )
		{
			removePlayCard( i );
		}
	}

	public void removePlayCard( int i )
	{
		m_playCard[i] = -1;
	}
	

	public void setShake()
	{
		m_nShakeCount++;
	}

	public int getShake()
	{
		return m_nShakeCount;
	}

	public int checkChongTong()
	{
		for( int i=0; i<MAX_PLAY_CARD; i++ )
		{
			int nCount = 0;
			int nNum = m_pMain.getCard(m_playCard[i]).getNumber();
			for( int j=0; j<MAX_PLAY_CARD; j++ )
			{
				if( nNum == m_pMain.getCard(m_playCard[j]).getNumber() )
				{
					nCount++;
				}
			}

			if( nCount == 4 )
			{
				return nNum;
			}
		}

		return -1;
	}

	
}
