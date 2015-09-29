package com.insa.rocketlyonandroid.view;

import com.insa.rocketlyonandroid.models.Arret;

public interface ArretsView {

    void openMap(Arret arret);

    void openTimetable(Arret arret);

    void openStreetview(Arret arret);
}
