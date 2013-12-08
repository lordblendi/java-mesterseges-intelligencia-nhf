
public class Targy {
	public String mKod;
	public String mNev;
	public String relURL;
	public String mSemester;
	public String mKovKod;
	public String mKredit;
	public String mFelev;
	
	public String getURL() {
		return "https://www.vik.bme.hu" + relURL;
	}

    @Override
    public String toString(){
        return "Név: " + mNev + " Kód: " + mKod + " Kredit: " + mKredit + " Szemeszter: " + mSemester + " Félév: " + mFelev;
    }
}
