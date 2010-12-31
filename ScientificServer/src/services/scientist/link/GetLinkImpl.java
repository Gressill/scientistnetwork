package services.scientist.link;

import java.util.ArrayList;
import java.util.List;

import blah.MyEntity;

public class GetLinkImpl implements GetLink {

	//@Override
	public List<MyEntity> getLink() {
		List<MyEntity> list = new ArrayList<MyEntity>();
		MyEntity entity = new MyEntity();
		entity.setFirstName("Hello");
		//entity.setLastName("World");
		entity.setPaper("hellmail");
		list.add(entity);
		MyEntity entity2 = new MyEntity();
		entity2.setFirstName("hi");
		//entity2.setLastName("Space");
		entity2.setPaper("ace.com");
		list.add(entity2);
		MyEntity entity3 = new MyEntity();
		entity3.setFirstName("keliwa");
		//entity3.setLastName("Neighbor");
		entity3.setPaper("daqiaobm");
		list.add(entity3);
		System.out.println("list size is: "+list.size());
		return list;
	}

}
