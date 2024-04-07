package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range{
	private Predicate<Integer> predicate = n -> true;
	protected RangePredicate(int min, int max) {
		super(min, max);
		
	}
	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	public static RangePredicate getRange(int min, int max) {
		checkMinMax(min, max);
		return new RangePredicate(min, max);
	}
	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator();
	}
	private class RangePredicateIterator implements Iterator<Integer> {
		int current = min;
		@Override
		public boolean hasNext() {
			int check = current;
			boolean res = false;
			while(check <= max && !(res=predicate.test(check++)));
			return res;
		}
		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			while(!predicate.test(current++));
			return (current-1);
		}
	}
}