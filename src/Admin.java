import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Admin extends JFrame {
	Connection con;
//	Connection con = null;
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String userId = "madang";
//	String pwd = "madang";
//	String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul";
//	
//	private void dbConnect() {
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,userId,pwd);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

	JMenuItem[] mi = new JMenuItem[8];

	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel admin0 = new JPanel(); // 영화
	JPanel admin1 = new JPanel(); // 상영일정
	JPanel admin2 = new JPanel(); // 상영관
	JPanel admin3 = new JPanel(); // 티켓
	JPanel admin4 = new JPanel(); // 좌석
	JPanel admin5 = new JPanel(); // 회원
	JPanel admin6 = new JPanel(); // 예매정보
	JTextArea ta = new JTextArea(200, 500);
	JScrollPane sp = new JScrollPane(ta);

	String[] itemTitle = { "영화 테이블 조회", "상영일정 테이블 조회", "상영관 테이블 조회", "티켓 테이블 조회", "좌석 테이블 조회", "회원 테이블 조회",
			"예매정보 테이블 조회", "데이터베이스 초기화", };

//	JRadioButton[] rb=new JRadioButton[3];
//    String[] names={"입력", "삭제", "변경"};

//    JButton confirm = new JButton("처리");

	String[] tables = { "영화", "상영일정", "상영관", "티켓", "좌석", "회원고객", "예매정보" };
	JComboBox tableCombo = new JComboBox(tables);

	public Admin() {

		String Driver = "";
		String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul";
		String userid = "madang";
		String pwd = "madang";
		// 접속변수를 초기화한다. url은 자바 드라이버 이름, 호스트명(localhost), 포트번호를 입력한다
		// userid는 관리자(madang), pwd는 사용자의 비밀번호(madang)를 입력한다.
		try { /* 드라이버를 찾는 과정 */
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Class.forName()으로 드라이버를 로딩한다. 드라이버 이름을 Class.forName에 입력한다.
		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setTitle("20011888/이은지");
		setVisible(true);
		setSize(1280, 1024);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("선택");

		for (int i = 0; i < mi.length; i++) { // 메뉴바에 액션 이벤트 리스너 달기
			mi[i] = new JMenuItem(itemTitle[i]);
			mi[i].addActionListener(new MyActionListener());
			screenMenu.add(mi[i]);
		}

		mb.add(screenMenu);
		setJMenuBar(mb);

		jp2.add(tableCombo);

//		ButtonGroup group=new ButtonGroup();
//		 for(int i=0; i<rb.length; i++){
//	            rb[i]=new JRadioButton(names[i]);
//	            group.add(rb[i]);
//	            jp2.add(rb[i]);
//	        }

		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jp1.setLayout(new BorderLayout());
		jp1.setPreferredSize(new Dimension(600, 500));
		jp1.add(sp);

		JLabel input0 = new JLabel("입력창: ");
		JTextField admin0input = new JTextField(50);
		JButton admin0btn = new JButton("확인");
		admin0.add(input0);
		admin0.add(admin0input);
		admin0.add(admin0btn);

		JLabel input1 = new JLabel("입력창: ");
		JTextField admin1input = new JTextField(50);
		JButton admin1btn = new JButton("확인");
		admin1.add(input1);
		admin1.add(admin1input);
		admin1.add(admin1btn);

		JLabel input2 = new JLabel("입력창: ");
		JTextField admin2input = new JTextField(50);
		JButton admin2btn = new JButton("확인");
		admin2.add(input2);
		admin2.add(admin2input);
		admin2.add(admin2btn);

		JLabel input3 = new JLabel("입력창: ");
		JTextField admin3input = new JTextField(50);
		JButton admin3btn = new JButton("확인");
		admin3.add(input3);
		admin3.add(admin3input);
		admin3.add(admin3btn);

		JLabel input4 = new JLabel("입력창: ");
		JTextField admin4input = new JTextField(50);
		JButton admin4btn = new JButton("확인");
		admin4.add(input4);
		admin4.add(admin4input);
		admin4.add(admin4btn);

		JLabel input5 = new JLabel("입력창: ");
		JTextField admin5input = new JTextField(50);
		JButton admin5btn = new JButton("확인");
		admin5.add(input5);
		admin5.add(admin5input);
		admin5.add(admin5btn);

		JLabel input6 = new JLabel("입력창: ");
		JTextField admin6input = new JTextField(50);
		JButton admin6btn = new JButton("확인");
		admin6.add(input6);
		admin6.add(admin6input);
		admin6.add(admin6btn);

		admin0btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin0btn) {
					String txt = admin0input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

		admin1btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin1btn) {
					String txt = admin1input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

		admin2btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin2btn) {
					String txt = admin2input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

		admin3btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin3btn) {
					String txt = admin3input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

		admin4btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin4btn) {
					String txt = admin4input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

		admin5btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin5btn) {
					String txt = admin5input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});

		admin6btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == admin6btn) {
					String txt = admin6input.getText();
					try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate(txt);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "다시 입력해주세요", "에러", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		// ----------------------------------------

		tableCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int idx = tableCombo.getSelectedIndex();
				if (idx == 0) { // 인덱스별로 패널 전환하기
					remove(admin1);
					remove(admin2);
					remove(admin3);
					remove(admin4);
					remove(admin5);
					remove(admin6);
					add(admin0, BorderLayout.SOUTH);
					revalidate();
					repaint();

				} else if (idx == 1) {
					remove(admin0);
					remove(admin2);
					remove(admin3);
					remove(admin4);
					remove(admin5);
					remove(admin6);
					add(admin1, BorderLayout.SOUTH);
					revalidate();
					repaint();
				} else if (idx == 2) {
					remove(admin0);
					remove(admin1);
					remove(admin3);
					remove(admin4);
					remove(admin5);
					remove(admin6);
					add(admin2, BorderLayout.SOUTH);
					revalidate();
					repaint();
				} else if (idx == 3) {
					remove(admin0);
					remove(admin1);
					remove(admin2);
					remove(admin4);
					remove(admin5);
					remove(admin6);
					add(admin3, BorderLayout.SOUTH);
					revalidate();
					repaint();
				} else if (idx == 4) {
					remove(admin0);
					remove(admin1);
					remove(admin3);
					remove(admin2);
					remove(admin5);
					remove(admin6);
					add(admin4, BorderLayout.SOUTH);
					revalidate();
					repaint();
				} else if (idx == 5) {
					remove(admin0);
					remove(admin1);
					remove(admin3);
					remove(admin4);
					remove(admin2);
					remove(admin6);
					add(admin5, BorderLayout.SOUTH);
					revalidate();
					repaint();
				} else if (idx == 6) {
					remove(admin0);
					remove(admin1);
					remove(admin3);
					remove(admin4);
					remove(admin5);
					remove(admin2);
					add(admin6, BorderLayout.SOUTH);
					revalidate();
					repaint();
				}
			}
		});
		add(jp1, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("영화 테이블 조회")) {
				ta.setText("");
				String query = "select * from movie";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("영화번호\t 영화명\t 상영시간\t 상영등급\t 감독명\t 배우명\t 장르\t 영화소개\t 개봉일\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getString(2) + "\t ");
						ta.append("" + rs.getString(3) + "\t ");
						ta.append("" + rs.getString(4) + "\t ");
						ta.append("" + rs.getString(5) + "\t ");
						ta.append("" + rs.getString(6) + "\t ");
						ta.append("" + rs.getString(7) + "\t ");
						ta.append("" + rs.getString(8) + "\t ");
						ta.append("" + rs.getString(9) + "\t \n");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("상영일정 테이블 조회")) {
				ta.setText("");
				String query = "select * from schedule";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("상영일정번호\t 영화번호\t 상영관번호\t 상영시작일\t 상영요일\t 상영회차\t 상영시작시간\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t ");
						ta.append("" + rs.getInt(3) + "\t ");
						ta.append("" + rs.getDate(4) + "\t ");
						ta.append("" + rs.getString(5) + "\t ");
						ta.append("" + rs.getInt(6) + "\t ");
						ta.append("" + rs.getString(7) + "\t\n ");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("상영관 테이블 조회")) {
				ta.setText("");
				String query = "select * from theater";
				try {
					Statement stmt = con.createStatement();
					System.out.println(stmt);
					ResultSet rs = stmt.executeQuery(query);
					ta.append("상영관번호\t 좌석수\t 상영관사용여부\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t ");
						ta.append("" + rs.getString(3) + "\t\n ");

					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("티켓 테이블 조회")) {
				ta.setText("");
				String query = "select * from ticket";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("티켓번호\t 상영일정번호\t 상영관번호\t 좌석번호\t 예매번호\t 발권여부\t 표준가격\t 판매가격\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t ");
						ta.append("" + rs.getInt(3) + "\t ");
						ta.append("" + rs.getInt(4) + "\t ");
						ta.append("" + rs.getInt(5) + "\t ");
						ta.append("" + rs.getString(6) + "\t ");
						ta.append("" + rs.getInt(7) + "\t ");
						ta.append("" + rs.getInt(8) + "\t\n ");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("좌석 테이블 조회")) {
				ta.setText("");
				String query = "select * from seat";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("좌석번호\t 상영관번호\t 좌석사용여부\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t ");
						ta.append("" + rs.getString(3) + "\t\n ");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("회원 테이블 조회")) {
				ta.setText("");
				String query = "select * from user";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("회원아이디\t 고객명\t 휴대폰번호\t\t 전자메일주소\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getString(2) + "\t ");
						ta.append("" + rs.getString(3) + "\t ");
						ta.append("" + rs.getString(4) + "\t\n ");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("예매정보 테이블 조회")) {
				ta.setText("");
				String query = "select * from Reservation";
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("예매번호\t 결제방법\t 결제상태\t 결제금액\t 회원아이디\t 결제일자\t\n");
					while (rs.next()) {
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getString(2) + "\t ");
						ta.append("" + rs.getString(3) + "\t ");
						ta.append("" + rs.getInt(4) + "\t ");
						ta.append("" + rs.getInt(5) + "\t ");
						ta.append("" + rs.getTimestamp(6) + "\t\n ");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "조회에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			} else if (cmd.equals("데이터베이스 초기화")) {

				ta.setText("");
				String query = "drop table if exists ticket";
				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
				}

				query = "drop table if exists seat";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "drop table if exists reservation";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "drop table if exists user";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "drop table if exists schedule";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "drop table if exists movie";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "drop table if exists theater";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "삭제에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				// 영화 테이블 생성
				query = "CREATE TABLE IF NOT EXISTS `madang`.`Movie` (";
				query += "`movieId` INT NOT NULL AUTO_INCREMENT,";
				query += "`movieName` VARCHAR(100) NULL,";
				query += "`runningTime` VARCHAR(10) NULL,";
				query += "`rating` FLOAT NULL,";
				query += "`director` VARCHAR(45) NULL,";
				query += "`actor` VARCHAR(45) NULL,";
				query += "`genre` VARCHAR(45) NULL,";
				query += "`introduction` VARCHAR(500) NULL,";
				query += "`releaseDate` DATE NULL,";
				query += "PRIMARY KEY (movieId))";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("영화 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				// 상영관 테이블
				query = "create table if not exists Theater (";
				query += "theaterId INT NOT NULL,";
				query += "seatCapacity INT NULL,";
				query += "isTheaterUsed VARCHAR(10) NULL DEFAULT 'YES',";
				query += "PRIMARY KEY (theaterId))";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("상영관 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				// 상영일정 테이블
				query = "create table if not exists Schedule (";
				query += "scheduleId INT NOT NULL AUTO_INCREMENT,";
				query += "movieId INT NOT NULL,";
				query += "theaterId INT NOT NULL,";
				query += "date DATE NULL,";
				query += "day VARCHAR(5) NULL,";
				query += "session INT NULL,";
				query += "startTime VARCHAR(45) NULL,";
				query += "PRIMARY KEY (scheduleId),";
				query += "INDEX `fk_Schedule_Movie_idx` (`movieId` ASC) VISIBLE,";
				query += "INDEX fk_Schedule_Theater1_idx (theaterId ASC) VISIBLE,";
				query += "CONSTRAINT fk_Schedule_Movie";
				query += " FOREIGN KEY (movieId)";
				query += " REFERENCES Movie (movieId)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION,";
				query += "CONSTRAINT `fk_Schedule_Theater1`";
				query += " FOREIGN KEY (`theaterId`)";
				query += " REFERENCES `Theater` (`theaterId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION)";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("상영일정 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "create table if not exists user (";
				query += "`userId` INT NOT NULL AUTO_INCREMENT,";
				query += "`userName` VARCHAR(45) NULL,";
				query += "`phoneNum` VARCHAR(45) NULL,";
				query += "`mail` VARCHAR(100) NULL,";
				query += "PRIMARY KEY (`userId`))";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("회원 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "CREATE TABLE IF NOT EXISTS `Reservation` (";
				query += "`reservationId` INT NOT NULL AUTO_INCREMENT,";
				query += "`paymentMethod` VARCHAR(45) NULL,";
				query += "`isPaid` VARCHAR(10) NULL DEFAULT 'YES',";
				query += "`price` INT NULL,";
				query += "`userId` INT NOT NULL,";
				query += "`paymentDate` TIMESTAMP NULL,";
				query += "PRIMARY KEY (`reservationId`),";
				query += "INDEX `fk_Reservation_User1_idx` (`userId` ASC) VISIBLE,";
				query += "CONSTRAINT `fk_Reservation_User1`";
				query += " FOREIGN KEY (`userId`)";
				query += " REFERENCES `madang`.`User` (`userId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION)";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("예매정보 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "CREATE TABLE IF NOT EXISTS `madang`.`Seat` (";
				query += "`seatId` INT NOT NULL,";
				query += "`theaterId` INT NOT NULL,";
				query += "`isSeated` VARCHAR(1) NULL DEFAULT 'X',";
				query += "PRIMARY KEY (`seatId`),";
				query += "INDEX `fk_Seats_Theater1_idx` (`theaterId` ASC) VISIBLE,";
				query += "CONSTRAINT `fk_Seats_Theater1`";
				query += " FOREIGN KEY (`theaterId`)";
				query += " REFERENCES `madang`.`Theater` (`theaterId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION )";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("좌석 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}

				query = "CREATE TABLE IF NOT EXISTS `madang`.`Ticket` (";
				query += "`ticketId` INT NOT NULL AUTO_INCREMENT,";
				query += "`scheduleId` INT NOT NULL,";
				query += "`theaterId` INT NOT NULL,";
				query += "`seatId` INT NOT NULL,";
				query += "`reservationId` INT NULL,";
				query += "`isTicketed` VARCHAR(1) NULL DEFAULT 'X',";
				query += "`standartPrice` INT NULL DEFAULT 13000,";
				query += "`salePrice` INT NULL,";
				query += "PRIMARY KEY (`ticketId`),";
				query += "INDEX `fk_Ticket_Schedule1_idx` (`scheduleId` ASC) VISIBLE,";
				query += "INDEX `fk_Ticket_Theater1_idx` (`theaterId` ASC) VISIBLE,";
				query += "INDEX `fk_Ticket_Seats1_idx` (`seatId` ASC) VISIBLE,";
				query += "INDEX `fk_Ticket_Reservation1_idx` (`reservationId` ASC) VISIBLE,";
				query += "CONSTRAINT `fk_Ticket_Schedule1`";
				query += " FOREIGN KEY (`scheduleId`)";
				query += " REFERENCES `madang`.`Schedule` (`scheduleId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION,";
				query += "CONSTRAINT `fk_Ticket_Theater1`";
				query += " FOREIGN KEY (`theaterId`)";
				query += " REFERENCES `madang`.`Theater` (`theaterId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION,";
				query += "CONSTRAINT `fk_Ticket_Seats1`";
				query += " FOREIGN KEY (`seatId`)";
				query += " REFERENCES `madang`.`Seat` (`seatId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION,";
				query += "CONSTRAINT `fk_Ticket_Reservation1`";
				query += " FOREIGN KEY (`reservationId`)";
				query += " REFERENCES `madang`.`Reservation` (`reservationId`)";
				query += " ON DELETE NO ACTION";
				query += " ON UPDATE NO ACTION)";

				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
					ta.append("티켓 테이블 초기화 완료\n");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "생성에러", "에러", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
}
