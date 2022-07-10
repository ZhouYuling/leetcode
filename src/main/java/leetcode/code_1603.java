package leetcode;

public class code_1603 {

    class ParkingSystem {

        int[] parkingNums;

        public ParkingSystem(int big, int medium, int small) {
            parkingNums = new int[]{big, medium, small};
        }

        public boolean addCar(int carType) {
            int num = parkingNums[carType - 1];
            if (num > 0) {
                parkingNums[carType - 1] --;
                return Boolean.TRUE;
            }

            return Boolean.FALSE;
        }
    }

}
