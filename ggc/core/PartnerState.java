package ggc.core;

import java.io.Serializable;

// abstract class instead for atributes
interface PartnerState {
    @Override
    String toString();
    double getModifier(); //Solução temporária, P2 para ja. Juntar períodos qndo Transaction feito + passar daysPassed como args para multar
    PartnerState getNext();
    PartnerState getPrevious();
    double getPointsLost();
}

class Normal implements PartnerState, Serializable {
    private static final long serialVersionUID = 2376498164L;

    private static final PartnerState SINGLETON = new Normal();
    private static final int POINTS_THRESHOLD = 0;

    private Normal(){}
    public static PartnerState getStatus() {
        return SINGLETON;
    }

    public String toString() {
        return "NORMAL";
    }

    public double getModifier() {
        return 1.0;
    }

    public PartnerState getNext() {
        return Selection.getStatus();
    }

    public PartnerState getPrevious() {
        return SINGLETON;
    }

    public double getPointsLost() {
        return 1.0;
    }
}


class Selection implements PartnerState, Serializable {
    private static final long serialVersionUID = 2376491114L;

    private static final PartnerState SINGLETON = new Selection();
    private static final int POINTS_THRESHOLD = 2000;

    private Selection(){}
    public static PartnerState getStatus() {
        return SINGLETON;
    }

    public String toString() {
        return "SELECTION";
    }

    public double getModifier() {
        return .95;
    }

    public PartnerState getNext() {
        return Elite.getStatus();
    }

    public PartnerState getPrevious() {
        return Normal.getStatus();
    }

    public double getPointsLost() {
        return 0.9;
    }
}


class Elite implements PartnerState, Serializable {
    private static final long serialVersionUID = 234588189L;

    private static final PartnerState SINGLETON = new Elite();
    private static final int POINTS_THRESHOLD = 25000;

    private Elite(){}
    public static PartnerState getStatus() {
        return SINGLETON;
    }

    public String toString() {
        return "ELITE";
    }

    public double getModifier() {
        return .9;
    }

    public PartnerState getNext() {
        return SINGLETON;
    }

    public PartnerState getPrevious() {
        return Selection.getStatus();
    }

    public double getPointsLost() {
        return 0.75;
    }
}
