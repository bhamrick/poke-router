//the in-battle stat modifiers to a pokemon (caused by e.g. x attack)
public class StatModifier {
    //private static final int XACCON = 100;
    //int from -6 to +6 that represents the stage
    private int atk = 0;
    private int def = 0;
    private int spd = 0;
    private int spc = 0;
    private int accuracy = 0;
    private int evasion = 0;
    private boolean usedxacc = false;
    
    public StatModifier() {
    }
    public StatModifier(int atk, int def, int spd, int spc) {
        this.atk = atk;
        this.def = def;
        this.spc = spc;
        this.spd = spd;
    }
    public StatModifier(int atk, int def, int spd, int spc, boolean xacc) {
        this(atk,def,spd,spc,0,0,xacc);
    }
    
//    public StatModifier(int atk, int def, int spd, int spc, int accuracy, int evasion) {
//        this(atk,def,spd,spc);
//        this.accuracy = accuracy;
//        this.evasion = evasion;
//    }
    public StatModifier(int atk, int def, int spd, int spc, int accuracy, int evasion, boolean xacc) {
        this(atk,def,spd,spc);
        this.accuracy = accuracy;
        this.evasion = evasion;
        this.usedxacc = xacc;
    }

    //used to keep the stage between -6 and +6
    private static int bound(int stage) {
        if (stage < -6)
            return -6;
        else if (stage > 6)
            return 6;
        else
            return stage;
    }
    
    //in gen 1, accuracy/evasion stages are done the same as other stats
    private static double accuracyEvasionMultiplier(int stage) {
        return normalStatMultiplier(stage);
    }
    
    //multiplier for atk,def,spc,spd
    private static double normalStatMultiplier(int stage) {
        if(stage >= 0)
            return (double) (2+stage) / 2;
        else
            return (double) 2 / (2-stage);
    }
    
    
    public int getAccuracyStage() {
        return accuracy;
    }
    public void setAccuracyStage(int accuracy) {
        this.accuracy = bound(accuracy);
    }
    public int getEvasionStage() {
        return evasion;
    }
    public void setEvasionStage(int evasion) {
        this.evasion = bound(evasion);
    }
    public int getAtkStage() {
        return atk;
    }
    public void setAtkStage(int atk) {
        this.atk = bound(atk);
    }
    public int getDefStage() {
        return def;
    }
    public void setDefStage(int def) {
        this.def = bound(def);
    }
    public int getSpcStage() {
        return spc;
    }
    public void setSpcStage(int spc) {
        this.spc = bound(spc);
    }
    public int getSpdStage() {
        return spd;
    }
    public void setSpdStage(int spd) {
        this.spd = bound(spd);
    }
    public boolean getUsedXAcc() {
        return usedxacc;
    }
    public void useXAcc() {
        this.usedxacc = true;
    }
    public void unuseXAcc() {
        this.usedxacc = false;
    }
    
    public void incrementAccuracyStage() {
        incrementAccuracyStage(1);
    }
    public void incrementEvasionStage() {
        incrementEvasionStage(1);
    }
    public void incrementAtkStage() {
        incrementAtkStage(1);
    }
    public void incrementDefStage() {
        incrementDefStage(1);
    }
    public void incrementSpcStage() {
        incrementSpcStage(1);
    }
    public void incrementSpdStage() {
        incrementSpdStage(1);
    }

    public void incrementAccuracyStage(int i) {
        setAccuracyStage(getAccuracyStage() + i);
    }
    public void incrementEvasionStage(int i) {
        setEvasionStage(getEvasionStage() + i);
    }
    public void incrementAtkStage(int i) {
        setAtkStage(getAtkStage() + i);
    }
    public void incrementDefStage(int i) {
        setDefStage(getDefStage() + i);
    }
    public void incrementSpcStage(int i) {
        setSpcStage(getSpcStage() + i);
    }
    public void incrementSpdStage(int i) {
        setSpdStage(getSpdStage() + i);
    }
    
    public double getAccuracyMultiplier() {
        return accuracyEvasionMultiplier(accuracy);
    }
    public double getEvasionMultiplier() {
        return accuracyEvasionMultiplier(evasion);
    }
    
    public double getAtkMultiplier() {
        return normalStatMultiplier(atk);
    }
    public double getDefMultiplier() {
        return normalStatMultiplier(def);
    }
    public double getSpcMultiplier() {
        return normalStatMultiplier(spc);
    }
    public double getSpdMultiplier() {
        return normalStatMultiplier(spd);
    }
    
    public String summary() {
        return String.format("+[%s/%s/%s/%s]%s",atk,def,spd,spc,(usedxacc ? " +X ACC" : ""));
    }
    
    public int modAtk(Pokemon p) {
        return Math.max((int) (p.getAtk() * getAtkMultiplier()),1);
    }
    public int modDef(Pokemon p) {
        return Math.max((int) (p.getDef() * getDefMultiplier()),1);
    }
    public int modSpc(Pokemon p) {
        return Math.max((int) (p.getSpc() * getSpcMultiplier()),1);
    }
    public int modSpd(Pokemon p) {
        return Math.max((int) (p.getSpd() * getSpdMultiplier()),1);
    }
    public boolean hasMods() {
        return atk != 0 || def != 0 || spc != 0 || spd != 0 || accuracy != 0 || evasion != 0 || usedxacc;
    }
    
    public String modSummary(Pokemon p) {
        return String.format("%s/%s/%s/%s/%s",p.getHP(),modAtk(p),modDef(p),modSpd(p),modSpc(p));
    }
    
}