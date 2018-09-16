package cekkaewnumchai.calendar.view;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JList;

import cekkaewnumchai.calendar.controller.AddCalendarController;
import cekkaewnumchai.calendar.controller.DeleteCalendarController;
import cekkaewnumchai.calendar.controller.LoadCalendarController;
import cekkaewnumchai.calendar.model.CalendarManagementSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class MainPage extends JPanel {

	// model
	CalendarManagementSystem model;
	JButton btnAddCalendar;
	JButton btnLoadCalendar;
	JButton btnDeleteCalendar;
	JList<String> calendarList;
	
	// getters for view components
	public JButton getAddCalendar() {
		return btnAddCalendar;
	}

	public JButton getLoadCalendar() {
		return btnLoadCalendar;
	}

	public JButton getDeleteCalendar() {
		return btnDeleteCalendar;
	}
	
	public JList<String> getCalendarNameList() {
		return calendarList;
	}
	
	/**
	 * Create the panel.
	 */
	public MainPage(CalendarManagementSystem cms) {
		model = cms;
		setLayout(null);
		
		JLabel label = new JLabel("Calendar");
		label.setBounds(10, 11, 111, 14);
		add(label);
		
		btnAddCalendar = new JButton("Add Calendar");
		btnAddCalendar.setBounds(10, 261, 125, 23);
		add(btnAddCalendar);
		btnAddCalendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddCalendarController(model, MainPage.this).process();
			}
		});
		
		btnLoadCalendar = new JButton("Load Calendar");
		btnLoadCalendar.setBounds(155, 261, 125, 23);
		btnLoadCalendar.setEnabled(false);
		add(btnLoadCalendar);
		btnLoadCalendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoadCalendarController(model, MainPage.this).process();
			}
		});
		
		btnDeleteCalendar = new JButton("Delete Calendar");
		btnDeleteCalendar.setBounds(299, 261, 125, 23);
		btnDeleteCalendar.setEnabled(false);
		add(btnDeleteCalendar);
		btnDeleteCalendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DeleteCalendarController(model, MainPage.this).process();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 415, 217);
		add(scrollPane);
		
		calendarList = new JList<String>();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		Iterator<String> it = cms.getCalendarNames();
		while (it.hasNext()) {
			listModel.addElement(it.next());
		}
		calendarList.setModel(listModel);
		calendarList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(calendarList);
		calendarList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				boolean enable = calendarList.getSelectedIndex() >= 0;
				btnDeleteCalendar.setEnabled(enable);
				btnLoadCalendar.setEnabled(enable);
			}
		});

	}
}
