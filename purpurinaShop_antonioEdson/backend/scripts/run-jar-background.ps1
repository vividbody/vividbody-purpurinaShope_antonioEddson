<#
run-jar-background.ps1

Roda o JAR do backend em background (como job do PowerShell) e grava saída em log.

Uso:
  # No diretório `backend`
  .\scripts\run-jar-background.ps1

Parâmetros:
  -JarPath : caminho para o JAR (padrão: target\purpurina-backend-0.0.1-SNAPSHOT.jar)

Observações:
  - Se o JAR não existir, execute `mvn -DskipTests package` antes.
#>

param(
    [string]$JarPath = "target\purpurina-backend-0.0.1-SNAPSHOT.jar"
)

function Write-Info($m){ Write-Host "[INFO] $m" -ForegroundColor Cyan }
function Write-Warn($m){ Write-Host "[WARN] $m" -ForegroundColor Yellow }

if(-not (Test-Path $JarPath)){
    Write-Warn "JAR não encontrado em: $JarPath"
    Write-Host "Execute: mvn -DskipTests package"
    exit 1
}

$jarFull = (Resolve-Path $JarPath).Path
$log = Join-Path $env:USERPROFILE "purpurina-backend.log"

Write-Info "Iniciando $jarFull como job em background. Log: $log"

$job = Start-Job -ScriptBlock {
    param($jar,$log)
    & java -jar $jar 2>&1 | Out-File -FilePath $log -Encoding utf8
} -ArgumentList $jarFull,$log

Write-Info "Job iniciado: Id=$($job.Id) Name=$($job.Name)"
Write-Info "Ver logs em: $log"
Write-Info "Para parar: Get-Job -Id $($job.Id) | Stop-Job; Get-Process java | Where-Object { $_.Path -eq (Get-Command java).Source } | Stop-Process"
