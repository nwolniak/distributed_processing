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

public class Author implements java.lang.Cloneable,
                               java.io.Serializable
{
    public String firstName;

    public String secondName;

    public Author()
    {
        this.firstName = "";
        this.secondName = "";
    }

    public Author(String firstName, String secondName)
    {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Author r = null;
        if(rhs instanceof Author)
        {
            r = (Author)rhs;
        }

        if(r != null)
        {
            if(this.firstName != r.firstName)
            {
                if(this.firstName == null || r.firstName == null || !this.firstName.equals(r.firstName))
                {
                    return false;
                }
            }
            if(this.secondName != r.secondName)
            {
                if(this.secondName == null || r.secondName == null || !this.secondName.equals(r.secondName))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Operations::Author");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, firstName);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, secondName);
        return h_;
    }

    public Author clone()
    {
        Author c = null;
        try
        {
            c = (Author)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.firstName);
        ostr.writeString(this.secondName);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.firstName = istr.readString();
        this.secondName = istr.readString();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Author v)
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

    static public Author ice_read(com.zeroc.Ice.InputStream istr)
    {
        Author v = new Author();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Author> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Author v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Author> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Author.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Author _nullMarshalValue = new Author();

    /** @hidden */
    public static final long serialVersionUID = -1359484942L;
}
