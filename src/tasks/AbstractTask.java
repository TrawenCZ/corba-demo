package tasks;


/**
* tasks/AbstractTask.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./HW01.idl
* Pond�l�, 6. b�ezna 2023 19:54:28 CET
*/

public interface AbstractTask extends org.omg.CORBA.portable.IDLEntity
{
  String info ();
  tasks.AbstractTask next () throws tasks.TaskException;
} // interface AbstractTask
