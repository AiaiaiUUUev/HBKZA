package com.example.d.healthbook.Models;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by D on 14.07.2017.
 */

public class ModelWhithTasks extends ExpandableGroup<Task> {
    List<Task> tasks;

    public ModelWhithTasks(List<Task> tasks,  String nameMission ) {
        super(nameMission,tasks);
        this.tasks = tasks;
        this.nameMission = nameMission;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getNameMission() {
        return nameMission;
    }

    public void setNameMission(String nameMission) {
        this.nameMission = nameMission;
    }

    String nameMission;


}
