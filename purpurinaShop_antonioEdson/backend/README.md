# Backend — PurpurinaShop

Como compilar e rodar (Windows PowerShell):

1. Navegue até a pasta do backend:
```
cd purpurinaShop_antonioEdson\backend
```

2. Compilar com Maven:
```powershell
mvn -U -DskipTests package
```

3. Rodar:
```powershell
mvn spring-boot:run
```

Observações:

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
