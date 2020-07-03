package beyondeyesight.chat.domain;

public class RegionId {
    private final String id;

    private RegionId(String id) {
        this.id = id;
    }

    static RegionId of(String id) {
        return new RegionId(id);
    }
}
