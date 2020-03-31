package com.geektcp.alpha.design.statemachine.event;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 事件定义（包含事件枚举 和 事件数据）
 *
 * @param <T>
 */
@Data
public abstract class AbstractEvent<T extends Enum<T>> {

    private final T type;

    public AbstractEvent(T type) {
        this.type = type;
    }

    public T getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Event{" + "type=" + type +  '}';
    }
}
