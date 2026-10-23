package com.animecloud.animelauncher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Desktop
import java.io.File

private val Bg = Color(0xFF0B0D12)
private val Panel = Color(0xFF151923)
private val Mint = Color(0xFF98FCE4)
private val Muted = Color(0xFF9AA4B5)

fun main() = androidx.compose.ui.window.singleWindowApplication(
    title = "Anime Launcher",
    state = androidx.compose.ui.window.rememberWindowState(width = 1100.dp, height = 720.dp)
) {
    var status by remember { mutableStateOf("Windows desktop preview — game launching is not wired up yet.") }
    var gameDirectory by remember { mutableStateOf(defaultGameDirectory().absolutePath) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Bg,
            surface = Panel,
            primary = Mint,
            onPrimary = Color(0xFF06110F),
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Row(Modifier.fillMaxSize().background(Bg)) {
            Column(
                Modifier.width(235.dp).fillMaxHeight().background(Panel).padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text("ANIME", color = Mint, fontSize = 27.sp)
                Text("LAUNCHER", color = Color.White, fontSize = 20.sp)
                Spacer(Modifier.height(10.dp))
                Text("HOME", color = Mint)
                Text("VERSIONS", color = Muted)
                Text("MODS", color = Muted)
                Text("ACCOUNTS", color = Muted)
                Spacer(Modifier.weight(1f))
                Text("WINDOWS x64", color = Muted, fontSize = 12.sp)
                Text("Desktop foundation 0.1.0", color = Muted, fontSize = 11.sp)
            }
            Column(
                Modifier.fillMaxSize().padding(30.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text("Welcome back.", fontSize = 32.sp, color = Color.White)
                Text("Your Minecraft launcher, built for Windows.", color = Muted, fontSize = 15.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    InfoCard("PLATFORM", "Windows 10 / 11", Modifier.weight(1f))
                    InfoCard("BUILD", "Native desktop EXE", Modifier.weight(1f))
                    InfoCard("STATUS", "Foundation preview", Modifier.weight(1f))
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Game directory", fontSize = 20.sp)
                        Text(gameDirectory, color = Muted, fontSize = 13.sp)
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Button(onClick = {
                                try {
                                    val chooser = java.awt.FileDialog(null as java.awt.Frame?, "Choose Minecraft folder", java.awt.FileDialog.LOAD)
                                    chooser.isVisible = true
                                    if (chooser.directory != null) {
                                        gameDirectory = File(chooser.directory).absolutePath
                                        status = "Selected folder: $gameDirectory"
                                    }
                                } catch (e: Exception) {
                                    status = "Could not open folder chooser: ${e.message}"
                                }
                            }) { Text("Choose folder") }
                            OutlinedButton(onClick = {
                                try {
                                    val dir = File(gameDirectory)
                                    dir.mkdirs()
                                    if (Desktop.isDesktopSupported()) Desktop.getDesktop().open(dir)
                                    status = "Opened game directory."
                                } catch (e: Exception) {
                                    status = "Could not open folder: ${e.message}"
                                }
                            }) { Text("Open folder") }
                        }
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Launcher integration", fontSize = 20.sp)
                        Text(
                            "This is the first native Windows shell. The Android app remains unchanged. " +
                                "Minecraft account authentication, version/mod installation, runtime setup, " +
                                "and actual game launching still need a Windows-compatible implementation.",
                            color = Muted,
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )
                        Button(onClick = {
                            try {
                                Desktop.getDesktop().browse(java.net.URI("https://github.com/sureshkumak26-art/Anime-Launcher"))
                                status = "Opened Anime Launcher repository."
                            } catch (e: Exception) {
                                status = "Could not open repository: ${e.message}"
                            }
                        }) { Text("Open GitHub repository") }
                    }
                }
                Spacer(Modifier.weight(1f))
                Text(status, color = Mint, fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun InfoCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Panel)
    ) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(label, color = Muted, fontSize = 11.sp)
            Text(value, color = Color.White, fontSize = 17.sp)
        }
    }
}

private fun defaultGameDirectory(): File {
    val appData = System.getenv("APPDATA")
    return if (appData.isNullOrBlank()) File(System.getProperty("user.home"), "AppData/Roaming/.minecraft")
    else File(appData, ".minecraft")
}
