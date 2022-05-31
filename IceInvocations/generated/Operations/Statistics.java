//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.7
//
// <auto-generated>
//
// Generated from file `operations.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Operations;

public class Statistics implements java.lang.Cloneable,
                                   java.io.Serializable
{
    public double avg;

    public double median;

    public double min;

    public double max;

    public Statistics()
    {
    }

    public Statistics(double avg, double median, double min, double max)
    {
        this.avg = avg;
        this.median = median;
        this.min = min;
        this.max = max;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Statistics r = null;
        if(rhs instanceof Statistics)
        {
            r = (Statistics)rhs;
        }

        if(r != null)
        {
            if(this.avg != r.avg)
            {
                return false;
            }
            if(this.median != r.median)
            {
                return false;
            }
            if(this.min != r.min)
            {
                return false;
            }
            if(this.max != r.max)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Operations::Statistics");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, avg);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, median);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, min);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, max);
        return h_;
    }

    public Statistics clone()
    {
        Statistics c = null;
        try
        {
            c = (Statistics)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeDouble(this.avg);
        ostr.writeDouble(this.median);
        ostr.writeDouble(this.min);
        ostr.writeDouble(this.max);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.avg = istr.readDouble();
        this.median = istr.readDouble();
        this.min = istr.readDouble();
        this.max = istr.readDouble();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Statistics v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Statistics ice_read(com.zeroc.Ice.InputStream istr)
    {
        Statistics v = new Statistics();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Statistics> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Statistics v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(32);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<Statistics> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(Statistics.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Statistics _nullMarshalValue = new Statistics();

    /** @hidden */
    public static final long serialVersionUID = -2110087156L;
}
