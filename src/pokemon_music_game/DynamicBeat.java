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
	
	//더블 버퍼링을 위한 객체들
	private Image screenImage; 
	private Graphics screenGraphic;
	
	//종료버튼
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")); //기본 이미지
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png")); //누른상태
	private JButton exitButton = new JButton(exitButtonBasicImage);
	
	//시작버튼
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")); //기본 이미지
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")); //누른 상태	
	private JButton startButton = new JButton(startButtonBasicImage);
	
	//나가기버튼
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png")); //기본 이미지
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png")); //누른 상태	
	private JButton quitButton = new JButton(quitButtonBasicImage);
	
	//배경과 메뉴바
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(); //배경화면
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//곡 선택 이미지
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/Route201 Game Start Image.png")).getImage(); //선택 화면
	private boolean isMainScreen = false; //선택화면 환성화 변수
	
	//곡 제목 이미지
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/Route201 Title Image.png")).getImage();
	
	//곡 넘기기 버튼 (왼, 오)
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png")); //기본 이미지
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png")); //누른 상태	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png")); //기본 이미지
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png")); //누른 상태	
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	//마우스 위치
	private int mouseX, mouseY;
	
	public DynamicBeat() {
		setUndecorated(true);
		setTitle("PokemonMusic_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //창 크기조절 고정
		setLocationRelativeTo(null); //로컬 모니터의 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x시 종료
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		//------------------------종료 버튼-----------------------------------
		exitButton.setBounds(1251, 1, 28, 28); //퇴장버튼
		exitButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				try { //음악이 재생되고 종료
					Thread.sleep(1000); //바로 종료되는 것을 방지(1초 정도 후 종료)
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //프로그램 종료
			}
		});
		add(exitButton);
		
		//----------------------시작 버튼-----------------------------------
		startButton.setBounds(0, 300, 400, 100); //퇴장버튼
		startButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				//게임 시작 이벤트
				startButton.setVisible(false);
				quitButton.setVisible(false);
				Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //인트로 화면
				leftButton.setVisible(true);
				rightButton.setVisible(true);
				isMainScreen = true; //시작버튼 클릭시 변경할 선택화면 변수 활성화해줌
			}
		});
		add(startButton);
		
		//----------------------나가기 버튼-----------------------------------
		quitButton.setBounds(0, 430, 400, 100); //퇴장버튼
		quitButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				try { //음악이 재생되고 종료
					Thread.sleep(1000); //바로 종료되는 것을 방지(1초 정도 후 종료)
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //프로그램 종료
			}
		});
		add(quitButton);
		
		//-----------------------메뉴 바------------------------------------
		menuBar.setBounds(0, 0, 1280, 30); //위치와 크기
		menuBar.addMouseListener(new MouseAdapter() { //메뉴바 이벤트리스너(눌렀을때)
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		//메뉴바를 잡고 드레그할때 게임창이 움직이는 이벤트
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { 
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-mouseX, y-mouseY); 
			}
		});
		add(menuBar);
		
		//----------------------왼쪽 버튼-----------------------------------
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60); //퇴장버튼
		leftButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				// 왼쪽 버튼 이벤트
				
			}
		});
		add(leftButton);
		
		//----------------------오른쪽 버튼-----------------------------------
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); //퇴장버튼
		rightButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				// 오른쪽 버튼 이벤트
				
			}
		});
		add(rightButton);
		
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
		g.drawImage(Background, 0, 0, null);
		if(isMainScreen) 
		{
			g.drawImage(selectedImage, 340, 100, null); //선택화면
			g.drawImage(titleImage, 340, 50, null);
		}
		paintComponents(g);
		this.repaint();
	}
}
