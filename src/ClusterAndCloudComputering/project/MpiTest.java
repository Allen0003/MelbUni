package ClusterAndCloudComputering.project;

import mpi.*;

public class MpiTest {

	public static void main(String[] args) {
		MPI.Init(args); // init mpi

		int me = MPI.COMM_WORLD.Rank();
		// where you are and turns to this process
		int size = MPI.COMM_WORLD.Size(); // how many processes

		System.out.println("Hi ~  from <" + me + "> size = " + size);
		
		
		System.out.println("cabi");
		
		MPI.Finalize(); // close mpi

		// MPI.SEND_OVERHEAD //send to receive.
		// MPI.RECV_OVERHEAD //receive from send

	}
}
