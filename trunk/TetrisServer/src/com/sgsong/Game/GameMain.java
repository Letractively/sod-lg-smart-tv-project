package com.sgsong.Game;

import javax.microedition.khronos.opengles.GL10;

import sod.common.NetworkUtils;
import sod.common.Packet;

import com.sgsong.Net.ConnectionBean;
import com.sgsong.Net.Networking;
import com.sgsong.Struct.*;

import com.sgsong.Animation.*;

import com.sgsong.Draw.SG;
import com.sgsong.Draw.SG_COLOR;
import com.sgsong.Draw.SG_PT;
import com.sgsong.Draw.SG_RECT;

import com.sgsong.Element.Element;
import com.sgsong.Element.ElementBound;
import com.sgsong.Element.ElementList;

import com.sgsong.main.MainApp;
import com.sgsong.main.Run;

import com.sgsong.Sound.GameSound;
import com.sgsong.GameCommand.GameCommand;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.webkit.ConsoleMessage;

import java.io.Console;
import java.util.*;

// portrait landscape

public class GameMain 
{	
	public static final int game_state_loading = -1;
	public static final int game_state_ready = 100;
	public static final int game_state_start = 110;
	public static final int game_state_run = 120;
	public static final int game_state_end = 130;	
	
	//사용자 기다리기
	public static final int game_state_wait = 150;
	
	public static final int selected_state_not = 200;
	public static final int selected_state_first_eat = 210;
	public static final int selected_state_second_eat = 220;
	public static final int selected_state_pimung = 230;	

	public static final int max_board_card = 10;

	public static final int max_player = 2;
	public static final int player_com = 0;
	public static final int player_me = 1;	
// public:
	public GameSound m_sound = new GameSound();
	public GameCommand m_cmd = new GameCommand();
	public Packet pkt=new Packet();
// private:
	private SG_COLOR clrFPS = new SG_COLOR();
	private SG_RECT rtFPS[] = new SG_RECT[4];	
private MainApp m_pApp = null;
	
	private SG m_sg = null;
	
	private ElementList m_eliList = new ElementList();
	private Vector<Animation> m_aniList = new Vector<Animation>();
	
	private SG_PT m_ptBG = new SG_PT();
	
	private SG_PT	m_ptMiniCard[] = new SG_PT[GamePlayer.MAX_PLAY_CARD];
	private SG_PT	m_ptMyCard[] = new SG_PT[GamePlayer.MAX_PLAY_CARD];

	private SG_PT	m_ptCenter = new SG_PT();
	
	private long m_curTime;
	
	private int m_state = game_state_loading;
	
// private:	
	private Element m_loading_bg = new Element();
	private ElementBound m_loading_bar = new ElementBound();	
	private SG_RECT m_rtLoadingBar = new SG_RECT();

	private Element m_game_bg = new Element();
	
	private EasingCurve m_curve = new EasingCurve();	
	
	private Element		m_miniCard[] = new Element[GamePlayer.MAX_PLAY_CARD];
	private Card		m_card[] = new Card[Card.MAX_CARD];	
	
	private BoardCard	m_boardCard[][] = new BoardCard[max_board_card][4];	
	private int			m_nBoardCardCount;

	
	private Vector<Card> m_originCard = new Vector<Card>();
	private Vector<Card> m_gameCard = new Vector<Card>();	

	private FindCardInfo	m_findInfo = new FindCardInfo();

	private int				m_nSendBoardCard;

	private Element			m_btnStart = new Element();
	
	Element			m_sunMark = new Element();
	Element			m_turnMark = new Element();

	private GamePlayer	m_player[] = new GamePlayer[max_player];

	private Vector<Integer>	m_curEatCard = new Vector<Integer>();

	private int			m_nEatNum;

	private int			m_nSendID;

	private Element		m_dummy = new Element();	

	private EffectMan	m_effectMan = new EffectMan();

	private int			m_nSelectedState;	

	private MessageBoxSelCard	m_message_box_sel_card = new MessageBoxSelCard();
	private MessageBoxGoStop	m_message_box_gostop = new MessageBoxGoStop();
	private MessageBoxNew		m_message_box_result = new MessageBoxNew();
	
	boolean				m_bEatBbuck;

	private int			m_nMyCardIndex;

	private boolean		m_bMyTurn;
	int			m_whoSun;
	int			m_nAddCount;

	SG_PT		m_turnPos[] = new SG_PT[max_player];
	SG_PT		m_sunPos[] = new SG_PT[max_player];


	
	private AnimationGroupSeq	m_aniGroupSeq = new AnimationGroupSeq();

	public GameMain(GL10 mGL)
	{
	//	ShowLog("[SGSG] GameMain");

		m_sg = new SG(mGL);
		
		clearList();

		m_curTime = 0;				

		for( int i=0; i<4; i++ )
		{
			rtFPS[i] = new SG_RECT();
		}
		
		m_aniGroupSeq.init( this );
		m_aniGroupSeq.clear();		

		m_whoSun = player_me;
		
	}




	public void clearList()
	{
		m_aniList.clear();
	}
	
	public SG getSG()
	{
		return m_sg;
	}
	
	public void ShowLog( String strMsg )
	{
		Log.i( "[SGSG]", strMsg );
	}

	public EasingCurve getCurve()
	{
		return m_curve;
	}

	public float getCurveValue( int nType, double percent )
	{
		return m_curve.getValue(nType, percent);
	}
	
	public void playSound( int nID )
	{
		m_sound.playSound( nID );
	}

	public void InitMe( MainApp pApp )
	{
		m_sg.InitMe(this);
		initPosition();
		
		m_pApp = pApp;
	}

	public void display(long curTime)
	{
		m_curTime = curTime;		
		
		if( game_state_loading == m_state )
		{			
			loadingState();			
			return;
		}		
		
		m_eliList.Draw(m_curTime);		
		m_sound.setCurTime( m_curTime );
		m_cmd.setCurTime( m_curTime );
	}
	
	public void loadingState()		//로딩 화면
	{
		m_loading_bg.DrawMe( m_curTime );
		
		int nNewX = (int)((float)(387) * m_pApp.getLoadPercent());
		
		m_rtLoadingBar.nRight = nNewX;
		
		m_loading_bar.DrawMe(m_curTime);
			
		if( true == m_pApp.loadNextImaage() )
		{
			m_loading_bg.setVisible( false );
			m_loading_bar.setVisible(false);
			registElements();	
		}
	}


	public void afterDraw()
	{
		int nEndID = -1;

		for (int i = 0; i < m_aniList.size(); i++)
		{
			if (m_aniList.get(i) != null)
			{
				if (true == m_aniList.get(i).isMoveEnd())
				{
					nEndID = m_aniList.get(i).getMoveID();

					m_aniList.get(i).clearMove();
					onEndAni(nEndID);
				}

				if (true == m_aniList.get(i).isPartEnd())
				{
					nEndID = m_aniList.get(i).getPartID();

					m_aniList.get(i).clearPart();
					onEndAni(nEndID);
				}
				
				if (true == m_aniList.get(i).isAlphaEnd())
				{
					nEndID = m_aniList.get(i).getAlphaID();

					m_aniList.get(i).clearAlpha();
					onEndAni(nEndID);
				}
				
				if (true == m_aniList.get(i).isCenterEnd())
				{
					nEndID = m_aniList.get(i).getCenterID();

					m_aniList.get(i).clearCenter();
					onEndAni(nEndID);
				}			

				if (true == m_aniList.get(i).isRotateEnd()) {
					nEndID = m_aniList.get(i).getRotateID();

					m_aniList.get(i).clearRotate();
					onEndAni(nEndID);
				}

				if (true == m_aniList.get(i).isScaleEnd()) {
					nEndID = m_aniList.get(i).getScaleID();

					m_aniList.get(i).clearScale();
					onEndAni(nEndID);
				}				
			}
		}
	}

	public void onEndAni(int nID)
	{
	//	String sgLog = String.format("[SGSG] onEndAni - %d", nID);
	//	ShowLog(sgLog);
		
		if( true == m_aniGroupSeq.isNextAni( nID ) )
		{
			m_aniGroupSeq.start();
			return;
		}

		if( (nID >= Animation.id_send_first_card_to_com) && (nID <= Animation.id_send_first_card_to_com + 5) )
		{
			int n = nID - Animation.id_send_first_card_to_com;
			
			getCard( m_player[player_com].getPlayCard(n) ).setVisible( false );

			m_miniCard[n].setVisible( true );
		
			return;
		}

		if( (nID >= Animation.id_send_first_card_to_me) && (nID <= Animation.id_send_first_card_to_me + 5) )
		{
			int n = nID - Animation.id_send_first_card_to_me;

			getCard( m_player[player_me].getPlayCard(n) ).setFront();
			return;
		}

		if( (nID >= Animation.id_send_second_card_to_com) && (nID <= Animation.id_send_second_card_to_com + 5) )
		{
			int n = nID - Animation.id_send_second_card_to_com + 5;

			getCard( m_player[player_com].getPlayCard(n) ).setVisible( false );

			m_miniCard[n].setVisible( true );;
			
			return;
		}
		
		if( (nID >= Animation.id_send_second_card_to_me) && (nID <= Animation.id_send_second_card_to_me + 5) )
		{
			int n = nID - Animation.id_send_second_card_to_me + 5;

			getCard( m_player[player_me].getPlayCard(n) ).setFront();
			return;
		}

		if( (nID >= Animation.id_send_first_card_to_board) && (nID <= Animation.id_send_first_card_to_board + 4) )
		{
			m_nSendBoardCard++;
			int n = nID - Animation.id_send_first_card_to_board;

			getCard( m_boardCard[n][0].nID ).setFront();		

			return;
		}

		if( (nID >= Animation.id_send_second_card_to_board) && (nID <= Animation.id_send_second_card_to_board + 4) )
		{
			m_nSendBoardCard++;

			int n = nID - Animation.id_send_second_card_to_board + 4;

			getCard( m_boardCard[n][0].nID ).setFront();

			if( 8 == m_nSendBoardCard )
			{
				sortBoardCard();
				m_nSendBoardCard = 0;
			}

			return;
		}		

		if( nID == Animation.id_send_mycard_to_board )
		{
			showCardEffect();
			sendBackCardToBoard();
			return;
		}

		if( nID == Animation.id_send_comcard_to_board )
		{
			showCardEffect();
			sendBackCardToBoard();
			return;
		}

		if( nID == Animation.id_backcard_to_board )
		{
			showCardEffect();

			dummyAnimation( Animation.id_dummy_end_turn, 0.4f );
			return;
		}

		if( nID == Animation.id_dummy_com_turn )
		{
			comAct();
			return;
		}

		if( nID == Animation.id_dummy_end_turn )
		{
			endTurn();
			return;
		}

		if( nID == Animation.id_dummy_calc_card )
		{
			calcCard();
			return;
		}


		if( nID == Animation.id_dummy_new_turn )
		{
			newTurn();		
			return;
		}

		if( nID >= Animation.id_effect_1go && nID <= Animation.id_effect_6go )
		{
			m_effectMan.hideGo();
			changeTurn();
			return;
		}
		
		if( nID == Animation.id_effect_stop )
		{
			m_effectMan.onEndAni( Animation.id_effect_stop );
			m_message_box_result.show();
			return;
		}

		if( nID >= Animation.id_eat_card_kwang && nID <= Animation.id_eat_card_pi )
		{
			if( true == m_bMyTurn )
			{
				m_player[player_me].onEndAi( nID );
			}
			else
			{
				m_player[player_com].onEndAi( nID );
			}			
			return;
		}

		if( nID >= Animation.id_effect_jjock && nID <= Animation.id_effect_ddadac )
		{
			m_effectMan.onEndAni( nID );
			return;
		}
	}	

	public void onSingleTapUp( int nX, int nY )
	{	
	}

	public void onDoubleTap( int nX, int nY )
	{
	}

	public void onLongClick( int nX, int nY )
	{

	}

	public void onFling( int nStartX, int nStartY, int nEndX, int nEndY, float fVelocityX, float VelocityY )
	{	
	}
	
	public void onTouchDown( int nX, int nY )	// 지금 스타트 버튼 누르니 들어왔음...카드 선택시에도 들어옴  (클라이언트에서 이부분만 조금 손질 하면 될듯)
	{		
	//	String sgLog = String.format("onTouchDown - nX: %d, nY: %d", nX, nY );
	//	ShowLog( sgLog );	

		int nID = -1;
		nID = m_eliList.getTouchedElementID( nX, nY );

	//	sgLog = String.format( "[SGSG] nID: %d", nID );		
	//	ShowLog( sgLog );	

		if( nID == -1 )
		{
			return;
		}

		onTouchedElement( nID );
	}	

	
	public void onTouchMove( int nX, int nY )
	{		
	}
	
	public void onTouchUp( int nX, int nY )
	{				
	}

	public void onTouchedElement(int nID)
	{
		System.out.println(nID);
		if( nID == Element.id_btn_start )
		{
		if(ConnectionBean.OpenSigniture)
			{
			game_start();
			m_btnStart.setVisible( false );
			}
			else
			{
				return;
			}
		}
		if( nID == Element.id_select_card1 || nID == Element.id_select_card2 )
		{
			onCmdSelectCard( nID );
			return;
		}

		if( nID == Element.id_messege_box_gostop_go )
		{
			m_message_box_gostop.hide();
			showGo();
			return;
		}

		if( nID == Element.id_messege_box_gostop_stop )
		{
			m_message_box_gostop.hide();

			showStop();
			return;
		}
		
		if( nID == Element.id_messege_box_ok )
		{

			m_message_box_result.hide();
			game_ready();
			return;
		}

		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{	
			if( m_player[player_me].existPlayCard(i) == false )
				continue;

			if( m_player[player_me].getPlayCard(i) == nID )
			{
				sendMyCardToBoard( i );
				return;
			}
		}
	}
	
	public void addElement(Element pEle, int nOrderType)
	{
		m_eliList.addElement(pEle, nOrderType);
	}
	
	public void registElements()
	{
		registPlayers();

		registBG();	

		registMiniCard();

		registCards();

		registBoardCard();
		
		registSunMark();

		registTurnMark();

		registDummy();

		registEffect();

		registMessageBox();
		
		game_ready();
	}
	
	public void registLoading()
	{		
		m_loading_bg.InitMe(this, m_sg);
		m_loading_bg.setID(Element.id_loading_bg);
		m_loading_bg.setImageID(Def.loading_bg);
		m_loading_bg.setPos(274, 161);
		m_loading_bg.setVisible(true);
		
		m_loading_bar.InitMe(this, m_sg);
		m_loading_bar.setID(Element.id_loading_bar);
		m_loading_bar.setImageID(Def.loading_bar);
		m_loading_bar.setPos(206, 360);
		
		m_rtLoadingBar.nLeft = 0;
		m_rtLoadingBar.nTop = 0;
		m_rtLoadingBar.nRight = 0;	// 387
		m_rtLoadingBar.nBottom = 20;

		m_loading_bar.setBound( m_rtLoadingBar );
		
		m_loading_bar.setVisible(true);

	//	m_eliList.addElement(m_loading, ElementList.bottom);
	}
	
	public void registPlayers()
	{
		m_player[player_com] = new GamePlayer();
		m_player[player_com].initMe( this );
		m_player[player_com].setPosTotalPoint( 162, 8 );
		m_player[player_com].setPosKwang( 215, 60 );
		m_player[player_com].setPosMung( 320, 60 );
		m_player[player_com].setPosDDi( 450, 60 );
		m_player[player_com].setPosPi( 320, 0 );

		m_player[player_me] = new GamePlayer();
		m_player[player_me].initMe( this );
		m_player[player_me].setPosTotalPoint( 162, 378 );
		m_player[player_me].setPosKwang( 215, 350 );
		m_player[player_me].setPosMung( 320, 350 );
		m_player[player_me].setPosDDi( 450, 350 );
		m_player[player_me].setPosPi( 215, 420 );

	}

	public void registBG()
	{
		m_game_bg.InitMe( this, m_sg );
		m_game_bg.setID( Element.id_bg );
		m_game_bg.setImageID( Def.game_bg );	
		m_game_bg.setPos( m_ptBG.nX, m_ptBG.nY );
		m_game_bg.setVisible( false );	

		m_eliList.addElement( m_game_bg, ElementList.bottom );
	}

	public void registMiniCard()
	{
		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{
			m_miniCard[i] = new Element();
			m_miniCard[i].InitMe( this, m_sg );
			m_miniCard[i].setID( Element.id_mini_card_0 + i );
			m_miniCard[i].setImageID( Def.mini_card );			

			m_miniCard[i].setPos( m_ptMiniCard[i].nX, m_ptMiniCard[i].nY );
			m_miniCard[i].setVisible( false );	

			m_eliList.addElement( m_miniCard[i], ElementList.middle );
		}	
	}
	
	void registTurnMark()
	{
		m_turnPos[player_com].nX = 7;
		m_turnPos[player_com].nY = 0;

		m_turnPos[player_me].nX = 7;
		m_turnPos[player_me].nY = 375;	

		m_turnMark.InitMe( this, m_sg );
		m_turnMark.setID( Element.id_turn_mark );
		m_turnMark.setImageID( Def.turn_frame );	
		m_turnMark.setPos( m_turnPos[player_me].nX, m_turnPos[player_me].nY );
		m_turnMark.setVisible( false );	

		m_eliList.addElement( m_turnMark, ElementList.top );

//		m_turnMark
	}

	void registSunMark()
	{
		m_sunPos[player_com].nX = 100;
		m_sunPos[player_com].nY = 40;

		m_sunPos[player_me].nX = 100;
		m_sunPos[player_me].nY = 410;

//		m_sunMark
		m_sunMark.InitMe( this, m_sg );
		m_sunMark.setID( Element.id_sun_mark );
		m_sunMark.setImageID( Def.mark_sun );	
		m_sunMark.setPos( m_sunPos[player_me].nX, m_sunPos[player_me].nY );
		m_sunMark.setVisible( false );	

		m_eliList.addElement( m_sunMark, ElementList.top );

	}


	public void registDummy()
	{
		m_dummy.InitMe( this, m_sg );
		m_dummy.addOnAnimation();	
		m_dummy.setID( Element.id_dummy );
		m_dummy.setImageID( Def.mini_card );			

		m_dummy.setPos( 0, 0 );
		m_dummy.setVisible( false );	

		m_eliList.addElement( m_dummy, ElementList.bottom );
	}

	public void registEffect()
	{	
		m_effectMan.initMe( this, m_sg );
	}

	public void registMessageBox()
	{
		m_message_box_sel_card.initMe( this, m_sg );
		m_message_box_sel_card.setPos( 495, 182 );

		m_message_box_gostop.initMe( this, m_sg );
		m_message_box_gostop.setPos( 480, 170 );

		m_message_box_result.initMe( this, m_sg );
		m_message_box_result.setBG( Def.message_box_result );
		m_message_box_result.setPos( 253, 97 );
	}
	public void registCards() // 카드 이미지 등록
	{
		for( int i=0; i<Card.MAX_CARD; i++ )
		{
			m_card[i] = new Card();
			m_card[i].InitMe( this, m_sg );
			m_card[i].addOnAnimation();	
			m_card[i].setImageID( Def.gostop_card );	
			m_card[i].setPartCount( 4, 13 );
			m_card[i].setBackCard( Card.back_index );	
			m_card[i].setBack();
			m_card[i].setTouch( false );
			m_card[i].setPos( m_ptCenter.nX, m_ptCenter.nY );
			m_card[i].setVisible( false );

			m_eliList.addElement( m_card[i], ElementList.middle );
		}

		// test
		m_btnStart.InitMe( this, m_sg );	
		m_btnStart.setImageID( Def.btn_start );	
		m_btnStart.setID( Element.id_btn_start );	
		m_btnStart.setTouch( true );
		m_btnStart.setPos( 280, 162 );	

		m_eliList.addElement( m_btnStart, ElementList.middle );

		makeCardInfomation();	
	}

	public void makeCardInfo( int nIndex, int nEleID, int nNumber, int nType, int nFrontPart )
	{
		m_card[nIndex].setID( nEleID );
		m_card[nIndex].setNumber( nNumber );
		m_card[nIndex].setType( nType );

		m_card[nIndex].setFrontCard( nFrontPart );
	}

	public void registBoardCard()
	{
		for( int i=0; i<max_board_card; i++ )
		{
			for( int j=0; j<4; j++ )
			{
				m_boardCard[i][j] = new BoardCard();
			}
		}
		
		for( int i=0; i<max_board_card; i++ )
		{
			for( int j=0; j<4; j++ )
			{
				m_boardCard[i][j].nID = -1;		
				m_boardCard[i][j].bExist = false;
			}
		}	

		for( int i=0; i<=8; i=i+2 )
		{
			for( int j=0; j<4; j++ )
			{			
				m_boardCard[i][j].nPosX = 70 + (i/2) * 80 + j * 10;
				m_boardCard[i][j].nPosY = 134 + j * 10;			
			}		
		}

		for( int i=1; i<=9; i=i+2 )
		{
			for( int j=0; j<4; j++ )
			{			
				m_boardCard[i][j].nPosX = 70 + (i-1)/2 * 80 + j * 10;
				m_boardCard[i][j].nPosY = 230 + j * 10;			
			}		
		}

	}		

	public void addAnimation(Animation pAni)
	{
		m_aniList.add(pAni);
	}

	public void initPosition()
	{
		m_ptBG.nX = 0;
		m_ptBG.nY = 0;

		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{
			m_ptMiniCard[i] = new SG_PT();
		}
		
		m_ptMiniCard[0].nX = 219;
		m_ptMiniCard[0].nY = 7;

		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{		
			if( i >= 5 )
			{
				m_ptMiniCard[i].nX = m_ptMiniCard[0].nX + Card.MINI_GAP_W * (i-5);
				m_ptMiniCard[i].nY = m_ptMiniCard[0].nY + Card.MINI_GAP_H;
			}
			else
			{
				m_ptMiniCard[i].nX = m_ptMiniCard[0].nX + Card.MINI_GAP_W * i;
				m_ptMiniCard[i].nY = m_ptMiniCard[0].nY;
			}
		}
		
		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{
			m_ptMyCard[i] = new SG_PT();
		}

		m_ptMyCard[0].nX = 592;
		m_ptMyCard[0].nY = 358;

		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{		
			if( i >= 5 )
			{
				m_ptMyCard[i].nX = m_ptMyCard[0].nX + Card.GAP_W * (i-5);
				m_ptMyCard[i].nY = m_ptMyCard[0].nY + Card.GAP_H;
			}
			else
			{
				m_ptMyCard[i].nX = m_ptMyCard[0].nX + Card.GAP_W * i;
				m_ptMyCard[i].nY = m_ptMyCard[0].nY;
			}
		}	

		m_ptCenter.nX = 9;
		m_ptCenter.nY = 134;
		
		for( int i=0; i<max_player; i++ )
		{
			m_turnPos[i] = new SG_PT();
			m_sunPos[i] = new SG_PT();
		}
	}

	public void ShowFPS(int n1000, int n100, int n10, int n1)
	{
		clrFPS.fRed = 1.0f;
		clrFPS.fGreen = 1.0f;
		clrFPS.fBlue = 1.0f;
		clrFPS.fAlpha = 1.0f;

		rtFPS[0].nLeft = 0;
		rtFPS[0].nTop = 0;

		rtFPS[1].nLeft = 16;
		rtFPS[1].nTop = 0;

		rtFPS[2].nLeft = 32;
		rtFPS[2].nTop = 0;

		rtFPS[3].nLeft = 48;
		rtFPS[3].nTop = 0;

		for (int i = 0; i < 4; i++) {
			rtFPS[i].nRight = rtFPS[i].nLeft + 16;
			rtFPS[i].nBottom = rtFPS[i].nTop + 16;
			rtFPS[i].nCenterX = rtFPS[i].nLeft + 8;
			rtFPS[i].nCenterY = rtFPS[i].nTop + 8;
		}

		m_sg.DrawPartImage(Def.game_font, 16, 16, n1000+144, rtFPS[0], 1.0f, 0.0f, clrFPS);
		m_sg.DrawPartImage(Def.game_font, 16, 16, n100+144, rtFPS[1], 1.0f, 0.0f, clrFPS);
		m_sg.DrawPartImage(Def.game_font, 16, 16, n10+144, rtFPS[2], 1.0f, 0.0f, clrFPS);
		m_sg.DrawPartImage(Def.game_font, 16, 16, n1+144, rtFPS[3], 1.0f, 0.0f, clrFPS);
	}

	public boolean isInRect(int nX, int nY, SG_RECT rt)
	{
		if ((nX >= rt.nLeft) && (nX <= rt.nRight))
		{
			if ((nY >= rt.nTop) && (nY <= rt.nBottom))
			{
				return true;
			}
		}

		return false;
	}

	public boolean isSameRect(SG_RECT pRectSrc, SG_RECT pRectDest)
	{
		if ((pRectSrc.nLeft == pRectDest.nLeft)
				&& (pRectSrc.nTop == pRectDest.nTop)
				&& (pRectSrc.nRight == pRectDest.nRight)
				&& (pRectSrc.nBottom == pRectDest.nBottom))
		{
			return true;
		}

		return false;
	}
	
	public void test()
	{		
	}

	public void sendFirstCardToCom()	// 컴퓨터에게 패 5장 주기 인덱스
	{
		for( int i=0; i<5; i++ )
		{
			int nIndex = (int) m_gameCard.size() - 1;
			Card findCard = m_gameCard.get(nIndex);

			m_player[player_com].setPlayCard( i, findCard.getID() );		

			m_eliList.setTopOrder( findCard.getID() );

			findCard.setMoveAni( Animation.id_send_first_card_to_com + i, EasingCurve.Linear,
				findCard.getPosX(), findCard.getPosY(), m_ptMiniCard[i].nX, m_ptMiniCard[i].nY, 0.5f );

			m_gameCard.remove( nIndex );
		}

		m_sound.playSound( GameSound.card_send );
	}

	public void sendSecondCardToCom()		// 컼퓨터에게 패 5장 주기
	{
		for( int i=0; i<5; i++ )
		{
			int nIndex = (int) m_gameCard.size() - 1;
			Card findCard = m_gameCard.get(nIndex);

			m_player[player_com].setPlayCard( i+5, findCard.getID() );		

			m_eliList.setTopOrder( findCard.getID() );		

			findCard.setMoveAni( Animation.id_send_second_card_to_com + i, EasingCurve.Linear,
				findCard.getPosX(), findCard.getPosY(), m_ptMiniCard[i+5].nX, m_ptMiniCard[i+5].nY, 0.5f );

			m_gameCard.remove( nIndex );
		}

		m_sound.playSound( GameSound.card_send );
	}
	public Packet SendNetCard(Integer CardId)
	{
		Packet pak=new Packet();
		pak.push("card");
		pak.push(CardId);
		return pak;
	}
	
	
	public void sendFirstCardToMe()		// 나에게 첫번째 패 5장 주기
	{
		pkt.push("sendCard");
		for( int i=0; i<5; i++ )
		{
			int nIndex = (int) m_gameCard.size() - 1;
			Card findCard = m_gameCard.get(nIndex);

			m_player[player_me].setPlayCard( i, findCard.getID() );
			pkt.push(findCard.getID());
			m_eliList.setTopOrder( findCard.getID() );
			findCard.setMoveAni( Animation.id_send_first_card_to_me + i, EasingCurve.Linear,
				findCard.getPosX(), findCard.getPosY(), m_ptMyCard[i].nX, m_ptMyCard[i].nY, 0.5f );

			m_gameCard.remove( nIndex );
		}

		m_sound.playSound( GameSound.card_send );
	}

	public void sendSecondCardToMe()	// 두번째 패 5자ㅏㅇ 주기
	{
		for( int i=0; i<5; i++ )
		{
			int nIndex = (int) m_gameCard.size() - 1;
			Card findCard = m_gameCard.get(nIndex);;

			m_player[player_me].setPlayCard( i+5, findCard.getID() );
			pkt.push(findCard.getID());
			m_eliList.setTopOrder( findCard.getID() );
			findCard.setMoveAni( Animation.id_send_second_card_to_me + i, EasingCurve.Linear,
				findCard.getPosX(), findCard.getPosY(), m_ptMyCard[i+5].nX, m_ptMyCard[i+5].nY, 0.5f );
			
			m_gameCard.remove( nIndex );
		}
		Networking.setPacket(pkt);
		Packet pt=new Packet();
		pt.push("down");
		ConnectionBean.server.send(pt,ConnectionBean.ClientId);
		Networking.setGM(this);
		
		m_sound.playSound( GameSound.card_send );

		setTouchMyCard( false );
	}

	public void sendFirstCardToBoard()	// 판 깔기
	{
		for( int i=0; i<4; i++ )
		{
			int nIndex = (int) m_gameCard.size() - 1;
			Card findCard = m_gameCard.get(nIndex);

			m_boardCard[i][0].nID = findCard.getID();
			m_boardCard[i][0].bExist = true;
			m_boardCard[i][0].nNumber = findCard.getNumber();

		//	updateBoardCard( i, 0, findCard );
			
			m_eliList.setTopOrder( findCard.getID() );
			findCard.setMoveAni( Animation.id_send_first_card_to_board + i, EasingCurve.Linear,
				findCard.getPosX(), findCard.getPosY(), m_boardCard[i][0].nPosX, m_boardCard[i][0].nPosY, 0.5f );

			m_gameCard.remove( nIndex );
		}

		m_sound.playSound( GameSound.card_send );	
		
		m_nBoardCardCount += 4;

	}

	public void setTopOrder( int nID )
	{
		m_eliList.setTopOrder( nID );
	}

	public void sendSecondCardToBoard()		// 두 번째 판 깔기
	{
		for( int i=0; i<4; i++ )
		{
			int nIndex = (int) m_gameCard.size() - 1;
			Card findCard = m_gameCard.get(nIndex);

			m_boardCard[i+4][0].nID = findCard.getID();
			m_boardCard[i+4][0].bExist = true;
			m_boardCard[i+4][0].nNumber = findCard.getNumber();

		//	updateBoardCard( i+5, 0, findCard );

			m_eliList.setTopOrder( findCard.getID() );
			findCard.setMoveAni( Animation.id_send_second_card_to_board + i, EasingCurve.Linear,
				findCard.getPosX(), findCard.getPosY(), m_boardCard[i+4][0].nPosX, m_boardCard[i+4][0].nPosY, 0.5f );

			m_gameCard.remove( nIndex );
		}	

		m_sound.playSound( GameSound.card_send );
		
		m_nBoardCardCount += 4;

	}

	public Card getCard( int nID )	// 셔플 과정에서,, 그리고 선태카드에서
	{
 		for( int i=0; i<Card.MAX_CARD; i++ )
		{
			if( m_card[i].getID() == nID )
				return m_card[i];
		}

		return m_card[0];
	}

	public void comTurn()		// 컴퓨터 
	{
		showTurnMark( player_com );		
		
		int nSel = (int)(Math.random() * 10);		

		// 0.1f ~ 3.9f
		float fSec = 0.1f + (float) nSel * 0.1f;

		dummyAnimation( Animation.id_dummy_com_turn, fSec );
	}
	
	public void comAct()
	{
		// 같은거 찾기
		boolean bFind = false;
		int nFindIndex = -1;

		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{
			if( m_player[player_com].existPlayCard(i) == false )
				continue;

			Card pCard = getCard(m_player[player_com].getPlayCard(i) );		
			
			if( canEat( pCard ) > -1 )
			{
				nFindIndex = i;
				bFind = true;
				break;
			}
		}

		// 찾았다
		if( true == bFind )
		{
			sendComCardToBoard( nFindIndex );
		}
		else
		{
			for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
			{
				if( m_player[player_com].existPlayCard(i) == false )
					continue;

				sendComCardToBoard( i );
				break;
			}
		}
		
		hideTurnMark();

	}
	
	public void sendCardAni( Card pCard, int nAniID )  // 보내는 카드의 애니메이션
	{
		float fScale = 2.0f;
		if( true == m_bMyTurn )
		{
			fScale = 4.0f;
		}

		m_aniGroupSeq.clear();

		m_aniGroupSeq.add( Animation.type_scale, pCard, -1, EasingCurve.InCirc, 1.0f, fScale, 0.3f, false );
		
		if( true == m_bMyTurn )
		{
			m_aniGroupSeq.add( Animation.type_alpha, pCard, -1, EasingCurve.InCirc, 1.0f, 0.5f, 0.3f, false );
		}

		m_aniGroupSeq.addMove( pCard, nAniID, EasingCurve.OutCirc, pCard.getPosX(), pCard.getPosY(),
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nPosX, m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nPosY, 0.2f, true );

		m_aniGroupSeq.addSound( GameSound.card_hit );

		m_aniGroupSeq.add( Animation.type_scale, pCard, 0, EasingCurve.OutCirc, fScale, 1.0f, 0.2f, false );
		
		if( true == m_bMyTurn )
		{
			m_aniGroupSeq.add( Animation.type_alpha, pCard, 0, EasingCurve.OutCirc, 0.5f, 1.0f, 0.2f, false );
		}

		m_aniGroupSeq.start();
	}

	public void sendComCardToBoard( int nIndex )
	{
		m_miniCard[nIndex].setVisible( false );

		m_curEatCard.clear();
		m_nEatNum = 0;

		// 낼 카드
		Card pComCard = getCard( m_player[player_com].getPlayCard(nIndex) );

		m_eliList.setTopOrder( pComCard.getID() );

		pComCard.setFront();
		pComCard.setVisible( true );

		// 조카면 하나 더 뒤집어.. 내고 받아..
		findSameCard( pComCard );

		m_nSendID = -1;

		if( m_findInfo.nSameCount == 0 )
		{			
			sendCardAni( pComCard, Animation.id_send_comcard_to_board );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pComCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pComCard.getNumber();

			m_nSendID = pComCard.getID();

			m_player[player_com].removePlayCard( nIndex );	
			
			m_nBoardCardCount += 1;

		}
		else if( m_findInfo.nSameCount == 1 )
		{			
			sendCardAni( pComCard, Animation.id_send_comcard_to_board );

			m_nEatNum = pComCard.getNumber();

			m_curEatCard.add( pComCard.getID() );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][0].nID );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pComCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pComCard.getNumber();

			m_player[player_com].removePlayCard( nIndex );
			
			m_nBoardCardCount -= 1;

		}
		else if( m_findInfo.nSameCount == 2 )
		{			
			sendCardAni( pComCard, Animation.id_send_comcard_to_board );

			m_nEatNum = pComCard.getNumber();

			m_curEatCard.add( pComCard.getID() );			
			
			int nSel = (int)(Math.random() * 2);

			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][nSel].nID );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pComCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pComCard.getNumber();

			m_player[player_com].removePlayCard( nIndex );
			
			m_nBoardCardCount -= 1;

		}
		else if( m_findInfo.nSameCount >= 3 )
		{
			// 다먹음			
			sendCardAni( pComCard, Animation.id_send_comcard_to_board );

			m_nEatNum = pComCard.getNumber();

			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][0].nID );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][1].nID );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][2].nID );
			m_curEatCard.add( pComCard.getID() );
			
			m_bEatBbuck = true;		
			m_nAddCount++;

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pComCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pComCard.getNumber();

			m_player[player_com].removePlayCard( nIndex );
			
			m_nBoardCardCount -= 3;

		}	
	}

	public void sendMyCardToBoard( int nIndex )	// 카트 선택이후 들어옴
	{
		setTouchMyCard( false );
		
		hideTurnMark();


		m_curEatCard.clear();
		m_nEatNum = 0;

		// 낼 카드
		Card pMyCard = getCard( m_player[player_me].getPlayCard(nIndex) );

		m_eliList.setTopOrder( pMyCard.getID() );

		// 조카면 하나 더 뒤집어.. 내고 받아..

		findSameCard( pMyCard );

		m_nSendID = -1;

		if( m_findInfo.nSameCount == 0 )
		{			
			sendCardAni( pMyCard, Animation.id_send_mycard_to_board );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pMyCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pMyCard.getNumber();

			m_nSendID = pMyCard.getID();

			m_player[player_me].removePlayCard( nIndex );
			
			m_nBoardCardCount += 1;

		}
		else if( m_findInfo.nSameCount == 1 )
		{		
			sendCardAni( pMyCard, Animation.id_send_mycard_to_board );

			m_nEatNum = pMyCard.getNumber();

			m_curEatCard.add( pMyCard.getID() );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][0].nID );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pMyCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pMyCard.getNumber();

			m_player[player_me].removePlayCard( nIndex );
			
			m_nBoardCardCount -= 1;

		}
		else if( m_findInfo.nSameCount == 2 )
		{
			m_nMyCardIndex = nIndex;

			if( true == isSameOnePi( m_boardCard[m_findInfo.nDestPosX][0].nID, m_boardCard[m_findInfo.nDestPosX][1].nID ) )
			{
				m_nSelectedState = selected_state_first_eat;
				onCmdSelectCard( Element.id_select_card2 );
			}
			else
			{
				// 고르는거 보여주기			
				showSelectedPe( selected_state_first_eat, m_boardCard[m_findInfo.nDestPosX][0].nID, m_boardCard[m_findInfo.nDestPosX][1].nID );		
			}	
		}
		else if( m_findInfo.nSameCount >= 3 )
		{			
			sendCardAni( pMyCard, Animation.id_send_mycard_to_board );

			m_nEatNum = pMyCard.getNumber();

			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][0].nID );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][1].nID );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][2].nID );
			m_curEatCard.add( pMyCard.getID() );
			
			m_bEatBbuck = true;
			m_nAddCount++;	

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pMyCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pMyCard.getNumber();

			m_player[player_me].removePlayCard( nIndex );
		}	
	}
	
	public boolean isSameOnePi( int nID1, int nID2 )
	{
		if( (getCard(nID1).getType() == Card.type_one_pi) && (getCard(nID2).getType() == Card.type_one_pi) )
		{
			return true;
		}
		
		return false;
	}

	public void onCmdSelectCard( int nID )
	{
		int nSel = 0;
		if( nID == Element.id_select_card2 )
			nSel = 1;

		m_message_box_sel_card.hide();

		if( m_nSelectedState == selected_state_first_eat )
		{
			Card pMyCard = getCard( m_player[player_me].getPlayCard(m_nMyCardIndex) );
			
			sendCardAni( pMyCard, Animation.id_send_mycard_to_board );

			m_nEatNum = pMyCard.getNumber();

			m_curEatCard.add( pMyCard.getID() );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][nSel].nID );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pMyCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pMyCard.getNumber();

			m_player[player_me].removePlayCard( m_nMyCardIndex );
			
			m_nBoardCardCount -= 1;
			return;
		}

		if( m_nSelectedState == selected_state_second_eat )
		{
			// 뒤집은 카드
			int nLastIndex = (int) m_gameCard.size() - 1;
			Card pBackCard = m_gameCard.get(nLastIndex);		
			
			sendCardAni( pBackCard, Animation.id_backcard_to_board );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

			m_curEatCard.add( pBackCard.getID() );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][nSel].nID );
			
			m_gameCard.remove( nLastIndex );
			m_nBoardCardCount -= 1;
		}
	}

	public void sendBackCardToBoard()
	{
		// 뒤집은 카드
		int nLastIndex = (int) m_gameCard.size() - 1;
		if( nLastIndex < 0 )
			return;

		Card pBackCard = m_gameCard.get(nLastIndex);

		m_eliList.setTopOrder( pBackCard.getID() );

		pBackCard.setFront();

		// 쪼카면.. 받아서 뒤집어

		findSameCard( pBackCard );	

		if( m_findInfo.nSameCount == 0 )
		{			
			sendCardAni( pBackCard, Animation.id_backcard_to_board );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

			m_gameCard.remove( nLastIndex );
			m_nBoardCardCount += 1;

		}
		else if( m_findInfo.nSameCount == 1 )
		{			
			sendCardAni( pBackCard, Animation.id_backcard_to_board );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

			m_curEatCard.add( pBackCard.getID() );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][0].nID );	

			if( m_nSendID != -1 )
			{
				if( pBackCard.getNumber() == getCard(m_nSendID).getNumber() )
				{
					showJJock();
					m_nAddCount++;			

				}
			}

			m_gameCard.remove( nLastIndex );
			m_nBoardCardCount -= 1;

		}
		else if( m_findInfo.nSameCount == 2 )
		{
			int nNumber = getCard( m_boardCard[m_findInfo.nDestPosX][0].nID ).getNumber();

			// 아까 먹은거면 뻑
			if( ((int)m_curEatCard.size() == 2) &&  (nNumber == m_nEatNum) )
			{
				int nTurn = player_com;
				if( m_bMyTurn == true )
				{
					nTurn = player_me;
				}

				if( true == m_player[nTurn].remainPlayCard() )
				{
					sendCardAni( pBackCard, Animation.id_backcard_to_board );

					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

					m_curEatCard.clear();

					showBBuck();

					m_gameCard.remove( nLastIndex );

					m_nBoardCardCount += 3;
				}
				// 마지막 뻑
				else
				{
					int nEmptyIndex = 0;
					for( int i=0; i<max_board_card; i++ )
					{
						if( m_boardCard[i][0].bExist == false )
						{
							nEmptyIndex = i;
							break;
						}
					}				

					m_findInfo.nDestPosX = nEmptyIndex;
					m_findInfo.nDestPosY = 0;

					sendCardAni( pBackCard, Animation.id_backcard_to_board );

					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

					m_gameCard.remove( nLastIndex );
					m_nBoardCardCount += 1;
				}			

			}
			else
			{
				// 고르는거 보여주기
				if( true == m_bMyTurn )
				{
					if( true == isSameOnePi( m_boardCard[m_findInfo.nDestPosX][0].nID, m_boardCard[m_findInfo.nDestPosX][1].nID ) )
					{
						m_nSelectedState = selected_state_second_eat;
						onCmdSelectCard( Element.id_select_card2 );
					}
					else
					{
						// 고르는거 보여주기			
						showSelectedPe( selected_state_second_eat, m_boardCard[m_findInfo.nDestPosX][0].nID, m_boardCard[m_findInfo.nDestPosX][1].nID );
					}				

				}
				// 상대방이면 랜덤으로 고르기
				else
				{					
					sendCardAni( pBackCard, Animation.id_backcard_to_board );

					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

					m_curEatCard.add( pBackCard.getID() );
					
					int nSel = (int) (Math.random() * 2 );

					m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][nSel].nID );

					m_gameCard.remove( nLastIndex );
					
					m_nBoardCardCount -= 1;

				}
			}
		}
		else if( m_findInfo.nSameCount >= 3 )
		{
			// 다먹음			
			sendCardAni( pBackCard, Animation.id_backcard_to_board );

			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].bExist = true;
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nID = pBackCard.getID();
			m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nNumber = pBackCard.getNumber();

			m_curEatCard.add( pBackCard.getID() );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][0].nID );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][1].nID );
			m_curEatCard.add( m_boardCard[m_findInfo.nDestPosX][2].nID );
			
			int nNumber = getCard( m_boardCard[m_findInfo.nDestPosX][0].nID ).getNumber();

			// 아까 먹은거면 따닥
			if( nNumber == m_nEatNum )
			{
				m_effectMan.showDdadac();		
				m_nBoardCardCount -= 1;
				m_nAddCount++;
			}
			else
			{
				m_nBoardCardCount -= 3;
				m_nAddCount++;
			}

			m_bEatBbuck = true;
			m_gameCard.remove( nLastIndex );
		}
		
		if( m_nBoardCardCount == 0 )
		{
			int nTurn = player_com;
			if( m_bMyTurn == true )
			{
				nTurn = player_me;
			}

			if( true == m_player[nTurn].remainPlayCard() )
			{
				m_effectMan.showSsul();
				m_nAddCount++;
			}		
		}
	}

	public int canEat( Card pCard )
	{
		int nRet = -1;

		for( int i=0; i<max_board_card; i++ )
		{
			if( m_boardCard[i][0].bExist == false )
				continue;

			if( getCard(m_boardCard[i][0].nID).getNumber() == pCard.getNumber() )
			{
				nRet = i;
				break;
			}
		}

		return nRet;
	}

	public void findSameCard( Card pCard )
	{
		clearFindInfo();

		boolean bFind = false;
		int nX = 0;
		int nY = 0;
		
		for( int i=0; i<max_board_card; i++ )
		{
			for( int j=3; j>=0; j-- )
			{
				if( m_boardCard[i][j].bExist == false )
					continue;

				if( getCard(m_boardCard[i][j].nID).getNumber() == pCard.getNumber() )
				{
					nX = i;
					nY = j;
					bFind = true;
					break;
				}
			}
		}

		int nDestPosX = 0;
		int nDestPosY = 0;

		m_findInfo.bFind = bFind;

		if( false == bFind )
		{
			int nEmptyIndex = 0;
			for( int i=0; i<max_board_card; i++ )
			{
				if( m_boardCard[i][0].bExist == false )
				{
					nEmptyIndex = i;
					break;
				}
			}

			nDestPosX = nEmptyIndex;
			nDestPosY = 0;

			m_findInfo.nDestPosX = nDestPosX;
			m_findInfo.nDestPosY = nDestPosY;		
		}
		else
		{
			// 단짝
			if( 0 == nY )
			{
				m_findInfo.nSameCount = 1;
			}
			// 2장
			else if( 1 == nY )
			{
				// 선택 해야해..
				m_findInfo.nSameCount = 2;
			}
			// 3장 이상
			else
			{
				m_findInfo.nSameCount = nY + 1;
			}

			nDestPosX = nX;
			nDestPosY = nY + 1;

			m_findInfo.nDestPosX = nDestPosX;
			m_findInfo.nDestPosY = nDestPosY;
		}
	}

	public void showSelectedPe( int nState, int nFirstID, int nSecondID )
	{
		// 보여주고	
		m_nSelectedState = nState;

		m_message_box_sel_card.setFirstCard( getCard(nFirstID) );
		m_message_box_sel_card.setSecondCard( getCard(nSecondID) );

		m_message_box_sel_card.show();	
	}

	public void game_ready()
	{
		m_state = game_state_ready;

		m_game_bg.setVisible( true );

		for( int i=0; i<Card.MAX_CARD; i++ )
		{
			m_card[i].setBack();
			m_card[i].setTouch( false );
			m_card[i].setPos( m_ptCenter.nX, m_ptCenter.nY );
			m_card[i].setVisible( true );
		}

		m_btnStart.setVisible( true );

		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{		
			m_miniCard[i].setVisible( false );
		}

		m_nSelectedState = selected_state_not;	

		m_player[player_com].initVariable();
		m_player[player_me].initVariable();
		
		m_nBoardCardCount = 0;
		m_nAddCount = 0;

		m_bMyTurn = false;		
		
		m_bEatBbuck = false;
		
		showSunMark( m_whoSun );
	}

	public void game_start()
	{
		shuffleCard();		
		
		for( int i=0; i<max_player; i++ )
		{
			m_player[i].clearPlayCard();
		}
		
		m_nSendBoardCard = 0;
		if( m_whoSun == player_me )
		{
			m_cmd.registCommand( GameCommand.id_send_first_card_to_board, 0.0f );
			m_cmd.registCommand( GameCommand.id_send_first_card_to_com, 0.5f );
			m_cmd.registCommand( GameCommand.id_send_first_card_to_me, 1.0f );

			m_cmd.registCommand( GameCommand.id_send_second_card_to_board, 1.5f );
			m_cmd.registCommand( GameCommand.id_send_second_card_to_com, 2.0f );	
			m_cmd.registCommand( GameCommand.id_send_second_card_to_me, 2.5f );
		}
		else
		{
			m_cmd.registCommand( GameCommand.id_send_first_card_to_board, 0.0f );
			m_cmd.registCommand( GameCommand.id_send_first_card_to_me, 0.5f );
			m_cmd.registCommand( GameCommand.id_send_first_card_to_com, 1.0f );		

			m_cmd.registCommand( GameCommand.id_send_second_card_to_board, 1.5f );
			m_cmd.registCommand( GameCommand.id_send_second_card_to_me, 2.0f );
			m_cmd.registCommand( GameCommand.id_send_second_card_to_com, 2.5f );
		}

		m_cmd.registCommand( GameCommand.id_game_run, 3.5f );	

//		m_whoSun

	}

	public void game_run()
	{
		if( m_whoSun == player_me )
		{
			m_bMyTurn = true;
			myTurn();
		}
		else
		{
			comTurn();
		}

	}

	public void game_end()
	{

	}

	public void shuffleCard()
	{
		m_originCard.clear();
		m_gameCard.clear();

		for( int i=0; i<Card.MAX_CARD; i++ )
		{
			Card tmpCard = m_card[i];
			m_originCard.add( tmpCard );
		}
		
		for( int i=0; i<100; i++ )
		{
			int nSize = (int) m_originCard.size();
			if( nSize == 0 )
				break;
			
			int nGetIndex = (int)( Math.random() * nSize );
			Card tmpCard = m_originCard.get(nGetIndex);
			m_gameCard.add( tmpCard );
			m_originCard.remove( nGetIndex );
		}		
		
	//	testShuffle();

		for( int i=0; i<max_board_card; i++ )
		{
			for( int j=0; j<4; j++ )
				clearBoardCard( i, j );
		}
	}
	
	// 카드 조작
	void testShuffle()
	{
		// 4장 보드카드1
		addGameCard( Element.id_card_sol_kwang );
		addGameCard( Element.id_card_sol_hong );
		addGameCard( Element.id_card_sol_one_pi1 );
		addGameCard( Element.id_card_2_sae );

		// 5장 상대방 카드1
		addGameCard( Element.id_card_2_hong );
		addGameCard( Element.id_card_3_hong );
		addGameCard( Element.id_card_cho_ddi );
		addGameCard( Element.id_card_cho_one_pi1 );
		addGameCard( Element.id_card_moc_one_pi1 );

		// 5장 내 카드1
		addGameCard( Element.id_card_bi_kwang );
		addGameCard( Element.id_card_bi_ddi );
		addGameCard( Element.id_card_bi_mung );
		addGameCard( Element.id_card_ddong_kwang );
		addGameCard( Element.id_card_ddong_two_pi );

		// 4장 보드 카드2
		addGameCard( Element.id_card_10_mung );
		addGameCard( Element.id_card_10_one_pi1 );
		addGameCard( Element.id_card_bi_two_pi );
		addGameCard( Element.id_card_ddong_one_pi2 );

		// 5장 상대방 카드2
		addGameCard( Element.id_card_pig_mung );
		addGameCard( Element.id_card_pig_one_pi2 );
		addGameCard( Element.id_card_9_chung );
		addGameCard( Element.id_card_black_one_pi1 );
		addGameCard( Element.id_card_black_one_pi2 );

		// 5장 내카드2
		addGameCard( Element.id_card_ddong_one_pi1 );
		addGameCard( Element.id_card_9_one_pi2 );
		addGameCard( Element.id_card_9_chung );
		addGameCard( Element.id_card_8_one_pi1 );
		addGameCard( Element.id_card_8_one_pi2 );

		// 나머지 카드
		for( int i=0; i<(int)m_originCard.size(); i++ )
		{
			Card tmpCard = m_originCard.get(i);
			m_gameCard.add( tmpCard );		
		}
		
		m_originCard.clear();
		for( int i=(int)m_gameCard.size()-1; i>=0; i-- )
		{
			Card tmpCard = m_gameCard.get(i);
			m_originCard.add( tmpCard );		
		}

		m_gameCard.clear();
		for( int i=0; i<(int)m_originCard.size(); i++ )
		{
			Card tmpCard = m_originCard.get(i);
			m_gameCard.add( tmpCard );		
		}
	}

	void addGameCard( int id )
	{
		for( int i=0; i<(int)m_originCard.size(); i++ )
		{
			if( id == m_originCard.get(i).getID() )
			{
				Card tmpCard = m_originCard.get(i);
				m_gameCard.add( tmpCard );
				m_originCard.remove( i );
				break;
			}		
		}
	}

	public void sortBoardCard()
	{
		for( int i=0; i<max_board_card-1; i++ )
		{
			if( m_boardCard[i][0].bExist == false )
				continue;

			for( int j=i+1; j<max_board_card; j++ )
			{
				if( m_boardCard[i][0].nNumber == m_boardCard[j][0].nNumber )
				{
					Card pCard = getCard( m_boardCard[j][0].nID );

					// 빈곳을 찾아
					for( int k=1; k<4; k++ )
					{
						if( m_boardCard[i][k].bExist == false )
						{
							// 거기로 이동시킴
							updateBoardCard( i, k, pCard );

							clearBoardCard( j, 0 );
							break;
						}
					}
				}
			}
		}
	}

	public void clearBoardCard( int i, int j )
	{
		m_boardCard[i][j].nID = -1;
		m_boardCard[i][j].bExist = false;
		m_boardCard[i][j].nNumber = 0;
	}

	public void clearBoardCard( int nID )
	{
		for( int i=0; i<max_board_card; i++ )
		{
			for( int j=0; j<4; j++ )
			{
				if( m_boardCard[i][j].nID == nID )
					clearBoardCard( i, j );
			}
		}
	}

	public void updateBoardCard( int i, int j, int nID, int nNumber )
	{
		m_boardCard[i][j].nID = nID;
		m_boardCard[i][j].bExist = true;
		m_boardCard[i][j].nNumber = nNumber;
	}

	public void updateBoardCard( int i, int j, Card pCard )
	{
		m_boardCard[i][j].nID = pCard.getID();
		m_boardCard[i][j].bExist = true;
		m_boardCard[i][j].nNumber = pCard.getNumber();

		pCard.setPos( m_boardCard[i][j].nPosX, m_boardCard[i][j].nPosY );
	}

	public void clearFindInfo()
	{
		m_findInfo.bFind = false;
		m_findInfo.nSameCount = 0;
		m_findInfo.nDestPosX = 0;
		m_findInfo.nDestPosY = 0;
	}

	public void showChungDan()
	{
		m_effectMan.showChungDan();	
	}

	public void showHongDan()
	{
		m_effectMan.showHongDan();	
	}

	public void showChoDan()
	{
		m_effectMan.showChoDan();	
	}

	public void showGodori()
	{
		m_effectMan.showGodori();	
	}

	public void showJJock()
	{
		m_effectMan.showJJock();	
	}

	public void showBBuck()
	{
		m_effectMan.showBBuck();	
	}
	
	void showFiveKwang()
	{

	}


	public void showGo()
	{
		int nGo = 0;
		if( true == m_bMyTurn )
		{
			m_player[player_me].updateGo();
			nGo = m_player[player_me].getGo();
		}
		else
		{
			m_player[player_com].updateGo();
			nGo = m_player[player_com].getGo();
		}

		m_effectMan.showGo( nGo );
	}

	public void showStop()
	{
		m_effectMan.showStop();

		if( true == m_bMyTurn )
		{
			// 내가  이김
			m_whoSun = player_me;

			m_message_box_result.setWin();
		}
		else
		{
			// 상대방이 이김
			m_whoSun = player_com;

			m_message_box_result.setLose();
		}
	}

	public void endTurn()
	{	
		// 먹은거 자리로 가져오기
		int nEatCount = (int)m_curEatCard.size();
		if( nEatCount > 0 )
		{
			for( int i=0; i<nEatCount; i++ )
			{
				Card pCard = getCard( m_curEatCard.get(i) );
				
				if( true == m_bMyTurn )
					m_player[player_me].addCard( pCard );
				else
					m_player[player_com].addCard( pCard );

				clearBoardCard( m_curEatCard.get(i) );
			}
		}

		m_curEatCard.clear();
		
		// 두번째꺼 먹은 카드 정렬
		for( int i=0; i<max_board_card; i++ )
		{
			if( (m_boardCard[i][0].bExist == false) && (m_boardCard[i][1].bExist == true) )
			{
				// 두번째를 첫번째로 이동
				Card pCard = getCard( m_boardCard[i][1].nID );
				updateBoardCard( i, 0, pCard );

				// 정보 갱신
				clearBoardCard( i, 1 );
			}
		}

		// 카드 정산.. 
//		dummyAnimation( Animation.id_dummy_new_turn, 1.0f );
		dummyAnimation( Animation.id_dummy_calc_card, 1.0f );

	}
	
	void calcCard()
	{
		// 카드 정산.. 
		if( m_nAddCount > 0 )
		{
			int otherMan = player_me;
			if( true == m_bMyTurn )
			{
				otherMan = player_com;
			}

			for( int i=0; i<m_nAddCount; i++ )
			{
				if( true == m_player[otherMan].havePi() )
				{

				//	m_curEatCard.push_back( m_player[otherMan].getPi()->getID() );

					if( true == m_bMyTurn )
						m_player[player_me].addCard( m_player[player_com].getPi() );
					else
						m_player[player_com].addCard( m_player[player_me].getPi() );

					m_player[otherMan].deletePi();
				}
			}

			m_nAddCount = 0;
			dummyAnimation( Animation.id_dummy_new_turn, 1.0f );
		}
		else
		{
			dummyAnimation( Animation.id_dummy_new_turn, 0.1f );
		}	
	}

	void showSunMark( int pos )
	{
		m_sunMark.setVisible( true );
		m_sunMark.setPos( m_sunPos[pos].nX, m_sunPos[pos].nY );
	}

	void hideSunMark()
	{
		m_sunMark.setVisible( false );
	}

	void showTurnMark( int pos )
	{
		m_turnMark.setVisible( true );
		m_turnMark.setPos( m_turnPos[pos].nX, m_turnPos[pos].nY );
	}

	void hideTurnMark()
	{
		m_turnMark.setVisible( false );
	}

	void showCardHint( int pos )
	{

	}

	void showChongTong( int nNum )
	{

	}

	void hideChongTong()
	{

	}

	void hideCardHint()
	{

	}


	public void showSelectGoStop()
	{
		m_message_box_gostop.show();
	}

	public void newTurn()
	{
		// 낫는지 체크..
		if( true == m_bMyTurn )
		{
			if( true == m_player[player_me].canGo() )
			{
				if( true == m_player[player_me].remainPlayCard() )
				{
					showSelectGoStop();
				}
				else
				{
					showStop();
				}
				return;
			}
			
			changeTurn();		
		}
		else
		{
			if( true == m_player[player_com].canGo() )
			{
				comSelectGoStop();
			}
			else
			{
				changeTurn();
			}
		}
	}

	public void comSelectGoStop()
	{
		if( false == m_player[player_com].remainPlayCard() )
		{
			showStop();
			return;
		}
		
		if( m_player[player_me].getGo() > 0 )
		{
			showStop();
			return;
		}

		
		int nSel = (int)( Math.random() * 2 );

		if( nSel == 0 )
		{
			showGo();
		}
		else
		{
			showStop();
		}
	}

	public void changeTurn()
	{
		if( true == m_bMyTurn )
		{
			m_bMyTurn = false;
			comTurn();
		}
		else
		{
			m_bMyTurn = true;
			myTurn();
		}
	}

	public void showShake()
	{

	}

	public void actBomb()
	{

	}

	public void myTurn()
	{
		showTurnMark( player_me );
		setTouchMyCard( true );
	}

	public void dummyAnimation( int nID, float fSec )
	{
		m_dummy.setMoveAni( nID, EasingCurve.Linear, 0, 0, 100, 100, fSec );
	}

	public void setTouchMyCard( boolean bSet )
	{
		for( int i=0; i<GamePlayer.MAX_PLAY_CARD; i++ )
		{
			if( false == m_player[player_me].existPlayCard(i) )
				continue;		

			Card pCard = getCard( m_player[player_me].getPlayCard(i) );
			pCard.setTouch( bSet );
		}
	}
	
	void showCardEffect()
	{
		if( m_bEatBbuck == true )
		{
			m_effectMan.showEatBBuckCard( m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nPosX,
				m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nPosY );
		}
		else
		{
			if( m_findInfo.nDestPosY > 0 )
			{
				m_effectMan.showCardImpact( m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nPosX,
					m_boardCard[m_findInfo.nDestPosX][m_findInfo.nDestPosY].nPosY );
			}
		}
		m_bEatBbuck = false;
	}

	public void makeCardInfomation()
	{
		// 솔
		makeCardInfo( 0, Element.id_card_sol_kwang, Card.num_1, Card.type_kwnag, 4 );
		makeCardInfo( 1, Element.id_card_sol_hong, Card.num_1, Card.type_ddi_hong, 5 );
		makeCardInfo( 2, Element.id_card_sol_one_pi1, Card.num_1, Card.type_one_pi, 6 );
		makeCardInfo( 3, Element.id_card_sol_one_pi2, Card.num_1, Card.type_one_pi, 7 );
		
		makeCardInfo( 4, Element.id_card_2_sae, Card.num_2, Card.type_mung_sae, 8 );
		makeCardInfo( 5, Element.id_card_2_hong, Card.num_2, Card.type_ddi_hong, 9 );
		makeCardInfo( 6, Element.id_card_2_one_pi1, Card.num_2, Card.type_one_pi, 10 );
		makeCardInfo( 7, Element.id_card_2_one_pi2, Card.num_2, Card.type_one_pi, 11 );

		makeCardInfo( 8, Element.id_card_3_kwang, Card.num_3, Card.type_kwnag, 12 );
		makeCardInfo( 9, Element.id_card_3_hong, Card.num_3, Card.type_ddi_hong, 13 );
		makeCardInfo( 10, Element.id_card_3_one_pi1, Card.num_3, Card.type_one_pi, 14 );
		makeCardInfo( 11, Element.id_card_3_one_pi2, Card.num_3, Card.type_one_pi, 15 );	
		
		makeCardInfo( 12, Element.id_card_black_sae, Card.num_4, Card.type_mung_sae, 16 );
		makeCardInfo( 13, Element.id_card_black_ddi, Card.num_4, Card.type_ddi_cho, 17 );
		makeCardInfo( 14, Element.id_card_black_one_pi1, Card.num_4, Card.type_one_pi, 18 );
		makeCardInfo( 15, Element.id_card_black_one_pi2, Card.num_4, Card.type_one_pi, 19 );

		makeCardInfo( 16, Element.id_card_cho_mung, Card.num_5, Card.type_mung_normal, 20 );
		makeCardInfo( 17, Element.id_card_cho_ddi, Card.num_5, Card.type_ddi_cho, 21 );
		makeCardInfo( 18, Element.id_card_cho_one_pi1, Card.num_5, Card.type_one_pi, 22 );
		makeCardInfo( 19, Element.id_card_cho_one_pi2, Card.num_5, Card.type_one_pi, 23 );

		makeCardInfo( 20, Element.id_card_moc_mung, Card.num_6, Card.type_mung_normal, 24 );
		makeCardInfo( 21, Element.id_card_moc_chung, Card.num_6, Card.type_ddi_chung, 25 );
		makeCardInfo( 22, Element.id_card_moc_one_pi1, Card.num_6, Card.type_one_pi, 26 );
		makeCardInfo( 23, Element.id_card_moc_one_pi2, Card.num_6, Card.type_one_pi, 27 );

		makeCardInfo( 24, Element.id_card_pig_mung, Card.num_7, Card.type_mung_pig, 28 );
		makeCardInfo( 25, Element.id_card_pig_ddi, Card.num_7, Card.type_ddi_cho, 29 );
		makeCardInfo( 26, Element.id_card_pig_one_pi1, Card.num_7, Card.type_one_pi, 30 );
		makeCardInfo( 27, Element.id_card_pig_one_pi2, Card.num_7, Card.type_one_pi, 31 );

		makeCardInfo( 28, Element.id_card_8_kwang, Card.num_8, Card.type_kwnag, 32 );
		makeCardInfo( 29, Element.id_card_8_sae, Card.num_8, Card.type_mung_sae, 33 );
		makeCardInfo( 30, Element.id_card_8_one_pi1, Card.num_8, Card.type_one_pi, 34 );
		makeCardInfo( 31, Element.id_card_8_one_pi2, Card.num_8, Card.type_one_pi, 35 );

//		makeCardInfo( 32, Element.id_card_9_two_pi, Card.num_9, Card.type_pimung, 36 );
		makeCardInfo( 32, Element.id_card_9_two_pi, Card.num_9, Card.type_two_pi, 36 );
		makeCardInfo( 33, Element.id_card_9_chung, Card.num_9, Card.type_ddi_chung, 37 );
		makeCardInfo( 34, Element.id_card_9_one_pi1, Card.num_9, Card.type_one_pi, 38 );
		makeCardInfo( 35, Element.id_card_9_one_pi2, Card.num_9, Card.type_one_pi, 39 );

		makeCardInfo( 36, Element.id_card_10_mung, Card.num_10, Card.type_mung_normal, 40 );
		makeCardInfo( 37, Element.id_card_10_chung, Card.num_10, Card.type_ddi_chung, 41 );
		makeCardInfo( 38, Element.id_card_10_one_pi1, Card.num_10, Card.type_one_pi, 42 );
		makeCardInfo( 39, Element.id_card_10_one_pi2, Card.num_10, Card.type_one_pi, 43 );

		makeCardInfo( 40, Element.id_card_ddong_kwang, Card.num_11, Card.type_kwnag, 44 );
		makeCardInfo( 41, Element.id_card_ddong_two_pi, Card.num_11, Card.type_two_pi, 45 );
		makeCardInfo( 42, Element.id_card_ddong_one_pi1, Card.num_11, Card.type_one_pi, 46 );
		makeCardInfo( 43, Element.id_card_ddong_one_pi2, Card.num_11, Card.type_one_pi, 47 );

		makeCardInfo( 44, Element.id_card_bi_kwang, Card.num_12, Card.type_kwnag_bi, 48 );
		makeCardInfo( 45, Element.id_card_bi_mung, Card.num_12, Card.type_mung_normal, 49 );
		makeCardInfo( 46, Element.id_card_bi_ddi, Card.num_12, Card.type_ddi_bi, 50 );
		makeCardInfo( 47, Element.id_card_bi_two_pi, Card.num_12, Card.type_two_pi, 51 );
	}	
}


