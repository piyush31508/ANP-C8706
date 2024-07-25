package July24;

import java.util.Comparator;

public class IdComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		
		Emp e1 = (Emp)o1;
		Emp e2 = (Emp)o2;
		
		if(e1.EmpId == e2.EmpId)
		return 0;
		else if(e1.EmpId > e2.EmpId) {
			return 1;
		}
		else
			return -1;
			
		}
	}

