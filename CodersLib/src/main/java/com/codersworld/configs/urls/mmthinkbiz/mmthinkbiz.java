package com.codersworld.configs.urls.mmthinkbiz;

import com.codersworld.configs.constants.b.b;
import com.codersworld.configs.constants.c.c;
import com.codersworld.configs.constants.e.e;
import com.codersworld.configs.constants.h.h;
import com.codersworld.configs.constants.i.i;
import com.codersworld.configs.constants.k.k;
import com.codersworld.configs.constants.l.l;
import com.codersworld.configs.constants.m.m;
import com.codersworld.configs.constants.n.n;
import com.codersworld.configs.constants.o.o;
import com.codersworld.configs.constants.r.r;
import com.codersworld.configs.constants.s.s;
import com.codersworld.configs.constants.symbols.symbols;
import com.codersworld.configs.constants.t.t;
import com.codersworld.configs.constants.v.v;
import com.codersworld.configs.constants.z.z;
import com.codersworld.configs.urls.common.common;

public class mmthinkbiz extends common {
    //mmthinkbiz.com/MobileService.aspx
    public static String getmmthinkbiz() {
        return m.m + m.m + t.t + h.h + i.i + n.n + k.k + b.b + i.i + z.z;
    }

    public static String getMobileService() {
        return m.M+ o.o+b.b+i.i+ l.l+ e.e+ s.S+e.e+ r.r+ v.v+i.i+ c.c+e.e;
    }

    public static String getMTBMidUrl() {
        return getmmthinkbiz()+getDotCom()+getMobileService()+getaspx();
    }

    public static String getMTBBaseUrl() {
        return getHttp() + getMTBMidUrl();
    }
}
