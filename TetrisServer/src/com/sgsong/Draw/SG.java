
package com.sgsong.Draw;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.sgsong.Game.GameMain;
import com.sgsong.Struct.Def;

public class SG
{
	private GL10 m_gl;
	
	private FloatBuffer vertexBuffer;
	private float[] m_bgVertices = new float[8];
	
	private FloatBuffer textureBuffer;
	private float bgTexCoords_new[] = new float[8];	
	
	private SGIMG[] m_img = new SGIMG[Def.MAX_TEX_SIZE];
	private int[] tex = new int[Def.MAX_TEX_SIZE];

	GameMain	m_pMain;
	
	private Def		m_def = new Def();	
	
	public SG( GL10 mGL )
	{		
		m_gl = mGL;
		
		for( int i=0; i<Def.MAX_TEX_SIZE; i++ )
		{
			m_img[i] = new SGIMG();
		}			

		m_pMain = null;
	}
	
	public void InitMe( GameMain pMain )
	{
		m_pMain = pMain;

		m_pMain.ShowLog( "[SGSG] SG::InitMe" );
	}
	
	public Def getDef()
	{
		return m_def;
	}	
	
	public void setImg( int nID, SGIMG img, int pImage )
	{
		m_img[nID] = img;
		
		tex[nID] = pImage;
	}

	public SGIMG getImg( int nID )
	{
		return m_img[nID];
	}	
	
	public void Setup( int nWidth, int nHeight )
	{		
		m_def.setWidth(nWidth);
		m_def.setHeight(nHeight);
		
		m_gl.glViewport(0, 0, m_def.MobileWidth, m_def.MobileHeight);

		m_gl.glShadeModel(GL10.GL_SMOOTH);	

		m_gl.glDisable(GL10.GL_CULL_FACE);
		m_gl.glDisable(GL10.GL_DEPTH_TEST);	

		m_gl.glMatrixMode(GL10.GL_PROJECTION);
		m_gl.glLoadIdentity();
/* resolution : 1440X900 은 
		m_gl.glOrthox( Def.FixedFromInt(-m_def.MobileWidth/2), Def.FixedFromInt(m_def.MobileWidth/8), 
				Def.FixedFromInt(-m_def.MobileHeight/6), Def.FixedFromInt(m_def.MobileHeight/2),
				Def.FixedFromInt(-1) , Def.FixedFromInt(1));
		*/
		m_gl.glOrthox( Def.FixedFromInt(-m_def.MobileWidth/2), Def.FixedFromInt(m_def.MobileWidth/8), 
				Def.FixedFromInt(-m_def.MobileHeight/6), Def.FixedFromInt(m_def.MobileHeight/2),
				Def.FixedFromInt(-1) , Def.FixedFromInt(1));

		m_gl.glMatrixMode(GL10.GL_MODELVIEW);

		m_gl.glEnable(GL10.GL_TEXTURE_2D);

		m_gl.glEnable(GL10.GL_DEPTH_TEST);
		m_gl.glDepthFunc(GL10.GL_LEQUAL);

		m_gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		m_gl.glClearDepthf(1.0f);

		m_gl.glEnable(GL10.GL_CULL_FACE);
		m_gl.glShadeModel(GL10.GL_SMOOTH);

		m_gl.glEnable(GL10.GL_BLEND);
		m_gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		m_gl.glAlphaFunc(GL10.GL_NOTEQUAL, 0);		

		m_pMain.ShowLog( "[SGSG] setup" );
	}

	public void setColor( SG_COLOR clr )
	{
		m_gl.glColor4f( clr.fRed, clr.fGreen, clr.fBlue, clr.fAlpha );
	}		

	public void DrawFullImage( int nID, SG_RECT rt, float fScale, float fRotate, SG_COLOR clr )
	{
		int newPosX = -(m_def.MobileWidth/2) + rt.nLeft  + m_img[nID].nWidth/2;
		int newPosY = m_def.MobileHeight/2 - rt.nTop - m_img[nID].nHeight/2;		
		
		setColor( clr );

		m_gl.glLoadIdentity();

		// 위치
		m_gl.glTranslatex( Def.FixedFromInt(newPosX), Def.FixedFromInt(newPosY), 0 );

		// 크기
		m_gl.glScalef( fScale, fScale, 0.0f );

		// 회전
		m_gl.glRotatef( fRotate, 0.0f, 0.0f, 1.0f );
		
		setVertexRect( m_img[nID].nWidth, m_img[nID].nHeight );

		setTextureRectNew( nID );

		m_gl.glColor4f( 1.0f, 1.0f, 1.0f, 1.0f );
	}
	
	public void DrawPartImage( int nID, int nCntWidth, int nCntHeight, int nIndex,
			   SG_RECT rt, float fScale, float fRotate, SG_COLOR clr )
	{
	
		m_gl.glColor4f( clr.fRed, clr.fGreen, clr.fBlue, clr.fAlpha );
		
		int nPartWidth = m_img[nID].nWidth / nCntWidth;
		int nPartHeight = m_img[nID].nHeight / nCntHeight;

		int newPosX = -(m_def.MobileWidth/2) + rt.nLeft  + nPartWidth/2;
		int newPosY = m_def.MobileHeight/2 - rt.nTop - nPartHeight/2;

		m_gl.glLoadIdentity();

		// 위치
		m_gl.glTranslatex( Def.FixedFromInt(newPosX), Def.FixedFromInt(newPosY), 0 );

		// 크기
		m_gl.glScalef( fScale, fScale, 0.0f );

		// 회전
		m_gl.glRotatef( fRotate, 0.0f, 0.0f, 1.0f );	
		
		setVertexRect( nPartWidth, nPartHeight );
	
		setPartTextureRectNew( nID, nCntWidth, nCntHeight, nIndex );
		
		m_gl.glColor4f( 1.0f, 1.0f, 1.0f, 1.0f );
	}	

	public void DrawBoundImage( int nID, SG_RECT rt, int nPosX, int nPosY, float fScale, float fRotate, SG_COLOR clr )
	{
		int nPartWidth = rt.nRight - rt.nLeft;
		int nPartHeight = rt.nBottom - rt.nTop;

		int newPosX = -(m_def.MobileWidth/2) + nPosX  + nPartWidth/2;
		int newPosY = m_def.MobileHeight/2 - nPosY - nPartHeight/2;

		m_gl.glLoadIdentity();

		m_gl.glLoadIdentity();

		// 위치
		m_gl.glTranslatex(Def.FixedFromInt(newPosX), Def.FixedFromInt(newPosY), 0 );

		// 크기
		m_gl.glScalef( fScale, fScale, 0.0f );

		// 회전
		m_gl.glRotatef( fRotate, 0.0f, 0.0f, 1.0f );

		setVertexRect( nPartWidth, nPartHeight );		

		setBoundTextureRectNew( nID, rt );

		m_gl.glColor4f( 1.0f, 1.0f, 1.0f, 1.0f );
	}
	
	public void setVertexRect( int width, int height )
	{
		float bgVertices[] =
		{
			-1.0f,  1.0f,
			-1.0f, -1.0f,
			1.0f,  1.0f,
			1.0f, -1.0f	
		};
		
		// 정점 영역
		for( int i=0; i<8; i++ )
		{
			if( (i%2) == 0)
				m_bgVertices[i] = bgVertices[i] * (float)(width/2);
			else
				m_bgVertices[i] = bgVertices[i] * (float)(height/2);
		}	
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect( m_bgVertices.length * 4 );
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(m_bgVertices);
		vertexBuffer.position(0);
		
		m_gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertexBuffer);
	}	
	
	public void setTextureRectNew( int nID )
	{
		int width = m_img[nID].nWidth;
		int height = m_img[nID].nHeight;

		int nStartX = m_img[nID].nImgStartX;
		int nEndX = nStartX + width;

		int nStartY = m_img[nID].nImgStartY;
		int nEndY = nStartY + height;
		
		// 이미지 영역
		bgTexCoords_new[0] = (float)nStartX / (float)m_img[nID].nTexWidth;
		bgTexCoords_new[1] = (float)nStartY / (float)m_img[nID].nTexHeight;

		bgTexCoords_new[2] = bgTexCoords_new[0];		
		bgTexCoords_new[3] = (float)nEndY / (float)m_img[nID].nTexHeight;
	
		bgTexCoords_new[4] = (float)nEndX / (float)m_img[nID].nTexWidth;
		bgTexCoords_new[5] = bgTexCoords_new[1];

		bgTexCoords_new[6] = bgTexCoords_new[4];
		bgTexCoords_new[7] = bgTexCoords_new[3];
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(bgTexCoords_new.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(bgTexCoords_new);
		textureBuffer.position(0);
		
		m_gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);	
		
		m_gl.glBindTexture( GL10.GL_TEXTURE_2D, tex[nID] );
		
		m_gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );
	}
	
	public void setPartTextureRectNew( int nID, int nCntWidth, int nCntHeight, int nIndex )
	{
		// 부분 이미지 영역
		int width = m_img[nID].nWidth / nCntWidth;
		int height = m_img[nID].nHeight / nCntHeight;

		if( nIndex >= (nCntWidth * nCntHeight) )
		{
			nIndex = 0;
		}

		int nWidthIndex = nIndex % nCntWidth;
		int nHeightIndex = nIndex / nCntWidth;

		int nStartX = width * nWidthIndex + m_img[nID].nImgStartX;
		int nEndX = width * (nWidthIndex+1) + m_img[nID].nImgStartX;

		int nStartY = height * nHeightIndex + m_img[nID].nImgStartY;
		int nEndY = height * (nHeightIndex+1) + m_img[nID].nImgStartY;
			
		bgTexCoords_new[0] = (float)nStartX / (float)m_img[nID].nTexWidth;
		bgTexCoords_new[1] = (float)(nStartY) / (float)m_img[nID].nTexHeight;

		bgTexCoords_new[2] = bgTexCoords_new[0];
		bgTexCoords_new[3] = (float)(nEndY) / (float)m_img[nID].nTexHeight;

		bgTexCoords_new[4] = (float)nEndX / (float)m_img[nID].nTexWidth;
		bgTexCoords_new[5] = bgTexCoords_new[1];

		bgTexCoords_new[6] = bgTexCoords_new[4];
		bgTexCoords_new[7] = bgTexCoords_new[3];
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(bgTexCoords_new.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(bgTexCoords_new);
		textureBuffer.position(0);
			
		m_gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
			
		m_gl.glBindTexture( GL10.GL_TEXTURE_2D, m_img[nID].pImage );
			
		m_gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );	
	}
	
	public void setBoundTextureRectNew( int nID, SG_RECT rt )
	{
		// 부분 이미지 영역
		int nStartX = rt.nLeft;
		int nEndX = rt.nRight;

		int nStartY = rt.nTop;
		int nEndY = rt.nBottom;

		// 이미지 영역	
		bgTexCoords_new[0] = (float)nStartX / (float)m_img[nID].nTexWidth;
		bgTexCoords_new[1] = (float)(nStartY) / (float)m_img[nID].nTexHeight;

		bgTexCoords_new[2] = bgTexCoords_new[0];
		bgTexCoords_new[3] = (float)(nEndY) / (float)m_img[nID].nTexHeight;

		bgTexCoords_new[4] = (float)nEndX / (float)m_img[nID].nTexWidth;
		bgTexCoords_new[5] = bgTexCoords_new[1];

		bgTexCoords_new[6] = bgTexCoords_new[4];
		bgTexCoords_new[7] = bgTexCoords_new[3];
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(bgTexCoords_new.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(bgTexCoords_new);
		textureBuffer.position(0);
	
		m_gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		m_gl.glBindTexture( GL10.GL_TEXTURE_2D, m_img[nID].pImage );
		
		m_gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );
	}
		
}
