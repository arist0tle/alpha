package com.geektcp.alpha.design.statemachine.example;

import com.geektcp.alpha.design.statemachine.event.AbstractEvent;

public class LocalStateEvent extends AbstractEvent<LocalStateEventType> {

    private String uuid;

    public LocalStateEvent(String uuid, LocalStateEventType type) {
        super(type);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
