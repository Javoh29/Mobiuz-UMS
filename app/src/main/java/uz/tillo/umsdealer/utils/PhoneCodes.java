package uz.tillo.umsdealer.utils;

import android.net.Uri;

/**
 * Created by Kholmatov on 22.03.2017.
 * Developed by Rakhmatillo & Mukhammadyunus 26.09.2018.
 */

public class PhoneCodes {
    public static final String encodedHash = Uri.encode("#");
    public static final String callCentre = "+998971300909";
    public static final String balanceUssdRu = "*171*1*1*2"+encodedHash;
    public static final String balanceUssdUz = "*100"+encodedHash;
    public static final String lastPayment = "*171*1*2"+encodedHash;
    public static final String myDisCharge = "*171*1*3"+encodedHash;
    public static final String myNumber = "*150"+encodedHash;
    public static final String allMyNumbers = "*151"+encodedHash;
    public static final String RemainTrafBtn= "*102"+encodedHash;
    public static final String CheckActServ= "*140"+encodedHash;
    public static final String languageChangeRu = "*111*0*2"+encodedHash;
    public static final String languageChangeUz = "*111*0*1"+encodedHash;

    public static final String getSettings = "*111*021"+encodedHash;
    public static final String turnOnMobileNet = "*111*0011"+encodedHash;
    public static final String turnOffMobileNet = "*111*0010"+encodedHash;
    public static final String turnOffOnNet = "*202*0"+encodedHash;
    //-----------------------standart internet packages-----------
    public static final String packageCheck = "*171*019"+encodedHash;
    public static final String package300 = "*171*019*1*010100180"+encodedHash;
    public static final String package500 = "*171*019*7*010100180"+encodedHash;
    public static final String package1000 = "*171*019*2*010100180"+encodedHash;
    public static final String package2000 = "*171*019*5*010100180"+encodedHash;
    public static final String package3000 = "*171*019*3*010100180"+encodedHash;
    public static final String package5000 = "*171*019*4*010100180"+encodedHash;
    public static final String package10000 = "*171*019*6*010100180"+encodedHash;
    public static final String package20000 = "*171*019*8*010100180"+encodedHash;
    public static final String package30000 = "*171*019*9*010100180"+encodedHash;
    public static final String package50000 = "*171*019*10*010100180"+encodedHash;

    //-----------------------night internet packages-----------
    public static final String nightCheck = "*203"+encodedHash;
    public static final String night1000 = "*171*203*1000*010100180"+encodedHash;
    public static final String night2000 = "*171*203*2000*010100180"+encodedHash;
    public static final String night3000 = "*171*203*3000*010100180"+encodedHash;
    public static final String night5000 = "*171*203*5000*010100180"+encodedHash;
    public static final String night10000 = "*171*203*10000*010100180"+encodedHash;
    public static final String night20000 = "*171*203*20000*010100180"+encodedHash;
    public static final String night50000 = "*171*203*50000*010100180"+encodedHash;

    //------------------------mini internet packages----------
    public static final String miniCheck = "*102"+encodedHash;

    //------------------------drive packages-------------------
    public static final String drive1 = "*171*200*1*010100180"+encodedHash;
    public static final String drive7 = "*171*200*7*010100180"+encodedHash;
    public static final String drive30 = "*171*200*30*010100180"+encodedHash;
    public static final String week100 = "*171*105*100*010100180"+encodedHash;
    public static final String week200 = "*171*105*200*010100180"+encodedHash;
    public static final String week300 = "*171*105*300*010100180"+encodedHash;
    public static final String day50 = "*171*204*50*010100180"+encodedHash;
    public static final String day100 = "*171*204*100*010100180"+encodedHash;
    public static final String day200 = "*171*204*200*010100180"+encodedHash;
    public static final String day300 = "*171*204*300*010100180"+encodedHash;
    public static final String day500 = "*171*204*500*010100180"+encodedHash;
    public static final String day1000 = "*171*204*1000*010100180"+encodedHash;
    public static final String day2000 = "*171*204*2000*010100180"+encodedHash;
    public static final String day3000 = "*171*204*3000*010100180"+encodedHash;
    public static final String day5000 = "*171*204*5000*010100180"+encodedHash;
    public static final String day10000 = "*171*204*10000*010100180"+encodedHash;

    //------------------------services------------------
/*
    public static final String mutlaqo_cheksiz = "*165*1"+encodedHash; ochiramiz shazodaniyam
*/
    public static final String promisePayment = "*111*32"+encodedHash;
    public static final String holdCall = "*43"+encodedHash;
    public static final String holdCallCancel = "#43"+encodedHash;
    public static final String missedCall = "*111*0131"+encodedHash;
    public static final String missedCallCancel = "*111*0130"+encodedHash;
    public static final String antiAON = "*111*0101"+encodedHash;
    public static final String antiAONCancel = "*111*0100"+encodedHash;
    public static final String lte4G = "*222*1"+encodedHash;
    public static final String lte4GCancel = "*222*0"+encodedHash;
    public static final String activeServices = "*140"+encodedHash;
    public static final String internationalCall = "*111*0021"+encodedHash;
    public static final String super0 = "*166"+encodedHash;


    //--------------------------minute packages-----------------
    public static final String minuteCheck = "*103"+encodedHash;
    public static final String minute60 = "*171*103*60*010100180"+encodedHash;
    public static final String minute120 = "*171*103*120*1*010100180"+encodedHash;
    public static final String minute180 = "*171*103*180*1*010100180"+encodedHash;
    public static final String minute300 = "*171*103*300*1*010100180"+encodedHash;
    public static final String minute900 = "*171*103*900*1*010100180"+encodedHash;
    public static final String minute1200 = "*171*103*1200*1*010100180"+encodedHash;
    public static final String minute1800 = "*171*103*1800*1*010100180"+encodedHash;
    //--------------------------sms packages-----------------
    public static final String smsCheck = "*111*018"+encodedHash;
    public static final String sms100 = "*111*018*1"+encodedHash;
    public static final String sms300 = "*111*018*2"+encodedHash;



}
