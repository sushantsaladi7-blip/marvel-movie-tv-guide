public class Main {
public static void main(String[] args) {
    IO.println("Marvel Media Guide: Road to Avengers: Doomsday and Avengers: Secret Wars");
    MediaDataLoader loader = new MediaDataLoader();
    MediaManager manager = new MediaManager();
    loader.addFromCSV(manager);
    while (true) {
        IO.println("1 - MCU Release Order");
        IO.println("2 - MCU Chronological Order");
        IO.println("3 - Non-MCU Media");
        IO.println("4 - Road to Secret Wars");
        IO.println("5 - EXIT");
        try {
            int a = Integer.parseInt(IO.readln("Choose list to view:\n"));
            if (!((a == 1) || (a == 2) || (a == 3) || (a == 4) || (a == 5))) {
                IO.println("Invalid input, try again");
            } else {
                if (a == 1) {
                    IO.println(manager.getMCUByReleaseOrder());
                } else if (a == 2) {
                    IO.println(manager.getMCUByChronologyOrder());
                } else if (a == 3) {
                    IO.println(manager.getNonMCU());
                } else if (a == 4) {
                    IO.println(manager.getSecretWarsWatchOrder());
                } else if (a == 5) {
                    IO.println("Exiting");
                    break;
                }
            }
        } catch (NumberFormatException e) {
            IO.println("Invalid input, try again");
        }
    }
}
}
