# Cheapest Transfer Route

This application is built using Maven and requires **JDK 17**. Additionally, **Python** is needed to display test outputs in a nicely formatted JSON structure.

## Building the Application

1. Clone the repository to your local machine.
2. Open a terminal (e.g., Windows Terminal) and navigate to the project's directory.
3. Build the project by running the following command:  
```bash
mvn clean package
```

## Running the Application

After building, you can run the generated JAR file using this command:  
```bash
java -jar target/CheapestTransferRoute-0.0.1-SNAPSHOT.jar
```

## Testing the Application

1. Ensure the application is running.
2. Navigate to the `src/test/resources` directory, where several test files are located:
   - **Test input files:** `test#.json`
   - **Expected output files:** `test#_output.json`
   - **Batch script for testing:** `run_test.bat`

3. To execute a test, use the following command in the terminal:  
   ```bash
   run_test.bat test1
   ```
   Replace `test1` with the desired test number (e.g., `test8` for the eighth test).  

Each test will display:  
- The test input.  
- The program's output.  
- The expected output (from the `test#_output.json` file).

  run_test.bat uses CURL command and python to display test outputs in a nicely formatted JSON structure.
  ```bash
   curl -X POST "http://localhost:8080/api/transfers/select" -H "Content-Type: application/json" -d @%TEST_NAME%.json -s | python -m json.tool
   ```

This setup allows you to verify the application's correctness against predefined test cases.
