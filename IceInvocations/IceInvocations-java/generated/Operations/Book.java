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

public class Book implements java.lang.Cloneable,
                             java.io.Serializable
{
    public String bookName;

    public Author author;

    public long length;

    public Image bookImage;

    public Book()
    {
        this.bookName = "";
        this.author = new Author();
        this.bookImage = new Image();
    }

    public Book(String bookName, Author author, long length, Image bookImage)
    {
        this.bookName = bookName;
        this.author = author;
        this.length = length;
        this.bookImage = bookImage;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Book r = null;
        if(rhs instanceof Book)
        {
            r = (Book)rhs;
        }

        if(r != null)
        {
            if(this.bookName != r.bookName)
            {
                if(this.bookName == null || r.bookName == null || !this.bookName.equals(r.bookName))
                {
                    return false;
                }
            }
            if(this.author != r.author)
            {
                if(this.author == null || r.author == null || !this.author.equals(r.author))
                {
                    return false;
                }
            }
            if(this.length != r.length)
            {
                return false;
            }
            if(this.bookImage != r.bookImage)
            {
                if(this.bookImage == null || r.bookImage == null || !this.bookImage.equals(r.bookImage))
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
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Operations::Book");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, bookName);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, author);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, length);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, bookImage);
        return h_;
    }

    public Book clone()
    {
        Book c = null;
        try
        {
            c = (Book)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.bookName);
        Author.ice_write(ostr, this.author);
        ostr.writeLong(this.length);
        Image.ice_write(ostr, this.bookImage);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.bookName = istr.readString();
        this.author = Author.ice_read(istr);
        this.length = istr.readLong();
        this.bookImage = Image.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Book v)
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

    static public Book ice_read(com.zeroc.Ice.InputStream istr)
    {
        Book v = new Book();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Book> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Book v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Book> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Book.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Book _nullMarshalValue = new Book();

    /** @hidden */
    public static final long serialVersionUID = 1998754605L;
}
