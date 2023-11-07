# NeoPerformance - Lag Management Utility for Minecraft Servers

NeoPerformance is a powerful anti-lag and lag management utility designed to ensure your Minecraft server runs smoothly and efficiently. This plugin is packed with features that help prevent lag machines, optimize standard gameplay, and enhance server performance. It also comes with optional requirements for advanced metrics using Spark. NeoPerformance can be customized to suit your server's specific needs and automatically identifies and resolves sources of lag.

## Features

- **Lag Prevention**: Prevent server lag by stopping various forms of lag machines and optimizing regular gameplay.
- **Server Crash Prevention**: Halt the server when it reaches a set TPS to prevent crashes.
- **SmartClear**: Efficiently remove large clusters of entities, a superior method compared to standard entity removal plugins.
- **Chunks**: Identify the top chunks with the most entities, helping you pinpoint lag sources.
- **SmartNotify**: Notifies administrators when excessive entities are causing lag.
- **Email Alerts**: Receive emails when the server is lagging.
- **Highly Customizable**: Fine-tune NeoPerformance to meet your server's unique requirements by configuring the YAML file.
- **Auto Lag Detection**: Automatically identify the source of lag and perform server cleanup.

## Optional Requirements

- **Spark Integration**: If you have Spark installed on your server, NeoPerformance can hook into it and provide additional metrics. [Download Spark here](#).

## Commands and Permissions

- `/Neoperformance` or `/np` (`neoperformance.admin`): Display server status for administrators.
- `/np help` (`neoperformance.help`): List all available commands and their usage.
- `/np halt` (`neoperformance.halt`): Manually halt the server.
- `/np chunks <world>` (`neoperformance.chunks`): Show chunks with the most entities. Use `/np chunks` to display the top 10 chunks for all worlds and `/np chunks <world>` for the top ten in the specified world.
- `/np reload` (`neoperformance.reload`): Reload the plugin's configuration file.
- `/np bypass <player>` (`neoperformance.bypass` or use `neoperformance.bypass.auto`): Allow specific players to bypass the server halt.
- `/np smartclear <flags>` (`neoperformance.smartclear`): Destroy entity clusters using SmartClear.
- For SmartNotify, an integrated notification system with chunks and clusters, either have operator permissions or `neoperformance.smartnotify`.
- `/np config` (`neoperformance.config`): Open the in-game config editor.
- `/np report` (`neoperformance.report`): Generate a report of your server's overall performance.

## Lag Prevention

- Prevents excessive explosions.
- Prevents too many mob spawns.
- Prevents lag machines, such as an abundance of minecarts.

## Crash Prevention

If the plugin fails to prevent lag, NeoPerformance will enable halt mode, stopping the following activities until the server reaches a stable TPS:

- Teleportation.
- Players moving too fast (speed hacks, etc).
- Entity explosions.
- Redstone (Redstone activity is cached and then restored once the server reaches a stable TPS, ensuring redstone contraptions won't break).
- Chunk loading.
- Entity spawn events (and items from being dropped).
- Command blocks executing commands.
- Blocks being broken (to prevent items from being dropped).
- Projectiles.
- Entity targeting.
- Block physics.
- Players from joining (extreme lag can be caused by too many players; the plugin stops players from joining until the server is in a playable state).

## To-Do List

- [ ] Add schedulers for more effective lag management.
- [ ] Enhance the SmartClear feature for better entity management.
- [ ] Optimize server cleanup and lag detection algorithms.
- [ ] Ensure compatibility with all Minecraft versions.

Feel free to contribute to NeoPerformance by forking the repository and submitting pull requests. Your help is greatly appreciated in making Minecraft servers lag-free and enjoyable for players!

For more information, questions, or support, please visit the [NeoPerformance GitHub repository](https://github.com/KyTDK/NeoPerformance).
