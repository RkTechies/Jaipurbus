package jaipurbus.jaipur.tourism.jaipurbus.ui.home.extension

import androidx.fragment.app.Fragment
import jaipurbus.jaipur.tourism.jaipurbus.R
 import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentFindBus
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentOtherPlaces
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentRoutes
import jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments.FragmentSettings


enum class JBNavigationPosition(val position: Int, val id: Int) {
    SEARCHBUS(0, R.id.navSearchBus),
    ROUTES(1, R.id.navRoutes),
    PLACES(2, R.id.navPlaces),
    SETTINGS(3, R.id.navSettings), 
}

fun findNavigationPositionById(id: Int): JBNavigationPosition = when (id) {
    JBNavigationPosition.SEARCHBUS.id -> JBNavigationPosition.SEARCHBUS
    JBNavigationPosition.ROUTES.id -> JBNavigationPosition.ROUTES
    JBNavigationPosition.PLACES.id -> JBNavigationPosition.PLACES
    JBNavigationPosition.SETTINGS.id -> JBNavigationPosition.SETTINGS
     else -> JBNavigationPosition.SEARCHBUS
}

fun JBNavigationPosition.createFragment(): Fragment = when (this) {
    JBNavigationPosition.SEARCHBUS -> FragmentFindBus.newInstance()
    JBNavigationPosition.ROUTES -> FragmentRoutes.newInstance()
    JBNavigationPosition.PLACES -> FragmentOtherPlaces.newInstance()
    JBNavigationPosition.SETTINGS -> FragmentSettings.newInstance()
 }

fun JBNavigationPosition.getTag(): String = when (this) {
    JBNavigationPosition.SEARCHBUS -> FragmentFindBus.TAG
    JBNavigationPosition.ROUTES -> FragmentRoutes.TAG
    JBNavigationPosition.PLACES -> FragmentOtherPlaces.TAG
    JBNavigationPosition.SETTINGS -> FragmentSettings.TAG
 }

