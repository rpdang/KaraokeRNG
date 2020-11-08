public class Star {
    private final String starName;
    private Star starPartner;

    public Star(String starName) {
        this.starName = starName;
    }

    public String getStarName() {
        return this.starName;
    }

    public Star getStarPartner() {
        return this.starPartner;
    }

    public void setStarPartner(Star starPartner) {
        if (!starPartner.getStarName().equalsIgnoreCase(this.starName))
            this.starPartner = starPartner;
    }

    public boolean isAvailable()    {
        return this.starPartner==null;
    }
}
