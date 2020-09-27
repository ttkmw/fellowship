package beyondeyesight.chat.domain;

import java.util.Objects;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("sender")
public class Sender {
    private final UUID id;

    private Sender(UUID id) {
        this.id = id;
    }

    public static Sender of(UUID id) {
        return new Sender(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sender sender = (Sender) o;
        return Objects.equals(id, sender.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
