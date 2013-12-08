
public class Targy implements Comparable{
	
	public String mKod = "";
	public String mNev = "";
	public String relURL = "";
	public String mSemester = "";
	public String mKovKod = "";
	public String mKredit = "";
	public String mFelev = "";
	public double mSumSuly;
	
	public int compareTo(Targy t){
		return (int) (1000*(t.mSumSuly - mSumSuly));
	}
	
	public String getURL() {
		return "https://www.vik.bme.hu" + relURL;
	}

    @Override
    public String toString(){
        return "Név: " + mNev + " Kód: " + mKod + " Kredit: " + mKredit + " Szemeszter: " + mSemester + " Félév: " + mFelev;
    }

	@Override
	public int compareTo(Object t) {
		Targy tmp = (Targy)t;
		return (int) (1000*(tmp.mSumSuly - mSumSuly));
	}
}
