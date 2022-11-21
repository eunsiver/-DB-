import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class User extends JFrame {

	Connection con = null;
//   String driver = "com.mysql.cj.jdbc.Driver";
//   String userId = "madang";
//   String pwd = "madang";
//   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul";
//
//   private void dbConnect() {
//      try {
//         Class.forName(driver);
//         con = DriverManager.getConnection(url, userId, pwd);
//         System.out.println("DB connected");
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }

	JTable jtable;
	static String[] JtableHeader = new String[] { "예매하기", "영화번호", "영화명", "상영시간", "상영등급", "감독명", "배우명", "장르", "영화소개",
			"개봉일" };
	static DefaultTableModel tableBookModel = new DefaultTableModel(JtableHeader, 0);
	static JTable resultBookBox = new JTable(tableBookModel);

	static String[] JtableHeader2 = new String[] { "상세조회", "영화명", "상영일", "상영관번호", "좌석번호", "판매가격", "삭제", "영화변경",
			"상영일정변경" };
	static DefaultTableModel tableSearchModel = new DefaultTableModel(JtableHeader2, 0);
	static JTable resultSearchBox = new JTable(tableSearchModel);

	static JPanel MainContentPanel = new JPanel();
	static JPanel HeadPanel = new JPanel();
	static String clickedSearchUserName;
	String[] showAllMovie = { "영화명", "감독명", "배우명", "장르" };
	String[] selectMovieOrReserved = { "모든 영화 조회", "본인의 예매 정보" };
	JComboBox tableCombo = new JComboBox(selectMovieOrReserved);
	JTextField movieName = new JTextField(20);
	JTextField movieDirector = new JTextField(10);
	JTextField movieActor = new JTextField(10);
	JTextField movieGenre = new JTextField(10);
	JTextField upperUserId = new JTextField(10);
	static JTextField underUserIdText = new JTextField(10);
	JLabel userName;
	JLabel underUserId;
	JButton movieSelectBtn = new JButton("선택");
	JButton userNameBtn = new JButton("확인");
	JRadioButton[] payment = new JRadioButton[2];
	String[] paymentMethod = { "카드", "현금" };
	JRadioButton[] paySelect = new JRadioButton[2];
	String[] paymentSelectMethod = { "지금결제", "나중결제" };

	static JScrollPane sp = new JScrollPane();
	static JTextArea ta = new JTextArea(100, 50);

	int[] movieIdIndex;
	int[] theaterIdIndex;
	int[] ticketIdIndex;
	int[] reservationIdIndex;
	int[] seatIdIndex;
	int[] scheduleIdIndex;

	// 테이블에 스크롤 생기게 하기
	String[] movietemp;

	public User() {

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
		setSize(1024, 1024);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel movieSelect = new JPanel();
		JPanel userNameSearch = new JPanel();
		JPanel userReserveName = new JPanel();
		JButton underUserIdBtn = new JButton("id 저장");

		userName = new JLabel("ID: ");
		underUserId = new JLabel("ID: ");

		userNameSearch.add(userName);
		userNameSearch.add(upperUserId);
		userNameSearch.add(userNameBtn);
		JLabel Name = new JLabel("영화명: ");

		movieSelect.add(Name);
		movieSelect.add(movieName);

		JLabel director = new JLabel("감독명: ");

		movieSelect.add(director);
		movieSelect.add(movieDirector);

		JLabel actor = new JLabel("배우명: ");

		movieSelect.add(actor);
		movieSelect.add(movieActor);

		JLabel genre = new JLabel("장르: ");

		movieSelect.add(genre);
		movieSelect.add(movieGenre);

		movieSelect.add(movieSelectBtn);

		userReserveName.add(underUserId);
		userReserveName.add(underUserIdText);
		userReserveName.add(underUserIdBtn);
		underUserIdBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("userId:" + underUserIdText.getText());
			}
		});
//      ButtonGroup groupOne = new ButtonGroup();
//      JLabel pay = new JLabel("결제방식: ");
//      for (int i = 0; i < payment.length; i++) {
//         payment[i] = new JRadioButton(paymentMethod[i]);
//         groupOne.add(payment[i]);
//         userReserveName.add(payment[i]);
//      }
//
//      ButtonGroup groupTwo = new ButtonGroup();
//      JLabel paymentSel = new JLabel("결제방법: ");
//      for (int i = 0; i < payment.length; i++) {
//         paySelect[i] = new JRadioButton(paymentSelectMethod[i]);
//         groupTwo.add(paySelect[i]);
//         userReserveName.add(paySelect[i]);
//      }

		add(userReserveName, BorderLayout.SOUTH);

		tableCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tableBookModel.setNumRows(0);
				int idx = tableCombo.getSelectedIndex();

				if (idx == 0) {
					MainContentPanel.remove(sp);
					MainContentPanel.remove(userNameSearch);

					MainContentPanel.add(movieSelect, BorderLayout.NORTH);
					resultBookBox.getColumnModel().getColumn(0).setCellRenderer(new TableCellBook());
					resultBookBox.getColumnModel().getColumn(0).setCellEditor(new TableCellBook());

					sp = new JScrollPane(resultBookBox);
					sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					MainContentPanel.add(sp);
					String sql = "select * from movie";

					movietemp = new String[100]; // 수정2
					int tempi = 0; // 수정 3
					try {
						PreparedStatement pstmt = con.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();

						while (rs.next()) {

							movietemp[tempi] = rs.getString("movieid"); // 수정4
							tableBookModel.addRow(new Object[] { null, rs.getString("movieId"),
									rs.getString("movieName"), rs.getString("runningTime"), rs.getString("rating"),
									rs.getString("director"), rs.getString("actor"), rs.getString("genre"),
									rs.getString("introduction"), rs.getString("releaseDate") });
							tempi++; // 수정 5
						}

					}

					catch (SQLException er) {
						er.printStackTrace();
					}
					revalidate();
					repaint();
					movieSelectBtn.addActionListener(new movieSelectButtonListener());

				}
				// 본인의 예매조회
				else if (idx == 1) {
					userName.setVisible(true);
					upperUserId.setVisible(true);
					userNameBtn.setVisible(true);
					MainContentPanel.remove(sp);
					MainContentPanel.remove(movieSelect);
					MainContentPanel.add(userNameSearch, BorderLayout.NORTH);

					resultSearchBox.getColumnModel().getColumn(0).setCellRenderer(new TableCellSearch());
					resultSearchBox.getColumnModel().getColumn(0).setCellEditor(new TableCellSearch());

					resultSearchBox.getColumnModel().getColumn(6).setCellRenderer(new TableCellDelete());
					resultSearchBox.getColumnModel().getColumn(6).setCellEditor(new TableCellDelete());

					resultSearchBox.getColumnModel().getColumn(7).setCellRenderer(new TableCellChangeBook());// 예매변경
					resultSearchBox.getColumnModel().getColumn(7).setCellEditor(new TableCellChangeBook());

					resultSearchBox.getColumnModel().getColumn(8).setCellRenderer(new TableCellChangeSchedule());// 상영일정
					// 변경
					resultSearchBox.getColumnModel().getColumn(8).setCellEditor(new TableCellChangeSchedule());

					sp = new JScrollPane(resultSearchBox);
					sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					MainContentPanel.add(sp);

					userNameBtn.addActionListener(new userNameselectButtonListener());
					revalidate();
					repaint();

				}
			}
		});

		MainContentPanel.setLayout(new BorderLayout());

		HeadPanel.setLayout(new BorderLayout());

		HeadPanel.add(tableCombo, BorderLayout.CENTER);

		add(MainContentPanel, BorderLayout.CENTER);
		add(HeadPanel, BorderLayout.NORTH);

	}

	class userNameselectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String i = upperUserId.getText();
			int id = Integer.parseInt(i);
			clickedSearchUserName = upperUserId.getText();

			String sql = "select m.movieName, m.movieId,m.runningTime,t.theaterId, t.seatId, t.salePrice, t.reservationId, t.ticketId,sd.date   \r\n"
					+ "from reservation b\r\n" + "left join ticket t on b.reservationId = t.reservationId\r\n"
					+ "left join schedule sd on sd.scheduleId = t.scheduleId\r\n"
					+ "left join movie m on m.movieId = sd.movieId\r\n" + "where userId = " + id + ";";

//         String sql = "select *\r\n" + "from movie,\r\n"
//               + "(select schedule.scheduleId,schedule.session,schedule.movieId,schedule.day,schedule.date,schedule.startTime,one.isTicketed,one.theaterId,one.seatId,one.salePrice, one.standartPrice,one.ticketId,one.reservationId\r\n"
//               + "from schedule,\r\n"
//               + "(select scheduleId,theaterId,seatId, salePrice, standartPrice,ticketId,ticket.reservationId,ticket.isTicketed\r\n"
//               + "from ticket,\r\n" + "(select reservationId,user.userId\r\n" + "from user,reservation \r\n"
//               + "where user.userId=reservation.userId \r\n" + "and user.userName=" + "\"" + clickedSearchUserName
//               + "\" " + ") as zero\r\n" + "where ticket.reservationId=zero.reservationId) as one \r\n"
//               + "where schedule.scheduleId=one.scheduleId) as two\r\n" + "where movie.movieId=two.movieId;";
			// String sql = "select * from user where userName=" + "\"" + a + "\"";

			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				tableSearchModel.setNumRows(0);
				while (rs.next()) {

					tableSearchModel.addRow(new Object[] { null, rs.getString("movieName"), rs.getString("date"),
							rs.getString("theaterId"), rs.getString("seatId"), rs.getString("salePrice"), null, null,
							null });
				}

				int index = 0, rowCount = tableSearchModel.getRowCount();
				movieIdIndex = new int[rowCount];
				theaterIdIndex = new int[rowCount];
				ticketIdIndex = new int[rowCount];
				reservationIdIndex = new int[rowCount];
				seatIdIndex = new int[rowCount];

				rs = pstmt.executeQuery();
				while (rs.next()) {

//               System.out.println(rowCount);
					movieIdIndex[index] = rs.getInt("movieId");
					theaterIdIndex[index] = rs.getInt("theaterId");
					ticketIdIndex[index] = rs.getInt("ticketId");
					reservationIdIndex[index] = rs.getInt("reservationId");

					seatIdIndex[index] = rs.getInt("seatId");

					// System.out.println("mId:" + movieIdIndex[index]);
//               System.out.println("th:" + theaterIdIndex[index]);
//               System.out.println("t:" + ticketIdIndex[index]);

					index++;
				}

			}

			catch (SQLException er) {
				er.printStackTrace();
			}

		}
	}

	class movieSelectButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String a = "";
			String b = "";
			String c = "";
			String d = "";

			a = movieName.getText();
			b = movieDirector.getText();
			c = movieActor.getText();
			d = movieGenre.getText();

			String movieSelectString = "";

			if (!a.isEmpty()) {
				if (!movieSelectString.isEmpty())
					movieSelectString += " and ";
				movieSelectString += "movieName=" + "\"" + a + "\"";
			}
			if (!b.isEmpty()) {
				if (!movieSelectString.isEmpty())
					movieSelectString += " and ";
				movieSelectString = movieSelectString + "director=" + "\"" + b + "\"";
			}
			if (!c.isEmpty()) {
				if (!movieSelectString.isEmpty())
					movieSelectString += " and ";
				movieSelectString = movieSelectString + "actor=" + "\"" + c + "\"";
			}
			if (!d.isEmpty()) {
				if (!movieSelectString.isEmpty())
					movieSelectString += " and ";
				movieSelectString = movieSelectString + "genre=" + "\"" + d + "\"";

			}
			String sql;
			if (a.isEmpty() && b.isEmpty() && c.isEmpty() && d.isEmpty())
				sql = "select * from movie";
			else
				sql = "select * from movie where " + movieSelectString;

			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				tableBookModel.setNumRows(0);
				while (rs.next()) {

					tableBookModel.addRow(new Object[] { rs.getString("movieId"), rs.getString("movieName"),
							rs.getString("runningTime"), rs.getString("rating"), rs.getString("director"),
							rs.getString("actor"), rs.getString("genre"), rs.getString("introduction"),
							rs.getString("releaseDate") });

				}

			}

			catch (SQLException er) {
				er.printStackTrace();
			}

		}
	}

	class TableCellSearch extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JButton jb, goBack;
		JPanel scheduleTheaterTicketAll = new JPanel();

		public TableCellSearch() {
			// TODO Auto-generated constructor stub
			jb = new JButton("선택");

			jb.addActionListener(e -> {
				int rowIndex = resultSearchBox.getSelectedRow();
//            System.out.println(resultBox2.getSelectedRow());

				ta.setText("");
				String query = "select * from schedule where scheduleId=" + movieIdIndex[rowIndex];
				String query2 = "select * from theater where theaterId=" + theaterIdIndex[rowIndex];
				String query3 = "select * from ticket where ticketId=" + ticketIdIndex[rowIndex];
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ta.append("상영일정 정보 \t상영일정번호\t 영화번호\t 상영관번호\t 상영요일\t 상영회차\t 상영시작시간\t \n");
					while (rs.next()) {
						ta.append("\t \t");
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t ");
						ta.append("" + rs.getInt(3) + "\t ");
						ta.append("" + rs.getString(4) + "\t ");
						ta.append("" + rs.getString(5) + "\t ");
						ta.append("" + rs.getString(6) + "\t \n");

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query2);

					ta.append("상영관 정보 \t\t 상영관번호\t 좌석수\t 상영관사용여부\t \n");
					while (rs.next()) {
						ta.append("\t \t");
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t ");
						ta.append("" + rs.getString(3) + "\t \n");

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query3);

					ta.append("티켓 정보 \t\t 티켓번호\t 상영일정번호\t 상영관번호\t 좌석번호\t 예매번호\t 발권여부\t 표준가격\t 판매가격\t \n");
					while (rs.next()) {
						ta.append("\t \t");
						ta.append("" + rs.getInt(1) + "\t ");
						ta.append("" + rs.getInt(2) + "\t\t ");
						ta.append("" + rs.getInt(3) + "\t ");
						ta.append("" + rs.getInt(4) + "\t ");
						ta.append("" + rs.getInt(5) + "\t ");
						ta.append("" + rs.getString(6) + "\t ");
						ta.append("" + rs.getInt(7) + "\t ");
						ta.append("" + rs.getInt(8) + "\t \n");

					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				userName.setVisible(false);
				upperUserId.setVisible(false);
				userNameBtn.setVisible(false);
				scheduleTheaterTicketAll.setPreferredSize(new Dimension(100, 50));
				scheduleTheaterTicketAll.add(ta, BorderLayout.CENTER);
				sp.setViewportView(scheduleTheaterTicketAll);

			});

		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}

	}

	String movieinfo;

	class TableCellBook extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JButton jb, goBack;
		JPanel scheduleTheaterTicketAll = new JPanel();

		public TableCellBook() {
			// TODO Auto-generated constructor stub
			jb = new JButton("예매");

			jb.addActionListener(e -> {
				int movierow = resultBookBox.getSelectedRow();
				movieinfo = movietemp[movierow];
				Helpbooking();
			});

		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}

	}

	class TableCellDelete extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JButton jb;

		public TableCellDelete() {
			// TODO Auto-generated constructor stub
			jb = new JButton("삭제");

			jb.addActionListener(e -> {

				int rowIndex = resultSearchBox.getSelectedRow();
				tableSearchModel.removeRow(rowIndex);
				int nameSelectedDeleteSeatId = seatIdIndex[rowIndex];
				int nameSelectedDeleteTheaterId = theaterIdIndex[rowIndex];
				int nameSelectedDeleteTicketId = ticketIdIndex[rowIndex];
				int nameSelectedDeleteReservationId = reservationIdIndex[rowIndex];
				int seatCapacity = 0;
//            System.out.println(nameSelectedDeleteSeatId);
//            System.out.println(nameSelectedDeleteTheaterId);
//            System.out.println(nameSelectedDeleteTicketId);

				String query = "select seatCapacity from theater where theaterId=" + nameSelectedDeleteTheaterId;
				//
				try {

					Statement stmt0 = con.createStatement();
					ResultSet rs = stmt0.executeQuery(query);
					while (rs.next()) {
						seatCapacity = rs.getInt(1) + 1;

					}
				} catch (SQLException er) {
					er.printStackTrace();
				}
				String theaterQ = "update theater set seatCapacity=" + seatCapacity + " where theaterId="
						+ nameSelectedDeleteTheaterId;
				String seatQ = "update seat set isSeated=\"X\" where seatId=" + nameSelectedDeleteSeatId;

				String ticketQ = "update ticket set isTicketed=\"X\",reservationId=NULL where ticketId="
						+ nameSelectedDeleteTicketId;
				String reservationQ = "delete from reservation where reservationId=" + nameSelectedDeleteReservationId;

//            System.out.println(theaterQ);
//            System.out.println(seatQ);
//            System.out.println(ticketQ);

				try {

					int r;

					PreparedStatement stmt = con.prepareStatement(seatQ);
					stmt.executeUpdate();
					stmt = con.prepareStatement(theaterQ);
					stmt.executeUpdate();
					stmt = con.prepareStatement(ticketQ);
					stmt.executeUpdate();
					stmt = con.prepareStatement(reservationQ);
					stmt.executeUpdate();
				}

				catch (SQLException er) {
					er.printStackTrace();
				}
			});

		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}

	}

	class TableCellChangeBook extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JButton jb;
		JPanel scheduleTheaterTicketAll = new JPanel();

		public TableCellChangeBook() {
			// TODO Auto-generated constructor stub
			jb = new JButton("예매변경");

			jb.addActionListener(e -> {
				showMovieList();
				movieChange();

			});

		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}

	}

	class TableCellChangeSchedule extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

		JButton jb;

		public TableCellChangeSchedule() {
			// TODO Auto-generated constructor stub
			jb = new JButton("상영일정변경");

			jb.addActionListener(e -> {
				// 영화id에 해당하는 모든 상영일정 조회 그러나 현재 상영일정을 제외한 다른 상영일정을 가져옴

				// movieIdIndex = new int[rowCount];
				// theaterIdIndex = new int[rowCount];
				// ticketIdIndex = new int[rowCount];
				// seatIdIndex = new int[rowCount];
				// scheduleIdIndex= new int[rowCount];
				// 이걸로 해당 row 값에 위의 값들을 다 앎.

				// 그 상영일정의 번호 입력하면
				// ticket에서 ticketId로 가져와서 reservation 변경

				// 영화id에 해당하는 모든 상영일정 조회
				// 상영 일정 id값을 넣는 것으로 변경예정
				// 한 영화는 한 상영관에만 배정,

				int rowIndex = resultSearchBox.getSelectedRow();

//            userName.setVisible(false);
//            userNameText.setVisible(false);
//            userNameBtn.setVisible(false);
//            
//            scheduleTheaterTicketAll.setPreferredSize(new Dimension(100, 50));
//            scheduleTheaterTicketAll.add(ta, BorderLayout.CENTER);
//            sp.setViewportView(scheduleTheaterTicketAll);
			});

		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return jb;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return jb;
		}

	}

	public void showMovieList() {
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		jf.setTitle("영화 모든 영화 조회");
		jf.setBounds(0, 0, 600, 600);

		jp.setLayout(null);
		jf.add(jp);

		int rowIndex = resultSearchBox.getSelectedRow();

		ta.setText("");
		String query = "select * from movie";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ta.append("영화번호 \t영화명\t 상영시간\t 상영등급\t 감독명\t 배우명\t 장르\t 개봉일\t \n");
			while (rs.next()) {
//            ta.append("\t \t");
				ta.append("" + rs.getInt(1) + "\t ");
				ta.append("" + rs.getString(2) + "\t ");
				ta.append("" + rs.getString(3) + "\t ");
				ta.append("" + rs.getFloat(4) + "\t ");
				ta.append("" + rs.getString(5) + "\t ");
				ta.append("" + rs.getString(6) + "\t ");
				ta.append("" + rs.getString(7) + "\t ");
				ta.append("" + rs.getString(8) + "\t \n");

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ta.setBounds(10, 10, 500, 500);
		jp.add(ta);
		jf.setVisible(true);

	}

	public void movieChange() {
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();

		jf.setTitle("영화 예매변경 하기");
		jf.setBounds(600, 400, 300, 300);

		jp.setLayout(null);
		jf.add(jp);

		JLabel jl = new JLabel("변경할 예매 아이디를 입력해주세요!");
		jl.setBounds(10, 60, 200, 40);

		JTextField jtf = new JTextField();
		jtf.setBounds(10, 100, 150, 40);
		System.out.println(jtf.getText());
		JButton jb = new JButton("입력");
		jb.setBounds(170, 100, 100, 40);
		jb.addActionListener(e -> {
			System.out.println(jtf.getText());
		});

		jp.add(jl);
		jp.add(jtf);
		jp.add(jb);
		jf.setVisible(true);
	}

	String userID;
	int canbook = 0;
	String ticketID;

	public void Helpbooking() {
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();

		jf.setTitle("영화 예매하기");
		jf.setBounds(0, 0, 300, 300);

		jp.setLayout(null);
		jf.add(jp);

		JLabel jl = new JLabel("회원 아이디를 입력해주세요.");
		jl.setBounds(10, 60, 200, 40);

		JTextField jtf = new JTextField();
		jtf.setBounds(10, 100, 150, 40);

		JButton jb = new JButton("입력");
		jb.setBounds(170, 100, 100, 40);

		jp.add(jl);
		jp.add(jtf);
		jp.add(jb);

		String movieschid;

		try {
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String str1 = "예매가 완료 되었습니다.";
					String str2 = "죄송하지만, 해당 영화는 예매할 수 없습니다.";
					userID = jtf.getText();
					if (canbook == 0) {
						guidebooking(str2);
					} else if (canbook == 1) {
						guidebooking(str1);
					}
				}
			});
			String qu = "select scheduleId\r\n" + "from Schedule\r\n"
					+ "where (Schedule.theaterId in (select theaterId from Theater\r\n"
					+ "where (Theater.seatCapacity > 0))) and\r\n" + "Schedule.movieID = " + movieinfo + ";";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qu);
			rs.next();
			movieschid = rs.getString(1);
			System.out.println(movieinfo);
			System.out.println(movieschid);
			if (!movieschid.equals("")) {
				canbook = 1;
				qu = "UPDATE Theater\r\n" + "SET Theater.seatCapacity = Theater.seatCapacity - 1\r\n"
						+ "where Theater.theaterId = (select Schedule.theaterId\r\n" + "from Schedule\r\n"
						+ "where Schedule.scheduleId =" + movieschid + ");";
				stmt.executeUpdate(qu);
				qu = "select ticketId\r\n" + "from Ticket\r\n" + "where (scheduleId= " + movieschid
						+ ") and (isTicketed = 'X');";
				rs = stmt.executeQuery(qu);
				rs.next();
				ticketID = rs.getString(1);
				System.out.println(ticketID);
				String i = underUserIdText.getText();
				int id = Integer.parseInt(i);
				qu = "INSERT INTO reservation VALUES (NULL, 'card', DEFAULT, 13000, " + i + " , DEFAULT);";
				stmt.executeUpdate(qu);
				qu = "UPDATE ticket\r\n" + "SET isTicketed = 'O'\r\n" + "where ticketid = " + ticketID + ";";
				stmt.executeUpdate(qu);
				qu = "UPDATE seat\r\n" + "SET isSeated = 'O'\r\n"
						+ "where seat.seatid = (select ticket.seatid from ticket\r\n" + "where ticket.ticketid = "
						+ ticketID + ");";
				stmt.executeUpdate(qu);
				qu = "UPDATE ticket\r\n" + "SET ticket.reservationId = (select max(reservationId) from reservation)\r\n"
						+ "where ticketId = " + ticketID + ";";
				stmt.executeUpdate(qu);
			} else {
				canbook = 0;
			}

		}

		catch (SQLException er) {
			er.printStackTrace();
		}

		jf.setVisible(true);
	}

	public void guidebooking(String str1) {
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();

		jf.setTitle("예매 안내 메시지");
		jf.setBounds(200, 200, 200, 200);

		jp.setLayout(null);
		jf.add(jp);

		JLabel jl = new JLabel(str1);
		jl.setBounds(20, 60, 200, 40);

		jp.add(jl);

		jf.setVisible(true);
	}
}