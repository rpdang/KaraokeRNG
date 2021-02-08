import lombok.Getter;

public class Star {
    @Getter
    private final String starName;
    @Getter
    private Star starPartner;

    public Star(String starName) {
        this.starName = starName;
    }

    public void setStarPartner(Star starPartner) {
        if (!starPartner.getStarName().equalsIgnoreCase(this.starName))
            this.starPartner = starPartner;
    }

    public boolean isAvailable() {
        return this.starPartner == null;
    }
}
