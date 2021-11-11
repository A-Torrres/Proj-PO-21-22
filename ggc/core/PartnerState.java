package ggc.core;

import java.io.Serializable;

// abstract class instead for atributes
interface PartnerState {
    @Override
    String toString();

}

class Normal implements PartnerState, Serializable {
    private static final long serialVersionUID = 2376498164L;

    @Override
    public String toString() {
        return "NORMAL";
    }

}


class Selection implements PartnerState, Serializable {
    private static final long serialVersionUID = 2376491114L;

    @Override
    public String toString() {
        return "SELECTION";
    }

}


class Elite implements PartnerState, Serializable {
    private static final long serialVersionUID = 234588189L;

    @Override
    public String toString() {
        return "ELITE";
    }

}
