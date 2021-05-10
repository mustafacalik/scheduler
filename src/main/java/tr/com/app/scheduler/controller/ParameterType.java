package tr.com.app.scheduler.controller;

public enum ParameterType {
    EVENT_START("parameter.event.start"),
    EVENT_END("parameter.event.end"),
    LUNCH_START("parameter.lunch.start"),
    LUNCH_END("parameter.lunch.end"),
    NETWORK_EVENT_START("parameter.network.event.start"),
    NETWORK_EVENT_END("parameter.network.event.end");

    private String key;

    ParameterType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
