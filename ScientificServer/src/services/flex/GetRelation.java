package services.flex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.flex.roo.addon.asdt.core.internal.antlr.AS3Parser.interfaceDefinition_return;
import org.springframework.flex.roo.addon.asdt.core.internal.antlr.AS3Parser.returnStatement_return;

import services.scientist.relation.Authorpaper;
import services.scientist.relation.AuthorpaperDAO;
import services.scientist.relation.coauthor.Coauth;
import services.scientist.relation.coauthor.CoauthDAO;
import services.scientist.relation.temprelation.Temprelation;
import services.scientist.relation.temprelation.TemprelationDAO;

public class GetRelation implements Runnable {
	private AuthorpaperDAO authorpaperDAO;
	private ApplicationContext context;
	private String path = "applicationContext.xml";
	private static int counter = 0;

	// MessageBrokerHandlerAdapter sd = new MessageBrokerHandlerAdapter();

	// SELECT distinct(R1.aname),R2.aname,R2.pname FROM authorpaper R1,
	// authorpaper R2
	// WHERE R1.pname=R2.pname and R1.aname!=R2.aname and R1.pname = 'Optical
	// bistability in lasers induced by active molecules with a large permanent
	// dipole moment'

	public GetRelation() {

		// context = new ClassPathXmlApplicationContext(path);
	}

	/**
	 * 从原始表中获取数据,然后插入到临时表
	 * 
	 * @param userName
	 * @return
	 */
	public HashMap<String, List<String>> authorList(String userName) {
		context = new ClassPathXmlApplicationContext(path);
		authorpaperDAO = (AuthorpaperDAO) context.getBean("authorpaperDAO");
		// 获取该作者参与的所有paper
		userName = userName.trim();
		HashMap<String, List<String>> authorpaperMap = new HashMap<String, List<String>>();
		List<Authorpaper> authorpaperlist = authorpaperDAO
				.findByAname(userName);
		String tempAuthorName = null;
		String tempPaperName = null;
		for (Authorpaper authorpaper : authorpaperlist) {
			tempPaperName = authorpaper.getPname();
			List<Authorpaper> subAuthorPaperList = authorpaperDAO
					.findByPname(tempPaperName);
			// 获取作者参加所有paper的其他作者
			for (Authorpaper authorpaper2 : subAuthorPaperList) {
				tempAuthorName = authorpaper2.getAname();
				// 数据库里面有一些空字段
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					// System.out.println(tempAuthorName);
					// System.out.println(userName);
					// System.out.println(tempAuthorName.equalsIgnoreCase(userName));
					// 去处相同作者的大小写问题
					if (!tempAuthorName.equalsIgnoreCase(userName)) {
						try {
							HashSet<String> tempPaperSet = new HashSet<String>();
							// author exist in map
							if (authorpaperMap.containsKey(tempAuthorName)) {
								tempPaperSet.addAll(authorpaperMap
										.get(tempAuthorName));
							}
							// not exist just add paper to set, and put set in
							// map
							if (tempPaperSet.add(tempPaperName)) {
								authorpaperMap.put(tempAuthorName,
										new ArrayList<String>(tempPaperSet));
							} else {
								// System.out.println("add paper filed : " +
								// tempPaperName);
							}
							insertIntoTempRelationTable(userName,
									tempAuthorName, tempPaperName);
						} catch (Exception e) {
							System.out.println("add paper filed : "
									+ tempPaperName);
							e.printStackTrace();
						}
					}
				} else {
					continue;
				}
			}
			// System.out.println(authorpaper.getPname());
		}

		authorpaperMap.remove(userName);
		// printMap(authorpaperMap,userName);
		return authorpaperMap;
	}

	/**
	 * 插入数据到临时表里面去
	 * 
	 * @param author1
	 * @param author2
	 * @param paper
	 */
	private void insertIntoTempRelationTable(String author1, String author2,
			String paper) {
		context = new ClassPathXmlApplicationContext(path);
		TemprelationDAO temprelationDAO = new TemprelationDAO();
		temprelationDAO = (TemprelationDAO) context.getBean("TemprelationDAO");
		Temprelation temprelation = new Temprelation();
		temprelation.setAuthor1(author1);
		temprelation.setAuthor2(author2);
		temprelation.setPaper(paper);
		// System.out.println(author1+"--"+author2+"-->"+paper);
		temprelationDAO.save(temprelation);

	}

	/**
	 * 从韦冬的临时表coauthor里面读取数据,如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	private HashMap<String, List<String>> getRelationFromCoauthor(String name) {
		context = new ClassPathXmlApplicationContext(path);
		CoauthDAO coauthorDAO = new CoauthDAO();
		coauthorDAO = (CoauthDAO) context.getBean("CoauthDAO");
		name = name.trim();
		List<Coauth> coAuthorList = coauthorDAO.findByName(name);
		if (coAuthorList.size() > 0) {
			String tempAuthorName = null;
			String tempPaperName = null;
			String tempDoi = null;
			HashMap<String, List<String>> authorpaperMap = new HashMap<String, List<String>>();
			for (Coauth coauthor : coAuthorList) {
				tempAuthorName = coauthor.getCoauth();
				tempPaperName = coauthor.getTitle().trim();
				tempDoi = coauthor.getUrl();
				tempPaperName = tempPaperName+"="+tempDoi;
				// 数据库里面coauthor是很多作者用=来分隔的
				String[] authorStrings = tempAuthorName.split("=");
				// 数据库里面有一些空字段
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					for (String author : authorStrings) {
						author = author.trim();
						if (!author.isEmpty() || author != null) {
							// 去处相同作者的大小写问题
							if (!author.equalsIgnoreCase(name)) {
								HashSet<String> tempPaperSet = new HashSet<String>();
								// System.out.println("author is :"+author);
								try {
									// author exist in map
									if (authorpaperMap.containsKey(author)) {
										tempPaperSet.addAll(authorpaperMap
												.get(author));
									}
									// not exist just add paper to set, and put
									// set in
									// map
									// tempPaperSet.add(tempPaperName);
									// authorpaperMap.put(author, new
									// ArrayList<String>(tempPaperSet));
									if (tempPaperSet.add(tempPaperName)) {
										// System.out.println("author is :"+author);
										authorpaperMap.put(author,
												new ArrayList<String>(
														tempPaperSet));
									} else {
										System.out.println("add paper filed : "
												+ tempPaperName);
									}
									// System.out.println(authorpaperMap.size());
								} catch (Exception e) {
									System.out.println("add paper filed : "
											+ tempPaperName);
									e.printStackTrace();
								}
							}
						}
					}
				} else {
					continue;
				}
				// authorpaperMap.put(temprelation.getAuthor2(), value)
			}
			authorpaperMap.remove(name);
			return authorpaperMap;
		} else {
			return getRelationFromTempTable(name);
		}
	}

	/**
	 * 从临时表里面读取数据,如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	private HashMap<String, List<String>> getRelationFromTempTable(String name) {
		context = new ClassPathXmlApplicationContext(path);
		TemprelationDAO temprelationDAO = new TemprelationDAO();
		temprelationDAO = (TemprelationDAO) context.getBean("TemprelationDAO");
		name = name.trim();
		List<Temprelation> author1List = temprelationDAO.findByAuthor1(name);
		if (author1List.size() > 0) {
			String tempAuthorName = null;
			String tempPaperName = null;
			HashMap<String, List<String>> authorpaperMap = new HashMap<String, List<String>>();
			for (Temprelation temprelation : author1List) {
				tempAuthorName = temprelation.getAuthor2();
				tempPaperName = temprelation.getPaper();
				// 数据库里面有一些空字段
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					// 去处相同作者的大小写问题
					if (!tempAuthorName.equalsIgnoreCase(name)) {
						try {
							HashSet<String> tempPaperSet = new HashSet<String>();
							// author exist in map
							if (authorpaperMap.containsKey(tempAuthorName)) {
								tempPaperSet.addAll(authorpaperMap
										.get(tempAuthorName));
							}
							// not exist just add paper to set, and put set in
							// map
							if (tempPaperSet.add(tempPaperName)) {
								authorpaperMap.put(tempAuthorName,
										new ArrayList<String>(tempPaperSet));
							} else {
								// System.out.println("add paper filed : " +
								// tempPaperName);
							}
						} catch (Exception e) {
							System.out.println("add paper filed : "
									+ tempPaperName);
							e.printStackTrace();
						}
					}
				} else {
					continue;
				}
				// authorpaperMap.put(temprelation.getAuthor2(), value)
			}
			authorpaperMap.remove(name);
			return authorpaperMap;
		} else {
			return authorList(name);
		}
	}

	/**
	 * 六度分离主函数 从韦冬的临时表coauthor里面读取数据,如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	private HashMap<String, List<String>> sixDegreesSeparation(String authorOne) {
		context = new ClassPathXmlApplicationContext(path);
		CoauthDAO coauthorDAO = new CoauthDAO();
		coauthorDAO = (CoauthDAO) context.getBean("CoauthDAO");
		authorOne = authorOne.trim();
		List<Coauth> coAuthorList = coauthorDAO.findByName(authorOne.trim());
		if (coAuthorList.size() > 0) {
			String tempAuthorName = null;
			String tempPaperName = null;
			HashMap<String, List<String>> authorpaperMap = new HashMap<String, List<String>>();
			for (Coauth coauthor : coAuthorList) {
				tempAuthorName = coauthor.getCoauth();
				tempPaperName = coauthor.getTitle().trim();
				// 数据库里面coauthor是很多作者用=来分隔的
				String[] authorStrings = tempAuthorName.split("=");
				// 数据库里面有一些空字段
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					for (String author : authorStrings) {
						author = author.trim();
						if (!author.isEmpty() || author != null) {
							// 去处相同作者的大小写问题
							if (!author.equalsIgnoreCase(authorOne)) {
								HashSet<String> tempPaperSet = new HashSet<String>();
								// System.out.println("author is :"+author);
								try {
									// author exist in map
									if (authorpaperMap.containsKey(author)) {
										tempPaperSet.addAll(authorpaperMap
												.get(author));
									}
									// not exist just add paper to set, and put
									// set in
									// map
									// tempPaperSet.add(tempPaperName);
									// authorpaperMap.put(author, new
									// ArrayList<String>(tempPaperSet));
									if (tempPaperSet.add(tempPaperName)) {
										// System.out.println("author is :"+author);
										authorpaperMap.put(author,
												new ArrayList<String>(
														tempPaperSet));
									} else {
										System.out.println("add paper filed : "
												+ tempPaperName);
									}
									// System.out.println(authorpaperMap.size());
								} catch (Exception e) {
									System.out.println("add paper filed : "
											+ tempPaperName);
									e.printStackTrace();
								}
							}
						}
					}
				} else {
					continue;
				}
				// authorpaperMap.put(temprelation.getAuthor2(), value)
			}
			authorpaperMap.remove(authorOne);
			return authorpaperMap;
		} else {
			return getRelationFromTempTable(authorOne);
		}
	}

	/**
	 * 六度分离主函数 从韦冬的临时表coauthor里面读取数据,如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	private List<String> sixDegreesSeparation(String authorOne, String authorTwo) {
		authorOne = authorOne.trim();
		authorTwo = authorTwo.trim();
		List<String> sixDegreesList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		sixDegreesList = compareList(authorOne, authorTwo);
		if (sixDegreesList.size() > 0) {
			return sixDegreesList;// 直接在相邻的邻居有交集
		} else {
			//存放左边已经遍历过的点，这里有个问题，已经遍历过的点虽然可能不和目标节点直接联系
			//但是却和可能和目标节点的邻居有直接联系，所以并不是说已经遍历过了就不再遍历了
			//而是广度优先的时候每一层可以只遍历一次
			Set<String> leftVisitedAuthorSet = new HashSet<String>();
			//存放右边已经遍历过的点
			Set<String> rightVisitedAuthorSet = new HashSet<String>();
			// 实在抱歉啊，下面的代码逻辑太混乱了
			// 从左边开始找第一轮
			HashMap<String, List<String>> authorOneHashMap = sixDegreesSeparation(authorOne);
			// printMap(authorOneHashMap, "authorOneHashMap");
			if (authorOneHashMap.size() > 0) {
				// 右边开始找第一轮
				Set<String> authorOneFirstKeySet = new HashSet<String>();
				authorOneFirstKeySet = authorOneHashMap.keySet();

				HashMap<String, List<String>> authorTwoHashMap = sixDegreesSeparation(authorTwo);
				Set<String> authorTwoFirstKeySet = new HashSet<String>();
				authorTwoFirstKeySet = authorTwoHashMap.keySet();
				
				Set<String> tempSet = null;
				if (authorTwoHashMap.size() > 0) {
					// 两边各找 一度,然后求交集
					tempSet = authorOneFirstKeySet;
				}
				// 左边开始找第二轮
				else {
					if (counter <= 6) {
						System.out.println("now counter is " + counter);
						// HashMap<String, List<String>> authorTwomHashMap =
						// sixDegreesSeparation(authorTwo);
						// 第二次查找(二度空间)从左边查找
						for (String secondDegreesFromLeftName : authorOneFirstKeySet) {
							HashMap<String, List<String>> secondDegreesTempCoauthorHashMap = sixDegreesSeparation(secondDegreesFromLeftName);
							if (secondDegreesTempCoauthorHashMap.size() > 0) {
								//这里开始把访问过点加入set，避免重复访问
								//leftVisitedAuthorSet.addAll(secondDegreesTempCoauthorHashMap.keySet());
								// 循环调用compareList求交集
								tempList = compareList(
										secondDegreesTempCoauthorHashMap, authorTwo);
								if (tempList.size() > 0) {
									sixDegreesList.add("author:"
											+ secondDegreesFromLeftName);
									sixDegreesList.addAll(authorOneHashMap
											.get(secondDegreesFromLeftName));
									sixDegreesList.addAll(tempList);

									System.out.println("second degree is :"
											+ counter);
								} else {//没有交集
									// 左边第二度,右边第一度,然后求交集
									//Set<String> tempSet = secondDegreesTempCoauthorHashMap.keySet();
									tempSet.addAll(secondDegreesTempCoauthorHashMap.keySet());
								}
							}
							// sixDegreesSeparation(subNameString, authorTwo);
							// System.out.println(subNameString);
						}
						Set<String> authorTwoKeySet = new HashSet<String>();
						authorTwoKeySet = authorTwoHashMap.keySet();

						// //第二次查找(二度空间)从右边查找
						// for (String secondDegreesFromRightName :
						// authorTwoKeySet)
						// {
						// if
						// (sixDegreesSeparation(secondDegreesFromRightName).containsKey(authorTwo))
						// {
						// System.out.println("second degree is :"+counter);
						// return null;
						// }
						// //sixDegreesSeparation(subNameString, authorTwo);
						// //System.out.println(subNameString);
						// }
						//					
						// //第三次查找(三度空间)
						// //这里查找规模就比较大了,还是两边各找两度好了..
						// for (String subNameString : authorOneFirstKeySet) {
						// for (String thirdDegreesName :
						// sixDegreesSeparation(subNameString).keySet()) {
						// if
						// (sixDegreesSeparation(thirdDegreesName).containsKey(authorTwo))
						// {
						// System.out.println("third degrees :"+counter);
						// return null;
						// }
						// }
						// }

					} else {
						System.err.println("~~~~can't counter is :" + counter);
						return null;
					}

					// //Collections.disjoint(authorOnemHashMap.keySet()
				}
			} else {
				return null;
			}
		}
		return null;
	}

	private List<String> compareList(String authorOne, String authorTwo) {

		authorOne = authorOne.trim();
		authorTwo = authorTwo.trim();
		if (authorOne.equalsIgnoreCase(authorTwo)) {
			System.out.println("same one");
			return null;
		}
		// 从左边开始找第一轮
		HashMap<String, List<String>> authorOneHashMap = sixDegreesSeparation(authorOne);
		return compareList(authorOneHashMap,authorTwo);
	}
	
	private List<String> compareList(HashMap<String, List<String>> leftMap, String authorTwo) {

		authorTwo = authorTwo.trim();
		List<String> sixDegreesList = new ArrayList<String>();
		// 从左边开始找第一轮
		//HashMap<String, List<String>> authorOneHashMap = sixDegreesSeparation(authorOne);
		// printMap(authorOneHashMap, "authorOneHashMap");
		if (leftMap.size() > 0) {
			// 直接有合作的话,直接输出 一度空间...
			if (leftMap.containsKey(authorTwo)) {
				System.out.println("got it from left " + authorTwo);

				sixDegreesList.add("author:" + authorTwo);
				sixDegreesList.addAll(leftMap.get(authorTwo));
				return sixDegreesList;
			}
			// 右边开始找第一轮
			Set<String> authorOneFirstKeySet = new HashSet<String>();
			authorOneFirstKeySet = leftMap.keySet();

			HashMap<String, List<String>> authorTwoHashMap = sixDegreesSeparation(authorTwo);
			Set<String> authorTwoFirstKeySet = new HashSet<String>();
			authorTwoFirstKeySet = authorTwoHashMap.keySet();
			if (authorTwoHashMap.size() > 0) {
				// 两边各找 一度,然后求交集
				Set<String> tempSet = authorOneFirstKeySet;
				tempSet.retainAll(authorTwoFirstKeySet);
				if (tempSet.size() > 0) {
					System.out.println("got it from right" + authorTwo);
					// HashMap<String, List<String>> tempCoauthorHashMap = new
					// HashMap<String, List<String>>();
					//这里偷懒了，本来应该去全部共同用户比较一下那个合作文章多选哪个，
					//但是现在只是去交集里面的第一个用户
					for (String string : tempSet) {
						// tempCoauthorHashMap.put(string,authorOneHashMap.get(string));
						// tempCoauthorHashMap.put(authorTwo,authorTwoHashMap.get(string));

						sixDegreesList.add("author:" + string);
						sixDegreesList.addAll(leftMap.get(string));

						sixDegreesList.add("author:" + authorTwo);
						sixDegreesList.addAll(authorTwoHashMap.get(string));

						return sixDegreesList;
					}
				}
			}
		}
		return null;
	}
	
	private List<String> compareList(String authorOne, HashMap<String, List<String>> rightMap) {

		return compareList(rightMap, authorOne);
	}

	private Set<String> getNextDegreeAuthorSet(Set<String> preSet){
		Set<String> tempSet = null;
		return null;
	}
	
	public static List<String> union(List<String> one, List<String> two) {
		Set<String> t1 = new TreeSet<String>();
		t1.addAll(one);
		Set<String> t2 = new TreeSet<String>();
		t2.addAll(two);
		List<String> result = new ArrayList<String>();
		for (String s : t1.size() < t2.size() ? t1 : t2) {
			if (t1.size() < t2.size() ? t2.contains(s) : t1.contains(s)) {
				result.add(s);
			}
		}
		Collections.disjoint(one, two);
		// one.retainAll(two);
		return result;
	}

	public static List<String> union(List<List<String>> list) {
		List<String> result = list.get(0);
		if (list.size() == 0)
			return result;
		for (int i = 1; i < list.size(); i++) {
			result = union(result, list.get(i));
		}
		return result;
	}

	public static void printMap(HashMap<String, List<String>> map, String name) {
		for (String element : map.keySet()) {
			// System.err.println(map.get(element).size());
			for (String paper : map.get(element)) {
				System.out.println(name + "--" + element + "-->" + paper);
			}
		}
	}

	public static void printList(List<String> list) {
		for (String element : list) {
			System.out.println("-->" + element);
		}
	}

	/**
	 * 把搜索的map结果转化成元素relation的list
	 * 
	 * @param rMap
	 * @return
	 */
	public List<Relations> transferRelations(HashMap<String, List<String>> rMap) {
		List<Relations> rlList = new ArrayList<Relations>();
		for (String name : rMap.keySet()) {
			Relations relations = new Relations();
			relations.setArthorString(name);
			relations.setPaperList(rMap.get(name));
			rlList.add(relations);
		}
		//printMap(rMap, "Yi-Cheng Zhang");
		return rlList;
	}

	public static void main(String[] args) {
		GetRelation getRealation = new GetRelation();
		// OscarG.Calderón Lai Chin Lu
		// getRealation.createReations("Yi-Cheng Zhang");
		List<String> list = null;
		// sixDegreesSeparation
		list = getRealation.compareList("Yi-Cheng Zhang", "Tao Zhou");
		printList(list);
	}

	public AuthorpaperDAO getAuthorpaperDAO() {
		return authorpaperDAO;
	}

	public void setAuthorpaperDAO(AuthorpaperDAO authorpaperDAO) {
		this.authorpaperDAO = authorpaperDAO;
	}

	public List<Relations> createReations(String name) {
		HashMap<String, List<String>> authorpaperMap = getRelationFromCoauthor(name);
		return transferRelations(authorpaperMap);
	}

	public void run() {

	}
}
