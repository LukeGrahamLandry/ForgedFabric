# ForgedFabric

The goal of this project is to enable porting fabric mods to forge with as few changes to their codebase as possible. This would make it easy for existing fabric mods to maintain a forge version without manually porting every update or rewriting to use a different api that provides a platform abstraction layer.

ForgedFabric is a reimplementation of the Fabric API on Forge. Same idea as [QuiltMC/quilted-fabric-api](https://github.com/QuiltMC/quilted-fabric-api). 
Useful in porting mods from fabric to forge and developing multiplatform mods in general. 
It's currently in the early very stages of development. I'm only implementing parts as they're used by mods that I'm working on. 
Eventually it should transition to being a fork of [FabricMC/fabric](https://github.com/FabricMC/fabric) and trying to maintain parity with the full fabric api.

- See [examples.md](examples.md) for a list of mods that use this library. Their source is linked, so you can see how it works.
- See [changelog.md](changelog.md) for a list of which parts of the fabric api are supported by each version of ForgedFabric. 
- See [dev.md](dev.md) if you're interested in more information on past problems and experiments during development of ForgedFabric. 
 

## Structure

Any parts of the fabric api that are used must be duplicated in this library.
The api classes must have exactly the same names, methods and package structure.
Then their functionality is reimplemented using forge events or mixins.

The signature of methods and fields must match the fabric api exactly to avoid NoSuchFieldError/NoSuchMethodError.
For example, it is not enough for `ServerPlayConnectionEvents#register` to accept a `TriConsumer<ServerPlayNetworkHandler, PacketSender, MinecraftServer>`,
it must take a `Join` which is a functional interface that exposes a `onPlayReady` method corresponding to that consumer.
Also, interfaces must be in the same package/class as they are in the fabric api. In that example, the `PackeSender` must be an inner class since it will not be found if it is its own file in the package.
Make sure the return types of methods are the same! Even though the result is never used by the mod, `ServerPlayNetworking#registerGlobalReceiver` must return a boolean or the method will not be recognized. 

ForgedFabric may be added to your project as a dependency. Take care that event handlers get registered properly when shadowed. 

## Problems 

- mods adding mixins to places where forge already has a patch are a bit scary 
- forge registry shenanigans. perhaps terrible solution: mixin to `Registry#register` that redirects to deferred registers based on the mod id of the identifier  
- other multiplatform libraries used from common code need to have exactly the same method signatures for everything you use 
- still tedious. gradle script that generates mods.toml, mod main class that calls initializers, pack.mcmeta. Even automatically wrapping registry and FabricLoader calls with an ExpectPlatform abstraction should be possible
- if another mod does the same clever trick with having the fabric api packages, they'll conflict
- can't provide the FabricLoader class because Architectury's ExpectPlatform uses its presence to detect when it's running on fabric and looks for that platform impl in other mods

## Tips 

- common code can check if it's running on forge by checking for the mod id `forge`
- architectury-loom lets you use yarn mappings on forge. if you stay with yarn when porting fabric mods you can merge upstream changes easily
- you must manually call your ModInitializer and ClientModInitializer from your forge mod
- forge must load `FabricOnForgeMod` as a mod, be careful when shadowing
- if you include the library in your jar, make sure to `relocate` the `net.fabricmc` package so your mod doesn't conflict with this or others that try to do the same thing

## Licensing

- ForgedFabric is Copyright 2022 LukeGrahamLandry, available under the Apache License 2.0, you can pretty much do whatever you want with it.
- ForgedFabric uses the same package and method names as the Fabric API, Copyright (c) 2016, 2017, 2018, 2019 FabricMC under the [Apache License 2.0](https://github.com/FabricMC/fabric/blob/1.19.2/LICENSE). 
- ForgedFabric includes ZsoltMolnarrr/TinyConfig, v1.0.2, Copyright (c) 2022 under the [MIT License](https://github.com/ZsoltMolnarrr/TinyConfig/blob/be53fb3091555c09bcd59508d9be9c8b04bfba16/LICENSE).
