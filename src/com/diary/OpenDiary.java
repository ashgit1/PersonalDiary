package com.diary;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.diary.util.DBUtil;

public class OpenDiary extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	JFrame frame=null;
	JLabel lblDate,lblSubject,lblText=null;
	JTextField txtDate,txtSub=null;
	JTextArea txtNote=null;
	JButton btnsubmit,btnHome,btnPopup=null;
	JScrollPane scroll=null;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	public OpenDiary(){
		frame=new JFrame("Open a Diary");
		lblDate=new JLabel("Date:");
		lblSubject=new JLabel("Subject:");
		lblText=new JLabel("View Diary:");
		
		txtDate=new JTextField(15);
		txtDate.setText("dd/mm/yyyy");
		txtDate.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtDate.setEditable(false);
		
		btnPopup=new JButton("Popup");
		btnPopup.addActionListener(new ActionListener()
		{                    
			public void actionPerformed(ActionEvent ae) {
				txtDate.setText(new DatePicker(frame).setPickedDate());  
				txtSub.setText("");
				txtNote.setText("");
				}       
			});
		
		txtSub=new JTextField(15);
		txtSub.setText("");
		txtSub.setEnabled(false);
		txtSub.setFont(new Font("Calibri", Font.BOLD, 14));
		
		txtNote=new JTextArea(20, 40);
		txtNote.setFont(new Font("Calibri", Font.BOLD, 14));
		txtNote.setSize(500,600);    
		txtNote.setLineWrap(true);
		txtNote.setEditable(false);
		txtNote.setVisible(true);
		txtNote.setEnabled(false);
	
		
		scroll = new JScrollPane(txtNote);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        btnsubmit=new JButton("Open");
        btnsubmit.addActionListener(this);
	
        btnHome=new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
            	new DiaryHome();
            }
        });
		
        frame.setLayout(new FlowLayout());
        frame.add(lblDate);
		frame.add(txtDate);
		frame.add(btnPopup);
		frame.add(lblSubject);
		frame.add(txtSub);
        frame.add(lblText);
		frame.add(scroll);
		frame.add(btnsubmit);
		frame.add(btnHome);
		//JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setSize(520, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

  	}
	  public void actionPerformed(ActionEvent event) {
		  System.out.println("open diary");
		  if(txtDate.getText()==null || txtDate.getText().equals("") || txtDate.getText().equalsIgnoreCase("dd/mm/yyyy")){
			  JOptionPane.showMessageDialog(frame, "Please enter date to view subject and diary", "Warning", JOptionPane.WARNING_MESSAGE);
		  }else{
			  System.out.println("Welcome...to retrieving");  
			  // retrieving from database...
			  try{
				    con=DBUtil.getCon();
					ps=con.prepareStatement("select Subject, diarytext from Diary "
							+ " where diarydate = STR_TO_DATE(?, '%d-%m-%Y') ");
					ps.setString(1,txtDate.getText());
					rs=ps.executeQuery();
					if(rs.next()){
						
						txtSub.setEnabled(true);
						txtSub.setEditable(false);
						txtNote.setEnabled(true);
						txtNote.setEditable(false);
						
						txtSub.setText(rs.getString(1));
						txtNote.setText(rs.getString(2));
						
					}else{
						 JOptionPane.showMessageDialog(frame, "No Diary for " + txtDate.getText() + "...", "information", JOptionPane.INFORMATION_MESSAGE);
					}
			  
			  }
			  catch(SQLException se){
				  se.printStackTrace();
			  }
			  finally
				{
				DBUtil.close(con);
				}
		  }
	  }
	public static void main(String[] args) {
		new OpenDiary();
	}
}
