import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JC20011888M extends JFrame {

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
//	
	public JC20011888M() {
		JPanel jpanelOne = new JPanel();
		JButton adminBtn = new JButton("관리자");
		adminBtn.setPreferredSize(new Dimension(120, 200));
		adminBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Admin();
			}

		});

		JButton userBtn = new JButton("회원");
		userBtn.setPreferredSize(new Dimension(120, 200));
		userBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new User();
			}
		});

		jpanelOne.add(adminBtn);
		jpanelOne.add(userBtn);
		add(jpanelOne, BorderLayout.CENTER);

		setTitle("20011888/이은지");
		setSize(300, 250);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new JC20011888M();
	}
}
