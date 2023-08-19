class ParkingSystem {

    int[] slotsCount;

    public ParkingSystem(int big, int medium, int small) {
        slotsCount = new int[]{big, medium, small};
    }
    
    public boolean addCar(int carType) {
        if (slotsCount[carType - 1] > 0) {
            slotsCount[carType - 1]--;
            return true;
        }
        return false;
    }
}
