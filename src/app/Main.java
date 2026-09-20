package app;
import gui.GUIFactory;
import gui.WindowsFactory;
import gui.MacOSFactory;
import logistics.Logistics;
import logistics.RoadLogistics;
import logistics.SeaLogistics;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Delivery Mode (ROAD/SEA):");
        String deliveryModeInput = readInput(scanner);

        System.out.println("Enter UI platform (WINDOWS/MACOS)");
        String platformInput = readInput(scanner);

        Logistics logistics = selectLogistics(deliveryModeInput);
        if (logistics == null) {
            System.err.println("Error: Unsupported or missing delivery mode = `" + deliveryModeInput + "`. Valid options : ROAD, SEA.");
            return;
        }
        GUIFactory guiFactory = selectGUIFactory(platformInput);
        if (guiFactory == null) {
            System.err.println("Error: Unsupported or missing UI platform: `" + platformInput + "`. Valid options; WINDOWS, MACOS.");
            return;
        }
        System.out.println("\n--- Application Configuration Successful --- ");
        System.out.println("Delivery mode: " + deliveryModeInput.toUpperCase());
        System.out.println("UI platform: " + platformInput.toUpperCase());

        DeliveryApplication app = new DeliveryApplication(guiFactory, logistics);

        String cargo = "Laboratory equipment";
        String destination = "Aktau warehouse";

        app.renderAndDeliver(cargo, destination);
    }

    private static String readInput(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return "";
        };
        return scanner.nextLine().trim();
    }

    private static Logistics selectLogistics(String mode) {
        if (mode == null || mode.isEmpty()) {
            return null;
        }
        return switch (mode.toUpperCase()){
            case "ROAD" -> new RoadLogistics();
            case "SEA" -> new SeaLogistics();
            default -> null;
        };
    }



    private static GUIFactory selectGUIFactory(String platform) {
        if (platform == null || platform.isEmpty()){
            return null;
        }
        return switch (platform.toUpperCase()){
            case "WINDOWS" -> new WindowsFactory();
            case "MACOS" -> new MacOSFactory();
            default -> null;
        };
    }
}


