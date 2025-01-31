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
	private Image blueFlareImage; 
	private Image judgeImage;
	
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
			if(note.getY() > 620){
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}else {
				note.screenDraw(g);
			}
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
		g.drawImage(blueFlareImage, 460, 410, null);
		g.drawImage(judgeImage, 460, 370, null);
	}
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //눌렸을때 이미지로 변경
		new Music("note.mp3", false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //눌렸을때 이미지로 변경		
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if(titleName.equals("Route - 201") && difficulty.equals("Easy")) {
			int startTime = 4000 - Main.REACH_TIME * 1000;
			
			int repeat = 23500 + 4500; //도돌이표
			beats = new Beat[] {
					//2마디
					new Beat(startTime + 0, "S"),
					new Beat(startTime + 200, "F"),
					new Beat(startTime + 400, "Space"),
					
					new Beat(startTime + 1000, "L"),
					new Beat(startTime + 1200, "J"),
					new Beat(startTime + 1400, "Space"),
					
					//3마디
					new Beat(startTime + 2000, "S"),
					new Beat(startTime + 2200, "F"),
					new Beat(startTime + 2400, "Space"),
					
					new Beat(startTime + 3000, "L"),
					new Beat(startTime + 3200, "J"),
					new Beat(startTime + 3400, "Space"),
					
					//4마디
					new Beat(startTime + 4000, "S"),
					new Beat(startTime + 4200, "F"),
					new Beat(startTime + 4400, "Space"),
					
					new Beat(startTime + 5000, "L"),
					new Beat(startTime + 5200, "J"),
					new Beat(startTime + 5400, "Space"),
					
					//5마디
					new Beat(startTime + 6000, "S"),
					new Beat(startTime + 6200, "F"),
					new Beat(startTime + 6400, "Space"),
					
					new Beat(startTime + 7000, "L"),
					new Beat(startTime + 7200, "J"),
					new Beat(startTime + 7400, "Space"), //start + 7400s
					//-------------------------------------------------------
					//6마디
					new Beat(startTime + 7800, "S"), //500 간격
					new Beat(startTime + 8300, "L"),
					new Beat(startTime + 8800, "S"),
					new Beat(startTime + 9300, "L"),
					
					//7마디 스킵 (2600)
					
					//8마디
					new Beat(startTime + 11900, "S"), //500 간격
					new Beat(startTime + 12400, "L"),
					new Beat(startTime + 12900, "S"),
					new Beat(startTime + 13400, "L"),
					
					//9마디 스킵 (2600)
					
					//10마디
					new Beat(startTime + 15700, "S"), //--빠아아바아바암 
					new Beat(startTime + 16500, "D"), //800 간격
					new Beat(startTime + 17300, "F"),
							
					//11마디  600 쉬고
					new Beat(startTime + 17900, "L"), 
					new Beat(startTime + 18400, "K"), //500 간격
					new Beat(startTime + 18900, "J"),
					new Beat(startTime + 19300, "Space"),
					
					//12마디 400 쉬고
					new Beat(startTime + 19800, "S"), //--빠아아바아바암 
					new Beat(startTime + 20600, "D"), //800 간격
					new Beat(startTime + 21400, "F"),
							
					//13마디  600 쉬고
					new Beat(startTime + 22000, "L"), 
					new Beat(startTime + 22500, "K"), //500 간격
					new Beat(startTime + 23000, "J"),
					new Beat(startTime + 23400, "Space"),
					
					//---------도돌이표-------------------------------------------------------------//
					
					//2마디
					new Beat(startTime + repeat + 0, "S"),
					new Beat(startTime + repeat + 200, "F"),
					new Beat(startTime + repeat + 400, "Space"),
					
					new Beat(startTime + repeat + 1000, "L"),
					new Beat(startTime + repeat + 1200, "J"),
					new Beat(startTime + repeat + 1400, "Space"),
					
					//3마디
					new Beat(startTime + repeat + 2000, "S"),
					new Beat(startTime + repeat + 2200, "F"),
					new Beat(startTime + repeat + 2400, "Space"),
					
					new Beat(startTime + repeat + 3000, "L"),
					new Beat(startTime + repeat + 3200, "J"),
					new Beat(startTime + repeat + 3400, "Space"),
					
					//4마디
					new Beat(startTime + repeat + 4000, "S"),
					new Beat(startTime + repeat + 4200, "F"),
					new Beat(startTime + repeat + 4400, "Space"),
					
					new Beat(startTime + repeat + 5000, "L"),
					new Beat(startTime + repeat + 5200, "J"),
					new Beat(startTime + repeat + 5400, "Space"),
					
					//5마디
					new Beat(startTime + repeat + 6000, "S"),
					new Beat(startTime + repeat + 6200, "F"),
					new Beat(startTime + repeat + 6400, "Space"),
					
					new Beat(startTime + repeat + 7000, "L"),
					new Beat(startTime + repeat + 7200, "J"),
					new Beat(startTime + repeat + 7400, "Space"), //start + 7400s
					//-------------------------------------------------------
					//6마디
					new Beat(startTime + repeat + 7800, "S"), //500 간격
					new Beat(startTime + repeat + 8300, "L"),
					new Beat(startTime + repeat + 8800, "S"),
					new Beat(startTime + repeat + 9300, "L"),
					
					//7마디 스킵 (2600)
					
					//8마디
					new Beat(startTime + repeat + 11900, "S"), //500 간격
					new Beat(startTime + repeat + 12400, "L"),
					new Beat(startTime + repeat + 12900, "S"),
					new Beat(startTime + repeat + 13400, "L"),
					
					//9마디 스킵 (2600)
					
					//10마디
					new Beat(startTime + repeat + 15700, "S"), //--빠아아바아바암 
					new Beat(startTime + repeat + 16500, "D"), //800 간격
					new Beat(startTime + repeat + 17300, "F"),
							
					//11마디  600 쉬고
					new Beat(startTime + repeat + 17900, "L"), 
					new Beat(startTime + repeat + 18400, "K"), //500 간격
					new Beat(startTime + repeat + 18900, "J"),
					new Beat(startTime + repeat + 19300, "Space"),
					
					//12마디 400 쉬고
					new Beat(startTime + repeat + 19800, "S"), //--빠아아바아바암 
					new Beat(startTime + repeat + 20600, "D"), //800 간격
					new Beat(startTime + repeat + 21400, "F"),
							
					//13마디  600 쉬고
					new Beat(startTime + repeat + 22000, "L"), 
					new Beat(startTime + repeat + 22500, "K"), //500 간격
					new Beat(startTime + repeat + 23000, "J"),
					new Beat(startTime + repeat + 23400, "Space"),
					
			};
		}
		else if(titleName.equals("Route - 201") && difficulty.equals("hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 너무 힘드렁 ㅋㅋ
			};
		}
		else if(titleName.equals("Route - 209 8bit") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 너무 힘드렁 ㅋㅋ
			};
		}
		else if(titleName.equals("Route - 209 8bit") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 너무 힘드렁 ㅋㅋ
			};
		}
		else if(titleName.equals("Ending - 209 8bit") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 너무 힘드렁 ㅋㅋ
			};
		}
		else if(titleName.equals("Route - 209 8bit") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 너무 힘드렁 ㅋㅋ
			};
		}
			
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(InterruptedException e) {
					//e.printStackTrace();
				}
			}
		}
	}
	
	public void judge(String input) { //노트 판정 함수
		for(int i=0; i<noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		if(!judge.equals("None")){
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
		}else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
		}else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
		}else if(judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}else if(judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		}
		
	}
}
