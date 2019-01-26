package Q2;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * ChatBox represents a chat window in which the users chat
 * the chatBox will observe if new messages arrive at the TextInput of any of the users,
 * and if there are new messages, he will add them to the chat pane.
 * the chatBox will also observe if any of the font buttons are clicked, and update
 * the font of the messages accordingly.
 * the chatBox will draw new messages with font dictated dynamiclly with a Style strategy object
 *
 * name - the name of the user that the chat window belongs to
 * chatArea - the pane where the messages will be drawn
 * doc - the styled document of the pane
 * style - a strategy object which will determine in which font the next message will be drawn
 * messages - the existing messages in the chat so far
 * isBold - will keep if the messages are currently bolded or not
 *
 * Representation Invariant:
 * all fields != null
 * name doesn't contain ':'
 */
public class DiscussionWindow implements Observer {
	private String name;
	private JTextPane chatArea;
	private StyledDocument doc;
	private Style style;
	private List<String> messages; // keep all the messages and redraw them each time with a new style strategy.
	private boolean isBold;

	/**
	 * @requires name doesn't contain ':'
	 * @param name - the name of the user that the chat window belongs to
	 * @return a new ChatBox object representing a chat window
	 */
	public DiscussionWindow(String name){
		this.name = name;
		this.chatArea = new JTextPane();
		this.chatArea.setEditable(false);
		chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
		this.doc = chatArea.getStyledDocument();
		Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		this.style = defaultStyle;
		this.messages = new ArrayList<>();
		isBold = Boolean.FALSE;
		checkRep();
	}

	/**
	 * @return the JTextPane of the chat
	 */
	public JTextPane getChatPane(){
		checkRep();
		return this.chatArea;
	}

	// Observer Implementation
	/**
	 * will receive notification from an observable (about either a new message to add or a new font to draw
	 * the messages with) and handle it accordingly
	 * @param o - the observable that notifies us.
	 */

	@Override
	public void update(Observable o) { // TODO: delete this
		checkRep();
		// either an TextInput signaled us or a fontButton
		// check who signaled us
		if(o.getClass().equals(TextInput.class)){
			// an TextInput has signaled us that there is a new message from one of the users.
			// handle the new message
			this.handleNewMessage((TextInput)o);
		}
		else{
			// a fontButton signaled us we should change font
			this.changeFont((FontButtonListener)o);
		}
		checkRep();
	}

	/**
	 * will get the new message from the TextInput that has a new message, and add it to the chat window
	 * @param o - the TextInput that has a new message
	 */
	public void handleNewMessage(TextInput o){
		checkRep();
		// get the message
		String message = o.getCurrentMessage();
		// get the name of the user that typed it
		String name = o.getName();
		// add the new message to the chat area
		this.addNewMessage(message,name);
		checkRep();
	}

	/**
	 * add a new message to the chat window
	 * @param message - the message
	 * @param userName - the name of the user who wrote the message
	 */
	private void addNewMessage(String message,String userName){
		checkRep();
		// form the message to be added
		String messageToAdd = String.format("%s: %s\n", userName,message);
		// add the new message to the list of messages
		this.messages.add(messageToAdd);
		// add the new message to the chat pane
		// check if the new message is from our user. if it is - color it black, otherwise - color it in green.
		if(userName.equals(this.name)){
			StyleConstants.setForeground(this.style, Color.black);
		}else{
			StyleConstants.setForeground(this.style, Color.GREEN.darker());
		}
		try {
			this.doc.insertString(doc.getLength(), messageToAdd, this.style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		checkRep();
	}

	/**
	 * will change the font of all the existing messages on the chat window according to the font requested
	 * @param o - the listener of the font button that was pressed
	 */
	public void changeFont(FontButtonListener o){
		checkRep();
		// we need to check if the font button that was pressed belongs to our user or not
		// if it doesn't belong to our user - ignore it
		if(!o.getUserName().equals(this.name)){
			checkRep();
			return;
		}

		// we need to change the style of the existing messages, and to also change the
		// style so that future messages will be added with the right style
		// check what font was required
		if(o.getName().equals("Bold")){

			this.isBold = !this.isBold;
			// change the used style
			StyleConstants.setBold(this.style, this.isBold);
			// change the existing messages' style
			this.redrawMessages();
		}
		else if(o.getName().equals("Default")){
			StyleConstants.setBold(this.style, false);
			// change the existing messages' style
			this.redrawMessages();
			// change the font to Arial - the default font
			chatArea.setFont(new Font("Arial", Font.PLAIN, 15));
		}
		else if(o.getName().equals("Garamond")){
			chatArea.setFont(new Font("Garamond", Font.PLAIN, 15));
		}
		checkRep();
	}

	/**
	 * will redraw the messages on the screen with the current style
	 */
	private void redrawMessages() {
		checkRep();
		// first clear the text pane
		this.chatArea.setText("");
		// now redraw the messages
		for(String message : this.messages){
			if(message.startsWith(String.format("%s:",this.name))){
				// this message is from our user. should be painted black
				StyleConstants.setForeground(this.style, Color.black);
			}else{
				// this message is not from our user. should be painted in green
				StyleConstants.setForeground(this.style, Color.GREEN.darker());
			}
			// redraw the message
			try {
				this.doc.insertString(doc.getLength(), message, this.style);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		checkRep();
	}

	private void checkRep(){
		assert this.name != null &&
				this.chatArea != null &&
				this.doc != null &&
				this.style != null &&
				this.messages != null;
		assert this.name.contains(":") == false;
	}
}