package com.mmd;
import java.util.*;

public interface ISaveable {
    List<String> write();
    void read(List<String> list);
}
