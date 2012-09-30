
package com.sgsong.Struct;

//nick: SG Song
//e-mail: girlae@nate.com

public class Def
{
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int DOWN = 3;
	
	public final static int PRECISION = 16;

	// texture
	public final static int MAX_TEX_SIZE = 47;	

	public final static int game_font = 0;
	
	public final static int loading_bg = 1;
	public final static int loading_bar = 2;	
	
	public final static int btn_start = 3;	
	
	public final static int game_bg = 4;
	public final static int gostop_card = 5;
	public final static int card_impact = 6;		
	public final static int mini_card = 7;
	
	public final static int bbuck = 8;
	public final static int bbuck_one = 9;	
	public final static int bbuck_two = 10;
	public final static int bbuck_three = 11;
	
	public final static int chodan = 12;
	public final static int chungdan = 13;
	public final static int godori = 14;
	public final static int hongdan = 15;	
	public final static int jjock = 16;
	public final static int ssul = 17;
	public final static int mung_guri = 18;	
	
	public final static int ddadack = 19;
	public final static int ddadack_one = 20;
	
	public final static int go_one = 21;
	public final static int go_two = 22;
	public final static int go_three = 23;
	public final static int go_four = 24;	
	public final static int go_five = 25;		
	public final static int go_six = 26;
	public final static int stop = 27;
	
	public final static int message_box_gostop = 28;		
	public final static int btn_go = 29;
	public final static int btn_stop = 30;
	
	public final static int message_box_select_card = 31;		
	public final static int message_box_buttons = 32;
	public final static int message_box_gookgin = 33;		
	public final static int message_box_result = 34;
	
	public final static int mark_sun = 35;		
	public final static int mark_win_lose = 36;
	public final static int hint_pe = 37;		
	public final static int turn_frame = 38;
	
	public final static int number_big = 39;		
	public final static int number_blue = 40;
	public final static int number_dark_yellow = 41;		
	public final static int number_red = 42;
	public final static int number_yellow = 43;		
	public final static int comma_blue = 44;
	public final static int comma_red = 45;	
	
	public final static int eat_bbuck = 46;
	// texture end

	public final static int sg_color_red = 0;
	public final static int sg_color_green = 1;
	public final static int sg_color_blue = 2;
	public final static int sg_color_black = 3;
	public final static int sg_color_yellow = 4;
	public final static int sg_color_normal = 5;	
	
	public int MobileWidth = 0;
	public int MobileHeight = 0;

	public Def()
	{
	}
	
	public void setWidth( int nWidth )
	{
		MobileWidth = nWidth;
	}
	
	public void setHeight( int nHeight )
	{
		MobileHeight = nHeight;
	}

	public static int FixedFromInt(int value) {
		return value << PRECISION;
	};
}
