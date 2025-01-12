package pokemon_music_game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	private Player player;
	private boolean isLoop; //�� ���ѹݺ��Ұ���
	
	//����
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); //������ ���ۿ� ����
			player = new Player(bis); 		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() { //���� ����ǰ� �ִ� ������ �����ġ ��ȯ
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() { //������ � ���µ� �����ϴ� �Լ�
		isLoop = false;
		player.close();
		this.interrupt(); //������ ����
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); //������ ���ۿ� ����
				player = new Player(bis); 
			}while(isLoop); //true�� �� ���ѹݺ� ���
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
