package beyondeyesight.chat.domain;

public class Id {
    //todo: check if UUID is better
    private final String id;

    private Id(String id) {
        this.id = id;
    }

    static Id of(String id) {
        return new Id(id);
    }
}
