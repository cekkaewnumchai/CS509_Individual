package cekkaewnumchai.calendar.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ShowTimeslotPage extends JDialog {

	JList<String> list;
	JLabel lblLabel;
	
	public JList<String> getShowList(){
		return list;
	}
	
	public JLabel getLabel() {
		return lblLabel;
	}

	/**
	 * Create the dialog.
	 */
	public ShowTimeslotPage() {
		setBounds(100, 100, 435, 350);
		getContentPane().setLayout(null);
		
		lblLabel = new JLabel("Label");
		lblLabel.setBounds(10, 11, 130, 14);
		getContentPane().add(lblLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 265, 266);
		getContentPane().add(scrollPane);
		
		list = new JList<>();
		scrollPane.setViewportView(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(285, 277, 124, 23);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowTimeslotPage.this.setVisible(false);
			}
		});
	}

}
