
package com.sgsong.Animation;

public class EasingCurve
{	
	public static final int MAX_SIZE = 34;
	public static final int MAX_STEP = 101;
	
	public static final int Linear = 0;
	
	public static final int InQuad = 1;
	public static final int OutQuad = 2;
	public static final int InOutQuad = 3;
	public static final int OutInQuad = 4;	
	
	public static final int InCubic = 5;
	public static final int OutCubic = 6;
	public static final int InOutCubic = 7;
	public static final int OutInCubic = 8;	
	
	public static final int InQuart = 9;
	public static final int OutQuart = 10;
	public static final int InOutQuart = 11;
	public static final int OutInQuart = 12;
	
	public static final int InQuint = 13;
	public static final int OutQuint = 14;
	public static final int InOutQuint = 15;
	public static final int OutInQuint = 16;
	
	public static final int InSine = 17;
	public static final int OutSine = 18;
	public static final int InOutSine = 19;
	public static final int OutInSine = 20;	
	
	public static final int InExpo = 21;
	public static final int OutExpo = 22;
	public static final int InOutExpo = 23;
	public static final int OutInExpo = 24;
	
	public static final int InCirc = 25;
	public static final int OutCirc = 26;
	public static final int InOutCirc = 27;
	public static final int OutInCirc = 28;
	
	public static final int OutInBack = 29;
	
	public static final int InBounce = 30;
	public static final int OutBounce = 31;
	public static final int InOutBounce = 32;
	public static final int OutInBounce = 33;
	
	private float m_fValue[][] = new float[MAX_SIZE][MAX_STEP];
	
	public EasingCurve()
	{
		for( int i=0; i<MAX_SIZE; i++ )
		{
			for( int j=0; j<MAX_STEP; j++ )
			{	
				makeValue( i, j, 0.0f );
			}
		}

		makeLinear();

		makeInQuad();
		makeOutQuad();
		makeInOutQuad();
		makeOutInQuad();

		makeInCubic();
		makeOutCubic();
		makeInOutCubic();
		makeOutInCubic();

		makeInQuart();
		makeOutQuart();
		makeInOutQuart();
		makeOutInQuart();

		makeInQuint();
		makeOutQuint();
		makeInOutQuint();
		makeOutInQuint();

		makeInSine();
		makeOutSine();
		makeInOutSine();
		makeOutInSine();	

		makeInExpo();
		makeOutExpo();
		makeInOutExpo();
		makeOutInExpo();

		makeInCirc();
		makeOutCirc();
		makeInOutCirc();
		makeOutInCirc();

		makeOutInBack();		

		makeInBounce();
		makeOutBounce();
		makeInOutBounce();
		makeOutInBounce();	
	}	

	public void makeValue( int nType, int nStep, float fValue )
	{
		m_fValue[nType][nStep] = fValue;
	}

	//float getValue( int nType, int nStep )
	public float getValue( int nType, double percent )
	{
		int nStep = (int)( 100.0f * percent );
		return m_fValue[nType][nStep];
	}

	public void makeLinear()
	{
		makeValue( Linear, 0, 0.0f );

		makeValue( Linear, 1, 0.01f );	makeValue( Linear, 2, 0.02f );
		makeValue( Linear, 3, 0.03f );	makeValue( Linear, 4, 0.04f );
		makeValue( Linear, 5, 0.05f );	makeValue( Linear, 6, 0.06f );
		makeValue( Linear, 7, 0.07f );	makeValue( Linear, 8, 0.08f );
		makeValue( Linear, 9, 0.09f );	makeValue( Linear, 10, 0.10f );

		makeValue( Linear, 11, 0.11f );	makeValue( Linear, 12, 0.12f );
		makeValue( Linear, 13, 0.13f );	makeValue( Linear, 14, 0.14f );
		makeValue( Linear, 15, 0.15f );	makeValue( Linear, 16, 0.16f );
		makeValue( Linear, 17, 0.17f );	makeValue( Linear, 18, 0.18f );
		makeValue( Linear, 19, 0.19f );	makeValue( Linear, 20, 0.20f );

		makeValue( Linear, 21, 0.21f );	makeValue( Linear, 22, 0.22f );
		makeValue( Linear, 23, 0.23f );	makeValue( Linear, 24, 0.24f );
		makeValue( Linear, 25, 0.25f );	makeValue( Linear, 26, 0.26f );
		makeValue( Linear, 27, 0.27f );	makeValue( Linear, 28, 0.28f );
		makeValue( Linear, 29, 0.29f );	makeValue( Linear, 30, 0.30f );

		makeValue( Linear, 31, 0.31f );	makeValue( Linear, 32, 0.32f );
		makeValue( Linear, 33, 0.33f );	makeValue( Linear, 34, 0.34f );
		makeValue( Linear, 35, 0.35f );	makeValue( Linear, 36, 0.36f );
		makeValue( Linear, 37, 0.37f );	makeValue( Linear, 38, 0.38f );
		makeValue( Linear, 39, 0.39f );	makeValue( Linear, 40, 0.40f );

		makeValue( Linear, 41, 0.41f );	makeValue( Linear, 42, 0.42f );
		makeValue( Linear, 43, 0.43f );	makeValue( Linear, 44, 0.44f );
		makeValue( Linear, 45, 0.45f );	makeValue( Linear, 46, 0.46f );
		makeValue( Linear, 47, 0.47f );	makeValue( Linear, 48, 0.48f );
		makeValue( Linear, 49, 0.49f );	makeValue( Linear, 50, 0.50f );

		makeValue( Linear, 51, 0.51f );	makeValue( Linear, 52, 0.52f );
		makeValue( Linear, 53, 0.53f );	makeValue( Linear, 54, 0.54f );
		makeValue( Linear, 55, 0.55f );	makeValue( Linear, 56, 0.56f );
		makeValue( Linear, 57, 0.57f );	makeValue( Linear, 58, 0.58f );
		makeValue( Linear, 59, 0.59f );	makeValue( Linear, 60, 0.60f );

		makeValue( Linear, 61, 0.61f );	makeValue( Linear, 62, 0.62f );
		makeValue( Linear, 63, 0.63f );	makeValue( Linear, 64, 0.64f );
		makeValue( Linear, 65, 0.65f );	makeValue( Linear, 66, 0.66f );
		makeValue( Linear, 67, 0.67f );	makeValue( Linear, 68, 0.68f );
		makeValue( Linear, 69, 0.69f );	makeValue( Linear, 70, 0.70f );

		makeValue( Linear, 71, 0.71f );	makeValue( Linear, 72, 0.72f );
		makeValue( Linear, 73, 0.73f );	makeValue( Linear, 74, 0.74f );
		makeValue( Linear, 75, 0.75f );	makeValue( Linear, 76, 0.76f );
		makeValue( Linear, 77, 0.77f );	makeValue( Linear, 78, 0.78f );
		makeValue( Linear, 79, 0.79f );	makeValue( Linear, 80, 0.80f );

		makeValue( Linear, 81, 0.81f );	makeValue( Linear, 82, 0.82f );
		makeValue( Linear, 83, 0.83f );	makeValue( Linear, 84, 0.84f );
		makeValue( Linear, 85, 0.85f );	makeValue( Linear, 86, 0.86f );
		makeValue( Linear, 87, 0.87f );	makeValue( Linear, 88, 0.88f );
		makeValue( Linear, 89, 0.89f );	makeValue( Linear, 90, 0.90f );

		makeValue( Linear, 91, 0.91f );	makeValue( Linear, 92, 0.92f );
		makeValue( Linear, 93, 0.93f );	makeValue( Linear, 94, 0.94f );
		makeValue( Linear, 95, 0.95f );	makeValue( Linear, 96, 0.96f );
		makeValue( Linear, 97, 0.97f );	makeValue( Linear, 98, 0.98f );
		makeValue( Linear, 99, 0.99f );	makeValue( Linear, 100, 1.0f );
	}

	public void makeInCubic()
	{
		makeValue( InCubic, 0, 0.0f );

		makeValue( InCubic, 1, 0.00f );	makeValue( InCubic, 2, 0.00f );
		makeValue( InCubic, 3, 0.00f );	makeValue( InCubic, 4, 0.00f );
		makeValue( InCubic, 5, 0.00f );	makeValue( InCubic, 6, 0.00f );
		makeValue( InCubic, 7, 0.00f );	makeValue( InCubic, 8, 0.00f );
		makeValue( InCubic, 9, 0.00f );	makeValue( InCubic, 10, 0.00f );

		makeValue( InCubic, 11, 0.00f );	makeValue( InCubic, 12, 0.00f );
		makeValue( InCubic, 13, 0.00f );	makeValue( InCubic, 14, 0.00f );
		makeValue( InCubic, 15, 0.00f );	makeValue( InCubic, 16, 0.00f );
		makeValue( InCubic, 17, 0.00f );	makeValue( InCubic, 18, 0.01f );
		makeValue( InCubic, 19, 0.01f );	makeValue( InCubic, 20, 0.01f );

		makeValue( InCubic, 21, 0.01f );	makeValue( InCubic, 22, 0.01f );
		makeValue( InCubic, 23, 0.01f );	makeValue( InCubic, 24, 0.01f );
		makeValue( InCubic, 25, 0.02f );	makeValue( InCubic, 26, 0.02f );
		makeValue( InCubic, 27, 0.02f );	makeValue( InCubic, 28, 0.02f );
		makeValue( InCubic, 29, 0.03f );	makeValue( InCubic, 30, 0.03f );

		makeValue( InCubic, 31, 0.03f );	makeValue( InCubic, 32, 0.03f );
		makeValue( InCubic, 33, 0.04f );	makeValue( InCubic, 34, 0.04f );
		makeValue( InCubic, 35, 0.04f );	makeValue( InCubic, 36, 0.05f );
		makeValue( InCubic, 37, 0.05f );	makeValue( InCubic, 38, 0.06f );
		makeValue( InCubic, 39, 0.06f );	makeValue( InCubic, 40, 0.06f );

		makeValue( InCubic, 41, 0.07f );	makeValue( InCubic, 42, 0.07f );
		makeValue( InCubic, 43, 0.08f );	makeValue( InCubic, 44, 0.08f );
		makeValue( InCubic, 45, 0.09f );	makeValue( InCubic, 46, 0.10f );
		makeValue( InCubic, 47, 0.10f );	makeValue( InCubic, 48, 0.11f );
		makeValue( InCubic, 49, 0.12f );	makeValue( InCubic, 50, 0.12f );

		makeValue( InCubic, 51, 0.13f );	makeValue( InCubic, 52, 0.14f );
		makeValue( InCubic, 53, 0.15f );	makeValue( InCubic, 54, 0.15f );
		makeValue( InCubic, 55, 0.16f );	makeValue( InCubic, 56, 0.17f );
		makeValue( InCubic, 57, 0.18f );	makeValue( InCubic, 58, 0.19f );
		makeValue( InCubic, 59, 0.20f );	makeValue( InCubic, 60, 0.21f );

		makeValue( InCubic, 61, 0.22f );	makeValue( InCubic, 62, 0.23f );
		makeValue( InCubic, 63, 0.26f );	makeValue( InCubic, 64, 0.27f );
		makeValue( InCubic, 65, 0.28f );	makeValue( InCubic, 66, 0.29f );
		makeValue( InCubic, 67, 0.31f );	makeValue( InCubic, 68, 0.32f );
		makeValue( InCubic, 69, 0.33f );	makeValue( InCubic, 70, 0.35f );

		makeValue( InCubic, 71, 0.36f );	makeValue( InCubic, 72, 0.38f );
		makeValue( InCubic, 73, 0.39f );	makeValue( InCubic, 74, 0.41f );
		makeValue( InCubic, 75, 0.43f );	makeValue( InCubic, 76, 0.44f );
		makeValue( InCubic, 77, 0.46f );	makeValue( InCubic, 78, 0.48f );
		makeValue( InCubic, 79, 0.49f );	makeValue( InCubic, 80, 0.51f );

		makeValue( InCubic, 81, 0.53f );	makeValue( InCubic, 82, 0.55f );
		makeValue( InCubic, 83, 0.57f );	makeValue( InCubic, 84, 0.59f );
		makeValue( InCubic, 85, 0.61f );	makeValue( InCubic, 86, 0.63f );
		makeValue( InCubic, 87, 0.66f );	makeValue( InCubic, 88, 0.68f );
		makeValue( InCubic, 89, 0.70f );	makeValue( InCubic, 90, 0.72f );

		makeValue( InCubic, 91, 0.75f );	makeValue( InCubic, 92, 0.77f );
		makeValue( InCubic, 93, 0.80f );	makeValue( InCubic, 94, 0.82f );
		makeValue( InCubic, 95, 0.85f );	makeValue( InCubic, 96, 0.88f );
		makeValue( InCubic, 97, 0.90f );	makeValue( InCubic, 98, 0.93f );
		makeValue( InCubic, 99, 0.96f );	makeValue( InCubic, 100, 1.0f );	
	}

	public void makeOutCubic()
	{
		makeValue( OutCubic, 0, 0.0f );

		makeValue( OutCubic, 1, 0.03f );	makeValue( OutCubic, 2, 0.06f );
		makeValue( OutCubic, 3, 0.09f );	makeValue( OutCubic, 4, 0.11f );
		makeValue( OutCubic, 5, 0.14f );	makeValue( OutCubic, 6, 0.16f );
		makeValue( OutCubic, 7, 0.19f );	makeValue( OutCubic, 8, 0.22f );
		makeValue( OutCubic, 9, 0.24f );	makeValue( OutCubic, 10, 0.26f );

		makeValue( OutCubic, 11, 0.29f );	makeValue( OutCubic, 12, 0.31f );
		makeValue( OutCubic, 13, 0.33f );	makeValue( OutCubic, 14, 0.36f );
		makeValue( OutCubic, 15, 0.38f );	makeValue( OutCubic, 16, 0.40f );
		makeValue( OutCubic, 17, 0.42f );	makeValue( OutCubic, 18, 0.44f );
		makeValue( OutCubic, 19, 0.46f );	makeValue( OutCubic, 20, 0.48f );

		makeValue( OutCubic, 21, 0.50f );	makeValue( OutCubic, 22, 0.52f );
		makeValue( OutCubic, 23, 0.53f );	makeValue( OutCubic, 24, 0.55f );
		makeValue( OutCubic, 25, 0.57f );	makeValue( OutCubic, 26, 0.58f );
		makeValue( OutCubic, 27, 0.60f );	makeValue( OutCubic, 28, 0.62f );
		makeValue( OutCubic, 29, 0.63f );	makeValue( OutCubic, 30, 0.65f );

		makeValue( OutCubic, 31, 0.66f );	makeValue( OutCubic, 32, 0.69f );
		makeValue( OutCubic, 33, 0.70f );	makeValue( OutCubic, 34, 0.71f );
		makeValue( OutCubic, 35, 0.73f );	makeValue( OutCubic, 36, 0.74f );
		makeValue( OutCubic, 37, 0.75f );	makeValue( OutCubic, 38, 0.76f );
		makeValue( OutCubic, 39, 0.77f );	makeValue( OutCubic, 40, 0.78f );

		makeValue( OutCubic, 41, 0.79f );	makeValue( OutCubic, 42, 0.80f );
		makeValue( OutCubic, 43, 0.81f );	makeValue( OutCubic, 44, 0.82f );
		makeValue( OutCubic, 45, 0.83f );	makeValue( OutCubic, 46, 0.84f );
		makeValue( OutCubic, 47, 0.85f );	makeValue( OutCubic, 48, 0.86f );
		makeValue( OutCubic, 49, 0.87f );	makeValue( OutCubic, 50, 0.87f );

		makeValue( OutCubic, 51, 0.88f );	makeValue( OutCubic, 52, 0.89f );
		makeValue( OutCubic, 53, 0.89f );	makeValue( OutCubic, 54, 0.90f );
		makeValue( OutCubic, 55, 0.91f );	makeValue( OutCubic, 56, 0.91f );
		makeValue( OutCubic, 57, 0.92f );	makeValue( OutCubic, 58, 0.92f );
		makeValue( OutCubic, 59, 0.93f );	makeValue( OutCubic, 60, 0.93f );

		makeValue( OutCubic, 61, 0.94f );	makeValue( OutCubic, 62, 0.94f );
		makeValue( OutCubic, 63, 0.95f );	makeValue( OutCubic, 64, 0.95f );
		makeValue( OutCubic, 65, 0.96f );	makeValue( OutCubic, 66, 0.96f );
		makeValue( OutCubic, 67, 0.96f );	makeValue( OutCubic, 68, 0.97f );
		makeValue( OutCubic, 69, 0.97f );	makeValue( OutCubic, 70, 0.97f );

		makeValue( OutCubic, 71, 0.97f );	makeValue( OutCubic, 72, 0.98f );
		makeValue( OutCubic, 73, 0.98f );	makeValue( OutCubic, 74, 0.98f );
		makeValue( OutCubic, 75, 0.98f );	makeValue( OutCubic, 76, 0.99f );
		makeValue( OutCubic, 77, 0.99f );	makeValue( OutCubic, 78, 0.99f );
		makeValue( OutCubic, 79, 0.99f );	makeValue( OutCubic, 80, 0.99f );

		makeValue( OutCubic, 81, 0.99f );	makeValue( OutCubic, 82, 0.99f );
		makeValue( OutCubic, 83, 1.00f );	makeValue( OutCubic, 84, 1.00f );
		makeValue( OutCubic, 85, 1.00f );	makeValue( OutCubic, 86, 1.00f );
		makeValue( OutCubic, 87, 1.00f );	makeValue( OutCubic, 88, 1.00f );
		makeValue( OutCubic, 89, 1.00f );	makeValue( OutCubic, 90, 1.00f );

		makeValue( OutCubic, 91, 1.00f );	makeValue( OutCubic, 92, 1.00f );
		makeValue( OutCubic, 93, 1.00f );	makeValue( OutCubic, 94, 1.00f );
		makeValue( OutCubic, 95, 1.00f );	makeValue( OutCubic, 96, 1.00f );
		makeValue( OutCubic, 97, 1.00f );	makeValue( OutCubic, 98, 1.00f );
		makeValue( OutCubic, 99, 1.00f );	makeValue( OutCubic, 100, 1.00f );
	}

	public void makeInQuad()
	{
		makeValue( InQuad, 0, 0.0f );

		makeValue( InQuad, 1, 0.00f );	makeValue( InQuad, 2, 0.00f );
		makeValue( InQuad, 3, 0.00f );	makeValue( InQuad, 4, 0.00f );
		makeValue( InQuad, 5, 0.00f );	makeValue( InQuad, 6, 0.00f );
		makeValue( InQuad, 7, 0.00f );	makeValue( InQuad, 8, 0.01f );
		makeValue( InQuad, 9, 0.01f );	makeValue( InQuad, 10, 0.01f );

		makeValue( InQuad, 11, 0.01f );	makeValue( InQuad, 12, 0.01f );
		makeValue( InQuad, 13, 0.02f );	makeValue( InQuad, 14, 0.02f );
		makeValue( InQuad, 15, 0.02f );	makeValue( InQuad, 16, 0.02f );
		makeValue( InQuad, 17, 0.03f );	makeValue( InQuad, 18, 0.03f );
		makeValue( InQuad, 19, 0.03f );	makeValue( InQuad, 20, 0.04f );

		makeValue( InQuad, 21, 0.04f );	makeValue( InQuad, 22, 0.05f );
		makeValue( InQuad, 23, 0.05f );	makeValue( InQuad, 24, 0.05f );
		makeValue( InQuad, 25, 0.06f );	makeValue( InQuad, 26, 0.06f );
		makeValue( InQuad, 27, 0.07f );	makeValue( InQuad, 28, 0.07f );
		makeValue( InQuad, 29, 0.08f );	makeValue( InQuad, 30, 0.09f );

		makeValue( InQuad, 31, 0.09f );	makeValue( InQuad, 32, 0.10f );
		makeValue( InQuad, 33, 0.10f );	makeValue( InQuad, 34, 0.12f );
		makeValue( InQuad, 35, 0.12f );	makeValue( InQuad, 36, 0.13f );
		makeValue( InQuad, 37, 0.14f );	makeValue( InQuad, 38, 0.14f );
		makeValue( InQuad, 39, 0.15f );	makeValue( InQuad, 40, 0.16f );

		makeValue( InQuad, 41, 0.17f );	makeValue( InQuad, 42, 0.18f );
		makeValue( InQuad, 43, 0.18f );	makeValue( InQuad, 44, 0.19f );
		makeValue( InQuad, 45, 0.20f );	makeValue( InQuad, 46, 0.21f );
		makeValue( InQuad, 47, 0.22f );	makeValue( InQuad, 48, 0.23f );
		makeValue( InQuad, 49, 0.24f );	makeValue( InQuad, 50, 0.25f );

		makeValue( InQuad, 51, 0.26f );	makeValue( InQuad, 52, 0.27f );
		makeValue( InQuad, 53, 0.28f );	makeValue( InQuad, 54, 0.29f );
		makeValue( InQuad, 55, 0.30f );	makeValue( InQuad, 56, 0.31f );
		makeValue( InQuad, 57, 0.32f );	makeValue( InQuad, 58, 0.33f );
		makeValue( InQuad, 59, 0.34f );	makeValue( InQuad, 60, 0.35f );

		makeValue( InQuad, 61, 0.37f );	makeValue( InQuad, 62, 0.38f );
		makeValue( InQuad, 63, 0.39f );	makeValue( InQuad, 64, 0.40f );
		makeValue( InQuad, 65, 0.42f );	makeValue( InQuad, 66, 0.43f );
		makeValue( InQuad, 67, 0.44f );	makeValue( InQuad, 68, 0.45f );
		makeValue( InQuad, 69, 0.47f );	makeValue( InQuad, 70, 0.48f );

		makeValue( InQuad, 71, 0.49f );	makeValue( InQuad, 72, 0.51f );
		makeValue( InQuad, 73, 0.52f );	makeValue( InQuad, 74, 0.54f );
		makeValue( InQuad, 75, 0.55f );	makeValue( InQuad, 76, 0.58f );
		makeValue( InQuad, 77, 0.59f );	makeValue( InQuad, 78, 0.61f );
		makeValue( InQuad, 79, 0.63f );	makeValue( InQuad, 80, 0.64f );

		makeValue( InQuad, 81, 0.66f );	makeValue( InQuad, 82, 0.67f );
		makeValue( InQuad, 83, 0.69f );	makeValue( InQuad, 84, 0.71f );
		makeValue( InQuad, 85, 0.72f );	makeValue( InQuad, 86, 0.74f );
		makeValue( InQuad, 87, 0.76f );	makeValue( InQuad, 88, 0.77f );
		makeValue( InQuad, 89, 0.79f );	makeValue( InQuad, 90, 0.81f );

		makeValue( InQuad, 91, 0.82f );	makeValue( InQuad, 92, 0.84f );
		makeValue( InQuad, 93, 0.86f );	makeValue( InQuad, 94, 0.88f );
		makeValue( InQuad, 95, 0.90f );	makeValue( InQuad, 96, 0.92f );
		makeValue( InQuad, 97, 0.93f );	makeValue( InQuad, 98, 0.95f );
		makeValue( InQuad, 99, 0.97f );	makeValue( InQuad, 100, 1.0f );
	}

	public void makeOutQuad()
	{
		makeValue( OutQuad, 0, 0.0f );

		makeValue( OutQuad, 1, 0.02f );	makeValue( OutQuad, 2, 0.04f );
		makeValue( OutQuad, 3, 0.06f );	makeValue( OutQuad, 4, 0.08f );
		makeValue( OutQuad, 5, 0.10f );	makeValue( OutQuad, 6, 0.11f );
		makeValue( OutQuad, 7, 0.13f );	makeValue( OutQuad, 8, 0.15f );
		makeValue( OutQuad, 9, 0.17f );	makeValue( OutQuad, 10, 0.19f );

		makeValue( OutQuad, 11, 0.20f );	makeValue( OutQuad, 12, 0.22f );
		makeValue( OutQuad, 13, 0.24f );	makeValue( OutQuad, 14, 0.26f );
		makeValue( OutQuad, 15, 0.27f );	makeValue( OutQuad, 16, 0.29f );
		makeValue( OutQuad, 17, 0.30f );	makeValue( OutQuad, 18, 0.32f );
		makeValue( OutQuad, 19, 0.34f );	makeValue( OutQuad, 20, 0.35f );

		makeValue( OutQuad, 21, 0.37f );	makeValue( OutQuad, 22, 0.38f );
		makeValue( OutQuad, 23, 0.40f );	makeValue( OutQuad, 24, 0.43f );
		makeValue( OutQuad, 25, 0.44f );	makeValue( OutQuad, 26, 0.46f );
		makeValue( OutQuad, 27, 0.47f );	makeValue( OutQuad, 28, 0.49f );
		makeValue( OutQuad, 29, 0.50f );	makeValue( OutQuad, 30, 0.51f );

		makeValue( OutQuad, 31, 0.53f );	makeValue( OutQuad, 32, 0.54f );
		makeValue( OutQuad, 33, 0.55f );	makeValue( OutQuad, 34, 0.57f );
		makeValue( OutQuad, 35, 0.58f );	makeValue( OutQuad, 36, 0.59f );
		makeValue( OutQuad, 37, 0.60f );	makeValue( OutQuad, 38, 0.62f );
		makeValue( OutQuad, 39, 0.63f );	makeValue( OutQuad, 40, 0.64f );

		makeValue( OutQuad, 41, 0.65f );	makeValue( OutQuad, 42, 0.66f );
		makeValue( OutQuad, 43, 0.67f );	makeValue( OutQuad, 44, 0.69f );
		makeValue( OutQuad, 45, 0.70f );	makeValue( OutQuad, 46, 0.71f );
		makeValue( OutQuad, 47, 0.72f );	makeValue( OutQuad, 48, 0.73f );
		makeValue( OutQuad, 49, 0.74f );	makeValue( OutQuad, 50, 0.75f );

		makeValue( OutQuad, 51, 0.76f );	makeValue( OutQuad, 52, 0.77f );
		makeValue( OutQuad, 53, 0.78f );	makeValue( OutQuad, 54, 0.79f );
		makeValue( OutQuad, 55, 0.79f );	makeValue( OutQuad, 56, 0.80f );
		makeValue( OutQuad, 57, 0.81f );	makeValue( OutQuad, 58, 0.82f );
		makeValue( OutQuad, 59, 0.83f );	makeValue( OutQuad, 60, 0.84f );

		makeValue( OutQuad, 61, 0.84f );	makeValue( OutQuad, 62, 0.85f );
		makeValue( OutQuad, 63, 0.86f );	makeValue( OutQuad, 64, 0.87f );
		makeValue( OutQuad, 65, 0.87f );	makeValue( OutQuad, 66, 0.89f );
		makeValue( OutQuad, 67, 0.89f );	makeValue( OutQuad, 68, 0.90f );
		makeValue( OutQuad, 69, 0.91f );	makeValue( OutQuad, 70, 0.91f );

		makeValue( OutQuad, 71, 0.92f );	makeValue( OutQuad, 72, 0.92f );
		makeValue( OutQuad, 73, 0.93f );	makeValue( OutQuad, 74, 0.93f );
		makeValue( OutQuad, 75, 0.94f );	makeValue( OutQuad, 76, 0.94f );
		makeValue( OutQuad, 77, 0.95f );	makeValue( OutQuad, 78, 0.95f );
		makeValue( OutQuad, 79, 0.96f );	makeValue( OutQuad, 80, 0.96f );

		makeValue( OutQuad, 81, 0.96f );	makeValue( OutQuad, 82, 0.97f );
		makeValue( OutQuad, 83, 0.97f );	makeValue( OutQuad, 84, 0.97f );
		makeValue( OutQuad, 85, 0.98f );	makeValue( OutQuad, 86, 0.98f );
		makeValue( OutQuad, 87, 0.98f );	makeValue( OutQuad, 88, 0.99f );
		makeValue( OutQuad, 89, 0.99f );	makeValue( OutQuad, 90, 0.99f );

		makeValue( OutQuad, 91, 0.99f );	makeValue( OutQuad, 92, 0.99f );
		makeValue( OutQuad, 93, 0.99f );	makeValue( OutQuad, 94, 1.00f );
		makeValue( OutQuad, 95, 1.00f );	makeValue( OutQuad, 96, 1.00f );
		makeValue( OutQuad, 97, 1.00f );	makeValue( OutQuad, 98, 1.00f );
		makeValue( OutQuad, 99, 1.00f );	makeValue( OutQuad, 100, 1.00f );
	}

	public void makeInOutQuad()
	{
		makeValue( InOutQuad, 0, 0.0f );

		makeValue( InOutQuad, 1, 0.00f );	makeValue( InOutQuad, 2, 0.00f );
		makeValue( InOutQuad, 3, 0.00f );	makeValue( InOutQuad, 4, 0.00f );
		makeValue( InOutQuad, 5, 0.00f );	makeValue( InOutQuad, 6, 0.01f );
		makeValue( InOutQuad, 7, 0.01f );	makeValue( InOutQuad, 8, 0.01f );
		makeValue( InOutQuad, 9, 0.02f );	makeValue( InOutQuad, 10, 0.02f );

		makeValue( InOutQuad, 11, 0.02f );	makeValue( InOutQuad, 12, 0.03f );
		makeValue( InOutQuad, 13, 0.03f );	makeValue( InOutQuad, 14, 0.04f );
		makeValue( InOutQuad, 15, 0.04f );	makeValue( InOutQuad, 16, 0.05f );
		makeValue( InOutQuad, 17, 0.06f );	makeValue( InOutQuad, 18, 0.06f );
		makeValue( InOutQuad, 19, 0.07f );	makeValue( InOutQuad, 20, 0.08f );

		makeValue( InOutQuad, 21, 0.08f );	makeValue( InOutQuad, 22, 0.09f );
		makeValue( InOutQuad, 23, 0.10f );	makeValue( InOutQuad, 24, 0.12f );
		makeValue( InOutQuad, 25, 0.13f );	makeValue( InOutQuad, 26, 0.14f );
		makeValue( InOutQuad, 27, 0.15f );	makeValue( InOutQuad, 28, 0.16f );
		makeValue( InOutQuad, 29, 0.17f );	makeValue( InOutQuad, 30, 0.18f );

		makeValue( InOutQuad, 31, 0.20f );	makeValue( InOutQuad, 32, 0.21f );
		makeValue( InOutQuad, 33, 0.22f );	makeValue( InOutQuad, 34, 0.23f );
		makeValue( InOutQuad, 35, 0.25f );	makeValue( InOutQuad, 36, 0.26f );
		makeValue( InOutQuad, 37, 0.28f );	makeValue( InOutQuad, 38, 0.29f );
		makeValue( InOutQuad, 39, 0.31f );	makeValue( InOutQuad, 40, 0.32f );

		makeValue( InOutQuad, 41, 0.34f );	makeValue( InOutQuad, 42, 0.35f );
		makeValue( InOutQuad, 43, 0.37f );	makeValue( InOutQuad, 44, 0.39f );
		makeValue( InOutQuad, 45, 0.40f );	makeValue( InOutQuad, 46, 0.42f );
		makeValue( InOutQuad, 47, 0.44f );	makeValue( InOutQuad, 48, 0.46f );
		makeValue( InOutQuad, 49, 0.48f );	makeValue( InOutQuad, 50, 0.50f );

		makeValue( InOutQuad, 51, 0.52f );	makeValue( InOutQuad, 52, 0.53f );
		makeValue( InOutQuad, 53, 0.55f );	makeValue( InOutQuad, 54, 0.57f );
		makeValue( InOutQuad, 55, 0.59f );	makeValue( InOutQuad, 56, 0.61f );
		makeValue( InOutQuad, 57, 0.62f );	makeValue( InOutQuad, 58, 0.64f );
		makeValue( InOutQuad, 59, 0.66f );	makeValue( InOutQuad, 60, 0.67f );

		makeValue( InOutQuad, 61, 0.69f );	makeValue( InOutQuad, 62, 0.70f );
		makeValue( InOutQuad, 63, 0.72f );	makeValue( InOutQuad, 64, 0.73f );
		makeValue( InOutQuad, 65, 0.75f );	makeValue( InOutQuad, 66, 0.77f );
		makeValue( InOutQuad, 67, 0.79f );	makeValue( InOutQuad, 68, 0.80f );
		makeValue( InOutQuad, 69, 0.81f );	makeValue( InOutQuad, 70, 0.82f );

		makeValue( InOutQuad, 71, 0.84f );	makeValue( InOutQuad, 72, 0.85f );
		makeValue( InOutQuad, 73, 0.86f );	makeValue( InOutQuad, 74, 0.87f );
		makeValue( InOutQuad, 75, 0.88f );	makeValue( InOutQuad, 76, 0.89f );
		makeValue( InOutQuad, 77, 0.90f );	makeValue( InOutQuad, 78, 0.90f );
		makeValue( InOutQuad, 79, 0.91f );	makeValue( InOutQuad, 80, 0.92f );

		makeValue( InOutQuad, 81, 0.93f );	makeValue( InOutQuad, 82, 0.94f );
		makeValue( InOutQuad, 83, 0.94f );	makeValue( InOutQuad, 84, 0.95f );
		makeValue( InOutQuad, 85, 0.95f );	makeValue( InOutQuad, 86, 0.96f );
		makeValue( InOutQuad, 87, 0.97f );	makeValue( InOutQuad, 88, 0.97f );
		makeValue( InOutQuad, 89, 0.98f );	makeValue( InOutQuad, 90, 0.98f );

		makeValue( InOutQuad, 91, 0.98f );	makeValue( InOutQuad, 92, 0.99f );
		makeValue( InOutQuad, 93, 0.99f );	makeValue( InOutQuad, 94, 0.99f );
		makeValue( InOutQuad, 95, 0.99f );	makeValue( InOutQuad, 96, 1.00f );
		makeValue( InOutQuad, 97, 1.00f );	makeValue( InOutQuad, 98, 1.00f );
		makeValue( InOutQuad, 99, 1.00f );	makeValue( InOutQuad, 100, 1.00f );
	}

	public void makeOutInQuad()
	{
		makeValue( OutInQuad, 0, 0.0f );

		makeValue( OutInQuad, 1, 0.02f );	makeValue( OutInQuad, 2, 0.04f );
		makeValue( OutInQuad, 3, 0.06f );	makeValue( OutInQuad, 4, 0.07f );
		makeValue( OutInQuad, 5, 0.09f );	makeValue( OutInQuad, 6, 0.11f );
		makeValue( OutInQuad, 7, 0.13f );	makeValue( OutInQuad, 8, 0.14f );
		makeValue( OutInQuad, 9, 0.16f );	makeValue( OutInQuad, 10, 0.18f );

		makeValue( OutInQuad, 11, 0.19f );	makeValue( OutInQuad, 12, 0.21f );
		makeValue( OutInQuad, 13, 0.22f );	makeValue( OutInQuad, 14, 0.24f );
		makeValue( OutInQuad, 15, 0.25f );	makeValue( OutInQuad, 16, 0.26f );
		makeValue( OutInQuad, 17, 0.28f );	makeValue( OutInQuad, 18, 0.29f );
		makeValue( OutInQuad, 19, 0.30f );	makeValue( OutInQuad, 20, 0.31f );

		makeValue( OutInQuad, 21, 0.33f );	makeValue( OutInQuad, 22, 0.34f );
		makeValue( OutInQuad, 23, 0.35f );	makeValue( OutInQuad, 24, 0.37f );
		makeValue( OutInQuad, 25, 0.38f );	makeValue( OutInQuad, 26, 0.39f );
		makeValue( OutInQuad, 27, 0.40f );	makeValue( OutInQuad, 28, 0.41f );
		makeValue( OutInQuad, 29, 0.41f );	makeValue( OutInQuad, 30, 0.42f );

		makeValue( OutInQuad, 31, 0.43f );	makeValue( OutInQuad, 32, 0.44f );
		makeValue( OutInQuad, 33, 0.44f );	makeValue( OutInQuad, 34, 0.45f );
		makeValue( OutInQuad, 35, 0.46f );	makeValue( OutInQuad, 36, 0.46f );
		makeValue( OutInQuad, 37, 0.47f );	makeValue( OutInQuad, 38, 0.47f );
		makeValue( OutInQuad, 39, 0.48f );	makeValue( OutInQuad, 40, 0.48f );

		makeValue( OutInQuad, 41, 0.48f );	makeValue( OutInQuad, 42, 0.49f );
		makeValue( OutInQuad, 43, 0.49f );	makeValue( OutInQuad, 44, 0.49f );
		makeValue( OutInQuad, 45, 0.49f );	makeValue( OutInQuad, 46, 0.50f );
		makeValue( OutInQuad, 47, 0.50f );	makeValue( OutInQuad, 48, 0.50f );
		makeValue( OutInQuad, 49, 0.50f );	makeValue( OutInQuad, 50, 0.50f );

		makeValue( OutInQuad, 51, 0.50f );	makeValue( OutInQuad, 52, 0.50f );
		makeValue( OutInQuad, 53, 0.50f );	makeValue( OutInQuad, 54, 0.50f );
		makeValue( OutInQuad, 55, 0.50f );	makeValue( OutInQuad, 56, 0.51f );
		makeValue( OutInQuad, 57, 0.51f );	makeValue( OutInQuad, 58, 0.51f );
		makeValue( OutInQuad, 59, 0.51f );	makeValue( OutInQuad, 60, 0.52f );

		makeValue( OutInQuad, 61, 0.52f );	makeValue( OutInQuad, 62, 0.53f );
		makeValue( OutInQuad, 63, 0.53f );	makeValue( OutInQuad, 64, 0.54f );
		makeValue( OutInQuad, 65, 0.54f );	makeValue( OutInQuad, 66, 0.55f );
		makeValue( OutInQuad, 67, 0.56f );	makeValue( OutInQuad, 68, 0.57f );
		makeValue( OutInQuad, 69, 0.57f );	makeValue( OutInQuad, 70, 0.58f );

		makeValue( OutInQuad, 71, 0.59f );	makeValue( OutInQuad, 72, 0.60f );
		makeValue( OutInQuad, 73, 0.61f );	makeValue( OutInQuad, 74, 0.62f );
		makeValue( OutInQuad, 75, 0.63f );	makeValue( OutInQuad, 76, 0.64f );
		makeValue( OutInQuad, 77, 0.65f );	makeValue( OutInQuad, 78, 0.66f );
		makeValue( OutInQuad, 79, 0.67f );	makeValue( OutInQuad, 80, 0.68f );

		makeValue( OutInQuad, 81, 0.69f );	makeValue( OutInQuad, 82, 0.70f );
		makeValue( OutInQuad, 83, 0.72f );	makeValue( OutInQuad, 84, 0.73f );
		makeValue( OutInQuad, 85, 0.74f );	makeValue( OutInQuad, 86, 0.76f );
		makeValue( OutInQuad, 87, 0.77f );	makeValue( OutInQuad, 88, 0.79f );
		makeValue( OutInQuad, 89, 0.80f );	makeValue( OutInQuad, 90, 0.82f );

		makeValue( OutInQuad, 91, 0.83f );	makeValue( OutInQuad, 92, 0.85f );
		makeValue( OutInQuad, 93, 0.87f );	makeValue( OutInQuad, 94, 0.88f );
		makeValue( OutInQuad, 95, 0.90f );	makeValue( OutInQuad, 96, 0.92f );
		makeValue( OutInQuad, 97, 0.94f );	makeValue( OutInQuad, 98, 0.95f );
		makeValue( OutInQuad, 99, 0.97f );	makeValue( OutInQuad, 100, 1.00f );
	}

	public void makeInOutCubic()
	{
		makeValue( InOutCubic, 0, 0.0f );

		makeValue( InOutCubic, 1, 0.00f );	makeValue( InOutCubic, 2, 0.00f );
		makeValue( InOutCubic, 3, 0.00f );	makeValue( InOutCubic, 4, 0.00f );
		makeValue( InOutCubic, 5, 0.00f );	makeValue( InOutCubic, 6, 0.00f );
		makeValue( InOutCubic, 7, 0.00f );	makeValue( InOutCubic, 8, 0.00f );
		makeValue( InOutCubic, 9, 0.00f );	makeValue( InOutCubic, 10, 0.00f );

		makeValue( InOutCubic, 11, 0.00f );	makeValue( InOutCubic, 12, 0.01f );
		makeValue( InOutCubic, 13, 0.01f );	makeValue( InOutCubic, 14, 0.01f );
		makeValue( InOutCubic, 15, 0.01f );	makeValue( InOutCubic, 16, 0.02f );
		makeValue( InOutCubic, 17, 0.02f );	makeValue( InOutCubic, 18, 0.02f );
		makeValue( InOutCubic, 19, 0.03f );	makeValue( InOutCubic, 20, 0.03f );

		makeValue( InOutCubic, 21, 0.03f );	makeValue( InOutCubic, 22, 0.04f );
		makeValue( InOutCubic, 23, 0.05f );	makeValue( InOutCubic, 24, 0.05f );
		makeValue( InOutCubic, 25, 0.06f );	makeValue( InOutCubic, 26, 0.07f );
		makeValue( InOutCubic, 27, 0.07f );	makeValue( InOutCubic, 28, 0.08f );
		makeValue( InOutCubic, 29, 0.09f );	makeValue( InOutCubic, 30, 0.10f );

		makeValue( InOutCubic, 31, 0.11f );	makeValue( InOutCubic, 32, 0.13f );
		makeValue( InOutCubic, 33, 0.15f );	makeValue( InOutCubic, 34, 0.16f );
		makeValue( InOutCubic, 35, 0.17f );	makeValue( InOutCubic, 36, 0.19f );
		makeValue( InOutCubic, 37, 0.20f );	makeValue( InOutCubic, 38, 0.22f );
		makeValue( InOutCubic, 39, 0.24f );	makeValue( InOutCubic, 40, 0.26f );

		makeValue( InOutCubic, 41, 0.28f );	makeValue( InOutCubic, 42, 0.30f );
		makeValue( InOutCubic, 43, 0.32f );	makeValue( InOutCubic, 44, 0.34f );
		makeValue( InOutCubic, 45, 0.36f );	makeValue( InOutCubic, 46, 0.39f );
		makeValue( InOutCubic, 47, 0.41f );	makeValue( InOutCubic, 48, 0.44f );
		makeValue( InOutCubic, 49, 0.47f );	makeValue( InOutCubic, 50, 0.49f );

		makeValue( InOutCubic, 51, 0.52f );	makeValue( InOutCubic, 52, 0.55f );
		makeValue( InOutCubic, 53, 0.58f );	makeValue( InOutCubic, 54, 0.60f );
		makeValue( InOutCubic, 55, 0.63f );	makeValue( InOutCubic, 56, 0.65f );
		makeValue( InOutCubic, 57, 0.67f );	makeValue( InOutCubic, 58, 0.70f );
		makeValue( InOutCubic, 59, 0.72f );	makeValue( InOutCubic, 60, 0.74f );

		makeValue( InOutCubic, 61, 0.75f );	makeValue( InOutCubic, 62, 0.77f );
		makeValue( InOutCubic, 63, 0.79f );	makeValue( InOutCubic, 64, 0.81f );
		makeValue( InOutCubic, 65, 0.82f );	makeValue( InOutCubic, 66, 0.83f );
		makeValue( InOutCubic, 67, 0.85f );	makeValue( InOutCubic, 68, 0.86f );
		makeValue( InOutCubic, 69, 0.87f );	makeValue( InOutCubic, 70, 0.89f );

		makeValue( InOutCubic, 71, 0.90f );	makeValue( InOutCubic, 72, 0.91f );
		makeValue( InOutCubic, 73, 0.92f );	makeValue( InOutCubic, 74, 0.93f );
		makeValue( InOutCubic, 75, 0.94f );	makeValue( InOutCubic, 76, 0.95f );
		makeValue( InOutCubic, 77, 0.95f );	makeValue( InOutCubic, 78, 0.96f );
		makeValue( InOutCubic, 79, 0.96f );	makeValue( InOutCubic, 80, 0.97f );

		makeValue( InOutCubic, 81, 0.97f );	makeValue( InOutCubic, 82, 0.98f );
		makeValue( InOutCubic, 83, 0.98f );	makeValue( InOutCubic, 84, 0.98f );
		makeValue( InOutCubic, 85, 0.99f );	makeValue( InOutCubic, 86, 0.99f );
		makeValue( InOutCubic, 87, 0.99f );	makeValue( InOutCubic, 88, 0.99f );
		makeValue( InOutCubic, 89, 0.99f );	makeValue( InOutCubic, 90, 1.00f );

		makeValue( InOutCubic, 91, 1.00f );	makeValue( InOutCubic, 92, 1.00f );
		makeValue( InOutCubic, 93, 1.00f );	makeValue( InOutCubic, 94, 1.00f );
		makeValue( InOutCubic, 95, 1.00f );	makeValue( InOutCubic, 96, 1.00f );
		makeValue( InOutCubic, 97, 1.00f );	makeValue( InOutCubic, 98, 1.00f );
		makeValue( InOutCubic, 99, 1.00f );	makeValue( InOutCubic, 100, 1.00f );
	}

	public void makeOutInCubic()
	{
		makeValue( OutInCubic, 0, 0.0f );

		makeValue( OutInCubic, 1, 0.03f );	makeValue( OutInCubic, 2, 0.06f );
		makeValue( OutInCubic, 3, 0.08f );	makeValue( OutInCubic, 4, 0.11f );
		makeValue( OutInCubic, 5, 0.13f );	makeValue( OutInCubic, 6, 0.16f );
		makeValue( OutInCubic, 7, 0.18f );	makeValue( OutInCubic, 8, 0.20f );
		makeValue( OutInCubic, 9, 0.22f );	makeValue( OutInCubic, 10, 0.24f );

		makeValue( OutInCubic, 11, 0.26f );	makeValue( OutInCubic, 12, 0.28f );
		makeValue( OutInCubic, 13, 0.29f );	makeValue( OutInCubic, 14, 0.31f );
		makeValue( OutInCubic, 15, 0.32f );	makeValue( OutInCubic, 16, 0.34f );
		makeValue( OutInCubic, 17, 0.35f );	makeValue( OutInCubic, 18, 0.36f );
		makeValue( OutInCubic, 19, 0.38f );	makeValue( OutInCubic, 20, 0.39f );

		makeValue( OutInCubic, 21, 0.40f );	makeValue( OutInCubic, 22, 0.41f );
		makeValue( OutInCubic, 23, 0.42f );	makeValue( OutInCubic, 24, 0.43f );
		makeValue( OutInCubic, 25, 0.43f );	makeValue( OutInCubic, 26, 0.44f );
		makeValue( OutInCubic, 27, 0.45f );	makeValue( OutInCubic, 28, 0.45f );
		makeValue( OutInCubic, 29, 0.46f );	makeValue( OutInCubic, 30, 0.46f );

		makeValue( OutInCubic, 31, 0.47f );	makeValue( OutInCubic, 32, 0.47f );
		makeValue( OutInCubic, 33, 0.48f );	makeValue( OutInCubic, 34, 0.48f );
		makeValue( OutInCubic, 35, 0.48f );	makeValue( OutInCubic, 36, 0.49f );
		makeValue( OutInCubic, 37, 0.49f );	makeValue( OutInCubic, 38, 0.49f );
		makeValue( OutInCubic, 39, 0.49f );	makeValue( OutInCubic, 40, 0.50f );

		makeValue( OutInCubic, 41, 0.50f );	makeValue( OutInCubic, 42, 0.50f );
		makeValue( OutInCubic, 43, 0.50f );	makeValue( OutInCubic, 44, 0.50f );
		makeValue( OutInCubic, 45, 0.50f );	makeValue( OutInCubic, 46, 0.50f );
		makeValue( OutInCubic, 47, 0.50f );	makeValue( OutInCubic, 48, 0.50f );
		makeValue( OutInCubic, 49, 0.50f );	makeValue( OutInCubic, 50, 0.50f );

		makeValue( OutInCubic, 51, 0.50f );	makeValue( OutInCubic, 52, 0.50f );
		makeValue( OutInCubic, 53, 0.50f );	makeValue( OutInCubic, 54, 0.50f );
		makeValue( OutInCubic, 55, 0.50f );	makeValue( OutInCubic, 56, 0.50f );
		makeValue( OutInCubic, 57, 0.50f );	makeValue( OutInCubic, 58, 0.50f );
		makeValue( OutInCubic, 59, 0.50f );	makeValue( OutInCubic, 60, 0.50f );

		makeValue( OutInCubic, 61, 0.50f );	makeValue( OutInCubic, 62, 0.51f );
		makeValue( OutInCubic, 63, 0.51f );	makeValue( OutInCubic, 64, 0.51f );
		makeValue( OutInCubic, 65, 0.51f );	makeValue( OutInCubic, 66, 0.51f );
		makeValue( OutInCubic, 67, 0.52f );	makeValue( OutInCubic, 68, 0.52f );
		makeValue( OutInCubic, 69, 0.52f );	makeValue( OutInCubic, 70, 0.53f );

		makeValue( OutInCubic, 71, 0.53f );	makeValue( OutInCubic, 72, 0.54f );
		makeValue( OutInCubic, 73, 0.54f );	makeValue( OutInCubic, 74, 0.55f );
		makeValue( OutInCubic, 75, 0.56f );	makeValue( OutInCubic, 76, 0.56f );
		makeValue( OutInCubic, 77, 0.57f );	makeValue( OutInCubic, 78, 0.58f );
		makeValue( OutInCubic, 79, 0.60f );	makeValue( OutInCubic, 80, 0.61f );

		makeValue( OutInCubic, 81, 0.62f );	makeValue( OutInCubic, 82, 0.63f );
		makeValue( OutInCubic, 83, 0.64f );	makeValue( OutInCubic, 84, 0.66f );
		makeValue( OutInCubic, 85, 0.67f );	makeValue( OutInCubic, 86, 0.69f );
		makeValue( OutInCubic, 87, 0.70f );	makeValue( OutInCubic, 88, 0.72f );
		makeValue( OutInCubic, 89, 0.74f );	makeValue( OutInCubic, 90, 0.75f );

		makeValue( OutInCubic, 91, 0.77f );	makeValue( OutInCubic, 92, 0.79f );
		makeValue( OutInCubic, 93, 0.81f );	makeValue( OutInCubic, 94, 0.83f );
		makeValue( OutInCubic, 95, 0.86f );	makeValue( OutInCubic, 96, 0.88f );
		makeValue( OutInCubic, 97, 0.91f );	makeValue( OutInCubic, 98, 0.93f );
		makeValue( OutInCubic, 99, 0.96f );	makeValue( OutInCubic, 100, 1.00f );
	}

	public void makeInQuart()
	{
		makeValue( InQuart, 0, 0.0f );

		makeValue( InQuart, 1, 0.00f );	makeValue( InQuart, 2, 0.00f );
		makeValue( InQuart, 3, 0.00f );	makeValue( InQuart, 4, 0.00f );
		makeValue( InQuart, 5, 0.00f );	makeValue( InQuart, 6, 0.00f );
		makeValue( InQuart, 7, 0.00f );	makeValue( InQuart, 8, 0.00f );
		makeValue( InQuart, 9, 0.00f );	makeValue( InQuart, 10, 0.00f );

		makeValue( InQuart, 11, 0.00f );	makeValue( InQuart, 12, 0.00f );
		makeValue( InQuart, 13, 0.00f );	makeValue( InQuart, 14, 0.00f );
		makeValue( InQuart, 15, 0.00f );	makeValue( InQuart, 16, 0.01f );
		makeValue( InQuart, 17, 0.01f );	makeValue( InQuart, 18, 0.01f );
		makeValue( InQuart, 19, 0.01f );	makeValue( InQuart, 20, 0.01f );

		makeValue( InQuart, 21, 0.02f );	makeValue( InQuart, 22, 0.02f );
		makeValue( InQuart, 23, 0.02f );	makeValue( InQuart, 24, 0.03f );
		makeValue( InQuart, 25, 0.03f );	makeValue( InQuart, 26, 0.04f );
		makeValue( InQuart, 27, 0.04f );	makeValue( InQuart, 28, 0.05f );
		makeValue( InQuart, 29, 0.06f );	makeValue( InQuart, 30, 0.07f );

		makeValue( InQuart, 31, 0.08f );	makeValue( InQuart, 32, 0.09f );
		makeValue( InQuart, 33, 0.10f );	makeValue( InQuart, 34, 0.11f );
		makeValue( InQuart, 35, 0.12f );	makeValue( InQuart, 36, 0.14f );
		makeValue( InQuart, 37, 0.15f );	makeValue( InQuart, 38, 0.17f );
		makeValue( InQuart, 39, 0.19f );	makeValue( InQuart, 40, 0.21f );

		makeValue( InQuart, 41, 0.23f );	makeValue( InQuart, 42, 0.25f );
		makeValue( InQuart, 43, 0.27f );	makeValue( InQuart, 44, 0.30f );
		makeValue( InQuart, 45, 0.33f );	makeValue( InQuart, 46, 0.36f );
		makeValue( InQuart, 47, 0.42f );	makeValue( InQuart, 48, 0.46f );
		makeValue( InQuart, 49, 0.49f );	makeValue( InQuart, 50, 0.53f );

		makeValue( InQuart, 51, 0.57f );	makeValue( InQuart, 52, 0.60f );
		makeValue( InQuart, 53, 0.63f );	makeValue( InQuart, 54, 0.66f );
		makeValue( InQuart, 55, 0.69f );	makeValue( InQuart, 56, 0.72f );
		makeValue( InQuart, 57, 0.74f );	makeValue( InQuart, 58, 0.77f );
		makeValue( InQuart, 59, 0.79f );	makeValue( InQuart, 60, 0.81f );

		makeValue( InQuart, 61, 0.83f );	makeValue( InQuart, 62, 0.84f );
		makeValue( InQuart, 63, 0.86f );	makeValue( InQuart, 64, 0.87f );
		makeValue( InQuart, 65, 0.89f );	makeValue( InQuart, 66, 0.90f );
		makeValue( InQuart, 67, 0.91f );	makeValue( InQuart, 68, 0.92f );
		makeValue( InQuart, 69, 0.93f );	makeValue( InQuart, 70, 0.94f );

		makeValue( InQuart, 71, 0.95f );	makeValue( InQuart, 72, 0.95f );
		makeValue( InQuart, 73, 0.96f );	makeValue( InQuart, 74, 0.96f );
		makeValue( InQuart, 75, 0.97f );	makeValue( InQuart, 76, 0.97f );
		makeValue( InQuart, 77, 0.98f );	makeValue( InQuart, 78, 0.98f );
		makeValue( InQuart, 79, 0.98f );	makeValue( InQuart, 80, 0.99f );

		makeValue( InQuart, 81, 0.99f );	makeValue( InQuart, 82, 0.99f );
		makeValue( InQuart, 83, 0.99f );	makeValue( InQuart, 84, 0.99f );
		makeValue( InQuart, 85, 1.00f );	makeValue( InQuart, 86, 1.00f );
		makeValue( InQuart, 87, 1.00f );	makeValue( InQuart, 88, 1.00f );
		makeValue( InQuart, 89, 1.00f );	makeValue( InQuart, 90, 1.00f );

		makeValue( InQuart, 91, 1.00f );	makeValue( InQuart, 92, 1.00f );
		makeValue( InQuart, 93, 1.00f );	makeValue( InQuart, 94, 1.00f );
		makeValue( InQuart, 95, 1.00f );	makeValue( InQuart, 96, 1.00f );
		makeValue( InQuart, 97, 1.00f );	makeValue( InQuart, 98, 1.00f );
		makeValue( InQuart, 99, 1.00f );	makeValue( InQuart, 100, 1.00f );
	}

	public void makeOutQuart()
	{
		makeValue( OutQuart, 0, 0.0f );

		makeValue( OutQuart, 1, 0.04f );	makeValue( OutQuart, 2, 0.08f );
		makeValue( OutQuart, 3, 0.11f );	makeValue( OutQuart, 4, 0.15f );
		makeValue( OutQuart, 5, 0.18f );	makeValue( OutQuart, 6, 0.22f );
		makeValue( OutQuart, 7, 0.25f );	makeValue( OutQuart, 8, 0.28f );
		makeValue( OutQuart, 9, 0.31f );	makeValue( OutQuart, 10, 0.34f );

		makeValue( OutQuart, 11, 0.37f );	makeValue( OutQuart, 12, 0.39f );
		makeValue( OutQuart, 13, 0.42f );	makeValue( OutQuart, 14, 0.44f );
		makeValue( OutQuart, 15, 0.47f );	makeValue( OutQuart, 16, 0.52f );
		makeValue( OutQuart, 17, 0.54f );	makeValue( OutQuart, 18, 0.56f );
		makeValue( OutQuart, 19, 0.58f );	makeValue( OutQuart, 20, 0.60f );

		makeValue( OutQuart, 21, 0.62f );	makeValue( OutQuart, 22, 0.64f );
		makeValue( OutQuart, 23, 0.66f );	makeValue( OutQuart, 24, 0.67f );
		makeValue( OutQuart, 25, 0.69f );	makeValue( OutQuart, 26, 0.71f );
		makeValue( OutQuart, 27, 0.72f );	makeValue( OutQuart, 28, 0.74f );
		makeValue( OutQuart, 29, 0.75f );	makeValue( OutQuart, 30, 0.76f );

		makeValue( OutQuart, 31, 0.78f );	makeValue( OutQuart, 32, 0.79f );
		makeValue( OutQuart, 33, 0.80f );	makeValue( OutQuart, 34, 0.81f );
		makeValue( OutQuart, 35, 0.82f );	makeValue( OutQuart, 36, 0.83f );
		makeValue( OutQuart, 37, 0.84f );	makeValue( OutQuart, 38, 0.85f );
		makeValue( OutQuart, 39, 0.86f );	makeValue( OutQuart, 40, 0.87f );

		makeValue( OutQuart, 41, 0.88f );	makeValue( OutQuart, 42, 0.89f );
		makeValue( OutQuart, 43, 0.89f );	makeValue( OutQuart, 44, 0.90f );
		makeValue( OutQuart, 45, 0.91f );	makeValue( OutQuart, 46, 0.91f );
		makeValue( OutQuart, 47, 0.92f );	makeValue( OutQuart, 48, 0.93f );
		makeValue( OutQuart, 49, 0.93f );	makeValue( OutQuart, 50, 0.94f );

		makeValue( OutQuart, 51, 0.94f );	makeValue( OutQuart, 52, 0.95f );
		makeValue( OutQuart, 53, 0.95f );	makeValue( OutQuart, 54, 0.95f );
		makeValue( OutQuart, 55, 0.96f );	makeValue( OutQuart, 56, 0.96f );
		makeValue( OutQuart, 57, 0.96f );	makeValue( OutQuart, 58, 0.97f );
		makeValue( OutQuart, 59, 0.97f );	makeValue( OutQuart, 60, 0.98f );

		makeValue( OutQuart, 61, 0.98f );	makeValue( OutQuart, 62, 0.98f );
		makeValue( OutQuart, 63, 0.98f );	makeValue( OutQuart, 64, 0.98f );
		makeValue( OutQuart, 65, 0.99f );	makeValue( OutQuart, 66, 0.99f );
		makeValue( OutQuart, 67, 0.99f );	makeValue( OutQuart, 68, 0.99f );
		makeValue( OutQuart, 69, 0.99f );	makeValue( OutQuart, 70, 0.99f );

		makeValue( OutQuart, 71, 0.99f );	makeValue( OutQuart, 72, 0.99f );
		makeValue( OutQuart, 73, 0.99f );	makeValue( OutQuart, 74, 1.00f );
		makeValue( OutQuart, 75, 1.00f );	makeValue( OutQuart, 76, 1.00f );
		makeValue( OutQuart, 77, 1.00f );	makeValue( OutQuart, 78, 1.00f );
		makeValue( OutQuart, 79, 1.00f );	makeValue( OutQuart, 80, 1.00f );

		makeValue( OutQuart, 81, 1.00f );	makeValue( OutQuart, 82, 1.00f );
		makeValue( OutQuart, 83, 1.00f );	makeValue( OutQuart, 84, 1.00f );
		makeValue( OutQuart, 85, 1.00f );	makeValue( OutQuart, 86, 1.00f );
		makeValue( OutQuart, 87, 1.00f );	makeValue( OutQuart, 88, 1.00f );
		makeValue( OutQuart, 89, 1.00f );	makeValue( OutQuart, 90, 1.00f );

		makeValue( OutQuart, 91, 1.00f );	makeValue( OutQuart, 92, 1.00f );
		makeValue( OutQuart, 93, 1.00f );	makeValue( OutQuart, 94, 1.00f );
		makeValue( OutQuart, 95, 1.00f );	makeValue( OutQuart, 96, 1.00f );
		makeValue( OutQuart, 97, 1.00f );	makeValue( OutQuart, 98, 1.00f );
		makeValue( OutQuart, 99, 1.00f );	makeValue( OutQuart, 100, 1.00f );
	}

	public void makeInOutQuart()
	{
		makeValue( InOutQuart, 0, 0.0f );

		makeValue( InOutQuart, 1, 0.00f );	makeValue( InOutQuart, 2, 0.00f );
		makeValue( InOutQuart, 3, 0.00f );	makeValue( InOutQuart, 4, 0.00f );
		makeValue( InOutQuart, 5, 0.00f );	makeValue( InOutQuart, 6, 0.00f );
		makeValue( InOutQuart, 7, 0.00f );	makeValue( InOutQuart, 8, 0.00f );
		makeValue( InOutQuart, 9, 0.00f );	makeValue( InOutQuart, 10, 0.00f );

		makeValue( InOutQuart, 11, 0.00f );	makeValue( InOutQuart, 12, 0.00f );
		makeValue( InOutQuart, 13, 0.00f );	makeValue( InOutQuart, 14, 0.00f );
		makeValue( InOutQuart, 15, 0.00f );	makeValue( InOutQuart, 16, 0.01f );
		makeValue( InOutQuart, 17, 0.01f );	makeValue( InOutQuart, 18, 0.01f );
		makeValue( InOutQuart, 19, 0.01f );	makeValue( InOutQuart, 20, 0.01f );

		makeValue( InOutQuart, 21, 0.02f );	makeValue( InOutQuart, 22, 0.02f );
		makeValue( InOutQuart, 23, 0.02f );	makeValue( InOutQuart, 24, 0.03f );
		makeValue( InOutQuart, 25, 0.03f );	makeValue( InOutQuart, 26, 0.04f );
		makeValue( InOutQuart, 27, 0.04f );	makeValue( InOutQuart, 28, 0.05f );
		makeValue( InOutQuart, 29, 0.06f );	makeValue( InOutQuart, 30, 0.07f );

		makeValue( InOutQuart, 31, 0.08f );	makeValue( InOutQuart, 32, 0.09f );
		makeValue( InOutQuart, 33, 0.10f );	makeValue( InOutQuart, 34, 0.11f );
		makeValue( InOutQuart, 35, 0.12f );	makeValue( InOutQuart, 36, 0.14f );
		makeValue( InOutQuart, 37, 0.15f );	makeValue( InOutQuart, 38, 0.17f );
		makeValue( InOutQuart, 39, 0.19f );	makeValue( InOutQuart, 40, 0.21f );

		makeValue( InOutQuart, 41, 0.23f );	makeValue( InOutQuart, 42, 0.25f );
		makeValue( InOutQuart, 43, 0.27f );	makeValue( InOutQuart, 44, 0.30f );
		makeValue( InOutQuart, 45, 0.33f );	makeValue( InOutQuart, 46, 0.36f );
		makeValue( InOutQuart, 47, 0.42f );	makeValue( InOutQuart, 48, 0.46f );
		makeValue( InOutQuart, 49, 0.49f );	makeValue( InOutQuart, 50, 0.53f );

		makeValue( InOutQuart, 51, 0.57f );	makeValue( InOutQuart, 52, 0.60f );
		makeValue( InOutQuart, 53, 0.63f );	makeValue( InOutQuart, 54, 0.66f );
		makeValue( InOutQuart, 55, 0.69f );	makeValue( InOutQuart, 56, 0.72f );
		makeValue( InOutQuart, 57, 0.74f );	makeValue( InOutQuart, 58, 0.77f );
		makeValue( InOutQuart, 59, 0.79f );	makeValue( InOutQuart, 60, 0.81f );

		makeValue( InOutQuart, 61, 0.83f );	makeValue( InOutQuart, 62, 0.84f );
		makeValue( InOutQuart, 63, 0.86f );	makeValue( InOutQuart, 64, 0.87f );
		makeValue( InOutQuart, 65, 0.89f );	makeValue( InOutQuart, 66, 0.90f );
		makeValue( InOutQuart, 67, 0.91f );	makeValue( InOutQuart, 68, 0.92f );
		makeValue( InOutQuart, 69, 0.93f );	makeValue( InOutQuart, 70, 0.94f );

		makeValue( InOutQuart, 71, 0.95f );	makeValue( InOutQuart, 72, 0.95f );
		makeValue( InOutQuart, 73, 0.96f );	makeValue( InOutQuart, 74, 0.96f );
		makeValue( InOutQuart, 75, 0.97f );	makeValue( InOutQuart, 76, 0.97f );
		makeValue( InOutQuart, 77, 0.98f );	makeValue( InOutQuart, 78, 0.98f );
		makeValue( InOutQuart, 79, 0.98f );	makeValue( InOutQuart, 80, 0.99f );

		makeValue( InOutQuart, 81, 0.99f );	makeValue( InOutQuart, 82, 0.99f );
		makeValue( InOutQuart, 83, 0.99f );	makeValue( InOutQuart, 84, 0.99f );
		makeValue( InOutQuart, 85, 1.00f );	makeValue( InOutQuart, 86, 1.00f );
		makeValue( InOutQuart, 87, 1.00f );	makeValue( InOutQuart, 88, 1.00f );
		makeValue( InOutQuart, 89, 1.00f );	makeValue( InOutQuart, 90, 1.00f );

		makeValue( InOutQuart, 91, 1.00f );	makeValue( InOutQuart, 92, 1.00f );
		makeValue( InOutQuart, 93, 1.00f );	makeValue( InOutQuart, 94, 1.00f );
		makeValue( InOutQuart, 95, 1.00f );	makeValue( InOutQuart, 96, 1.00f );
		makeValue( InOutQuart, 97, 1.00f );	makeValue( InOutQuart, 98, 1.00f );
		makeValue( InOutQuart, 99, 1.00f );	makeValue( InOutQuart, 100, 1.00f );
	}

	public void makeOutInQuart()
	{
		makeValue( OutInQuart, 0, 0.0f );

		makeValue( OutInQuart, 1, 0.08f );	makeValue( OutInQuart, 2, 0.11f );
		makeValue( OutInQuart, 3, 0.14f );	makeValue( OutInQuart, 4, 0.17f );
		makeValue( OutInQuart, 5, 0.20f );	makeValue( OutInQuart, 6, 0.22f );
		makeValue( OutInQuart, 7, 0.25f );	makeValue( OutInQuart, 8, 0.27f );
		makeValue( OutInQuart, 9, 0.29f );	makeValue( OutInQuart, 10, 0.31f );

		makeValue( OutInQuart, 11, 0.33f );	makeValue( OutInQuart, 12, 0.34f );
		makeValue( OutInQuart, 13, 0.36f );	makeValue( OutInQuart, 14, 0.37f );
		makeValue( OutInQuart, 15, 0.39f );	makeValue( OutInQuart, 16, 0.40f );
		makeValue( OutInQuart, 17, 0.41f );	makeValue( OutInQuart, 18, 0.42f );
		makeValue( OutInQuart, 19, 0.43f );	makeValue( OutInQuart, 20, 0.44f );

		makeValue( OutInQuart, 21, 0.45f );	makeValue( OutInQuart, 22, 0.45f );
		makeValue( OutInQuart, 23, 0.46f );	makeValue( OutInQuart, 24, 0.47f );
		makeValue( OutInQuart, 25, 0.47f );	makeValue( OutInQuart, 26, 0.47f );
		makeValue( OutInQuart, 27, 0.48f );	makeValue( OutInQuart, 28, 0.48f );
		makeValue( OutInQuart, 29, 0.49f );	makeValue( OutInQuart, 30, 0.49f );

		makeValue( OutInQuart, 31, 0.49f );	makeValue( OutInQuart, 32, 0.49f );
		makeValue( OutInQuart, 33, 0.49f );	makeValue( OutInQuart, 34, 0.49f );
		makeValue( OutInQuart, 35, 0.50f );	makeValue( OutInQuart, 36, 0.50f );
		makeValue( OutInQuart, 37, 0.50f );	makeValue( OutInQuart, 38, 0.50f );
		makeValue( OutInQuart, 39, 0.50f );	makeValue( OutInQuart, 40, 0.50f );

		makeValue( OutInQuart, 41, 0.50f );	makeValue( OutInQuart, 42, 0.50f );
		makeValue( OutInQuart, 43, 0.50f );	makeValue( OutInQuart, 44, 0.50f );
		makeValue( OutInQuart, 45, 0.50f );	makeValue( OutInQuart, 46, 0.50f );
		makeValue( OutInQuart, 47, 0.50f );	makeValue( OutInQuart, 48, 0.50f );
		makeValue( OutInQuart, 49, 0.50f );	makeValue( OutInQuart, 50, 0.50f );

		makeValue( OutInQuart, 51, 0.50f );	makeValue( OutInQuart, 52, 0.50f );
		makeValue( OutInQuart, 53, 0.50f );	makeValue( OutInQuart, 54, 0.50f );
		makeValue( OutInQuart, 55, 0.50f );	makeValue( OutInQuart, 56, 0.50f );
		makeValue( OutInQuart, 57, 0.50f );	makeValue( OutInQuart, 58, 0.50f );
		makeValue( OutInQuart, 59, 0.50f );	makeValue( OutInQuart, 60, 0.50f );

		makeValue( OutInQuart, 61, 0.50f );	makeValue( OutInQuart, 62, 0.50f );
		makeValue( OutInQuart, 63, 0.50f );	makeValue( OutInQuart, 64, 0.50f );
		makeValue( OutInQuart, 65, 0.50f );	makeValue( OutInQuart, 66, 0.51f );
		makeValue( OutInQuart, 67, 0.51f );	makeValue( OutInQuart, 68, 0.51f );
		makeValue( OutInQuart, 69, 0.51f );	makeValue( OutInQuart, 70, 0.51f );

		makeValue( OutInQuart, 71, 0.52f );	makeValue( OutInQuart, 72, 0.52f );
		makeValue( OutInQuart, 73, 0.52f );	makeValue( OutInQuart, 74, 0.53f );
		makeValue( OutInQuart, 75, 0.53f );	makeValue( OutInQuart, 76, 0.54f );
		makeValue( OutInQuart, 77, 0.54f );	makeValue( OutInQuart, 78, 0.55f );
		makeValue( OutInQuart, 79, 0.56f );	makeValue( OutInQuart, 80, 0.56f );

		makeValue( OutInQuart, 81, 0.57f );	makeValue( OutInQuart, 82, 0.58f );
		makeValue( OutInQuart, 83, 0.59f );	makeValue( OutInQuart, 84, 0.62f );
		makeValue( OutInQuart, 85, 0.63f );	makeValue( OutInQuart, 86, 0.64f );
		makeValue( OutInQuart, 87, 0.66f );	makeValue( OutInQuart, 88, 0.68f );
		makeValue( OutInQuart, 89, 0.70f );	makeValue( OutInQuart, 90, 0.72f );

		makeValue( OutInQuart, 91, 0.74f );	makeValue( OutInQuart, 92, 0.76f );
		makeValue( OutInQuart, 93, 0.79f );	makeValue( OutInQuart, 94, 0.81f );
		makeValue( OutInQuart, 95, 0.84f );	makeValue( OutInQuart, 96, 0.87f );
		makeValue( OutInQuart, 97, 0.91f );	makeValue( OutInQuart, 98, 0.94f );
		makeValue( OutInQuart, 99, 0.98f );	makeValue( OutInQuart, 100, 1.00f );
	}

	public void makeInQuint()
	{
		makeValue( InQuint, 0, 0.0f );

		makeValue( InQuint, 1, 0.00f );	makeValue( InQuint, 2, 0.00f );
		makeValue( InQuint, 3, 0.00f );	makeValue( InQuint, 4, 0.00f );
		makeValue( InQuint, 5, 0.00f );	makeValue( InQuint, 6, 0.00f );
		makeValue( InQuint, 7, 0.00f );	makeValue( InQuint, 8, 0.00f );
		makeValue( InQuint, 9, 0.00f );	makeValue( InQuint, 10, 0.00f );

		makeValue( InQuint, 11, 0.00f );	makeValue( InQuint, 12, 0.00f );
		makeValue( InQuint, 13, 0.00f );	makeValue( InQuint, 14, 0.00f );
		makeValue( InQuint, 15, 0.00f );	makeValue( InQuint, 16, 0.00f );
		makeValue( InQuint, 17, 0.00f );	makeValue( InQuint, 18, 0.00f );
		makeValue( InQuint, 19, 0.00f );	makeValue( InQuint, 20, 0.00f );

		makeValue( InQuint, 21, 0.00f );	makeValue( InQuint, 22, 0.00f );
		makeValue( InQuint, 23, 0.00f );	makeValue( InQuint, 24, 0.00f );
		makeValue( InQuint, 25, 0.00f );	makeValue( InQuint, 26, 0.00f );
		makeValue( InQuint, 27, 0.00f );	makeValue( InQuint, 28, 0.00f );
		makeValue( InQuint, 29, 0.00f );	makeValue( InQuint, 30, 0.00f );

		makeValue( InQuint, 31, 0.00f );	makeValue( InQuint, 32, 0.00f );
		makeValue( InQuint, 33, 0.00f );	makeValue( InQuint, 34, 0.00f );
		makeValue( InQuint, 35, 0.01f );	makeValue( InQuint, 36, 0.01f );
		makeValue( InQuint, 37, 0.01f );	makeValue( InQuint, 38, 0.01f );
		makeValue( InQuint, 39, 0.01f );	makeValue( InQuint, 40, 0.01f );

		makeValue( InQuint, 41, 0.01f );	makeValue( InQuint, 42, 0.01f );
		makeValue( InQuint, 43, 0.01f );	makeValue( InQuint, 44, 0.02f );
		makeValue( InQuint, 45, 0.02f );	makeValue( InQuint, 46, 0.02f );
		makeValue( InQuint, 47, 0.02f );	makeValue( InQuint, 48, 0.02f );
		makeValue( InQuint, 49, 0.03f );	makeValue( InQuint, 50, 0.03f );

		makeValue( InQuint, 51, 0.03f );	makeValue( InQuint, 52, 0.04f );
		makeValue( InQuint, 53, 0.04f );	makeValue( InQuint, 54, 0.05f );
		makeValue( InQuint, 55, 0.05f );	makeValue( InQuint, 56, 0.06f );
		makeValue( InQuint, 57, 0.06f );	makeValue( InQuint, 58, 0.07f );
		makeValue( InQuint, 59, 0.07f );	makeValue( InQuint, 60, 0.08f );

		makeValue( InQuint, 61, 0.09f );	makeValue( InQuint, 62, 0.10f );
		makeValue( InQuint, 63, 0.10f );	makeValue( InQuint, 64, 0.11f );
		makeValue( InQuint, 65, 0.12f );	makeValue( InQuint, 66, 0.13f );
		makeValue( InQuint, 67, 0.14f );	makeValue( InQuint, 68, 0.15f );
		makeValue( InQuint, 69, 0.16f );	makeValue( InQuint, 70, 0.17f );

		makeValue( InQuint, 71, 0.18f );	makeValue( InQuint, 72, 0.20f );
		makeValue( InQuint, 73, 0.21f );	makeValue( InQuint, 74, 0.22f );
		makeValue( InQuint, 75, 0.24f );	makeValue( InQuint, 76, 0.26f );
		makeValue( InQuint, 77, 0.27f );	makeValue( InQuint, 78, 0.29f );
		makeValue( InQuint, 79, 0.31f );	makeValue( InQuint, 80, 0.33f );

		makeValue( InQuint, 81, 0.35f );	makeValue( InQuint, 82, 0.37f );
		makeValue( InQuint, 83, 0.39f );	makeValue( InQuint, 84, 0.42f );
		makeValue( InQuint, 85, 0.44f );	makeValue( InQuint, 86, 0.47f );
		makeValue( InQuint, 87, 0.49f );	makeValue( InQuint, 88, 0.52f );
		makeValue( InQuint, 89, 0.55f );	makeValue( InQuint, 90, 0.58f );

		makeValue( InQuint, 91, 0.62f );	makeValue( InQuint, 92, 0.65f );
		makeValue( InQuint, 93, 0.69f );	makeValue( InQuint, 94, 0.76f );
		makeValue( InQuint, 95, 0.80f );	makeValue( InQuint, 96, 0.84f );
		makeValue( InQuint, 97, 0.89f );	makeValue( InQuint, 98, 0.93f );
		makeValue( InQuint, 99, 0.98f );	makeValue( InQuint, 100, 1.00f );
	}

	public void makeOutQuint()
	{
		makeValue( OutQuint, 0, 0.0f );

		makeValue( OutQuint, 1, 0.05f );	makeValue( OutQuint, 2, 0.10f );
		makeValue( OutQuint, 3, 0.14f );	makeValue( OutQuint, 4, 0.18f );
		makeValue( OutQuint, 5, 0.22f );	makeValue( OutQuint, 6, 0.26f );
		makeValue( OutQuint, 7, 0.30f );	makeValue( OutQuint, 8, 0.33f );
		makeValue( OutQuint, 9, 0.37f );	makeValue( OutQuint, 10, 0.40f );

		makeValue( OutQuint, 11, 0.43f );	makeValue( OutQuint, 12, 0.46f );
		makeValue( OutQuint, 13, 0.49f );	makeValue( OutQuint, 14, 0.52f );
		makeValue( OutQuint, 15, 0.55f );	makeValue( OutQuint, 16, 0.57f );
		makeValue( OutQuint, 17, 0.60f );	makeValue( OutQuint, 18, 0.62f );
		makeValue( OutQuint, 19, 0.64f );	makeValue( OutQuint, 20, 0.66f );

		makeValue( OutQuint, 21, 0.68f );	makeValue( OutQuint, 22, 0.70f );
		makeValue( OutQuint, 23, 0.72f );	makeValue( OutQuint, 24, 0.74f );
		makeValue( OutQuint, 25, 0.75f );	makeValue( OutQuint, 26, 0.77f );
		makeValue( OutQuint, 27, 0.78f );	makeValue( OutQuint, 28, 0.80f );
		makeValue( OutQuint, 29, 0.81f );	makeValue( OutQuint, 30, 0.82f );

		makeValue( OutQuint, 31, 0.84f );	makeValue( OutQuint, 32, 0.85f );
		makeValue( OutQuint, 33, 0.86f );	makeValue( OutQuint, 34, 0.88f );
		makeValue( OutQuint, 35, 0.89f );	makeValue( OutQuint, 36, 0.89f );
		makeValue( OutQuint, 37, 0.90f );	makeValue( OutQuint, 38, 0.91f );
		makeValue( OutQuint, 39, 0.92f );	makeValue( OutQuint, 40, 0.92f );

		makeValue( OutQuint, 41, 0.93f );	makeValue( OutQuint, 42, 0.93f );
		makeValue( OutQuint, 43, 0.94f );	makeValue( OutQuint, 44, 0.94f );
		makeValue( OutQuint, 45, 0.95f );	makeValue( OutQuint, 46, 0.95f );
		makeValue( OutQuint, 47, 0.96f );	makeValue( OutQuint, 48, 0.96f );
		makeValue( OutQuint, 49, 0.97f );	makeValue( OutQuint, 50, 0.97f );

		makeValue( OutQuint, 51, 0.97f );	makeValue( OutQuint, 52, 0.97f );
		makeValue( OutQuint, 53, 0.98f );	makeValue( OutQuint, 54, 0.98f );
		makeValue( OutQuint, 55, 0.98f );	makeValue( OutQuint, 56, 0.98f );
		makeValue( OutQuint, 57, 0.98f );	makeValue( OutQuint, 58, 0.99f );
		makeValue( OutQuint, 59, 0.99f );	makeValue( OutQuint, 60, 0.99f );

		makeValue( OutQuint, 61, 0.99f );	makeValue( OutQuint, 62, 0.99f );
		makeValue( OutQuint, 63, 0.99f );	makeValue( OutQuint, 64, 0.99f );
		makeValue( OutQuint, 65, 0.99f );	makeValue( OutQuint, 66, 1.00f );
		makeValue( OutQuint, 67, 1.00f );	makeValue( OutQuint, 68, 1.00f );
		makeValue( OutQuint, 69, 1.00f );	makeValue( OutQuint, 70, 1.00f );

		makeValue( OutQuint, 71, 1.00f );	makeValue( OutQuint, 72, 1.00f );
		makeValue( OutQuint, 73, 1.00f );	makeValue( OutQuint, 74, 1.00f );
		makeValue( OutQuint, 75, 1.00f );	makeValue( OutQuint, 76, 1.00f );
		makeValue( OutQuint, 77, 1.00f );	makeValue( OutQuint, 78, 1.00f );
		makeValue( OutQuint, 79, 1.00f );	makeValue( OutQuint, 80, 1.00f );

		makeValue( OutQuint, 81, 1.00f );	makeValue( OutQuint, 82, 1.00f );
		makeValue( OutQuint, 83, 1.00f );	makeValue( OutQuint, 84, 1.00f );
		makeValue( OutQuint, 85, 1.00f );	makeValue( OutQuint, 86, 1.00f );
		makeValue( OutQuint, 87, 1.00f );	makeValue( OutQuint, 88, 1.00f );
		makeValue( OutQuint, 89, 1.00f );	makeValue( OutQuint, 90, 1.00f );

		makeValue( OutQuint, 91, 1.00f );	makeValue( OutQuint, 92, 1.00f );
		makeValue( OutQuint, 93, 1.00f );	makeValue( OutQuint, 94, 1.00f );
		makeValue( OutQuint, 95, 1.00f );	makeValue( OutQuint, 96, 1.00f );
		makeValue( OutQuint, 97, 1.00f );	makeValue( OutQuint, 98, 1.00f );
		makeValue( OutQuint, 99, 1.00f );	makeValue( OutQuint, 100, 1.00f );
	}

	public void makeInOutQuint()
	{
		makeValue( InOutQuint, 0, 0.00f );

		makeValue( InOutQuint, 1, 0.00f );	makeValue( InOutQuint, 2, 0.00f );
		makeValue( InOutQuint, 3, 0.00f );	makeValue( InOutQuint, 4, 0.00f );
		makeValue( InOutQuint, 5, 0.00f );	makeValue( InOutQuint, 6, 0.00f );
		makeValue( InOutQuint, 7, 0.00f );	makeValue( InOutQuint, 8, 0.00f );
		makeValue( InOutQuint, 9, 0.00f );	makeValue( InOutQuint, 10, 0.00f );

		makeValue( InOutQuint, 11, 0.00f );	makeValue( InOutQuint, 12, 0.00f );
		makeValue( InOutQuint, 13, 0.00f );	makeValue( InOutQuint, 14, 0.00f );
		makeValue( InOutQuint, 15, 0.00f );	makeValue( InOutQuint, 16, 0.00f );
		makeValue( InOutQuint, 17, 0.00f );	makeValue( InOutQuint, 18, 0.00f );
		makeValue( InOutQuint, 19, 0.00f );	makeValue( InOutQuint, 20, 0.00f );

		makeValue( InOutQuint, 21, 0.01f );	makeValue( InOutQuint, 22, 0.01f );
		makeValue( InOutQuint, 23, 0.01f );	makeValue( InOutQuint, 24, 0.01f );
		makeValue( InOutQuint, 25, 0.01f );	makeValue( InOutQuint, 26, 0.02f );
		makeValue( InOutQuint, 27, 0.02f );	makeValue( InOutQuint, 28, 0.02f );
		makeValue( InOutQuint, 29, 0.03f );	makeValue( InOutQuint, 30, 0.03f );

		makeValue( InOutQuint, 31, 0.04f );	makeValue( InOutQuint, 32, 0.06f );
		makeValue( InOutQuint, 33, 0.06f );	makeValue( InOutQuint, 34, 0.07f );
		makeValue( InOutQuint, 35, 0.09f );	makeValue( InOutQuint, 36, 0.10f );
		makeValue( InOutQuint, 37, 0.11f );	makeValue( InOutQuint, 38, 0.13f );
		makeValue( InOutQuint, 39, 0.15f );	makeValue( InOutQuint, 40, 0.17f );

		makeValue( InOutQuint, 41, 0.19f );	makeValue( InOutQuint, 42, 0.21f );
		makeValue( InOutQuint, 43, 0.23f );	makeValue( InOutQuint, 44, 0.26f );
		makeValue( InOutQuint, 45, 0.29f );	makeValue( InOutQuint, 46, 0.33f );
		makeValue( InOutQuint, 47, 0.36f );	makeValue( InOutQuint, 48, 0.40f );
		makeValue( InOutQuint, 49, 0.44f );	makeValue( InOutQuint, 50, 0.49f );

		makeValue( InOutQuint, 51, 0.54f );	makeValue( InOutQuint, 52, 0.58f );
		makeValue( InOutQuint, 53, 0.62f );	makeValue( InOutQuint, 54, 0.66f );
		makeValue( InOutQuint, 55, 0.69f );	makeValue( InOutQuint, 56, 0.73f );
		makeValue( InOutQuint, 57, 0.75f );	makeValue( InOutQuint, 58, 0.78f );
		makeValue( InOutQuint, 59, 0.80f );	makeValue( InOutQuint, 60, 0.83f );

		makeValue( InOutQuint, 61, 0.85f );	makeValue( InOutQuint, 62, 0.86f );
		makeValue( InOutQuint, 63, 0.88f );	makeValue( InOutQuint, 64, 0.90f );
		makeValue( InOutQuint, 65, 0.91f );	makeValue( InOutQuint, 66, 0.92f );
		makeValue( InOutQuint, 67, 0.93f );	makeValue( InOutQuint, 68, 0.94f );
		makeValue( InOutQuint, 69, 0.95f );	makeValue( InOutQuint, 70, 0.96f );

		makeValue( InOutQuint, 71, 0.96f );	makeValue( InOutQuint, 72, 0.97f );
		makeValue( InOutQuint, 73, 0.98f );	makeValue( InOutQuint, 74, 0.98f );
		makeValue( InOutQuint, 75, 0.98f );	makeValue( InOutQuint, 76, 0.99f );
		makeValue( InOutQuint, 77, 0.99f );	makeValue( InOutQuint, 78, 0.99f );
		makeValue( InOutQuint, 79, 0.99f );	makeValue( InOutQuint, 80, 0.99f );

		makeValue( InOutQuint, 81, 1.00f );	makeValue( InOutQuint, 82, 1.00f );
		makeValue( InOutQuint, 83, 1.00f );	makeValue( InOutQuint, 84, 1.00f );
		makeValue( InOutQuint, 85, 1.00f );	makeValue( InOutQuint, 86, 1.00f );
		makeValue( InOutQuint, 87, 1.00f );	makeValue( InOutQuint, 88, 1.00f );
		makeValue( InOutQuint, 89, 1.00f );	makeValue( InOutQuint, 90, 1.00f );

		makeValue( InOutQuint, 91, 1.00f );	makeValue( InOutQuint, 92, 1.00f );
		makeValue( InOutQuint, 93, 1.00f );	makeValue( InOutQuint, 94, 1.00f );
		makeValue( InOutQuint, 95, 1.00f );	makeValue( InOutQuint, 96, 1.00f );
		makeValue( InOutQuint, 97, 1.00f );	makeValue( InOutQuint, 98, 1.00f );
		makeValue( InOutQuint, 99, 1.00f );	makeValue( InOutQuint, 100, 1.00f );
	}

	public void makeOutInQuint()
	{
		makeValue( OutInQuint, 0, 0.00f );

		makeValue( OutInQuint, 1, 0.05f );	makeValue( OutInQuint, 2, 0.09f );
		makeValue( OutInQuint, 3, 0.13f );	makeValue( OutInQuint, 4, 0.17f );
		makeValue( OutInQuint, 5, 0.20f );	makeValue( OutInQuint, 6, 0.23f );
		makeValue( OutInQuint, 7, 0.26f );	makeValue( OutInQuint, 8, 0.29f );
		makeValue( OutInQuint, 9, 0.31f );	makeValue( OutInQuint, 10, 0.33f );

		makeValue( OutInQuint, 11, 0.35f );	makeValue( OutInQuint, 12, 0.37f );
		makeValue( OutInQuint, 13, 0.38f );	makeValue( OutInQuint, 14, 0.40f );
		makeValue( OutInQuint, 15, 0.41f );	makeValue( OutInQuint, 16, 0.42f );
		makeValue( OutInQuint, 17, 0.43f );	makeValue( OutInQuint, 18, 0.44f );
		makeValue( OutInQuint, 19, 0.45f );	makeValue( OutInQuint, 20, 0.46f );

		makeValue( OutInQuint, 21, 0.46f );	makeValue( OutInQuint, 22, 0.47f );
		makeValue( OutInQuint, 23, 0.47f );	makeValue( OutInQuint, 24, 0.48f );
		makeValue( OutInQuint, 25, 0.48f );	makeValue( OutInQuint, 26, 0.49f );
		makeValue( OutInQuint, 27, 0.49f );	makeValue( OutInQuint, 28, 0.49f );
		makeValue( OutInQuint, 29, 0.49f );	makeValue( OutInQuint, 30, 0.50f );

		makeValue( OutInQuint, 31, 0.50f );	makeValue( OutInQuint, 32, 0.50f );
		makeValue( OutInQuint, 33, 0.50f );	makeValue( OutInQuint, 34, 0.50f );
		makeValue( OutInQuint, 35, 0.50f );	makeValue( OutInQuint, 36, 0.50f );
		makeValue( OutInQuint, 37, 0.50f );	makeValue( OutInQuint, 38, 0.50f );
		makeValue( OutInQuint, 39, 0.50f );	makeValue( OutInQuint, 40, 0.50f );

		makeValue( OutInQuint, 41, 0.50f );	makeValue( OutInQuint, 42, 0.50f );
		makeValue( OutInQuint, 43, 0.50f );	makeValue( OutInQuint, 44, 0.50f );
		makeValue( OutInQuint, 45, 0.50f );	makeValue( OutInQuint, 46, 0.50f );
		makeValue( OutInQuint, 47, 0.50f );	makeValue( OutInQuint, 48, 0.50f );
		makeValue( OutInQuint, 49, 0.50f );	makeValue( OutInQuint, 50, 0.50f );

		makeValue( OutInQuint, 51, 0.50f );	makeValue( OutInQuint, 52, 0.50f );
		makeValue( OutInQuint, 53, 0.50f );	makeValue( OutInQuint, 54, 0.50f );
		makeValue( OutInQuint, 55, 0.50f );	makeValue( OutInQuint, 56, 0.50f );
		makeValue( OutInQuint, 57, 0.50f );	makeValue( OutInQuint, 58, 0.50f );
		makeValue( OutInQuint, 59, 0.50f );	makeValue( OutInQuint, 60, 0.50f );

		makeValue( OutInQuint, 61, 0.50f );	makeValue( OutInQuint, 62, 0.50f );
		makeValue( OutInQuint, 63, 0.50f );	makeValue( OutInQuint, 64, 0.50f );
		makeValue( OutInQuint, 65, 0.50f );	makeValue( OutInQuint, 66, 0.50f );
		makeValue( OutInQuint, 67, 0.50f );	makeValue( OutInQuint, 68, 0.50f );
		makeValue( OutInQuint, 69, 0.50f );	makeValue( OutInQuint, 70, 0.50f );

		makeValue( OutInQuint, 71, 0.51f );	makeValue( OutInQuint, 72, 0.51f );
		makeValue( OutInQuint, 73, 0.51f );	makeValue( OutInQuint, 74, 0.51f );
		makeValue( OutInQuint, 75, 0.52f );	makeValue( OutInQuint, 76, 0.52f );
		makeValue( OutInQuint, 77, 0.52f );	makeValue( OutInQuint, 78, 0.53f );
		makeValue( OutInQuint, 79, 0.53f );	makeValue( OutInQuint, 80, 0.54f );

		makeValue( OutInQuint, 81, 0.55f );	makeValue( OutInQuint, 82, 0.55f );
		makeValue( OutInQuint, 83, 0.56f );	makeValue( OutInQuint, 84, 0.57f );
		makeValue( OutInQuint, 85, 0.58f );	makeValue( OutInQuint, 86, 0.60f );
		makeValue( OutInQuint, 87, 0.61f );	makeValue( OutInQuint, 88, 0.62f );
		makeValue( OutInQuint, 89, 0.64f );	makeValue( OutInQuint, 90, 0.66f );

		makeValue( OutInQuint, 91, 0.68f );	makeValue( OutInQuint, 92, 0.70f );
		makeValue( OutInQuint, 93, 0.73f );	makeValue( OutInQuint, 94, 0.76f );
		makeValue( OutInQuint, 95, 0.79f );	makeValue( OutInQuint, 96, 0.82f );
		makeValue( OutInQuint, 97, 0.85f );	makeValue( OutInQuint, 98, 0.89f );
		makeValue( OutInQuint, 99, 0.93f );	makeValue( OutInQuint, 100, 1.0f );
	}

	public void makeInSine()
	{
		makeValue( InSine, 0, 0.00f );

		makeValue( InSine, 1, 0.00f );	makeValue( InSine, 2, 0.00f );
		makeValue( InSine, 3, 0.00f );	makeValue( InSine, 4, 0.00f );
		makeValue( InSine, 5, 0.00f );	makeValue( InSine, 6, 0.00f );
		makeValue( InSine, 7, 0.01f );	makeValue( InSine, 8, 0.01f );
		makeValue( InSine, 9, 0.01f );	makeValue( InSine, 10, 0.01f );

		makeValue( InSine, 11, 0.01f );	makeValue( InSine, 12, 0.02f );
		makeValue( InSine, 13, 0.02f );	makeValue( InSine, 14, 0.02f );
		makeValue( InSine, 15, 0.03f );	makeValue( InSine, 16, 0.03f );
		makeValue( InSine, 17, 0.03f );	makeValue( InSine, 18, 0.04f );
		makeValue( InSine, 19, 0.04f );	makeValue( InSine, 20, 0.05f );

		makeValue( InSine, 21, 0.05f );	makeValue( InSine, 22, 0.06f );
		makeValue( InSine, 23, 0.06f );	makeValue( InSine, 24, 0.07f );
		makeValue( InSine, 25, 0.07f );	makeValue( InSine, 26, 0.08f );
		makeValue( InSine, 27, 0.09f );	makeValue( InSine, 28, 0.10f );
		makeValue( InSine, 29, 0.10f );	makeValue( InSine, 30, 0.11f );

		makeValue( InSine, 31, 0.12f );	makeValue( InSine, 32, 0.13f );
		makeValue( InSine, 33, 0.13f );	makeValue( InSine, 34, 0.14f );
		makeValue( InSine, 35, 0.15f );	makeValue( InSine, 36, 0.16f );
		makeValue( InSine, 37, 0.17f );	makeValue( InSine, 38, 0.17f );
		makeValue( InSine, 39, 0.18f );	makeValue( InSine, 40, 0.19f );

		makeValue( InSine, 41, 0.20f );	makeValue( InSine, 42, 0.21f );
		makeValue( InSine, 43, 0.22f );	makeValue( InSine, 44, 0.23f );
		makeValue( InSine, 45, 0.24f );	makeValue( InSine, 46, 0.25f );
		makeValue( InSine, 47, 0.26f );	makeValue( InSine, 48, 0.27f );
		makeValue( InSine, 49, 0.28f );	makeValue( InSine, 50, 0.29f );

		makeValue( InSine, 51, 0.30f );	makeValue( InSine, 52, 0.31f );
		makeValue( InSine, 53, 0.32f );	makeValue( InSine, 54, 0.34f );
		makeValue( InSine, 55, 0.35f );	makeValue( InSine, 56, 0.36f );
		makeValue( InSine, 57, 0.37f );	makeValue( InSine, 58, 0.38f );
		makeValue( InSine, 59, 0.39f );	makeValue( InSine, 60, 0.41f );

		makeValue( InSine, 61, 0.42f );	makeValue( InSine, 62, 0.43f );
		makeValue( InSine, 63, 0.44f );	makeValue( InSine, 64, 0.46f );
		makeValue( InSine, 65, 0.47f );	makeValue( InSine, 66, 0.48f );
		makeValue( InSine, 67, 0.50f );	makeValue( InSine, 68, 0.52f );
		makeValue( InSine, 69, 0.54f );	makeValue( InSine, 70, 0.55f );

		makeValue( InSine, 71, 0.56f );	makeValue( InSine, 72, 0.58f );
		makeValue( InSine, 73, 0.59f );	makeValue( InSine, 74, 0.61f );
		makeValue( InSine, 75, 0.62f );	makeValue( InSine, 76, 0.63f );
		makeValue( InSine, 77, 0.65f );	makeValue( InSine, 78, 0.66f );
		makeValue( InSine, 79, 0.68f );	makeValue( InSine, 80, 0.69f );

		makeValue( InSine, 81, 0.71f );	makeValue( InSine, 82, 0.72f );
		makeValue( InSine, 83, 0.74f );	makeValue( InSine, 84, 0.75f );
		makeValue( InSine, 85, 0.77f );	makeValue( InSine, 86, 0.78f );
		makeValue( InSine, 87, 0.80f );	makeValue( InSine, 88, 0.81f );
		makeValue( InSine, 89, 0.83f );	makeValue( InSine, 90, 0.84f );

		makeValue( InSine, 91, 0.86f );	makeValue( InSine, 92, 0.87f );
		makeValue( InSine, 93, 0.89f );	makeValue( InSine, 94, 0.90f );
		makeValue( InSine, 95, 0.92f );	makeValue( InSine, 96, 0.93f );
		makeValue( InSine, 97, 0.95f );	makeValue( InSine, 98, 0.96f );
		makeValue( InSine, 99, 0.98f );	makeValue( InSine, 100, 1.00f );
	}

	public void makeOutSine()
	{
		makeValue( OutSine, 0, 0.00f );

		makeValue( OutSine, 1, 0.02f );	makeValue( OutSine, 2, 0.03f );
		makeValue( OutSine, 3, 0.05f );	makeValue( OutSine, 4, 0.06f );
		makeValue( OutSine, 5, 0.08f );	makeValue( OutSine, 6, 0.09f );
		makeValue( OutSine, 7, 0.11f );	makeValue( OutSine, 8, 0.14f );
		makeValue( OutSine, 9, 0.15f );	makeValue( OutSine, 10, 0.17f );

		makeValue( OutSine, 11, 0.18f );	makeValue( OutSine, 12, 0.20f );
		makeValue( OutSine, 13, 0.21f );	makeValue( OutSine, 14, 0.23f );
		makeValue( OutSine, 15, 0.24f );	makeValue( OutSine, 16, 0.26f );
		makeValue( OutSine, 17, 0.27f );	makeValue( OutSine, 18, 0.29f );
		makeValue( OutSine, 19, 0.30f );	makeValue( OutSine, 20, 0.32f );

		makeValue( OutSine, 21, 0.33f );	makeValue( OutSine, 22, 0.35f );
		makeValue( OutSine, 23, 0.36f );	makeValue( OutSine, 24, 0.37f );
		makeValue( OutSine, 25, 0.39f );	makeValue( OutSine, 26, 0.40f );
		makeValue( OutSine, 27, 0.42f );	makeValue( OutSine, 28, 0.43f );
		makeValue( OutSine, 29, 0.44f );	makeValue( OutSine, 30, 0.46f );

		makeValue( OutSine, 31, 0.47f );	makeValue( OutSine, 32, 0.49f );
		makeValue( OutSine, 33, 0.50f );	makeValue( OutSine, 34, 0.51f );
		makeValue( OutSine, 35, 0.53f );	makeValue( OutSine, 36, 0.54f );
		makeValue( OutSine, 37, 0.55f );	makeValue( OutSine, 38, 0.56f );
		makeValue( OutSine, 39, 0.58f );	makeValue( OutSine, 40, 0.59f );

		makeValue( OutSine, 41, 0.60f );	makeValue( OutSine, 42, 0.61f );
		makeValue( OutSine, 43, 0.63f );	makeValue( OutSine, 44, 0.64f );
		makeValue( OutSine, 45, 0.65f );	makeValue( OutSine, 46, 0.66f );
		makeValue( OutSine, 47, 0.67f );	makeValue( OutSine, 48, 0.68f );
		makeValue( OutSine, 49, 0.69f );	makeValue( OutSine, 50, 0.72f );

		makeValue( OutSine, 51, 0.73f );	makeValue( OutSine, 52, 0.74f );
		makeValue( OutSine, 53, 0.75f );	makeValue( OutSine, 54, 0.76f );
		makeValue( OutSine, 55, 0.77f );	makeValue( OutSine, 56, 0.78f );
		makeValue( OutSine, 57, 0.79f );	makeValue( OutSine, 58, 0.80f );
		makeValue( OutSine, 59, 0.80f );	makeValue( OutSine, 60, 0.81f );

		makeValue( OutSine, 61, 0.82f );	makeValue( OutSine, 62, 0.83f );
		makeValue( OutSine, 63, 0.84f );	makeValue( OutSine, 64, 0.85f );
		makeValue( OutSine, 65, 0.86f );	makeValue( OutSine, 66, 0.86f );
		makeValue( OutSine, 67, 0.87f );	makeValue( OutSine, 68, 0.88f );
		makeValue( OutSine, 69, 0.89f );	makeValue( OutSine, 70, 0.89f );

		makeValue( OutSine, 71, 0.90f );	makeValue( OutSine, 72, 0.91f );
		makeValue( OutSine, 73, 0.91f );	makeValue( OutSine, 74, 0.92f );
		makeValue( OutSine, 75, 0.93f );	makeValue( OutSine, 76, 0.93f );
		makeValue( OutSine, 77, 0.94f );	makeValue( OutSine, 78, 0.94f );
		makeValue( OutSine, 79, 0.95f );	makeValue( OutSine, 80, 0.95f );

		makeValue( OutSine, 81, 0.96f );	makeValue( OutSine, 82, 0.96f );
		makeValue( OutSine, 83, 0.96f );	makeValue( OutSine, 84, 0.97f );
		makeValue( OutSine, 85, 0.97f );	makeValue( OutSine, 86, 0.98f );
		makeValue( OutSine, 87, 0.98f );	makeValue( OutSine, 88, 0.98f );
		makeValue( OutSine, 89, 0.98f );	makeValue( OutSine, 90, 0.99f );

		makeValue( OutSine, 91, 0.99f );	makeValue( OutSine, 92, 0.99f );
		makeValue( OutSine, 93, 1.00f );	makeValue( OutSine, 94, 1.00f );
		makeValue( OutSine, 95, 1.00f );	makeValue( OutSine, 96, 1.00f );
		makeValue( OutSine, 97, 1.00f );	makeValue( OutSine, 98, 1.00f );
		makeValue( OutSine, 99, 1.00f );	makeValue( OutSine, 100, 1.00f );
	}

	public void makeInOutSine()
	{
		makeValue( InOutSine, 0, 0.00f );

		makeValue( InOutSine, 1, 0.00f );	makeValue( InOutSine, 2, 0.00f );
		makeValue( InOutSine, 3, 0.00f );	makeValue( InOutSine, 4, 0.00f );
		makeValue( InOutSine, 5, 0.01f );	makeValue( InOutSine, 6, 0.01f );
		makeValue( InOutSine, 7, 0.01f );	makeValue( InOutSine, 8, 0.01f );
		makeValue( InOutSine, 9, 0.02f );	makeValue( InOutSine, 10, 0.02f );

		makeValue( InOutSine, 11, 0.03f );	makeValue( InOutSine, 12, 0.03f );
		makeValue( InOutSine, 13, 0.04f );	makeValue( InOutSine, 14, 0.05f );
		makeValue( InOutSine, 15, 0.06f );	makeValue( InOutSine, 16, 0.07f );
		makeValue( InOutSine, 17, 0.07f );	makeValue( InOutSine, 18, 0.08f );
		makeValue( InOutSine, 19, 0.09f );	makeValue( InOutSine, 20, 0.10f );

		makeValue( InOutSine, 21, 0.11f );	makeValue( InOutSine, 22, 0.12f );
		makeValue( InOutSine, 23, 0.13f );	makeValue( InOutSine, 24, 0.14f );
		makeValue( InOutSine, 25, 0.15f );	makeValue( InOutSine, 26, 0.16f );
		makeValue( InOutSine, 27, 0.17f );	makeValue( InOutSine, 28, 0.19f );
		makeValue( InOutSine, 29, 0.20f );	makeValue( InOutSine, 30, 0.21f );

		makeValue( InOutSine, 31, 0.22f );	makeValue( InOutSine, 32, 0.23f );
		makeValue( InOutSine, 33, 0.25f );	makeValue( InOutSine, 34, 0.26f );
		makeValue( InOutSine, 35, 0.27f );	makeValue( InOutSine, 36, 0.29f );
		makeValue( InOutSine, 37, 0.30f );	makeValue( InOutSine, 38, 0.32f );
		makeValue( InOutSine, 39, 0.33f );	makeValue( InOutSine, 40, 0.35f );

		makeValue( InOutSine, 41, 0.36f );	makeValue( InOutSine, 42, 0.38f );
		makeValue( InOutSine, 43, 0.39f );	makeValue( InOutSine, 44, 0.41f );
		makeValue( InOutSine, 45, 0.42f );	makeValue( InOutSine, 46, 0.44f );
		makeValue( InOutSine, 47, 0.45f );	makeValue( InOutSine, 48, 0.47f );
		makeValue( InOutSine, 49, 0.48f );	makeValue( InOutSine, 50, 0.50f );

		makeValue( InOutSine, 51, 0.51f );	makeValue( InOutSine, 52, 0.53f );
		makeValue( InOutSine, 53, 0.54f );	makeValue( InOutSine, 54, 0.56f );
		makeValue( InOutSine, 55, 0.59f );	makeValue( InOutSine, 56, 0.60f );
		makeValue( InOutSine, 57, 0.62f );	makeValue( InOutSine, 58, 0.63f );
		makeValue( InOutSine, 59, 0.65f );	makeValue( InOutSine, 60, 0.66f );

		makeValue( InOutSine, 61, 0.68f );	makeValue( InOutSine, 62, 0.69f );
		makeValue( InOutSine, 63, 0.70f );	makeValue( InOutSine, 64, 0.72f );
		makeValue( InOutSine, 65, 0.73f );	makeValue( InOutSine, 66, 0.75f );
		makeValue( InOutSine, 67, 0.76f );	makeValue( InOutSine, 68, 0.77f );
		makeValue( InOutSine, 69, 0.79f );	makeValue( InOutSine, 70, 0.80f );

		makeValue( InOutSine, 71, 0.81f );	makeValue( InOutSine, 72, 0.82f );
		makeValue( InOutSine, 73, 0.83f );	makeValue( InOutSine, 74, 0.84f );
		makeValue( InOutSine, 75, 0.86f );	makeValue( InOutSine, 76, 0.87f );
		makeValue( InOutSine, 77, 0.88f );	makeValue( InOutSine, 78, 0.89f );
		makeValue( InOutSine, 79, 0.90f );	makeValue( InOutSine, 80, 0.91f );

		makeValue( InOutSine, 81, 0.91f );	makeValue( InOutSine, 82, 0.92f );
		makeValue( InOutSine, 83, 0.93f );	makeValue( InOutSine, 84, 0.94f );
		makeValue( InOutSine, 85, 0.95f );	makeValue( InOutSine, 86, 0.95f );
		makeValue( InOutSine, 87, 0.96f );	makeValue( InOutSine, 88, 0.96f );
		makeValue( InOutSine, 89, 0.97f );	makeValue( InOutSine, 90, 0.97f );

		makeValue( InOutSine, 91, 0.98f );	makeValue( InOutSine, 92, 0.98f );
		makeValue( InOutSine, 93, 0.99f );	makeValue( InOutSine, 94, 0.99f );
		makeValue( InOutSine, 95, 0.99f );	makeValue( InOutSine, 96, 1.00f );
		makeValue( InOutSine, 97, 1.00f );	makeValue( InOutSine, 98, 1.00f );
		makeValue( InOutSine, 99, 1.00f );	makeValue( InOutSine, 100, 1.00f );
	}

	public void makeOutInSine()
	{
		makeValue( OutInSine, 0, 0.00f );

		makeValue( OutInSine, 1, 0.02f );	makeValue( OutInSine, 2, 0.03f );
		makeValue( OutInSine, 3, 0.05f );	makeValue( OutInSine, 4, 0.06f );
		makeValue( OutInSine, 5, 0.08f );	makeValue( OutInSine, 6, 0.09f );
		makeValue( OutInSine, 7, 0.11f );	makeValue( OutInSine, 8, 0.12f );
		makeValue( OutInSine, 9, 0.14f );	makeValue( OutInSine, 10, 0.15f );

		makeValue( OutInSine, 11, 0.17f );	makeValue( OutInSine, 12, 0.18f );
		makeValue( OutInSine, 13, 0.19f );	makeValue( OutInSine, 14, 0.21f );
		makeValue( OutInSine, 15, 0.22f );	makeValue( OutInSine, 16, 0.25f );
		makeValue( OutInSine, 17, 0.26f );	makeValue( OutInSine, 18, 0.28f );
		makeValue( OutInSine, 19, 0.29f );	makeValue( OutInSine, 20, 0.30f );

		makeValue( OutInSine, 21, 0.31f );	makeValue( OutInSine, 22, 0.32f );
		makeValue( OutInSine, 23, 0.34f );	makeValue( OutInSine, 24, 0.35f );
		makeValue( OutInSine, 25, 0.36f );	makeValue( OutInSine, 26, 0.37f );
		makeValue( OutInSine, 27, 0.38f );	makeValue( OutInSine, 28, 0.39f );
		makeValue( OutInSine, 29, 0.40f );	makeValue( OutInSine, 30, 0.41f );

		makeValue( OutInSine, 31, 0.42f );	makeValue( OutInSine, 32, 0.42f );
		makeValue( OutInSine, 33, 0.43f );	makeValue( OutInSine, 34, 0.44f );
		makeValue( OutInSine, 35, 0.45f );	makeValue( OutInSine, 36, 0.45f );
		makeValue( OutInSine, 37, 0.46f );	makeValue( OutInSine, 38, 0.47f );
		makeValue( OutInSine, 39, 0.47f );	makeValue( OutInSine, 40, 0.48f );

		makeValue( OutInSine, 41, 0.48f );	makeValue( OutInSine, 42, 0.48f );
		makeValue( OutInSine, 43, 0.49f );	makeValue( OutInSine, 44, 0.49f );
		makeValue( OutInSine, 45, 0.49f );	makeValue( OutInSine, 46, 0.50f );
		makeValue( OutInSine, 47, 0.50f );	makeValue( OutInSine, 48, 0.50f );
		makeValue( OutInSine, 49, 0.50f );	makeValue( OutInSine, 50, 0.50f );

		makeValue( OutInSine, 51, 0.50f );	makeValue( OutInSine, 52, 0.50f );
		makeValue( OutInSine, 53, 0.50f );	makeValue( OutInSine, 54, 0.50f );
		makeValue( OutInSine, 55, 0.51f );	makeValue( OutInSine, 56, 0.51f );
		makeValue( OutInSine, 57, 0.51f );	makeValue( OutInSine, 58, 0.52f );
		makeValue( OutInSine, 59, 0.52f );	makeValue( OutInSine, 60, 0.53f );

		makeValue( OutInSine, 61, 0.53f );	makeValue( OutInSine, 62, 0.54f );
		makeValue( OutInSine, 63, 0.54f );	makeValue( OutInSine, 64, 0.55f );
		makeValue( OutInSine, 65, 0.56f );	makeValue( OutInSine, 66, 0.57f );
		makeValue( OutInSine, 67, 0.57f );	makeValue( OutInSine, 68, 0.58f );
		makeValue( OutInSine, 69, 0.59f );	makeValue( OutInSine, 70, 0.60f );

		makeValue( OutInSine, 71, 0.61f );	makeValue( OutInSine, 72, 0.62f );
		makeValue( OutInSine, 73, 0.63f );	makeValue( OutInSine, 74, 0.64f );
		makeValue( OutInSine, 75, 0.65f );	makeValue( OutInSine, 76, 0.66f );
		makeValue( OutInSine, 77, 0.67f );	makeValue( OutInSine, 78, 0.68f );
		makeValue( OutInSine, 79, 0.70f );	makeValue( OutInSine, 80, 0.71f );

		makeValue( OutInSine, 81, 0.72f );	makeValue( OutInSine, 82, 0.73f );
		makeValue( OutInSine, 83, 0.75f );	makeValue( OutInSine, 84, 0.76f );
		makeValue( OutInSine, 85, 0.77f );	makeValue( OutInSine, 86, 0.79f );
		makeValue( OutInSine, 87, 0.80f );	makeValue( OutInSine, 88, 0.82f );
		makeValue( OutInSine, 89, 0.83f );	makeValue( OutInSine, 90, 0.84f );

		makeValue( OutInSine, 91, 0.86f );	makeValue( OutInSine, 92, 0.87f );
		makeValue( OutInSine, 93, 0.89f );	makeValue( OutInSine, 94, 0.90f );
		makeValue( OutInSine, 95, 0.92f );	makeValue( OutInSine, 96, 0.93f );
		makeValue( OutInSine, 97, 0.95f );	makeValue( OutInSine, 98, 0.96f );
		makeValue( OutInSine, 99, 0.99f );	makeValue( OutInSine, 100, 1.00f );
	}

	public void makeInExpo()
	{
		makeValue( InExpo, 0, 0.00f );

		makeValue( InExpo, 1, 0.00f );	makeValue( InExpo, 2, 0.00f );
		makeValue( InExpo, 3, 0.00f );	makeValue( InExpo, 4, 0.00f );
		makeValue( InExpo, 5, 0.00f );	makeValue( InExpo, 6, 0.00f );
		makeValue( InExpo, 7, 0.00f );	makeValue( InExpo, 8, 0.00f );
		makeValue( InExpo, 9, 0.00f );	makeValue( InExpo, 10, 0.00f );

		makeValue( InExpo, 11, 0.00f );	makeValue( InExpo, 12, 0.00f );
		makeValue( InExpo, 13, 0.00f );	makeValue( InExpo, 14, 0.00f );
		makeValue( InExpo, 15, 0.00f );	makeValue( InExpo, 16, 0.00f );
		makeValue( InExpo, 17, 0.00f );	makeValue( InExpo, 18, 0.00f );
		makeValue( InExpo, 19, 0.00f );	makeValue( InExpo, 20, 0.00f );

		makeValue( InExpo, 21, 0.00f );	makeValue( InExpo, 22, 0.00f );
		makeValue( InExpo, 23, 0.00f );	makeValue( InExpo, 24, 0.00f );
		makeValue( InExpo, 25, 0.00f );	makeValue( InExpo, 26, 0.00f );
		makeValue( InExpo, 27, 0.01f );	makeValue( InExpo, 28, 0.01f );
		makeValue( InExpo, 29, 0.01f );	makeValue( InExpo, 30, 0.01f );

		makeValue( InExpo, 31, 0.01f );	makeValue( InExpo, 32, 0.01f );
		makeValue( InExpo, 33, 0.01f );	makeValue( InExpo, 34, 0.01f );
		makeValue( InExpo, 35, 0.01f );	makeValue( InExpo, 36, 0.01f );
		makeValue( InExpo, 37, 0.01f );	makeValue( InExpo, 38, 0.01f );
		makeValue( InExpo, 39, 0.01f );	makeValue( InExpo, 40, 0.01f );

		makeValue( InExpo, 41, 0.02f );	makeValue( InExpo, 42, 0.02f );
		makeValue( InExpo, 43, 0.02f );	makeValue( InExpo, 44, 0.02f );
		makeValue( InExpo, 45, 0.02f );	makeValue( InExpo, 46, 0.02f );
		makeValue( InExpo, 47, 0.02f );	makeValue( InExpo, 48, 0.03f );
		makeValue( InExpo, 49, 0.03f );	makeValue( InExpo, 50, 0.03f );

		makeValue( InExpo, 51, 0.03f );	makeValue( InExpo, 52, 0.03f );
		makeValue( InExpo, 53, 0.04f );	makeValue( InExpo, 54, 0.04f );
		makeValue( InExpo, 55, 0.04f );	makeValue( InExpo, 56, 0.05f );
		makeValue( InExpo, 57, 0.05f );	makeValue( InExpo, 58, 0.05f );
		makeValue( InExpo, 59, 0.06f );	makeValue( InExpo, 60, 0.06f );

		makeValue( InExpo, 61, 0.06f );	makeValue( InExpo, 62, 0.07f );
		makeValue( InExpo, 63, 0.07f );	makeValue( InExpo, 64, 0.08f );
		makeValue( InExpo, 65, 0.08f );	makeValue( InExpo, 66, 0.09f );
		makeValue( InExpo, 67, 0.10f );	makeValue( InExpo, 68, 0.11f );
		makeValue( InExpo, 69, 0.12f );	makeValue( InExpo, 70, 0.13f );

		makeValue( InExpo, 71, 0.14f );	makeValue( InExpo, 72, 0.15f );
		makeValue( InExpo, 73, 0.16f );	makeValue( InExpo, 74, 0.17f );
		makeValue( InExpo, 75, 0.18f );	makeValue( InExpo, 76, 0.19f );
		makeValue( InExpo, 77, 0.20f );	makeValue( InExpo, 78, 0.22f );
		makeValue( InExpo, 79, 0.23f );	makeValue( InExpo, 80, 0.25f );

		makeValue( InExpo, 81, 0.27f );	makeValue( InExpo, 82, 0.29f );
		makeValue( InExpo, 83, 0.31f );	makeValue( InExpo, 84, 0.33f );
		makeValue( InExpo, 85, 0.35f );	makeValue( InExpo, 86, 0.38f );
		makeValue( InExpo, 87, 0.40f );	makeValue( InExpo, 88, 0.43f );
		makeValue( InExpo, 89, 0.46f );	makeValue( InExpo, 90, 0.49f );

		makeValue( InExpo, 91, 0.53f );	makeValue( InExpo, 92, 0.56f );
		makeValue( InExpo, 93, 0.60f );	makeValue( InExpo, 94, 0.65f );
		makeValue( InExpo, 95, 0.69f );	makeValue( InExpo, 96, 0.74f );
		makeValue( InExpo, 97, 0.79f );	makeValue( InExpo, 98, 0.85f );
		makeValue( InExpo, 99, 0.91f );	makeValue( InExpo, 100, 1.00f );
	}

	public void makeOutExpo()
	{
		makeValue( OutExpo, 0, 0.00f );

		makeValue( OutExpo, 1, 0.07f );	makeValue( OutExpo, 2, 0.13f );
		makeValue( OutExpo, 3, 0.18f );	makeValue( OutExpo, 4, 0.24f );
		makeValue( OutExpo, 5, 0.29f );	makeValue( OutExpo, 6, 0.33f );
		makeValue( OutExpo, 7, 0.38f );	makeValue( OutExpo, 8, 0.42f );
		makeValue( OutExpo, 9, 0.46f );	makeValue( OutExpo, 10, 0.49f );

		makeValue( OutExpo, 11, 0.53f );	makeValue( OutExpo, 12, 0.56f );
		makeValue( OutExpo, 13, 0.59f );	makeValue( OutExpo, 14, 0.61f );
		makeValue( OutExpo, 15, 0.64f );	makeValue( OutExpo, 16, 0.66f );
		makeValue( OutExpo, 17, 0.68f );	makeValue( OutExpo, 18, 0.71f );
		makeValue( OutExpo, 19, 0.72f );	makeValue( OutExpo, 20, 0.74f );

		makeValue( OutExpo, 21, 0.76f );	makeValue( OutExpo, 22, 0.78f );
		makeValue( OutExpo, 23, 0.79f );	makeValue( OutExpo, 24, 0.80f );
		makeValue( OutExpo, 25, 0.82f );	makeValue( OutExpo, 26, 0.83f );
		makeValue( OutExpo, 27, 0.84f );	makeValue( OutExpo, 28, 0.85f );
		makeValue( OutExpo, 29, 0.86f );	makeValue( OutExpo, 30, 0.87f );

		makeValue( OutExpo, 31, 0.88f );	makeValue( OutExpo, 32, 0.89f );
		makeValue( OutExpo, 33, 0.89f );	makeValue( OutExpo, 34, 0.90f );
		makeValue( OutExpo, 35, 0.91f );	makeValue( OutExpo, 36, 0.91f );
		makeValue( OutExpo, 37, 0.92f );	makeValue( OutExpo, 38, 0.93f );
		makeValue( OutExpo, 39, 0.93f );	makeValue( OutExpo, 40, 0.94f );

		makeValue( OutExpo, 41, 0.94f );	makeValue( OutExpo, 42, 0.95f );
		makeValue( OutExpo, 43, 0.95f );	makeValue( OutExpo, 44, 0.95f );
		makeValue( OutExpo, 45, 0.96f );	makeValue( OutExpo, 46, 0.96f );
		makeValue( OutExpo, 47, 0.96f );	makeValue( OutExpo, 48, 0.96f );
		makeValue( OutExpo, 49, 0.97f );	makeValue( OutExpo, 50, 0.97f );

		makeValue( OutExpo, 51, 0.97f );	makeValue( OutExpo, 52, 0.97f );
		makeValue( OutExpo, 53, 0.98f );	makeValue( OutExpo, 54, 0.98f );
		makeValue( OutExpo, 55, 0.98f );	makeValue( OutExpo, 56, 0.98f );
		makeValue( OutExpo, 57, 0.98f );	makeValue( OutExpo, 58, 0.98f );
		makeValue( OutExpo, 59, 0.98f );	makeValue( OutExpo, 60, 0.98f );

		makeValue( OutExpo, 61, 0.99f );	makeValue( OutExpo, 62, 0.99f );
		makeValue( OutExpo, 63, 0.99f );	makeValue( OutExpo, 64, 0.99f );
		makeValue( OutExpo, 65, 0.99f );	makeValue( OutExpo, 66, 0.99f );
		makeValue( OutExpo, 67, 0.99f );	makeValue( OutExpo, 68, 0.99f );
		makeValue( OutExpo, 69, 0.99f );	makeValue( OutExpo, 70, 0.99f );

		makeValue( OutExpo, 71, 0.99f );	makeValue( OutExpo, 72, 0.99f );
		makeValue( OutExpo, 73, 0.99f );	makeValue( OutExpo, 74, 0.99f );
		makeValue( OutExpo, 75, 1.00f );	makeValue( OutExpo, 76, 1.00f );
		makeValue( OutExpo, 77, 1.00f );	makeValue( OutExpo, 78, 1.00f );
		makeValue( OutExpo, 79, 1.00f );	makeValue( OutExpo, 80, 1.00f );

		makeValue( OutExpo, 81, 1.00f );	makeValue( OutExpo, 82, 1.00f );
		makeValue( OutExpo, 83, 1.00f );	makeValue( OutExpo, 84, 1.00f );
		makeValue( OutExpo, 85, 1.00f );	makeValue( OutExpo, 86, 1.00f );
		makeValue( OutExpo, 87, 1.00f );	makeValue( OutExpo, 88, 1.00f );
		makeValue( OutExpo, 89, 1.00f );	makeValue( OutExpo, 90, 1.00f );

		makeValue( OutExpo, 91, 1.00f );	makeValue( OutExpo, 92, 1.00f );
		makeValue( OutExpo, 93, 1.00f );	makeValue( OutExpo, 94, 1.00f );
		makeValue( OutExpo, 95, 1.00f );	makeValue( OutExpo, 96, 1.00f );
		makeValue( OutExpo, 97, 1.00f );	makeValue( OutExpo, 98, 1.00f );
		makeValue( OutExpo, 99, 1.00f );	makeValue( OutExpo, 100, 1.0f );
	}

	public void makeInOutExpo()
	{
		//	InOutExpo

		makeValue( InOutExpo, 0, 0.00f );

		makeValue( InOutExpo, 1, 0.00f );	makeValue( InOutExpo, 2, 0.00f );
		makeValue( InOutExpo, 3, 0.00f );	makeValue( InOutExpo, 4, 0.00f );
		makeValue( InOutExpo, 5, 0.00f );	makeValue( InOutExpo, 6, 0.00f );
		makeValue( InOutExpo, 7, 0.00f );	makeValue( InOutExpo, 8, 0.00f );
		makeValue( InOutExpo, 9, 0.00f );	makeValue( InOutExpo, 10, 0.00f );

		makeValue( InOutExpo, 11, 0.00f );	makeValue( InOutExpo, 12, 0.00f );
		makeValue( InOutExpo, 13, 0.00f );	makeValue( InOutExpo, 14, 0.00f );
		makeValue( InOutExpo, 15, 0.00f );	makeValue( InOutExpo, 16, 0.00f );
		makeValue( InOutExpo, 17, 0.01f );	makeValue( InOutExpo, 18, 0.01f );
		makeValue( InOutExpo, 19, 0.01f );	makeValue( InOutExpo, 20, 0.01f );

		makeValue( InOutExpo, 21, 0.01f );	makeValue( InOutExpo, 22, 0.01f );
		makeValue( InOutExpo, 23, 0.01f );	makeValue( InOutExpo, 24, 0.01f );
		makeValue( InOutExpo, 25, 0.02f );	makeValue( InOutExpo, 26, 0.02f );
		makeValue( InOutExpo, 27, 0.02f );	makeValue( InOutExpo, 28, 0.02f );
		makeValue( InOutExpo, 29, 0.03f );	makeValue( InOutExpo, 30, 0.03f );

		makeValue( InOutExpo, 31, 0.04f );	makeValue( InOutExpo, 32, 0.04f );
		makeValue( InOutExpo, 33, 0.05f );	makeValue( InOutExpo, 34, 0.06f );
		makeValue( InOutExpo, 35, 0.06f );	makeValue( InOutExpo, 36, 0.07f );
		makeValue( InOutExpo, 37, 0.08f );	makeValue( InOutExpo, 38, 0.10f );
		makeValue( InOutExpo, 39, 0.11f );	makeValue( InOutExpo, 40, 0.13f );

		makeValue( InOutExpo, 41, 0.14f );	makeValue( InOutExpo, 42, 0.19f );
		makeValue( InOutExpo, 43, 0.22f );	makeValue( InOutExpo, 44, 0.25f );
		makeValue( InOutExpo, 45, 0.28f );	makeValue( InOutExpo, 46, 0.32f );
		makeValue( InOutExpo, 47, 0.37f );	makeValue( InOutExpo, 48, 0.42f );
		makeValue( InOutExpo, 49, 0.49f );	makeValue( InOutExpo, 50, 0.55f );

		makeValue( InOutExpo, 51, 0.61f );	makeValue( InOutExpo, 52, 0.66f );
		makeValue( InOutExpo, 53, 0.70f );	makeValue( InOutExpo, 54, 0.74f );
		makeValue( InOutExpo, 55, 0.77f );	makeValue( InOutExpo, 56, 0.80f );
		makeValue( InOutExpo, 57, 0.83f );	makeValue( InOutExpo, 58, 0.85f );
		makeValue( InOutExpo, 59, 0.87f );	makeValue( InOutExpo, 60, 0.88f );

		makeValue( InOutExpo, 61, 0.90f );	makeValue( InOutExpo, 62, 0.91f );
		makeValue( InOutExpo, 63, 0.92f );	makeValue( InOutExpo, 64, 0.93f );
		makeValue( InOutExpo, 65, 0.94f );	makeValue( InOutExpo, 66, 0.95f );
		makeValue( InOutExpo, 67, 0.96f );	makeValue( InOutExpo, 68, 0.96f );
		makeValue( InOutExpo, 69, 0.97f );	makeValue( InOutExpo, 70, 0.97f );

		makeValue( InOutExpo, 71, 0.97f );	makeValue( InOutExpo, 72, 0.98f );
		makeValue( InOutExpo, 73, 0.98f );	makeValue( InOutExpo, 74, 0.98f );
		makeValue( InOutExpo, 75, 0.99f );	makeValue( InOutExpo, 76, 0.99f );
		makeValue( InOutExpo, 77, 0.99f );	makeValue( InOutExpo, 78, 0.99f );
		makeValue( InOutExpo, 79, 0.99f );	makeValue( InOutExpo, 80, 0.99f );

		makeValue( InOutExpo, 81, 0.99f );	makeValue( InOutExpo, 82, 0.99f );
		makeValue( InOutExpo, 83, 1.00f );	makeValue( InOutExpo, 84, 1.00f );
		makeValue( InOutExpo, 85, 1.00f );	makeValue( InOutExpo, 86, 1.00f );
		makeValue( InOutExpo, 87, 1.00f );	makeValue( InOutExpo, 88, 1.00f );
		makeValue( InOutExpo, 89, 1.00f );	makeValue( InOutExpo, 90, 1.00f );

		makeValue( InOutExpo, 91, 1.00f );	makeValue( InOutExpo, 92, 1.00f );
		makeValue( InOutExpo, 93, 1.00f );	makeValue( InOutExpo, 94, 1.00f );
		makeValue( InOutExpo, 95, 1.00f );	makeValue( InOutExpo, 96, 1.00f );
		makeValue( InOutExpo, 97, 1.00f );	makeValue( InOutExpo, 98, 1.00f );
		makeValue( InOutExpo, 99, 1.00f );	makeValue( InOutExpo, 100, 1.00f );
	}

	public void makeOutInExpo()
	{
		// OutInExpo,

		makeValue( OutInExpo, 0, 0.00f );

		makeValue( OutInExpo, 1, 0.06f );	makeValue( OutInExpo, 2, 0.12f );
		makeValue( OutInExpo, 3, 0.17f );	makeValue( OutInExpo, 4, 0.21f );
		makeValue( OutInExpo, 5, 0.25f );	makeValue( OutInExpo, 6, 0.31f );
		makeValue( OutInExpo, 7, 0.33f );	makeValue( OutInExpo, 8, 0.35f );
		makeValue( OutInExpo, 9, 0.37f );	makeValue( OutInExpo, 10, 0.39f );

		makeValue( OutInExpo, 11, 0.40f );	makeValue( OutInExpo, 12, 0.41f );
		makeValue( OutInExpo, 13, 0.43f );	makeValue( OutInExpo, 14, 0.44f );
		makeValue( OutInExpo, 15, 0.44f );	makeValue( OutInExpo, 16, 0.45f );
		makeValue( OutInExpo, 17, 0.46f );	makeValue( OutInExpo, 18, 0.46f );
		makeValue( OutInExpo, 19, 0.47f );	makeValue( OutInExpo, 20, 0.47f );

		makeValue( OutInExpo, 21, 0.48f );	makeValue( OutInExpo, 22, 0.48f );
		makeValue( OutInExpo, 23, 0.48f );	makeValue( OutInExpo, 24, 0.48f );
		makeValue( OutInExpo, 25, 0.49f );	makeValue( OutInExpo, 26, 0.49f );
		makeValue( OutInExpo, 27, 0.49f );	makeValue( OutInExpo, 28, 0.49f );
		makeValue( OutInExpo, 29, 0.49f );	makeValue( OutInExpo, 30, 0.49f );

		makeValue( OutInExpo, 31, 0.49f );	makeValue( OutInExpo, 32, 0.49f );
		makeValue( OutInExpo, 33, 0.50f );	makeValue( OutInExpo, 34, 0.50f );
		makeValue( OutInExpo, 35, 0.50f );	makeValue( OutInExpo, 36, 0.50f );
		makeValue( OutInExpo, 37, 0.50f );	makeValue( OutInExpo, 38, 0.50f );
		makeValue( OutInExpo, 39, 0.50f );	makeValue( OutInExpo, 40, 0.50f );

		makeValue( OutInExpo, 41, 0.50f );	makeValue( OutInExpo, 42, 0.50f );
		makeValue( OutInExpo, 43, 0.50f );	makeValue( OutInExpo, 44, 0.50f );
		makeValue( OutInExpo, 45, 0.50f );	makeValue( OutInExpo, 46, 0.50f );
		makeValue( OutInExpo, 47, 0.50f );	makeValue( OutInExpo, 48, 0.50f );
		makeValue( OutInExpo, 49, 0.50f );	makeValue( OutInExpo, 50, 0.50f );

		makeValue( OutInExpo, 51, 0.50f );	makeValue( OutInExpo, 52, 0.50f );
		makeValue( OutInExpo, 53, 0.50f );	makeValue( OutInExpo, 54, 0.50f );
		makeValue( OutInExpo, 55, 0.50f );	makeValue( OutInExpo, 56, 0.50f );
		makeValue( OutInExpo, 57, 0.50f );	makeValue( OutInExpo, 58, 0.50f );
		makeValue( OutInExpo, 59, 0.50f );	makeValue( OutInExpo, 60, 0.50f );

		makeValue( OutInExpo, 61, 0.50f );	makeValue( OutInExpo, 62, 0.50f );
		makeValue( OutInExpo, 63, 0.50f );	makeValue( OutInExpo, 64, 0.50f );
		makeValue( OutInExpo, 65, 0.50f );	makeValue( OutInExpo, 66, 0.50f );
		makeValue( OutInExpo, 67, 0.50f );	makeValue( OutInExpo, 68, 0.51f );
		makeValue( OutInExpo, 69, 0.51f );	makeValue( OutInExpo, 70, 0.51f );

		makeValue( OutInExpo, 71, 0.51f );	makeValue( OutInExpo, 72, 0.51f );
		makeValue( OutInExpo, 73, 0.51f );	makeValue( OutInExpo, 74, 0.51f );
		makeValue( OutInExpo, 75, 0.52f );	makeValue( OutInExpo, 76, 0.52f );
		makeValue( OutInExpo, 77, 0.52f );	makeValue( OutInExpo, 78, 0.52f );
		makeValue( OutInExpo, 79, 0.53f );	makeValue( OutInExpo, 80, 0.53f );

		makeValue( OutInExpo, 81, 0.54f );	makeValue( OutInExpo, 82, 0.54f );
		makeValue( OutInExpo, 83, 0.55f );	makeValue( OutInExpo, 84, 0.55f );
		makeValue( OutInExpo, 85, 0.56f );	makeValue( OutInExpo, 86, 0.57f );
		makeValue( OutInExpo, 87, 0.58f );	makeValue( OutInExpo, 88, 0.59f );
		makeValue( OutInExpo, 89, 0.62f );	makeValue( OutInExpo, 90, 0.64f );

		makeValue( OutInExpo, 91, 0.66f );	makeValue( OutInExpo, 92, 0.68f );
		makeValue( OutInExpo, 93, 0.71f );	makeValue( OutInExpo, 94, 0.74f );
		makeValue( OutInExpo, 95, 0.77f );	makeValue( OutInExpo, 96, 0.82f );
		makeValue( OutInExpo, 97, 0.86f );	makeValue( OutInExpo, 98, 0.91f );
		makeValue( OutInExpo, 99, 0.97f );	makeValue( OutInExpo, 100, 1.0f );
	}

	public void makeInCirc()
	{
		// InCirc,

		makeValue( InCirc, 0, 0.00f );

		makeValue( InCirc, 1, 0.00f );	makeValue( InCirc, 2, 0.00f );
		makeValue( InCirc, 3, 0.00f );	makeValue( InCirc, 4, 0.00f );
		makeValue( InCirc, 5, 0.00f );	makeValue( InCirc, 6, 0.00f );
		makeValue( InCirc, 7, 0.00f );	makeValue( InCirc, 8, 0.00f );
		makeValue( InCirc, 9, 0.00f );	makeValue( InCirc, 10, 0.01f );

		makeValue( InCirc, 11, 0.01f );	makeValue( InCirc, 12, 0.01f );
		makeValue( InCirc, 13, 0.01f );	makeValue( InCirc, 14, 0.01f );
		makeValue( InCirc, 15, 0.01f );	makeValue( InCirc, 16, 0.01f );
		makeValue( InCirc, 17, 0.02f );	makeValue( InCirc, 18, 0.02f );
		makeValue( InCirc, 19, 0.02f );	makeValue( InCirc, 20, 0.02f );

		makeValue( InCirc, 21, 0.02f );	makeValue( InCirc, 22, 0.03f );
		makeValue( InCirc, 23, 0.03f );	makeValue( InCirc, 24, 0.03f );
		makeValue( InCirc, 25, 0.03f );	makeValue( InCirc, 26, 0.04f );
		makeValue( InCirc, 27, 0.04f );	makeValue( InCirc, 28, 0.04f );
		makeValue( InCirc, 29, 0.04f );	makeValue( InCirc, 30, 0.05f );

		makeValue( InCirc, 31, 0.05f );	makeValue( InCirc, 32, 0.05f );
		makeValue( InCirc, 33, 0.06f );	makeValue( InCirc, 34, 0.06f );
		makeValue( InCirc, 35, 0.06f );	makeValue( InCirc, 36, 0.07f );
		makeValue( InCirc, 37, 0.07f );	makeValue( InCirc, 38, 0.08f );
		makeValue( InCirc, 39, 0.08f );	makeValue( InCirc, 40, 0.08f );

		makeValue( InCirc, 41, 0.09f );	makeValue( InCirc, 42, 0.10f );
		makeValue( InCirc, 43, 0.10f );	makeValue( InCirc, 44, 0.11f );
		makeValue( InCirc, 45, 0.11f );	makeValue( InCirc, 46, 0.12f );
		makeValue( InCirc, 47, 0.12f );	makeValue( InCirc, 48, 0.13f );
		makeValue( InCirc, 49, 0.13f );	makeValue( InCirc, 50, 0.14f );

		makeValue( InCirc, 51, 0.14f );	makeValue( InCirc, 52, 0.15f );
		makeValue( InCirc, 53, 0.16f );	makeValue( InCirc, 54, 0.16f );
		makeValue( InCirc, 55, 0.17f );	makeValue( InCirc, 56, 0.18f );
		makeValue( InCirc, 57, 0.18f );	makeValue( InCirc, 58, 0.19f );
		makeValue( InCirc, 59, 0.20f );	makeValue( InCirc, 60, 0.20f );

		makeValue( InCirc, 61, 0.21f );	makeValue( InCirc, 62, 0.22f );
		makeValue( InCirc, 63, 0.23f );	makeValue( InCirc, 64, 0.24f );
		makeValue( InCirc, 65, 0.24f );	makeValue( InCirc, 66, 0.25f );
		makeValue( InCirc, 67, 0.26f );	makeValue( InCirc, 68, 0.27f );
		makeValue( InCirc, 69, 0.28f );	makeValue( InCirc, 70, 0.29f );

		makeValue( InCirc, 71, 0.30f );	makeValue( InCirc, 72, 0.31f );
		makeValue( InCirc, 73, 0.32f );	makeValue( InCirc, 74, 0.33f );
		makeValue( InCirc, 75, 0.34f );	makeValue( InCirc, 76, 0.35f );
		makeValue( InCirc, 77, 0.36f );	makeValue( InCirc, 78, 0.38f );
		makeValue( InCirc, 79, 0.39f );	makeValue( InCirc, 80, 0.40f );

		makeValue( InCirc, 81, 0.41f );	makeValue( InCirc, 82, 0.43f );
		makeValue( InCirc, 83, 0.44f );	makeValue( InCirc, 84, 0.47f );
		makeValue( InCirc, 85, 0.49f );	makeValue( InCirc, 86, 0.51f );
		makeValue( InCirc, 87, 0.52f );	makeValue( InCirc, 88, 0.54f );
		makeValue( InCirc, 89, 0.56f );	makeValue( InCirc, 90, 0.58f );

		makeValue( InCirc, 91, 0.60f );	makeValue( InCirc, 92, 0.63f );
		makeValue( InCirc, 93, 0.65f );	makeValue( InCirc, 94, 0.68f );
		makeValue( InCirc, 95, 0.71f );	makeValue( InCirc, 96, 0.74f );
		makeValue( InCirc, 97, 0.79f );	makeValue( InCirc, 98, 0.84f );
		makeValue( InCirc, 99, 0.91f );	makeValue( InCirc, 100, 1.0f );
	}

	public void makeOutCirc()
	{
		// OutCirc

		makeValue( OutCirc, 0, 0.00f );

		makeValue( OutCirc, 1, 0.14f );	makeValue( OutCirc, 2, 0.20f );
		makeValue( OutCirc, 3, 0.24f );	makeValue( OutCirc, 4, 0.28f );
		makeValue( OutCirc, 5, 0.31f );	makeValue( OutCirc, 6, 0.34f );
		makeValue( OutCirc, 7, 0.36f );	makeValue( OutCirc, 8, 0.39f );
		makeValue( OutCirc, 9, 0.41f );	makeValue( OutCirc, 10, 0.43f );

		makeValue( OutCirc, 11, 0.45f );	makeValue( OutCirc, 12, 0.47f );
		makeValue( OutCirc, 13, 0.49f );	makeValue( OutCirc, 14, 0.50f );
		makeValue( OutCirc, 15, 0.52f );	makeValue( OutCirc, 16, 0.54f );
		makeValue( OutCirc, 17, 0.55f );	makeValue( OutCirc, 18, 0.57f );
		makeValue( OutCirc, 19, 0.58f );	makeValue( OutCirc, 20, 0.59f );

		makeValue( OutCirc, 21, 0.62f );	makeValue( OutCirc, 22, 0.63f );
		makeValue( OutCirc, 23, 0.64f );	makeValue( OutCirc, 24, 0.66f );
		makeValue( OutCirc, 25, 0.67f );	makeValue( OutCirc, 26, 0.68f );
		makeValue( OutCirc, 27, 0.69f );	makeValue( OutCirc, 28, 0.70f );
		makeValue( OutCirc, 29, 0.71f );	makeValue( OutCirc, 30, 0.72f );

		makeValue( OutCirc, 31, 0.73f );	makeValue( OutCirc, 32, 0.74f );
		makeValue( OutCirc, 33, 0.74f );	makeValue( OutCirc, 34, 0.75f );
		makeValue( OutCirc, 35, 0.76f );	makeValue( OutCirc, 36, 0.77f );
		makeValue( OutCirc, 37, 0.78f );	makeValue( OutCirc, 38, 0.79f );
		makeValue( OutCirc, 39, 0.79f );	makeValue( OutCirc, 40, 0.80f );

		makeValue( OutCirc, 41, 0.81f );	makeValue( OutCirc, 42, 0.81f );
		makeValue( OutCirc, 43, 0.82f );	makeValue( OutCirc, 44, 0.83f );
		makeValue( OutCirc, 45, 0.83f );	makeValue( OutCirc, 46, 0.84f );
		makeValue( OutCirc, 47, 0.85f );	makeValue( OutCirc, 48, 0.85f );
		makeValue( OutCirc, 49, 0.86f );	makeValue( OutCirc, 50, 0.86f );

		makeValue( OutCirc, 51, 0.87f );	makeValue( OutCirc, 52, 0.88f );
		makeValue( OutCirc, 53, 0.88f );	makeValue( OutCirc, 54, 0.89f );
		makeValue( OutCirc, 55, 0.89f );	makeValue( OutCirc, 56, 0.90f );
		makeValue( OutCirc, 57, 0.90f );	makeValue( OutCirc, 58, 0.91f );
		makeValue( OutCirc, 59, 0.91f );	makeValue( OutCirc, 60, 0.91f );

		makeValue( OutCirc, 61, 0.92f );	makeValue( OutCirc, 62, 0.92f );
		makeValue( OutCirc, 63, 0.93f );	makeValue( OutCirc, 64, 0.93f );
		makeValue( OutCirc, 65, 0.94f );	makeValue( OutCirc, 66, 0.94f );
		makeValue( OutCirc, 67, 0.95f );	makeValue( OutCirc, 68, 0.95f );
		makeValue( OutCirc, 69, 0.95f );	makeValue( OutCirc, 70, 0.95f );

		makeValue( OutCirc, 71, 0.96f );	makeValue( OutCirc, 72, 0.96f );
		makeValue( OutCirc, 73, 0.96f );	makeValue( OutCirc, 74, 0.97f );
		makeValue( OutCirc, 75, 0.97f );	makeValue( OutCirc, 76, 0.97f );
		makeValue( OutCirc, 77, 0.97f );	makeValue( OutCirc, 78, 0.98f );
		makeValue( OutCirc, 79, 0.98f );	makeValue( OutCirc, 80, 0.98f );

		makeValue( OutCirc, 81, 0.98f );	makeValue( OutCirc, 82, 0.98f );
		makeValue( OutCirc, 83, 0.99f );	makeValue( OutCirc, 84, 0.99f );
		makeValue( OutCirc, 85, 0.99f );	makeValue( OutCirc, 86, 0.99f );
		makeValue( OutCirc, 87, 0.99f );	makeValue( OutCirc, 88, 0.99f );
		makeValue( OutCirc, 89, 0.99f );	makeValue( OutCirc, 90, 0.99f );

		makeValue( OutCirc, 91, 1.00f );	makeValue( OutCirc, 92, 1.00f );
		makeValue( OutCirc, 93, 1.00f );	makeValue( OutCirc, 94, 1.00f );
		makeValue( OutCirc, 95, 1.00f );	makeValue( OutCirc, 96, 1.00f );
		makeValue( OutCirc, 97, 1.00f );	makeValue( OutCirc, 98, 1.00f );
		makeValue( OutCirc, 99, 1.00f );	makeValue( OutCirc, 100, 1.0f );
	}

	public void makeInOutCirc()
	{
//		InOutCirc

		makeValue( InOutCirc, 0, 0.00f );

		makeValue( InOutCirc, 1, 0.00f );	makeValue( InOutCirc, 2, 0.00f );
		makeValue( InOutCirc, 3, 0.00f );	makeValue( InOutCirc, 4, 0.00f );
		makeValue( InOutCirc, 5, 0.00f );	makeValue( InOutCirc, 6, 0.00f );
		makeValue( InOutCirc, 7, 0.00f );	makeValue( InOutCirc, 8, 0.01f );
		makeValue( InOutCirc, 9, 0.01f );	makeValue( InOutCirc, 10, 0.01f );

		makeValue( InOutCirc, 11, 0.01f );	makeValue( InOutCirc, 12, 0.01f );
		makeValue( InOutCirc, 13, 0.02f );	makeValue( InOutCirc, 14, 0.02f );
		makeValue( InOutCirc, 15, 0.02f );	makeValue( InOutCirc, 16, 0.03f );
		makeValue( InOutCirc, 17, 0.03f );	makeValue( InOutCirc, 18, 0.03f );
		makeValue( InOutCirc, 19, 0.04f );	makeValue( InOutCirc, 20, 0.04f );

		makeValue( InOutCirc, 21, 0.05f );	makeValue( InOutCirc, 22, 0.05f );
		makeValue( InOutCirc, 23, 0.06f );	makeValue( InOutCirc, 24, 0.06f );
		makeValue( InOutCirc, 25, 0.07f );	makeValue( InOutCirc, 26, 0.08f );
		makeValue( InOutCirc, 27, 0.08f );	makeValue( InOutCirc, 28, 0.09f );
		makeValue( InOutCirc, 29, 0.09f );	makeValue( InOutCirc, 30, 0.10f );

		makeValue( InOutCirc, 31, 0.11f );	makeValue( InOutCirc, 32, 0.12f );
		makeValue( InOutCirc, 33, 0.13f );	makeValue( InOutCirc, 34, 0.14f );
		makeValue( InOutCirc, 35, 0.14f );	makeValue( InOutCirc, 36, 0.15f );
		makeValue( InOutCirc, 37, 0.17f );	makeValue( InOutCirc, 38, 0.18f );
		makeValue( InOutCirc, 39, 0.19f );	makeValue( InOutCirc, 40, 0.20f );

		makeValue( InOutCirc, 41, 0.21f );	makeValue( InOutCirc, 42, 0.23f );
		makeValue( InOutCirc, 43, 0.24f );	makeValue( InOutCirc, 44, 0.26f );
		makeValue( InOutCirc, 45, 0.28f );	makeValue( InOutCirc, 46, 0.30f );
		makeValue( InOutCirc, 47, 0.33f );	makeValue( InOutCirc, 48, 0.36f );
		makeValue( InOutCirc, 49, 0.39f );	makeValue( InOutCirc, 50, 0.46f );

		makeValue( InOutCirc, 51, 0.59f );	makeValue( InOutCirc, 52, 0.63f );
		makeValue( InOutCirc, 53, 0.66f );	makeValue( InOutCirc, 54, 0.69f );
		makeValue( InOutCirc, 55, 0.71f );	makeValue( InOutCirc, 56, 0.73f );
		makeValue( InOutCirc, 57, 0.75f );	makeValue( InOutCirc, 58, 0.77f );
		makeValue( InOutCirc, 59, 0.78f );	makeValue( InOutCirc, 60, 0.81f );

		makeValue( InOutCirc, 61, 0.82f );	makeValue( InOutCirc, 62, 0.83f );
		makeValue( InOutCirc, 63, 0.84f );	makeValue( InOutCirc, 64, 0.85f );
		makeValue( InOutCirc, 65, 0.86f );	makeValue( InOutCirc, 66, 0.87f );
		makeValue( InOutCirc, 67, 0.88f );	makeValue( InOutCirc, 68, 0.89f );
		makeValue( InOutCirc, 69, 0.90f );	makeValue( InOutCirc, 70, 0.90f );

		makeValue( InOutCirc, 71, 0.91f );	makeValue( InOutCirc, 72, 0.92f );
		makeValue( InOutCirc, 73, 0.92f );	makeValue( InOutCirc, 74, 0.93f );
		makeValue( InOutCirc, 75, 0.93f );	makeValue( InOutCirc, 76, 0.94f );
		makeValue( InOutCirc, 77, 0.94f );	makeValue( InOutCirc, 78, 0.95f );
		makeValue( InOutCirc, 79, 0.95f );	makeValue( InOutCirc, 80, 0.96f );

		makeValue( InOutCirc, 81, 0.96f );	makeValue( InOutCirc, 82, 0.97f );
		makeValue( InOutCirc, 83, 0.97f );	makeValue( InOutCirc, 84, 0.97f );
		makeValue( InOutCirc, 85, 0.98f );	makeValue( InOutCirc, 86, 0.98f );
		makeValue( InOutCirc, 87, 0.98f );	makeValue( InOutCirc, 88, 0.99f );
		makeValue( InOutCirc, 89, 0.99f );	makeValue( InOutCirc, 90, 0.99f );

		makeValue( InOutCirc, 91, 0.99f );	makeValue( InOutCirc, 92, 0.99f );
		makeValue( InOutCirc, 93, 0.99f );	makeValue( InOutCirc, 94, 1.00f );
		makeValue( InOutCirc, 95, 1.00f );	makeValue( InOutCirc, 96, 1.00f );
		makeValue( InOutCirc, 97, 1.00f );	makeValue( InOutCirc, 98, 1.00f );
		makeValue( InOutCirc, 99, 1.00f );	makeValue( InOutCirc, 100, 1.0f );
	}

	public void makeOutInCirc()
	{
		//	OutInCirc

		makeValue( OutInCirc, 0, 0.00f );

		makeValue( OutInCirc, 1, 0.10f );	makeValue( OutInCirc, 2, 0.14f );
		makeValue( OutInCirc, 3, 0.17f );	makeValue( OutInCirc, 4, 0.19f );
		makeValue( OutInCirc, 5, 0.22f );	makeValue( OutInCirc, 6, 0.24f );
		makeValue( OutInCirc, 7, 0.25f );	makeValue( OutInCirc, 8, 0.27f );
		makeValue( OutInCirc, 9, 0.28f );	makeValue( OutInCirc, 10, 0.30f );

		makeValue( OutInCirc, 11, 0.31f );	makeValue( OutInCirc, 12, 0.32f );
		makeValue( OutInCirc, 13, 0.33f );	makeValue( OutInCirc, 14, 0.34f );
		makeValue( OutInCirc, 15, 0.35f );	makeValue( OutInCirc, 16, 0.36f );
		makeValue( OutInCirc, 17, 0.37f );	makeValue( OutInCirc, 18, 0.38f );
		makeValue( OutInCirc, 19, 0.39f );	makeValue( OutInCirc, 20, 0.40f );

		makeValue( OutInCirc, 21, 0.41f );	makeValue( OutInCirc, 22, 0.42f );
		makeValue( OutInCirc, 23, 0.42f );	makeValue( OutInCirc, 24, 0.43f );
		makeValue( OutInCirc, 25, 0.44f );	makeValue( OutInCirc, 26, 0.44f );
		makeValue( OutInCirc, 27, 0.45f );	makeValue( OutInCirc, 28, 0.45f );
		makeValue( OutInCirc, 29, 0.46f );	makeValue( OutInCirc, 30, 0.46f );

		makeValue( OutInCirc, 31, 0.46f );	makeValue( OutInCirc, 32, 0.47f );
		makeValue( OutInCirc, 33, 0.47f );	makeValue( OutInCirc, 34, 0.47f );
		makeValue( OutInCirc, 35, 0.48f );	makeValue( OutInCirc, 36, 0.48f );
		makeValue( OutInCirc, 37, 0.48f );	makeValue( OutInCirc, 38, 0.49f );
		makeValue( OutInCirc, 39, 0.49f );	makeValue( OutInCirc, 40, 0.49f );

		makeValue( OutInCirc, 41, 0.49f );	makeValue( OutInCirc, 42, 0.49f );
		makeValue( OutInCirc, 43, 0.49f );	makeValue( OutInCirc, 44, 0.50f );
		makeValue( OutInCirc, 45, 0.50f );	makeValue( OutInCirc, 46, 0.50f );
		makeValue( OutInCirc, 47, 0.50f );	makeValue( OutInCirc, 48, 0.50f );
		makeValue( OutInCirc, 49, 0.50f );	makeValue( OutInCirc, 50, 0.50f );

		makeValue( OutInCirc, 51, 0.50f );	makeValue( OutInCirc, 52, 0.50f );
		makeValue( OutInCirc, 53, 0.50f );	makeValue( OutInCirc, 54, 0.50f );
		makeValue( OutInCirc, 55, 0.50f );	makeValue( OutInCirc, 56, 0.50f );
		makeValue( OutInCirc, 57, 0.50f );	makeValue( OutInCirc, 58, 0.51f );
		makeValue( OutInCirc, 59, 0.51f );	makeValue( OutInCirc, 60, 0.51f );

		makeValue( OutInCirc, 61, 0.51f );	makeValue( OutInCirc, 62, 0.51f );
		makeValue( OutInCirc, 63, 0.52f );	makeValue( OutInCirc, 64, 0.52f );
		makeValue( OutInCirc, 65, 0.52f );	makeValue( OutInCirc, 66, 0.53f );
		makeValue( OutInCirc, 67, 0.53f );	makeValue( OutInCirc, 68, 0.53f );
		makeValue( OutInCirc, 69, 0.54f );	makeValue( OutInCirc, 70, 0.54f );

		makeValue( OutInCirc, 71, 0.55f );	makeValue( OutInCirc, 72, 0.55f );
		makeValue( OutInCirc, 73, 0.56f );	makeValue( OutInCirc, 74, 0.56f );
		makeValue( OutInCirc, 75, 0.57f );	makeValue( OutInCirc, 76, 0.57f );
		makeValue( OutInCirc, 77, 0.58f );	makeValue( OutInCirc, 78, 0.59f );
		makeValue( OutInCirc, 79, 0.59f );	makeValue( OutInCirc, 80, 0.60f );

		makeValue( OutInCirc, 81, 0.61f );	makeValue( OutInCirc, 82, 0.62f );
		makeValue( OutInCirc, 83, 0.62f );	makeValue( OutInCirc, 84, 0.63f );
		makeValue( OutInCirc, 85, 0.64f );	makeValue( OutInCirc, 86, 0.65f );
		makeValue( OutInCirc, 87, 0.66f );	makeValue( OutInCirc, 88, 0.67f );
		makeValue( OutInCirc, 89, 0.69f );	makeValue( OutInCirc, 90, 0.70f );

		makeValue( OutInCirc, 91, 0.71f );	makeValue( OutInCirc, 92, 0.73f );
		makeValue( OutInCirc, 93, 0.74f );	makeValue( OutInCirc, 94, 0.76f );
		makeValue( OutInCirc, 95, 0.78f );	makeValue( OutInCirc, 96, 0.80f );
		makeValue( OutInCirc, 97, 0.82f );	makeValue( OutInCirc, 98, 0.85f );
		makeValue( OutInCirc, 99, 0.90f );	makeValue( OutInCirc, 100, 1.0f );
	}

	public void makeOutInBack()
	{
		// OutInBack

		makeValue( OutInBack, 0, 0.00f );

		makeValue( OutInBack, 1, 0.05f );	makeValue( OutInBack, 2, 0.09f );
		makeValue( OutInBack, 3, 0.13f );	makeValue( OutInBack, 4, 0.17f );
		makeValue( OutInBack, 5, 0.20f );	makeValue( OutInBack, 6, 0.23f );
		makeValue( OutInBack, 7, 0.27f );	makeValue( OutInBack, 8, 0.29f );
		makeValue( OutInBack, 9, 0.32f );	makeValue( OutInBack, 10, 0.35f );

		makeValue( OutInBack, 11, 0.37f );	makeValue( OutInBack, 12, 0.39f );
		makeValue( OutInBack, 13, 0.41f );	makeValue( OutInBack, 14, 0.43f );
		makeValue( OutInBack, 15, 0.45f );	makeValue( OutInBack, 16, 0.46f );
		makeValue( OutInBack, 17, 0.48f );	makeValue( OutInBack, 18, 0.49f );
		makeValue( OutInBack, 19, 0.51f );	makeValue( OutInBack, 20, 0.52f );

		makeValue( OutInBack, 21, 0.53f );	makeValue( OutInBack, 22, 0.53f );
		makeValue( OutInBack, 23, 0.54f );	makeValue( OutInBack, 24, 0.54f );
		makeValue( OutInBack, 25, 0.55f );	makeValue( OutInBack, 26, 0.55f );
		makeValue( OutInBack, 27, 0.55f );	makeValue( OutInBack, 28, 0.55f );
		makeValue( OutInBack, 29, 0.55f );	makeValue( OutInBack, 30, 0.55f );

		makeValue( OutInBack, 31, 0.55f );	makeValue( OutInBack, 32, 0.55f );
		makeValue( OutInBack, 33, 0.54f );	makeValue( OutInBack, 34, 0.54f );
		makeValue( OutInBack, 35, 0.54f );	makeValue( OutInBack, 36, 0.54f );
		makeValue( OutInBack, 37, 0.53f );	makeValue( OutInBack, 38, 0.53f );
		makeValue( OutInBack, 39, 0.53f );	makeValue( OutInBack, 40, 0.52f );

		makeValue( OutInBack, 41, 0.52f );	makeValue( OutInBack, 42, 0.52f );
		makeValue( OutInBack, 43, 0.51f );	makeValue( OutInBack, 44, 0.51f );
		makeValue( OutInBack, 45, 0.51f );	makeValue( OutInBack, 46, 0.50f );
		makeValue( OutInBack, 47, 0.50f );	makeValue( OutInBack, 48, 0.50f );
		makeValue( OutInBack, 49, 0.50f );	makeValue( OutInBack, 50, 0.50f );

		makeValue( OutInBack, 51, 0.50f );	makeValue( OutInBack, 52, 0.50f );
		makeValue( OutInBack, 53, 0.50f );	makeValue( OutInBack, 54, 0.50f );
		makeValue( OutInBack, 55, 0.49f );	makeValue( OutInBack, 56, 0.49f );
		makeValue( OutInBack, 57, 0.49f );	makeValue( OutInBack, 58, 0.49f );
		makeValue( OutInBack, 59, 0.48f );	makeValue( OutInBack, 60, 0.47f );

		makeValue( OutInBack, 61, 0.47f );	makeValue( OutInBack, 62, 0.47f );
		makeValue( OutInBack, 63, 0.46f );	makeValue( OutInBack, 64, 0.46f );
		makeValue( OutInBack, 65, 0.46f );	makeValue( OutInBack, 66, 0.46f );
		makeValue( OutInBack, 67, 0.45f );	makeValue( OutInBack, 68, 0.45f );
		makeValue( OutInBack, 69, 0.45f );	makeValue( OutInBack, 70, 0.45f );

		makeValue( OutInBack, 71, 0.45f );	makeValue( OutInBack, 72, 0.45f );
		makeValue( OutInBack, 73, 0.45f );	makeValue( OutInBack, 74, 0.45f );
		makeValue( OutInBack, 75, 0.46f );	makeValue( OutInBack, 76, 0.46f );
		makeValue( OutInBack, 77, 0.47f );	makeValue( OutInBack, 78, 0.47f );
		makeValue( OutInBack, 79, 0.48f );	makeValue( OutInBack, 80, 0.49f );

		makeValue( OutInBack, 81, 0.50f );	makeValue( OutInBack, 82, 0.51f );
		makeValue( OutInBack, 83, 0.52f );	makeValue( OutInBack, 84, 0.53f );
		makeValue( OutInBack, 85, 0.55f );	makeValue( OutInBack, 86, 0.56f );
		makeValue( OutInBack, 87, 0.58f );	makeValue( OutInBack, 88, 0.60f );
		makeValue( OutInBack, 89, 0.62f );	makeValue( OutInBack, 90, 0.64f );

		makeValue( OutInBack, 91, 0.67f );	makeValue( OutInBack, 92, 0.69f );
		makeValue( OutInBack, 93, 0.72f );	makeValue( OutInBack, 94, 0.75f );
		makeValue( OutInBack, 95, 0.79f );	makeValue( OutInBack, 96, 0.82f );
		makeValue( OutInBack, 97, 0.86f );	makeValue( OutInBack, 98, 0.90f );
		makeValue( OutInBack, 99, 0.94f );	makeValue( OutInBack, 100, 1.0f );
	}

	public void makeInBounce()
	{
		// InBounce

		makeValue( InBounce, 0, 0.00f );

		makeValue( InBounce, 1, 0.01f );	makeValue( InBounce, 2, 0.01f );
		makeValue( InBounce, 3, 0.01f );	makeValue( InBounce, 4, 0.00f );
		makeValue( InBounce, 5, 0.00f );	makeValue( InBounce, 6, 0.02f );
		makeValue( InBounce, 7, 0.02f );	makeValue( InBounce, 8, 0.03f );
		makeValue( InBounce, 9, 0.03f );	makeValue( InBounce, 10, 0.03f );

		makeValue( InBounce, 11, 0.03f );	makeValue( InBounce, 12, 0.02f );
		makeValue( InBounce, 13, 0.01f );	makeValue( InBounce, 14, 0.00f );
		makeValue( InBounce, 15, 0.03f );	makeValue( InBounce, 16, 0.05f );
		makeValue( InBounce, 17, 0.07f );	makeValue( InBounce, 18, 0.08f );
		makeValue( InBounce, 19, 0.11f );	makeValue( InBounce, 20, 0.12f );

		makeValue( InBounce, 21, 0.12f );	makeValue( InBounce, 22, 0.12f );
		makeValue( InBounce, 23, 0.12f );	makeValue( InBounce, 24, 0.12f );
		makeValue( InBounce, 25, 0.11f );	makeValue( InBounce, 26, 0.10f );
		makeValue( InBounce, 27, 0.09f );	makeValue( InBounce, 28, 0.08f );
		makeValue( InBounce, 29, 0.06f );	makeValue( InBounce, 30, 0.04f );

		makeValue( InBounce, 31, 0.02f );	makeValue( InBounce, 32, 0.02f );
		makeValue( InBounce, 33, 0.07f );	makeValue( InBounce, 34, 0.12f );
		makeValue( InBounce, 35, 0.17f );	makeValue( InBounce, 36, 0.21f );
		makeValue( InBounce, 37, 0.25f );	makeValue( InBounce, 38, 0.28f );
		makeValue( InBounce, 39, 0.32f );	makeValue( InBounce, 40, 0.35f );

		makeValue( InBounce, 41, 0.38f );	makeValue( InBounce, 42, 0.40f );
		makeValue( InBounce, 43, 0.42f );	makeValue( InBounce, 44, 0.44f );
		makeValue( InBounce, 45, 0.46f );	makeValue( InBounce, 46, 0.47f );
		makeValue( InBounce, 47, 0.49f );	makeValue( InBounce, 48, 0.49f );
		makeValue( InBounce, 49, 0.50f );	makeValue( InBounce, 50, 0.50f );

		makeValue( InBounce, 51, 0.50f );	makeValue( InBounce, 52, 0.50f );
		makeValue( InBounce, 53, 0.51f );	makeValue( InBounce, 54, 0.52f );
		makeValue( InBounce, 55, 0.53f );	makeValue( InBounce, 56, 0.55f );
		makeValue( InBounce, 57, 0.57f );	makeValue( InBounce, 58, 0.59f );
		makeValue( InBounce, 59, 0.61f );	makeValue( InBounce, 60, 0.67f );

		makeValue( InBounce, 61, 0.70f );	makeValue( InBounce, 62, 0.74f );
		makeValue( InBounce, 63, 0.78f );	makeValue( InBounce, 64, 0.82f );
		makeValue( InBounce, 65, 0.86f );	makeValue( InBounce, 66, 0.91f );
		makeValue( InBounce, 67, 0.96f );	makeValue( InBounce, 68, 0.99f );
		makeValue( InBounce, 69, 0.97f );	makeValue( InBounce, 70, 0.95f );

		makeValue( InBounce, 71, 0.93f );	makeValue( InBounce, 72, 0.91f );
		makeValue( InBounce, 73, 0.90f );	makeValue( InBounce, 74, 0.89f );
		makeValue( InBounce, 75, 0.88f );	makeValue( InBounce, 76, 0.88f );
		makeValue( InBounce, 77, 0.88f );	makeValue( InBounce, 78, 0.88f );
		makeValue( InBounce, 79, 0.88f );	makeValue( InBounce, 80, 0.89f );

		makeValue( InBounce, 81, 0.90f );	makeValue( InBounce, 82, 0.91f );
		makeValue( InBounce, 83, 0.92f );	makeValue( InBounce, 84, 0.94f );
		makeValue( InBounce, 85, 0.96f );	makeValue( InBounce, 86, 0.99f );
		makeValue( InBounce, 87, 0.99f );	makeValue( InBounce, 88, 0.98f );
		makeValue( InBounce, 89, 0.98f );	makeValue( InBounce, 90, 0.97f );

		makeValue( InBounce, 91, 0.97f );	makeValue( InBounce, 92, 0.97f );
		makeValue( InBounce, 93, 0.97f );	makeValue( InBounce, 94, 0.98f );
		makeValue( InBounce, 95, 0.99f );	makeValue( InBounce, 96, 1.00f );
		makeValue( InBounce, 97, 0.99f );	makeValue( InBounce, 98, 0.99f );
		makeValue( InBounce, 99, 0.99f );	makeValue( InBounce, 100, 1.0f );
	}

	public void makeOutBounce()
	{
		// OutBounce

		makeValue( OutBounce, 0, 0.00f );

		makeValue( OutBounce, 1, 0.00f );	makeValue( OutBounce, 2, 0.00f );
		makeValue( OutBounce, 3, 0.01f );	makeValue( OutBounce, 4, 0.01f );
		makeValue( OutBounce, 5, 0.02f );	makeValue( OutBounce, 6, 0.03f );
		makeValue( OutBounce, 7, 0.04f );	makeValue( OutBounce, 8, 0.06f );
		makeValue( OutBounce, 9, 0.07f );	makeValue( OutBounce, 10, 0.09f );

		makeValue( OutBounce, 11, 0.10f );	makeValue( OutBounce, 12, 0.12f );
		makeValue( OutBounce, 13, 0.14f );	makeValue( OutBounce, 14, 0.16f );
		makeValue( OutBounce, 15, 0.18f );	makeValue( OutBounce, 16, 0.21f );
		makeValue( OutBounce, 17, 0.23f );	makeValue( OutBounce, 18, 0.26f );
		makeValue( OutBounce, 19, 0.29f );	makeValue( OutBounce, 20, 0.32f );

		makeValue( OutBounce, 21, 0.35f );	makeValue( OutBounce, 22, 0.38f );
		makeValue( OutBounce, 23, 0.42f );	makeValue( OutBounce, 24, 0.45f );
		makeValue( OutBounce, 25, 0.49f );	makeValue( OutBounce, 26, 0.53f );
		makeValue( OutBounce, 27, 0.56f );	makeValue( OutBounce, 28, 0.61f );
		makeValue( OutBounce, 29, 0.65f );	makeValue( OutBounce, 30, 0.69f );

		makeValue( OutBounce, 31, 0.74f );	makeValue( OutBounce, 32, 0.79f );
		makeValue( OutBounce, 33, 0.83f );	makeValue( OutBounce, 34, 0.88f );
		makeValue( OutBounce, 35, 0.93f );	makeValue( OutBounce, 36, 0.99f );
		makeValue( OutBounce, 37, 0.98f );	makeValue( OutBounce, 38, 0.96f );
		makeValue( OutBounce, 39, 0.93f );	makeValue( OutBounce, 40, 0.91f );

		makeValue( OutBounce, 41, 0.89f );	makeValue( OutBounce, 42, 0.87f );
		makeValue( OutBounce, 43, 0.85f );	makeValue( OutBounce, 44, 0.84f );
		makeValue( OutBounce, 45, 0.82f );	makeValue( OutBounce, 46, 0.81f );
		makeValue( OutBounce, 47, 0.79f );	makeValue( OutBounce, 48, 0.78f );
		makeValue( OutBounce, 49, 0.77f );	makeValue( OutBounce, 50, 0.76f );

		makeValue( OutBounce, 51, 0.76f );	makeValue( OutBounce, 52, 0.75f );
		makeValue( OutBounce, 53, 0.75f );	makeValue( OutBounce, 54, 0.75f );
		makeValue( OutBounce, 55, 0.75f );	makeValue( OutBounce, 56, 0.75f );
		makeValue( OutBounce, 57, 0.76f );	makeValue( OutBounce, 58, 0.76f );
		makeValue( OutBounce, 59, 0.77f );	makeValue( OutBounce, 60, 0.78f );

		makeValue( OutBounce, 61, 0.79f );	makeValue( OutBounce, 62, 0.80f );
		makeValue( OutBounce, 63, 0.81f );	makeValue( OutBounce, 64, 0.82f );
		makeValue( OutBounce, 65, 0.84f );	makeValue( OutBounce, 66, 0.86f );
		makeValue( OutBounce, 67, 0.87f );	makeValue( OutBounce, 68, 0.89f );
		makeValue( OutBounce, 69, 0.91f );	makeValue( OutBounce, 70, 0.94f );

		makeValue( OutBounce, 71, 0.96f );	makeValue( OutBounce, 72, 0.99f );
		makeValue( OutBounce, 73, 0.99f );	makeValue( OutBounce, 74, 0.98f );
		makeValue( OutBounce, 75, 0.97f );	makeValue( OutBounce, 76, 0.96f );
		makeValue( OutBounce, 77, 0.95f );	makeValue( OutBounce, 78, 0.95f );
		makeValue( OutBounce, 79, 0.94f );	makeValue( OutBounce, 80, 0.94f );

		makeValue( OutBounce, 81, 0.94f );	makeValue( OutBounce, 82, 0.94f );
		makeValue( OutBounce, 83, 0.94f );	makeValue( OutBounce, 84, 0.94f );
		makeValue( OutBounce, 85, 0.94f );	makeValue( OutBounce, 86, 0.95f );
		makeValue( OutBounce, 87, 0.96f );	makeValue( OutBounce, 88, 0.97f );
		makeValue( OutBounce, 89, 0.98f );	makeValue( OutBounce, 90, 0.99f );

		makeValue( OutBounce, 91, 1.00f );	makeValue( OutBounce, 92, 0.99f );
		makeValue( OutBounce, 93, 0.99f );	makeValue( OutBounce, 94, 0.98f );
		makeValue( OutBounce, 95, 0.98f );	makeValue( OutBounce, 96, 0.99f );
		makeValue( OutBounce, 97, 0.99f );	makeValue( OutBounce, 98, 0.99f );
		makeValue( OutBounce, 99, 1.00f );	makeValue( OutBounce, 100, 1.0f );
	}

	public void makeInOutBounce()
	{
		// InOutBounce

		makeValue( InOutBounce, 0, 0.00f );

		makeValue( InOutBounce, 1, 0.01f );	makeValue( InOutBounce, 2, 0.01f );
		makeValue( InOutBounce, 3, 0.01f );	makeValue( InOutBounce, 4, 0.00f );
		makeValue( InOutBounce, 5, 0.00f );	makeValue( InOutBounce, 6, 0.02f );
		makeValue( InOutBounce, 7, 0.02f );	makeValue( InOutBounce, 8, 0.03f );
		makeValue( InOutBounce, 9, 0.03f );	makeValue( InOutBounce, 10, 0.03f );

		makeValue( InOutBounce, 11, 0.03f );	makeValue( InOutBounce, 12, 0.02f );
		makeValue( InOutBounce, 13, 0.01f );	makeValue( InOutBounce, 14, 0.00f );
		makeValue( InOutBounce, 15, 0.03f );	makeValue( InOutBounce, 16, 0.05f );
		makeValue( InOutBounce, 17, 0.07f );	makeValue( InOutBounce, 18, 0.08f );
		makeValue( InOutBounce, 19, 0.11f );	makeValue( InOutBounce, 20, 0.12f );

		makeValue( InOutBounce, 21, 0.12f );	makeValue( InOutBounce, 22, 0.12f );
		makeValue( InOutBounce, 23, 0.12f );	makeValue( InOutBounce, 24, 0.12f );
		makeValue( InOutBounce, 25, 0.11f );	makeValue( InOutBounce, 26, 0.10f );
		makeValue( InOutBounce, 27, 0.09f );	makeValue( InOutBounce, 28, 0.08f );
		makeValue( InOutBounce, 29, 0.06f );	makeValue( InOutBounce, 30, 0.04f );

		makeValue( InOutBounce, 31, 0.02f );	makeValue( InOutBounce, 32, 0.02f );
		makeValue( InOutBounce, 33, 0.07f );	makeValue( InOutBounce, 34, 0.12f );
		makeValue( InOutBounce, 35, 0.17f );	makeValue( InOutBounce, 36, 0.21f );
		makeValue( InOutBounce, 37, 0.25f );	makeValue( InOutBounce, 38, 0.28f );
		makeValue( InOutBounce, 39, 0.32f );	makeValue( InOutBounce, 40, 0.35f );

		makeValue( InOutBounce, 41, 0.38f );	makeValue( InOutBounce, 42, 0.40f );
		makeValue( InOutBounce, 43, 0.42f );	makeValue( InOutBounce, 44, 0.44f );
		makeValue( InOutBounce, 45, 0.46f );	makeValue( InOutBounce, 46, 0.47f );
		makeValue( InOutBounce, 47, 0.49f );	makeValue( InOutBounce, 48, 0.49f );
		makeValue( InOutBounce, 49, 0.50f );	makeValue( InOutBounce, 50, 0.50f );

		makeValue( InOutBounce, 51, 0.50f );	makeValue( InOutBounce, 52, 0.50f );
		makeValue( InOutBounce, 53, 0.51f );	makeValue( InOutBounce, 54, 0.52f );
		makeValue( InOutBounce, 55, 0.53f );	makeValue( InOutBounce, 56, 0.55f );
		makeValue( InOutBounce, 57, 0.57f );	makeValue( InOutBounce, 58, 0.59f );
		makeValue( InOutBounce, 59, 0.61f );	makeValue( InOutBounce, 60, 0.67f );

		makeValue( InOutBounce, 61, 0.70f );	makeValue( InOutBounce, 62, 0.74f );
		makeValue( InOutBounce, 63, 0.78f );	makeValue( InOutBounce, 64, 0.82f );
		makeValue( InOutBounce, 65, 0.86f );	makeValue( InOutBounce, 66, 0.91f );
		makeValue( InOutBounce, 67, 0.96f );	makeValue( InOutBounce, 68, 0.99f );
		makeValue( InOutBounce, 69, 0.97f );	makeValue( InOutBounce, 70, 0.95f );

		makeValue( InOutBounce, 71, 0.93f );	makeValue( InOutBounce, 72, 0.91f );
		makeValue( InOutBounce, 73, 0.90f );	makeValue( InOutBounce, 74, 0.89f );
		makeValue( InOutBounce, 75, 0.88f );	makeValue( InOutBounce, 76, 0.88f );
		makeValue( InOutBounce, 77, 0.88f );	makeValue( InOutBounce, 78, 0.88f );
		makeValue( InOutBounce, 79, 0.88f );	makeValue( InOutBounce, 80, 0.89f );

		makeValue( InOutBounce, 81, 0.90f );	makeValue( InOutBounce, 82, 0.91f );
		makeValue( InOutBounce, 83, 0.92f );	makeValue( InOutBounce, 84, 0.94f );
		makeValue( InOutBounce, 85, 0.96f );	makeValue( InOutBounce, 86, 0.99f );
		makeValue( InOutBounce, 87, 0.99f );	makeValue( InOutBounce, 88, 0.98f );
		makeValue( InOutBounce, 89, 0.98f );	makeValue( InOutBounce, 90, 0.97f );

		makeValue( InOutBounce, 91, 0.97f );	makeValue( InOutBounce, 92, 0.97f );
		makeValue( InOutBounce, 93, 0.97f );	makeValue( InOutBounce, 94, 0.98f );
		makeValue( InOutBounce, 95, 0.99f );	makeValue( InOutBounce, 96, 1.00f );
		makeValue( InOutBounce, 97, 0.99f );	makeValue( InOutBounce, 98, 0.99f );
		makeValue( InOutBounce, 99, 0.99f );	makeValue( InOutBounce, 100, 1.0f );
	}

	public void makeOutInBounce()
	{
		// OutInBounce

		makeValue( OutInBounce, 0, 0.00f );

		makeValue( OutInBounce, 1, 0.00f );	makeValue( OutInBounce, 2, 0.01f );
		makeValue( OutInBounce, 3, 0.01f );	makeValue( OutInBounce, 4, 0.02f );
		makeValue( OutInBounce, 5, 0.04f );	makeValue( OutInBounce, 6, 0.05f );
		makeValue( OutInBounce, 7, 0.07f );	makeValue( OutInBounce, 8, 0.09f );
		makeValue( OutInBounce, 9, 0.12f );	makeValue( OutInBounce, 10, 0.14f );

		makeValue( OutInBounce, 11, 0.17f );	makeValue( OutInBounce, 12, 0.21f );
		makeValue( OutInBounce, 13, 0.24f );	makeValue( OutInBounce, 14, 0.28f );
		makeValue( OutInBounce, 15, 0.32f );	makeValue( OutInBounce, 16, 0.37f );
		makeValue( OutInBounce, 17, 0.41f );	makeValue( OutInBounce, 18, 0.47f );
		makeValue( OutInBounce, 19, 0.48f );	makeValue( OutInBounce, 20, 0.43f );

		makeValue( OutInBounce, 21, 0.39f );	makeValue( OutInBounce, 22, 0.35f );
		makeValue( OutInBounce, 23, 0.32f );	makeValue( OutInBounce, 24, 0.29f );
		makeValue( OutInBounce, 25, 0.28f );	makeValue( OutInBounce, 26, 0.26f );
		makeValue( OutInBounce, 27, 0.25f );	makeValue( OutInBounce, 28, 0.25f );
		makeValue( OutInBounce, 29, 0.25f );	makeValue( OutInBounce, 30, 0.26f );

		makeValue( OutInBounce, 31, 0.28f );	makeValue( OutInBounce, 32, 0.30f );
		makeValue( OutInBounce, 33, 0.32f );	makeValue( OutInBounce, 34, 0.36f );
		makeValue( OutInBounce, 35, 0.39f );	makeValue( OutInBounce, 36, 0.44f );
		makeValue( OutInBounce, 37, 0.49f );	makeValue( OutInBounce, 38, 0.48f );
		makeValue( OutInBounce, 39, 0.46f );	makeValue( OutInBounce, 40, 0.44f );

		makeValue( OutInBounce, 41, 0.44f );	makeValue( OutInBounce, 42, 0.44f );
		makeValue( OutInBounce, 43, 0.45f );	makeValue( OutInBounce, 44, 0.47f );
		makeValue( OutInBounce, 45, 0.49f );	makeValue( OutInBounce, 46, 0.49f );
		makeValue( OutInBounce, 47, 0.49f );	makeValue( OutInBounce, 48, 0.48f );
		makeValue( OutInBounce, 49, 0.49f );	makeValue( OutInBounce, 50, 0.50f );

		makeValue( OutInBounce, 51, 0.51f );	makeValue( OutInBounce, 52, 0.51f );
		makeValue( OutInBounce, 53, 0.52f );	makeValue( OutInBounce, 54, 0.51f );
		makeValue( OutInBounce, 55, 0.50f );	makeValue( OutInBounce, 56, 0.53f );
		makeValue( OutInBounce, 57, 0.54f );	makeValue( OutInBounce, 58, 0.56f );
		makeValue( OutInBounce, 59, 0.56f );	makeValue( OutInBounce, 60, 0.56f );

		makeValue( OutInBounce, 61, 0.56f );	makeValue( OutInBounce, 62, 0.54f );
		makeValue( OutInBounce, 63, 0.53f );	makeValue( OutInBounce, 64, 0.51f );
		makeValue( OutInBounce, 65, 0.54f );	makeValue( OutInBounce, 66, 0.59f );
		makeValue( OutInBounce, 67, 0.63f );	makeValue( OutInBounce, 68, 0.66f );
		makeValue( OutInBounce, 69, 0.69f );	makeValue( OutInBounce, 70, 0.71f );

		makeValue( OutInBounce, 71, 0.73f );	makeValue( OutInBounce, 72, 0.74f );
		makeValue( OutInBounce, 73, 0.75f );	makeValue( OutInBounce, 74, 0.75f );
		makeValue( OutInBounce, 75, 0.74f );	makeValue( OutInBounce, 76, 0.73f );
		makeValue( OutInBounce, 77, 0.72f );	makeValue( OutInBounce, 78, 0.69f );
		makeValue( OutInBounce, 79, 0.66f );	makeValue( OutInBounce, 80, 0.63f );

		makeValue( OutInBounce, 81, 0.54f );	makeValue( OutInBounce, 82, 0.51f );
		makeValue( OutInBounce, 83, 0.56f );	makeValue( OutInBounce, 84, 0.61f );
		makeValue( OutInBounce, 85, 0.66f );	makeValue( OutInBounce, 86, 0.70f );
		makeValue( OutInBounce, 87, 0.74f );	makeValue( OutInBounce, 88, 0.78f );
		makeValue( OutInBounce, 89, 0.81f );	makeValue( OutInBounce, 90, 0.84f );

		makeValue( OutInBounce, 91, 0.87f );	makeValue( OutInBounce, 92, 0.90f );
		makeValue( OutInBounce, 93, 0.92f );	makeValue( OutInBounce, 94, 0.94f );
		makeValue( OutInBounce, 95, 0.96f );	makeValue( OutInBounce, 96, 0.97f );
		makeValue( OutInBounce, 97, 0.98f );	makeValue( OutInBounce, 98, 0.99f );
		makeValue( OutInBounce, 99, 1.00f );	makeValue( OutInBounce, 100, 1.0f );
	}
	
}
	
	