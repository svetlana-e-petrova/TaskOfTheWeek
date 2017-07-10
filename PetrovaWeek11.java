package week11;

import java.util.PriorityQueue;

/**
 * Created by sepetrova on 10.07.17.
 */
public class PetrovaWeek11 {
    private static PriorityQueue<Step> distances = new PriorityQueue<>();
    private static int boxesCount;

    private int[] start;
    private int end[];

    public PetrovaWeek11(int[] start, int[] end, int count) {
        this.start = start;
        this.end = end;
        boxesCount = count;
    }

    public static void main(String[] args) {
        PetrovaWeek11 solution = new PetrovaWeek11(new int[]{0, 1, 0}, new int[]{4, 5, 1}, 10);
        solution.doWork(0, 0, 0, 0);
        System.out.println(solution.findMinDistance(distances));

    }

    private int findMinDistance(PriorityQueue<Step> queue) {
        int lastLevel = start.length - 1;
        int result = boxesCount * start.length;
        while (queue.peek().getLevel() == lastLevel) {
            int dist = queue.poll().getDistance();
            result = result <= dist ? result : dist;
        }
        return result;
    }

    public void doWork(int cpR1, int cpR2, int level, int prevDistance) {
        if (level < start.length) {
            int distance = Math.abs(cpR1 - start[level]) + Math.abs(start[level] - end[level]);
            distances.offer(new Step(distance + prevDistance, level));
            doWork(end[level], cpR2, level + 1, distance + prevDistance);
            distance = Math.abs(cpR2 - start[level]) + Math.abs(start[level] - end[level]);
            distances.offer(new Step(distance + prevDistance, level));
            doWork(cpR1, end[level], level + 1, distance + prevDistance);
        } else {
        }
    }

    class Step implements Comparable<Step> {
        int distance;
        int level;

        public Step(int distance, int level) {
            this.distance = distance;
            this.level = level;
        }

        public int getDistance() {
            return distance;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public int compareTo(Step s) {
            return s.getLevel() - this.getLevel();
        }
    }

}
