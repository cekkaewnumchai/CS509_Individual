package cekkaewnumchai.calendar.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cekkaewnumchai.calendar.controller.ValidateCalendarController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;

public class AddCalendarPage extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldStartDate;
	private JTextField textFieldEndDate;
	private JTextField textFieldEarlyHour;
	private JTextField textFieldLatestHour;
	private JComboBox comboBoxDuration;
	private JButton btnConfirm;
	private JButton btnCancel;

	public JTextField getCalendarName() {
		return textFieldName;
	}

	public JTextField getStartDate() {
		return textFieldStartDate;
	}

	public JTextField getEndDate() {
		return textFieldEndDate;
	}

	public JTextField getEarlyHour() {
		return textFieldEarlyHour;
	}

	public JTextField getLatestHour() {
		return textFieldLatestHour;
	}

	public JComboBox getDuration() {
		return comboBoxDuration;
	}

	public JButton getConfirm() {
		return btnConfirm;
	}

	public JButton getCancel() {
		return btnCancel;
	}

	private boolean updated = false;

	public boolean wasUpdated() {
		return updated;
	}

	/**
	 * Create the frame.
	 */
	public AddCalendarPage() {
		setBounds(100, 100, 264, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 46, 14);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(118, 8, 117, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblStartDate = new JLabel("<html>Start Date (YYYY/MM/DD)</html>");
		lblStartDate.setBounds(10, 36, 98, 41);
		contentPane.add(lblStartDate);

		textFieldStartDate = new JTextField();
		textFieldStartDate.setBounds(118, 45, 117, 20);
		contentPane.add(textFieldStartDate);
		textFieldStartDate.setColumns(10);

		JLabel lblEndDate = new JLabel("<html>End Date (YYYY/MM/DD)</html>");
		lblEndDate.setBounds(10, 83, 98, 33);
		contentPane.add(lblEndDate);

		textFieldEndDate = new JTextField();
		textFieldEndDate.setColumns(10);
		textFieldEndDate.setBounds(118, 88, 117, 20);
		contentPane.add(textFieldEndDate);

		textFieldEarlyHour = new JTextField();
		textFieldEarlyHour.setColumns(10);
		textFieldEarlyHour.setBounds(118, 131, 117, 20);
		contentPane.add(textFieldEarlyHour);

		textFieldLatestHour = new JTextField();
		textFieldLatestHour.setColumns(10);
		textFieldLatestHour.setBounds(118, 174, 117, 20);
		contentPane.add(textFieldLatestHour);

		Integer[] stringDuration = { 10, 15, 20, 30, 60 };
		comboBoxDuration = new JComboBox(stringDuration);
		comboBoxDuration.setSelectedIndex(-1);
		comboBoxDuration.setBounds(118, 218, 117, 20);
		contentPane.add(comboBoxDuration);

		JLabel lblEarlyHour = new JLabel("Early Hour");
		lblEarlyHour.setBounds(10, 134, 72, 14);
		contentPane.add(lblEarlyHour);

		JLabel labelLatestHour = new JLabel("Latest Hour");
		labelLatestHour.setBounds(10, 177, 72, 14);
		contentPane.add(labelLatestHour);

		JLabel lblDurationminute = new JLabel("Duration (Minute)");
		lblDurationminute.setBounds(10, 221, 98, 14);
		contentPane.add(lblDurationminute);

		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(5, 253, 89, 23);
		contentPane.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (new ValidateCalendarController(AddCalendarPage.this)
					.validate()) {
					updated = true;
					AddCalendarPage.this.setVisible(false);
				}
			}
		});

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(146, 253, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updated = false;
				AddCalendarPage.this.setVisible(false);
			}
		});
	}
}
