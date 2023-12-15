package com.codersworld.configs.urls.vehicletrack;

import com.codersworld.configs.constants.a.a;
import com.codersworld.configs.constants.b.b;
import com.codersworld.configs.constants.c.c;
import com.codersworld.configs.constants.d.d;
import com.codersworld.configs.constants.e.e;
import com.codersworld.configs.constants.f.f;
import com.codersworld.configs.constants.g.g;
import com.codersworld.configs.constants.h.h;
import com.codersworld.configs.constants.i.i;
import com.codersworld.configs.constants.k.k;
import com.codersworld.configs.constants.l.l;
import com.codersworld.configs.constants.m.m;
import com.codersworld.configs.constants.n.n;
import com.codersworld.configs.constants.numbers.numbers;
import com.codersworld.configs.constants.o.o;
import com.codersworld.configs.constants.p.p;
import com.codersworld.configs.constants.r.r;
import com.codersworld.configs.constants.s.s;
import com.codersworld.configs.constants.t.t;
import com.codersworld.configs.constants.u.u;
import com.codersworld.configs.constants.v.v;
import com.codersworld.configs.constants.w.w;
import com.codersworld.configs.constants.y.y;
import com.codersworld.configs.constants.symbols.symbols;
import com.codersworld.configs.urls.common.common;

public class membocool extends common {
    public static String getMembocoolBaseUrl() {
        return getHttps() + getvehicletrack() + symbols.dots + getmembocool() + getDotCom();
    }

    public static String getSafeobuddyAspx() {
        return s.S + a.a + f.f + e.e + o.o + b.b + u.u + d.d + d.d + y.y + getaspx() + symbols.question;
    }

    public static String getConServiceAspx() {
        return c.C + o.o + n.n + s.S + e.e + r.r + v.v + i.i + c.c + e.e + getaspx() + symbols.question;
    }

    public static String getSafeobuddyApiMethod() {
        return getSafeobuddyAspx() + getmethod() + a.a + p.p + s.s + s.s;
    }

    public static String getLoginApiLink() {
        return getmethod() + l.L + o.o + g.g + i.i + n.n + v.V + a.a + l.l + i.i + d.d + a.a + t.t + i.i + o.o + n.n + numbers.ONE;
    }

    public static String getAllV3LocksLink() {
        return getmethod() + g.G + e.e + t.t + getV3() + getlock() + getdetail();
    }

    public static String getupdateLockDataLink() {
        return getmethod() + getUpdate() + getLock() + d.D + a.a + t.t + a.a;
    }

    public static String getupdateLockNameLink() {
        return getmethod() + getUpdate() + getLock() + n.N + a.a + m.m + e.e;
    }

    public static String getDeleteLockLink() {
        return getmethod() + getInsert() + getunlock() + t.t + i.i + m.m + e.e + a.a + c.c + c.c + e.e + s.s + s.s + getdetail();
    }

    public static String getOpenLockLink() {
        return getmethod() + getInsert() + s.S + t.t + a.a + r.r + t.t + s.S + t.t + o.o + p.p + v.V + e.e + h.h + i.i + c.c + l.l + e.e + o.O + p.p + e.e + n.n + g.G + a.a + t.t + e.e;
    }

    public static String getUploadUnlockGateLink() {
        return n.N + e.e + w.w + r.R + e.e + g.g + i.i + s.s + t.t + r.r + a.a + t.t + i.i + o.o + n.n + getmethod() + u.U + p.p + l.l + o.o + a.a + d.d + g.g + a.a + t.t + e.e + getunlock();
    }

    public static String getSaveLockStatusLink() {
        return getmethod() + s.S + a.a + v.v + e.e + getLock() + s.S + t.t + a.a + t.t + u.u + s.s;
    }

    public static String getAddNewLockLink() {
        return getmethod() + a.A + d.d + d.d + symbols.underscores + getLock() + symbols.underscores + n.N + e.e + w.w;
    }

    public static String getCheckBTAccessLink() {
        return getConServiceAspx() + getmethod() + c.c + h.h + e.e + c.c + k.k + b.b + o.o + t.t + h.h + l.l + o.o + c.c + k.k + a.a + c.c + c.c + e.e + s.s + s.s;
    }

    public static String getGateRecordsLink() {
        return getmethod() + g.G + e.e + t.t + v.V + e.e + h.h + i.i + c.c + l.l + e.e + symbols.underscores + getLock() + symbols.underscores + s.S + u.u + m.m + m.m + a.a + r.r + y.y;
    }
}
