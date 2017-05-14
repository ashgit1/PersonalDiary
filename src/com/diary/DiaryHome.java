package com.diary;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class DiaryHome extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuBar menubar;
	JMenu menuFile;
	JMenu menuHelp;
	
	JMenuItem menuItemNew;
	JMenuItem menuItemOpen;
	JMenuItem menuItemClose;
	JMenuItem menuItemAbout;
	
	ImageIcon iconNew;
	ImageIcon iconOpen;
	ImageIcon iconClose;
	
	JPanel panel=null;
	JLabel label=null;
	JFrame frame=null;
	
	public DiaryHome(){
		
		frame=new JFrame("Personal Diary");
		frame.setLayout(new BorderLayout());
		
		panel = (JPanel)frame.getContentPane(); 
		
		label = new JLabel();   
		label.setIcon(new ImageIcon("F:/workspace/ashuworkspace/PersonalDiary/src/com/diary/img/diary.jpg"));// your image here  
		//label.setIcon(new ImageIcon("img/diary.jpg"));
		
		panel.setLayout(new FlowLayout());
		panel.add(label);   
		
		menubar=new JMenuBar();
		
		menuFile=new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		
		menuHelp=new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_E);
		

		menuItemNew=new JMenuItem("New");
		menuItemNew.setToolTipText("Create new Diary");
		menuItemNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
                new NewDiary();
            }
        });
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
			    ActionEvent.CTRL_MASK));
		
		menuItemOpen=new JMenuItem("Old");
		menuItemOpen.setToolTipText("Open Existing Diary");
		menuItemOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
            	new OpenDiary();
            }
        });
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
			    ActionEvent.CTRL_MASK));
		
		menuItemClose=new JMenuItem("Exit");
		menuItemClose.setToolTipText("Exit Diary");
		menuItemClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
			    ActionEvent.CTRL_MASK));

		
		menuItemAbout=new JMenuItem("About");
		menuItemAbout.setToolTipText("About Diary");
		menuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	frame.setVisible(false);
            	new About();
            }
        });
		
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemClose);
		menuHelp.add(menuItemAbout);
		menubar.add(menuFile);
		menubar.add(menuHelp);
		frame.setJMenuBar(menubar);
		
		
		frame.setTitle("Personal Diary");
		frame.setSize(520,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	
	public static void main(String[] args) {
		new DiaryHome();
	}

}
