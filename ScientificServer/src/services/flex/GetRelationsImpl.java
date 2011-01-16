package services.flex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import distribution.DegreeDistribution;
import distribution.DistanceDistribution;

public class GetRelationsImpl implements GetRelations{

	//@Override
	public List<Relations> relations(String name) {
		GetRelation getRealation = new GetRelation();
		//OscarG.Calderón		Y.C.Zhang
		return getRealation.createReations(name);
	}
	
	public List<Relations> testRelations() {
		List<Relations> rlList = new ArrayList<Relations>();
		Relations R1 = new Relations();
		R1.setArthorString("liuhao");
		//R1.setPaperString("how to mk love");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("how to mk love");
		arrayList.add("my heart will go on");
		R1.setPaperList(arrayList);
		//R1.getRelationMap().put("liuhao",R1.getPaperList());
		R1.setPaperList(arrayList);	
		rlList.add(R1);
		
		Relations R2 = new Relations();
		R2.setArthorString("zhangliang");
		arrayList= new ArrayList<String>();
		arrayList.add("keliwa");
		arrayList.add("dangdangdangdang");
		R2.setPaperList(arrayList);
		rlList.add(R2);
		//R1.getRelationMap().put("zhangliang",R1.getPaperList());
		
		Relations R3 = new Relations();
		R3.setArthorString("yufei");
		arrayList= new ArrayList<String>();
		arrayList.add("img");
		arrayList.add("kelly");
		R3.setPaperList(arrayList);
		rlList.add(R3);
		return rlList;
	}
	
	public List<Relations> startSearch(String name) {
		System.out.println(name);
		//GetRelation getRealation = new GetRelation();	
		//System.out.println(testRelations().size());
		return testRelations();
	}
	
	public List<Set<String>> getDegreeDestribution(String name) {
		DistanceDistribution distanceDistribution = new DistanceDistribution();
		//GetRelation getRealation = new GetRelation();	
		//System.out.println(testRelations().size());
		return distanceDistribution.getDistantsCloud(name);
	}
	
	public List<List<Integer>> getPowerLawDestribution(String tag) {
		DegreeDistribution degreeDistribution = new DegreeDistribution();
		List<List<Integer>> degreeList = new ArrayList<List<Integer>>();
		System.err.println(System.getProperty("user.dir"));
		degreeList.add(degreeDistribution.getDegreeData("../webapps/ScientificServer/WEB-INF/config/degree_name_number.txt"));
		degreeList.add(degreeDistribution.getDegreeData("../webapps/ScientificServer/WEB-INF/config/degree_coauthor_name.txt"));
//		return degreeDistribution.getDegreeData("G:/data/program temp data/degree_coauthor_name.txt");
//		return degreeDistribution.getDegreeData("G:/data/program temp data/degree_name_number.txt");
		return degreeList;
	}
	
	public static void main(String[] args) {
		GetRelationsImpl getRelationsImpl = new GetRelationsImpl();
		//Ren Jie Guido Caldarelli Ginestra Bianconi Albert-László Barabási Zoltán Dezs?
//		getRelationsImpl.relations("Zoltán Dezs?");
		//getRelationsImpl.sixDegreeSeparation("Yi-Cheng Zhang","barbarsi" );
		getRelationsImpl.getDegreeDestribution("Yi-Cheng Zhang");
	}
	
//	private void printMap(HashMap<String, List<String>> map,String name) {
//		for (String element : map.keySet()) {
//			//System.err.println(map.get(element).size());
//			for (String paper : map.get(element)) {
//				System.out.println(name+"--"+element+"-->"+paper);
//			}
//		}
//	}
	
	public List<String> sixDegreeSeparation(String authorOne,String authorTwo) {
		SixDegreesSeparation sixDegreesSeparation = new SixDegreesSeparation();
		//OscarG.Calderón		Y.C.Zhang
		return sixDegreesSeparation.createsixDegrees(authorOne,authorTwo);
	}

}
