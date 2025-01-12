package pokemon_music_game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop; //곡 무한반복할건지
	
	//버퍼
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //파일을 버퍼에 담음
			player = new Player(bis); 		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //현재 실행되고 있는 음악의 재생위치 반환
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() { //음악이 어떤 상태든 종료하는 함수
		isLoop = false;
		player.close();
		this.interrupt(); //쓰레드 중지
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); //파일을 버퍼에 담음
				player = new Player(bis); 
			}while(isLoop); //true면 곡 무한반복 재생
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
