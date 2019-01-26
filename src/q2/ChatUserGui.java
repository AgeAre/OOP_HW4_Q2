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
public class ChatUserGui extends JPanel {

    private static final long serialVersionUID = 1L;

    private  String userName = "Write your message here:";
    private  DiscussionWindow discussionWindow;

    // Allows the editing of a single line of text
    private  JTextField input;

    // Buttons
    private  JButton btnGaramond;
    private  JButton btnBold;
    private  JButton btnDefault;


    // Display chat
    private  JTextPane chat;


    /**
     * Creates a new GUI JPanel for the chat. containing the chat view and the input boxes.
     * @effects Creates a new GUI JPanel contained in frame.
     */
    public ChatUserGui(JFrame frame, String userName, DiscussionWindow myDiscussionWindow, List<Observer> chatBoxesList){
        //init the backend
        this.discussionWindow = myDiscussionWindow;

        // add all observers to one list for the observer design pattern implementation
        TextInput box = new TextInput(userName,chatBoxesList);

        btnGaramond = new JButton("Garamond");
        btnBold     = new JButton("Bold");
        btnDefault  = new JButton("Default");

        //FBL is short for FontButtonListener
        FontButtonListener FBLGaramond = new FontButtonListener("Garamond"  ,userName, btnGaramond,chatBoxesList);
        FontButtonListener FBLBold =     new FontButtonListener("Bold"      ,userName,btnBold   ,chatBoxesList);
        FontButtonListener FBLDefault =  new FontButtonListener("Default"   ,userName,btnDefault,chatBoxesList);



        // get the ref to the objects of the backend to show them
        this.chat = this.discussionWindow.getChatPane();

        this.input = box.getInputTextField();

        // create the label and scroll bar
        chat.setEditable(false);

        JScrollPane scrldiscussion = new JScrollPane(chat);
        scrldiscussion.setPreferredSize(new Dimension(400, 70));

        JLabel lbldiscussion = new JLabel("Chat window: ");
        lbldiscussion.setLabelFor(chat);


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
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(0,20,20,0);
        gridbag.setConstraints(scrldiscussion, c);
        this.add(scrldiscussion);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,0);
        gridbag.setConstraints(lbldiscussion, c);
        this.add(lbldiscussion);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(0,20,0,0);
        gridbag.setConstraints(lblinput, c);
        this.add(lblinput);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.insets = new Insets(0,20,20,0);
        gridbag.setConstraints(input, c);
        this.add(input);



        //-----------------------------
        //---------- Fonts ------------
        //-----------------------------

        // Garamond button font
        c.gridx = 5;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(20,20,20,20);
        gridbag.setConstraints(btnGaramond, c);
        this.add(btnGaramond);

        // Bold button font
        c.gridx = 2;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(20,20,20,20);
        gridbag.setConstraints(btnBold, c);
        this.add(btnBold);

        // Default button font
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(20,20,20,20);
        gridbag.setConstraints(btnDefault, c);
        this.add(btnDefault);

    }


    /**
     * @param args
     */

    public static void main(String[] args) {

        //---------------------
        //-------Users---------
        //---------------------
        String userName1 = "Student1";
        DiscussionWindow chatBox1 = new DiscussionWindow(userName1);
        JFrame frame1 = new JFrame(userName1);
        Container contentPane1 = frame1.getContentPane();

        String userName2 = "Student2";
        DiscussionWindow chatBox2 = new DiscussionWindow(userName2);
        JFrame frame2 = new JFrame(userName2);
        Container contentPane2 = frame2.getContentPane();

        String userName3 = "Student3";
        DiscussionWindow chatBox3 = new DiscussionWindow(userName3);
        JFrame frame3 = new JFrame(userName3);
        Container contentPane3 = frame3.getContentPane();


        List<Observer> chatBoxes = new ArrayList<>();
        chatBoxes.add(chatBox1);
        chatBoxes.add(chatBox2);
        chatBoxes.add(chatBox3);


        //---------------------
        //-------Chats---------
        //---------------------
        ChatUserGui chat1 = new ChatUserGui(frame1, userName1, chatBox1, chatBoxes);
        contentPane1.add(chat1);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);

        ChatUserGui chat2 = new ChatUserGui(frame2, userName2, chatBox2, chatBoxes);
        contentPane2.add(chat2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);

        ChatUserGui chat3 = new ChatUserGui(frame3, userName3, chatBox3, chatBoxes);
        contentPane3.add(chat3);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.pack();
        frame3.setVisible(true);

        //-------------------------
        //-------On screen---------
        //-------------------------
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame1.setLocation(dim.width/2-frame1.getSize().width/2, dim.height/2-frame1.getSize().height/2);
        frame2.setLocation(0,0);
        frame3.setLocation(dim.width - frame3.getSize().width,0);

    }

}