package com.hawa.scrap.event;


public class VolumePressEvent {

    public VolumePressEvent(Volume volume) {
        mVolume = volume;
    }

    private final Volume mVolume;

    public Volume getVolume() {
        return mVolume;
    }

    public enum Volume {
        Up,
        Down
    }


}
