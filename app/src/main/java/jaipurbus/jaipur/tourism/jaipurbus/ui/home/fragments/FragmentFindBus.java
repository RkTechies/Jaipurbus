package jaipurbus.jaipur.tourism.jaipurbus.ui.home.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.codersworld.jplibs.listeners.OnPageChangeListener;
import com.codersworld.jplibs.storage.UserSessions;
import com.codersworld.jplibs.utils.CommonMethods;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import jaipurbus.jaipur.tourism.jaipurbus.R;
import jaipurbus.jaipur.tourism.jaipurbus.databinding.FragmentFindBusBinding;
import jaipurbus.jaipur.tourism.jaipurbus.ui.search.DirectionsActrivity;
import jaipurbus.jaipur.tourism.jaipurbus.ui.search.SearchRoutesActivity;
import jaipurbus.jaipur.tourism.jaipurbus.utils.ApiHelper;

public class FragmentFindBus extends Fragment {


    EditText inputSource, inputDestination;
    Button btnGetBus;
    ImageView lowFloreBus, miniBus, metro;
    boolean lowFloreBool = true, miniBusBool = true, metrobool = true;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    String[][] bus_en = {
            {
                    "1",
                    "Bheru Khejda",
                    "Todi",
                    "Harmara",
                    "Nindar Mod",
                    "Jodla",
                    "Vki Road No. 14",
                    "Vki Road No. 12",
                    "Vki Road No. 9",
                    "Vki Road No. 8",
                    "Vki Road No. 5",
                    "Vki Road No. 1",
                    "Alka Cinema",
                    "Vidyadhar Nagar Bus Depot",
                    "Khetan Hospital",
                    "Dahar Ke Balaji",
                    "Bhawani Niketan",
                    "Chomu Puliya",
                    "Ambabari",
                    "Panipech",
                    "TB Hospital",
                    "Doodh Mandi",
                    "Pittal Factory",
                    "Chandpol",
                    "Choti Chaupar",
                    "Badi Chaupar",
                    "Sanganeri Gate",
                    "Transport Nagar",
                    "Galta Gate"
            },
            {
                    "1A",
                    "Vki Road No. 17",
                    "Vki Road No. 9",
                    "Vki Road No. 8",
                    "Vki Road No. 5",
                    "Vki Road No. 1",
                    "Alka Cinema",
                    "Vidyadhar Nagar Bus Depot",
                    "Khetan Hospital",
                    "Dahar Ke Balaji",
                    "Bhawani Niketan",
                    "Chomu Puliya",
                    "Ambabari",
                    "Panipech",
                    "TB Hospital",
                    "Doodh Mandi",
                    "Pittal Factory",
                    "Chandpol",
                    "Choti Chaupar",
                    "Badi Chaupar",
                    "Sanganeri Gate",
                    "Transport Nagar"
            },
            {
                    "3",
                    "Dwarikapuri",
                    "NRI Circle",
                    "Pratap Nagar Sector-16",
                    "Bhairon Mandir",
                    "Pratap Nagar Sector-11",
                    "Pratap Nagar Sector-10",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Barafkhana",
                    "Pagalkhana",
                    "Transport Nagar Mod",
                    "Transport Nagar"
            },
            {
                    "3A",
                    "Sanganer Town",
                    "Sanganer Stadium",
                    "Kundan Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Choti Chaupar"
            },
            {
                    "4A",
                    "Jaipur Railway Junction",
                    "Polo Victory",
                    "Panch Batti",
                    "Ajmeri Gate",
                    "Pagalkhana",
                    "Transport Nagar Mod",
                    "Transport Nagar",
                    "Khaniya",
                    "Balti Factory",
                    "Bawadi",
                    "Purani Chungi(Bawadi)",
                    "Hanuman Ji Mandir",
                    "Bagrana",
                    "Kanota"
            },
            {
                    "6A",
                    "Jawahar Circle",
                    "Malviya Nagar Sector",
                    "Malviya Nagar Sector-11",
                    "Malviya Nagar Sector-15",
                    "Malviya Nagar Sector-3",
                    "Malviya Nagar Sector-2(Satkar)",
                    "Calgary Hospital Mod",
                    "Hari Marg",
                    "Pradhan Marg",
                    "Saras Diary",
                    "MNIT",
                    "Bhaskar",
                    "Bajaj Nagar Enclave",
                    "Gandhinagar Station",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Panch Batti",
                    "Gpo",
                    "Khasa Kothi",
                    "Collectrate Circle",
                    "Chinkara Canteen",
                    "Panipech",
                    "Ambabari",
                    "Chomu Puliya",
                    "Lata Cinema",
                    "Jothwara Pankha",
                    "Vishnu Marg",
                    "Boring Road",
                    "Jothwara Kanta",
                    "Ram Nagar(Jothwara)",
                    "Lal Mandir",
                    "Khirni Phatak"
            },
            {
                    "7",
                    "Khirni Phatak",
                    "Heerapura",
                    "New Transport Nagar",
                    "Gaj singh pura",
                    "Kisan dharm kanta",
                    "Ganga Jamuna",
                    "Gurjar Ki Thadi",
                    "Mahesh Nagar",
                    "Ridhi Sidhi",
                    "Mahesh Nagar",
                    "Triveni Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "Trimurti Circle",
                    "Moti Dungri Mandir Circle",
                    "Rajapark",
                    "Barafkhana",
                    "Pagalkhana",
                    "Transport Nagar Mod",
                    "Transport Nagar"
            },
            {
                    "9A",
                    "Agarwal Farm",
                    "Thadi Market",
                    "Vijay Path",
                    "Patel Marg Crossing",
                    "Maharani Farm",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Sindhi Camp",
                    "Chandpol",
                    "Mayank Cinema",
                    "Pareek College",
                    "Pittal Factory",
                    "Shashtri Nagar Circle",
                    "Subhas colony",
                    "Kawatiya Circle",
                    "Hari Nagar",
                    "Ram Nagar(vidhyadhar nagar)",
                    "Vidhyadhar Nagar",
                    "Mandir Mod",
                    "Vidhyadhar Nagar Thana",
                    "Parsuram Circle",
                    "Alka Cinema",
                    "Vki Road No. 1",
                    "Murlipura Thana",
                    "Kedia Palace",
                    "Dadi Ka Phatak"
            },
            {
                    "10/10A/10B",
                    "Niwaru",
                    "34 No. Bus Stand",
                    "Dayal Nursing Home",
                    "Vaidhji Ka Chauraha",
                    "Ashok Marg",
                    "Niwaru Marg",
                    "Netaji Chowki",
                    "Laxmi Nagar",
                    "Ramneshpuri",
                    "Shalimar Factory",
                    "Shivaji Nagar chauraha",
                    "Jothwara Kanta",
                    "Boring Road",
                    "Jothwara Pankha",
                    "Lata Cinema",
                    "Chomu Puliya",
                    "Ambabari",
                    "Panipech",
                    "TB Hospital",
                    "Doodh Mandi",
                    "Pittal Factory",
                    "Power House",
                    "Chandpol",
                    "Mayank Cinema",
                    "Sindhi Camp",
                    "Polo Victory",
                    "Central Railway Hospital",
                    "Jaipur Club",
                    "Hasanpura",
                    "Esi No.4 Dispensary",
                    "Hatwada",
                    "Esi Hospital",
                    "Sodala Metro",
                    "Ram Nagar(Sodala)",
                    "Nandpuri",
                    "Ram Mandir",
                    "Sahkari Bhawan/22 Godam",
                    "High Court Circle",
                    "Rambagh Circle",
                    "JDA Circle",
                    "Lbs College Mod",
                    "Panchwati Circle",
                    "Pulia No 1",
                    "Behlad",
                    "Jawahar Nagar",
                    "Satsai College",
                    "Transport Nagar",
                    "Galta Gate"
            },
            {
                    "11",
                    "Goner",
                    "Vidhani",
                    "Mahatma Gandhi Hospital",
                    "BOSCH Limited",
                    "Goner Mod",
                    "12 Mill",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate"
            },
            {
                    "AC1",
                    "Sanganer Town",
                    "Sanganer Stadium",
                    "Kundan Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Sanganeri Gate",
                    "Badi Chaupar",
                    "Chandi Ki Taksal",
                    "Shubhash Chowk",
                    "Joravar Singh Gate",
                    "Govind Nagar",
                    "Ramgarh Mod",
                    "Shahpura Bag",
                    "Jal Mahal",
                    "Airforce Office",
                    "Mavata",
                    "Amer Fort",
                    "Amer",
                    "Amer Stadium",
                    "Morta",
                    "Narad Ka Bagh",
                    "Check Post Amer",
                    "Kukas"
            },
            {
                    "AC2",
                    "Mahatma Gandhi Hospital",
                    "BOSCH Limited",
                    "Goner Railway Phatak",
                    "Chatrala Circle",
                    "Genpact Sitapura",
                    "Sitapura Circle",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Sanganeri Gate",
                    "Badi Chaupar",
                    "Tripoliya Bazar",
                    "Choti Chaupar",
                    "Chandpol",
                    "Mayank Cinema",
                    "Pareek College",
                    "Pittal Factory",
                    "Doodh Mandi",
                    "TB Hospital",
                    "Panipech",
                    "Ambabari",
                    "Chomu Puliya",
                    "Lata Cinema",
                    "Jothwara Pankha",
                    "Vishnu Marg",
                    "Boring Road",
                    "Jothwara Kanta",
                    "Joshi Marg"
            },
            {
                    "14",
                    "Chomu Puliya",
                    "Jothwara Kanta",
                    "Boring Road",
                    "Jothwara Pankha",
                    "Lata Circle",
                    "Khatipura Mod",
                    "Chand Bihari Nagar",
                    "Jasvant Nagar",
                    "Khatipura",
                    "Khatipura Mod",
                    "Parivahan Nagar Khatipura",
                    "Khatipura Road",
                    "Kings Road Nirman Nagar",
                    "Gandhi Path",
                    "Gandhi Path Mod",
                    "Purani Chungi",
                    "Sushil Pura",
                    "Shayam Nagar",
                    "Sodala",
                    "Sodala Metro",
                    "Ram Nagar(Sodala)",
                    "Nandpuri",
                    "Ram Mandir",
                    "Sahkari Bhawan/22 Godam",
                    "High Court Circle",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Ram Niwas Garden Parking",
                    "Sanganeri Gate",
                    "Ghat Gate",
                    "Transport Nagar",
                    "Khaniya",
                    "Prem Nagar Pulliya",
                    "Balti Factory",
                    "Paldimina",
                    "Bawadi",
                    "Purani Chungi(Bawadi)",
                    "Hanuman Ji Mandir",
                    "Bagrana",
                    "Kanota",
                    "Dayaram Pura",
                    "Benada Mod",
                    "Ricco",
                    "Bassi Mod",
                    "Bassi"
            },
            {
                    "15",
                    "Chomu",
                    "Barala Hospital",
                    "Jaitpura",
                    "Rampura",
                    "Motu Ka Was",
                    "Rajawas",
                    "Todi",
                    "Harmara",
                    "Nindar Mod",
                    "Jodla",
                    "Vki Road No. 14",
                    "Vki Road No. 12",
                    "Vki Road No. 9",
                    "Vki Road No. 8",
                    "Vki Road No. 5",
                    "Vki Road No. 1",
                    "Alka Cinema",
                    "Vidyadhar Nagar Bus Depot",
                    "Khetan Hospital",
                    "Dahar Ke Balaji",
                    "Bhawani Niketan",
                    "Chomu Puliya",
                    "Ambabari",
                    "Panipech",
                    "TB Hospital",
                    "Doodh Mandi",
                    "Pittal Factory",
                    "Chandpol"
            },
            {
                    "16",
                    "Chaksu",
                    "Shitala",
                    "Yarlipura",
                    "Shivdaspura",
                    "Nagaliya",
                    "Bilwa",
                    "Goner Mod",
                    "12 Mill",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate"
            },
            {
                    "26",
                    "Bagru",
                    "Dhayami Balaji",
                    "Dhayami Kala Mod",
                    "Deoliya",
                    "Thikaria Mod",
                    "Bade Ke Balaji",
                    "Ramachandra Pura",
                    "Mahapura",
                    "Bhankrota",
                    "Keshupura",
                    "Chaudhary Nagar",
                    "Kamla Nehru Nagar",
                    "Dhawas Girdharipura Mode",
                    "Power House(hirapura)",
                    "Hirapura Bypass",
                    "Tagore Nagar",
                    "DCM",
                    "Kings Road Nirman Nagar",
                    "Purani Chungi",
                    "Sushil Pura",
                    "Shayam Nagar",
                    "Sodala",
                    "Esi No.4 Dispensary",
                    "Majdoor Nagar",
                    "Civil Lines Metro Station",
                    "Civil Lines Chauraha",
                    "Ajmer Pulia",
                    "Hathroi",
                    "Shalimar",
                    "Government Hostel",
                    "Gpo",
                    "MLA Quarters",
                    "Chandpol"
            },
            {
                    "34",
                    "Ananda Manglam City",
                    "Rampura Phatak",
                    "Triveni Puliya",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Choti Chaupar",
                    "Tripoliya Bazar",
                    "Badi Chaupar",
                    "Chandi Ki Taksal",
                    "Shubhash Chowk",
                    "Joravar Singh Gate",
                    "Govind Nagar",
                    "Ramgarh Mod",
                    "Nai Ki Thadi",
                    "Man Bag",
                    "JDA Colony",
                    "Jaisinghpura Khor",
            },
            {
                    "30",
                    "Badi Chaupar",
                    "Chandi Ki Taksal",
                    "Shubhash Chowk",
                    "Joravar Singh Gate",
                    "Govind Nagar",
                    "Ramgarh Mod",
                    "Nai Ki Thadi",
                    "Kunda",
                    "Ramgarh"
            },
            {
                    "32",
                    "Bhankrota",
                    "Keshupura",
                    "Kamla Nehru Nagar",
                    "New Transport Nagar",
                    "Ganga Jamuna Petrol Pump",
                    "Ganga Jamuna",
                    "Gurjar Ki Thadi",
                    "Mahesh Nagar",
                    "Surya Nagar",
                    "Triveni Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Pagalkhana",
                    "Transport Nagar Mod",
                    "Transport Nagar",
                    "Khaniya",
                    "Prem Nagar Pulliya",
                    "Balti Factory",
                    "Paldimina",
                    "Bawadi",
                    "Purani Chungi(Bawadi)",
                    "Hanuman Ji Mandir",
                    "Bagrana",
                    "Kanota",
                    "Heerawala",
                    "Geelawala",
                    "Nayala"
            },
            {
                    "25B",
                    "Teetriya",
                    "Chandlai",
                    "Shivdaspura",
                    "Goner Mod",
                    "12 Mill",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate"
            },
            {
                    "25A",
                    "Padampura",
                    "Shivdaspura",
                    "Goner Mod",
                    "12 Mill",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate"
            },
            {
                    "AC7",
                    "Chomu Puliya",
                    "Khasa Kothi",
                    "Jaipur Railway Junction",
                    "Polo Victory",
                    "Sindhi Camp",
                    "Ajmeri Gate",
                    "Maharani College",
                    "SMS Hospital",
                    "Narayan Singh Circle",
                    "Rambagh Circle",
                    "Lal Kothi",
                    "Gandhinagar Mod",
                    "Gandhi Circle",
                    "RTO Office",
                    "Baiji Ki Kothi",
                    "Kv No.3",
                    "Apex Circle",
                    "Haldia Garden",
                    "Dak Colony/Balaji Mod",
                    "Model Town",
                    "Jagatpura Railway Station",
                    "CBI Phatak",
                    "Indra Gandhi Ngar",
                    "Jagatpura"
            },
            {
                    "AC8",
                    "Mundiya Ramsar",
                    "Siwar Mod",
                    "Meenawala",
                    "Panchawala Puliya",
                    "Hanuman Nagar",
                    "Khatipura",
                    "Hasanpura",
                    "Khasa Kothi",
                    "Chandpol",
                    "Choti Chaupar"
            },
            {
                    "27/27A",
                    "Vatika",
                    "Bhatta Wala",
                    "Kalbada",
                    "Asha Wala",
                    "12 Mill",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Datti vada",
                    "Kalyan Nagar/Ram Mandir",
                    "Taron Ki Koot/surya nagar",
                    "City Plex",
                    "Jawahar Circle",
                    "Fortis Hospital",
                    "Genpact",
                    "Durgapura",
                    "Mahaveer Nagar",
                    "Mahaveer Nagar Mod",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Ram Niwas Garden Parking",
                    "Sanganeri Gate",
                    "Ghat Gate",
                    "Transport Nagar",
                    "Khaniya",
                    "Machh Ki Pipli",
                    "Loniyawas",
                    "Dantli",
                    "Siroli",
                    "Goner"
            },
            {
                    "24",
                    "Kalwad",
                    "Ram Kutiya",
                    "Govindpura",
                    "Rawan Gate",
                    "Joshi Marg",
                    "Jothwara Kanta",
                    "Boring Road",
                    "Jothwara Pankha",
                    "Lata Cinema",
                    "Chomu Puliya",
                    "Ambabari",
                    "Panipech",
                    "TB Hospital",
                    "Doodh Mandi",
                    "Pittal Factory",
                    "Pareek College",
                    "Mayank Cinema",
                    "Chandpol"
            },
            {
                    "10A city Bus",
                    "Jaipur Railway Junction",
                    "Polo Victory",
                    "Sindhi Camp",
                    "Mayank Cinema",
                    "Chandpol",
                    "Choti Chaupar",
                    "Tripoliya Bazar",
                    "Badi Chaupar",
                    "Ramganj Chaupar",
                    "Surajpole",
                    "Galta Gate",
                    "Lal Masjid Churaha",
                    "Khole Ke Hanuman Ji"
            },
            {
                    "7 city Bus",
                    "Khatipura Railway Station",
                    "Indra Gandhi Nagar Sector-1",
                    "Indra Gandhi Nagar Sector-2",
                    "Indra Gandhi Nagar Sector-3",
                    "Indra Gandhi Nagar Sector-4",
                    "Indra Gandhi Nagar Sector-5",
                    "Indra Gandhi Nagar Sector-6",
                    "CBI Phatak",
                    "Jagatpura Phatak",
                    "Jagatpura Railway Station",
                    "Model Town",
                    "Dak Colony/Balaji Mod",
                    "Malviya Nagar Sector-3",
                    "Malviya Nagar Sector-2(Satkar)",
                    "Calgary Hospital Mod",
                    "Hari Marg",
                    "Pradhan Marg",
                    "Gaurav Tower Puliya( GT )",
                    "Saras Diary",
                    "MNIT",
                    "Jaldhara/Shiksha Sankul",
                    "Commerce College",
                    "Gandhinagar Station",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Nehru Place",
                    "Gandhinagar Mod",
                    "Lal Kothi",
                    "Rambagh Circle",
                    "Narayan Singh Circle",
                    "SMS Hospital",
                    "Maharani College",
                    "Ajmeri Gate",
                    "Panch Batti",
                    "Gpo",
                    "Polo Victory",
                    "Jaipur Railway Junction",
                    "Chinkara Canteen",
                    "Panipech",
                    "Ambabari",
                    "Chomu Puliya",
                    "Lata Cinema",
                    "Jothwara Pankha",
                    "Vishnu Marg",
                    "Boring Road",
                    "Jothwara Kanta",
                    "Joshi Marg",
                    "Rawan Gate",
                    "Govindpura"
            },
            {
                    "13 city Bus",
                    "Khatipura Railway Station",
                    "Loniyawas",
                    "Machh Ki Pipli",
                    "Khaniya",
                    "Chulgiri",
                    "Transport Nagar",
                    "Ghat Gate",
                    "Sanganeri Gate",
                    "Trimurti Circle",
                    "Moti Dungri Mandir Circle",
                    "JDA Circle",
                    "Rambagh Circle",
                    "Lal Kothi",
                    "Gandhinagar Mod",
                    "Nehru Place",
                    "Laxmi Mandir",
                    "Tonk Phatak",
                    "Sayeed Ka Gatta",
                    "Gopalpura Mod",
                    "Mahaveer Nagar Mod",
                    "Mahaveer Nagar",
                    "Durgapura",
                    "Genpact",
                    "Fortis Hospital",
                    "Jawahar Circle",
                    "City Plex",
                    "Taron Ki Koot/surya nagar",
                    "Kalyan Nagar/Ram Mandir",
                    "Datti vada",
                    "Sanganer Thana",
                    "Kundan Nagar",
                    "Sanganer Stadium",
                    "Sanganer Town",
                    "Muana Mandi",
                    "Muana Gav"
            },
            {
                    "1 city Bus",
                    "Khole Ke Hanuman Ji",
                    "Lal Masjid Churaha",
                    "Galta Gate",
                    "Surajpole",
                    "Ramganj Chaupar",
                    "Badi Chaupar",
                    "Tripoliya Bazar",
                    "Choti Chaupar",
                    "Chandpol",
                    "Mayank Cinema",
                    "Sindhi Camp",
                    "Polo Victory",
                    "Jaipur Railway Junction",
                    "Hasanpura",
                    "Esi No.4 Dispensary",
                    "Hatwada",
                    "Esi Hospital",
                    "Sodala",
                    "Shyam Nagar Metro Station",
                    "Lazeez Hotel",
                    "Devi Nagar",
                    "Sanjeevani Hospital",
                    "Vivek Vihar Metro Station",
                    "Gurjar Ki Thadi",
                    "Ganga Jamuna",
                    "Ganga Jamuna Petrol Pump",
                    "Kaveri Path",
                    "Swarn Path",
                    "Varun Path",
                    "Kiran Path",
                    "Rajat Path",
                    "Hira Path",
                    "Akbar Vt Road",
                    "Aravali Marg",
                    "Kv No. 5",
                    "Market Road",
                    "Patel Marg",
                    "Meera Marg",
                    "Vijay Path",
                    "Thadi Market",
                    "Agarwal Farm",
                    "Suresh Nagar Mod",
                    "Ricco",
                    "Ricco Chauraha",
                    "Sanganer Petrol Pump",
                    "Sanganer Stadium",
                    "Kundan Nagar",
                    "Sanganer Thana",
                    "Shiv Nagar",
                    "Gaushala",
                    "Pratap Nagar Sector-3",
                    "Pratap Nagar Sector-5",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-8",
                    "Kumbha Marg"
            },
            {
                    "2 city Bus",
                    "Jaipur Railway Junction",
                    "Polo Victory",
                    "Sindhi Camp",
                    "Mayank Cinema",
                    "Chandpol",
                    "Choti Chaupar",
                    "Tripoliya Bazar",
                    "Badi Chaupar",
                    "Chandi Ki Taksal",
                    "Shubhash Chowk",
                    "Joravar Singh Gate",
                    "Govind Nagar",
                    "Ramgarh Mod",
                    "Man Bag",
                    "Sadva Mod",
                    "Muslim University"
            },
            {
                    "36 city Bus",
                    "Jagatpura Phatak",
                    "CBI Phatak",
                    "New Rto Office",
                    "JNU SADTM Campus",
                    "JNU Silas Campus",
                    "JNU Main Campus",
                    "JNU Silas Campus",
                    "JNU SADTM Campus",
                    "New Rto Office",
                    "Kho-Nagorian",
                    "Rahim Nagar",
                    "Karim Nagar",
                    "Madina Nagar",
                    "Khaniya",
                    "Chulgiri",
                    "Transport Nagar",
                    "Ghat Gate"
            },
            {
                    "5 city Bus",
                    "Jaipur Railway Junction",
                    "Polo Victory",
                    "Gpo",
                    "Panch Batti",
                    "Ajmeri Gate",
                    "Ram Niwas Garden Parking",
                    "Sanganeri Gate",
                    "Ghat Gate",
                    "Nayla House",
                    "Moti Dungri Mandir Circle",
                    "Jawahar Nagar"
            },
            {
                    "15 city Bus",
                    "Todi",
                    "Harmara",
                    "Nindar Mod",
                    "Jodla",
                    "Vki Road No. 14",
                    "Vki Road No. 12",
                    "Vki Road No. 9",
                    "Vki Road No. 8",
                    "Vki Road No. 5",
                    "Vki Road No. 1",
                    "Alka Cinema",
                    "Vidhyadhar Nagar Thana",
                    "Mandir Mod",
                    "Vidhyadhar Nagar",
                    "Ram Nagar(vidhyadhar nagar)",
                    "Hari Nagar",
                    "Kawatiya Circle",
                    "Subhas colony",
                    "Shashtri Nagar Circle",
                    "Mochion Ki Chhabil",
                    "Shashtri Nagar Thana",
                    "Pittal Factory",
                    "Pareek College",
                    "Mayank Cinema",
                    "Chandpol",
                    "Mayank Cinema",
                    "Sindhi Camp",
                    "Polo Victory",
                    "Gpo",
                    "Panch Batti",
                    "Ajmeri Gate",
                    "Ram Niwas Garden Parking",
                    "Sanganeri Gate",
                    "Ghat Gate",
                    "Transport Nagar",
                    "Sisodiya Rani Garden",
                    "Jamdoli",
                    "Bawadi",
                    "Purani Chungi(Bawadi)",
                    "Hanuman Ji Mandir",
                    "Babrana",
                    "Kanota"
            },
            {
                    "16 city Bus",
                    "Galta Gate",
                    "Surajpole",
                    "Ramganj Chaupar",
                    "Badi Chaupar",
                    "Tripoliya Bazar",
                    "Choti Chaupar",
                    "Chandpol",
                    "Mayank Cinema",
                    "Pareek College",
                    "Pittal Factory",
                    "Shashtri Nagar Thana",
                    "Mochion Ki Chhabil",
                    "Shashtri Nagar Circle",
                    "Naya Kheda",
                    "Khetan Hospital",
                    "Vidyadhar Nagar Bus Depot",
                    "Alka Cinema",
                    "Vki Road No. 1",
                    "Shivaji College",
                    "Murlipura Thana",
                    "Murlipura Circle",
                    "Kedia Palace",
                    "Dadi Ka Phatak"
            },
            {
                    "17 city Bus",
                    "Gyan Vihar College",
                    "7 No. Chauraha",
                    "Jagatpura Phatak",
                    "Kacchi Basti",
                    "Malviya Nagar Sector-15",
                    "Malviya Nagar Sector-11",
                    "Malviya Nagar Sector-4",
                    "Danapani",
                    "Pradhan Marg",
                    "Gaurav Tower Puliya( GT )",
                    "Saras Diary",
                    "MNIT",
                    "Bhaskar",
                    "Bajaj Nagar Enclave",
                    "Gandhinagar Station",
                    "Commerce College",
                    "Rajasthan College",
                    "Kanodia University",
                    "Rajasthan University",
                    "JDA Circle",
                    "Rambagh Circle",
                    "High Court Circle",
                    "Secretariat",
                    "Tilak Marg",
                    "Bagdiya Bhawan",
                    "Chomu House",
                    "Government press",
                    "Government Hostel",
                    "Jaipur Railway Junction",
                    "Polo Victory",
                    "Sindhi Camp",
                    "Mayank Cinema",
                    "Chandpol",
                    "Power House",
                    "Pittal Factory",
                    "Shashtri Nagar Thana",
                    "Mochion Ki Chhabil",
                    "Shashtri Nagar Circle",
                    "Subhas colony",
                    "Kawatiya Circle",
                    "Bhatta Basti"
            },
            {
                    "32 city Bus",
                    "Kacchi Basti",
                    "Malviya Nagar Sector-5",
                    "Malviya Nagar Sector-6",
                    "Malviya Nagar Sector-7",
                    "Malviya Nagar Sector-9",
                    "Malviya Nagar Sector",
                    "Malviya Nagar Sector-13",
                    "Gaurav Tower ( GT )",
                    "Jaipuria Hospital",
                    "Gopalpura Mod",
                    "Sayeed Ka Gatta",
                    "Tonk Phatak",
                    "Laxmi Mandir",
                    "Sahkari Bhawan/22 Godam",
                    "High Court Circle",
                    "Secretariat",
                    "Tilak Marg",
                    "Bagdiya Bhawan",
                    "Chomu House",
                    "Government press",
                    "Government Hostel",
                    "Gpo",
                    "MLA Quarters",
                    "Chandpol",
                    "Mayank Cinema",
                    "Pareek College",
                    "Pittal Factory",
                    "Doodh Mandi",
                    "TB Hospital",
                    "Panipech",
                    "Ambabari",
                    "Chomu Puliya",
                    "Lata Cinema",
                    "Jothwara Pankha",
                    "Vishnu Marg",
                    "Boring Road",
                    "Jothwara Kanta",
                    "Joshi Marg",
                    "Rawan Gate",
                    "Govindpura"
            },
            {
                    "28 city Bus",
                    "Surajpole",
                    "Galta Gate",
                    "Transport Nagar",
                    "Ghat Gate",
                    "Sanganeri Gate",
                    "Ram Niwas Garden Parking",
                    "Ajmeri Gate",
                    "Maharani College",
                    "SMS Hospital",
                    "Narayan Singh Circle",
                    "Rambagh Circle",
                    "Lal Kothi",
                    "Gandhinagar Mod",
                    "Nehru Place",
                    "Laxmi Mandir",
                    "Tonk Phatak",
                    "Sayeed Ka Gatta",
                    "Gopalpura Mod",
                    "Mahaveer Nagar Mod",
                    "Triveni Nagar",
                    "Mahesh Nagar Mod",
                    "Ridhi Sidhi",
                    "Mahesh Nagar",
                    "Gurjar Ki Thadi",
                    "Ganga Jamuna",
                    "Ganga Jamuna Petrol Pump",
                    "Kaveri Path",
                    "Swarn Path",
                    "Varun Path",
                    "Kiran Path",
                    "Rajat Path",
                    "Hira Path",
                    "Akbar Vt Road",
                    "Aravali Marg",
                    "Kv No. 5",
                    "Market Road",
                    "Patel Marg",
                    "Meera Marg",
                    "Vijay Path",
                    "Thadi Market",
                    "Agarwal Farm",
                    "Suresh Nagar Mod",
                    "Ricco",
                    "Sanganer Town",
                    "Muana Mandi"
            },
            {
                    "55 city Bus",
                    "Sikar Road",
                    "Vki Road No. 9",
                    "Vki Road No. 8",
                    "Vki Road No. 5",
                    "Vki Road No. 1",
                    "Alka Cinema",
                    "Vidyadhar Nagar Bus Depot",
                    "Khetan Hospital",
                    "Dahar Ke Balaji",
                    "Bhawani Niketan",
                    "Chomu Puliya",
                    "Ambabari",
                    "Panipech",
                    "Chinkara Canteen",
                    "Jaipur Railway Junction",
                    "Government Hostel",
                    "Gpo",
                    "Panch Batti",
                    "Ajmeri Gate",
                    "Maharani College",
                    "SMS Hospital",
                    "Narayan Singh Circle",
                    "Rambagh Circle",
                    "Lal Kothi",
                    "Gandhinagar Mod",
                    "Nehru Place",
                    "Laxmi Mandir",
                    "Tonk Phatak",
                    "Sayeed Ka Gatta",
                    "Gopalpura Mod",
                    "Mahaveer Nagar Mod",
                    "Mahaveer Nagar",
                    "Durgapura",
                    "Genpact",
                    "Fortis Hospital",
                    "Jawahar Circle",
                    "City Plex",
                    "Taron Ki Koot/surya nagar",
                    "Kalyan Nagar/Ram Mandir",
                    "Datti vada",
                    "Sanganer Thana",
                    "Shiv Nagar",
                    "Gaushala",
                    "Pratap Nagar Sector-3",
                    "Pratap Nagar Sector-5",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-8",
                    "Kumbha Marg",
                    "Pratap Nagar RSRTC Bus Stand",
                    "India Gate",
                    "12 Mill",
                    "Goner Mod",
                    "BOSCH Limited",
                    "Mahatma Gandhi Hospital"
            },
            {
                    "21 city Bus",
                    "Parsotwada",
                    "Ramgarh Mod",
                    "Idgah",
                    "Galta Gate",
                    "Transport Nagar",
                    "Ghat Gate",
                    "Sanganeri Gate",
                    "Ram Niwas Garden Parking",
                    "Ajmeri Gate",
                    "Maharani College",
                    "SMS Hospital",
                    "Narayan Singh Circle",
                    "Rambagh Circle",
                    "High Court Circle",
                    "Sahkari Bhawan/22 Godam",
                    "Ram Mandir",
                    "Nandpuri",
                    "Ram Nagar(Sodala)",
                    "Sodala Metro",
                    "Sodala",
                    "Shayam Nagar",
                    "Sushil Pura",
                    "Purani Chungi",
                    "Prince Road",
                    "Kings Road Nirman Nagar",
                    "Karani Palace",
                    "Panchyawala"
            },
            {
                    "29 city Bus",
                    "Kukas",
                    "Check Post Amer",
                    "Kunda",
                    "Narad Ka Bagh",
                    "Morta",
                    "Amer Stadium",
                    "Amer",
                    "Amer Fort",
                    "Mavata",
                    "Airforce Office",
                    "Jal Mahal",
                    "Shahpura Bag",
                    "Ramgarh Mod",
                    "Govind Nagar",
                    "Joravar Singh Gate",
                    "Shubhash Chowk",
                    "Chandi Ki Taksal",
                    "Badi Chaupar",
                    "Sanganeri Gate",
                    "Ajmeri Gate",
                    "Maharani College",
                    "SMS Hospital",
                    "Narayan Singh Circle",
                    "Rambagh Circle",
                    "High Court Circle",
                    "Sahkari Bhawan/22 Godam",
                    "Ram Mandir",
                    "Nandpuri",
                    "Ram Nagar(Sodala)",
                    "Sodala Metro",
                    "Sodala",
                    "Shayam Nagar",
                    "Sushil Pura",
                    "Purani Chungi",
                    "Kings Road Nirman Nagar",
                    "DCM",
                    "Tagore Nagar",
                    "Hirapura Bypass",
                    "Power House(hirapura)",
                    "Dhawas Girdharipura Mode",
                    "Kamla Nehru Nagar",
                    "Chaudhary Nagar",
                    "Keshupura",
                    "Bhankrota",
                    "Mahapura"
            },
            {
                    "22 city Bus",
                    "Jaisinghpura Khor",
                    "Ramgarh Mod",
                    "Govind Nagar",
                    "Joravar Singh Gate",
                    "Shubhash Chowk",
                    "Chandi Ki Taksal",
                    "Badi Chaupar",
                    "Sanganeri Gate",
                    "Ajmeri Gate",
                    "Maharani College",
                    "SMS Hospital",
                    "Narayan Singh Circle",
                    "Rambagh Circle",
                    "High Court Circle",
                    "Imli Phatak",
                    "Kartarpura Phatak",
                    "Mahesh Nagar Phatak",
                    "Gurjar Ki Thadi",
                    "Ganga Jamuna",
                    "Ganga Jamuna Petrol Pump",
                    "Kaveri Path",
                    "Swarn Path",
                    "Varun Path",
                    "Kiran Path",
                    "Rajat Path",
                    "Hira Path",
                    "Akbar Vt Road",
                    "Aravali Marg",
                    "Kv No. 5",
                    "Market Road",
                    "Patel Marg",
                    "Meera Marg",
                    "Vijay Path",
                    "Thadi Market",
                    "Agarwal Farm",
                    "Suresh Nagar Mod",
                    "Ricco",
                    "Ricco Chauraha",
                    "Sanganer Petrol Pump",
                    "Sanganer Stadium",
                    "Kundan Nagar",
                    "Sanganer Thana",
                    "Shiv Nagar",
                    "Gaushala",
                    "Pratap Nagar Sector-3",
                    "Pratap Nagar Sector-5",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-8",
                    "Kumbha Marg",
                    "Pratap Nagar RSRTC Bus Stand"
            },
            {
                    "9B city Bus",
                    "Naradpura Mod",
                    "Kunda",
                    "Narad Ka Bagh",
                    "Morta",
                    "Amer Stadium",
                    "Amer",
                    "Amer Fort",
                    "Mavata",
                    "Airforce Office",
                    "Jal Mahal",
                    "Shahpura Bag",
                    "Ramgarh Mod",
                    "Govind Nagar",
                    "Joravar Singh Gate",
                    "Shubhash Chowk",
                    "Chandi Ki Taksal",
                    "Badi Chaupar",
                    "Tripoliya Bazar",
                    "Choti Chaupar",
                    "Chandpol",
                    "Mayank Cinema",
                    "Sindhi Camp",
                    "Polo Victory",
                    "Jaipur Railway Junction"
            },
            {
                    "30 city Bus",
                    "Paldimina",
                    "Balti Factory",
                    "Prem Nagar Pulliya",
                    "Khaniya",
                    "Transport Nagar",
                    "Galta Gate",
                    "Surajpole",
                    "Ramganj Chaupar",
                    "Badi Chaupar",
                    "Tripoliya Bazar",
                    "Choti Chaupar",
                    "Chandpol",
                    "Mayank Cinema",
                    "Pareek College",
                    "Pittal Factory",
                    "Doodh Mandi",
                    "TB Hospital",
                    "Panipech",
                    "Ambabari",
                    "Chomu Puliya",
                    "Lata Cinema",
                    "Jothwara Pankha",
                    "Vishnu Marg",
                    "Boring Road",
                    "Jothwara Kanta",
                    "Shivaji Nagar chauraha",
                    "Shalimar Factory",
                    "Dadi Ka Phatak",
                    "Nadi Ka Phatak",
                    "Shyam Nagar(benad)",
                    "Balaji College",
                    "Mohan Hospital",
                    "Benad",
                    "Sarada"
            },
            {
                    "11 city Bus",
                    "Bilwa",
                    "Goner Mod",
                    "12 Mill",
                    "India Gate",
                    "Pratap Nagar RSRTC Bus Stand",
                    "Kumbha Marg",
                    "Pratap Nagar Sector-8",
                    "Haldigati Gate",
                    "Pratap Nagar Sector-5",
                    "Pratap Nagar Sector-3",
                    "Gaushala",
                    "Shiv Nagar",
                    "Sanganer Thana",
                    "Kundan Nagar",
                    "Sanganer Stadium",
                    "Sanganer Petrol Pump",
                    "Ricco Chauraha",
                    "Ricco",
                    "Suresh Nagar Mod",
                    "Agarwal Farm",
                    "Thadi Market",
                    "Vijay Path",
                    "Meera Marg",
                    "Patel Marg",
                    "Market Road",
                    "Kv No. 5",
                    "Aravali Marg",
                    "Akbar Vt Road",
                    "Hira Path",
                    "Rajat Path",
                    "Kiran Path",
                    "Varun Path",
                    "Swarn Path",
                    "Kaveri Path",
                    "Ganga Jamuna Petrol Pump",
                    "Ganga Jamuna",
                    "Gurjar Ki Thadi",
                    "Vivek Vihar Metro Station",
                    "Sanjeevani Hospital",
                    "Devi Nagar",
                    "Lazeez Hotel",
                    "Shyam Nagar Metro Station",
                    "Sodala",
                    "Esi Hospital",
                    "Majdoor Nagar",
                    "Civil Lines Metro Station",
                    "Civil Lines Chauraha",
                    "Hathroi",
                    "Shalimar",
                    "Government Hostel",
                    "Gpo",
                    "MLA Quarters",
                    "Chandpol",
                    "Mayank Cinema",
                    "Pareek College",
                    "Pittal Factory",
                    "Doodh Mandi",
                    "TB Hospital",
                    "Panipech",
                    "Ambabari",
                    "Chomu Puliya",
                    "Bhawani Niketan",
                    "Dahar Ke Balaji",
                    "Khetan Hospital",
                    "Vidyadhar Nagar Bus Depot",
                    "Alka Cinema",
                    "Vki Road No. 1",
                    "Shivaji College",
                    "Murlipura Thana",
                    "Murlipura Circle",
                    "Kedia Palace",
                    "Dadi Ka Phatak",
                    "Nadi Ka Phatak",
                    "Shyam Nagar(benad)",
                    "Balaji College",
                    "Mohan Hospital",
                    "Benad"
            },
            {
                    "Metro",
                    "Mansarovar Metro Station",
                    "New Aatish Market Metro Station",
                    "Vivek Vihar Metro Station",
                    "Shyam Nagar Metro Station",
                    "Ram Nagar(Sodala)",
                    "Civil Lines Metro Station",
                    "Jaipur Railway Junction",
                    "Sindhi Camp",
                    "Chandpol",
                    "Choti Chaupar",
                    "Badi Chaupar"
            }
    };

    String[][] bus_hi = {
            {
                    "1",
                    "भेरू खेजडा",
                    "टोडी",
                    "हरमाडा",
                    "निंदड मोड",
                    "जोड़ला",
                    "वी के आई रोड नंबर 14",
                    "वी के आई रोड नंबर 12",
                    "वी के आई रोड नंबर 9",
                    "वी के आई रोड नंबर 8",
                    "वी के आई रोड नंबर 5",
                    "वी के आई रोड नंबर 1",
                    "अल्का सिनेमा",
                    "विद्याधर नगर बस डिपो",
                    "खेतान हॉस्पिटल",
                    "डहर के बालाजी",
                    "भवानी निकेतन",
                    "चौमू पुलिया",
                    "अम्बाबाडी",
                    "पनीपेच",
                    "टीबी हॉस्पिटल",
                    "दूध मंडी",
                    "पित्तल फैक्ट्री",
                    "चाँदपोल",
                    "छोटी चौपड़",
                    "बड़ी चौपड़",
                    "सांगानेरी गेट",
                    "ट्रांसपोर्ट नगर",
                    "गलता गेट"
            },
            {
                    "1A",
                    "वी के आई रोड नंबर 17",
                    "वी के आई रोड नंबर 9",
                    "वी के आई रोड नंबर 8",
                    "वी के आई रोड नंबर 5",
                    "वी के आई रोड नंबर 1",
                    "अल्का सिनेमा",
                    "विद्याधर नगर बस डिपो",
                    "खेतान हॉस्पिटल",
                    "डहर के बालाजी",
                    "भवानी निकेतन",
                    "चौमू पुलिया",
                    "अम्बाबाडी",
                    "पनीपेच",
                    "टीबी अस्पताल",
                    "दूध मंडी",
                    "पित्तल फैक्ट्री",
                    "चाँदपोल",
                    "छोटी चौपड़",
                    "बड़ी चौपड़",
                    "सांगानेरी गेट",
                    "ट्रांसपोर्ट नगर"
            },
            {
                    "3",
                    "द्वारिकापुरी",
                    "एनआरआई सर्कल",
                    "प्रताप नगर सेक्टर-16",
                    "भैरों मंदिर",
                    "प्रताप नगर सेक्टर-11",
                    "प्रताप नगर सेक्टर-10",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "बार्फखाना",
                    "पागलखाना",
                    "ट्रांसपोर्ट नगर मोड़",
                    "ट्रांसपोर्ट नगर"
            },
            {
                    "3A",
                    "सांगानेर टाउन",
                    "सांगानेर स्टेडियम",
                    "कुंदन नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "छोटी चौपड़"
            },
            {
                    "4A",
                    "जयपुर रेलवे जंक्शन",
                    "पोलो विक्ट्री",
                    "पांच बत्ती",
                    "अजमेरी गेट",
                    "पागलखाना",
                    "ट्रांसपोर्ट नगर मोड़",
                    "ट्रांसपोर्ट नगर",
                    "खनिया",
                    "बाल्टी फैक्ट्री",
                    "बावड़ी",
                    "पुरानी चुंगी (बावड़ी)",
                    "हनुमान जी मंदिर",
                    "बगराना",
                    "कानोता"
            },
            {
                    "6A",
                    "जवाहर सर्किल",
                    "मालवीय नगर सेक्टर",
                    "मालवीय नगर सेक्टर-11",
                    "मालवीय नगर सेक्टर-15",
                    "मालवीय नगर सेक्टर-3",
                    "मालवीय नगर सेक्टर-2(सत्कार)",
                    "कैलगरी हॉस्पिटल मोड",
                    "हरि मार्ग",
                    "प्रधान मार्ग",
                    "सरस डेयरी",
                    "MNIT",
                    "भास्कर",
                    "बजाज नगर एन्क्लेव",
                    "गांधीनगर स्टेशन",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "पांच बत्ती",
                    "Gpo",
                    "खासा कोठी",
                    "कलेक्ट्रेट सर्कल",
                    "चिंकारा कैंटीन",
                    "पनीपेच",
                    "अम्बाबाडी",
                    "चौमू पुलिया",
                    "लता सिनेमा",
                    "झोटवाड़ा पंखा",
                    "विष्णु मार्ग",
                    "बोरिंग रोड",
                    "झोटवाड़ा कांटा",
                    "राम नगर (झोटवाड़ा)",
                    "लाल मंदिर",
                    "खिरनी फाटक"
            },
            {
                    "7",
                    "खिरनी फाटक",
                    "हीरापुरा",
                    "न्यू ट्रांसपोर्ट नगर",
                    "गजसिंह पुरा",
                    "किसान धर्म कांटा",
                    "गंगा जमुना",
                    "गुर्जर की थड़ी",
                    "महेश नगर",
                    "रिद्धि सिद्धि",
                    "महेश नगर",
                    "त्रिवेणी नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "त्रिमूर्ति सर्कल",
                    "मोती डूंगरी मंदिर सर्कल",
                    "राजापार्क",
                    "बार्फखाना",
                    "पागलखाना",
                    "ट्रांसपोर्ट नगर मोड़",
                    "ट्रांसपोर्ट नगर"
            },
            {
                    "9A",
                    "अग्रवाल फार्म",
                    "थडी मार्केट",
                    "विजय पथ",
                    "पटेल मार्ग क्रॉसिंग",
                    "महारानी फार्म",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "सिंधी कैंप",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "पारीक कॉलेज",
                    "पित्तल फैक्ट्री",
                    "शास्त्री नगर सर्किल",
                    "सुभाष कॉलोनी",
                    "कांवटिया सर्कल",
                    "हरि नगर",
                    "राम नगर (विद्याधर नगर)",
                    "विद्याधर नगर",
                    "मंदिर मोड़",
                    "विद्याधर नगर थाना",
                    "परशुराम सर्कल",
                    "अल्का सिनेमा",
                    "वी के आई रोड नंबर 1",
                    "मुरलीपुरा थाना",
                    "केडिया पैलेस",
                    "दादी का फाटक"
            },
            {
                    "10/10A/10B",
                    "निवारू",
                    "34 नं. बस स्टैंड",
                    "दयाल नर्सिंग होम",
                    "वैधजी का चौराहा",
                    "अशोक मार्ग",
                    "निवारू मार्ग",
                    "नेताजी चौकी",
                    "लक्ष्मी नगर",
                    "रमनेशपुरी",
                    "शालीमार फैक्ट्री",
                    "शिवाजी नगर चौराहा",
                    "झोटवाड़ा कांटा",
                    "बोरिंग रोड",
                    "झोटवाड़ा पंखा",
                    "लता सिनेमा",
                    "चौमू पुलिया",
                    "अम्बाबाडी",
                    "पनीपेच",
                    "टीबी अस्पताल",
                    "दूध मंडी",
                    "पित्तल फैक्ट्री",
                    "पावर हाउस",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "सिंधी कैंप",
                    "पोलो विक्ट्री",
                    "सेंट्रल रेल्वे हॉस्पिटल",
                    "जयपुर क्लब",
                    "हसनपुरा",
                    "4 नं. डिस्पेंसरी",
                    "हटवाड़ा",
                    "Esi हॉस्पिटल",
                    "सोडाला मेट्रो",
                    "राम नगर (सोडाला)",
                    "नंदपुरी",
                    "राम मंदिर",
                    "सहकारी भवन/22 गोदाम",
                    "हाई कोर्ट सर्कल",
                    "रामबाग सर्किल",
                    "JDA सर्किल",
                    "LBS कॉलेज मोड",
                    "पंचवटी सर्कल",
                    "पुलिया नं. 1",
                    "बेहलाड",
                    "जवाहर नगर",
                    "सतसांई कॉलेज",
                    "ट्रांसपोर्ट नगर",
                    "गलता गेट"
            },
            {
                    "11",
                    "गोनेर",
                    "विधानी",
                    "महात्मा गांधी हॉस्पिटल",
                    "BOSCH लिमिटेड",
                    "गोनेर मोड",
                    "12 मील",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट"
            },
            {
                    "AC1",
                    "सांगानेर टाउन",
                    "सांगानेर स्टेडियम",
                    "कुंदन नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "सांगानेरी गेट",
                    "बड़ी चौपड़",
                    "चांदी की टकसाल",
                    "सुभाष चौक",
                    "जोरावर सिंह गेट",
                    "गोविंद नगर",
                    "रामगढ़ मोड़",
                    "शाहपुरा बाग",
                    "जल महल",
                    "एयरफोर्स ऑफिस ",
                    "मावठा",
                    "आमेर का किला",
                    "आमेर",
                    "आमेर स्टेडियम",
                    "मोरता",
                    "नारद का बाग",
                    "चेक पोस्ट आमेर",
                    "कुकस"
            },
            {
                    "AC2",
                    "महात्मा गांधी हॉस्पिटल",
                    "BOSCH लिमिटेड",
                    "गोनर रेलवे गेट",
                    "चतराला सर्कल",
                    "जेनपैक्ट सीतापुरा",
                    "सीतापुरा सर्कल",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "सांगानेरी गेट",
                    "बड़ी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "छोटी चौपड़",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "पारीक कॉलेज",
                    "पित्तल फैक्ट्री",
                    "दूध मंडी",
                    "टीबी अस्पताल",
                    "पनीपेच",
                    "अम्बाबाडी",
                    "चौमू पुलिया",
                    "लता सिनेमा",
                    "झोटवाड़ा पंखा",
                    "विष्णु मार्ग",
                    "बोरिंग रोड",
                    "झोटवाड़ा कांटा",
                    "जोशी मार्ग"
            },
            {
                    "14",
                    "चौमू पुलिया",
                    "झोटवाड़ा कांटा",
                    "बोरिंग रोड",
                    "झोटवाड़ा पंखा",
                    "लता सर्किल",
                    "खातीपुरा मोड़",
                    "चाँद बिहारी नगर",
                    "जसवंत नगर",
                    "खातीपुरा",
                    "खातीपुरा मोड़",
                    "परिवहन नगर खातीपुरा",
                    "खातीपुरा रोड",
                    "किंग्स रोड निर्माण नगर",
                    "गांधी पथ",
                    "गांधी पथ मोड़",
                    "पुरानी चुंगी",
                    "सुशील पुरा",
                    "श्याम नगर",
                    "सोडाला",
                    "सोडाला मेट्रो",
                    "राम नगर (सोडाला)",
                    "नंदपुरी",
                    "राम मंदिर",
                    "सहकारी भवन/22 गोदाम",
                    "हाई कोर्ट सर्कल",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "राम निवास गार्डन पार्किंग",
                    "सांगानेरी गेट",
                    "घाट गेट",
                    "ट्रांसपोर्ट नगर",
                    "खनिया",
                    "प्रेम नगर पुलिया",
                    "बाल्टी फैक्ट्री",
                    "पाल्डीमीना",
                    "बावड़ी",
                    "पुरानी चुंगी (बावड़ी)",
                    "हनुमान जी मंदिर",
                    "बगराना",
                    "कानोता",
                    "दयाराम पुरा",
                    "बेनाडा मोड",
                    "Ricco",
                    "बस्सी मोड़",
                    "बस्सी"
            },
            {
                    "15",
                    "चौमू",
                    "बराला हॉस्पिटल",
                    "जैतपुरा",
                    "रामपुरा",
                    "मोटू का वास",
                    "राजावास",
                    "टोडी",
                    "हरमाडा",
                    "निंदड मोड",
                    "जोड़ला",
                    "वी के आई रोड नंबर 14",
                    "वी के आई रोड नंबर 12",
                    "वी के आई रोड नंबर 9",
                    "वी के आई रोड नंबर 8",
                    "वी के आई रोड नंबर 5",
                    "वी के आई रोड नंबर 1",
                    "अल्का सिनेमा",
                    "विद्याधर नगर बस डिपो",
                    "खेतान हॉस्पिटल",
                    "डहर के बालाजी",
                    "भवानी निकेतन",
                    "चौमू पुलिया",
                    "अम्बाबाडी",
                    "पनीपेच",
                    "टीबी अस्पताल",
                    "दूध मंडी",
                    "पित्तल फैक्ट्री",
                    "चाँदपोल"
            },
            {
                    "16",
                    "चाकसू",
                    "शीतला",
                    "यर्लीपुरा",
                    "शिवदासपुरा",
                    "नागलिया",
                    "बिलवा",
                    "गोनेर मोड",
                    "12 मील",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट"
            },
            {
                    "26",
                    "बगरू",
                    "धामी बालाजी",
                    "धामी कला मोड़",
                    "देवलिया",
                    "ठिकरिया मोड",
                    "बड़ के बालाजी",
                    "रामचंद्र पुरा",
                    "महापुरा",
                    "भांकरोटा ",
                    "केशूपूरा ",
                    "चौधरी नगर",
                    "कमला नेहरू नगर",
                    "धावास गिरधारीपुरा मोड",
                    "पावर हाउस (हीरापुरा)",
                    "हीरापुरा बाईपास",
                    "टैगोर नगर",
                    "DCM",
                    "किंग्स रोड निर्माण नगर",
                    "पुरानी चुंगी",
                    "सुशील पुरा",
                    "श्याम नगर",
                    "सोडाला",
                    "4 नं. डिस्पेंसरी",
                    "मजदूर नगर",
                    "सिविल लाइंस मेट्रो स्टेशन",
                    "सिविल लाइंस चौराहा",
                    "अजमेर पुलिया",
                    "हाथरोई",
                    "शालीमार",
                    "गवर्मेंट हॉस्टल",
                    "Gpo",
                    "MLA क्वार्टर्स",
                    "चाँदपोल"
            },
            {
                    "34",
                    "आनंदा मंगलम सिटी",
                    "रामपुरा फाटक",
                    "त्रिवेणी पुलिया",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "छोटी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "बड़ी चौपड़",
                    "चांदी की टकसाल",
                    "सुभाष चौक",
                    "जोरावर सिंह गेट",
                    "गोविंद नगर",
                    "रामगढ़ मोड़",
                    "नाई की थाड़ी",
                    "मान बाग",
                    "JDA कॉलोनी",
                    "जयसिंहपुरा खोर",
            },
            {
                    "30",
                    "बड़ी चौपड़",
                    "चांदी की टकसाल",
                    "सुभाष चौक",
                    "जोरावर सिंह गेट",
                    "गोविंद नगर",
                    "रामगढ़ मोड़",
                    "नाई की थाड़ी",
                    "कुंडा",
                    "रामगढ़"
            },
            {
                    "32",
                    "भांकरोटा ",
                    "केशूपूरा ",
                    "कमला नेहरू नगर",
                    "न्यू ट्रांसपोर्ट नगर",
                    "गंगा जमुना पेट्रोल पंप",
                    "गंगा जमुना",
                    "गुर्जर की थड़ी",
                    "महेश नगर",
                    "सूर्या नगर",
                    "त्रिवेणी नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "पागलखाना",
                    "ट्रांसपोर्ट नगर मोड़",
                    "ट्रांसपोर्ट नगर",
                    "खनिया",
                    "प्रेम नगर पुलिया",
                    "बाल्टी फैक्ट्री",
                    "पाल्डीमीना",
                    "बावड़ी",
                    "पुरानी चुंगी (बावड़ी)",
                    "हनुमान जी मंदिर",
                    "बगराना",
                    "कानोता",
                    "हीरावाला",
                    "गीलावाला",
                    "नायला"
            },
            {
                    "25B",
                    "तीतरिया",
                    "चांदलाई",
                    "शिवदासपुरा",
                    "गोनेर मोड",
                    "12 मील",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट"
            },
            {
                    "25A",
                    "पदमपुरा",
                    "शिवदासपुरा",
                    "गोनेर मोड",
                    "12 मील",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट"
            },
            {
                    "AC7",
                    "चौमू पुलिया",
                    "खासा कोठी",
                    "जयपुर रेलवे जंक्शन",
                    "पोलो विक्ट्री",
                    "सिंधी कैंप",
                    "अजमेरी गेट",
                    "महारानी कॉलेज",
                    "SMS हॉस्पिटल",
                    "नारायण सिंह सर्किल",
                    "रामबाग सर्किल",
                    "लाल कोठी",
                    "गांधीनगर मोड़",
                    "गांधी सर्किल",
                    "RTO ऑफिस",
                    "बाईजी की कोठी",
                    "केवी नं. 3",
                    "अपैक्स सर्कल",
                    "हल्दिया गार्डन",
                    "डाक कॉलोनी/बालाजी मोड़",
                    "मॉडल टाउन",
                    "जगतपुरा रेलवे स्टेशन",
                    "CBI फाटक",
                    "इंदिरा गांधी नगर",
                    "जगतपुरा"
            },
            {
                    "AC8",
                    "मुंडिया रामसर",
                    "सिवार मोड़",
                    "मीणावाला",
                    "पंच्यावाला पुलिया",
                    "हनुमान नगर",
                    "खातीपुरा",
                    "हसनपुरा",
                    "खासा कोठी",
                    "चाँदपोल",
                    "छोटी चौपड़"
            },
            {
                    "27/27A",
                    "वाटिका",
                    "भट्टा वाला",
                    "कालबाड़ा",
                    "आशा वाला",
                    "12 मील",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "दत्ती वड़ा",
                    "कल्याण नगर/राम मंदिर",
                    "तारों की कूट/सूर्य नगर",
                    "सिटी प्लेक्स",
                    "जवाहर सर्किल",
                    "फोर्टिस हॉस्पिटल",
                    "जेनपैक्ट",
                    "दुर्गापुरा",
                    "महावीर नगर",
                    "महावीर नगर मोड़",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "राम निवास गार्डन पार्किंग",
                    "सांगानेरी गेट",
                    "घाट गेट",
                    "ट्रांसपोर्ट नगर",
                    "खनिया",
                    "मच्छ की पिपली",
                    "लुनियावास",
                    "दाँतली",
                    "सिरोली",
                    "गोनेर"
            },
            {
                    "24",
                    "कालवाड़",
                    "राम कुटिया",
                    "गोविंदपुरा",
                    "रावण गेट",
                    "जोशी मार्ग",
                    "झोटवाड़ा कांटा",
                    "बोरिंग रोड",
                    "झोटवाड़ा पंखा",
                    "लता सिनेमा",
                    "चौमू पुलिया",
                    "अम्बाबाडी",
                    "पनीपेच",
                    "टीबी अस्पताल",
                    "दूध मंडी",
                    "पित्तल फैक्ट्री",
                    "पारीक कॉलेज",
                    "मयंक सिनेमा",
                    "चाँदपोल"
            },
            {
                    "10A city Bus",
                    "जयपुर रेलवे जंक्शन",
                    "पोलो विक्ट्री",
                    "सिंधी कैंप",
                    "मयंक सिनेमा",
                    "चाँदपोल",
                    "छोटी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "बड़ी चौपड़",
                    "रामगंज चौपड़",
                    "सूरजपोल",
                    "गलता गेट",
                    "लाल मस्जिद चौराहा",
                    "खोले के हनुमान जी"
            },
            {
                    "7 city Bus",
                    "खातीपुरा रेलवे स्टेशन",
                    "इंद्रा गांधी नगर सेक्टर-1",
                    "इंद्रा गांधी नगर सेक्टर-2",
                    "इंद्रा गांधी नगर सेक्टर-3",
                    "इंद्रा गांधी नगर सेक्टर-4",
                    "इंद्रा गांधी नगर सेक्टर-5",
                    "इंद्रा गांधी नगर सेक्टर-6",
                    "CBI फाटक",
                    "जगतपुरा फाटक",
                    "जगतपुरा रेलवे स्टेशन",
                    "मॉडल टाउन",
                    "डाक कॉलोनी/बालाजी मोड़",
                    "मालवीय नगर सेक्टर-3",
                    "मालवीय नगर सेक्टर-2(सत्कार)",
                    "कैलगरी हॉस्पिटल मोड",
                    "हरि मार्ग",
                    "प्रधान मार्ग",
                    "गौरव टावर पुलिया (GT)",
                    "सरस डेयरी",
                    "MNIT",
                    "जलधारा/शिक्षा संकुल",
                    "कॉमर्स कॉलेज",
                    "गांधीनगर स्टेशन",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "नेहरू प्लेस",
                    "गांधीनगर मोड़",
                    "लाल कोठी",
                    "रामबाग सर्किल",
                    "नारायण सिंह सर्किल",
                    "SMS हॉस्पिटल",
                    "महारानी कॉलेज",
                    "अजमेरी गेट",
                    "पांच बत्ती",
                    "Gpo",
                    "पोलो विक्ट्री",
                    "जयपुर रेलवे जंक्शन",
                    "चिंकारा कैंटीन",
                    "पनीपेच",
                    "अम्बाबाडी",
                    "चौमू पुलिया",
                    "लता सिनेमा",
                    "झोटवाड़ा पंखा",
                    "विष्णु मार्ग",
                    "बोरिंग रोड",
                    "झोटवाड़ा कांटा",
                    "जोशी मार्ग",
                    "रावण गेट",
                    "गोविंदपुरा"
            },
            {
                    "13 city Bus",
                    "खातीपुरा रेलवे स्टेशन",
                    "लुनियावास",
                    "मच्छ की पिपली",
                    "खनिया",
                    "चुलगिरी",
                    "ट्रांसपोर्ट नगर",
                    "घाट गेट",
                    "सांगानेरी गेट",
                    "त्रिमूर्ति सर्कल",
                    "मोती डूंगरी मंदिर सर्कल",
                    "JDA सर्किल",
                    "रामबाग सर्किल",
                    "लाल कोठी",
                    "गांधीनगर मोड़",
                    "नेहरू प्लेस",
                    "लक्ष्मी मंदिर",
                    "टोंक फाटक",
                    "सईद का गट्टा",
                    "गोपालपुरा मोड़",
                    "महावीर नगर मोड़",
                    "महावीर नगर",
                    "दुर्गापुरा",
                    "जेनपैक्ट",
                    "फोर्टिस हॉस्पिटल",
                    "जवाहर सर्किल",
                    "सिटी प्लेक्स",
                    "तारों की कूट/सूर्य नगर",
                    "कल्याण नगर/राम मंदिर",
                    "दत्ती वड़ा",
                    "सांगानेर थाना",
                    "कुंदन नगर",
                    "सांगानेर स्टेडियम",
                    "सांगानेर टाउन",
                    "मुहाना मंडी",
                    "मुहाना गाँव"
            },
            {
                    "1 city Bus",
                    "खोले के हनुमान जी",
                    "लाल मस्जिद चौराहा",
                    "गलता गेट",
                    "सूरजपोल",
                    "रामगंज चौपड़",
                    "बड़ी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "छोटी चौपड़",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "सिंधी कैंप",
                    "पोलो विक्ट्री",
                    "जयपुर रेलवे जंक्शन",
                    "हसनपुरा",
                    "4 नं. डिस्पेंसरी",
                    "हटवाड़ा",
                    "Esi हॉस्पिटल",
                    "सोडाला",
                    "श्याम नगर मेट्रो स्टेशन",
                    "लज़ीज़ होटल",
                    "देवी नगर",
                    "संजीवनी हॉस्पिटल",
                    "विवेक विहार मेट्रो स्टेशन",
                    "गुर्जर की थड़ी",
                    "गंगा जमुना",
                    "गंगा जमुना पेट्रोल पंप",
                    "कावेरी पथ",
                    "स्वर्ण पथ",
                    "वरुण पथ",
                    "किरण पथ",
                    "रजत पथ",
                    "हीरा पथ",
                    "अकबर VT रोड",
                    "अरावली मार्ग",
                    "केवी नं. 5",
                    "मार्केट रोड",
                    "पटेल मार्ग",
                    "मीरा मार्ग",
                    "विजय पथ",
                    "थडी मार्केट",
                    "अग्रवाल फार्म",
                    "सुरेश नगर मोड़",
                    "Ricco",
                    "Ricco चौराहा",
                    "सांगानेर पेट्रोल पंप",
                    "सांगानेर स्टेडियम",
                    "कुंदन नगर",
                    "सांगानेर थाना",
                    "शिव नगर",
                    "गौशाला",
                    "प्रताप नगर सेक्टर-3",
                    "प्रताप नगर सेक्टर-5",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-8",
                    "कुंभा मार्ग"
            },
            {
                    "2 city Bus",
                    "जयपुर रेलवे जंक्शन",
                    "पोलो विक्ट्री",
                    "सिंधी कैंप",
                    "मयंक सिनेमा",
                    "चाँदपोल",
                    "छोटी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "बड़ी चौपड़",
                    "चांदी की टकसाल",
                    "सुभाष चौक",
                    "जोरावर सिंह गेट",
                    "गोविंद नगर",
                    "रामगढ़ मोड़",
                    "मान बाग",
                    "सदवा मोड",
                    "मुस्लिम यूनिवर्सिटी"
            },
            {
                    "36 city Bus",
                    "जगतपुरा फाटक",
                    "CBI फाटक",
                    "न्यू RTO ऑफिस",
                    "JNU SADTM कैंपस",
                    "JNU Silas कैंपस",
                    "JNU Main कैंपस",
                    "JNU Silas कैंपस",
                    "JNU SADTM कैंपस",
                    "न्यू RTO ऑफिस",
                    "खो-नागोरियन",
                    "रहीम नगर",
                    "करीम नगर",
                    "मदीना नगर",
                    "खनिया",
                    "चुलगिरी",
                    "ट्रांसपोर्ट नगर",
                    "घाट गेट"
            },
            {
                    "5 city Bus",
                    "जयपुर रेलवे जंक्शन",
                    "पोलो विक्ट्री",
                    "Gpo",
                    "पांच बत्ती",
                    "अजमेरी गेट",
                    "राम निवास गार्डन पार्किंग",
                    "सांगानेरी गेट",
                    "घाट गेट",
                    "नायला हाउस",
                    "मोती डूंगरी मंदिर सर्कल",
                    "जवाहर नगर"
            },
            {
                    "15 city Bus",
                    "टोडी",
                    "हरमाडा",
                    "निंदड मोड",
                    "जोड़ला",
                    "वी के आई रोड नंबर 14",
                    "वी के आई रोड नंबर 12",
                    "वी के आई रोड नंबर 9",
                    "वी के आई रोड नंबर 8",
                    "वी के आई रोड नंबर 5",
                    "वी के आई रोड नंबर 1",
                    "अल्का सिनेमा",
                    "विद्याधर नगर थाना",
                    "मंदिर मोड़",
                    "विद्याधर नगर",
                    "राम नगर (विद्याधर नगर)",
                    "हरि नगर",
                    "कांवटिया सर्कल",
                    "सुभाष कॉलोनी",
                    "शास्त्री नगर सर्किल",
                    "मोचियों की छबील",
                    "शास्त्री नगर थाना",
                    "पित्तल फैक्ट्री",
                    "पारीक कॉलेज",
                    "मयंक सिनेमा",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "सिंधी कैंप",
                    "पोलो विक्ट्री",
                    "Gpo",
                    "पांच बत्ती",
                    "अजमेरी गेट",
                    "राम निवास गार्डन पार्किंग",
                    "सांगानेरी गेट",
                    "घाट गेट",
                    "ट्रांसपोर्ट नगर",
                    "सिसोदिया रानी उद्यान",
                    "जामडोली",
                    "बावड़ी",
                    "पुरानी चुंगी (बावड़ी)",
                    "हनुमान जी मंदिर",
                    "बाबराना",
                    "कानोता"
            },
            {
                    "16 city Bus",
                    "गलता गेट",
                    "सूरजपोल",
                    "रामगंज चौपड़",
                    "बड़ी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "छोटी चौपड़",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "पारीक कॉलेज",
                    "पित्तल फैक्ट्री",
                    "शास्त्री नगर थाना",
                    "मोचियों की छबील",
                    "शास्त्री नगर सर्किल",
                    "नया खेड़ा",
                    "खेतान हॉस्पिटल",
                    "विद्याधर नगर बस डिपो",
                    "अल्का सिनेमा",
                    "वी के आई रोड नंबर 1",
                    "शिवाजी कॉलेज",
                    "मुरलीपुरा थाना",
                    "मुरलीपुरा सर्कल",
                    "केडिया पैलेस",
                    "दादी का फाटक"
            },
            {
                    "17 city Bus",
                    "ज्ञान विहार कॉलेज",
                    "7 नं. चौराहा",
                    "जगतपुरा फाटक",
                    "कच्ची बस्ती",
                    "मालवीय नगर सेक्टर-15",
                    "मालवीय नगर सेक्टर-11",
                    "मालवीय नगर सेक्टर-4",
                    "Danapani",
                    "प्रधान मार्ग",
                    "गौरव टावर पुलिया (GT)",
                    "सरस डेयरी",
                    "MNIT",
                    "भास्कर",
                    "बजाज नगर एन्क्लेव",
                    "गांधीनगर स्टेशन",
                    "कॉमर्स कॉलेज",
                    "राजस्थान कॉलेज",
                    "कनोडिया यूनिवर्सिटी",
                    "राजस्थान यूनिवर्सिटी",
                    "JDA सर्किल",
                    "रामबाग सर्किल",
                    "हाई कोर्ट सर्कल",
                    "सचिवालय",
                    "तिलक मार्ग",
                    "बगड़िया भवन",
                    "चौमू हाउस",
                    "गवर्मेंट प्रेस",
                    "गवर्मेंट हॉस्टल",
                    "जयपुर रेलवे जंक्शन",
                    "पोलो विक्ट्री",
                    "सिंधी कैंप",
                    "मयंक सिनेमा",
                    "चाँदपोल",
                    "पावर हाउस",
                    "पित्तल फैक्ट्री",
                    "शास्त्री नगर थाना",
                    "मोचियों की छबील",
                    "शास्त्री नगर सर्किल",
                    "सुभाष कॉलोनी",
                    "कांवटिया सर्कल",
                    "भट्टा बस्ती"
            },
            {
                    "32 city Bus",
                    "कच्ची बस्ती",
                    "मालवीय नगर सेक्टर-5",
                    "मालवीय नगर सेक्टर-6",
                    "मालवीय नगर सेक्टर-7",
                    "मालवीय नगर सेक्टर-9",
                    "मालवीय नगर सेक्टर",
                    "मालवीय नगर सेक्टर-13",
                    "गौरव टॉवर (GT)",
                    "जयपुरिया हॉस्पिटल",
                    "गोपालपुरा मोड़",
                    "सईद का गट्टा",
                    "टोंक फाटक",
                    "लक्ष्मी मंदिर",
                    "सहकारी भवन/22 गोदाम",
                    "हाई कोर्ट सर्कल",
                    "सचिवालय",
                    "तिलक मार्ग",
                    "बगड़िया भवन",
                    "चौमू हाउस",
                    "गवर्मेंट प्रेस",
                    "गवर्मेंट हॉस्टल",
                    "Gpo",
                    "MLA क्वार्टर्स",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "पारीक कॉलेज",
                    "पित्तल फैक्ट्री",
                    "दूध मंडी",
                    "टीबी अस्पताल",
                    "पनीपेच",
                    "अम्बाबाडी",
                    "चौमू पुलिया",
                    "लता सिनेमा",
                    "झोटवाड़ा पंखा",
                    "विष्णु मार्ग",
                    "बोरिंग रोड",
                    "झोटवाड़ा कांटा",
                    "जोशी मार्ग",
                    "रावण गेट",
                    "गोविंदपुरा"
            },
            {
                    "28 city Bus",
                    "सूरजपोल",
                    "गलता गेट",
                    "ट्रांसपोर्ट नगर",
                    "घाट गेट",
                    "सांगानेरी गेट",
                    "राम निवास गार्डन पार्किंग",
                    "अजमेरी गेट",
                    "महारानी कॉलेज",
                    "SMS हॉस्पिटल",
                    "नारायण सिंह सर्किल",
                    "रामबाग सर्किल",
                    "लाल कोठी",
                    "गांधीनगर मोड़",
                    "नेहरू प्लेस",
                    "लक्ष्मी मंदिर",
                    "टोंक फाटक",
                    "सईद का गट्टा",
                    "गोपालपुरा मोड़",
                    "महावीर नगर मोड़",
                    "त्रिवेणी नगर",
                    "महेश नगर मोड़",
                    "रिद्धि सिद्धि",
                    "महेश नगर",
                    "गुर्जर की थड़ी",
                    "गंगा जमुना",
                    "गंगा जमुना पेट्रोल पंप",
                    "कावेरी पथ",
                    "स्वर्ण पथ",
                    "वरुण पथ",
                    "किरण पथ",
                    "रजत पथ",
                    "हीरा पथ",
                    "अकबर VT रोड",
                    "अरावली मार्ग",
                    "केवी नं. 5",
                    "मार्केट रोड",
                    "पटेल मार्ग",
                    "मीरा मार्ग",
                    "विजय पथ",
                    "थडी मार्केट",
                    "अग्रवाल फार्म",
                    "सुरेश नगर मोड़",
                    "Ricco",
                    "सांगानेर टाउन",
                    "मुहाना मंडी"
            },
            {
                    "55 city Bus",
                    "सीकर रोड",
                    "वी के आई रोड नंबर 9",
                    "वी के आई रोड नंबर 8",
                    "वी के आई रोड नंबर 5",
                    "वी के आई रोड नंबर 1",
                    "अल्का सिनेमा",
                    "विद्याधर नगर बस डिपो",
                    "खेतान हॉस्पिटल",
                    "डहर के बालाजी",
                    "भवानी निकेतन",
                    "चौमू पुलिया",
                    "अम्बाबाडी",
                    "पनीपेच",
                    "चिंकारा कैंटीन",
                    "जयपुर रेलवे जंक्शन",
                    "गवर्मेंट हॉस्टल",
                    "Gpo",
                    "पांच बत्ती",
                    "अजमेरी गेट",
                    "महारानी कॉलेज",
                    "SMS हॉस्पिटल",
                    "नारायण सिंह सर्किल",
                    "रामबाग सर्किल",
                    "लाल कोठी",
                    "गांधीनगर मोड़",
                    "नेहरू प्लेस",
                    "लक्ष्मी मंदिर",
                    "टोंक फाटक",
                    "सईद का गट्टा",
                    "गोपालपुरा मोड़",
                    "महावीर नगर मोड़",
                    "महावीर नगर",
                    "दुर्गापुरा",
                    "जेनपैक्ट",
                    "फोर्टिस हॉस्पिटल",
                    "जवाहर सर्किल",
                    "सिटी प्लेक्स",
                    "तारों की कूट/सूर्य नगर",
                    "कल्याण नगर/राम मंदिर",
                    "दत्ती वड़ा",
                    "सांगानेर थाना",
                    "शिव नगर",
                    "गौशाला",
                    "प्रताप नगर सेक्टर-3",
                    "प्रताप नगर सेक्टर-5",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-8",
                    "कुंभा मार्ग",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "इंडिया गेट",
                    "12 मील",
                    "गोनेर मोड",
                    "BOSCH लिमिटेड",
                    "महात्मा गांधी हॉस्पिटल"
            },
            {
                    "21 city Bus",
                    "परसोतवाड़ा",
                    "रामगढ़ मोड़",
                    "ईदगाह",
                    "गलता गेट",
                    "ट्रांसपोर्ट नगर",
                    "घाट गेट",
                    "सांगानेरी गेट",
                    "राम निवास गार्डन पार्किंग",
                    "अजमेरी गेट",
                    "महारानी कॉलेज",
                    "SMS हॉस्पिटल",
                    "नारायण सिंह सर्किल",
                    "रामबाग सर्किल",
                    "हाई कोर्ट सर्कल",
                    "सहकारी भवन/22 गोदाम",
                    "राम मंदिर",
                    "नंदपुरी",
                    "राम नगर (सोडाला)",
                    "सोडाला मेट्रो",
                    "सोडाला",
                    "श्याम नगर",
                    "सुशील पुरा",
                    "पुरानी चुंगी",
                    "प्रिंस रोड",
                    "किंग्स रोड निर्माण नगर",
                    "करणी पैलेस",
                    "पंच्यावाला"
            },
            {
                    "29 city Bus",
                    "कुकस",
                    "चेक पोस्ट आमेर",
                    "कुंडा",
                    "नारद का बाग",
                    "मोरता",
                    "आमेर स्टेडियम",
                    "आमेर",
                    "आमेर का किला",
                    "मावठा",
                    "एयरफोर्स ऑफिस ",
                    "जल महल",
                    "शाहपुरा बाग",
                    "रामगढ़ मोड़",
                    "गोविंद नगर",
                    "जोरावर सिंह गेट",
                    "सुभाष चौक",
                    "चांदी की टकसाल",
                    "बड़ी चौपड़",
                    "सांगानेरी गेट",
                    "अजमेरी गेट",
                    "महारानी कॉलेज",
                    "SMS हॉस्पिटल",
                    "नारायण सिंह सर्किल",
                    "रामबाग सर्किल",
                    "हाई कोर्ट सर्कल",
                    "सहकारी भवन/22 गोदाम",
                    "राम मंदिर",
                    "नंदपुरी",
                    "राम नगर (सोडाला)",
                    "सोडाला मेट्रो",
                    "सोडाला",
                    "श्याम नगर",
                    "सुशील पुरा",
                    "पुरानी चुंगी",
                    "किंग्स रोड निर्माण नगर",
                    "DCM",
                    "टैगोर नगर",
                    "हीरापुरा बाईपास",
                    "पावर हाउस (हीरापुरा)",
                    "धावास गिरधारीपुरा मोड",
                    "कमला नेहरू नगर",
                    "चौधरी नगर",
                    "केशूपूरा ",
                    "भांकरोटा ",
                    "महापुरा"
            },
            {
                    "22 city Bus",
                    "जयसिंहपुरा खोर",
                    "रामगढ़ मोड़",
                    "गोविंद नगर",
                    "जोरावर सिंह गेट",
                    "सुभाष चौक",
                    "चांदी की टकसाल",
                    "बड़ी चौपड़",
                    "सांगानेरी गेट",
                    "अजमेरी गेट",
                    "महारानी कॉलेज",
                    "SMS हॉस्पिटल",
                    "नारायण सिंह सर्किल",
                    "रामबाग सर्किल",
                    "हाई कोर्ट सर्कल",
                    "इमली फाटक",
                    "करतारपुरा फाटक",
                    "महेश नगर फाटक",
                    "गुर्जर की थड़ी",
                    "गंगा जमुना",
                    "गंगा जमुना पेट्रोल पंप",
                    "कावेरी पथ",
                    "स्वर्ण पथ",
                    "वरुण पथ",
                    "किरण पथ",
                    "रजत पथ",
                    "हीरा पथ",
                    "अकबर VT रोड",
                    "अरावली मार्ग",
                    "केवी नं. 5",
                    "मार्केट रोड",
                    "पटेल मार्ग",
                    "मीरा मार्ग",
                    "विजय पथ",
                    "थडी मार्केट",
                    "अग्रवाल फार्म",
                    "सुरेश नगर मोड़",
                    "Ricco",
                    "Ricco चौराहा",
                    "सांगानेर पेट्रोल पंप",
                    "सांगानेर स्टेडियम",
                    "कुंदन नगर",
                    "सांगानेर थाना",
                    "शिव नगर",
                    "गौशाला",
                    "प्रताप नगर सेक्टर-3",
                    "प्रताप नगर सेक्टर-5",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-8",
                    "कुंभा मार्ग",
                    "प्रताप नगर RSRTC बस स्टैंड"
            },
            {
                    "9B city Bus",
                    "नारदपुरा मोड",
                    "कुंडा",
                    "नारद का बाग",
                    "मोरता",
                    "आमेर स्टेडियम",
                    "आमेर",
                    "आमेर का किला",
                    "मावठा",
                    "एयरफोर्स ऑफिस ",
                    "जल महल",
                    "शाहपुरा बाग",
                    "रामगढ़ मोड़",
                    "गोविंद नगर",
                    "जोरावर सिंह गेट",
                    "सुभाष चौक",
                    "चांदी की टकसाल",
                    "बड़ी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "छोटी चौपड़",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "सिंधी कैंप",
                    "पोलो विक्ट्री",
                    "जयपुर रेलवे जंक्शन"
            },
            {
                    "30 city Bus",
                    "पाल्डीमीना",
                    "बाल्टी फैक्ट्री",
                    "प्रेम नगर पुलिया",
                    "खनिया",
                    "ट्रांसपोर्ट नगर",
                    "गलता गेट",
                    "सूरजपोल",
                    "रामगंज चौपड़",
                    "बड़ी चौपड़",
                    "त्रिपोलिया बाज़ार",
                    "छोटी चौपड़",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "पारीक कॉलेज",
                    "पित्तल फैक्ट्री",
                    "दूध मंडी",
                    "टीबी अस्पताल",
                    "पनीपेच",
                    "अम्बाबाडी",
                    "चौमू पुलिया",
                    "लता सिनेमा",
                    "झोटवाड़ा पंखा",
                    "विष्णु मार्ग",
                    "बोरिंग रोड",
                    "झोटवाड़ा कांटा",
                    "शिवाजी नगर चौराहा",
                    "शालीमार फैक्ट्री",
                    "दादी का फाटक",
                    "नाड़ी का फाटक",
                    "श्याम नगर (बेनाड)",
                    "बालाजी कॉलेज",
                    "मोहन हॉस्पिटल",
                    "बेनाड",
                    "शारदा"
            },
            {
                    "11 city Bus",
                    "बिलवा",
                    "गोनेर मोड",
                    "12 मील",
                    "इंडिया गेट",
                    "प्रताप नगर RSRTC बस स्टैंड",
                    "कुंभा मार्ग",
                    "प्रताप नगर सेक्टर-8",
                    "हल्दीघाटी गेट",
                    "प्रताप नगर सेक्टर-5",
                    "प्रताप नगर सेक्टर-3",
                    "गौशाला",
                    "शिव नगर",
                    "सांगानेर थाना",
                    "कुंदन नगर",
                    "सांगानेर स्टेडियम",
                    "सांगानेर पेट्रोल पंप",
                    "Ricco चौराहा",
                    "Ricco",
                    "सुरेश नगर मोड़",
                    "अग्रवाल फार्म",
                    "थडी मार्केट",
                    "विजय पथ",
                    "मीरा मार्ग",
                    "पटेल मार्ग",
                    "मार्केट रोड",
                    "केवी नं. 5",
                    "अरावली मार्ग",
                    "अकबर VT रोड",
                    "हीरा पथ",
                    "रजत पथ",
                    "किरण पथ",
                    "वरुण पथ",
                    "स्वर्ण पथ",
                    "कावेरी पथ",
                    "गंगा जमुना पेट्रोल पंप",
                    "गंगा जमुना",
                    "गुर्जर की थड़ी",
                    "विवेक विहार मेट्रो स्टेशन",
                    "संजीवनी हॉस्पिटल",
                    "देवी नगर",
                    "लज़ीज़ होटल",
                    "श्याम नगर मेट्रो स्टेशन",
                    "सोडाला",
                    "Esi हॉस्पिटल",
                    "मजदूर नगर",
                    "सिविल लाइंस मेट्रो स्टेशन",
                    "सिविल लाइंस चौराहा",
                    "हाथरोई",
                    "शालीमार",
                    "गवर्मेंट हॉस्टल",
                    "Gpo",
                    "MLA क्वार्टर्स",
                    "चाँदपोल",
                    "मयंक सिनेमा",
                    "पारीक कॉलेज",
                    "पित्तल फैक्ट्री",
                    "दूध मंडी",
                    "टीबी अस्पताल",
                    "पनीपेच",
                    "अम्बाबाडी",
                    "चौमू पुलिया",
                    "भवानी निकेतन",
                    "डहर के बालाजी",
                    "खेतान हॉस्पिटल",
                    "विद्याधर नगर बस डिपो",
                    "अल्का सिनेमा",
                    "वी के आई रोड नंबर 1",
                    "शिवाजी कॉलेज",
                    "मुरलीपुरा थाना",
                    "मुरलीपुरा सर्कल",
                    "केडिया पैलेस",
                    "दादी का फाटक",
                    "नाड़ी का फाटक",
                    "श्याम नगर (बेनाड)",
                    "बालाजी कॉलेज",
                    "मोहन हॉस्पिटल",
                    "बेनाड"
            },
            {
                    "Metro",
                    "मानसरोवर मेट्रो स्टेशन",
                    "न्यू आतिश मार्केट मेट्रो स्टेशन",
                    "विवेक विहार मेट्रो स्टेशन",
                    "श्याम नगर मेट्रो स्टेशन",
                    "राम नगर (सोडाला)",
                    "सिविल लाइंस मेट्रो स्टेशन",
                    "जयपुर रेलवे जंक्शन",
                    "सिंधी कैंप",
                    "चाँदपोल",
                    "छोटी चौपड़",
                    "बड़ी चौपड़"
            }
    };

    String[][] KM = {
            {
                    "1",
                    "0",
                    "3.5",
                    "5.4",
                    "5.9",
                    "6.9",
                    "7.4",
                    "7.9",
                    "8.4",
                    "8.9",
                    "9.4",
                    "10.4",
                    "10.9",
                    "11.4",
                    "11.9",
                    "12.5",
                    "13",
                    "13.5",
                    "14",
                    "15",
                    "16",
                    "16.5",
                    "17",
                    "18.5",
                    "19.5",
                    "20.5",
                    "21.5",
                    "25.5",
                    "28"
            },
            {
                    "1A",
                    "0",
                    "2.9",
                    "3.4",
                    "4",
                    "5",
                    "5.5",
                    "5.8",
                    "6",
                    "6.5",
                    "7",
                    "7.5",
                    "8",
                    "8.5",
                    "9",
                    "9.5",
                    "10",
                    "11",
                    "12",
                    "13",
                    "14",
                    "18"
            },
            {
                    "3",
                    "0",
                    "1",
                    "1.5",
                    "3.3",
                    "4.4",
                    "5",
                    "5.3",
                    "5.7",
                    "6.5",
                    "7",
                    "7.3",
                    "7.8",
                    "8.4",
                    "8.8",
                    "9.5",
                    "10",
                    "11",
                    "12",
                    "12.5",
                    "13",
                    "13.5",
                    "14",
                    "15",
                    "15.5",
                    "16",
                    "16.5",
                    "17",
                    "17.5",
                    "17.8",
                    "18.2",
                    "18.5",
                    "18.9",
                    "19.5",
                    "20.8",
                    "21.5",
                    "22",
                    "22.6",
                    "23.4",
                    "24",
                    "26",
                    "27"
            },
            {
                    "3A",
                    "0",
                    "2.2",
                    "3.7",
                    "4",
                    "4.2",
                    "6.2",
                    "7.7",
                    "9",
                    "9.5",
                    "9.8",
                    "10",
                    "11",
                    "11.5",
                    "12",
                    "12.8",
                    "13",
                    "13.7",
                    "14",
                    "14.5",
                    "15",
                    "15.5",
                    "16",
                    "17",
                    "18",
                    "18.5",
                    "19",
                    "20"
            },
            {
                    "4A",
                    "0",
                    "1",
                    "3",
                    "4.8",
                    "7",
                    "8",
                    "9.5",
                    "13.5",
                    "14.9",
                    "16.3",
                    "18",
                    "19",
                    "19.7",
                    "25"
            },
            {
                    "6A",
                    "0",
                    "1",
                    "1.5",
                    "1.8",
                    "2.5",
                    "2.9",
                    "4.1",
                    "9",
                    "11",
                    "13.7",
                    "16",
                    "19",
                    "20",
                    "20.5",
                    "21.2",
                    "21.4",
                    "22.4",
                    "22.6",
                    "23.6",
                    "25.3",
                    "28.7",
                    "29.7",
                    "30.2",
                    "31",
                    "32.5",
                    "34.5",
                    "36",
                    "37",
                    "38",
                    "39",
                    "39.5",
                    "40",
                    "41",
                    "41.5",
                    "42",
                    "42.5",
                    "42.8",
                    "43",
                    "43.2",
                    "44"
            },
            {
                    "7",
                    "0",
                    "6",
                    "6.5",
                    "7.5",
                    "10.5",
                    "11",
                    "12",
                    "14.5",
                    "15.5",
                    "16",
                    "18",
                    "20",
                    "20.5",
                    "21",
                    "21.5",
                    "21.7",
                    "22.2",
                    "22.7",
                    "23.2",
                    "24",
                    "25",
                    "20.5",
                    "20.8",
                    "21.8",
                    "22.2",
                    "23",
                    "23.7",
                    "24"
            },
            {
                    "9A",
                    "0",
                    "1.5",
                    "2.6",
                    "3",
                    "6.2",
                    "6.5",
                    "7.2",
                    "8.7",
                    "9.2",
                    "9.8",
                    "10.5",
                    "10.7",
                    "11.3",
                    "11.8",
                    "12.5",
                    "13",
                    "14",
                    "14.7",
                    "15.2",
                    "16",
                    "18.7",
                    "19.3",
                    "20",
                    "20.3",
                    "21.3",
                    "23",
                    "23.3",
                    "23.8",
                    "24.7",
                    "25",
                    "27",
                    "27.5",
                    "28.8",
                    "29",
                    "29.2",
                    "29.7",
                    "30.8",
                    "31.3",
                    "32.3"
            },
            {
                    "10/10A/10B",
                    "0",
                    "0.9",
                    "1.3",
                    "2",
                    "2.3",
                    "2.6",
                    "3.2",
                    "4",
                    "4.8",
                    "5.3",
                    "5.3",
                    "6.3",
                    "6.8",
                    "7.4",
                    "8.2",
                    "9.5",
                    "9.9",
                    "10.9",
                    "11.5",
                    "11.7",
                    "12.2",
                    "12.8",
                    "13.6",
                    "13.8",
                    "14.8",
                    "15.6",
                    "17.2",
                    "17.5",
                    "18.5",
                    "20.8",
                    "22",
                    "23.5",
                    "24.4",
                    "25.2",
                    "26.6",
                    "30",
                    "33",
                    "33.7",
                    "34.2",
                    "35.2",
                    "36.5",
                    "37.8",
                    "38.2",
                    "38.5",
                    "38.8",
                    "40.3",
                    "41.8",
                    "43"
            },
            {
                    "11",
                    "0",
                    "4",
                    "7.6",
                    "9",
                    "11",
                    "11.6",
                    "13.8",
                    "14.8",
                    "15",
                    "15.2",
                    "16.4",
                    "16.9",
                    "17.4",
                    "17.9",
                    "20.4",
                    "21.9",
                    "22.4",
                    "23.9",
                    "25.4",
                    "26",
                    "27",
                    "27.4",
                    "27.7",
                    "28.7",
                    "29.5",
                    "30.2",
                    "31.2",
                    "32",
                    "32.7",
                    "32.9",
                    "33.4",
                    "33.9",
                    "34.5",
                    "35",
                    "36",
                    "37",
                    "37.6",
                    "38.2"
            },
            {
                    "AC1",
                    "0",
                    "0.5",
                    "1",
                    "1.3",
                    "1.6",
                    "2.3",
                    "2.9",
                    "3.5",
                    "4.5",
                    "4.8",
                    "5.2",
                    "6.4",
                    "6.9",
                    "7.6",
                    "8.6",
                    "9.0",
                    "10",
                    "10.2",
                    "10.9",
                    "11.5",
                    "11.9",
                    "12.7",
                    "13.7",
                    "14.7",
                    "15.3",
                    "16",
                    "18",
                    "19",
                    "19.8",
                    "20.4",
                    "21.2",
                    "22",
                    "23.5",
                    "24.5",
                    "25.2",
                    "25.7",
                    "28.7",
                    "29.1",
                    "29.4",
                    "30.4",
                    "30.8",
                    "31.2",
                    "36.6",
                    "39"
            },
            {
                    "AC2",
                    "0",
                    "0.8",
                    "1.8",
                    "3.8",
                    "4.2",
                    "5.2",
                    "7.2",
                    "9.2",
                    "9.4",
                    "9.8",
                    "10.4",
                    "10.9",
                    "11.2",
                    "11.6",
                    "12",
                    "12.5",
                    "13",
                    "13.6",
                    "14",
                    "14.7",
                    "15.7",
                    "16.2",
                    "16.5",
                    "18",
                    "19.5",
                    "20.2",
                    "20.5",
                    "21",
                    "22",
                    "22.2",
                    "22.9",
                    "23.4",
                    "24.9",
                    "24.5",
                    "25.5",
                    "26.5",
                    "27",
                    "27.7",
                    "29.7",
                    "30.7",
                    "31.2",
                    "32.6",
                    "33.6",
                    "34",
                    "34.4",
                    "35.4",
                    "36.4",
                    "37",
                    "38",
                    "39.5",
                    "40",
                    "41.5",
                    "42.3",
                    "42.9",
                    "43.7",
                    "44.5",
                    "45"
            },
            {
                    "14",
                    "0",
                    "5",
                    "5.5",
                    "6.2",
                    "7",
                    "10.5",
                    "12.5",
                    "12.9",
                    "13.2",
                    "13.6",
                    "13.9",
                    "14.5",
                    "18.5",
                    "22",
                    "23",
                    "26",
                    "27.2",
                    "29.2",
                    "32.2",
                    "33.2",
                    "34.2",
                    "35",
                    "35.5",
                    "33",
                    "33.7",
                    "34.7",
                    "35.4",
                    "36.6",
                    "37.2",
                    "37.8",
                    "38.2",
                    "40",
                    "41.2",
                    "43.6",
                    "48.6",
                    "49.5",
                    "50",
                    "52.4",
                    "53.4",
                    "57",
                    "57.5",
                    "58.2",
                    "63.5",
                    "67",
                    "71",
                    "73",
                    "74",
                    "75"
            },
            {
                    "15",
                    "0",
                    "3.3",
                    "6.3",
                    "9.8",
                    "16",
                    "20",
                    "24",
                    "26",
                    "27",
                    "29",
                    "30",
                    "30.6",
                    "31.2",
                    "31.7",
                    "32.3",
                    "33.8",
                    "34.8",
                    "35",
                    "35.8",
                    "36.8",
                    "37.8",
                    "38.3",
                    "38.8",
                    "39.8",
                    "40.8",
                    "41.3",
                    "42",
                    "43"
            },
            {
                    "16",
                    "0",
                    "0.5",
                    "11.4",
                    "16",
                    "20",
                    "26",
                    "29.5",
                    "30.1",
                    "32.4",
                    "34",
                    "34.2",
                    "34.6",
                    "35",
                    "35.3",
                    "35.6",
                    "36",
                    "36.5",
                    "37",
                    "37.5",
                    "38",
                    "38.5",
                    "39.2",
                    "40.2",
                    "40.5",
                    "40.8",
                    "42",
                    "43.5",
                    "44",
                    "44.6",
                    "45.3",
                    "46.3",
                    "46.5",
                    "47",
                    "47.5",
                    "48",
                    "48.5",
                    "49.5",
                    "50.5",
                    "51.2",
                    "52"
            },
            {
                    "26",
                    "0",
                    "3.5",
                    "4.5",
                    "7.2",
                    "9.4",
                    "10.8",
                    "12.8",
                    "14.8",
                    "17.3",
                    "19.2",
                    "19.4",
                    "19.6",
                    "21.6",
                    "22.2",
                    "23.2",
                    "24",
                    "24.8",
                    "25.5",
                    "26.2",
                    "27.2",
                    "29",
                    "32",
                    "32.9",
                    "33.9",
                    "34.4",
                    "35.4",
                    "36",
                    "36.8",
                    "37.5",
                    "38",
                    "38.5",
                    "39.5",
                    "41"
            },
            {
                    "34",
                    "0",
                    "1",
                    "7",
                    "8",
                    "8.5",
                    "9.1",
                    "10",
                    "10.2",
                    "10.7",
                    "11.2",
                    "11.6",
                    "12.3",
                    "13.3",
                    "14.3",
                    "14.8",
                    "15.5",
                    "16.5",
                    "17.5",
                    "18",
                    "18.6",
                    "19.3",
                    "20",
                    "20.5",
                    "21.6",
                    "22.6",
                    "25",
                    "28.5",
                    "30",
            },
            {
                    "30",
                    "0",
                    "1",
                    "1.5",
                    "2.5",
                    "3.5",
                    "5.5",
                    "14",
                    "17.5",
                    "30"
            },
            {
                    "32",
                    "0",
                    "9",
                    "10",
                    "12",
                    "15",
                    "15.5",
                    "18.2",
                    "21.5",
                    "22.6",
                    "24",
                    "26",
                    "26.5",
                    "27",
                    "28",
                    "28.2",
                    "28.7",
                    "29.2",
                    "29.7",
                    "30.2",
                    "31.2",
                    "32.2",
                    "32.7",
                    "33.4",
                    "36.7",
                    "37.7",
                    "40",
                    "44",
                    "44.9",
                    "45.3",
                    "48.2",
                    "49.7",
                    "52",
                    "53",
                    "56",
                    "60",
                    "63",
                    "67",
                    "70"
            },
            //need change
            {
                    "25B",
                    "0",
                    "5.9",
                    "9",
                    "18",
                    "18.5",
                    "20.8",
                    "22.8",
                    "23",
                    "23.3",
                    "23.7",
                    "24.2",
                    "24.5",
                    "25",
                    "25.7",
                    "26.5",
                    "26.8",
                    "27.3",
                    "27.8",
                    "28.4",
                    "29.4",
                    "29.7",
                    "30",
                    "32",
                    "34",
                    "35",
                    "36.5",
                    "37",
                    "37.6",
                    "37.8",
                    "38.4",
                    "38.9",
                    "39.4",
                    "40.4",
                    "41.4",
                    "42.4",
                    "43",
                    "44"
            },
            {
                    "25A",
                    "0",
                    "6",
                    "16",
                    "16.5",
                    "18.8",
                    "20.8",
                    "21.2",
                    "21.5",
                    "22",
                    "22.3",
                    "22.6",
                    "23.2",
                    "23.5",
                    "24",
                    "24.3",
                    "24.8",
                    "25.4",
                    "26",
                    "27",
                    "27.3",
                    "27.5",
                    "29.5",
                    "30.5",
                    "31.5",
                    "33.5",
                    "34",
                    "35",
                    "35.2",
                    "35.7",
                    "38.2",
                    "39",
                    "40",
                    "41",
                    "42",
                    "42.5",
                    "43"
            },

            {
                    "AC7",
                    "0",
                    "0",
                    "5",
                    "6",
                    "6.7",
                    "10.4",
                    "11",
                    "11.5",
                    "12.5",
                    "13.5",
                    "14",
                    "14.5",
                    "15.5",
                    "17",
                    "17.7",
                    "18.9",
                    "21.4",
                    "22",
                    "22.6",
                    "23.4",
                    "24.6",
                    "27.2",
                    "30.9",
                    "34"
            },
            {
                    "AC8",
                    "0",
                    "3",
                    "10",
                    "12.2",
                    "15",
                    "16.8",
                    "21.8",
                    "24.8",
                    "27.7",
                    "29"
            },
            {
                    "27/27A",
                    "0",
                    "1",
                    "2.5",
                    "4",
                    "7.4",
                    "9.7",
                    "11",
                    "11.3",
                    "11.7",
                    "12.2",
                    "12.6",
                    "13",
                    "13.5",
                    "14",
                    "14.5",
                    "14.9",
                    "15.2",
                    "15.5",
                    "16",
                    "17",
                    "17.3",
                    "17.5",
                    "19",
                    "20",
                    "20.8",
                    "21.8",
                    "22.4",
                    "23.4",
                    "23.6",
                    "24",
                    "24.5",
                    "25",
                    "26",
                    "27",
                    "28",
                    "28.6",
                    "29.4",
                    "30",
                    "32",
                    "33.5",
                    "36",
                    "41",
                    "43",
                    "45.9",
                    "53",
                    "55",
                    "60"
            },
            {
                    "24",
                    "0",
                    "6",
                    "12",
                    "14",
                    "15.6",
                    "16.3",
                    "16.9",
                    "17.5",
                    "18.5",
                    "20",
                    "20.6",
                    "21.6",
                    "22.6",
                    "23",
                    "24",
                    "25",
                    "25.5",
                    "26"
            },
            {
                    "10A",
                    "0",
                    "0.8",
                    "1",
                    "1.5",
                    "2",
                    "3.2",
                    "3.6",
                    "3.9",
                    "4.8",
                    "5.8",
                    "6.2",
                    "8.1",
                    "8.6"
            },
            {
                    "7 city Bus",
                    "0",
                    "1",
                    "2",
                    "2.4",
                    "2.8",
                    "3",
                    "3.4",
                    "4.1",
                    "5.7",
                    "6",
                    "6.7",
                    "7.6",
                    "8.5",
                    "9.2",
                    "9.8",
                    "10.4",
                    "10.9",
                    "11.4",
                    "11.8",
                    "12.1",
                    "12.6",
                    "13.5",
                    "13.8",
                    "14.6",
                    "14.9",
                    "15.3",
                    "15.6",
                    "16.1",
                    "16.8",
                    "17.2",
                    "18",
                    "18.6",
                    "19.1",
                    "19.9",
                    "20.4",
                    "21.4",
                    "22.2",
                    "23.7",
                    "24.4",
                    "25.4",
                    "25.7",
                    "27",
                    "27.5",
                    "27.7",
                    "28.6",
                    "28.8",
                    "29.5",
                    "30.4",
                    "33.4"
            },
            {"13 city Bus",
                    "0", "6", "8", "11.2", "13", "14.5", "16", "16.8", "19", "19.3", "19.8", "20.4", "21.1", "21.6",
                    "21.9", "22.3", "22.6", "23.5", "24.1", "24.5", "25.3", "25.6", "26.7", "27", "27.7", "28.3", "28.6", "29.2",
                    "29.9", "30.6", "31.1", "31.9", "35", "40", "43"
            },
            {"1 city Bus",
                    "0", "0.5", "2.4", "2.8", "3.8", "4.7", "5", "5.4", "6.6", "7.1", "7.6", "7.8", "8.6", "10.6",
                    "11.7", "11.8", "12", "12.3", "12.8", "13.2", "13.3", "13.7", "14", "14.7", "15.3", "15.8", "16.7", "17.1",
                    "17.4", "17.6", "17.9", "18.2", "18.6", "18.9", "19.2", "19.5", "19.7", "19.9", "20.3", "20.7", "21", "21.7",
                    "22.3", "22.7", "24", "24.2", "25", "25.5", "25.9", "26.6", "26.7", "27.2", "27.6", "28", "28.4"
            },
            {"2 city Bus",
                    "0", "0.8", "1", "1.5", "2", "3.2", "3.6", "3.9", "4.6", "4.9", "5.7", "6.3", "6.8", "9",
                    "10", "12"
            },
            {"36 city Bus",
                    "0", "1.6", "3.1", "3.5", "4.5", "5.7", "6.9", "7.9", "8.3", "9", "9.7", "10.5", "12", "14",
                    "15", "17", "18.5"
            },
            {"5 city Bus",
                    "0", "0.8", "1.8", "2.3", "3.1", "3.5", "3.8",
                    "4.6", "6.6", "7.9", "11.2"
            },
            {"15 city Bus",
                    "0", "1", "1.9", "2.8", "3.6", "4.1", "4.5", "4.9", "5.5", "6.5", "7", "7.4", "8.1", "8.5",
                    "9.4", "9.6", "10.2", "10.7", "11", "11.5", "12", "12.5", "12.9", "13.7", "14.6", "15.1", "15.6", "15.8",
                    "16.8", "17.3", "18.1", "18.5", "18.8", "19.6", "21.1", "24.8", "28.8", "43.8", "44.2", "45.8", "46.8", "48.1"
            },
            {"16 city Bus",
                    "0", "0.4", "1.4", "2.3", "2.6", "3", "4.2", "4.7", "5.5", "5.9", "6.4", "6.9", "7.4", "10",
                    "12", "12.5", "12.8", "13.3", "15", "15.5", "16.5", "17.5", "18.5"
            },
            {"17 city Bus",
                    "0", "2", "3.1", "4", "6.9", "7.3", "7.8", "8.1", "8.3", "8.8", "9.2", "9.5", "10", "10.9",
                    "11.2", "12.6", "12.9", "13.5", "13.9", "14.3", "14.9", "15.9", "16.4", "17", "17.5", "20.9", "23.1", "24.7",
                    "25.9", "26.7", "26.9", "27.4", "27.9", "28.7", "29.3", "33.8", "34.3", "34.8", "35.1", "35.6", "36.3"
            },
            {"32 city Bus",
                    "0", "2", "2.4", "2.7", "3", "3.1", "3.4", "3.6", "4.8", "6", "6.6", "7.5", "7.8", "10.3",
                    "10.9", "11.4", "12", "12.5", "15.9", "18.1", "19.7", "20", "20.2", "21.2", "22.1", "22.9", "23.3", "24.1",
                    "24.1", "24.8", "25.3", "26.3", "27.6", "28.1", "28.3", "29.2", "29.4", "30.1", "31", "34"
            },
            {"28 city Bus",
                    "0", "0.4", "1.9", "3.4", "4.2", "4.5", "4.9", "5.4", "6", "6.8", "7.2", "7.9", "8.4", "8.7",
                    "9.1", "9.4", "10.3", "10.9", "11.3", "13.1", "13.4", "13.8", "14.2", "14.9", "15.8", "16.3", "16.6", "17",
                    "17.4", "17.6", "17.8", "18.1", "18.4", "18.7", "19.1", "19.4", "19.7", "19.9", "20.2", "20.6", "21.5", "22.2",
                    "22.8", "23.3", "28.3"
            },
            {"55 city Bus",
                    "0", "0.5", "0.9", "1.5", "2.5", "3", "3.3", "3.8", "5.2", "5.2", "6", "7", "7.5", "8.2",
                    "9.7", "11.2", "11.5", "12", "12.8", "13.3", "13.9", "14.7", "15.1", "15.8", "16.3", "16.6", "17", "17.3",
                    "18.2", "18.8", "19.2", "20", "20.3", "21.4", "21.7", "22.4", "23", "23.3", "23.9", "24.6", "25.3", "25.7",
                    "26.4", "26.5", "27", "27.4", "27.8", "28.2", "28.6", "30.3", "32.5", "33", "34.3", "34.7"
            },
            {"21 city Bus",
                    "0", "2", "3", "5.4", "6.9", "8.4", "9.2", "9.5", "9.9", "10.4", "11", "11.8", "12.2",
                    "13.2", "13.8", "14.6", "14.9", "15.5", "15.8", "16.2", "16.7", "17", "17.6", "18.2", "19.2", "22.1", "24"
            },
            {"29 city Bus",
                    "0", "2.9", "5.5", "6.1", "7", "7.1", "7.9", "8.3", "9.3", "11.3", "11.8", "12.1", "13.4", "13.9",
                    "14.5", "15.3", "15.6", "16.3", "17", "17.7", "18.2", "18.8", "19.6", "20", "21", "21.6", "22.4", "22.7",
                    "23.3", "23.6", "24", "24.9", "25.2", "25.9", "26.5", "27.2", "27.7", "28.1", "29.1", "30.1", "30.7", "31.1",
                    "31.4", "32.3", "35.6"

            },
            {"22 city Bus",
                    "0", "3", "3.5", "4.1", "4.9", "5.2", "5.9", "6.6", "7.3", "7.8", "8.4", "9.2", "9.6", "10.6",
                    "12.4", "13", "13.9", "16.5", "17.1", "17.6", "18.5", "18.9", "19.2", "19.4", "19.7", "20", "20.4", "20.7",
                    "21", "21.3", "21.5", "21.7", "22.1", "22.5", "22.8", "23.5", "24.1", "24.5", "25.8", "26", "26.8", "27.3",
                    "27.7", "28.4", "28.5", "29", "29.4", "29.8", "30.2", "30.6"

            },
            {"9B city Bus",
                    "0", "5.5", "6.1", "7", "7.1", "7.9", "8.3", "9.3", "11.3", "11.8", "12.1", "13.4", "13.9", "14.5",
                    "15.3", "15.6", "16.3", "16.6", "17", "18.2", "18.7", "19.2", "19.4", "20.2"

            },
            {"30 city Bus",
                    "0", "1", "2.3", "4.2", "7.5", "9", "9.4", "10.4", "11.3", "11.6", "12", "13.2", "13.7", "14.5",
                    "14.9", "15.7", "15.7", "16.4", "16.9", "17.9", "19.2", "19.7", "19.9", "20.8", "21", "22.2", "22.4", "25.8",
                    "26.4", "27.4", "28.4", "29.4", "30.4", "32"
            },
            {"11 city Bus",
                    "0", "2.4", "3", "5.2", "6.7", "7.1", "7.5", "7.9", "8.3", "8.8", "8.9", "9.6", "10", "10.5",
                    "11.3", "11.5", "12.8", "13.2", "13.8", "14.5", "14.8", "15.2", "15.6", "15.8", "16", "16.3", "16.6", "16.9",
                    "17.3", "17.6", "17.9", "18.1", "18.4", "18.8", "19.7", "20.2", "20.8", "21.5", "21.8", "22.2", "22.3", "22.7",
                    "23.2", "23.5", "23.9", "24", "24.8", "25.4", "25.8", "26.1", "26.4", "26.6", "27.6", "28.5", "29.3", "29.7",
                    "30.5", "30.5", "31.2", "31.7", "32.7", "33.5", "33.5", "34.9", "35.4", "35.7", "36.2", "37.9", "38.4", "39.4",
                    "40.4", "41.4", "42", "43", "44", "45", "46"
            },
            {
                    "Metro",
                    "0", "2", "4", "6", "8", "10", "12", "14", "16", "17.5", "19"
            }
    };

    String source, destination;
    @NotNull
    public static final String TAG = FragmentFindBus.class.getSimpleName();

    public FragmentFindBus() {
        //if required
    }
    // Use an atomic boolean to initialize the Google Mobile Ads SDK and load ads once.
    private final AtomicBoolean isMobileAdsInitializeCalled = new AtomicBoolean(false);



    private void loadAd(){
        loadAds(binding.adView);
        loadAds(binding.adView1);
    }
    @Override
    public void onResume() {
        super.onResume();
        loadInterstitialAds();
        loadAd();
       /* inputSource.setText(pref.getString("source", null));
        inputDestination.setText(pref.getString("destination", null));*/
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


/*        edit.remove("source");
        edit.remove("destination");
        edit.commit();*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    FragmentFindBusBinding binding;
    String[][] bus = new String[bus_en.length][];

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        new ApiHelper(requireActivity()).setApplicationlanguage(requireActivity(), new UserSessions().getLanguage(requireActivity()));
    }

    public void loadAds(AdView mView) {
        if (!mView.isShown()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mView.loadAd(adRequest);
        }

    }

    int counter = 0;
    public void loadInterstitialAds() {
        mInterstitialAd = null;
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireActivity(), getString(R.string.admob_unit_id_intersitial), adRequest,
        //InterstitialAd.load(requireActivity(), "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.e("adsLoad", "onAdLoaded");
                      //  adCallBack();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.e("adsLoad", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

    }

    private InterstitialAd mInterstitialAd;

    public void moveToNext(int resultRow,String[][] result){
        Intent i = new Intent(getActivity(), DirectionsActrivity.class);
        i.putExtra("source", source);
        i.putExtra("destination", destination);
        i.putExtra("noOfResult", resultRow);
        i.putExtra("lowfloorChecked", lowFloreBool);
        i.putExtra("miniBusChecked", miniBusBool);
        Bundle bundle = new Bundle();
        bundle.putSerializable("result", result);
        i.putExtras(bundle);
        startActivity(i);

    }
    public void adCallBack(int resultRow,String[][] result) {
        if(resultRow>0 && result !=null && result.length>0) {
            if (mInterstitialAd != null) {
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(TAG, "Ad was clicked.");
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        Log.d(TAG, "Ad dismissed fullscreen content.");
                        mInterstitialAd = null;
                        moveToNext(resultRow, result);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when ad fails to show.
                        Log.e(TAG, "Ad failed to show fullscreen content.");
                        mInterstitialAd = null;
                        moveToNext(resultRow, result);
                    }

                    @Override
                    public void onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(TAG, "Ad recorded an impression.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.");
                    }
                });
                counter++;
                if ( ( counter % 2 ) == 0 ) {
                    //Is even
                    moveToNext(resultRow, result);
                } else {
                    mInterstitialAd.show(requireActivity());
                    //Is odd
                }

                //moveToNext(resultRow, result);
            } else {
                moveToNext(resultRow, result);
                Log.d("TAG", "The interstitial ad wasn't ready yet.");
            }
        }else{
         CommonMethods.errorToast(requireActivity(),getString(R.string.error_no_result));
        }
    }
    private ConsentInformation consentInformation;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_find_bus, container, false);
        binding = DataBindingUtil.bind(view);

        requestConsent();

        if (new UserSessions().getLanguage(requireActivity()).equalsIgnoreCase("1")) {
            bus = bus_en;
        } else {
            bus = bus_hi;
        }

        for (int a = 0; a < bus.length; a++) {
            //Log.e("Bus_SIZE : ", "bus no " + bus[a][0] + " => " + bus[a].length + " \nKM NO " + KM[a][0] + " => " + KM[a].length);
        }
        inputSource = binding.inputSource;
        inputDestination = binding.inputDestination;
        lowFloreBus = binding.lowflooricon;
        miniBus = binding.minibusicon;
        metro = binding.metroicon;
        btnGetBus = binding.btnGetbus;

        lowFloreBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowFloreBool == true) {
                    lowFloreBus.setImageResource(R.drawable.ic_metro_unselected);
                    lowFloreBool = false;
                } else {
                    lowFloreBus.setImageResource(R.drawable.ic_lowfloor);
                    lowFloreBool = true;
                }
            }
        });
        miniBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (miniBusBool == true) {
                    miniBusBool = false;
                    miniBus.setImageResource(R.drawable.ic_minibus_unselected);
                } else {
                    miniBusBool = true;
                    miniBus.setImageResource(R.drawable.ic_minibus);
                }
            }
        });

        metro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (metrobool == true) {
                    metrobool = false;
                    metro.setImageResource(R.drawable.ic_metro_unselected);
                } else {
                    metrobool = true;
                    metro.setImageResource(R.drawable.ic_metro);
                }
            }
        });
        inputSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(requireActivity(), SearchRoutesActivity.class);
                mIntent.putExtra("type", "0");
                getResult.launch(mIntent);
            }
        });
        inputDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(requireActivity(), SearchRoutesActivity.class);
                mIntent.putExtra("type", "1");
                getResult.launch(mIntent);
            }
        });
        btnGetBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                source = inputSource.getText().toString();
                destination = inputDestination.getText().toString();
                if (!lowFloreBool && !miniBusBool && !metrobool) {
                    CommonMethods.errorDialog(requireActivity(), getString(R.string.error_type_required), getString(R.string.app_name), getString(R.string.lbl_ok));
                } else if (!CommonMethods.isValidString(binding.inputSource.getText().toString()) || !CommonMethods.isValidString(binding.inputDestination.getText().toString())) {
                    CommonMethods.errorDialog(requireActivity(), getString(R.string.error_source_dest_required), getString(R.string.app_name), getString(R.string.lbl_ok));
                } else {
                    if (source.equalsIgnoreCase("WTP Mall ( GT )"))
                        source = "Gaurav Tower ( GT )";
                    if (destination.equalsIgnoreCase("WTP Mall ( GT )"))
                        destination = "Gaurav Tower ( GT )";

                    if (source.equalsIgnoreCase("Pink Square Mall (Pagalkhana)"))
                        source = "Pagalkhana";
                    if (destination.equalsIgnoreCase("Pink Square Mall (Pagalkhana)"))
                        destination = "Pagalkhana";

                    //Triton Mall (Dahar ke Balaji)
                    if (source.equalsIgnoreCase("Triton Mall (Dahar ke Balaji)"))
                        source = "Dahar Ke Balaji";
                    if (destination.equalsIgnoreCase("Triton Mall (Dahar ke Balaji)"))
                        destination = "Dahar Ke Balaji";

                    //City Pulse Mall (Narayansingh Circle)
                    if (source.equalsIgnoreCase("City Pulse Mall (Narayansingh Circle)"))
                        source = "Narayan Singh Circle";
                    if (destination.equalsIgnoreCase("City Pulse Mall (Narayansingh Circle)"))
                        destination = "Narayan Singh Circle";

                    //Birla Mandir (JDA Circle)
                    if (source.equalsIgnoreCase("Birla Mandir (JDA Circle)"))
                        source = "JDA Circle";
                    if (destination.equalsIgnoreCase("Birla Mandir (JDA Circle)"))
                        destination = "JDA Circle";

                    //Raj Mandir Cinema (Paanchbatti)

                    if (source.equalsIgnoreCase("Raj Mandir Cinema (Paanchbatti)"))
                        source = "Paanch Batti";
                    if (destination.equalsIgnoreCase("Raj Mandir Cinema (Paanchbatti)"))
                        destination = "Paanch Batti";

                    //Chokhi Dhani (Goner Mod)
                    if (source.equalsIgnoreCase("Chokhi Dhani (Goner Mod)"))
                        source = "Goner Mod";
                    if (destination.equalsIgnoreCase("Chokhi Dhani (Goner Mod)"))
                        destination = "Goner Mod";

                    if (source.equalsIgnoreCase("Albert Hall (Ramniwasbagh)") || source.equalsIgnoreCase("Bapu Bazar (Ramniwas Bagh)") || source.equalsIgnoreCase("Choda Rasta (Ramniwas Bagh)"))
                        source = "Ram Niwas Garden Parking";
                    if (destination.equalsIgnoreCase("Albert Hall (Ramniwasbagh)") || destination.equalsIgnoreCase("Bapu Bazar (Ramniwas Bagh)") || destination.equalsIgnoreCase("Choda Rasta (Ramniwas Bagh)"))
                        destination = "Ram Niwas Garden Parking";

                    if (source.equalsIgnoreCase("Central Park (Rambagh)") || source.equalsIgnoreCase("Rambagh Palace (Rambagh)"))
                        source = "Rambagh Circle";
                    if (destination.equalsIgnoreCase("Central Park (Rambagh)") || destination.equalsIgnoreCase("Rambagh Palace (Rambagh)"))
                        destination = "Rambagh Circle";

                    if (source.equalsIgnoreCase("Jantar-Mantar (Tripoliya Bazar)") || source.equalsIgnoreCase("City Palace (Tripoliya Bazar)"))
                        source = "Tripoliya Bazar";
                    if (destination.equalsIgnoreCase("Jantar-Mantar (Tripolya Bazar)") || destination.equalsIgnoreCase("City Palace (Tripoliya Bazar)"))
                        destination = "Tripoliya Bazar";

                    if (source.equalsIgnoreCase("Hawa Mahal (Badi Chaupar)") || source.equalsIgnoreCase("Johari Bazar (Badi Chaupar)"))
                        source = "Badi Chaupar";
                    if (destination.equalsIgnoreCase("Hawa Mahal (Badi Chaupar)") || destination.equalsIgnoreCase("Johari Bazar (Badi Chaupar)"))
                        destination = "Badi Chaupar";

                    if (source.equalsIgnoreCase("Crystal Palm Mall (22 Godam)") || source.equalsIgnoreCase("MGF Metropoliton Mall (22 Godam)"))
                        source = "Sahkari Bhawan/22 Godam";
                    if (destination.equalsIgnoreCase("Crystal Palm Mall (22 Godam)") || destination.equalsIgnoreCase("MGF Metropoliton Mall (22 Godam)"))
                        destination = "Sahkari Bhawan/22 Godam";


                    String directBusArray[][] = new String[30][2];
                    int directBusArrLen = 0;
                    int x = 0, y = 0, startingIndex = 0, endingIndex = bus.length;
                    int z = 1, stationBw = 0, l;
                    int countStation;
                    boolean bus8 = false, metro_checked;
                    int a, b, c, d, e, f;
                    BigDecimal countDistance = new BigDecimal(0);
                    int saveSource[][] = new int[40][2];
                    int saveDestination[][] = new int[40][2];
                    String result[][] = new String[150][8];

                    int directBus1 = 0, directBus2 = 0;

                    int resultRow = 0;
                    boolean sameBus = false;

                    if (lowFloreBool == true && miniBusBool == true) {
                        startingIndex = 0;
                        endingIndex = bus.length - 1;
                    } else if (lowFloreBool == false && miniBusBool == true) {
                        startingIndex = 23;
                        endingIndex = bus.length - 1;
                    } else if (lowFloreBool == true && miniBusBool == false) {
                        startingIndex = 0;
                        endingIndex = 23;
                    } else {
                        startingIndex = 0;
                        endingIndex = 0;

                    }

                    l:
                    for (int busRow = startingIndex; busRow < endingIndex + 1; busRow++)      //for direct route
                    {

                        if (busRow == endingIndex) {
                            if (metrobool == true)
                                busRow = bus.length - 1;
                            else
                                continue l;
                        }
                        directBus2 = 0;
                        directBus1 = 0;
                        for (int busColomb = 1; busColomb < bus[busRow].length; busColomb++) {
                            if (bus[busRow][busColomb].equals(source))            //searching for source station
                            {
                                saveSource[x][0] = busRow;
                                saveSource[x++][1] = busColomb;            //saving source station detail in array
                                directBus1++;
                            }
                            if (bus[busRow][busColomb].equals(destination))        //searching for destination
                            {
                                saveDestination[y][0] = busRow;            //saving destination station detail in diff. array
                                saveDestination[y++][1] = busColomb;
                                directBus2++;
                            }
                            if (directBus1 >= 1 && directBus2 >= 1)            //saving data in result if direct route
                            {
                                if (resultRow != 0 && bus[busRow][0] == bus[Integer.parseInt(result[resultRow - 1][1])][0]) {
                                    //Math.abs((Integer.parseInt(KM[saveSource[x-1][0]][saveSource[x-1][1]]))-Integer.parseInt(KM[saveDestination[y-1][0]][saveDestination[y-1][1]]))<Integer.parseInt(result[resultRow-1][4])
                                    a = ((new BigDecimal(KM[saveSource[x - 1][0]][saveSource[x - 1][1]])).subtract(new BigDecimal(KM[saveDestination[y - 1][0]][saveDestination[y - 1][1]]))).abs().compareTo((new BigDecimal(result[resultRow - 1][5])));

                                    if (a == -1)//check it
                                    {
                                        //result[resultRow-1][2]="100";
                                        result[resultRow - 1][5] = "" + ((new BigDecimal(KM[saveSource[x - 1][0]][saveSource[x - 1][1]])).subtract(new BigDecimal(KM[saveDestination[y - 1][0]][saveDestination[y - 1][1]]))).abs();
                                        result[resultRow - 1][1] = busRow + "";
                                        result[resultRow - 1][0] = "" + Math.abs(saveSource[x - 1][1] - saveDestination[y - 1][1]);
                                        result[resultRow - 1][4] = "" + saveDestination[y - 1][1];
                                        result[resultRow - 1][6] = "" + saveSource[x - 1][1];
                                        directBusArray[directBusArrLen - 1][0] = bus[busRow][0];
                                        directBusArray[directBusArrLen - 1][1] = result[resultRow - 1][5];
                                    }
                                } else if (resultRow == 0) {
                                    //result[resultRow][5]=""+Math.abs((Integer.parseInt(KM[saveSource[x-1][0]][saveSource[x-1][1]]))-Integer.parseInt(KM[saveDestination[y-1][0]][saveDestination[y-1][1]]));
                                    result[resultRow][5] = "" + ((new BigDecimal(KM[saveSource[x - 1][0]][saveSource[x - 1][1]])).subtract(new BigDecimal(KM[saveDestination[y - 1][0]][saveDestination[y - 1][1]]))).abs();
                                    result[resultRow][1] = "" + busRow;
                                    result[resultRow][4] = "" + saveDestination[y - 1][1];
                                    result[resultRow][6] = "" + saveSource[x - 1][1];
                                    result[resultRow++][0] = "" + Math.abs(saveSource[x - 1][1] - saveDestination[y - 1][1]);
                                    //result[resultRow++][2]="100";
                                    directBusArray[directBusArrLen][0] = bus[busRow][0];
                                    directBusArray[directBusArrLen++][1] = result[resultRow - 1][5];
                                } else {

                                    for (int i = resultRow - 1; i >= 0; i--) {
                                        int sort1 = ((new BigDecimal(KM[saveSource[x - 1][0]][saveSource[x - 1][1]])).subtract(new BigDecimal(KM[saveDestination[y - 1][0]][saveDestination[y - 1][1]]))).abs().compareTo(new BigDecimal(result[i][5]));
                                        if (sort1 == 1 || sort1 == 0) {
                                            //result[resultRow][5]=""+Math.abs((Integer.parseInt(KM[saveSource[x-1][0]][saveSource[x-1][1]]))-Integer.parseInt(KM[saveDestination[y-1][0]][saveDestination[y-1][1]]));
                                            try {
                                                result[i + 1][5] = "" + ((new BigDecimal(KM[saveSource[x - 1][0]][saveSource[x - 1][1]])).subtract(new BigDecimal(KM[saveDestination[y - 1][0]][saveDestination[y - 1][1]]))).abs();
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][1] = "" + busRow;
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][4] = "" + saveDestination[y - 1][1];
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][6] = "" + saveSource[x - 1][1];
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][0] = "" + Math.abs(saveSource[x - 1][1] - saveDestination[y - 1][1]);
                                            } catch (Exception e1) {
                                            }
                                            //result[i+1][2]="100";
                                            resultRow++;

                                            directBusArray[directBusArrLen][0] = bus[busRow][0];
                                            directBusArray[directBusArrLen++][1] = result[i + 1][5];
                                            continue l;
                                        } else {
                                            try {
                                                result[i + 1][0] = result[i][0];
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][1] = result[i][1];
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][4] = result[i][4];
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][5] = result[i][5];
                                            } catch (Exception e1) {
                                            }
                                            try {
                                                result[i + 1][6] = result[i][6];
                                            } catch (Exception e1) {
                                            }
                                        }

                                    }
                                    result[0][5] = "" + ((new BigDecimal(KM[saveSource[x - 1][0]][saveSource[x - 1][1]])).subtract(new BigDecimal(KM[saveDestination[y - 1][0]][saveDestination[y - 1][1]]))).abs();
                                    result[0][1] = "" + busRow;
                                    result[0][4] = "" + saveDestination[y - 1][1];
                                    result[0][6] = "" + saveSource[x - 1][1];
                                    result[0][0] = "" + Math.abs(saveSource[x - 1][1] - saveDestination[y - 1][1]);
                                    resultRow++;

                                    directBusArray[directBusArrLen][0] = bus[busRow][0];
                                    directBusArray[directBusArrLen++][1] = result[0][5];
                                }
                                continue l;
                            }
                        }
                    }
                    try {
                        for (int saveSourceRow = 0; saveSourceRow < x; saveSourceRow++) {
                            for (int saveDestinationRow = 0; saveDestinationRow < y; saveDestinationRow++) {
                                b:
                                for (int saveSourceBusColomb = 1; saveSourceBusColomb < bus[saveSource[saveSourceRow][0]].length; saveSourceBusColomb++) {
                                    for (int saveDestinationBusColomb = 1; saveDestinationBusColomb < bus[saveDestination[saveDestinationRow][0]].length; saveDestinationBusColomb++) {
                                        if (bus[saveSource[saveSourceRow][0]][saveSourceBusColomb] == bus[saveDestination[saveDestinationRow][0]][saveDestinationBusColomb]) {
                                            countStation = Math.abs(saveSourceBusColomb - saveSource[saveSourceRow][1]) + Math.abs(saveDestination[saveDestinationRow][1] - saveDestinationBusColomb);
                                            countDistance = (new BigDecimal(KM[saveSource[saveSourceRow][0]][saveSource[saveSourceRow][1]]).subtract(new BigDecimal(KM[saveSource[saveSourceRow][0]][saveSourceBusColomb]))).abs().add
                                                    ((new BigDecimal(KM[saveDestination[saveDestinationRow][0]][saveDestination[saveDestinationRow][1]]).subtract
                                                            (new BigDecimal(KM[saveDestination[saveDestinationRow][0]]
                                                                    [saveDestinationBusColomb]))).abs());
                                            for (int directBusIndex = 0; directBusIndex < directBusArrLen; directBusIndex++) {
                                                if (bus[saveSource[saveSourceRow][0]][0].equals(directBusArray[directBusIndex][0]) || bus[saveDestination[saveDestinationRow][0]][0].equals(directBusArray[directBusIndex][0])) {
                                                    int xyz = countDistance.compareTo(new BigDecimal(directBusArray[directBusIndex][1]));
                                                    if (xyz == 1 || xyz == 0) {
                                                        continue b;
                                                    }
                                                }
                                            }
                                            if (resultRow == 0) {
                                                result[resultRow][0] = countStation + "";
                                                result[resultRow][1] = saveSource[saveSourceRow][0] + "";//starting bus index row
                                                result[resultRow][2] = saveDestination[saveDestinationRow][0] + ""; //2nd bus index row
                                                result[resultRow][5] = "" + countDistance;
                                                result[resultRow][3] = saveDestinationBusColomb + "";
                                                result[resultRow][6] = "" + saveSource[saveSourceRow][1];//source column index
                                                result[resultRow][7] = "" + saveDestination[saveDestinationRow][1];
                                                result[resultRow++][4] = "" + saveSourceBusColomb;
                                                continue b;
                                            } else {
                                                for (l = 0; l < resultRow; l++) {
                                                    if (bus[saveSource[saveSourceRow][0]][0] == bus[Integer.parseInt(result[l][1])][0] && result[l][2] != null && bus[saveDestination[saveDestinationRow][0]][0] == bus[Integer.parseInt(result[l][2])][0]) {
                                                        sameBus = true;
                                                        break;
                                                    }
                                                }
                                                if (sameBus == true) {
                                                    sameBus = false;
                                                    b = countDistance.compareTo(new BigDecimal(result[l][5]));
                                                    if (b == -1) {
                                                        for (int o = l - 1; o >= 0; o--) {
                                                            c = (new BigDecimal(result[o][5])).compareTo(countDistance);
                                                            if (c == 1) {
                                                                try {
                                                                    result[o + 1][5] = result[o][5];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][0] = result[o][0];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][1] = result[o][1];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][2] = result[o][2];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][3] = result[o][3];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][4] = result[o][4];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][6] = result[o][6];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][7] = result[o][7];
                                                                } catch (Exception e1) {
                                                                }
                                                            } else {
                                                                try {
                                                                    result[o + 1][5] = countDistance + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][0] = countStation + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][1] = saveSource[saveSourceRow][0] + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][2] = saveDestination[saveDestinationRow][0] + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][3] = saveDestinationBusColomb + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][4] = "" + saveSourceBusColomb;
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][6] = "" + saveSource[saveSourceRow][1];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[o + 1][7] = "" + saveDestination[saveDestinationRow][1];
                                                                } catch (Exception e1) {
                                                                }
                                                                continue b;
                                                            }
                                                        }
                                                        result[0][5] = countDistance + "";
                                                        result[0][0] = countStation + "";
                                                        result[0][1] = saveSource[saveSourceRow][0] + "";
                                                        result[0][2] = "" + saveDestination[saveDestinationRow][0];
                                                        result[0][3] = "" + saveDestinationBusColomb;
                                                        result[0][4] = "" + saveSourceBusColomb;
                                                        result[0][6] = "" + saveSource[saveSourceRow][1];
                                                        result[0][7] = "" + saveDestination[saveDestinationRow][1];
                                                        continue b;
                                                    }
                                                } else {
                                                    d = (new BigDecimal(result[0][5]).add(new BigDecimal("5"))).compareTo(countDistance);
                                                    if (d == 1) {
                                                        int i;
                                                        for (i = resultRow - 1; i >= 0; i--) {
                                                            e = countDistance.compareTo(new BigDecimal(result[i][5]));
                                                            if (e == -1) {
                                                                try {
                                                                    result[i + 1][5] = result[i][5];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][4] = result[i][4];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][0] = result[i][0];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][1] = result[i][1];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][2] = result[i][2];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][3] = result[i][3];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][6] = result[i][6];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][7] = result[i][7];
                                                                } catch (Exception e1) {
                                                                }
                                                            } else {
                                                                try {
                                                                    result[i + 1][5] = countDistance + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][0] = countStation + "";
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][1] = "" + saveSource[saveSourceRow][0];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][2] = "" + saveDestination[saveDestinationRow][0];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][3] = "" + saveDestinationBusColomb;
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][4] = "" + saveSourceBusColomb;
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][6] = "" + saveSource[saveSourceRow][1];
                                                                } catch (Exception e1) {
                                                                }
                                                                try {
                                                                    result[i + 1][7] = "" + saveDestination[saveDestinationRow][1];
                                                                } catch (Exception e1) {
                                                                }
                                                                resultRow++;
                                                                continue b;
                                                            }
                                                        }
                                                        result[0][5] = countDistance + "";
                                                        result[0][0] = countStation + "";
                                                        result[0][1] = saveSource[saveSourceRow][0] + "";
                                                        result[0][2] = saveDestination[saveDestinationRow][0] + "";
                                                        result[0][3] = saveDestinationBusColomb + "";
                                                        result[0][4] = saveSourceBusColomb + "";
                                                        result[0][6] = "" + saveSource[saveSourceRow][1];
                                                        result[0][7] = "" + saveDestination[saveDestinationRow][1];
                                                        resultRow++;
                                                    }
                                                }
                                            }
                                            // }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    adCallBack(resultRow,result);
                }
            }
        });


        return view;

    }

    private void requestConsent() {
        // Create a ConsentRequestParameters object.
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(requireActivity());
        consentInformation.requestConsentInfoUpdate(
                requireActivity(),
                params,
                (ConsentInformation.OnConsentInfoUpdateSuccessListener) () -> {
                    UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                            requireActivity(),
                            (ConsentForm.OnConsentFormDismissedListener) loadAndShowError -> {
                                if (loadAndShowError != null) {
                                    // Consent gathering failed.
                                    Log.w(TAG, String.format("%s: %s",
                                            loadAndShowError.getErrorCode(),
                                            loadAndShowError.getMessage()));
                                }

                                // Consent has been gathered.
                                if (consentInformation.canRequestAds()) {
                                    initializeMobileAdsSdk();
                                }
                            }
                    );
                },
                (ConsentInformation.OnConsentInfoUpdateFailureListener) requestConsentError -> {
                    // Consent gathering failed.
                    Log.w(TAG, String.format("%s: %s",
                            requestConsentError.getErrorCode(),
                            requestConsentError.getMessage()));
                });

        // Check if you can initialize the Google Mobile Ads SDK in parallel
        // while checking for new consent information. Consent obtained in
        // the previous session can be used to request ads.
        if (consentInformation.canRequestAds()) {
            initializeMobileAdsSdk();
        }

    }
    private void initializeMobileAdsSdk() {
        MobileAds.initialize(requireActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.e("getAdapterStatusMap", initializationStatus.getAdapterStatusMap().toString());
            }
        });
        loadAd();
    }

    private ActivityResultLauncher<Intent> getResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if (data.hasExtra("type")) {
                            String type = data.getStringExtra("type");
                            if (data.hasExtra("station")) {
                                String station = data.getStringExtra("station");
                                if (type.equalsIgnoreCase("0") && CommonMethods.isValidString(station)) {
                                    binding.inputSource.setText(station);
                                } else if (CommonMethods.isValidString(station)) {
                                    binding.inputDestination.setText(station);
                                }
                            }
                        }
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // code for cancelled result here
                    }
                }
            });


    @NotNull
    public static Fragment newInstance() {
        return new FragmentFindBus();
    }

    OnPageChangeListener mListener = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (OnPageChangeListener) context;
            if (mListener != null) {
                mListener.onPageChange("search");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}