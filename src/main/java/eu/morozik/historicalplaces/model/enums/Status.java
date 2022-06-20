package eu.morozik.historicalplaces.model.enums;

public enum Status {
    ACTIVE,
    BANNED;

    public static Status getStatus(Long i) {
        if (i == 1)
            return ACTIVE;
        if (i == 2)
            return BANNED;
        return null;
    }
}
