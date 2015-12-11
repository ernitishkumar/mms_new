package mms.com.beans;

public class FeederList {

	private String RESION; 
	private String CIRCLE_NAME; 
	private String DIVISION;
	private String EHV_SS_CODE;
	private String EHV_SS_NAME; 
	private String KV33_FDR_CODE; 
	private String KV33_FDR_NAME; 
	private String GROUP_ALLOTED; 
	private String SUBSTATION_NAME_33_11; 
	private String KV_SUBSTATION_CODE_33; 
	private String KV11FDR_NAME; 
	private String KV11FDR_CODE; 
	private String CATEGORY; 
	private String TYPE; 
	private String UID;
	public String getRESION() {
		return RESION;
	}
	public void setRESION(String rESION) {
		RESION = rESION;
	}
	public String getCIRCLE_NAME() {
		return CIRCLE_NAME;
	}
	public void setCIRCLE_NAME(String cIRCLE_NAME) {
		CIRCLE_NAME = cIRCLE_NAME;
	}
	public String getDIVISION() {
		return DIVISION;
	}
	public void setDIVISION(String dIVISION) {
		DIVISION = dIVISION;
	}
	public String getEHV_SS_CODE() {
		return EHV_SS_CODE;
	}
	public void setEHV_SS_CODE(String eHV_SS_CODE) {
		EHV_SS_CODE = eHV_SS_CODE;
	}
	public String getEHV_SS_NAME() {
		return EHV_SS_NAME;
	}
	public void setEHV_SS_NAME(String eHV_SS_NAME) {
		EHV_SS_NAME = eHV_SS_NAME;
	}
	public String getKV33_FDR_CODE() {
		return KV33_FDR_CODE;
	}
	public void setKV33_FDR_CODE(String kV33_FDR_CODE) {
		KV33_FDR_CODE = kV33_FDR_CODE;
	}
	public String getKV33_FDR_NAME() {
		return KV33_FDR_NAME;
	}
	public void setKV33_FDR_NAME(String kV33_FDR_NAME) {
		KV33_FDR_NAME = kV33_FDR_NAME;
	}
	public String getGROUP_ALLOTED() {
		return GROUP_ALLOTED;
	}
	public void setGROUP_ALLOTED(String gROUP_ALLOTED) {
		GROUP_ALLOTED = gROUP_ALLOTED;
	}
	public String getSUBSTATION_NAME_33_11() {
		return SUBSTATION_NAME_33_11;
	}
	public void setSUBSTATION_NAME_33_11(String sUBSTATION_NAME_33_11) {
		SUBSTATION_NAME_33_11 = sUBSTATION_NAME_33_11;
	}
	public String getKV_SUBSTATION_CODE_33() {
		return KV_SUBSTATION_CODE_33;
	}
	public void setKV_SUBSTATION_CODE_33(String kV_SUBSTATION_CODE_33) {
		KV_SUBSTATION_CODE_33 = kV_SUBSTATION_CODE_33;
	}
	public String getKV11FDR_NAME() {
		return KV11FDR_NAME;
	}
	public void setKV11FDR_NAME(String kV11FDR_NAME) {
		KV11FDR_NAME = kV11FDR_NAME;
	}
	public String getKV11FDR_CODE() {
		return KV11FDR_CODE;
	}
	public void setKV11FDR_CODE(String kV11FDR_CODE) {
		KV11FDR_CODE = kV11FDR_CODE;
	}
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String toString() {
		return "FeederList [RESION=" + RESION + ", CIRCLE_NAME=" + CIRCLE_NAME
				+ ", DIVISION=" + DIVISION + ", EHV_SS_CODE=" + EHV_SS_CODE
				+ ", EHV_SS_NAME=" + EHV_SS_NAME + ", KV33_FDR_CODE="
				+ KV33_FDR_CODE + ", KV33_FDR_NAME=" + KV33_FDR_NAME
				+ ", GROUP_ALLOTED=" + GROUP_ALLOTED
				+ ", SUBSTATION_NAME_33_11=" + SUBSTATION_NAME_33_11
				+ ", KV_SUBSTATION_CODE_33=" + KV_SUBSTATION_CODE_33
				+ ", KV11FDR_NAME=" + KV11FDR_NAME + ", KV11FDR_CODE="
				+ KV11FDR_CODE + ", CATEGORY=" + CATEGORY + ", TYPE=" + TYPE
				+ ", UID=" + UID + "]";
	}
	public FeederList(String rESION, String cIRCLE_NAME, String dIVISION,
			String eHV_SS_CODE, String eHV_SS_NAME, String kV33_FDR_CODE,
			String kV33_FDR_NAME, String gROUP_ALLOTED,
			String sUBSTATION_NAME_33_11, String kV_SUBSTATION_CODE_33,
			String kV11FDR_NAME, String kV11FDR_CODE, String cATEGORY,
			String tYPE, String uID) {
		
		RESION = rESION;
		CIRCLE_NAME = cIRCLE_NAME;
		DIVISION = dIVISION;
		EHV_SS_CODE = eHV_SS_CODE;
		EHV_SS_NAME = eHV_SS_NAME;
		KV33_FDR_CODE = kV33_FDR_CODE;
		KV33_FDR_NAME = kV33_FDR_NAME;
		GROUP_ALLOTED = gROUP_ALLOTED;
		SUBSTATION_NAME_33_11 = sUBSTATION_NAME_33_11;
		KV_SUBSTATION_CODE_33 = kV_SUBSTATION_CODE_33;
		KV11FDR_NAME = kV11FDR_NAME;
		KV11FDR_CODE = kV11FDR_CODE;
		CATEGORY = cATEGORY;
		TYPE = tYPE;
		UID = uID;
	}
	public FeederList() {
	
	}
	
	
}
