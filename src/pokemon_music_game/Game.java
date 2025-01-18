package pokemon_music_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	//게임 진행 관련 이미지
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage(); //노트 경로 경계선 이미지
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage(); //노트 판정 바
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage(); //게임 정보 이미지
	
	//노트 판정 판
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //노트 경로 이미지
	
	//게임 진행 관련 정보들
 	private String titleName; //곡 이름
	private String difficulty; //난이도
	private String musicTitle; //곡 제목
	private Music gameMusic; //곡 담을 변수
	
	//노트 관리 배열
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		gameMusic.start();
		dropNotes(titleName);
	}
	
	public void screenDraw(Graphics2D g) {
		
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		
		//각 노트들의 위치 그려주기
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			note.screenDraw(g);
		}
		
		//노트 판정 라인 그려기
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //폰트 화질 높이기
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702); //곡 제목 출력
		g.drawString(difficulty, 1190, 702); //곡 난이도 출력
		
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702); //점수 출력
	}
	
	public void pressS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	@Override
	public void run() {
	
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		//예시
		Note note = new Note(228, "short");
		note.start();
		noteList.add(note);
	}
}
