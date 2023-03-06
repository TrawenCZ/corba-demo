import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import tasks.*;

public class Client {
	// Run this class as "java -cp . Client <server object IOR>"

	public static Point[] switchXandYaxis(int numberOfPoints, Point[] polyLine) {
		Point[] switchedPolyLine = new Point[numberOfPoints];
		for (int i = 0; i < numberOfPoints; i++) {
			switchedPolyLine[i] = new Point(polyLine[i].y, polyLine[i].x);
		}
		return switchedPolyLine;
	}

	public static void main(String[] args) throws Exception {
		ORB orb = ORB.init(args, null);

		/*
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		DateProg obj = DateProgHelper.narrow(ncRef.resolve_str("DateProg"));
		*/

		InitialTask initTask = InitialTaskHelper.narrow(orb.string_to_object(args[0]));
		AbstractTask firstTask = initTask.init("511768@mail.muni.cz");
		System.out.println("First task info: " + firstTask.info());

		SimpleTask firstTaskRetype = SimpleTaskHelper.narrow(firstTask);
		while (!firstTaskRetype.isReady()) {
			continue;
		}

		AbstractTask secondTaskAbstract = firstTaskRetype.next();
		System.out.println("Second task info: " + secondTaskAbstract.info());


		AdderTask secondTaskRetype = AdderTaskHelper.narrow(secondTaskAbstract);
		secondTaskRetype.result(secondTaskRetype.a() + secondTaskRetype.b());

		AbstractTask thirdTaskAbstract = secondTaskRetype.next();
		System.out.println("Third task info: " + thirdTaskAbstract.info());

		MatrixTask thirdTaskRetype = MatrixTaskHelper.narrow(thirdTaskAbstract);
		int matrixSize = thirdTaskRetype.matrixSize;
		int[][] matrix = new int[matrixSize][matrixSize];
		int det = 0;
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				matrix[i][j] = thirdTaskRetype.getMatrixItem(i, j);
			}
		}
		for (int i = 0; i < matrixSize; i++) {
			det += (matrix[0][i] * (matrix[1][(i + 1) % matrixSize] * matrix[2][(i + 2) % matrixSize] - matrix[1][(i + 2) % matrixSize] * matrix[2][(i + 1) % matrixSize]));
		}
		thirdTaskRetype.sendResult(det);

		AbstractTask fourthTaskAbstract = thirdTaskRetype.next();
		System.out.println("Fourth task info: " + fourthTaskAbstract.info());

		PolygonTask fourthTaskRetype = PolygonTaskHelper.narrow(fourthTaskAbstract);
		Point[] polyLine = fourthTaskRetype.getPolyLine();
		float distance = 0;
		for (int i = 0; i < polyLine.length - 1; i++) {
			distance += Math.sqrt(Math.pow(polyLine[i].x - polyLine[i + 1].x, 2) + Math.pow(polyLine[i].y - polyLine[i + 1].y, 2));
		}
		fourthTaskRetype.sendResult(distance);

		AbstractTask fifthTaskAbstract = fourthTaskRetype.next();
		System.out.println("Fifth task info: " + fifthTaskAbstract.info());

		FlipLineTask fifthTaskRetype = FlipLineTaskHelper.narrow(fifthTaskAbstract);

		IntHolder lengthDifference = new IntHolder();
		int numberOfPoints = 0;
		fifthTaskRetype.update(switchXandYaxis(numberOfPoints, polyLine), lengthDifference);
		while (lengthDifference.value != 0) {
			numberOfPoints++;
			fifthTaskRetype.update(switchXandYaxis(numberOfPoints, polyLine), lengthDifference);
		}

		try {
			AbstractTask sixthTaskAbstract = fifthTaskRetype.next();
		} catch (TaskException e) {
			System.out.println("Task exception: " + e.message);
		}
	}
}
