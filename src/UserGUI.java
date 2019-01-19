/**
 * Chat application prototype package.
 * Has up to 3 students chating when each has a chat box to view the discussion.
 * Innovative human friendly UI and using the unique idea for message sending using the Enter button of your keyboard!
 * Incredible personalization with state of the art style toggle of the text to bold,standard or other font.
 * 
 * Representation Invariant:
 *   standard swing types , see swing for more detail 
 *   
 * Abstraction function:
 *  userName1-3 - name of the persons chating
 *  input1-3 - the input panels
 *  discussion1-3 - the output chat boxes
 */
package q2;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the UI implementation for the chat prototype.
 * The visual speaks for itself. Functionality:
 * Input boxes that get input text line and send it via Enter to all of the Discussions.
 * The students own chat discussion window has his text in black and others in blue.
 * There is a method to toggle, bold, other font, or standard font. 
 */
public class UserGUI  extends JPanel {
	
  private static final long serialVersionUID = 1L;

	  
  
  private  String userName1 = "Student1";
  private  String userName2 = "Student2";
  private  String userName3 = "Student3";
  
 
  private  JTextField input1;
  private  JTextField input2;
  private  JTextField input3;
  
  
  private  JButton btnDefault1    ;
  private  JButton btnDavid1      ;
  private  JButton btnBold1       ;
                                  
  private  JButton btnDefault2    ;
  private  JButton btnDavid2      ;
  private  JButton btnBold2       ;
                                  
  private  JButton btnDefault3    ;
  private  JButton btnDavid3      ;
  private  JButton btnBold3       ;
  
  
  private  JTextPane discussion1;
  private  JTextPane discussion2;
  private  JTextPane discussion3;
  
  
  /**
	 * Creates a new GUI JPanel for the chat. containing the chat view and the input boxes.
	 * @effects Creates a new GUI JPanel contained in frame.
    */
  public UserGUI(JFrame frame){
		//init the backend
		ChatBox chat1 = new ChatBox(userName1);    
		ChatBox chat2 = new ChatBox(userName2);
		ChatBox chat3 = new ChatBox(userName3);
		
		// add all observers to one list for the obeserver design pattern implementation
		ArrayList<Observer> chatBoxes = new ArrayList<>();
		chatBoxes.add(chat1);
		chatBoxes.add(chat2);
		chatBoxes.add(chat3);
		InputBox box1 = new InputBox(userName1,chatBoxes);
		InputBox box2 = new InputBox(userName2,chatBoxes);
		InputBox box3 = new InputBox(userName3,chatBoxes);
		
		btnDefault1  = new JButton("Default");
		btnBold1     = new JButton("Bold");    
		btnDavid1    = new JButton("David");   
		
		btnDefault2  = new JButton("Default"); 
		btnBold2     = new JButton("Bold");
		btnDavid2    = new JButton("David");   
		   
		             
		btnDefault3  = new JButton("Default"); 
		btnBold3      = new JButton("Bold");    
		btnDavid3    = new JButton("David"); 
		
		//FBL is short for FontButtonListener
		FontButtonListener FBLDefault1 = new FontButtonListener("Default",userName1,btnDefault1,chatBoxes);
		FontButtonListener FBLBold1 = new FontButtonListener("Bold",userName1,btnBold1,chatBoxes);
		FontButtonListener FBLDavid1 = new FontButtonListener("David",userName1,btnDavid1,chatBoxes);
		
		//FBL is short for FontButtonListener
		FontButtonListener FBLDefault2 = new FontButtonListener("Default",userName2,btnDefault2,chatBoxes);
		FontButtonListener FBLBold2 = new FontButtonListener("Bold",userName2,btnBold2,chatBoxes);
		FontButtonListener FBLDavid2 = new FontButtonListener("David",userName2,btnDavid2,chatBoxes);
		
		//FBL is short for FontButtonListener
		FontButtonListener FBLDefault3 = new FontButtonListener("Default",userName3,btnDefault3,chatBoxes);
		FontButtonListener FBLBold3 = new FontButtonListener("Bold",userName3,btnBold3,chatBoxes);
		FontButtonListener FBLDavid3 = new FontButtonListener("David",userName3,btnDavid3,chatBoxes);
		// get the ref to the objects of the backend to show them
		this.discussion1 = chat1.getChatPane();
		this.discussion2 = chat2.getChatPane();
		this.discussion3 = chat3.getChatPane();
				    
		this.input1 = box1.getInputTextField();
		this.input2 = box2.getInputTextField();
		this.input3 = box3.getInputTextField();
		
		//view chat messages	
		   
		// create the label and scroll bar
		discussion1.setEditable(false);
		JScrollPane scrldiscussion1 = new JScrollPane(discussion1);
		scrldiscussion1.setPreferredSize(new Dimension(400, 70));
		JLabel lbldiscussion1 = new JLabel("Discussion1: ");
		lbldiscussion1.setLabelFor(discussion1);
			
		
		discussion2.setEditable(false);
		JScrollPane scrldiscussion2 = new JScrollPane(discussion2);
		scrldiscussion2.setPreferredSize(new Dimension(400, 70));
		JLabel lbldiscussion2 = new JLabel("Discussion2: ");
		lbldiscussion2.setLabelFor(discussion2);
			
		
		discussion3.setEditable(false);
		JScrollPane scrldiscussion3 = new JScrollPane(discussion3);
		scrldiscussion3.setPreferredSize(new Dimension(400, 70));
		JLabel lbldiscussion3 = new JLabel("Discussion3: ");
		lbldiscussion3.setLabelFor(discussion3);
			
			
		// The box to Input a new chat message line
		JLabel lblinput1 = new JLabel(this.userName1);
		lblinput1.setLabelFor(lblinput1);
		input1.setEditable(true);
			
		// The box to Input a new chat message line
		JLabel lblinput2 = new JLabel(this.userName2);
		lblinput2.setLabelFor(lblinput2);
		input2.setEditable(true);
		// button to send messages
			
		// The box to Input a new chat message line
		JLabel lblinput3 = new JLabel(this.userName3);
		lblinput3.setLabelFor(lblinput3);
		input3.setEditable(true);
		
			
		// arrange components on grid
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lbldiscussion1, c);
		this.add(lbldiscussion1);
			
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(scrldiscussion1, c);
		this.add(scrldiscussion1);
			
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lbldiscussion2, c);
		this.add(lbldiscussion2);
			
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(scrldiscussion2, c);
		this.add(scrldiscussion2);
			
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lbldiscussion3, c);
		this.add(lbldiscussion3);
			
		c.gridx = 5;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(scrldiscussion3, c);
		this.add(scrldiscussion3);
			
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(input1, c);
		this.add(input1);
			
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lblinput1, c);
		this.add(lblinput1);
		
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnDefault1, c);
		this.add(btnDefault1);


		c.gridx = 5;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnBold1, c);
		this.add(btnBold1);


		c.gridx = 6;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnDavid1, c);
		this.add(btnDavid1);

			
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(input2, c);
		this.add(input2);
			
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lblinput2, c);
		this.add(lblinput2);
		

		c.gridx = 4;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnDefault2, c);
		this.add(btnDefault2);


		c.gridx = 5;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnBold2, c);
		this.add(btnBold2);


		c.gridx = 6;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnDavid2, c);
		this.add(btnDavid2);


		
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(input3, c);
		this.add(input3);
			
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,0,0);
		gridbag.setConstraints(lblinput3, c);
		this.add(lblinput3);

		c.gridx = 4;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnDefault3, c);
		this.add(btnDefault3);


		c.gridx = 5;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnBold3, c);
		this.add(btnBold3);


		c.gridx = 6;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0,20,20,0);
		gridbag.setConstraints(btnDavid3, c);
		this.add(btnDavid3);


	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chat GUI");
		Container contentPane = frame.getContentPane();
		
		JPanel chat = new UserGUI(frame);
		contentPane.add(chat);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

};