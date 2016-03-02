// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `sensor.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Assignment;

public interface SensorPrx extends Ice.ObjectPrx
{
    public void readFile();

    public void readFile(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_readFile();

    public Ice.AsyncResult begin_readFile(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_readFile(Ice.Callback __cb);

    public Ice.AsyncResult begin_readFile(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_readFile(Callback_Sensor_readFile __cb);

    public Ice.AsyncResult begin_readFile(java.util.Map<String, String> __ctx, Callback_Sensor_readFile __cb);

    public void end_readFile(Ice.AsyncResult __result);
}