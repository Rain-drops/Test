// ITimberService.aidl
package com.sgj.ayibang;

// Declare any non-default types here with import statements

interface ITimberService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */


    void openFile(String path);
    void open(in long [] list, int position, long sourceId, int sourctType);
    void stop();
    void play();
    void next();
}
