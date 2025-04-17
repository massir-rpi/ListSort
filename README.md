# List Sort
This is a simple sorted list of entries from a database of items.

## Core features
* **Compose**: This is a single screen implemented in JetPack Compose with a LazyColumn.
* **Modern Navigation**: This is wrapped in a type-safe Compose navigation layer.
* **Retrofit Networking**: The network layer is implemented in Retrofit with Dagger/Hilt providers.
* **Dynamic Theming**: Components use a MaterialTheme that adapts to light and dark modes. 

## Bells and whistles
* **Two display modes**: Toggle between displaying items in a single list or tabulated by list ID.
* **Built to scale**: Wrapping in robust architecture allows room for future work.