package cekkaewnumchai.calendar.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cekkaewnumchai.calendar.model.CalendarManagementSystem;

public class Application extends JFrame {

	private JPanel contentPane;
	
	CalendarManagementSystem model;

	/**
	 * Create the frame.
	 */
	public Application(CalendarManagementSystem cms) {
		model = cms;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 395);
		contentPane = new MainPage(cms);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
