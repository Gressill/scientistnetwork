package distribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import services.flex.Relations;
import services.scientist.relation.AuthorpaperDAO;
import services.scientist.relation.coauthor.Coauth;
import services.scientist.relation.coauthor.CoauthDAO;

public class DistributionTools {

	private AuthorpaperDAO authorpaperDAO;
	private ApplicationContext context;
	private String path = "applicationContext.xml";
	private static int counter = 0;
	// 储存下一度的作者
	private HashSet<String> nextDegreeAuthorSet = new HashSet<String>();

	private HashSet<String> leftSet = new HashSet<String>();
	private HashSet<String> rightSet = new HashSet<String>();

	public DistributionTools() {
		context = new ClassPathXmlApplicationContext(path);
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	/**
	 * 六度分离主函数 从韦冬的临时表coauthor里面读取数据,如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	public HashMap<String, List<String>> getSixDegreesSeparation(
			String authorOne) {
//		context = new ClassPathXmlApplicationContext(path);
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
			// return getRelationFromTempTable(authorOne);
			return null;
		}
	}

	/**
	 * 六度分离主函数 从韦冬的临时表coauthor里面读取数据, 这个函数只读取作者!!!!!!!!!!!!!!!!!!!!!!!
	 * 如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	public HashSet<String> getAuthorSet(String authorOne) {
//		context = new ClassPathXmlApplicationContext(path);
		CoauthDAO coauthorDAO = new CoauthDAO();
		coauthorDAO = (CoauthDAO) context.getBean("CoauthDAO");
		authorOne = authorOne.trim();
		List<Coauth> coAuthorList = coauthorDAO.findByName(authorOne.trim());
		// System.out.println("authorOne is "+ coAuthorList.size());
		if (coAuthorList.size() > 0) {
			String tempAuthorName = null;
			HashMap<String, List<String>> authorpaperMap = new HashMap<String, List<String>>();
			HashSet<String> tempAuthorSet = new HashSet<String>();
			for (Coauth coauthor : coAuthorList) {
				tempAuthorName = coauthor.getCoauth();
				// 数据库里面coauthor是很多作者用=来分隔的
				String[] authorStrings = tempAuthorName.split("=");
				// 数据库里面有一些空字段
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					for (String author : authorStrings) {
						author = author.trim();
						if (!author.isEmpty() || author != null) {
							// 去处相同作者的大小写问题
							if (!author.equalsIgnoreCase(authorOne)) {
								// System.out.println("author is :"+author);
								try {
									tempAuthorSet.add(author);
								} catch (Exception e) {
									System.out.println("add author filed : "
											+ author);
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
			tempAuthorSet.remove(authorOne);
			return tempAuthorSet;
		} else {
			// return getRelationFromTempTable(authorOne);
			return null;
		}
	}
	
	/**
	 * 六度分离主函数 从韦冬的临时表coauthor里面读取数据, 这个函数只读取作者!!!!!!!!!!!!!!!!!!!!!!!
	 * 如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	public int getAuthorSetSize(String authorOne) {
//		context = new ClassPathXmlApplicationContext(path);
		CoauthDAO coauthorDAO = new CoauthDAO();
		coauthorDAO = (CoauthDAO) context.getBean("CoauthDAO");
		authorOne = authorOne.trim();
		List<Coauth> coAuthorList = coauthorDAO.findByName(authorOne.trim());
		// System.out.println("authorOne is "+ coAuthorList.size());
		if (coAuthorList.size() > 0) {
			String tempAuthorName = null;
			HashSet<String> tempAuthorSet = new HashSet<String>();
			for (Coauth coauthor : coAuthorList) {
				tempAuthorName = coauthor.getCoauth();
				// 数据库里面coauthor是很多作者用=来分隔的
				String[] authorStrings = tempAuthorName.split("=");
				// 数据库里面有一些空字段
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					for (String author : authorStrings) {
						author = author.trim();
						if (!author.isEmpty() || author != null) {
							// 去处相同作者的大小写问题
							if (!author.equalsIgnoreCase(authorOne)) {
								// System.out.println("author is :"+author);
								try {
									tempAuthorSet.add(author);
								} catch (Exception e) {
									System.out.println("add author filed : "
											+ author);
									e.printStackTrace();
								}
							}
						}
					}
				} else {
					continue;
				}
			}
			tempAuthorSet.remove(authorOne);
			return tempAuthorSet.size();
		} else {
			// return getRelationFromTempTable(authorOne);
			return 0;
		}
	}

	/**
	 * 六度分离主函数 从韦冬的临时表coauthor里面读取数据, 这个函数只取authorOne到目标authorTwo一个人的paper
	 * 如果没有,就去原始表里面找
	 * 
	 * @param name
	 * @return
	 */
	public ArrayList<String> getAuthorList(String authorOne, String authorTwo,
			boolean flag) {
		// authorOne = authorOne.trim();
		// authorTwo = authorTwo.trim();
//		context = new ClassPathXmlApplicationContext(path);
		CoauthDAO coauthorDAO = new CoauthDAO();
		coauthorDAO = (CoauthDAO) context.getBean("CoauthDAO");
		List<Coauth> coAuthorList = coauthorDAO.findByName(authorOne);
		// System.out.println("authorOne is "+ coAuthorList.size());
		ArrayList<String> tempList = new ArrayList<String>();
		if (coAuthorList.size() > 0) {
			String tempAuthorName = null;
			String tempPaperName = null;
			HashMap<String, List<String>> authorpaperMap = new HashMap<String, List<String>>();
			HashSet<String> tempAuthorSet = new HashSet<String>();
			for (Coauth coauthor : coAuthorList) {
				tempAuthorName = coauthor.getCoauth();
				tempPaperName = coauthor.getTitle().trim();
				// 数据库里面coauthor是很多作者用=来分隔的
				String[] authorStrings = tempAuthorName.split("=");
				// 数据库里面有一些空字段
				// System.out.println("author is "+tempAuthorName);
				if (!tempAuthorName.isEmpty() || tempAuthorName != null) {
					for (String author : authorStrings) {
						author = author.trim();
						if (!author.isEmpty() || author != null) {
							// 去处相同作者的大小写问题
							if (!author.equalsIgnoreCase(authorOne)) {
								// System.out.println("author is :"+author+" @@@@one is: "+authorOne+" ****Two is:"+authorTwo);
								if (author.equalsIgnoreCase(authorTwo)) {
									tempList.add(tempPaperName);
									if (flag) {
										tempList.add("author:" + authorTwo);
									} else {
										tempList.add("author:" + authorOne);
									}
									return tempList;
								}
							}
						}
					}
				} else {
					continue;
				}
				// authorpaperMap.put(temprelation.getAuthor2(), value)
			}
			// return null;
		} else {
			// return getRelationFromTempTable(authorOne);
			// return null;
		}
		return null;
	}

	/**
	 * 计算关系云分布
	 */
	public List<Set<String>> getDistantsCloud(String authorOne) {
		List<Set<String>> authorList = new ArrayList<Set<String>>();
		Set<String> historySet = new HashSet<String>();
		Set<String> firstDegreeSet = new HashSet<String>();
		firstDegreeSet = getAuthorSet(authorOne);
		if (firstDegreeSet!=null) {
			historySet.addAll(firstDegreeSet);
			Set<String> secondDegreeSet = new HashSet<String>();
			for (String string : firstDegreeSet) {
				// System.out.println(string);
				if (getAuthorSet(string) != null) {
					Set<String> tempSet = new HashSet<String>();
					tempSet.addAll(getAuthorSet(string));
					//historySet.addAll(tempSet);
					secondDegreeSet.addAll(tempSet);
				}
			}
			secondDegreeSet.removeAll(firstDegreeSet);
			historySet.addAll(firstDegreeSet);
			Set<String> thridDegreeSet = new HashSet<String>();
			for (String string : secondDegreeSet) {
//				if (!historySet.contains(string)) {
					if (getAuthorSet(string) != null) {
						Set<String> tempSet = new HashSet<String>();
						tempSet.addAll(getAuthorSet(string));
						historySet.addAll(tempSet);
						thridDegreeSet.addAll(tempSet);
					}
//				}
			}
			thridDegreeSet.remove(firstDegreeSet);
			thridDegreeSet.remove(secondDegreeSet);

			Set<String> fourthDegreeSet = new HashSet<String>();
			for (String string : thridDegreeSet) {
//				if (!historySet.contains(string)) {
					if (getAuthorSet(string) != null) {
						Set<String> tempSet = new HashSet<String>();
						tempSet.addAll(getAuthorSet(string));
						historySet.addAll(tempSet);
						fourthDegreeSet.addAll(tempSet);
					}
//				}
			}
			thridDegreeSet.remove(firstDegreeSet);
			thridDegreeSet.remove(secondDegreeSet);
			thridDegreeSet.remove(thridDegreeSet);

			authorList.add(firstDegreeSet);
			authorList.add(secondDegreeSet);
			System.out.println("size is : " + secondDegreeSet.size());
			authorList.add(thridDegreeSet);
			System.out.println("size is : " + thridDegreeSet.size());
			authorList.add(fourthDegreeSet);
			System.out.println("size is : " + fourthDegreeSet.size());
//			printList(authorList);
			return authorList;
		}else {
			return null;
		}
	}

	/**
	 * store unvisited point
	 * 
	 * @param unVisitedAuthorList
	 * @param historyAuthorList
	 * @return
	 */
	public List<String> getList(List<String> unVisitedAuthorList,
			List<String> historyAuthorList) {
		List<String> authorList = new ArrayList<String>();
		Set<String> secondDegreeSet = new HashSet<String>();
		for (String string : unVisitedAuthorList) {
			if (!historyAuthorList.contains(string)) {
				Set<String> tempSet = new HashSet<String>();
				tempSet.addAll(getAuthorSet(string));
				historyAuthorList.addAll(tempSet);
				secondDegreeSet.addAll(tempSet);
			}
		}
		secondDegreeSet.removeAll(historyAuthorList);
		return authorList;
	}

	/**
	 * 六度分离主函数 <br>
	 * 从韦冬的临时表coauthor里面读取数据,如果没有,就去原始表里面找 <br>
	 * 已经遍历过的点虽然可能不和目标节点直接联系 但是却和可能和目标节点的邻居有直接联系， 所以并不是说已经遍历过了就不再遍历了
	 * 而是广度优先的时候每一层可以只遍历一次 0 1 2 3 3 2 1 0这个就是六度，注释用(0,0)(0,1)...(3,2)(3,3)表示关联
	 * 
	 * @param name
	 * @return
	 */
	public List<String> sixDegreesSeparation(String authorOne, String authorTwo) {
		System.out.println("from " + authorOne + " to " + authorTwo);
		authorOne = authorOne.trim();
		authorTwo = authorTwo.trim();
		List<String> sixDegreesList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();

		// (0,0),(0,1)(1,0)(1,1)
		tempList = getConnection(authorOne, authorTwo);
		if (tempList != null) {
			return tempList;// 直接在相邻的邻居有交集
			// sixDegreesList.addAll(getConnection(authorOne, authorTwo));
		} else {
			counter = 3;
			// 存放左边已经遍历过的点
			HashSet<String> leftSecondDegreeVisitedAuthorSet = new HashSet<String>();
			// 存放右边已经遍历过的点
			Set<String> rightVisitedAuthorSet = new HashSet<String>();
			// 实在抱歉啊，以后修改代码的兄弟啊，下面的代码逻辑太混乱了，自己都看不下去了，时间紧，任务重
			// 从左边开始找第一轮
			HashSet<String> authorOneFirstKeySet = getAuthorSet(authorOne);
			// printMap(authorOneHashMap, "authorOneHashMap");
			if (authorOneFirstKeySet != null) {
				// 右边开始找第一轮
				Set<String> authorTwoFirstKeySet = getAuthorSet(authorTwo);

				Set<String> tempSet = null;
				if (authorTwoFirstKeySet != null) {
					tempSet = authorOneFirstKeySet;
					if (counter <= 6) {
						// System.out.println("now counter is " + counter);
						// 第二次查找(二度空间)从左边查找
						for (String secondDegreesFromLeftName : authorOneFirstKeySet) {
							HashSet<String> secondDegreesTempCoauthorSet = getAuthorSet(secondDegreesFromLeftName);
							if (secondDegreesTempCoauthorSet != null) {
								// 这里开始把访问过点加入set，避免重复访问
								leftSecondDegreeVisitedAuthorSet
										.addAll(secondDegreesTempCoauthorSet);
								// 循环调用compareList求交集
								// (2,0)(2,1)
								tempList = getConnection(
										secondDegreesTempCoauthorSet, authorTwo);
								if (tempList != null) {
									sixDegreesList
											.add(secondDegreesFromLeftName);
									sixDegreesList.addAll(tempList);

									System.out.println("second degree is :"
											+ counter);
									return sixDegreesList;
								} else {// 没有交集
									// 左边第二度,右边第一度,然后求交集
									// (0 1 2 3--2,1,0)
									HashSet<String> tempLestHashSet = this.leftSet;
									HashSet<String> tempRightHashSet = this.rightSet;
									tempList = getConnectionFromSet(
											tempLestHashSet, tempRightHashSet);
									if (tempList != null) {
										sixDegreesList.addAll(tempList);
										return sixDegreesList;
									}
								}
							}
						}
						// 左边开始查找第三度
						// HashSet<String> leftThirdDegreeVisitedAuthorSet = new
						// HashSet<String>();
						// (3,0)(3,1)
						tempList = getConnectionFromSet(
								leftSecondDegreeVisitedAuthorSet, authorTwo);
						if (tempList != null) {
							sixDegreesList.addAll(tempList);
							return sixDegreesList;
						} else {// 没有交集
							// 左边第四度,右边第一度,然后求交集
							// (0,1,2,3--3,2,1,0)
							// HashSet<String> tempRight2HashSet =
							// (HashSet<String>) this.rightSet.clone();
							// tempList = getConnectionFromSet(
							// leftSecondDegreeVisitedAuthorSet,
							// tempRight2HashSet);
							// if (tempList != null) {
							// sixDegreesList.addAll(tempList);
							// return sixDegreesList;
							// }
						}
						counter = 4;

					} else {
						System.err.println("~~~~can't find counter is :"
								+ counter);
						return null;
					}
					// Collections.disjoint(authorOnemHashMap.keySet()
				} else {
					// 右边这个人没有合作者
					System.out.println("右边这个人" + authorTwo + "没有合作者");
					return null;
				}
			} else {
				// 左边这个人没有邻居直接返回null
				System.out.println("左边这个人" + authorOne + "没有邻居直接返回null");
				return null;
			}
		}
		System.out.println("counter is" + counter);
		return null;
	}

	/**
	 * 取出左边set里面每个作者和右边比较 ，看是否有相同邻居 调用了getConnection函数
	 * 
	 * @param tempLeftSet
	 * @param authorTwo
	 * @return
	 */
	public List<String> getConnectionFromSet(HashSet<String> tempLeftSet,
			String authorTwo) {
		List<String> tempList = null;
		for (String string : tempLeftSet) {
			HashSet<String> TempCoauthorSet = getAuthorSet(authorTwo);
			// 这里开始把访问过点加入set，避免重复访问
			this.rightSet.addAll(TempCoauthorSet);
			if (TempCoauthorSet.size() > 0) {
				// leftSecondDegreeVisitedAuthorSet.addAll(secondDegreesTempCoauthorSet);
				// 循环调用compareList求交集
				if (getConnection(string, authorTwo) != null) {
					tempList = getConnection(string, authorTwo);
					List<String> sixDegreesList = new ArrayList<String>();
					sixDegreesList.add(string);
					sixDegreesList.addAll(tempList);
					clearLeftRightSet();
					return sixDegreesList;
				}
			}
		}
		// 加入左边临时表
		this.leftSet.addAll(tempLeftSet);
		return null;
	}

	/**
	 * 取出左边set里面全部作者和右边set里面全部作者比较 调用了getConnection函数
	 * 
	 * @param tempLeftSet
	 * @param tempRightSet
	 * @return
	 */
	public List<String> getConnectionFromSet(HashSet<String> tempLeftSet,
			HashSet<String> tempRightSet) {
		List<String> tempList = null;
		HashSet<String> TempCoauthorSet = null;
		for (String rightAuthor : tempRightSet) {
			TempCoauthorSet = getAuthorSet(rightAuthor);
			if (TempCoauthorSet != null) {
				// 这里开始把访问过点加入set，避免重复访问
				// 循环调用compareList求交集
				tempList = getConnectionFromSet(tempLeftSet, rightAuthor);
				if (tempList != null) {
					List<String> sixDegreesList = new ArrayList<String>();
					sixDegreesList.addAll(tempList);

					return sixDegreesList;
				}
			}

		}
		return null;
	}

	/**
	 * 找到两个人之间是否有相同的邻居
	 * 
	 * @param authorOne
	 * @param authorTwo
	 * @return
	 */
	public List<String> getConnection(String authorOne, String authorTwo) {

		authorOne = authorOne.trim();
		authorTwo = authorTwo.trim();
		if (authorOne.equalsIgnoreCase(authorTwo)) {
			System.out.println("same one");
			return null;
		}
		// 从左边开始找第一轮
		HashSet<String> authorOneSet = getAuthorSet(authorOne);
		return getConnection(authorOneSet, authorTwo);
	}

	/**
	 * 找出右边的人和他的邻居是否和和左边的集合有交集
	 * 
	 * @param tempLeftSet
	 * @param authorTwo
	 * @return
	 */
	public List<String> getConnection(HashSet<String> tempLeftSet,
			String authorTwo) {
		authorTwo = authorTwo.trim();
		List<String> sixDegreesList = new ArrayList<String>();
		// 从左边开始找第一轮
		if (tempLeftSet != null) {
			// 直接有合作的话,直接输出 一度空间...
			if (tempLeftSet.contains(authorTwo)) {
				System.out.println("got it from left " + authorTwo);
				sixDegreesList.add(authorTwo);
				clearLeftRightSet();
				printList(sixDegreesList);
				return sixDegreesList;
			}
			// 右边开始找第一轮
			HashSet<String> authorTwoSet = new HashSet<String>();
			authorTwoSet = getAuthorSet(authorTwo);
			if (authorTwoSet != null) {
				// 两边各找 一度,然后求交集
				Set<String> tempSet = tempLeftSet;
				tempSet.retainAll(authorTwoSet);
				if (tempSet.size() > 0) {
					System.out.println("got it from right : " + authorTwo);
					// 这里偷懒了，本来应该去全部共同用户比较一下那个合作文章多选哪个，
					// 但是现在只是去交集里面的第一个用户
					for (String string : tempSet) {
						sixDegreesList.add(string);
						sixDegreesList.add(authorTwo);
						clearLeftRightSet();
						return sixDegreesList;
					}
				}
				this.rightSet.addAll(authorTwoSet);
			}
			this.leftSet.addAll(tempLeftSet);
		}
		return null;
	}

	/**
	 * 找出左边的人和他的邻居是否和和右边的集合有交集
	 * 
	 * @param authorOne
	 * @param tempRightSet
	 * @return
	 */
	public List<String> getConnection(String authorOne,
			HashSet<String> tempRightSet) {
		return getConnection(tempRightSet, authorOne);
	}

	/**
	 * 层次遍历的时候获取下一层的节点
	 * 
	 * @param preSet
	 * @return
	 */
	public Set<String> getNextDegreeAuthorSet(Set<String> preSet) {
		Set<String> tempSet = null;
		for (String string : preSet) {
			tempSet.addAll(getAuthorSet(string));
		}
		return tempSet;
	}

	/**
	 * hahaha
	 * 
	 * @param sixSet
	 * @param authorNext
	 * @return
	 */
	public ArrayList<String> buildSixList(List<String> sixList,
			String authorPre) {
		ArrayList<String> tempList = new ArrayList<String>();
		for (String string : sixList) {
			System.out.println("line 497 is :" + string);
			if (getAuthorList(authorPre, string, true) != null) {
				tempList.addAll(getAuthorList(authorPre, string, true));
				authorPre = string;
			} else if (getAuthorList(authorPre, string, false) != null) {
				tempList.addAll(getAuthorList(string, authorPre, false));
				authorPre = string;
			}
		}
		return tempList;
	}

	/**
	 * 求两个list的交集
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
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

	// public static void printMap(HashMap<String, List<String>> map, String
	// name) {
	// for (String element : map.keySet()) {
	// // System.err.println(map.get(element).size());
	// for (String paper : map.get(element)) {
	// System.out.println(name + "--" + element + "-->" + paper);
	// }
	// }
	// }

	public static void printList(List list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof List) {
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					System.out.println("-->" + string);
				}
			} else {
				System.out.println("-->" + list.get(i));
			}
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
		// printMap(rMap, "Yi-Cheng Zhang");
		return rlList;
	}

	/**
	 * 存在问题，大小写问题，六度搜索时候输入的用户名大小写不对可能会导致结果异常 原因是数据路里面就大小写有问题，比如chuang Li和Chuang
	 * Liu数据库里面就是不同的记录
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DistributionTools distributionTools = new DistributionTools();
		// OscarG.Calderón Lai Chin Lu Gang Yan Albert-LászlóBarabási
		// Albert-László Barabási Matus Medo wei-kang yuan
		// getRealation.createReations("Yi-Cheng Zhang");
		List<String> list = null;
		// sixDegreesSeparation
		list = distributionTools.sixDegreesSeparation("Jie Ren",
				"Albert-László Barabási");
		System.out.println("our coauthor list is: " + list.size());
		if (list != null && list.size() > 0) {
			list = distributionTools.buildSixList(list, "Jie Ren");
			System.out.println("our coauthor list is: " + list.size());
			printList(list);
		} else {
			System.err.println("no item to find");
		}
	}

	public AuthorpaperDAO getAuthorpaperDAO() {
		return authorpaperDAO;
	}

	public void setAuthorpaperDAO(AuthorpaperDAO authorpaperDAO) {
		this.authorpaperDAO = authorpaperDAO;
	}

	// @Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public List<String> createsixDegrees(String authorOne, String authorTwo) {
		List<String> sixList = new ArrayList<String>();
		if (sixDegreesSeparation(authorOne, authorTwo) != null) {
			sixList = sixDegreesSeparation(authorOne, authorTwo);
			sixList = buildSixList(sixList, authorOne);
		} else {
			// sixList.add("Self-organized model of cascade spreading");
			// sixList.add("author:Matus Medo");
			// sixList.add("Model incorporating deposition, diffusion");
			// sixList.add("and aggregation in submonolayer nanostructures");
			// sixList.add("Market Model with Heterogeneous Buyers");
			// sixList.add("author:Yi-Cheng Zhang");
			// sixList.add("Discretized Diffusion Processes");
			// sixList.add("author:Guido Caldarelli");
			// sixList.add("Loops structure of the Internet at the Autonomous System Level");
			// sixList.add("author:Ginestra Bianconi");
			// sixList.add("Bose-Einstein Condensation in Complex Networks");
			// sixList.add("author:Albert-László Barabási");
			// sixList.add("Halting viruses in scale-free networks");
			// sixList.add("author:Zoltán Dezs?");
			// sixList.add("Analysis of Kelly-optimal portfolios");
			// sixList.add("author:" + authorTwo);
			// sixList.clear();
		}
		printList(sixList);
		return sixList;
	}

	public void clearLeftRightSet() {
		this.leftSet.clear();
		this.rightSet.clear();
	}
}
