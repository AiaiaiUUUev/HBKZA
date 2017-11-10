package com.example.d.healthbook.Models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by D on 14.07.2017.
 */

public class MissionName implements Parent<Task> {

    // a recipe contains several ingredients
    private List<Task> taskList;

    public MissionName(String name, List<Task> taskList) {
        taskList = taskList;
    }

    @Override
    public List<Task> getChildList() {
        return taskList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}