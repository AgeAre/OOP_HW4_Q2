package Q2;


public interface Observable {
	public void notifyObservers();
	public void attach(Observer o);
	public void detach(Observer o);
}
