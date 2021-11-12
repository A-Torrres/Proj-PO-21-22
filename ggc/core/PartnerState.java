package ggc.core;

import java.io.Serializable;

// abstract class instead for atributes
interface PartnerState {
    @Override
    String toString();
    double getModifier(PaymentPeriod p, int diff); //Solução temporária, P2 para ja. Juntar períodos qndo Transaction feito + passar daysPassed como args para multar
    PartnerState getNext();
    PartnerState getPrevious();
    double getPointsRemaining();
    int getGracePeriod();
    int getPointThreshold();
}

class Normal implements PartnerState, Serializable {
    private static final long serialVersionUID = 2376498164L;

    private static final PartnerState SINGLETON = new Normal();
    private static final int POINTS_THRESHOLD = 0;
    private static final int GRACE_PERIOD = 0;

    private Normal(){}
    public static PartnerState getStatus() {
        return SINGLETON;
    }

    public String toString() {
        return "NORMAL";
    }

    public double getModifier(PaymentPeriod p, int diff) {
        switch(p){
            case P1: return 0.9;
            case P2: return 1.0;
            case P3: return 1.0 + diff*0.05;
            case P4: return 1.0 + diff*0.1;
        }
        return 1.0;
    }

    public PartnerState getNext() {
        return Selection.getStatus();
    }

    public PartnerState getPrevious() {
        return SINGLETON;
    }

    public double getPointsRemaining() {
        return .0;
    }

    public int getGracePeriod() {
        return GRACE_PERIOD;
    }

    public int getPointThreshold() {
        return POINTS_THRESHOLD;
    }
}


class Selection implements PartnerState, Serializable {
    private static final long serialVersionUID = 2376491114L;

    private static final PartnerState SINGLETON = new Selection();
    private static final int POINTS_THRESHOLD = 2000;
    private static final int GRACE_PERIOD = 2;

    private Selection(){}
    public static PartnerState getStatus() {
        return SINGLETON;
    }

    public String toString() {
        return "SELECTION";
    }

    public double getModifier(PaymentPeriod p, int diff) {
        switch(p){
            case P1: return 0.9;
            case P2: if(diff <= -2) return 0.95; else return 1.0;
            case P3: if(diff > 1) return 1.0 + diff*0.02; else return 1.0;
            case P4: return 1.0 + diff*0.05;
        }
        return 1.0;
    }

    public PartnerState getNext() {
        return Elite.getStatus();
    }

    public PartnerState getPrevious() {
        return Normal.getStatus();
    }

    public double getPointsRemaining() {
        return .1;
    }

    public int getGracePeriod() {
        return GRACE_PERIOD;
    }

    public int getPointThreshold() {
        return POINTS_THRESHOLD;
    }
}


class Elite implements PartnerState, Serializable {
    private static final long serialVersionUID = 234588189L;

    private static final PartnerState SINGLETON = new Elite();
    private static final int POINTS_THRESHOLD = 25000;
    private static final int GRACE_PERIOD = 15;

    private Elite(){}
    public static PartnerState getStatus() {
        return SINGLETON;
    }

    public String toString() {
        return "ELITE";
    }

    public double getModifier(PaymentPeriod p, int diff) {
        switch(p){
            case P1: return 0.9;
            case P2: return 0.9;
            case P3: return 0.95;
            case P4: return 1.0;
        }
        return 1.0;
    }

    public PartnerState getNext() {
        return SINGLETON;
    }

    public PartnerState getPrevious() {
        return Selection.getStatus();
    }

    public double getPointsRemaining() {
        return .25;
    }

    public int getGracePeriod() {
        return GRACE_PERIOD;
    }

    public int getPointThreshold() {
        return POINTS_THRESHOLD;
    }
}
