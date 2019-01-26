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
package Q2;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the UI implementation for the chat prototype.
 * The visual speaks for itself. Functionality:
 * Input boxes that get input text line and send it via Enter to all of the Discussions.
 * The students own chat discussion window has his text in black and others in green.
 * There is a method to toggle, bold, other font, or standard font. 
 */
public class User extends JPanel {

    private static final long serialVersionUID = 1L;

    private  String userName = "Student";

    // Allows the editing of a single line of text
    private  JTextField input;

    // Buttons
    private  JButton btnDefault;
    private  JButton btnDavid;
    private  JButton btnBold;

    // Display chat
    private  JTextPane discussion;


    /**
     * Creates a new GUI JPanel for the chat. containing the chat view and the input boxes.
     * @effects Creates a new GUI JPanel contained in frame.
     */
    public User(JFrame frame){
        //init the backend
        ChatBox chat = new ChatBox(userName);

        // add all observers to one list for the observer design pattern implementation
        ArrayList<Observer> chatBoxes = new ArrayList<>();
        chatBoxes.add(chat);
        InputBox box = new InputBox(userName,chatBoxes);

        btnDefault  = new JButton("Default");
        btnBold     = new JButton("Bold");
        btnDavid    = new JButton("David");

        //FBL is short for FontButtonListener
        FontButtonListener FBLDefault = new FontButtonListener("Default",userName,btnDefault,chatBoxes);
        FontButtonListener FBLBold =    new FontButtonListener("Bold"   ,userName,btnBold   ,chatBoxes);
        FontButtonListener FBLDavid =   new FontButtonListener("David"  ,userName,btnDavid  ,chatBoxes);

        // get the ref to the objects of the backend to show them
        this.discussion = chat.getChatPane();

        this.input = box.getInputTextField();

        //view chat messages

        // create the label and scroll bar
        discussion.setEditable(false);

        JScrollPane scrldiscussion = new JScrollPane(discussion);
        scrldiscussion.setPreferredSize(new Dimension(400, 70));

        JLabel lbldiscussion = new JLabel("Discussion: ");
        lbldiscussion.setLabelFor(discussion);


        // The box to Input a new chat message line
        JLabel lblinput = new JLabel(this.userName);
        lblinput.setLabelFor(lblinput);
        input.setEditable(true);

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
        gridbag.setConstraints(lbldiscussion, c);
        this.add(lbldiscussion);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(0,20,20,0);
        gridbag.setConstraints(scrldiscussion, c);
        this.add(scrldiscussion);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(0,20,20,0);
        gridbag.setConstraints(input, c);
        this.add(input);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,0);
        gridbag.setConstraints(lblinput, c);
        this.add(lblinput);


        //-----------------------------
        //---------- Fonts ------------
        //-----------------------------

        // Font default button
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(20,20,20,20);
        gridbag.setConstraints(btnDefault, c);
        this.add(btnDefault);

        // Font bold button
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(20,20,20,20);
        gridbag.setConstraints(btnBold, c);
        this.add(btnBold);

        // Font David button
        c.gridx = 5;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(20,20,20,20);
        gridbag.setConstraints(btnDavid, c);
        this.add(btnDavid);

    }



    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Student1");
        Container contentPane1 = frame1.getContentPane();
        JPanel chat1 = new User(frame1);
        contentPane1.add(chat1);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);

        JFrame frame2 = new JFrame("Student2");
        Container contentPane2 = frame2.getContentPane();
        JPanel chat2 = new User(frame2);
        contentPane2.add(chat2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
    }

};