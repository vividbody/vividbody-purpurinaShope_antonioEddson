# Backend — PurpurinaShop

## Requisitos

- **Java 21** (LTS) - Temurin/Adoptium OpenJDK 21.0.5+
- **Maven 3.9.6+** (ou use o Maven Wrapper incluído)

## Como compilar e rodar (Windows PowerShell)

### Opção 1: Usando Maven Wrapper (Recomendado)

O projeto inclui o Maven Wrapper que baixa e usa o Maven automaticamente:

1. Navegue até a pasta do backend:
```powershell
cd purpurinaShop_antonioEdson\backend
```

2. Configure o Java 21 (se necessário):
```powershell
$env:JAVA_HOME = "$PWD\.jdk\jdk-21.0.5+11"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

3. Compilar:
```powershell
.\mvnw.cmd -U clean package
```

4. Rodar:
```powershell
java -jar .\target\purpurina-backend-0.0.1-SNAPSHOT.jar
```

Ou use:
```powershell
.\mvnw.cmd spring-boot:run
```

### Opção 2: Usando Maven Global

Se você tem Maven instalado globalmente:

```powershell
mvn -version  # Verificar versão
mvn -U clean package
mvn spring-boot:run
```

## JDK 21 Local

O projeto inclui o JDK 21 em `.jdk/jdk-21.0.5+11/` para desenvolvimento local. Para usá-lo:

```powershell
$env:JAVA_HOME = "$PWD\.jdk\jdk-21.0.5+11"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -version  # Deve mostrar: openjdk version "21.0.5"
```

## Script auxiliar (Windows)

Caso você não tenha Maven instalado globalmente, há um script que baixa o Maven e o configura apenas para a sessão PowerShell.

1. Abra PowerShell na pasta `backend` e execute:

```powershell
.\scripts\setup-maven.ps1
```

2. Depois rode:

```powershell
mvn -version
mvn spring-boot:run
```
