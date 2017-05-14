package com.diary;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.diary.util.DBUtil;

public class NewDiary extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JFrame frame=null;
	JPanel headPanel=null;
	JPanel footPanel=null;
	JLabel lblDate,lblSubject,lblText=null;
	JTextField txtDate,txtSub=null;
	JTextArea txtNote=null;
	JButton btnsubmit,btnReset,btnHome,btnPopup=null;
	JScrollPane scroll=null;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	Boolean isAlready=false;
	String getDate=null;
	
	public NewDiary(){
		frame=new JFrame("Write a Diary");
		
		frame.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtDate.setText("1");
				btnsubmit.setFocusable(true);
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			});
		headPanel=new JPanel();
		footPanel=new JPanel();
		lblDate=new JLabel("Date:");
		lblSubject=new JLabel("Subject:");
		lblText=new JLabel("Enter Diary:");
		
		txtDate=new JTextField(15);
		txtDate.setText("dd/mm/yyyy");
		txtDate.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtDate.setEditable(false);
		
		txtDate.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtDate.setText("dd/mm/yyyy");
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
			});
		
		btnPopup=new JButton("Popup");
		btnPopup.addActionListener(new ActionListener()
		{                    
			public void actionPerformed(ActionEvent ae) {
				if(checkAlreadyExist()){
					txtDate.setText("dd/mm/yyyy");
					JOptionPane.showMessageDialog(frame, "Diary already exist for "+getDate+"... Please choose some other day.", "information", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					txtDate.setText(getDate);  
				}
			}
		});
		
		txtSub=new JTextField(15);
		txtSub.setText("Enter Subject...");
		txtSub.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtSub.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtSub.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
			});
		
		txtNote=new JTextArea(20, 40);
		txtNote.setText("Enter for today...");
		txtNote.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtNote.setSize(500,600);    
		txtNote.setLineWrap(true);
		txtNote.setEditable(true);
		txtNote.setVisible(true);
		txtNote.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtNote.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
			}
			});
		
		scroll = new JScrollPane(txtNote);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        btnsubmit=new JButton("Submit");
		btnsubmit.addActionListener(this);
       
		btnReset=new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	txtDate.setText("dd/mm/yyyy");
            	txtSub.setText("Enter Subject...");
            	txtNote.setText("Enter for today...");
            }
        });
		
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
		frame.add(btnReset);
		frame.add(btnHome);
		
		//JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setSize(520,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

  	}
	
	  public void actionPerformed(ActionEvent event) {
		 /* Object[] options = { "OK", "CANCEL" };
		  JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning", 
		  JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		  null, options, options[0]);*/
		  System.out.println("hello");
		  if(txtDate.getText()==null || txtDate.getText().equals("") || txtDate.getText().equalsIgnoreCase("dd/mm/yyyy")
				  || txtSub.getText()==null || txtSub.getText().equals("")  || txtSub.getText().equalsIgnoreCase("Enter Subject...")
				  || txtNote.getText()==null || txtNote.getText().equals("") || txtNote.getText().equalsIgnoreCase("Enter for today...")){
			  JOptionPane.showMessageDialog(frame, "Please enter date, subject and diary", "Warning", JOptionPane.WARNING_MESSAGE);
		  }else{
			  System.out.println("Welcome...to insertion");  
			  // to insert into the database...
			  try{
				  con=DBUtil.getCon();
				  ps=con.prepareStatement(" insert into Diary " +
					" (DiaryDate, Subject, DiaryText) values(STR_TO_DATE(?, '%d-%m-%Y'), ?, ? ) ");
				  ps.setString(1,txtDate.getText());
				  ps.setString(2,txtSub.getText());
				  ps.setString(3,txtNote.getText());
				  
				  int i=ps.executeUpdate();
				  if(i!=0)
					{
					 JOptionPane.showMessageDialog(frame, "Diary Note inserted successfully...", "information", JOptionPane.INFORMATION_MESSAGE);
					 frame.setVisible(false);
					 new DiaryHome();
					}
				  else{
					 JOptionPane.showMessageDialog(frame, "There was an error inserting the record. Please contact support team",
							 "alert", JOptionPane.ERROR_MESSAGE); 
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
	  
		private boolean checkAlreadyExist() {
			// TODO Auto-generated method stub
			try{
				
				getDate=new DatePicker(frame).setPickedDate();
				con=DBUtil.getCon();
				ps=con.prepareStatement("select * from Diary where DiaryDate=?");
				ps.setString(1,getDate);
				rs=ps.executeQuery();
				if(rs.next()){
					isAlready=true;
				}
			else{
				isAlready=false;
			}
			
			}
			 catch(SQLException se){
				  se.printStackTrace();
			  }
			  finally
				{
				DBUtil.close(con);
				}
			return isAlready;
		}
	  
	public static void main(String[] args) {
		new NewDiary();
	}
}
