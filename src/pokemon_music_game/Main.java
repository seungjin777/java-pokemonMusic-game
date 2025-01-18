package pokemon_music_game;

public class Main {
	//해상도
	public static final int SCREEN_WIDTH = 1280; //창 가로크기
	public static final int SCREEN_HEIGHT = 720; //창 세로크기
	public static final int NOTE_SPEED = 7; //노트 떨어지는 속도
	public static final int SLEEP_TIME = 10; //노트 떨어지는 주기
	
	public static void main(String[] args) {
		new DynamicBeat();
	
	}
}
