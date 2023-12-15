package com.codersworld.configs.urls.common;

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
import com.codersworld.configs.constants.symbols.symbols;
import com.codersworld.configs.constants.t.t;
import com.codersworld.configs.constants.u.u;
import com.codersworld.configs.constants.v.v;
import com.codersworld.configs.constants.w.w;
import com.codersworld.configs.constants.x.x;
import com.codersworld.configs.constants.y.y;
import com.codersworld.configs.constants.z.z;

public class common {
    public static String getHttps() {
        return h.h + t.t + t.t + p.p + s.s + symbols.colons + symbols.b_slash + symbols.b_slash;
    }

    public static String getHttp() {
        return h.h + t.t + t.t + p.p + symbols.colons + symbols.b_slash + symbols.b_slash;
    }

    public static String getDotCom() {
        return symbols.dots + c.c + o.o + m.m;
    }

    public static String getlock() {
        return l.l + o.o + c.c + k.k;
    }

    public static String getunlock() {
        return u.u + n.n + l.l + o.o + c.c + k.k;
    }

    public static String getlist() {
        return l.l + i.i + s.s + t.t;
    }

    public static String getkey() {
        return k.k + e.e + y.y;
    }

    public static String getv3() {
        return v.v + numbers.THREE;
    }
    public static String getV3() {
        return v.v + numbers.THREE;
    }

    public static String getgateway() {
        return g.g + a.a + t.t + e.e + w.w + a.a + y.y;
    }

    public static String getUpload() {
        return u.u + p.p + l.l + o.o + a.a + d.d;
    }

    public static String getDetail() {
        return d.D + e.e + t.t + a.a + i.i + l.l;
    }
    public static String getdetail() {
        return d.d + e.e + t.t + a.a + i.i + l.l;
    }

    public static String getrename() {
        return r.r + e.e + n.n + a.a + m.m + e.e;
    }

    public static String getSuccess() {
        return s.S + u.u + c.c + c.c + e.e + s.s + s.s;
    }

    public static String getInit() {
        return i.I + n.n + i.i + t.t;
    }

    public static String getinitialize() {
        return i.i + n.n + i.i + t.t + i.i + a.a + l.l + i.i + z.z + e.e;
    }

    public static String getisInitSuccess() {
        return u.u + s.s + getInit() + getSuccess();
    }

    public static String getRecord() {
        return r.R + e.e + c.c + o.o + r.r + d.d;
    }

    public static String getTTClientId() {
        return numbers.FOUR + numbers.THREE + numbers.NINE + numbers.ZERO + numbers.SIX + numbers.THREE + e.e + numbers.THREE + numbers.ONE + numbers.TWO + numbers.FOUR + numbers.FOUR + numbers.FOUR + f.f + numbers.ONE + f.f + numbers.EIGHT + numbers.FIVE + numbers.ZERO + numbers.FIVE + numbers.ZERO + a.a + numbers.FIVE + numbers.TWO + e.e + f.f + c.c + e.e + c.c + d.d + numbers.TWO + e.e;
    }

    public static String getTTClientSecrete() {
        return numbers.ZERO + e.e + f.f + numbers.ONE + c.c + numbers.FOUR + numbers.NINE + b.b + numbers.SEVEN + numbers.ZERO + c.c + numbers.ZERO + numbers.TWO + a.a + e.e + numbers.SIX + numbers.THREE + numbers.ONE + numbers.FOUR + b.b + d.d + e.e + numbers.SIX + numbers.ZERO + numbers.THREE + d.d + numbers.FOUR + e.e + numbers.NINE + b.b + numbers.ZERO + numbers.FIVE;
    }

    public static String getvehicletrack() {
        return v.v + e.e + h.h + i.i + c.c + l.l + e.e + t.t + r.r + a.a + c.c + k.k;
    }

    public static String getmembocool() {
        return m.m + e.e + m.m + b.b + o.o + c.c + o.o + o.o + l.l;
    }

    public static String getaspx() {
        return symbols.dots+a.a+s.s+p.p+ x.x;
    }

    public static String getmethod() {
        return m.m+e.e+t.t+h.h+o.o+d.d+symbols.equal;
    }
    public static String getUpdate() {
        return u.U+p.p+d.d+a.a+t.t+e.e;
    }
    public static String getLock() {
        return l.L+o.o+c.c+k.k;
    }
    public static String getInsert() {
        return i.I+n.n+s.s+e.e+r.r+t.t;
    }

}
