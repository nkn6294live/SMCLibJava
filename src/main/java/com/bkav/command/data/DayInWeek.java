package com.bkav.command.data;

public class DayInWeek extends CommonData {

	public static DayInWeek createFromStringArray(String[] datas) {
		return new DayInWeek(getSimpleName(datas));
	}

	public DayInWeek(String name) {
		super(name);
	}
}
