/**
 * Commmand Line Utility
 */
public class TopSecret {
    private ProgramControl controller;

    public TopSecret(ProgramControl controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {

        FileHandler fileHandler = new FileHandler();
        CipherService cipherService = new CipherService();

        ProgramControl controller = new ProgramControl(fileHandler, cipherService);

        TopSecret app = new TopSecret(controller);

        app.run(args);
    }

    public void run(String[] args) {
        try {
            if (args.length == 0) {
                controller.getNumberedFileList()
                        .forEach(System.out::println);
            }
            else if (args.length == 1) {
                String result = controller.getFileByNumber(args[0], null);
                System.out.println(result);
            }
            else if (args.length == 2) {
                String result = controller.getFileByNumber(args[0], args[1]);
                System.out.println(result);
            }
            else {
                throw new IllegalArgumentException("Invalid arguments");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
