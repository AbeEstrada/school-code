package com.banuane.helpers;

import java.io.*;
import java.util.*;
import java.text.*;

public class Today {
    public String getString() {
        Date now = new Date ();
        SimpleDateFormat _format = new SimpleDateFormat("dd/MM/yyyy");
        String today = new StringBuilder(_format.format(now)).toString();
        return today;
    }
}