import java.awt.Container;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import javax.mail.Session;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

public class EmailLayout implements ActionListener, KeyListener{
	JFrame emailFrame;
	Container cp;
	JPanel panel;
	JPanel smallPanel;
	JTextField username;
	JTextField password;
	JLabel l_user;
	JLabel l_pass;
	SpringLayout layout;
	JButton loginBut;
	JTextField to;
	JLabel l_to;
	JTextField subject;
	JLabel l_subject;
	JScrollPane scroll;
	JTextArea text;
	Session session;
	JButton sendBut;
	JMenu timeMenu;
	JPopupMenu popupMenu = new JPopupMenu("Set Time");
	JMenuBar menuBar;
	JMenuItem time;
	JLabel l_hour;
	JLabel l_minute;
	JLabel l_day;
	JLabel l_month;
	SpringLayout popupLayout;
	JRadioButton amButton;
	JRadioButton pmButton;
	JComboBox<Integer> minutes;
	JComboBox<Integer> hour;
	JButton exit;
	int monthNum;
	Date sendEmailTime; 
	boolean timeSetOpened;
	DefaultComboBoxModel<Integer> model;
	Integer[] hoursList = { 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, };
	String[] monthsList = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	Integer[] minutesList = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52,
			53, 54, 55, 56, 57, 58, 59 };
	Integer[] daysOption1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			26, 27, 28, 29, 30, 31 };
	Integer[] daysOption2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			26, 27, 28, 29, 30 };
	Integer[] daysOption3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
			26, 27, 28 };
	JComboBox<Integer> days;
	JComboBox<String> months = new JComboBox<>(monthsList);
	

	public static void main(String[] args) {
		EmailLayout el = new EmailLayout();
		el.launch();
	}

	private void launch() {
		emailFrame = new JFrame("Gmail");
		emailFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = emailFrame.getContentPane();
		emailFrame.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		loginsetup();
		emailFrame.setVisible(true);
	}

	public void loginsetup() {
		layout = new SpringLayout();
		cp.setLayout(layout);
		username = new JTextField("", 15);
		username.setPreferredSize(new Dimension(100, 50));
		username.setFont(new Font("Serif", Font.PLAIN, 35));
		l_user = new JLabel("Username: ");
		l_user.setFont(new Font("Serif", Font.PLAIN, 35));
		cp.add(l_user);
		cp.add(username);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, l_user, -200, SpringLayout.HORIZONTAL_CENTER, cp);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, l_user, 0, SpringLayout.VERTICAL_CENTER, cp);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, username, 300, SpringLayout.HORIZONTAL_CENTER, l_user);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, username, 0, SpringLayout.VERTICAL_CENTER, cp);
		l_pass = new JLabel("Password: ");
		cp.add(l_pass);
		l_pass.setFont(new Font("Serif", Font.PLAIN, 35));
		password = new JPasswordField(15);
		password.setFont(new Font("Serif", Font.PLAIN, 35));
		password.addActionListener(this);
		password.setPreferredSize(new Dimension(100, 50));
		cp.add(password);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, l_pass, -200, SpringLayout.HORIZONTAL_CENTER, cp);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, l_pass, 100, SpringLayout.VERTICAL_CENTER, cp);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, password, 300, SpringLayout.HORIZONTAL_CENTER, l_pass);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, password, 100, SpringLayout.VERTICAL_CENTER, cp);
		loginBut = new JButton("login");
		username.addKeyListener(this);
	    password.addKeyListener(this);
		loginBut.setFont(new Font("Serif", Font.PLAIN, 35));
		loginBut.setPreferredSize(new Dimension(150, 50));
		cp.add(loginBut);
		loginBut.addActionListener(this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginBut, 0, SpringLayout.HORIZONTAL_CENTER, cp);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginBut, 200, SpringLayout.VERTICAL_CENTER, cp);
	}

	String getUsername() {
		return username.getText();
	}

	String getPassword() {
		return password.getText();
	}

	public void remove() {
		cp.removeAll();
		cp.setVisible(true);
	}

	public void emailSetup() {
		remove();
		emailFrame.setVisible(true);
		l_to = new JLabel("to: ");
		menuBar = new JMenuBar();
		emailFrame.setJMenuBar(menuBar);
		timeMenu = new JMenu("Time");
		timeMenu.setFont(new Font("Serif", Font.PLAIN, 25));
		menuBar.add(timeMenu);
		menuBar.setPreferredSize(new Dimension(100, 100));
		timeMenu.setPreferredSize(new Dimension(200, 50));
		timeMenu.addActionListener(this);
		time = new JMenuItem("Set Time");
		time.setPreferredSize(new Dimension(200, 50));
		timeMenu.add(time);
		time.addActionListener(this);
		time.setFont(new Font("Serif", Font.PLAIN, 25));
		to = new JTextField("", 15);
		cp.add(l_to);
		cp.add(to);
		l_to.setFont(new Font("Serif", Font.PLAIN, 35));
		to.setFont(new Font("Serif", Font.PLAIN, 35));
		layout.putConstraint(SpringLayout.WEST, l_to, 10, SpringLayout.WEST, cp);
		layout.putConstraint(SpringLayout.NORTH, l_to, 20, SpringLayout.NORTH, cp);
		layout.putConstraint(SpringLayout.WEST, to, 75, SpringLayout.EAST, l_to);
		layout.putConstraint(SpringLayout.NORTH, to, 20, SpringLayout.NORTH, cp);
		l_subject = new JLabel("subject: ");
		subject = new JTextField("", 15);
		cp.add(l_subject);
		cp.add(subject);
		l_subject.setFont(new Font("Serif", Font.PLAIN, 35));
		subject.setFont(new Font("Serif", Font.PLAIN, 35));
		layout.putConstraint(SpringLayout.WEST, l_subject, 10, SpringLayout.WEST, cp);
		layout.putConstraint(SpringLayout.NORTH, l_subject, 90, SpringLayout.NORTH, cp);
		layout.putConstraint(SpringLayout.WEST, subject, 75, SpringLayout.EAST, l_to);
		layout.putConstraint(SpringLayout.NORTH, subject, 90, SpringLayout.NORTH, cp);
		text = new JTextArea();
		text.setLineWrap(true);
		text.setPreferredSize(new Dimension(emailFrame.getWidth(), 1000));
		scroll = new JScrollPane(text);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel = new JPanel();
		panel.add(scroll);
		JLabel l_msg = new JLabel("Message: ");
		l_msg.setFont(new Font("Serif", Font.PLAIN, 35));
		text.setFont(new Font("Serif", Font.PLAIN, 35));
		layout.putConstraint(SpringLayout.NORTH, l_msg, 250, SpringLayout.NORTH, cp);
		layout.putConstraint(SpringLayout.NORTH, panel, 50, SpringLayout.NORTH, l_msg);
		cp.add(l_msg);
		cp.add(panel);
		sendBut = new JButton("Send");
		sendBut.addActionListener(this);
		sendBut.setPreferredSize(new Dimension(200, 50));
		sendBut.setFont(new Font("Serif", Font.PLAIN, 35));
		layout.putConstraint(SpringLayout.WEST, sendBut, 800, SpringLayout.EAST, emailFrame);
		layout.putConstraint(SpringLayout.NORTH, sendBut, 100, SpringLayout.NORTH, emailFrame);
		cp.add(sendBut);
		emailFrame.setVisible(true);
	}

	public int MonthtoInt() {
		return months.getSelectedIndex();
	}

	public String getSubject() {
		return subject.getText();
	}

	public String getRecipient() {
		return to.getText();
	}

	public void createPopupMenu() {
		timeSetOpened = true;
		popupMenu.setPopupSize(new Dimension(1000, 1000));
		popupMenu.setVisible(true);
		time.setComponentPopupMenu(popupMenu);
		popupLayout = new SpringLayout();
		popupMenu.setLayout(popupLayout);
		l_hour = new JLabel("Enter Hour: ");
		l_hour.setFont(new Font("Serif", Font.PLAIN, 35));
		popupMenu.add(l_hour);
		popupLayout.putConstraint(SpringLayout.WEST, l_hour, 10, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, l_hour, 20, SpringLayout.NORTH, popupMenu);
		hour = new JComboBox<Integer>(hoursList);
		hour.setFont(new Font("Serif", Font.PLAIN, 35));
		hour.setSize(new Dimension(150, 150));
		popupMenu.add(hour);
		popupLayout.putConstraint(SpringLayout.WEST, hour, 225, SpringLayout.WEST, l_hour);
		popupLayout.putConstraint(SpringLayout.NORTH, hour, 20, SpringLayout.NORTH, popupMenu);
		amButton = new JRadioButton("AM", true);
		pmButton = new JRadioButton("PM", false);
		amButton.addActionListener(this);
		pmButton.addActionListener(this);
		popupLayout.putConstraint(SpringLayout.WEST, amButton, 400, SpringLayout.WEST, l_hour);
		popupLayout.putConstraint(SpringLayout.NORTH, amButton, 20, SpringLayout.NORTH, popupMenu);
		popupMenu.add(amButton);
		popupLayout.putConstraint(SpringLayout.WEST, pmButton, 500, SpringLayout.WEST, l_hour);
		popupLayout.putConstraint(SpringLayout.NORTH, pmButton, 20, SpringLayout.NORTH, popupMenu);
		popupMenu.add(pmButton);
		amButton.setFont(new Font("Serif", Font.PLAIN, 25));
		pmButton.setFont(new Font("Serif", Font.PLAIN, 25));
		l_minute = new JLabel("Enter Minute: ");
		l_minute.setFont(new Font("Serif", Font.PLAIN, 35));
		popupMenu.add(l_minute);
		popupLayout.putConstraint(SpringLayout.WEST, l_minute, 10, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, l_minute, 120, SpringLayout.NORTH, popupMenu);
		minutes = new JComboBox<Integer>(minutesList);
		minutes.setFont(new Font("Serif", Font.PLAIN, 35));
		minutes.setSize(new Dimension(150, 150));
		popupMenu.add(minutes);
		popupLayout.putConstraint(SpringLayout.WEST, minutes, 225, SpringLayout.WEST, l_minute);
		popupLayout.putConstraint(SpringLayout.NORTH, minutes, 120, SpringLayout.NORTH, popupMenu);
		l_month = new JLabel("Month:");
		popupLayout.putConstraint(SpringLayout.WEST, l_month, 10, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, l_month, 220, SpringLayout.NORTH, popupMenu);
		months.setSize(new Dimension(200, 50));
		l_month.setFont(new Font("Serif", Font.PLAIN, 35));
		months.setFont(new Font("Serif", Font.PLAIN, 35));
		months.addActionListener(this);
		popupMenu.add(l_month);
		popupMenu.add(months);
		popupLayout.putConstraint(SpringLayout.WEST, months, 225, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, months, 220, SpringLayout.NORTH, popupMenu);
		days = new JComboBox<>(daysOption1);
		l_day = new JLabel("Day");
		l_day.setSize(new Dimension(200, 200));
		l_day.setFont(new Font("Serif", Font.PLAIN, 35));
		days.setFont(new Font("Serif", Font.PLAIN, 35));
		days.setSize(new Dimension(150, 200));
		popupMenu.add(l_day);
		popupMenu.add(days);
		exit = new JButton("Exit");
		exit.setFont(new Font("Serif", Font.PLAIN, 35));
		exit.addActionListener(this);
		popupMenu.add(exit);
		exit.setSize(new Dimension(150, 100));
		popupLayout.putConstraint(SpringLayout.EAST, exit, 900, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, exit, 20, SpringLayout.NORTH, popupMenu);
		popupLayout.putConstraint(SpringLayout.WEST, l_day, 10, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, l_day, 320, SpringLayout.NORTH, popupMenu);
		popupLayout.putConstraint(SpringLayout.WEST, days, 225, SpringLayout.WEST, popupMenu);
		popupLayout.putConstraint(SpringLayout.NORTH, days, 320, SpringLayout.NORTH, popupMenu);
	}

	public String getDate() {
		return months.getSelectedItem().toString() + " " + days.getSelectedItem().toString() + ", " + Calendar.YEAR;
	}

	public String getTime() {
		if (amButton.isSelected()) {
			return hour.getSelectedItem() + ":" + minutes.getSelectedItem().toString() + " ";
		}
		return hour.getSelectedItem() + ":" + minutes.getSelectedItem().toString() + " ";
	}

	public String getMessage() {
		return text.getText();
	}

	public boolean isamButtonSelected() {
		if (amButton.isSelected()) {
			pmButton.setSelected(false);
			return true;
		}
		amButton.setSelected(false);
		return false;
	}

	public int convertToTwentyFourHours() {
		if (isamButtonSelected()) {
			return hour.getSelectedIndex();
		} else if (hour.getSelectedItem().toString().equals("12")) {
			return 12;
		}
		String temp = hour.getSelectedItem().toString();
		int hour = Integer.parseInt(temp);
		return hour + 12;
	}

	public int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public Calendar findTime() {
		Calendar sendEmailTime = GregorianCalendar.getInstance();
		sendEmailTime.set(getYear(), MonthtoInt(), dayAsInt(), convertToTwentyFourHours(), minutesAsInt(), 0);
		return sendEmailTime;
	}

	public int dayAsInt() {
		String temp = days.getSelectedItem().toString();
		int dayAsInt = Integer.parseInt(temp);
		return dayAsInt;
	}

	public int minutesAsInt() {
		String temp = minutes.getSelectedItem().toString();
		int minutesAsInt = Integer.parseInt(temp);
		return minutesAsInt;
	}

	public Calendar getCurrentTime() {
		Calendar currentTime = GregorianCalendar.getInstance();
		int year = GregorianCalendar.getInstance().get(Calendar.YEAR);
		int month = GregorianCalendar.getInstance().get(Calendar.MONTH);
		int dayOfMonth = GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int hour = GregorianCalendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int minute = GregorianCalendar.getInstance().get(Calendar.MINUTE);
		currentTime.set(year, month, dayOfMonth, hour, minute, 0);
		return currentTime;
	}

	public Session getSession() {
		session = Login.login(getUsername(), getPassword());
		return session;
	}

	public void runTask() {
		Timer time = new Timer();
		time.schedule(new GoogleMail(getUsername(), getPassword(), getSession(), getRecipient(), getSubject(),
				getMessage(), findTime(), getCurrentTime()), sendEmailTime);
	}

	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		if (e.getActionCommand() == loginBut.getActionCommand()) {
			emailSetup();
		}
		if (e.getActionCommand().equals("Set Time"))
		{
			if(timeSetOpened == true)
			{
				popupMenu.setVisible(true);
			}
			else
			{
			createPopupMenu();
			}
		} 
		if (e.getActionCommand().equals("AM")) {
			pmButton.setSelected(false);
		} 
		if (e.getActionCommand().equals("PM")) {
			amButton.setSelected(false);
		} 
		if (e.getActionCommand().equals("Send")) {
			getSession();
			runTask();
		}
		if (e.getActionCommand().equals("Exit")) {
			System.out.println(findTime().getTime());
			System.out.println(getCurrentTime().getTime());
			sendEmailTime = findTime().getTime();
			popupMenu.setVisible(false);
		}

		if (e.getActionCommand().equals("comboBoxChanged")) {
			if (months.getSelectedItem().equals("January") || months.getSelectedItem().equals("March")
					|| months.getSelectedItem().equals("May") || months.getSelectedItem().equals("July")
					|| months.getSelectedItem().equals("August") || months.getSelectedItem().equals("October")
					|| months.getSelectedItem().equals("December")) {
				int dayTemp=days.getSelectedIndex();
				model = new DefaultComboBoxModel<Integer>(daysOption1);
				days.setModel(model);
				days.setSelectedIndex(dayTemp);

			}
			if (months.getSelectedItem().equals("April") || months.getSelectedItem().equals("June")
					|| months.getSelectedItem().equals("September") || months.getSelectedItem().equals("November")) {
				int dayTemp=days.getSelectedIndex();
				model = new DefaultComboBoxModel<Integer>(daysOption2);
				days.setModel(model);
				days.setSelectedIndex(dayTemp);
			}
			if ((months.getSelectedItem().equals("February"))) {
				int dayTemp=days.getSelectedIndex();
				model = new DefaultComboBoxModel<Integer>(daysOption3);
				days.setModel(model);
				days.setSelectedIndex(dayTemp);
			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			emailSetup();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
