package pokemon_music_game;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {
	
	//���� ���۸��� ���� ��ü��
	private Image screenImage; 
	private Graphics screenGraphic;
	
	//���� �޴���
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(); //��Ʈ�� ȭ��
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//�����ư
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")); //�⺻ �̹���
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png")); //��������
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	//���콺 ��ġ
	private int mouseX, mouseY;
	
	public DynamicBeat() {
		setUndecorated(true);
		setTitle("PokemonMusic_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //â ũ������ ����
		setLocationRelativeTo(null); //���� ������� ���߾ӿ� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x�� ����
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		//------------------------���� ��ư-----------------------------------
		exitButton.setBounds(1251, 1, 28, 28); //�����ư
		exitButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				try { //������ ����ǰ� ����
					Thread.sleep(2000); //�ٷ� ����Ǵ� ���� ����(1�� ���� �� ����)
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //���α׷� ����
			}
		});
		add(exitButton);
		
		//-----------------------�޴� ��------------------------------------
		menuBar.setBounds(0, 0, 1280, 30); //��ġ�� ũ��
		menuBar.addMouseListener(new MouseAdapter() { //�޴��� �̺�Ʈ������(��������)
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		//�޴��ٸ� ��� �巹���Ҷ� ����â�� �����̴� �̺�Ʈ
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { 
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY); 
			}
		});
		add(menuBar);
		
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
		paintComponents(g);
		this.repaint();
	}
}
