package cekkaewnumchai.calendar.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddTimeslotPage extends JDialog {
	private JTextField textField;
	boolean updated = false;

	public JTextField getTextField() {
		return textField;
	}
	
	public boolean wasUpdated() {
		return updated;
	}

	/**
	 * Create the dialog.
	 */
	public AddTimeslotPage() {
		getContentPane().setLayout(null);

		setBounds(100, 100, 240, 120);

		JLabel lbldateyyyymmdd = new JLabel("<html>Date (YYYY/MM/DD)</html>");
		lbldateyyyymmdd.setBounds(10, 11, 89, 28);
		getContentPane().add(lbldateyyyymmdd);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 50, 89, 23);
		getContentPane().add(btnAdd);
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updated = true;
				AddTimeslotPage.this.setVisible(false);
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(125, 50, 89, 23);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updated = false;
				AddTimeslotPage.this.setVisible(false);
			}
		});

		textField = new JTextField();
		textField.setBounds(109, 15, 105, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg) {
				boolean enabled = false;
				try {
					Document doc = arg.getDocument();
					enabled = validateDate(doc.getText(0, doc.getLength()));
				} catch (Exception e) {
					enabled = false;
				}
				btnAdd.setEnabled(enabled);
			}

			@Override
			public void insertUpdate(DocumentEvent arg) {
				boolean enabled = false;
				try {
					Document doc = arg.getDocument();
					enabled = validateDate(doc.getText(0, doc.getLength()));
				} catch (Exception e) {
					enabled = false;
				}
				btnAdd.setEnabled(enabled);
			}

			@Override
			public void removeUpdate(DocumentEvent arg) {
				boolean enabled = false;
				try {
					Document doc = arg.getDocument();
					enabled = validateDate(doc.getText(0, doc.getLength()));
				} catch (Exception e) {
					enabled = false;
				}
				btnAdd.setEnabled(enabled);
			}
		});
	}

	private boolean validateDate(String text) {
		try {
			DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate.parse(text, formatter);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
