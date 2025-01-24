package pokemon_music_game;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {
	
	//���� ���۸��� ���� ��ü��
	private Image screenImage; 
	private Graphics screenGraphic;
	
	//���콺 ��ġ
	private int mouseX, mouseY;
	
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
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(); //���ȭ��
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//�� ���� �̹���
	private Image selectedImage; //���� ȭ��
	private boolean isMainScreen = false; //����ȭ�� ȯ��ȭ ����
	
	//�� ���� �̹���
	private Image titleImage;
	
	//Ʈ�� �迭
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Music selectedMusic; //���� ���� ���� ����
	private int nowSelected = 0; //���� ���õ� ���� �ε���
	
	//�� �ѱ�� ��ư (��, ��)
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png")); //�⺻ �̹���
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png")); //���� ����	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png")); //�⺻ �̹���
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png")); //���� ����	
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	//���̵� ���� ��ư(����, �����)
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png")); //�⺻ �̹���
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png")); //���� ����	
	private JButton easyButton = new JButton(easyButtonBasicImage);
	
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png")); //�⺻ �̹���
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png")); //���� ����	
	private JButton hardButton = new JButton(hardButtonBasicImage);
	
	//�߰��� ������ ��ư
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png")); //�⺻ �̹���
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png")); //���� �̹���
	private JButton backButton = new JButton(backButtonBasicImage);
	
	//��Ʈ�� ���� ����
	private Music introMusic = new Music("introMusic.mp3", true);
	
	//����ȭ������ �Ѿ� �Դ���?�� ���� ����
	private boolean isGameScreen = false; 
	
	//���� Ŭ���� ��������
	public static Game game;
	
	public DynamicBeat() {
		
		//Ʈ���� �뷡 ���� ���
		trackList.add(new Track("Route201 Title Image.png", "Route201 Game Start Image.png",
				"Route201 Game Image.png", "Route - 201.mp3", "Route - 201.mp3", "Route - 201")); //Ʈ�� 1
		trackList.add(new Track("Route209 Title Image.png", "Route209 Game Start Image.png",
				"Route209 Game Image.png", "Route - 209 8bit.mp3", "Route - 209 8bit.mp3", "Route - 209 8bit")); //Ʈ�� 2
		trackList.add(new Track("Ending209 Title Image.png", "Route209 EndingVer Game Start Image.png",
				"Route209 EndingVer Game Image.png", "Ending - 209.mp3", "Ending - 209.mp3", "Ending - 209")); //Ʈ�� 3

		
		setUndecorated(true);
		setTitle("PokemonMusic_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //â ũ������ ����
		setLocationRelativeTo(null); //���� ������� ���߾ӿ� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x�� ����
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener()); //Ű���� ������
		
		//--------------------------����ȭ�� �뷡------------------------------
		introMusic.start();
				
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
				introMusic.close(); //��Ʈ�� ���� ��
				enterMain(); //���� ���� �̺�Ʈ
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
				selectedLeft(); // ���� ��ư �̺�Ʈ
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
				selectedRight(); // ������ ��ư �̺�Ʈ
			}
		});
		add(rightButton);
		
		//----------------------���� ��ư-----------------------------------
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67); //�����ư
		easyButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy"); // ���̵� ���� �̺�Ʈ	
			}
		});
		add(easyButton);
		
		//----------------------����� ��ư-----------------------------------
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67); //�����ư
		hardButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
			@Override
			public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� ������ ��
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard"); // ���̵� ����� �̺�Ʈ	
			}
		});
		add(hardButton);
		
		//----------------------������ ��ư-----------------------------------
				backButton.setVisible(false);
				backButton.setBounds(20, 50, 60, 60); //�����ư
				backButton.setBorderPainted(false); //������ �簢���� �ƴ� �̹����� ���� ����
				backButton.setContentAreaFilled(false);
				backButton.setFocusPainted(false);
				backButton.addMouseListener(new MouseAdapter() { //���� �̺�Ʈ ������
					@Override
					public void mouseEntered(MouseEvent e) { //���콺�� �ö��� ��
						backButton.setIcon(backButtonEnteredImage);
						backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�հ������
						Music buttonEnteredMusic = new Music("PianoE.mp3", false); //�ѹ��� ȿ���� ���
						buttonEnteredMusic.start();
					}
					
					@Override
					public void mouseExited(MouseEvent e) { //���콺�� �������� �� 
						backButton.setIcon(backButtonBasicImage);
						backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻���콺
					}
					
					@Override
					public void mousePressed(MouseEvent e) { //���콺�� ������ ��
						Music buttonEnteredMusic = new Music("PianoC.mp3", false); //�ѹ��� ȿ���� ���
						buttonEnteredMusic.start();
						backMain(); //�������� ���ư��� �̺�Ʈ
					}
				});
				add(backButton);
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //screenImage��ü ����
		screenGraphic = screenImage.getGraphics(); //screenImage��ü�� �׷����� ���� ��ü����
		screenDraw((Graphics2D)screenGraphic); //screenGraphic��ü�� �׷���
		g.drawImage(screenImage,  0, 0, null); //�׷��� g ��ü�� screenGraphic��ü �׷���
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) 
		{
			g.drawImage(selectedImage, 340, 100, null); //����ȭ��
			g.drawImage(titleImage, 340, 50, null);
		}
		if(isGameScreen) 
		{
			game.screenDraw(g);
		}
		paintComponents(g);
		
		try {
			Thread.sleep(5);
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) { //Ʈ�� ���� �Լ�
		if(selectedMusic != null) 
			selectedMusic.close(); //���õ� �뷡�� ������� ����
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); //���� �� �޾ƿ�
		selectedMusic.start(); //���õ� �� ����
	}
	
	public void selectedLeft() { //���� ��ư ������ ���� �Լ�
		if(nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectedRight() { //������ ��ư ������ ���� �Լ�
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false; //������ ����ȭ���� �ƴ�
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);	
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getStartMusic());
		game.start();
		setFocusable(true); //��Ŀ�� �ֱ�
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close(); //���� ����, ���� ����
		game = null;
	}
	
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //��Ʈ�� ȭ��
		isMainScreen = true; //���۹�ư Ŭ���� ������ ����ȭ�� ���� Ȱ��ȭ����
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0); //�� ó�� Ʈ��
	}
}
