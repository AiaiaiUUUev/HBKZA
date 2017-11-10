package com.example.d.healthbook.GlobalVariables;

import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.Models.ListChannel;
import com.example.d.healthbook.Models.Mention;
import com.example.d.healthbook.Models.ResponseGetUserData;

import java.util.Date;
import java.util.List;

/**
 * Created by D on 05.06.2017.
 */

public class GlobalVariables {
    public static String user_auth_token = "";
    public static Integer user_id = 0;

    public static Date clickedDateGlobal = null;
    public static ResponseGetUserData responseGetUserData = null;

    public static List<Document> documentList = null;

    public static List<Document> documentNewsList = null;
    public static List<Document> documentClinicList = null;
    public static List<ListChannel> listChannels = null;
    public static List<Mention> mentions = null;

    public static boolean checkMenuBtn = false;
    public static boolean isClinic=false;


    public static final String titleClinic_key = "titleClinic";
    public static final String cityName_key = "cityName";
    public static final String speciality_key = "speciality";



}
