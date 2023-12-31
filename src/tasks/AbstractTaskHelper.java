package tasks;


/**
* tasks/AbstractTaskHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./HW01.idl
* Pond�l�, 6. b�ezna 2023 19:54:28 CET
*/

abstract public class AbstractTaskHelper
{
  private static String  _id = "IDL:tasks/AbstractTask:1.0";

  public static void insert (org.omg.CORBA.Any a, tasks.AbstractTask that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static tasks.AbstractTask extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (tasks.AbstractTaskHelper.id (), "AbstractTask");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static tasks.AbstractTask read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (((org.omg.CORBA_2_3.portable.InputStream)istream).read_abstract_interface (_AbstractTaskStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, tasks.AbstractTask value)
  {
    ((org.omg.CORBA_2_3.portable.OutputStream)ostream).write_abstract_interface ((java.lang.Object) value);
  }

  public static tasks.AbstractTask narrow (java.lang.Object obj)  {
    if (obj == null)
      return null;
    else if (obj instanceof tasks.AbstractTask)
      return (tasks.AbstractTask)obj;
    else if ((obj instanceof org.omg.CORBA.portable.ObjectImpl) &&
             (((org.omg.CORBA.Object)obj)._is_a (id ()))) {
      org.omg.CORBA.portable.ObjectImpl impl = (org.omg.CORBA.portable.ObjectImpl)obj ;
      org.omg.CORBA.portable.Delegate delegate = impl._get_delegate() ;
      tasks._AbstractTaskStub stub = new tasks._AbstractTaskStub ();
      stub._set_delegate(delegate);
      return stub;
    }
    throw new org.omg.CORBA.BAD_PARAM ();
  }


  public static tasks.AbstractTask unchecked_narrow (java.lang.Object obj)  {
    if (obj == null)
      return null;
    else if (obj instanceof tasks.AbstractTask)
      return (tasks.AbstractTask)obj;
    else if (obj instanceof org.omg.CORBA.portable.ObjectImpl) {
      org.omg.CORBA.portable.ObjectImpl impl = (org.omg.CORBA.portable.ObjectImpl)obj ;
      org.omg.CORBA.portable.Delegate delegate = impl._get_delegate() ;
      tasks._AbstractTaskStub stub = new tasks._AbstractTaskStub ();
      stub._set_delegate(delegate);
      return stub;
    }
    throw new org.omg.CORBA.BAD_PARAM ();
  }


}
