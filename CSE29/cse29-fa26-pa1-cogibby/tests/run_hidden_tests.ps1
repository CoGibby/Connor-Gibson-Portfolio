$ErrorActionPreference = "Stop"

$repoRoot = Split-Path -Parent $PSScriptRoot
$analyzer = Join-Path $repoRoot "tests\utf8analyzer-hidden.exe"
$unitTests = Join-Path $repoRoot "tests\hidden_edge_tests.exe"

Push-Location $repoRoot
gcc -std=c11 -Wall -Wextra -Wpedantic -I. utf8.c tests\hidden_edge_tests.c -o tests\hidden_edge_tests.exe
$buildResult = $LASTEXITCODE
gcc -std=c11 main.c utf8.c -o tests\utf8analyzer-hidden.exe
if ($buildResult -ne 0 -or $LASTEXITCODE -ne 0) { Pop-Location; exit 1 }
Pop-Location

& $unitTests
$unitResult = $LASTEXITCODE

$testDirectory = Join-Path $env:TEMP "utf8-hidden-tests"
New-Item -ItemType Directory -Force -Path $testDirectory | Out-Null
$emptyInput = Join-Path $testDirectory "empty.txt"
$longInput = Join-Path $testDirectory "1024-ascii-bytes.txt"

[IO.File]::WriteAllBytes($emptyInput, [byte[]]::new(0))
[byte[]]$longBytes = New-Object byte[] 1024
for ($byteIndex = 0; $byteIndex -lt 1024; $byteIndex++) { $longBytes[$byteIndex] = 98 }
[IO.File]::WriteAllBytes($longInput, $longBytes)

Push-Location $repoRoot
$emptyOutput = cmd /c "tests\utf8analyzer-hidden.exe < $emptyInput"
$longOutput = cmd /c "tests\utf8analyzer-hidden.exe < $longInput"
Pop-Location

Write-Host "Empty input output:"
$emptyOutput

$longLengthLine = $longOutput | Where-Object { $_ -like "Length in bytes:*" }
if ($longLengthLine -ne "Length in bytes: 1024") {
    Write-Host "FAIL long-input boundary: expected 'Length in bytes: 1024', got '$longLengthLine'"
    $unitResult++
}

exit $unitResult
