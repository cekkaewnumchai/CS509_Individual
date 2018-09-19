package cekkaewnumchai.calendar.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cekkaewnumchai.calendar.model.Calendar;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class CancelTimeslotPage extends JDialog {

	private JList<String> meetingList;
	private boolean updated = false;
	
	public JList<String> getMeetingList() {
		return meetingList;
	}
	
	public boolean wasUpdated() {
		return updated;
	}

	/**
	 * Create the dialog.
	 */
	public CancelTimeslotPage(Calendar calendar) {
		setBounds(100, 100, 240, 310);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 204, 210);
		getContentPane().add(scrollPane);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 232, 89, 23);
		getContentPane().add(btnCancel);
		btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updated = true;
				CancelTimeslotPage.this.setVisible(false);
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(125, 232, 89, 23);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updated = false;
				CancelTimeslotPage.this.setVisible(false);
			}
		});

		meetingList = new JList<>();
		DefaultListModel<String> listModel = new DefaultListModel<>();
		DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		Iterator<Map.Entry<LocalDateTime, String>> it =
			calendar.getReservedSlots();
		while (it.hasNext()) {
			Map.Entry<LocalDateTime, String> entry = it.next();
			listModel.addElement(entry.getKey().format(formatter) + " -- " +
										entry.getValue());
		}
		meetingList.setModel(listModel);
		scrollPane.setViewportView(meetingList);
		meetingList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg) {
				btnCancel.setEnabled(!meetingList.isSelectionEmpty());
			}
		});
	}
}
