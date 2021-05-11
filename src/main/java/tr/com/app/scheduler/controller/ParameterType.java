package tr.com.app.scheduler.controller;

/**
 * The enum Parameter type.
 */
public enum ParameterType {
    /**
     * Event start parameter type.
     */
    EVENT_START("parameter.event.start"),
    /**
     * Event end parameter type.
     */
    EVENT_END("parameter.event.end"),
    /**
     * Lunch start parameter type.
     */
    LUNCH_START("parameter.lunch.start"),
    /**
     * Lunch end parameter type.
     */
    LUNCH_END("parameter.lunch.end"),
    /**
     * Network event start parameter type.
     */
    NETWORK_EVENT_START("parameter.network.event.start"),
    /**
     * Network event end parameter type.
     */
    NETWORK_EVENT_END("parameter.network.event.end");

    private String key;

    ParameterType(String key) {
        this.key = key;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }
}
