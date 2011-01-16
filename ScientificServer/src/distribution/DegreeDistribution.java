package distribution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import libs.FileOperate;

public class DegreeDistribution extends DistributionTools {
	
	private Set<String> authorSet = new HashSet<String>();
	private static HashSet<Integer> idHashSet = new HashSet<Integer>();
	private static HashMap<Integer, Integer> degreeHashMap = new HashMap<Integer, Integer>();
	private static int temptag = 0;
	
	public DegreeDistribution() {
		super();
		for (int i = 0; i < 100; i++) {
			degreeHashMap.put(i, 0);
		}
	}
	/**
	 * 算出每个人的合作者数
	 */
	public void caculateDrgreeDistubrition() {
		operateFileByLines("G:/data/program temp data/name.txt");
		StringBuilder sBuilder = new StringBuilder();
		for (String name : authorSet) {
			sBuilder.append(name);
			sBuilder.append("=");
			sBuilder.append(getAuthorSetSize(name));
			sBuilder.append("\r\n");
//			System.out.println(name+" "+ getAuthorSet(name).size());
			if (sBuilder.length() >= 1000000) {
				System.out.println("name is "+name);
				FileOperate.writeWithFileWrite("G:/data/program temp data/coauthor_name.txt",sBuilder.toString());
				sBuilder.setLength(0);
			}
		}
		FileOperate.writeWithFileWrite("G:/data/program temp data/coauthor_name.txt", sBuilder.toString());
		sBuilder.setLength(0);
	}
	
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void operateFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
//				System.out.println("line " + line + ": " + tempString);
				authorSet.add(tempString);
				//fileProcessByLines();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	//--------------------------------------
	
	public void getNodeDegree(String fileString) {
		String[] idStrings = null;
		int temp;
		idStrings = fileString.split(" ");
		//System.out.println(idStrings[temptag]);
		temp = Integer.parseInt(idStrings[temptag]);
		idHashSet.add(temp);
	}

	// //put set into map
	private void setInMap() {
		Iterator<Integer> iterator = idHashSet.iterator();

		//System.out.println("idHashSet size is : "+idHashSet.size());
		while (iterator.hasNext()) {
			int elem = (int) iterator.next();
			degreeHashMap.put(elem, 0);
		}
		//System.out.println("degreeHashMap size is : "+degreeHashMap.size());
	}

	public void caculateDegree(String fileString) {
		String[] idStrings = null;
		int tempKey, tempValue;
		idStrings = fileString.split(",");
		tempKey = Integer.parseInt(idStrings[temptag]);
		if (degreeHashMap.containsKey(tempKey)) {
			tempValue = degreeHashMap.get(tempKey) + 1;
			degreeHashMap.put(tempKey, tempValue);
		} else {
			System.out.println(fileString);
		}
	}

	public static void writeDown(String fileName) {
		FileOperate.delFile(fileName);
		StringBuilder sBuilder = new StringBuilder();
		for (Integer obj : degreeHashMap.keySet()) {
			int key = (int) obj;
			int value = degreeHashMap.get(obj);
			sBuilder.append(key);
			sBuilder.append(",");
			sBuilder.append(value);
			sBuilder.append("\r\n");
			if (sBuilder.length() >= 1000000) {
				FileOperate.writeWithFileWrite(fileName, sBuilder.toString());
				sBuilder.setLength(0);
			}
		}
		FileOperate.writeWithFileWrite(fileName, sBuilder.toString());
		sBuilder.setLength(0);
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void GetMaxNumber(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		int max = 0;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//System.out.println("line " + line + ": " + tempString);
//				fileProcessByLines(tempString);
				String[] idStrings = null;
				int temp;
				idStrings = tempString.split("=");
				//System.out.println(idStrings[temptag]);
				temp = Integer.parseInt(idStrings[1]);
				if (temp>max) {
					max = temp;
				}
			}
			reader.close();
			System.out.println(idHashSet.size());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					System.out.println("max is : "+ max);
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void fileOper(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//System.out.println("line " + line + ": " + tempString);
//				fileProcessByLines(tempString);
				String[] idStrings = null;
				int temp;
				idStrings = tempString.split("=");
				//System.out.println(idStrings[temptag]);
				temp = Integer.parseInt(idStrings[1]);
				if (degreeHashMap.containsKey(temp)) {
					int tempValue = degreeHashMap.get(temp);
					degreeHashMap.put(temp, tempValue+1);
				}else {
					degreeHashMap.put(temp, 1);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					writeDown("G:/data/program temp data/degree_coauthor_name.txt");
				} catch (IOException e1) {
				}
			}
		}
	}
	
	public List<Integer> getDegreeData(String fileName) {
		File file = new File(fileName);
//		List<Map<Integer, Integer>> authorList = new ArrayList();
//		Map<Integer, Integer> authorMap = new HashMap<Integer, Integer>();
		List<Integer> authorPaperList = new ArrayList<Integer>();
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				String[] idStrings = null;
				int tempKey,tempValue;
				idStrings = tempString.split(",");
				//System.out.println(idStrings[temptag]);
				tempKey = Integer.parseInt(idStrings[0]);
				tempValue = Integer.parseInt(idStrings[1]);
				authorPaperList.add(tempKey);
				authorPaperList.add(tempValue);
//				authorMap.put(tempKey, tempValue);
//				authorList.add(authorMap);
//				authorMap.clear();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
//		System.out.println("authorList size is : "+authorPaperList.size());
		return authorPaperList;
	}
	

	public List<Double> getLnDegreeData(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 计算对数
	 * @param value
	 * @param base
	 * @return
	 */
	public double log(double value, double base) {
		return Math.log(value) / Math.log(base);
	}
	
	public static void main(String[] args) {
		DegreeDistribution degreeDistribution = new DegreeDistribution();
//		degreeDistribution.caculateDrgreeDistubrition();
//		degreeDistribution.GetMaxNumber("G:/data/program temp data/name_number.txt");
		degreeDistribution.fileOper("G:/data/program temp data/coauthor_name.txt");
	}
}
