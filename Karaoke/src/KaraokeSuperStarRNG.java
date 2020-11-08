import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class KaraokeSuperStarRNG {
    private final Properties properties;
    private List<Star> stars;

    public KaraokeSuperStarRNG() {
        properties = new Properties();
    }

    public void getStars() {
        loadProperties();
        generateStars();
        outputTXT();
    }

    private void loadProperties() {
        Path starLocation = Paths.get("resources/RESOURCES.properties");
        try {
            properties.load(Files.newInputStream(starLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateStars() {
        String[] stars = properties.get("karaokeStars").toString().replaceAll("^\"|\"$", "").trim().split(",");
        List<Star> starList = Arrays.stream(stars).map(Star::new).collect(Collectors.toList());
        Collections.shuffle(starList);
        for (int i = 0; i < starList.size(); i += 2) {
            Star star1, star2;
            star1 = starList.get(i);
            star2 = starList.get(i + 1);
            if (star1.isAvailable() && star2.isAvailable()) {
                star1.setStarPartner(star2);
                star2.setStarPartner(star1);
            }
        }
        this.stars = starList;
    }

    private void outputTXT() {
        File starsCSV = new File("stars.txt");
        try (PrintWriter pw = new PrintWriter(starsCSV)) {
           pw.append("Star Partner");
           pw.append(System.lineSeparator());
           stars.forEach(star -> {
               pw.append(star.getStarName())
                       .append(" ")
                       .append(star.getStarPartner().getStarName())
                       .append(System.lineSeparator());
           });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        KaraokeSuperStarRNG app = new KaraokeSuperStarRNG();
        app.getStars();
    }
}
