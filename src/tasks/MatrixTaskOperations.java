package tasks;


/**
* tasks/MatrixTaskOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ./HW01.idl
* Pond�l�, 6. b�ezna 2023 19:54:28 CET
*/

public interface MatrixTaskOperations  extends tasks.AbstractTask
{
  int getMatrixItem (int row, int col) throws tasks.TaskException;
  void sendResult (int matrixDeterminant);
} // interface MatrixTaskOperations