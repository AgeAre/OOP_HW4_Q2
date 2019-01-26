//package Q2;
//
//import javax.jws.soap.SOAPBinding;
//import javax.swing.*;
//import java.awt.*;
//import java.util.*;
//import java.util.List;
//
//public class ChatApp implements Observer {
//    private List<User> usersList;
//
//
//    /**
//     * @param usersList - the name of the user that the chat window belongs to
//     * @return a new ChatBox object representing a chat window
//     * @requires name doesn't contain ':'
//     */
//    private ChatApp(List<User> usersList) {
//        this.usersList = new ArrayList<>(usersList);
//    }
//
//    @Override
//    public void update(Observable o) {
//        checkRep();
//        // either an InputBox signaled us or a fontButton
//        // check who signaled us
//        // iterate via "New way to loop"
//        System.out.printf("check1");
//        for (User currUser : usersList) {
//            if (o.getClass().equals(InputBox.class)) {
//                // an InputBox has signaled us that there is a new message from one of the users.
//                // handle the new message
//                currUser.getChatBox().handleNewMessage((InputBox) o);
//            } else {
//                // a fontButton signaled us we should change font
//                currUser.getChatBox().changeFont((FontButtonListener) o);
//            }
//        }
//        checkRep();
//    }
//
//    private void checkRep() {
//    }
//
//
//    public static void main(String[] args) {
//        // User 1
//        String userName1 = "Student1";
//        JFrame frame1 = new JFrame(userName1);
//        Container contentPane1 = frame1.getContentPane();
//        ChatBox chatBox1 = new ChatBox(userName1);
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame1.pack();
//        frame1.setVisible(true);
//
//        // User 2
//        String userName2 = "Student2";
//        JFrame frame2 = new JFrame(userName2);
//        Container contentPane2 = frame2.getContentPane();
//        ChatBox chatBox2 = new ChatBox(userName2);
//        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame2.pack();
//        frame2.setVisible(true);
//
//        // Create list and add chat boxes
//        List<Observer> chatBoxes = new ArrayList<>();
//        chatBoxes.add(chatBox1);
//        chatBoxes.add(chatBox2);
//
//
//        // Create user 1
//        User user1 = new User(frame1, userName1, chatBox1, chatBoxes);
//        contentPane1.add(user1);
//
//        // Crate user 2
//        User user2 = new User(frame2, userName2, chatBox2, chatBoxes);
//        contentPane2.add(user2);
//
//
//
//
//
//
//
//    }
//}