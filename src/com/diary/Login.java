package com.diary;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame loginFrame=null;
	JPanel textPanel, panelForTextFields, completionPanel=null;
	JLabel titleLabel, usernameLabel, passwordLabel, userLabel, passLabel=null;
    JTextField usernameField=null;
    JPasswordField loginField=null;
    JButton loginButton=null;
    static int loginCheck=3;
    String userPass=null;
    public Login(){
    	loginFrame=new JFrame("Diary Login");
    	loginFrame.setLayout(new BorderLayout());
    	
    	titleLabel = new JLabel("Login Screen");
    	titleLabel.setPreferredSize(new Dimension(290, 30));
        titleLabel.setHorizontalAlignment(0);
        loginFrame.add(titleLabel, BorderLayout.PAGE_START);

     // Creation of a Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(70, 80));
        loginFrame.add(textPanel, BorderLayout.LINE_START);

     // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setPreferredSize(new Dimension(70, 30));
        usernameLabel.setHorizontalAlignment(4);
        textPanel.add(usernameLabel);

        // Login Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setPreferredSize(new Dimension(70, 30));
        passwordLabel.setHorizontalAlignment(4);
        textPanel.add(passwordLabel);


     // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setPreferredSize(new Dimension(100, 70));
        loginFrame.add(panelForTextFields, BorderLayout.CENTER);

        // Username Textfield
        usernameField = new JTextField(8);
        usernameField.setPreferredSize(new Dimension(100, 30));
        panelForTextFields.add(usernameField);

        // Login Textfield
        loginField = new JPasswordField(8);
        loginField.setPreferredSize(new Dimension(100, 30));
        panelForTextFields.add(loginField);

     // Creation of a Panel to contain the completion JLabels
        completionPanel = new JPanel();
        completionPanel.setPreferredSize(new Dimension(70, 80));
        loginFrame.add(completionPanel, BorderLayout.LINE_END);

     // Username Label
        userLabel = new JLabel("Wrong");
        userLabel.setForeground(Color.red);
        userLabel.setPreferredSize(new Dimension(70, 30));
        userLabel.setVisible(false);
        completionPanel.add(userLabel);

        // Login Label
        passLabel = new JLabel("Wrong");
        passLabel.setForeground(Color.red);
        passLabel.setPreferredSize(new Dimension(70, 30));
        passLabel.setVisible(false);
        completionPanel.add(passLabel);

        // Button for Logging in
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.lightGray);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	// code for validating the user in checkUser Function...
            	checkUser();
            }
            
        });
        loginFrame.add(loginButton, BorderLayout.PAGE_END);
        
      
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);


        }
    
    boolean checkUser() {
		// TODO Auto-generated method stub
    	userPass=new String(loginField.getPassword());
    	System.out.println(usernameField.getText() +" " + userPass );
    	
    	if(usernameField.getText()== null || usernameField.getText().equals("") || 
    			userPass == null || userPass.equals(""))
    	{  
    		JOptionPane.showMessageDialog(loginFrame,"Please enter username and password", "Warning", JOptionPane.WARNING_MESSAGE);
    	}else{
    		if(usernameField.getText().equalsIgnoreCase("tanya") && userPass.equals("tsethi8")){
    			JOptionPane.showMessageDialog(loginFrame,"Login Successfull for : "+usernameField.getText(), "information", JOptionPane.INFORMATION_MESSAGE);
    			loginFrame.setVisible(false);
    			new DiaryHome();
    		}else{
    			--loginCheck;
    			JOptionPane.showMessageDialog(loginFrame,"Incorrect username/password... Attemts left: "+loginCheck, "Warning", JOptionPane.WARNING_MESSAGE);
    			
    			if(loginCheck==0){
    				JOptionPane.showMessageDialog(loginFrame,"Unauthorized Access. Access Denied. Please try after sometime.", "Warning", JOptionPane.WARNING_MESSAGE);
    				System.exit(0);
    			}
    		}
    	}
    	return false;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
