package pokemon_music_game;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	
	//더블 버퍼링을 위한 객체들
	private Image screenImage; 
	private Graphics screenGraphic;
	private Image introBackground; //인트로 화면
	
	public DynamicBeat() {
		setTitle("PokemonMusic_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //창 크기조절 고정
		setLocationRelativeTo(null); //로컬 모니터의 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x시 종료
		setVisible(true);
		
		//image파일에서 인트로이미지를 불러옴
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //screenImage객체 생성
		screenGraphic = screenImage.getGraphics(); //screenImage객체의 그래픽을 얻어와 객체생성
		screenDraw(screenGraphic); //screenGraphic객체에 그려줌
		g.drawImage(screenImage,  0, 0, null); //그래픽 g 객체에 screenGraphic객체 그려줌
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
	}
}
