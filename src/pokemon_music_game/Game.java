package pokemon_music_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	//惟績 遭楳 淫恵 戚耕走
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage(); //葛闘 井稽 井域識 戚耕走
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage(); //葛闘 毒舛 郊
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage(); //惟績 舛左 戚耕走
	private Image blueFlareImage; 
	private Image judgeImage;
	
	//葛闘 毒舛 毒
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //葛闘 井稽 戚耕走
	
	//惟績 遭楳 淫恵 舛左級
 	private String titleName; //溢 戚硯
	private String difficulty; //貝戚亀
	private String musicTitle; //溢 薦鯉
	private Music gameMusic; //溢 眼聖 痕呪
	
	//葛闘 淫軒 壕伸
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
		
		//唖 葛闘級税 是帖 益形爽奄
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
		
		//葛闘 毒舛 虞昔 益形奄
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
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //肉闘 鉢霜 株戚奄
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702); //溢 薦鯉 窒径
		g.drawString(difficulty, 1190, 702); //溢 貝戚亀 窒径
		
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
		g.drawString("000000", 565, 702); //繊呪 窒径
		g.drawImage(blueFlareImage, 460, 410, null);
		g.drawImage(judgeImage, 460, 370, null);
	}
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
	}
	
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
	}
	
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
	}
	
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/notePressed.png")).getImage(); //喚携聖凶 戚耕走稽 痕井
		new Music("note.mp3", false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage(); //喚携聖凶 戚耕走稽 痕井		
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
			
			int repeat = 23500 + 4500; //亀宜戚妊
			beats = new Beat[] {
					//2原巨
					new Beat(startTime + 0, "S"),
					new Beat(startTime + 200, "F"),
					new Beat(startTime + 400, "Space"),
					
					new Beat(startTime + 1000, "L"),
					new Beat(startTime + 1200, "J"),
					new Beat(startTime + 1400, "Space"),
					
					//3原巨
					new Beat(startTime + 2000, "S"),
					new Beat(startTime + 2200, "F"),
					new Beat(startTime + 2400, "Space"),
					
					new Beat(startTime + 3000, "L"),
					new Beat(startTime + 3200, "J"),
					new Beat(startTime + 3400, "Space"),
					
					//4原巨
					new Beat(startTime + 4000, "S"),
					new Beat(startTime + 4200, "F"),
					new Beat(startTime + 4400, "Space"),
					
					new Beat(startTime + 5000, "L"),
					new Beat(startTime + 5200, "J"),
					new Beat(startTime + 5400, "Space"),
					
					//5原巨
					new Beat(startTime + 6000, "S"),
					new Beat(startTime + 6200, "F"),
					new Beat(startTime + 6400, "Space"),
					
					new Beat(startTime + 7000, "L"),
					new Beat(startTime + 7200, "J"),
					new Beat(startTime + 7400, "Space"), //start + 7400s
					//-------------------------------------------------------
					//6原巨
					new Beat(startTime + 7800, "S"), //500 娃維
					new Beat(startTime + 8300, "L"),
					new Beat(startTime + 8800, "S"),
					new Beat(startTime + 9300, "L"),
					
					//7原巨 什典 (2600)
					
					//8原巨
					new Beat(startTime + 11900, "S"), //500 娃維
					new Beat(startTime + 12400, "L"),
					new Beat(startTime + 12900, "S"),
					new Beat(startTime + 13400, "L"),
					
					//9原巨 什典 (2600)
					
					//10原巨
					new Beat(startTime + 15700, "S"), //--匙焼焼郊焼郊章 
					new Beat(startTime + 16500, "D"), //800 娃維
					new Beat(startTime + 17300, "F"),
							
					//11原巨  600 習壱
					new Beat(startTime + 17900, "L"), 
					new Beat(startTime + 18400, "K"), //500 娃維
					new Beat(startTime + 18900, "J"),
					new Beat(startTime + 19300, "Space"),
					
					//12原巨 400 習壱
					new Beat(startTime + 19800, "S"), //--匙焼焼郊焼郊章 
					new Beat(startTime + 20600, "D"), //800 娃維
					new Beat(startTime + 21400, "F"),
							
					//13原巨  600 習壱
					new Beat(startTime + 22000, "L"), 
					new Beat(startTime + 22500, "K"), //500 娃維
					new Beat(startTime + 23000, "J"),
					new Beat(startTime + 23400, "Space"),
					
					//---------亀宜戚妊-------------------------------------------------------------//
					
					//2原巨
					new Beat(startTime + repeat + 0, "S"),
					new Beat(startTime + repeat + 200, "F"),
					new Beat(startTime + repeat + 400, "Space"),
					
					new Beat(startTime + repeat + 1000, "L"),
					new Beat(startTime + repeat + 1200, "J"),
					new Beat(startTime + repeat + 1400, "Space"),
					
					//3原巨
					new Beat(startTime + repeat + 2000, "S"),
					new Beat(startTime + repeat + 2200, "F"),
					new Beat(startTime + repeat + 2400, "Space"),
					
					new Beat(startTime + repeat + 3000, "L"),
					new Beat(startTime + repeat + 3200, "J"),
					new Beat(startTime + repeat + 3400, "Space"),
					
					//4原巨
					new Beat(startTime + repeat + 4000, "S"),
					new Beat(startTime + repeat + 4200, "F"),
					new Beat(startTime + repeat + 4400, "Space"),
					
					new Beat(startTime + repeat + 5000, "L"),
					new Beat(startTime + repeat + 5200, "J"),
					new Beat(startTime + repeat + 5400, "Space"),
					
					//5原巨
					new Beat(startTime + repeat + 6000, "S"),
					new Beat(startTime + repeat + 6200, "F"),
					new Beat(startTime + repeat + 6400, "Space"),
					
					new Beat(startTime + repeat + 7000, "L"),
					new Beat(startTime + repeat + 7200, "J"),
					new Beat(startTime + repeat + 7400, "Space"), //start + 7400s
					//-------------------------------------------------------
					//6原巨
					new Beat(startTime + repeat + 7800, "S"), //500 娃維
					new Beat(startTime + repeat + 8300, "L"),
					new Beat(startTime + repeat + 8800, "S"),
					new Beat(startTime + repeat + 9300, "L"),
					
					//7原巨 什典 (2600)
					
					//8原巨
					new Beat(startTime + repeat + 11900, "S"), //500 娃維
					new Beat(startTime + repeat + 12400, "L"),
					new Beat(startTime + repeat + 12900, "S"),
					new Beat(startTime + repeat + 13400, "L"),
					
					//9原巨 什典 (2600)
					
					//10原巨
					new Beat(startTime + repeat + 15700, "S"), //--匙焼焼郊焼郊章 
					new Beat(startTime + repeat + 16500, "D"), //800 娃維
					new Beat(startTime + repeat + 17300, "F"),
							
					//11原巨  600 習壱
					new Beat(startTime + repeat + 17900, "L"), 
					new Beat(startTime + repeat + 18400, "K"), //500 娃維
					new Beat(startTime + repeat + 18900, "J"),
					new Beat(startTime + repeat + 19300, "Space"),
					
					//12原巨 400 習壱
					new Beat(startTime + repeat + 19800, "S"), //--匙焼焼郊焼郊章 
					new Beat(startTime + repeat + 20600, "D"), //800 娃維
					new Beat(startTime + repeat + 21400, "F"),
							
					//13原巨  600 習壱
					new Beat(startTime + repeat + 22000, "L"), 
					new Beat(startTime + repeat + 22500, "K"), //500 娃維
					new Beat(startTime + repeat + 23000, "J"),
					new Beat(startTime + repeat + 23400, "Space"),
					
			};
		}
		else if(titleName.equals("Route - 201") && difficulty.equals("hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 格巷 毘球祁 せせ
			};
		}
		else if(titleName.equals("Route - 209 8bit") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 格巷 毘球祁 せせ
			};
		}
		else if(titleName.equals("Route - 209 8bit") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 格巷 毘球祁 せせ
			};
		}
		else if(titleName.equals("Ending - 209 8bit") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 格巷 毘球祁 せせ
			};
		}
		else if(titleName.equals("Route - 209 8bit") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					//---------- 格巷 毘球祁 せせ
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
	
	public void judge(String input) { //葛闘 毒舛 敗呪
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
