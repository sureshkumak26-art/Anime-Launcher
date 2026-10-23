# Anime Launcher for Windows

This is a separate native desktop foundation for Windows 10/11 x64. It does not replace or modify the existing Android launcher modules.

## Build on Windows

Install JDK 17 or newer and use the Gradle wrapper from the repository root if available, or install Gradle 8.13+.

From PowerShell:

```powershell
cd windows-desktop
gradle packageExe
```

The installer is produced under `build/compose/binaries/main/exe/`.

## Current scope

The first milestone provides an Anime Launcher-branded Windows desktop shell, a game-directory selector/opener, and a link to the project repository. It is not yet a feature-complete Minecraft launcher. Microsoft account sign-in, Minecraft version/mod installation, Java runtime provisioning, renderer integration, and game process launching must be ported and tested separately before describing it as feature-complete.

The existing Android project and its build workflow are intentionally left unchanged.
