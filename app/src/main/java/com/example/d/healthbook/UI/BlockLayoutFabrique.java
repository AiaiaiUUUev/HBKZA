package com.example.d.healthbook.UI;

import android.content.Context;
import android.support.annotation.Nullable;


import com.example.d.healthbook.Appi.App;

import java.lang.reflect.InvocationTargetException;

public final class BlockLayoutFabrique
{

    @Nullable
    public static IBlockLayout getBlock(Class cls)
    {
        try
        {
            return ((IBlockLayout) cls.getDeclaredConstructor(Context.class).newInstance(App.getInstance().getCurrentContext())).__construct();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
