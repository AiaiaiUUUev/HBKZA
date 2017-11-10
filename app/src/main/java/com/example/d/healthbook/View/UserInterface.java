package com.example.d.healthbook.View;

import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.Models.ResponseGetUserData;
import com.example.d.healthbook.Models.ResponseMyFamilyMembers;
import com.example.d.healthbook.Models.ResponseProgressUser;

import java.util.List;

/**
 * Created by D on 01.07.2017.
 */

public interface UserInterface {
    ResponseGetUserData getData();
    List<ResponseAllSubscriptionsToDoctor> getDataSub();
    ResponseProgressUser getDataProgress();
    ResponseMyFamilyMembers getDataFamily();
}
