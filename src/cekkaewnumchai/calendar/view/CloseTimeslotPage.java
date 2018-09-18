package cekkaewnumchai.calendar.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cekkaewnumchai.calendar.controller.ValidateTimeslotController;

import javax.swing.JComboBox;

public class CloseTimeslotPage extends JDialog {

	private JTextField textFieldDate;
	private JTextField textFieldTime;
	private JComboBox comboBoxWeekday;

	public JTextField getTextFieldDate() {
		return textFieldDate;
	}

	public JTextField getTextFieldTime() {
		return textFieldTime;
	}

	public JComboBox getWeekday() {
		return comboBoxWeekday;
	}

	private boolean updated = false;

	public boolean wasUpdated() {
		return updated;
	}

	/**
	 * Create the dialog.
	 */
	public CloseTimeslotPage() {
		setBounds(100, 100, 240, 190);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("<html>Date (YYYY/MM/DD)</html>");
		label.setBounds(10, 11, 89, 28);
		getContentPane().add(label);

		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(109, 15, 105, 20);
		getContentPane().add(textFieldDate);

		JLabel lbltimehhmm = new JLabel("<html>Time (HH:mm)</html>");
		lbltimehhmm.setBounds(10, 50, 89, 28);
		getContentPane().add(lbltimehhmm);

		textFieldTime = new JTextField();
		textFieldTime.setColumns(10);
		textFieldTime.setBounds(109, 54, 105, 20);
		getContentPane().add(textFieldTime);

		JLabel lblWeekday = new JLabel("Weekday");
		lblWeekday.setBounds(10, 89, 89, 14);
		getContentPane().add(lblWeekday);

		DayOfWeek[] stringWeekday =
			{	null, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY, DayOfWeek.FRIDAY };
		comboBoxWeekday = new JComboBox(stringWeekday);
		comboBoxWeekday.setBounds(109, 86, 105, 20);
		getContentPane().add(comboBoxWeekday);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(10, 117, 89, 23);
		getContentPane().add(btnRemove);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (new ValidateTimeslotController(CloseTimeslotPage.this)
					.validate()) {
					updated = true;
					CloseTimeslotPage.this.setVisible(false);
				}
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(125, 117, 89, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updated = false;
				CloseTimeslotPage.this.setVisible(false);
			}
		});
	}
}
