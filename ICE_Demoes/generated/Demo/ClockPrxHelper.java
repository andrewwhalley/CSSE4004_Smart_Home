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
// Generated from file `Clock.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Demo;

public final class ClockPrxHelper extends Ice.ObjectPrxHelperBase implements ClockPrx
{
    private static final String __tick_name = "tick";

    public void tick(String time)
    {
        tick(time, null, false);
    }

    public void tick(String time, java.util.Map<String, String> __ctx)
    {
        tick(time, __ctx, true);
    }

    private void tick(String time, java.util.Map<String, String> __ctx, boolean __explicitCtx)
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "tick", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __delBase = __getDelegate(false);
                    _ClockDel __del = (_ClockDel)__delBase;
                    __del.tick(time, __ctx, __observer);
                    return;
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_tick(String time)
    {
        return begin_tick(time, null, false, null);
    }

    public Ice.AsyncResult begin_tick(String time, java.util.Map<String, String> __ctx)
    {
        return begin_tick(time, __ctx, true, null);
    }

    public Ice.AsyncResult begin_tick(String time, Ice.Callback __cb)
    {
        return begin_tick(time, null, false, __cb);
    }

    public Ice.AsyncResult begin_tick(String time, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_tick(time, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_tick(String time, Callback_Clock_tick __cb)
    {
        return begin_tick(time, null, false, __cb);
    }

    public Ice.AsyncResult begin_tick(String time, java.util.Map<String, String> __ctx, Callback_Clock_tick __cb)
    {
        return begin_tick(time, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_tick(String time, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __tick_name, __cb);
        try
        {
            __result.__prepare(__tick_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(time);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public void end_tick(Ice.AsyncResult __result)
    {
        __end(__result, __tick_name);
    }

    public static ClockPrx checkedCast(Ice.ObjectPrx __obj)
    {
        ClockPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ClockPrx)
            {
                __d = (ClockPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    ClockPrxHelper __h = new ClockPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ClockPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        ClockPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ClockPrx)
            {
                __d = (ClockPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    ClockPrxHelper __h = new ClockPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ClockPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ClockPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    ClockPrxHelper __h = new ClockPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ClockPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        ClockPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    ClockPrxHelper __h = new ClockPrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ClockPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        ClockPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ClockPrx)
            {
                __d = (ClockPrx)__obj;
            }
            else
            {
                ClockPrxHelper __h = new ClockPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static ClockPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ClockPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            ClockPrxHelper __h = new ClockPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::Demo::Clock",
        "::Ice::Object"
    };

    public static String ice_staticId()
    {
        return __ids[0];
    }

    protected Ice._ObjectDelM __createDelegateM()
    {
        return new _ClockDelM();
    }

    protected Ice._ObjectDelD __createDelegateD()
    {
        return new _ClockDelD();
    }

    public static void __write(IceInternal.BasicStream __os, ClockPrx v)
    {
        __os.writeProxy(v);
    }

    public static ClockPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ClockPrxHelper result = new ClockPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
