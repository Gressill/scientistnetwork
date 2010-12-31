package services.flex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//QW2NX5fy6Un5
public class Relations {
	private String arthorString;
	
	private List<String> paperList;
	//private HashMap<String, List<String>> relationMap ;
	public Relations(){
		//relationMap = new HashMap<String, List<String>>();
		paperList = new ArrayList<String>();
	}
	
	public String getArthorString() {
		return arthorString;
	}
	public void setArthorString(String arthorString) {
		this.arthorString = arthorString;
	}
	
	public List<String> getPaperList() {
		return paperList;
	}
	public void setPaperList(List<String> paperList) {
		this.paperList = paperList;
	}
//	public HashMap<String, List<String>> getRelationMap() {
//		return relationMap;
//	}
//	public void setRelationMap(HashMap<String, List<String>> relationMap) {
//		this.relationMap = relationMap;
//	}
}
