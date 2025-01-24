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
	
	//더블 버퍼링을 위한 객체들
	private Image screenImage; 
	private Graphics screenGraphic;
	
	//마우스 위치
	private int mouseX, mouseY;
	
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
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(); //배경화면
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//곡 선택 이미지
	private Image selectedImage; //선택 화면
	private boolean isMainScreen = false; //선택화면 환성화 변수
	
	//곡 제목 이미지
	private Image titleImage;
	
	//트랙 배열
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Music selectedMusic; //선택 음악 담을 변수
	private int nowSelected = 0; //현재 선택된 곡의 인데스
	
	//곡 넘기기 버튼 (왼, 오)
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png")); //기본 이미지
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png")); //누른 상태	
	private JButton leftButton = new JButton(leftButtonBasicImage);
	
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png")); //기본 이미지
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png")); //누른 상태	
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	//난이도 선택 버튼(쉬움, 어려움)
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png")); //기본 이미지
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png")); //누른 상태	
	private JButton easyButton = new JButton(easyButtonBasicImage);
	
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png")); //기본 이미지
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png")); //누른 상태	
	private JButton hardButton = new JButton(hardButtonBasicImage);
	
	//중간에 나가기 버튼
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png")); //기본 이미지
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png")); //누른 이미지
	private JButton backButton = new JButton(backButtonBasicImage);
	
	//인트로 음악 변수
	private Music introMusic = new Music("introMusic.mp3", true);
	
	//게임화면으로 넘어 왔는지?에 대한 변수
	private boolean isGameScreen = false; 
	
	//게임 클래스 변수선언
	public static Game game;
	
	public DynamicBeat() {
		
		//트랙에 노래 정보 담기
		trackList.add(new Track("Route201 Title Image.png", "Route201 Game Start Image.png",
				"Route201 Game Image.png", "Route - 201.mp3", "Route - 201.mp3", "Route - 201")); //트랙 1
		trackList.add(new Track("Route209 Title Image.png", "Route209 Game Start Image.png",
				"Route209 Game Image.png", "Route - 209 8bit.mp3", "Route - 209 8bit.mp3", "Route - 209 8bit")); //트랙 2
		trackList.add(new Track("Ending209 Title Image.png", "Route209 EndingVer Game Start Image.png",
				"Route209 EndingVer Game Image.png", "Ending - 209.mp3", "Ending - 209.mp3", "Ending - 209")); //트랙 3

		
		setUndecorated(true);
		setTitle("PokemonMusic_Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //창 크기조절 고정
		setLocationRelativeTo(null); //로컬 모니터의 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x시 종료
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener()); //키보드 리스너
		
		//--------------------------시작화면 노래------------------------------
		introMusic.start();
				
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
				introMusic.close(); //인트로 음악 끔
				enterMain(); //게임 시작 이벤트
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
				selectedLeft(); // 왼쪽 버튼 이벤트
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
				selectedRight(); // 오른쪽 버튼 이벤트
			}
		});
		add(rightButton);
		
		//----------------------쉬움 버튼-----------------------------------
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67); //퇴장버튼
		easyButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy"); // 난이도 쉬움 이벤트	
			}
		});
		add(easyButton);
		
		//----------------------어려움 버튼-----------------------------------
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67); //퇴장버튼
		hardButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
			@Override
			public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
				Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
			}
			
			@Override
			public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
			}
			
			@Override
			public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
				Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard"); // 난이도 어려움 이벤트	
			}
		});
		add(hardButton);
		
		//----------------------나가기 버튼-----------------------------------
				backButton.setVisible(false);
				backButton.setBounds(20, 50, 60, 60); //퇴장버튼
				backButton.setBorderPainted(false); //기존의 사각형이 아닌 이미지의 형태 따옴
				backButton.setContentAreaFilled(false);
				backButton.setFocusPainted(false);
				backButton.addMouseListener(new MouseAdapter() { //퇴장 이벤트 리스너
					@Override
					public void mouseEntered(MouseEvent e) { //마우스가 올라갔을 때
						backButton.setIcon(backButtonEnteredImage);
						backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //손가락모양
						Music buttonEnteredMusic = new Music("PianoE.mp3", false); //한번만 효과음 재생
						buttonEnteredMusic.start();
					}
					
					@Override
					public void mouseExited(MouseEvent e) { //마우스가 내려갔을 때 
						backButton.setIcon(backButtonBasicImage);
						backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본마우스
					}
					
					@Override
					public void mousePressed(MouseEvent e) { //마우스를 눌렀을 때
						Music buttonEnteredMusic = new Music("PianoC.mp3", false); //한번만 효과음 재생
						buttonEnteredMusic.start();
						backMain(); //메인으로 돌아가는 이벤트
					}
				});
				add(backButton);
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //screenImage객체 생성
		screenGraphic = screenImage.getGraphics(); //screenImage객체의 그래픽을 얻어와 객체생성
		screenDraw((Graphics2D)screenGraphic); //screenGraphic객체에 그려줌
		g.drawImage(screenImage,  0, 0, null); //그래픽 g 객체에 screenGraphic객체 그려줌
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) 
		{
			g.drawImage(selectedImage, 340, 100, null); //선택화면
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
	
	public void selectTrack(int nowSelected) { //트랙 선택 함수
		if(selectedMusic != null) 
			selectedMusic.close(); //선택된 노래가 있을경우 종료
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); //현재 곡 받아옴
		selectedMusic.start(); //선택된 곡 실행
	}
	
	public void selectedLeft() { //왼쪽 버튼 눌렀을 때의 함수
		if(nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectedRight() { //오른쪽 버튼 눌렀을 때의 함수
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false; //이제는 메인화면이 아님
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);	
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getStartMusic());
		game.start();
		setFocusable(true); //포커스 주기
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
		game.close(); //게임 종료, 음악 종료
		game = null;
	}
	
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //인트로 화면
		isMainScreen = true; //시작버튼 클릭시 변경할 선택화면 변수 활성화해줌
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0); //젤 처음 트랙
	}
}
