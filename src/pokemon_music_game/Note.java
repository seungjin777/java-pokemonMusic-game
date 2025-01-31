package pokemon_music_game;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage(); //��Ʈ �̹���
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true; //���� ��Ʈ�� ���� ����
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() { //��Ʈ�� �������� �ʰ�
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}
		else if(noteType.equals("D")){
			x = 332;
		}
		else if(noteType.equals("F")){
			x = 436;
		}
		else if(noteType.equals("Space")){
			x = 540;
		}
		else if(noteType.equals("J")){
			x = 744;
		}
		else if(noteType.equals("K")){
			x = 848;
		}
		else if(noteType.equals("L")){
			x = 952;
		}
		this.noteType = noteType;
	}
	
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else 
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) { //��Ʈ�� �����ٸ� ���
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() { //��Ʈ ���� �Լ�
		if(y >= 613) {
			System.out.println("Late");
			close();
			return "Late";
		}
		else if(y >= 580) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 540) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y >= 520) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 500) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
