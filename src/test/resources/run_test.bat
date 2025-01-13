@echo off
set TEST_NAME=%1

echo ==== Running Test %TEST_NAME% ====

:: Display Input from test1.json
echo.
echo Input:
type %TEST_NAME%.json
echo.

:: Run the test (calling your API and formatting the output)
echo.
echo Output:
curl -X POST "http://localhost:8080/api/transfers/select" -H "Content-Type: application/json" -d @%TEST_NAME%.json -s | python -m json.tool

:: Display Expected Output from test1_output.json
echo.
echo Expected Output:
type %TEST_NAME%_output.json
echo.
