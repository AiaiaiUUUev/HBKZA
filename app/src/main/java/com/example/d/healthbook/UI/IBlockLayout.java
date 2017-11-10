package com.example.d.healthbook.UI;

import android.view.View;

public interface IBlockLayout
{
    public IBlockLayout __construct();
    public void adjustToView(View view, Size constraints);
    public View getChildAt(int index);
}
