package com.codersworld.jplibs.listeners;

import com.codersworld.jplibs.beans.PlacesBean;
import com.codersworld.jplibs.beans.PlacesTypeBean;

public interface OnPlacesListener {
    void onPlaceCategory(PlacesTypeBean mBeanPlace);
    void onPlace(PlacesBean mBeanPlace,int position);
 }
