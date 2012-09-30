package com.sgsong.main;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import sod.common.NetworkUtils;

import com.sgsong.Draw.SGIMG;
import com.sgsong.Game.GameMain;
import com.sgsong.Infomation.FPS;
import com.sgsong.Net.ConnectionBean;
import com.sgsong.Net.Networking;
import com.sgsong.Net.WifiSubService;
import com.sgsong.Struct.Def;
import com.sgsong.Struct.ImageInfo;
import com.sgsong.Sound.GameSound;
import com.sgsong.GameCommand.GameCommand;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import android.media.SoundPool;
import android.media.AudioManager;

public class MainApp extends Activity implements Renderer
{	
	private GL10 mGL;
	
	private GameMain m_gMain;	
	
	private FPS		m_fps;
	
	private long mLastTime = 0;
	
	private int[] texture = new int[Def.MAX_TEX_SIZE];
	private int[] m_nTexWidth = new int[Def.MAX_TEX_SIZE];
	private int[] m_nTexHeight = new int[Def.MAX_TEX_SIZE];
	
	private ImageInfo[] m_imgInfo = new ImageInfo[Def.MAX_TEX_SIZE];
	
	
	/** The Activity Context ( NEW ) */
	private Context context;	 
	
	private SoundPool sound_pool;

	private int[] game_sound = new int[GameSound.MAX_SIZE];
	
	private int m_nCurTex = 0;
	static int connection_count=0;
	/**
	 * Instance the Cube object and set 
	 * the Activity Context handed over
	 */
	public MainApp( Context context )
	{
		this.context = context;	
	}

	/**
	 * The Surface is created/init()
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		mGL = gl;		
		
		m_fps = new FPS();
		
		m_gMain = new GameMain( gl );		
		m_gMain.InitMe( this );
		
		Log.i( "[SGSG]", "m_gMain.InitMe();" );

		loadSounds();
		
	//	Log.i( "[SGSG]", "loadSounds();" );
		
		makeImageInfoAll();		
		
		loadImage( Def.game_font );		
		loadImage( Def.loading_bg );
		loadImage( Def.loading_bar );		
		m_nCurTex = 3;		
		
		if (connection_count == 0) {
			// 0. Run.java 에서 액티비티의 context 정보를 미리 셋팅했다. ( Run.java  64 Line)
			// 1. 자기 자신의 Ip를 알아와서 셋팅한다./////////////////
			NetworkUtils.setLocalIp(WifiSubService.getLocalIpAddress());
			new Networking().TVServerIni();
			// 2.멀티캐스트 락을 해제한다. (검색기능을 위해)
			WifiSubService.unLockMulticast();
			// 3. 서버 시작
			ConnectionBean.server.start(ConnectionBean.ServerConfig);
			connection_count++;
		}

		m_gMain.registLoading();		
		
	//	Log.i( "[SGSG]", "m_gMain.registElements();" );

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		
		// forceError();
	}

	
	
	
	public void loadSounds()
	{
		sound_pool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 0);
		
		loadSound( GameSound.card_send, R.raw.card_send );		
		loadSound( GameSound.card_hit, R.raw.card_hit );		
		
		loadSound( GameSound.bomb, R.raw.bomb );
		loadSound( GameSound.bbuck, R.raw.bbuck );
		
		loadSound( GameSound.dan, R.raw.dan );
		loadSound( GameSound.ddadac, R.raw.ddadac );
		loadSound( GameSound.godori, R.raw.godori );		
		loadSound( GameSound.jjock, R.raw.jjock );
		loadSound( GameSound.ssul, R.raw.ssul );
		
		loadSound( GameSound.go_one, R.raw.go_one );
		loadSound( GameSound.go_two, R.raw.go_two );
		loadSound( GameSound.go_three, R.raw.go_three );
		loadSound( GameSound.go_four, R.raw.go_four );
		loadSound( GameSound.go_five, R.raw.go_five );
		loadSound( GameSound.go_six, R.raw.go_six );
		
		loadSound( GameSound.stop, R.raw.stop );
	}
	
	public void loadSound( int nID, int nResID )
	{
		game_sound[nID] = sound_pool.load(this.context, nResID, 1);
	}	
	
	public boolean loadNextImaage()
	{
		if( m_nCurTex == Def.MAX_TEX_SIZE )
			return true;
		
		loadTex( m_nCurTex, m_imgInfo[m_nCurTex].nResID );
		addImageInfo( m_nCurTex, m_imgInfo[m_nCurTex].nImgStartX, m_imgInfo[m_nCurTex].nImgStartY,
				m_imgInfo[m_nCurTex].nImgWidth, m_imgInfo[m_nCurTex].nImgHeight );
		
		m_nCurTex++;
		
		return false;
	}
	
	public float getLoadPercent()
	{
		float fPercent = 0.0f;
		
		fPercent = (float)m_nCurTex / (float)(Def.MAX_TEX_SIZE - 1);
		
		return fPercent;
	}	
	
	public void loadImage( int nTexID )
	{		
		loadTex( nTexID, m_imgInfo[nTexID].nResID );
		addImageInfo( nTexID, m_imgInfo[nTexID].nImgStartX, m_imgInfo[nTexID].nImgStartY,
				m_imgInfo[nTexID].nImgWidth, m_imgInfo[nTexID].nImgHeight );
	}	
	
	public void makeImageInfoAll()
	{
		makeImageInfo( Def.game_font, R.drawable.game_font, 0, 0, 256, 256 );
		
		makeImageInfo( Def.loading_bg, R.drawable.loading_bg, 0, 0, 256, 157 );
		makeImageInfo( Def.loading_bar, R.drawable.loading_bar, 0, 0, 387, 20 );
		
		makeImageInfo( Def.btn_start, R.drawable.btn_start, 0, 0, 241, 156 );

		makeImageInfo( Def.game_bg, R.drawable.game_bg, 0, 0, 800, 480 );		
		makeImageInfo( Def.gostop_card, R.drawable.gostop_card, 0, 0, 148, 728 );
		makeImageInfo( Def.card_impact, R.drawable.card_impact, 0, 0, 368, 102 );
		makeImageInfo( Def.mini_card, R.drawable.mini_card, 0, 0, 16, 22 );
		
		makeImageInfo( Def.bbuck, R.drawable.bbuck, 0, 0, 700, 117 );
		makeImageInfo( Def.bbuck_one, R.drawable.bbuck_one, 0, 0, 804, 300 );
		makeImageInfo( Def.bbuck_two, R.drawable.bbuck_two, 0, 0, 792, 304 );
		makeImageInfo( Def.bbuck_three, R.drawable.bbuck_three, 0, 0, 260, 100 );
		
		makeImageInfo( Def.chodan, R.drawable.chodan, 0, 0, 990, 636 );
		makeImageInfo( Def.chungdan, R.drawable.chungdan, 0, 0, 990, 636 );
		makeImageInfo( Def.godori, R.drawable.godori, 0, 0, 646, 137 );
		makeImageInfo( Def.hongdan, R.drawable.hongdan, 0, 0, 1196, 670 );
		makeImageInfo( Def.jjock, R.drawable.jjock, 0, 0, 680, 310 );
		makeImageInfo( Def.ssul, R.drawable.ssul, 0, 0, 1530, 127 );
		makeImageInfo( Def.mung_guri, R.drawable.mung_guri, 0, 0, 384, 44 );
		
		makeImageInfo( Def.ddadack, R.drawable.ddadack, 0, 0, 464, 89 );
		makeImageInfo( Def.ddadack_one, R.drawable.ddadack_one, 0, 0, 860, 109 );		
		
		makeImageInfo( Def.go_one, R.drawable.go_one, 0, 0, 636, 176 );
		makeImageInfo( Def.go_two, R.drawable.go_two, 0, 0, 636, 176 );
		makeImageInfo( Def.go_three, R.drawable.go_three, 0, 0, 639, 176 );
		makeImageInfo( Def.go_four, R.drawable.go_four, 0, 0, 636, 176 );
		makeImageInfo( Def.go_five, R.drawable.go_five, 0, 0, 1105, 394 );
		makeImageInfo( Def.go_six, R.drawable.go_six, 0, 0, 1245, 402 );
		makeImageInfo( Def.stop, R.drawable.stop, 0, 0, 1386, 163 );
		
		makeImageInfo( Def.message_box_gostop, R.drawable.message_box_gostop, 0, 0, 223, 156 );
		makeImageInfo( Def.btn_go, R.drawable.btn_go, 0, 0, 96, 49 );
		makeImageInfo( Def.btn_stop, R.drawable.btn_stop, 0, 0, 96, 49 );
		
		makeImageInfo( Def.message_box_select_card, R.drawable.message_box_select_card, 0, 0, 195, 128 );
		makeImageInfo( Def.message_box_buttons, R.drawable.message_box_buttons, 0, 0, 78, 324 );
		makeImageInfo( Def.message_box_gookgin, R.drawable.message_box_gookgin, 0, 0, 195, 143 );
		makeImageInfo( Def.message_box_result, R.drawable.message_box_result, 0, 0, 294, 285 );		
		
		makeImageInfo( Def.mark_sun, R.drawable.mark_sun, 0, 0, 21, 20 );
		makeImageInfo( Def.mark_win_lose, R.drawable.mark_win_lose, 0, 0, 160, 80 );
		makeImageInfo( Def.hint_pe, R.drawable.hint_pe, 0, 0, 41, 60 );
		makeImageInfo( Def.turn_frame, R.drawable.turn_frame, 0, 0, 204, 102 );
		
		makeImageInfo( Def.number_big, R.drawable.number_big, 0, 0, 270, 25 );
		makeImageInfo( Def.number_blue, R.drawable.number_blue, 0, 0, 220, 21 );
		makeImageInfo( Def.number_dark_yellow, R.drawable.number_dark_yellow, 0, 0, 220, 21 );
		makeImageInfo( Def.number_red, R.drawable.number_red, 0, 0, 220, 21 );
		makeImageInfo( Def.number_yellow, R.drawable.number_yellow, 0, 0, 220, 21 );
		makeImageInfo( Def.comma_blue, R.drawable.comma_blue, 0, 0, 9, 11 );
		makeImageInfo( Def.comma_red, R.drawable.comma_red, 0, 0, 9, 11 );		
		
		makeImageInfo( Def.eat_bbuck, R.drawable.eat_bbuck, 0, 0, 770, 322 );
	}
	
	public void makeImageInfo( int nTexID, int nResID, int nImgStartX, int nImgStartY, int nImgWidth, int nImgHeight )
	{	
		m_imgInfo[nTexID] = new ImageInfo();		
		
		m_imgInfo[nTexID].nResID = nResID;
		
		m_imgInfo[nTexID].nImgStartX = nImgStartX;
		m_imgInfo[nTexID].nImgStartY = nImgStartY;
		
		m_imgInfo[nTexID].nImgWidth = nImgWidth;
		m_imgInfo[nTexID].nImgHeight = nImgHeight;
	}	
	
	public void addImageInfo( int nTexID, int nImgStartX, int nImgStartY, int nImgWidth, int nImgHeight )
	{		
		SGIMG imgTemp = new SGIMG();
		
		imgTemp.pImage = texture[nTexID];		
		
		imgTemp.nTexID = nTexID;
		imgTemp.nID = nTexID;
		imgTemp.nImgStartX = nImgStartX;
		imgTemp.nImgStartY = nImgStartY;

		imgTemp.nWidth = nImgWidth;
		imgTemp.nHeight = nImgHeight;
		imgTemp.nTexWidth = m_nTexWidth[nTexID];
		imgTemp.nTexHeight = m_nTexHeight[nTexID];

		m_gMain.getSG().setImg( nTexID, imgTemp, texture[nTexID] );

//		char sgLog[512];
//		sprintf( sgLog, "[SGSG] nID: %d, width: %d, height: %d", nImgID, tgaFile.width, tgaFile.height );
//		m_gMain.ShowLog( sgLog );
		
		String sgLog = String.format("addImageInfo - nTexID: %d, nImgID: %d, startX: %d, starY: %d, nImgWidth: %d, nImgHeight: %d, m_nTexWidth[nTexID]: %d, m_nTexHeight[nTexID]: %d",
				nTexID, nTexID,	nImgStartX, nImgStartY, nImgWidth, nImgHeight, m_nTexWidth[nTexID], m_nTexHeight[nTexID] );		
		Log.i("[SGSG]", sgLog );
	}
	
	public void loadTex(int nTexID, int nResID)
	{
		// Get the texture from the Android resource directory
		InputStream is = this.context.getResources().openRawResource(nResID);

		Bitmap bitmap = null;
		try {
			// BitmapFactory is an Android graphics utility for images
			bitmap = BitmapFactory.decodeStream(is);

		} finally {
			// Always clear and close
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}
		}

		// Generate one texture pointer...
		mGL.glGenTextures(1, texture, nTexID);

		// ...and bind it to our array
		mGL.glBindTexture(GL10.GL_TEXTURE_2D, texture[nTexID]);

		// Create Nearest Filtered Texture
		mGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_LINEAR);
		mGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		// Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		mGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_CLAMP_TO_EDGE);
		mGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_CLAMP_TO_EDGE);
				

		// Use the Android GLUtils to specify a two-dimensional texture
		// image from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		m_nTexWidth[nTexID] = bitmap.getWidth();
		m_nTexHeight[nTexID] = bitmap.getHeight();		

		// Clean up
		bitmap.recycle();
	}	
	
	
	public void onSingleTapUp( int nX, int nY )
	{
	//	Log.e( "[SGSG]", "onSingleTapUp" );
		
		m_gMain.onSingleTapUp( nX, nY );
	}
	
	public void onDoubleTap( int nX, int nY )
	{
	//	Log.e( "[SGSG]", "onDoubleTap" );
		
		m_gMain.onDoubleTap( nX, nY );
	}
	
	public void onLongClick( int nX, int nY )
	{
	//	Log.e( "[SGSG]", "onLongClick" );
		
		m_gMain.onLongClick( nX, nY );
	}
	
	public void onFling( int nStartX, int nStartY, int nEndX, int nEndY, float fVelocityX, float VelocityY )
	{
	//	Log.e( "[SGSG]", "onFling" );
		
		m_gMain.onFling( nStartX, nStartY, nEndX, nEndY, fVelocityX, VelocityY );
	}
	
	public void onTouchDown( int nX, int nY )
	{

		m_gMain.onTouchDown( nX, nY );
	}
	
	public void onTouchUp( int nX, int nY )
	{
		m_gMain.onTouchUp( nX, nY );
	}
	
	public void onTouchMove( int nX, int nY )
	{
		m_gMain.onTouchMove( nX, nY );
	}
	
	public void forceError()
	{
		if(true)
		{
			throw new Error( "Whoops" );
		}
	}

	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl)
	{		
		long thisTime = System.currentTimeMillis();
		
		mLastTime = thisTime;
			
		calcFPS();			
			
		mGL.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		mGL.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		mGL.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		long curTime = System.currentTimeMillis();					
			
		m_gMain.display( curTime );
		
		m_gMain.ShowFPS( m_fps.n100, m_fps.n100, m_fps.n10, m_fps.n1 );					

		// Disable the client state before leaving
		mGL.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		mGL.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);	
			
		mGL.glFlush();
			
		// after draw
		m_gMain.afterDraw();

		playSound();
			
		playCommand();
		
	}	
	
	public void calcFPS( )
	{
		if( mLastTime - m_fps.nCurTime  >= 1000 )
		{
			m_fps.nCurFPS = m_fps.nFPS;
			m_fps.nFPS = 0;
			m_fps.nCurTime  = mLastTime;

			m_fps.n1000 = m_fps.nCurFPS / 1000;

			m_fps.nCurFPS -= 1000 * m_fps.n1000;

			m_fps.n100 = m_fps.nCurFPS / 100;

			m_fps.nCurFPS -= 100 * m_fps.n100;

			m_fps.n10 = m_fps.nCurFPS / 10;

			m_fps.nCurFPS -= 10 * m_fps.n10;

			m_fps.n1 = m_fps.nCurFPS;		
		}
		else
		{
			m_fps.nFPS++;
		}
	}
	
	public void playCommand()
	{
		if( m_gMain.m_cmd.isRegistCommand() )
		{
			int nID = m_gMain.m_cmd.getID();

			if( nID == GameCommand.id_send_first_card_to_com )
			{
				m_gMain.sendFirstCardToCom();
			}
			else if( nID == GameCommand.id_send_second_card_to_com )
			{
				m_gMain.sendSecondCardToCom();
			}
			else if( nID == GameCommand.id_send_first_card_to_me )
			{
				m_gMain.sendFirstCardToMe();
			}
			else if( nID == GameCommand.id_send_second_card_to_me )
			{
				m_gMain.sendSecondCardToMe();
			}
			else if( nID == GameCommand.id_send_first_card_to_board )
			{
				m_gMain.sendFirstCardToBoard();
			}
			else if( nID == GameCommand.id_send_second_card_to_board )
			{
				m_gMain.sendSecondCardToBoard();
			}
			else if( nID == GameCommand.id_game_run )
			{
				m_gMain.game_run();
			}

		}
	}
	
	public void playSound()
	{
		if( m_gMain.m_sound.isRegistSound() )
		{
			int nID = m_gMain.m_sound.getID();
		
			sound_pool.play( game_sound[nID], 0.1f, 0.1f, 0, 0, 1f);
			
		//	String str = String.format( "[SGSG] playsound - nid: %d", nID );
		//	m_gMain.ShowLog( str );
		}			
	}

	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		Log.i( "[SGSG]", "width: " + width + " height: " +height );
		
		m_gMain.getSG().Setup(width, height);		
	}
}
