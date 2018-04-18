import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		Knight knight = new Knight(0,0,null,null,null);
		DragonKnight dragonKnight = new DragonKnight(0,0,null,null,null);
		Archer archer = new Archer(0,0,null,null,null);
		Warrior warrior = new Warrior(0,0,null,null,null);
		Healer healer = new Healer(0,0,null,null,null);
		Mage mage = new Mage(0,0,null,null,null);
		Alchemist alchemist = new Alchemist(0,0,null,null,null);
		Assassin assassin = new Assassin(0,0,null,null,null);
		UserCredentials user = new UserCredentials("TrucThanh", "TrucThanh");
		
		StartMenuGUI start = new StartMenuGUI(user);
		
		//LoginGUI login = new LoginGUI();
				
//		Music music = new Music("Drowning.wav", "icecream.wav", "xotourlife.wav",
//				"iSpy.wav", "rolex.wav");
//
//		music.run();


	}

}
