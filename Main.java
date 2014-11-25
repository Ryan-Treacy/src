
public class Main {

		public static void main(String[] args) {
			
		FileIO.createGuestFile();  // This creates or loads the GUEST file.
		UserInfoGUI UserGUI = new UserInfoGUI(); // Initializes the GUI and files needed.
		UserGUI.buildLogin(); // Starts the program.
	}

}