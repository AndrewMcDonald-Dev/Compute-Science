import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Airplane {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Amount of minutes to land: ");
		int timeToLand = scan.nextInt();
		System.out.print("Amount of minutes to take off: ");
		int timeToTakeoff = scan.nextInt();
		System.out.print("Average amount of time between planes to land: ");
		int avgTimeLand = scan.nextInt();
		System.out.print("Average amount of time between planes to take off: ");
		int avgTimeTakeoff = scan.nextInt();
		System.out.print("Maximum amount of time in the air before crashing: ");
		int maxTimeInAir = scan.nextInt();
		System.out.print("Total simulation minutes: ");
		int totalTime = scan.nextInt();

		scan.close();

		Queue<Integer> landingPlanes = new LinkedList<Integer>();
		Queue<Integer> leavingPlanes = new LinkedList<Integer>();

		int planesLanded = 0;
		int planesLeft = 0;
		int crashedPlanes = 0;

		double totalWaitLanding = 0;
		double totalWaitLeaving = 0;

		int operationTime = 0;
		// main loop
		for (int i = 0; i < totalTime; i++) {
			// adds planes to queues
			if (Math.random() < 1.0 / avgTimeLand)
				landingPlanes.add(i);
			if (Math.random() < 1.0 / avgTimeTakeoff)
				leavingPlanes.add(i);

			// simulates landing and taking off
			if (operationTime > 0)
				operationTime--;
			else {
				while (landingPlanes.peek() != null) {
					int newPlane = landingPlanes.poll();
					if (i - newPlane > maxTimeInAir)
						crashedPlanes++;
					else {
						planesLanded++;
						totalWaitLanding += i - newPlane;
						operationTime = timeToLand - 1;
						break;
					}
				}
				if (leavingPlanes.peek() != null && operationTime == 0) {
					int newPlane = leavingPlanes.poll();
					planesLeft++;
					totalWaitLeaving += i - newPlane;
					operationTime = timeToTakeoff - 1;
				}
			}
		}
		while (landingPlanes.peek() != null) {
			int newPlane = landingPlanes.poll();
			if (totalTime - newPlane > maxTimeInAir) {
				crashedPlanes++;
			}
		}
		System.out.println();
		System.out.println("Number of planes taken off: " + planesLeft);
		System.out.println("Number of planes landed: " + planesLanded);
		System.out.println("Number of planes crashed: " + crashedPlanes);
		System.out.println(
				"Average waiting time for taking off: " + Math.round((totalWaitLeaving / planesLeft) * 100.0) / 100.0
						+ " minutes");
		System.out.println(
				"Average waiting time for landing: " + Math.round((totalWaitLanding / planesLanded) * 100.0) / 100.0
						+ " minutes");
	}
}