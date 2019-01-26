package Q2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 * Abstraction Function:
 * FontButtonListener listens on a button and notifies the chatBoxes
 * of the newly requested font when pressed
 * 
 * name - the name of the button. this indicates of the font that is required
 * observers - the list of chatboxes that this button should notify
 * button - the button this class listens on.
 * 
 * Representation Invariant:
 * all fields != null
 * name is either "Default","Bold" or "Garmond"
 */
public class FontButtonListener implements Observable, ActionListener{
	private String userName;
	private String fontName;
	private JButton btn;
	private List<Observer> observers;

	/** 
	 * returns a new FontButtonListener
	 * @return a new FontButtonListener 
	 */
	public FontButtonListener(String fontName,String userName,JButton btn,List<Observer> observers){
		this.userName = userName;
		this.fontName = fontName;
		this.btn = btn;
		this.btn.addActionListener(this);
		this.observers = new ArrayList<>(observers);
		checkRep();
	}
	
	/**
	 * @return the name of the button listener
	 */
	public String getName(){
		checkRep();
		return this.fontName;
	}
	
	/**
	 * @return the user name of the button listener
	 */
	public String getUserName(){
		checkRep();
		return this.userName;
	}

	/**
	 * will notify all the observers of the font button that was pressed
	 */
	@Override
	public void notifyObservers() {
		checkRep();
		for(Observer o : this.observers){
			o.update(this);
		}
		checkRep();
	}

	/**
	 * will add a new observer to the observers' list
	 * @param o - the observer to add
	 */
	@Override
	public void attach(Observer o) {
		checkRep();
		if(!this.observers.contains(o)){
			this.observers.add(o);
		}
		checkRep();
	}

	/**
	 * will remove an observer from the observers' list
	 * @param o - the observer to remove
	 */
	@Override
	public void detach(Observer o) {
		checkRep();
		if(this.observers.contains(o)){
			this.observers.remove(o);
		}
		checkRep();
	}

	/**
	 * The action handler that handles pressing event on the button we listen on
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		checkRep();
		this.notifyObservers();
		checkRep();
	}
	
	private void checkRep(){
		assert this.fontName != null && this.observers != null && this.btn != null;
		assert this.fontName.equals("Default") || this.fontName.equals("Bold") || this.fontName.equals("Garamond");
	}
}
