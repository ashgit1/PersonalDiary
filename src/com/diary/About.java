package com.diary;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class About {

	JPanel headPanel=null;
	JPanel footPanel=null;
	JFrame frame=null;
	JLabel label,lbltxt=null;
	JButton btnHome=null;
	
	public About(){
		frame=new JFrame("About");
		frame.setLayout(new FlowLayout());
		
		headPanel=new JPanel();
		footPanel=new JPanel();
		
		label = new JLabel();   
		label.setIcon(new ImageIcon("F:/workspace/ashuworkspace/PersonalDiary/src/com/diary/img/hum.jpg"));
		headPanel.setLayout(new FlowLayout());
		headPanel.add(label);  
		
		lbltxt=new JLabel("Absence is to love what wind is to fire; it extinguishes the small, it inflames the great.");
		lbltxt.setFont(new Font("Calibri", Font.BOLD, 14));
		footPanel.setLayout(new FlowLayout());
		footPanel.add(lbltxt);
		
		btnHome=new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
            	new DiaryHome();
            }
        });
		
		frame.add(headPanel);
		frame.add(footPanel);
		frame.add(btnHome);
		
		frame.setSize(520,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new About();

	}

}
