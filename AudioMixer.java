public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    // No-argument constructor using this() chaining
    public AudioMixer() {
        this("StandardMix-8", 8, false); // calls 3-parameter constructor
    }

    // Two-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false); // bluetooth disabled
    }

    // Three-parameter constructor using this() chaining
    public AudioMixer(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0); // default max volume
    }

    // Main constructor - all parameters
    public AudioMixer(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;
        System.out.println(">> Constructor executed for model: " + mixerModel);
    }

    // Method to connect device
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }

    // Method to display mixer status
    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");

        // Create mixer using no-argument constructor
        AudioMixer mixer1 = new AudioMixer();

        // Create mixer using two-parameter constructor
        AudioMixer mixer2 = new AudioMixer("CompactMix-4", 4);

        // Create mixer using three-parameter constructor
        AudioMixer mixer3 = new AudioMixer("LiveMix-12", 12, true);

        // Create mixer using full constructor
        AudioMixer mixer4 = new AudioMixer("ProMix-16", 16, true, 150.0);

        // Connect devices
        mixer1.connectDevice("Microphone");
        mixer1.connectDevice("Guitar");

        mixer2.connectDevice("Keyboard");
        mixer2.connectDevice("Drum Machine");

        mixer3.connectDevice("DJ Console");
        mixer3.connectDevice("Laptop");
        mixer3.connectDevice("Synthesizer");

        mixer4.connectDevice("Electric Guitar");
        mixer4.connectDevice("Bass");
        mixer4.connectDevice("Vocal Mic");
        mixer4.connectDevice("Sampler");

        // Display status
        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();

        // Comment on constructor chaining
        System.out.println("\n=== NOTE ON CONSTRUCTOR CHAINING ===");
        System.out.println("No-arg constructor -> calls 3-param constructor -> calls 4-param constructor.");
        System.out.println("2-param constructor -> calls 3-param constructor -> calls 4-param constructor.");
        System.out.println("3-param constructor -> calls 4-param constructor.");
        System.out.println("4-param constructor -> initializes all fields directly.");
    }
}
