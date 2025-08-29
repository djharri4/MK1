import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private String bio;
    private List<String> fatalities = new ArrayList<>();
    private List<String> specialMoves = new ArrayList<>();

    public Character(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public String getName() { return name; }
    public String getBio() { return bio; }
    public List<String> getFatalities() { return fatalities; }
    public List<String> getSpecialMoves() { return specialMoves; }

    public void addFatality(String fatality) { fatalities.add(fatality); }
    public void addSpecialMove(String move) { specialMoves.add(move); }
}
