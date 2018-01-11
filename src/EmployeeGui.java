import javax.swing.*;     
import java.awt.*;
import java.awt.event.*;

public class EmployeeGui 
{
	private Employee thisEmployee;
	private String strEMPID;

	public Component createComponents() {
		final JLabel 
		labSpacer1= new JLabel(""),
		labSpacer2= new JLabel("");

		JButton 
		butLoad = new JButton("Load Employee"),
		butSave = new JButton("Save Employee");

		JLabel
		labEMPID  = new JLabel("Employee ID"),
		labLname   = new JLabel("Last Name"),  
		labFname   = new JLabel("First Name");

		final JTextField 
		txtEMPID  = new JTextField(6),
		txtLname   = new JTextField(15),
		txtFname   = new JTextField(15);    

		butLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strEMPID = txtEMPID.getText();
				thisEmployee = new Employee(strEMPID);
				txtLname.setText(thisEmployee.Lname);
				txtFname.setText(thisEmployee.Fname);
			}
		});

		butSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisEmployee.Lname = txtLname.getText();
				thisEmployee.Fname = txtFname.getText();
				thisEmployee.Save();
				txtEMPID.setText("");
				txtLname.setText("");
				txtFname.setText("");
			}
		});

		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(5,2));
		pane.add(labEMPID);
		pane.add(txtEMPID);
		pane.add(labLname);
		pane.add(txtLname);
		pane.add(labFname);
		pane.add(txtFname);
		pane.add(labSpacer1);
		pane.add(labSpacer2);
		pane.add(butLoad);
		pane.add(butSave);

		return pane;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) { }

		//Create the top-level container and add contents to it.
		JFrame frame = new JFrame("EmployeeGui");
		EmployeeGui app = new EmployeeGui();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		//Finish setting up the frame, and show it.
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
