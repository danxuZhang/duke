package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.data.task.Task;
import duke.data.TaskList;
import duke.data.exception.DuplicateTaskException;
import duke.parser.CsvParser;


/**
 * Represents the file used to store user data (task list).
 */
public final class StorageFile {
    /** Default file directory used to store user data. */
    private static final String DEFAULT_DIR = "data";
    /** Default file name used to store user data. */
    private static final String DEFAULT_FILE = "duke.csv";
    /** Default file path (dir + file name) used to  store user data. */
    private static final String DEFAULT_FILE_PATH = DEFAULT_DIR + "/" + DEFAULT_FILE;
    /** Path used for storing user data.*/
    private final Path path;

    public StorageFile() {
        this(DEFAULT_FILE_PATH);
    }

    public StorageFile(String filePath) {
        path = Paths.get(filePath);
    }

    public String getPath() {
        return path.toString();
    }

    /**
     * Writes the task list to a CSV file.
     * @param taskList task list to be written.
     * @throws IOException when the storage file cannot be found or opened.
     */
    public void writeCsv(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path.toFile());
        for (Task task : taskList) {
            fw.write(task.toCSV() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Initializes the CSV file used for storing task list, creates one if there does not exist.
     */
    public void initCsv() {
        File directory = new File(DEFAULT_DIR);
        try {
            assert directory.mkdir();
        } catch (AssertionError ae) {
            System.err.println("Directory already exists.");
        }
        File file = new File(DEFAULT_DIR + "/" + DEFAULT_FILE);
        try {
            assert file.createNewFile();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            ioe.printStackTrace();
        } catch (AssertionError ae){
            System.err.println("File already exists.");
        }
    }

    /**
     * Loads task list from CSV file.
     * @param taskList task list to be loaded.
     * @return number of lines successfully loaded.
     * @throws FileNotFoundException if the csv file is empty.
     */
    public int loadCsv(TaskList taskList) throws FileNotFoundException {
        File file = new File(path.toFile().toURI());
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner fileScanner = new Scanner(file);
        int cnt = 0;
        int cnt_success = 0;
        while (fileScanner.hasNext()) {
            cnt += 1;
            try {
                taskList.addTask(new CsvParser().parseLine(fileScanner.nextLine()));
                cnt_success += 1;
            } catch (DuplicateTaskException | IllegalArgumentException e) {
                System.err.printf("Failed to load line %d.\n", cnt);
            }
        }
        return cnt_success;
    }

}
