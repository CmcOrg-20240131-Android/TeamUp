package com.cmcorg20230301.teamup.model.vo;

import java.util.Map;

public class LongObjectMapVO<T> {

    /**
     * map对象
     */
    private Map<Long, T> map;

    public Map<Long, T> getMap() {
        return map;
    }

    public void setMap(Map<Long, T> map) {
        this.map = map;
    }
}
