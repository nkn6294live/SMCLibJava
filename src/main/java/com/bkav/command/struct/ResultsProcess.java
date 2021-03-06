package com.bkav.command.struct;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bkav.command.util.Utils;
/***
 * Wrapper String array input with value.
 */
public class ResultsProcess implements Iterable<Object> {

	@SafeVarargs
	public ResultsProcess(String[] remains, Object... values) {
		this.values = Arrays.stream(values).collect(Collectors.toList());
		this.stringsMark = new ListStringWithMask(remains);
	}

	public ResultsProcess(String[] remains, Collection<Object> values) {
		this.values = values.stream().collect(Collectors.toList());
		this.stringsMark = new ListStringWithMask(remains);
	}
	
	public int size() {
		return this.values.size();
	}
	
	public boolean isEmpty() {
		return this.values.isEmpty();
	}
	
	public void addValue(Object value) {
		this.values.add(value);
	}
	public void addValues(Collection<? extends Object> values) {
		values.stream().filter(Utils::isNotNull).forEach(this.values::add);
	}
	public void removeValue(Object value) {
		this.values.remove(value);
	}

	public void clear() {
		this.values.clear();
	}

	public String[] remains() {
		return this.stringsMark.unMarkString();
	}
	
	public String[] strings() {
		return this.stringsMark.datas();
	}
	
	public Stream<Object> stream() {
		return this.values.stream();
	}

	public ListStringWithMask stringsMark() {
		return this.stringsMark;
	}
	
	@Override
	public Iterator<Object> iterator() {
		return this.values.iterator();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		if (this.values.size() > 0) {
			Object value = this.values.get(0);
			builder.append(String.format("%s(%s)", value.toString(), value.getClass().getSimpleName()));
		}
		for (int index = 1; index < this.values.size(); index++) {
			Object value = this.values.get(index);
			builder.append(String.format(", %s(%s)", value.toString(), value.getClass().getSimpleName()));
		}
		builder.append("]");
		return String.format("%s [values=%s, stringsMark=%s]", this.getClass().getSimpleName(), builder.toString(), stringsMark);
	}

	private List<Object> values;
	private ListStringWithMask stringsMark;
}
