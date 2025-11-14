

param(
    [string]$MavenVersion = '3.9.4',
    [string]$JavaHome = $null
)

function Write-Info($msg){ Write-Host "[INFO] $msg" -ForegroundColor Cyan }
function Write-Warn($msg){ Write-Host "[WARN] $msg" -ForegroundColor Yellow }
function Write-Err($msg){ Write-Host "[ERROR] $msg" -ForegroundColor Red }

try{
    $tmpZip = Join-Path $env:TEMP "apache-maven-$MavenVersion-bin.zip"
    $destBase = Join-Path $env:USERPROFILE "apache-maven-$MavenVersion"
    $destLink = Join-Path $env:USERPROFILE "apache-maven"

    if(Test-Path $destLink){
        Write-Info "Pasta '$destLink' já existe. Será usada sem baixar novamente."
    } else {
        if(-not (Test-Path $tmpZip)){
            $url = "https://dlcdn.apache.org/maven/maven-3/$MavenVersion/binaries/apache-maven-$MavenVersion-bin.zip"
            Write-Info "Baixando Maven $MavenVersion de $url ..."
            Invoke-WebRequest -Uri $url -OutFile $tmpZip -UseBasicParsing
        } else {
            Write-Info "Arquivo já baixado em $tmpZip"
        }

        if(-not (Test-Path $destBase)){
            Write-Info "Extraindo $tmpZip para $env:USERPROFILE ..."
            Expand-Archive -Path $tmpZip -DestinationPath $env:USERPROFILE -Force
        }

        if(Test-Path $destBase -and -not (Test-Path $destLink)){
            Write-Info "Criando link simbólico (rename) $destBase -> $destLink"
            try{ Rename-Item -Path $destBase -NewName 'apache-maven' -ErrorAction Stop } catch { Write-Warn "Não foi possível renomear automaticamente: $_" }
        }
    }

 
    $sessionMaven = Join-Path $env:USERPROFILE 'apache-maven'
    if(-not (Test-Path $sessionMaven)){
        Write-Err "Não foi possível localizar $sessionMaven. Verifique se o download/extracao ocorreu corretamente."; return
    }

    if($JavaHome){
        if(Test-Path $JavaHome){
            Write-Info "Configurando JAVA_HOME para esta sessão: $JavaHome"
            $env:JAVA_HOME = $JavaHome
            $env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
        } else {
            Write-Warn "Caminho informado para -JavaHome não existe: $JavaHome"
        }
    }

    $env:MAVEN_HOME = $sessionMaven
    $env:PATH = "$env:MAVEN_HOME\bin;$env:PATH"

    Write-Info "Maven disponível nesta sessão a partir de: $env:MAVEN_HOME"
    Write-Info "Java (session) = $env:JAVA_HOME"

    Write-Info "Verificando versões..."
    & mvn -version 2>$null | ForEach-Object { Write-Host $_ }

    if($LASTEXITCODE -ne 0){
        Write-Warn "`mvn` retornou código $LASTEXITCODE. Se houver falha, verifique se o Java está disponível (java -version)."
    } else {
        Write-Info "Maven pronto — você pode rodar: mvn spring-boot:run dentro de backend."
    }

} catch {
    Write-Err "Erro durante setup: $_"
}
