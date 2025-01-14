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
	
	//�����ư
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")); //�⺻ �̹���
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png")); //��������
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	//���۹�ư
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")); //�⺻ �̹���
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")); //���� ����	
	private JButton startButton = new JButton(startButtonBasicImage);
	
	//�������ư
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png")); //�⺻ �̹���
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png")); //���� ����	
	private JButton quitButton = new JButton(quitButtonBasicImage);
	
	//���� �޴���
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(); //���ȭ��
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//�� ���� �̹���
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/Route201 Game Start Image.png")).getImage(); //���� ȭ��
	private boolean isMainScreen = false; //����ȭ�� ȯ��ȭ ����
	
	//�� ���� �̹���
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/Route201 Title Image.png")).getImage();
	
	//�� �ѱ�� ��ư (��, ��)
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png")); //�⺻ �̹���
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png")); //���� ����	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png")); //�⺻ �̹���
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png")); //���� ����	
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
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
					Thread.sleep(1000); //�ٷ� ����Ǵ� ���� ����(1�� ���� �� ����)
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //���α׷� ����
			}
		});
		add(exitButton);
		
		//----------------------���� ��ư-----------------------------------
		startButton.setBounds(0, 300, 400, 100); //�����ư
		startButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				//���� ���� �̺�Ʈ
				startButton.setVisible(false);
				quitButton.setVisible(false);
				Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //��Ʈ�� ȭ��
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				isMainScreen = true; //���۹�ư Ŭ���� ������ ����ȭ�� ���� Ȱ��ȭ����
			}
		});
		add(startButton);
		
		//----------------------������ ��ư-----------------------------------
		quitButton.setBounds(0, 430, 400, 100); //�����ư
		quitButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				try { //������ ����ǰ� ����
					Thread.sleep(1000); //�ٷ� ����Ǵ� ���� ����(1�� ���� �� ����)
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //���α׷� ����
			}
		});
		add(quitButton);
		
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
		
		//----------------------���� ��ư-----------------------------------
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60); //�����ư
		leftButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				// ���� ��ư �̺�Ʈ
				
			}
		});
		add(leftButton);
		
		//----------------------������ ��ư-----------------------------------
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); //�����ư
		rightButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				// ������ ��ư �̺�Ʈ
				
			}
		});
		add(rightButton);
		
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
		g.drawImage(Background, 0, 0, null);
		if(isMainScreen) 
		{
			g.drawImage(selectedImage, 340, 100, null); //����ȭ��
			g.drawImage(titleImage, 340, 50, null);
		}
		paintComponents(g);
		this.repaint();
	}
}
