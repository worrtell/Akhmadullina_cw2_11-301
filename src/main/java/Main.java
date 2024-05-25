import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) throws IOException {
        String name = "C:/Users/Dell/IdeaProjects/Akhmadullina_cw2_11-301/src/main/";
        String[] filenames = new String[]{name + "2be78862-eebb-46ee-a191-6f90edf8c625", name + "2ed42393-34a9-4671-9eea-0da2e56ee9f8", name + "7b6715c3-dd97-4c3c-9975-09e703e4c198", name + "72aa39bd-3954-43d1-a597-af7c40cbb1a4",  name + "bd4bec47-0242-48b7-a0e5-244f3c9a2df0",  name + "d52690e9-6ec5-42af-bf37-377edfd1cdb7",  name + "f9eb3da3-1cee-4ee7-98b9-3599bebc1518", name + "f365e1c1-4a86-4f48-a34d-c04bcba642df"};

        FileWriter logFile = new FileWriter("v26.log");
        FileOutputStream fos = new FileOutputStream("v26.png");

        try (ExecutorService es = Executors.newFixedThreadPool(4)) {
            for (String filename : filenames) {
                es.submit(new Task(filename, logFile, fos));
            }
            es.shutdown();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}