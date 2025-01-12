package pokemon_music_game;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	
	//���� ���۸��� ���� ��ü��
	private Image screenImage; 
	private Graphics screenGraphic;
	private Image introBackground; //��Ʈ�� ȭ��
	
	public DynamicBeat() {
		setTitle("PokemonMusic_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //â ũ������ ����
		setLocationRelativeTo(null); //���� ������� ���߾ӿ� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x�� ����
		setVisible(true);
		
		//image���Ͽ��� ��Ʈ���̹����� �ҷ���
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //screenImage��ü ����
		screenGraphic = screenImage.getGraphics(); //screenImage��ü�� �׷����� ���� ��ü����
		screenDraw(screenGraphic); //screenGraphic��ü�� �׷���
		g.drawImage(screenImage,  0, 0, null); //�׷��� g ��ü�� screenGraphic��ü �׷���
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
	}
}
