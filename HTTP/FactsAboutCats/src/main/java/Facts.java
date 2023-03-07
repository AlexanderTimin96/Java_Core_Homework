import com.fasterxml.jackson.annotation.JsonProperty;

public class Facts {
    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private int upvotes;

    public Facts(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        if (upvotes == null) {
            this.upvotes = 0;
        } else {
            this.upvotes = upvotes;
        }
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Facts{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}
