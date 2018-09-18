package cekkaewnumchai.calendar.view;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import cekkaewnumchai.calendar.controller.AddTimeslotController;
import cekkaewnumchai.calendar.controller.CloseTimeslotController;
import cekkaewnumchai.calendar.controller.DeleteTimeslotController;
import cekkaewnumchai.calendar.model.Calendar;
import cekkaewnumchai.calendar.model.CalendarManagementSystem;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CalendarPage extends JPanel {

	// be able to recreate to go back
	CalendarManagementSystem cms;
	Calendar calendar;
	
	JList<String> slotList;
	private JTextField textFieldMeetingName;
	
	public JList<String> getSlotList() {
		return slotList;
	}
	
	public JTextField getMeetingNameField() {
		return textFieldMeetingName;
	}
	
	/**
	 * Create the panel.
	 */
	public CalendarPage(CalendarManagementSystem cms, Calendar calendar) {
		this.cms = cms;
		this.calendar = calendar;
		
		setLayout(null);
		
		JLabel lblFreeSlots = new JLabel("Free Slots");
		lblFreeSlots.setBounds(10, 11, 113, 14);
		add(lblFreeSlots);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 265, 253);
		add(scrollPane);
		
		slotList = new JList<>();
		slotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> listModel = new DefaultListModel<>();
		DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		Iterator<LocalDateTime> it = calendar.getFreeSlots();
		while (it.hasNext()) {
			listModel.addElement(it.next().format(formatter));
		}
		slotList.setModel(listModel);
		scrollPane.setViewportView(slotList);
		
		JButton btnAddTimeslots = new JButton("Add Timeslots");
		btnAddTimeslots.setBounds(285, 49, 136, 23);
		add(btnAddTimeslots);
		btnAddTimeslots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddTimeslotController(calendar, CalendarPage.this).process();
			}		
		});
		
		JButton btnCloseTimeslots = new JButton("Close Timeslots");
		btnCloseTimeslots.setBounds(285, 83, 136, 23);
		add(btnCloseTimeslots);
		btnCloseTimeslots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CloseTimeslotController(calendar, CalendarPage.this).process();
			}		
		});
		
		
		textFieldMeetingName = new JTextField();
		textFieldMeetingName.setBounds(285, 131, 136, 20);
		add(textFieldMeetingName);
		textFieldMeetingName.setColumns(10);
		
		JLabel lblMeetingName = new JLabel("Meeting Name");
		lblMeetingName.setBounds(285, 117, 137, 14);
		add(lblMeetingName);
		
		JButton btnScheduleMeeting = new JButton("Schedule Meeting");
		btnScheduleMeeting.setBounds(285, 153, 136, 23);
		add(btnScheduleMeeting);
		
		JButton btnCancelMeeting = new JButton("Cancel Meeting");
		btnCancelMeeting.setBounds(285, 187, 136, 23);
		add(btnCancelMeeting);
		btnCancelMeeting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CloseTimeslotController(calendar, CalendarPage.this).process();
			}		
		});
		
		JButton btnDeleteDate = new JButton("Delete Date");
		btnDeleteDate.setBounds(285, 221, 136, 23);
		add(btnDeleteDate);
		btnDeleteDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DeleteTimeslotController(calendar, CalendarPage.this).process();
			}		
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(285, 266, 136, 23);
		add(btnBack);
	}
}
