import java.sql.*;
import java.util.Scanner;

public class MKApp {

    public static Character getCharacterByName(String characterName) {
        Character character = null;

        String charSql = "SELECT id, name, bio FROM characters WHERE name = ?";
        String fatalitySql = "SELECT name, input_command FROM fatalities WHERE character_id = ?";
        String specialSql = "SELECT move_name, input_command FROM special_moves WHERE character_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement charStmt = conn.prepareStatement(charSql)) {

            charStmt.setString(1, characterName);
            ResultSet rsChar = charStmt.executeQuery();

            if (rsChar.next()) {
                int charId = rsChar.getInt("id");
                character = new Character(rsChar.getString("name"), rsChar.getString("bio"));

                // Fatalities!!!!!
                try (PreparedStatement fStmt = conn.prepareStatement(fatalitySql)) {
                    fStmt.setInt(1, charId);
                    ResultSet rsF = fStmt.executeQuery();
                    while (rsF.next()) {
                        character.addFatality(rsF.getString("name") + " (" + rsF.getString("input_command") + ")");
                    }
                }

                // Special Moves!!!!
                try (PreparedStatement sStmt = conn.prepareStatement(specialSql)) {
                    sStmt.setInt(1, charId);
                    ResultSet rsS = sStmt.executeQuery();
                    while (rsS.next()) {
                        character.addSpecialMove(rsS.getString("move_name") + " (" + rsS.getString("input_command") + ")");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return character;
    }

    public static void displayCharacter(Character c) {
        System.out.println(Colors.BOLD + Colors.BLUE + "\n=== " + c.getName() + " ===" + Colors.RESET);
        System.out.println(Colors.YELLOW + "Bio: " + Colors.RESET + c.getBio());

        System.out.println(Colors.RED + "\nFatalities:" + Colors.RESET);
        if (c.getFatalities().isEmpty()) System.out.println("- None");
        else c.getFatalities().forEach(f -> System.out.println("- " + f));

        System.out.println(Colors.GREEN + "\nSpecial Moves:" + Colors.RESET);
        if (c.getSpecialMoves().isEmpty()) System.out.println("- None");
        else c.getSpecialMoves().forEach(s -> System.out.println("- " + s));
    }

    public static void main(String[] args) {
        // Play theme in background!!!
        new Thread(() -> MusicPlayer.playMusic("resources/mktheme.wav", true)).start();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a Mortal Kombat character name: ");
        String inputName = scanner.nextLine();

        Character c = getCharacterByName(inputName);
        if (c != null) displayCharacter(c);
        else System.out.println(Colors.RED + "Character not found." + Colors.RESET);
    }
}


// by devonte harris