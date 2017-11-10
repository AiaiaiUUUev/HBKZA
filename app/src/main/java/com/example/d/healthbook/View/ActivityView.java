package com.example.d.healthbook.View;

import android.view.Menu;

/**
 * Created by D on 31.05.2017.
 */

public interface ActivityView {
    void setActivityTitle(String titlename);
    void setActivityToolbarIcon(int menu_id);
    void changeMenuItems(int type);
    void changeImageVisibility (int type);
//    Menu getMenu();
}
